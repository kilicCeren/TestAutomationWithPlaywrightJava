import org.junit.jupiter.api.Test;
import utilities.BaseTest;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class C22_BaseTestKullanimi_ExtentReport extends BaseTest {

    @Test
    public void test(){

        // Log Events: Buraya bilgi eklemek için test.info(), test.pass(), test.fail() veya test.warning() metotlarını kullanabililiriz

        test.info("Web sitesine gidiliyor: " + "http://www.testotomasyonu.com");
        page.navigate("http://www.testotomasyonu.com");

        test.info("Tarayıcı açıldı,sayfa başarıyla yüklendi.");
        assertThat(page).hasTitle("Test Otomasyonu - Test Otomasyonu");
        test.pass("Title  başarıyla görüntülendi.");
    }


}
