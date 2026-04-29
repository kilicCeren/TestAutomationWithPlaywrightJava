import io.qameta.allure.*;
import org.junit.jupiter.api.Test;
import utilities.BaseTest;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

/**
 * ==============================================================================
 * PLAYWRIGHT SCROLL STRATEJİLERİ VE ALLURE V2 YAPILANDIRMASI
 * ==============================================================================
 * Bu class, web sayfalarında navigasyon ve elemente odaklanma tekniklerini gösterir.
 * * TEKNİK ÖZET:
 * 1. Auto-Scroll: Playwright aksiyon öncesi elementi otomatik bulur ve kaydırır.
 * 2. scrollIntoViewIfNeeded(): Elementi görünür kılmak için zorunlu odaklanma sağlar.
 * 3. Mouse Wheel: Koordinat tabanlı manuel kaydırma (Sonsuz scroll durumları için).
 * ==============================================================================
 */

@Epic("Web Element Etkileşimleri")
@Feature("Scroll ve Görünürlük Yönetimi")
public class C32_Scroll_AllureReportV2 extends BaseTest {

    @Test
    @Story("Sayfa İçi Kaydırma ve Element Odaklanma Teknikleri")
    @Description("V2: Lambda adımları ile otomatik ve manuel scroll stratejilerinin uygulanması")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Ceren KILIÇ")
    public void scrollTest() {

        // ANA ADIM 1: Otomatik Odaklanma (Auto-Scroll)
        Allure.step("1. Hazırlık: Ana sayfa ziyareti ve Element Odaklanma", () -> {
            page.navigate("https://automationexercise.com/");

            /*
             * .scrollIntoViewIfNeeded():
             * Playwright bir elemente aksiyon (tıkla, yaz vb.) yapmadan önce zaten oraya kaydırır.
             * Ancak bazen sadece elementin görünmesini sağlamak ve ekran görüntüsü almak
             * istiyorsak bu metodu manuel tetikleriz.
             */
            Allure.step("Alt bilgi alanına (Subscription) otomatik odaklanılıyor", () -> {
                page.getByText("Subscription").scrollIntoViewIfNeeded();

                // Assertions (Doğrulama) - Playwright burada element gelene kadar akıllıca bekler.
                assertThat(page.getByText("Subscription")).isVisible();
            });
        });

        // ANA ADIM 2: Manuel Kaydırma (Mouse Wheel)
        Allure.step("2. Gelişmiş Teknik: Mouse Wheel ile Manuel Scroll", () -> {

            /*
             * page.mouse().wheel(x, y):
             * Sayfayı belirli piksel değerlerinde kaydırır.
             * x: Yatay eksen (0 sabit)
             * y: Dikey eksen (Pozitif değer aşağı, Negatif değer yukarı kaydırır)
             */
            Allure.step("Sayfa manuel olarak 1000 birim aşağı kaydırılıyor", () -> {
                page.mouse().wheel(0, 1000);
                Allure.description("Manuel scroll ile sayfa sonundaki footer alanı kontrol ediliyor.");
            });

            // Final Kontrolü
            Allure.step("Sayfa sonu (Footer) doğrulanıyor", () -> {
                assertThat(page.locator("#footer")).isVisible();
            });
        });
    }
}