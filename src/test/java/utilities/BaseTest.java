package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import io.qameta.allure.Attachment;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;

public class BaseTest {

    private static final Logger logger = LoggerFactory.getLogger(BaseTest.class);

    protected Playwright playwright;
    protected Browser browser;
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
        test = extent.createTest(testInfo.getDisplayName());
        test.info("Test hazırlıkları tamamlanıyor...");

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
        test.pass("Tarayıcı ve Playwright hazır.");    }

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
