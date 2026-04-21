import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Page;
import io.qameta.allure.*;
import org.junit.jupiter.api.Test;
import utilities.BaseTest;

import java.nio.file.Paths;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

/**
 * Allure Report & Video Recording Notları:
 * 1. setRecordVideoDir: Videoların kaydedileceği klasörü belirler.
 * 2. Video kaydı ancak context.close() yapıldığında dosyaya tam olarak yazılır.
 */

@Epic("Görsel Kayıt Sistemleri") // En üst seviye başlık
@Feature("Video Kayıt Özelliği") // Özellik başlığı
public class C27_ScreenRecord extends BaseTest {

    @Test
    @Story("Kullanıcı tarayıcı işlemlerini videoya alabilmeli") // Spesifik senaryo
    @Description("Kullanıcı işlemlerinin video kaydına alınması testi")
    @Severity(SeverityLevel.MINOR)
    @Issue("QA-456") // Eğer bir bug report linkin varsa buraya
    public void videoRecordTest() {

        // Video kaydı için özel bir context oluşturuyoruz
        allureStep("Video kayıt ayarları yapılandırılıyor ve yeni context açılıyor");
        String dosyaYolu = "target/videos";

        BrowserContext context = browser.newContext(new Browser.NewContextOptions()
                .setRecordVideoDir(Paths.get(dosyaYolu))
                .setRecordVideoSize(1280, 720)); // Standart HD boyut daha net olur

        // ÖNEMLİ: BaseTest'ten gelen 'page' yerine bu context'e bağlı yeni bir 'page' oluşturmalıyız
        Page recordPage = context.newPage();

        allureStep("Test Otomasyonu sitesine gidiliyor");
        recordPage.navigate("https://www.testotomasyonu.com");

        allureStep("Menü ikonuna tıklanıyor");
        recordPage.locator(".menu-icon-text").first().click();

        allureStep("Sayfa başlığı doğrulanıyor");
        assertThat(recordPage).hasTitle("Test Otomasyonu");

        allureStep("Video kaydı sonlandırılıyor ve dosya kaydediliyor");
        // Video dosyasının tam oluşması için context'in kapanması şarttır!
        context.close();

        // Kaydedilen videonun yolunu konsola yazdıralım (opsiyonel)
        System.out.println("Video kaydedildi: " + recordPage.video().path());
    }
    @Step("{message}")
    public void allureStep(String message) {
        // Bu method : Allure interceptor'ını tetikleyerek parametreyi rapor adımı olarak işler.
        // Yani Allure sistemine bir Hook atar. Ben metodu çağırdığımda o kancaya takılır ve Allure'un mekanizmasını çalıştırıp raporu günceller.
    }

}
