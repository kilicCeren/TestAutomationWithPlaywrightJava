import io.qameta.allure.*;
import org.junit.jupiter.api.Test;
import utilities.BaseTest;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

/**
 * ==============================================================================
 * PLAYWRIGHT SCROLL STRATEJİLERİ - ALLURE V1 YAPILANDIRMASI
 * ==============================================================================
 * Bu versiyonda 'allureStep' yardımcı metodu kullanılarak rapor beslenir.
 * Adımlar doğrusal (linear) bir liste halinde raporlanır.
 * * TEKNİK ÖZET:
 * 1. Auto-Scroll: Playwright aksiyon öncesi elementi otomatik bulur.
 * 2. scrollIntoViewIfNeeded(): Elementi görünür kılmak için manuel tetikleme.
 * 3. Mouse Wheel: Piksel bazlı manuel kaydırma (x,y koordinatları).
 * ==============================================================================
 */

@Epic("Web Element Etkileşimleri")
@Feature("Scroll ve Görünürlük Yönetimi - V1")
public class C31_Scroll_AllureReportV1 extends BaseTest {

    @Test
    @Story("Metod Notasyonları ile Sayfa Kaydırma Teknikleri")
    @Description("V1: allureStep kancası (hook) kullanılarak oluşturulan scroll testi")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Ceren KILIÇ")
    public void scrollTest() {

        allureStep("1. Hazırlık: Ana sayfa ziyareti gerçekleştiriliyor");
        page.navigate("https://automationexercise.com/");

        allureStep("2. Otomatik Odaklanma: Subscription alanına kaydırılıyor");
        /*
         * .scrollIntoViewIfNeeded(): Playwright elemente aksiyon yapmadan önce
         * otomatik kaydırır ancak bazen manuel odaklanma gerekebilir.
         */
        page.getByText("Subscription").scrollIntoViewIfNeeded();

        allureStep("3. Doğrulama: Subscription yazısının görünürlüğü kontrol ediliyor");
        assertThat(page.getByText("Subscription")).isVisible();

        allureStep("4. Manuel Scroll: Mouse Wheel ile 1000 birim aşağı iniliyor");
        /*
         * page.mouse().wheel(x, y):
         * Pozitif y değeri aşağı, negatif y değeri yukarı kaydırır.
         */
        page.mouse().wheel(0, 1000);

        allureStep("5. Final: Sayfa sonu (Footer) alanı doğrulanıyor");
        assertThat(page.locator("#footer")).isVisible();
    }

    @Step("{message}")
    public void allureStep(String message) {
        /* * Bu metod Allure interceptor'ını tetikler.
         * V1 yapısında her adımın raporda görünmesini sağlayan 'kanca' (hook) metodudur.
         */
    }
}