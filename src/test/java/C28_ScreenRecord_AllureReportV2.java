import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import io.qameta.allure.*;
import org.junit.jupiter.api.Test;
import utilities.BaseTest;

import java.nio.file.Paths;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

/**
 * ==============================================================================
 * ALLURE V1 vs V2 KARŞILAŞTIRMALI ANALİZ NOTLARI
 * ==============================================================================
 * Özellik          | V1                                | V2
 * -----------------|-----------------------------------|--------------------------
 * Metod Yapısı     | @Step içeren boş metod zorunlu.   | Allure.step() doğrudan kullanılır.
 * Hata Takibi      | Test geneli için görünür.         | İlgili Lambda bloğu altında kırmızı yanar.
 * Clean Code       | Metod kalabalığı (Boilerplate).   | Kütüphane odaklı, temiz ve kompakt.
 * Görsel Rapor     | Tek düze bir liste.               | Katlanabilir "Sub-step" ağacı.
 * ==============================================================================
 */

/**
 * [V2 - PROFESYONEL ALLURE ENTEGRASYONU]
 * * Bu clas, V1 versiyonundaki "allureStep" yardımcı metoduna olan bağımlılığı bitirir.
 * Farklar ve Yenilikler:
 * 1. Lambda Expressions: Allure.step() içine gömülen kod blokları sayesinde "Sub-Step" (Alt Adım) hiyerarşisi kurulur.
 * 2. Dinamik Takip: Kod bloğu içindeki herhangi bir hata, doğrudan o adımın altında raporlanır.
 * 3. Hiyerarşik Gruplama: @Epic, @Feature ve @Story ile test raporu daha kurumsal bir dökümana dönüşür.
 */

@Epic("Görsel Kayıt Sistemleri")
@Feature("Video Kayıt Özelliği")
public class C28_ScreenRecord_AllureReportV2 extends BaseTest {

    @Test
    @Story("Kullanıcı tarayıcı işlemlerini videoya alabilmeli")
    @Description("V2: Lambda fonksiyonları ile geliştirilmiş, Sub-Step yapılı Video Kayıt Testi")
    @Severity(SeverityLevel.NORMAL)
    @Issue("QA-456")
    @TmsLink("TEST-101") // Test Case yönetim sistemine link
    public void videoRecordTest() {

        // 1. ADIM Allure'un kendi step metodunu kullanıyoruz
        Allure.step("Video kayıt ayarları yapılandırılıyor", () -> {
            String dosyaYolu = "target/videos";
            BrowserContext context = browser.newContext(new Browser.NewContextOptions()
                    .setRecordVideoDir(Paths.get(dosyaYolu))
                    .setRecordVideoSize(1280, 720));

            Page recordPage = context.newPage();

            // 2. ADIM: İşlemleri gerçekleştiriyoruz
            Allure.step("Test Otomasyonu sitesine gidiliyor ve işlemler yapılıyor", () -> {
                recordPage.navigate("https://www.testotomasyonu.com");
                recordPage.locator(".menu-icon-text").first().click();

                assertThat(recordPage).hasTitle("Test Otomasyonu");
            });

            // 3. ADIM: Videoyu kapat ve raporla
            Allure.step("Video kaydı sonlandırılıyor", () -> {
                context.close();
                // Opsiyonel: Videonun kaydedildiği yolu rapora bir parametre olarak ekle
                Allure.parameter("Video Kayıt Yolu", recordPage.video().path().toString());
            });
        });
    }

    /*
     * ==================================================================================
     * ALLURE NOTASYON VE METOD REHBERİ
     * ==================================================================================
     * * [ TEMEL NOTASYONLAR ]
     * @Epic        -> En üst seviye gruplama. Örn: "Müşteri Alışveriş Yolculuğu"
     * @Feature     -> Epic altındaki büyük özellik. Örn: "Ödeme Sistemleri"
     * @Story       -> Feature altındaki spesifik senaryo. Örn: "Kredi Kartı ile Ödeme"
     * @Severity    -> Testin kritiklik seviyesi (Blocker, Critical, Normal, Minor, Trivial)
     * @Description -> Testin neyi hedeflediğini anlatan detaylı açıklama.
     * * [ TAKİP VE KANIT ARAÇLARI ]
     * @Issue / @TmsLink -> Jira Bug ID veya Test Case linki (Örn: JIRA-123)
     * @Attachment       -> Rapora ekran görüntüsü veya video ekler.
     * Allure.step()     -> Kodun çalıştığı o anı rapor satırı olarak asar.
     * Allure.parameter()-> Testte kullanılan veriyi (User, URL, Path) rapora yazar.
     * * [ LIFECYCLE METODLARI ]
     * Allure.getLifecycle() -> Raporlama sürecine (başlangıç, bitiş, hata) müdahale etmeyi sağlar.
     * Allure.addAttachment()-> Çalışma anında (runtime) rapora dosya eklemek için kullanılır.
     * Allure.label()        -> Raporu filtrelemek için özel etiketler (Owner, Tag vb.) ekler.
     * ==================================================================================
     */

}
