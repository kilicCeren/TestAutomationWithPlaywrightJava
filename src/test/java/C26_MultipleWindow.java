import com.microsoft.playwright.Page;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Test;
import utilities.BaseTest;

import java.util.List;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class C26_MultipleWindow extends BaseTest {

    @Test
    @Description("Kullanıcı çoklu pencere (Popup) yönetimini test eder")
    @Severity(SeverityLevel.NORMAL)
    public void multipleWindowTest() {

        allureStep("Browser Windows test sayfası ziyaret ediliyor");
        page.navigate("https://demoqa.com/browser-windows");

        allureStep("Yeni pencere açılması bekleniyor ve butona tıklanıyor");
        // waitForPopup, tıklama sonrası açılacak olan pencereyi bir "popup" objesi olarak yakalar
        //NOT: tek bir yeni sayfa/tab açılıyorsa bu lambda kod yeterlidir.
        Page popup = page.waitForPopup(() -> {
            page.getByText("New Window").first().click();
        });

        /*
        Not:Eğer tıkladığın buton bazen 2-3 pencereyi birden tetikliyorsa veya sayfanın açılması çok karmaşık
         şartlara bağlıysa bu Predicate (koşul) yapısını kullanabiliriz.

                Page popup = page.waitForPopup(new Page.WaitForPopupOptions().setPredicate(
                p->p.context().pages().size()==2),()->{page.getByText("New Window").first().click();}
        );
         */

        allureStep("Yeni açılan pencerenin URL'si doğrulanıyor");
        assertThat(popup).hasURL("https://demoqa.com/sample");
        System.out.println("Ana sayfanın URL'i: "+page.url());
        System.out.println("Açılan New window'un URL'i: "+popup.url());

        allureStep("Yeni pencere içindeki başlık metni doğrulanıyor");
        assertThat(popup.locator("#sampleHeading")).hasText("This is a sample page");

        allureStep("Toplam açık olan sayfa sayısı kontrol ediliyor");
        List<Page> allPages = popup.context().pages();
        System.out.println("Toplam sayfa sayısı: " + allPages.size());

        // Basit bir Java doğrulaması (Opsiyonel)
        assert allPages.size() == 2 : "Sayfa sayısı beklenen (2) adet değil!";

        allureStep("Yeni pencere kapatılıyor ve ana sayfaya dönülüyor");
        popup.close();

        // Ana sayfanın hala orada olduğunu doğrulayalım
        assertThat(page).hasURL("https://demoqa.com/browser-windows");


    }

    @Step("{message}")
    public void allureStep(String message) {
        // Bu method : Allure interceptor'ını tetikleyerek parametreyi rapor adımı olarak işler.
        // Yani Allure sistemine bir Hook atar. Ben metodu çağırdığımda o kancaya takılır ve Allure'un mekanizmasını çalıştırıp raporu günceller.
    }
}
