package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.microsoft.playwright.*;
import io.qameta.allure.Attachment;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;


/**
 * ==============================================================================
 * BASE TEST - EVRİMSEL GELİŞİM NOTLARI (V1 vs V2)
 * ==============================================================================
 * Bu sınıf, Playwright testlerinin temel yapılandırmasını içerir.
 * Zaman içinde V1'den V2'ye geçilerek daha stabil bir yapı hedeflenmiştir.
 * ==============================================================================
 */

public class BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(BaseTest.class);

    protected Playwright playwright;
    protected Browser browser;
    /** * [V2 Yenilik]: BrowserContext
     * Neden? Playwright'ta her testin birbirinden tamamen izole olması gerekir.
     * Context; çerezleri, oturumları ve yerel depolamayı (cache) birbirinden ayırır.
     * Bir testin çerezleri diğer testi kirletmez.
     */
    protected BrowserContext context; // Context eklendi (Entegrasyon için önemli) BaseTest Version 2
    protected Page page;

    // Raporlama Nesneleri: Statik yapı sayesinde tüm test sınıfları aynı rapor objesini kullanır.
    protected static ExtentReports extent = ExtentManager.getInstance();
    protected static ExtentTest test;



    @BeforeAll
    static void setupReport() {
        // Global rapor hazırlıkları buraya gelebilir.
    }
    @BeforeEach
    void setup(TestInfo testInfo) {

        logger.info(">>> Test başlıyor: {}", testInfo.getDisplayName());


        // Extent Report: Her test metodu için raporda yeni bir "Test Case" alanı oluşturur.
        test = extent.createTest(testInfo.getDisplayName());

        // Grafikleri tetiklemek için en az bir INFO logu düşmeliyiz
        test.info("Test hazırlıkları tamamlanıyor...");
        /*
         * ==========================================================
         * BASE TEST VERSION 2 (Güncel & Profesyonel Strateji)
         * ==========================================================
         * AVANTAJLARI:
         * 1. Tam Ekran Uyumu: --start-maximized argümanı ile tarayıcıyı fiziksel olarak yayarız.
         * 2. Viewport Esnekliği: setViewportSize(null) diyerek Playwright'ın sayfayı
         * mevcut pencereye göre otomatik ölçeklendirmesini sağlarız (Scroll işlemi sırasında yaşanılan küçülme sorunu çözüldü).
         * 3. İzolasyon: BrowserContext sayesinde her test "Gizli Sekme" tazeliğinde başlar.
         * ==========================================================
         */


        // 1. Playwright başlatma
        playwright = Playwright.create();

        // 2. Tarayıcı Ayarları: Chromium tabanlı, fiziksel ekranı kaplayan modda başlatılır.
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                .setHeadless(false)
                .setArgs(java.util.Arrays.asList(
                        "--start-maximized",   // Mevcut tam ekran argümanın
                        "--disable-quic"       // ERR_QUIC_PROTOCOL_ERROR  hatası alınmasın diye 'Protokol Hatası' engelleyici
                )));

        // 3. Context Ayarları: Viewport null verildiğinde 'Auto-Resize' aktif olur. Yani pencereye uyumlama
        // Bu sayede o küçülme/basıklık sorunu çözülür.
        context = browser.newContext(new Browser.NewContextOptions()
                .setViewportSize(null));

        // 4. Context üzerinden Sayfa Oluşturma: Her test için temiz bir sayfa (Page) açılır.
        page = context.newPage();

        page.setDefaultNavigationTimeout(30000);
        test.pass("Tarayıcı ve Playwright tam ekran modunda hazır.");

/*
         * ----------------------------------------------------------
         * [ARŞİV] BASE TEST VERSION 1 (Başlangıç Seviyesi)
         * ----------------------------------------------------------
         * DEZAVANTAJLARI:
         * - Toolkit ile alınan ekran pikselleri, tarayıcının adres çubuğu ve Windows
         * görev çubuğunu hesaba katmaz. Bu durum sayfanın "basık/küçülmüş" görünmesine neden olur.
         * - browser.newPage() doğrudan kullanıldığında default context oluşur, bu da
         * karmaşık test senaryolarında oturum yönetimi zorluğu çıkarabilir.

         * * KOD ÖRNEĞİ (BaseTest V1):

        // Dinamik Ekran Çözünürlüğü: Testlerin çalışacağı pencere boyutunu sistem çözünürlüğüne göre ayarlar.
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screenSize.width;
        int height = screenSize.height;

        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        page = browser.newPage();
        page.setViewportSize(screenSize.width, screenSize.height);

        // Timeout'u 60 yapmak yerine 30'da bırakıp, navigate ederken strateji belirlemek daha profesyoneldir.
        page.setDefaultNavigationTimeout(30000);
        test.pass("Tarayıcı ve Playwright hazır.");

 */
         }


    @AfterEach
    void teardown(TestInfo testInfo) {
        try {
             // Allure & Extent: Test sonunda tam sayfa ekran görüntüsü alıp raporlara ekler.
             byte[] screenshot = page.screenshot(new Page.ScreenshotOptions().setFullPage(true));
             saveScreenshot(testInfo.getDisplayName(), screenshot);
             test.info("Ekran görüntüsü alındı.");
            } catch (Exception e) {
            logger.error("Ekran görüntüsü alınırken hata oluştu: " + e.getMessage());
        }

        // Tarayıcı ve Playwright kaynaklarını güvenli bir şekilde kapatır.
        // Kapatma Sırası: Page -> Context -> Browser -> Playwright (En içten en dışa)
        if (context != null) context.close(); // Context kapatıldı - BaseTest Version 2
        if (browser != null) browser.close();
        if (playwright != null) playwright.close();

        // Extent Report: Verileri fiziksel HTML dosyasına yazar (Flush).
        extent.flush();
        logger.info("Test tamamlandı, kaynaklar kapatıldı.");
    }

    // Allure Attachment: Alınan byte dizisi halindeki ekran görüntüsünü Allure raporuna bağlar.
    @Attachment(value = "{name}", type = "image/png")
    public byte[] saveScreenshot(String name, byte[] screenshot) {
        return screenshot;
    }

}
