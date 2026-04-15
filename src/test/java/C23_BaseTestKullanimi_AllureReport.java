import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Test;
import utilities.BaseTest;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;


    /**
        * Allure Report Kullanım Notları:
        * 1. allure-results klasörünün oluşması için önce testin koşulmuş (run) olması gerekir.
        * 2. Raporu görüntülemek için Terminale: 'allure serve allure-results' yazılmalıdır.
    */
public class C23_BaseTestKullanimi_AllureReport extends BaseTest {


    @Test
    @Description("Kullanıcı anasayfa başlık kontrolü testi")
    @Severity(SeverityLevel.CRITICAL) // Testin önem derecesi
    public void test() {
        allureStep("Test Otomasyonu sitesine gidiliyor");
        page.navigate("http://www.testotomasyonu.com");

        allureStep("Sayfa başlığının doğruluğu kontrol ediliyor");
        assertThat(page).hasTitle("Test Otomasyonu - Test Otomasyonu");
    }

    @Step("{message}")
    public void allureStep(String message) {
        // Bu method : Allure interceptor'ını tetikleyerek parametreyi rapor adımı olarak işler.
        // Yani Allure sistemine bir Hook atar. Ben metodu çağırdığımda o kancaya takılır ve Allure'un mekanizmasını çalıştırıp raporu günceller.
    }
}
