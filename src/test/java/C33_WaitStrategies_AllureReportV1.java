import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.options.WaitForSelectorState;
import io.qameta.allure.*;
import org.junit.jupiter.api.Test;
import utilities.BaseTest;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

@Epic("Playwright Bekleme Stratejileri")
@Feature("Wait Management - V1")
public class C33_WaitStrategies_AllureReportV1 extends BaseTest {
    /*
     * ==========================================================================================
     * PLAYWRIGHT BEKLEME STRATEJİLERİ - ALLURE V1 YAPILANDIRMASI
     * ==========================================================================================

     ============================================================================================
     PLAYWRIGHT BEKLEME STRATEJİLERİ (WAIT STRATEGIES) DERS NOTLARI
     ============================================================================================
     UYARI: Thread.sleep() otomasyonun düşmanıdır! Testi gereksiz uzatır ve kararsız (flaky) yapar.
     Playwright'ta bekleme yönetimi 5 ana kategoriye ayrılır:

     --------------------------------------------------------------------------------------------
     1. AUTO-WAITING (Otomatik Bekleme)
     --------------------------------------------------------------------------------------------
     * Playwright bir aksiyon (click, fill, vb.) yapmadan önce elementin;
     Görünür (Visible), Stabil, Etkileşime hazır (Enabled) olmasını otomatik bekler.
     * Örnek: page.click("#login"); // Playwright buton hazır olana kadar sessizce bekler.

     --------------------------------------------------------------------------------------------
     2. SAYFA YÜKLEME BEKLEMELERİ (waitForLoadState)
     --------------------------------------------------------------------------------------------
     * Sayfanın ağ trafiğine veya yüklenme durumuna göre bekler.
     * a) LOAD: HTML indiğinde (Resimler yükleniyor olabilir).
     * b) DOMCONTENTLOADED: HTML işlendiğinde.
     * c) NETWORKIDLE (En Güçlü): Ağ trafiği durduğunda (500ms boyunca yeni istek yoksa).
     * Örnek: page.waitForLoadState(LoadState.NETWORKIDLE); // Ağır siteler ve reklamlar için ideal.

     --------------------------------------------------------------------------------------------
     3. EXPLICIT WAITS (Belirli / Açık Beklemeler)
     --------------------------------------------------------------------------------------------
     * Belirli bir şartın gerçekleşmesini beklediğimiz en profesyonel yöntemdir.

     * a) Element Durum Beklemesi (waitForSelector):
     - VISIBLE: Element hem DOM'da olsun hem ekranda görünsün.
     - HIDDEN: Elementin kaybolmasını bekler (Örn: Loading simgesi).
     - ATTACHED: DOM'da olsun yeter (Gizli inputlar için).
     Örnek: page.waitForSelector("#search", new Page.WaitForSelectorOptions().setState(WaitForSelectorState.VISIBLE));

     * b) Navigasyon Beklemesi (waitForNavigation):
     - Tıklama sonrası URL'nin değişmesini ve sayfanın yüklenmesini garanti eder.
     Örnek: page.waitForNavigation(() -> page.getByText("Giriş").click());

     * c) URL Beklemesi (waitForURL):
     - URL belirli bir formata gelene kadar bekler.
     Örnek: page.waitForURL("* * /dashboard");  NOT:*'lar ve / arasında boşluk olmayacak.

     --------------------------------------------------------------------------------------------
     4. PLAYWRIGHT ASSERTIONS (Akıllı Doğrulamalar)
     --------------------------------------------------------------------------------------------
     * assertThat() kullanıldığında Playwright, o şart sağlanana kadar (default 5sn) bekler.
     * Örnek: assertThat(page.locator(".success")).isVisible(); // Görünene kadar bekleyip sonra doğrular.

     --------------------------------------------------------------------------------------------
     5. İLERİ SEVİYE BEKLEMELER (Functional & API)
     --------------------------------------------------------------------------------------------
     * a) waitForFunction: Kendi JS kuralını yazarsın.
     Örnek: page.waitForFunction("() => document.querySelectorAll('.item').length > 3");

     * b) waitForResponse: Arka plandaki API cevabını bekler.
     Örnek: page.waitForResponse("* * /api/update", () -> page.click("#save")); NOT:*'lar ve / arasında boşluk olmayacak.

     --------------------------------------------------------------------------------------------
     ÖZET KARŞILAŞTIRMA TABLOSU
     --------------------------------------------------------------------------------------------
     | Bekleme Türü      | Hız        | Güvenilirlik | Kullanım Alanı                          |
     |-------------------|------------|--------------|-----------------------------------------|
     | Auto-Waiting      | Çok Hızlı  | Orta         | Standart tıklama/yazma işlemleri.       |
     | NetworkIdle       | Yavaş      | Çok Yüksek   | Ağır, bol reklamlı ve yavaş sayfalar.   |
     | Visible State     | Hızlı      | Yüksek       | Dinamik açılan pencereler ve mesajlar.  |
     | Assertions        | Orta       | Yüksek       | Testin doğruluğunu kontrol ederken.     |
     ============================================================================================
     */

        @Test
        @Story("allureStep Kancası ile Bekleme Yönetimi")
        @Description("V1: allureStep metodu kullanılarak oluşturulan lineer bekleme stratejileri testi")
        @Severity(SeverityLevel.CRITICAL)
        @Owner("Ceren KILIÇ")
        public void WaitStrategiesTestV1() {

            allureStep("1. Giriş: Ana sayfa navigasyonu ve DOM yükleme bekleyişi");
            page.navigate("https://automationexercise.com/");

            // [STRATEJİ NOTU]:
            // DOMCONTENTLOADED:
            // Neden: Sayfanın en temel yapısının (HTML) hazır olması işlemleri hızlandırır.
            // Sadece HTML hazır olsun yeter der, hızlıdır. (Bazen takılabilir)
            //page.waitForLoadState(LoadState.DOMCONTENTLOADED);
            // LOAD: Resimler ve reklamlar dahil her şey yüklensin der, daha garantidir.
            // Eğer site kararsızsa LOAD kullanmak testi daha stabil hale getirir.
            page.waitForLoadState(LoadState.LOAD);



            allureStep("2. Navigasyon: Ürünler sayfasına geçiş ve URL doğrulaması");
            // Auto-waiting burada devreye girer: Element görünür ve stabil olana kadar tıkla demez.
            page.getByText(" Products").click();

            // Niçin: Reklamlar bazen sayfa değişimini engeller. URL değişmediyse manuel yönlendiriyoruz.
            // Reklam kontrolü ve bypass
            if (!page.url().contains("/products")) {
                page.navigate("https://automationexercise.com/products");
            }
            // waitForURL Kullanımı: Sayfanın gerçekten 'products' sayfasına geçtiğinden emin olana kadar bekler.
            page.waitForURL("**/products");

            allureStep("3. Explicit Wait: Arama kutusunun görünürlüğü için 15sn limitli bekleme");
            // Neden: Dinamik sitelerde element DOM'a gelse bile görünür olması zaman alabilir.
            page.waitForSelector("#search_product", new Page.WaitForSelectorOptions()
                    .setState(WaitForSelectorState.VISIBLE)
                    .setTimeout(15000));
            page.locator("#search_product").fill("tshirt");

            allureStep("4. Arama Sonuçları: API Response ve URL Doğrulaması");
            /*
             * Butona bastığımızda URL 'products?search=tshirt' olarak değişiyor.
             * Bu bir "Sayfa Navigasyonu"dur.
             *
             * [NOT]:
             * Teknik olarak 'waitForResponse' ile de bu URL'nin 200 dönmesini yakalayabiliriz.
             * Ancak en temiz yaklaşım: Sayfa değişiyorsa waitForURL, veri arka planda akıyorsa waitForResponse'dur.
             */
            page.waitForResponse(
                    // URL'nin 'products?search=' içermesini ve sunucunun '200 OK' dönmesini bekle.
                    // Dinamik olması için 'tshirt' yerine genel bir kalıp (search=) kullandık.
                    response -> response.url().contains("products?search=") && response.status() == 200,
                    () -> {
                        page.locator("#submit_search").click();
                    }
            );

            /*
             * KRİTİK AYRIM:
             * Eğer bu site modern bir React/Vue sitesi olsaydı (sayfa yenilenmeden veri gelseydi),
             * yukarıdaki waitForResponse hayat kurtarırdı.
             * Bu sitede sayfa yenilendiği için aşağıdaki waitForURL kullanımı daha doğru bir kullanım olarak kabul edilir:
             */
            page.waitForURL("**/products?search=tshirt");

            /*
             * ====================================================================================================
             *                          BEKLEME STRATEJİLERİ ÖZET TABLOSU
             * ====================================================================================================
             * | Durum                        | Kullanılacak Kod            | Mantık                               |
             * |------------------------------|-----------------------------|--------------------------------------|
             * | Sayfa Komple Yenileniyor     | waitForURL()                | Adres çubuğundaki değişimi baz alır. |
             * | Sadece Veri Güncelleniyor    | waitForResponse()           | Arka plandaki API trafiğini dinler.  |
             * | Butona bastım, bir şey açıldı| waitForSelector(VISIBLE)    | Elementin ekrandaki varlığına bakar. |
             * ====================================================================================================
             */

            allureStep("5. Doğrulama: Smart Assertion ile sonuçların kontrolü");
            // Neden: assertThat() kendi içinde 'retry' (tekrar deneme) mekanizmasına sahiptir.
            // Default 5 saniye boyunca elementin isVisible olmasını bekler.
            assertThat(page.locator(".features_items")).isVisible();

            allureStep("6. Final: Network Idle ile sayfa trafiğinin durmasını bekleme");
            page.waitForLoadState(LoadState.NETWORKIDLE);
            // Not:Bu strateji çok güçlüdür ama yavaş sitelerde testi gereksiz uzatabilir.
            // Playwright dökümantasyonu genellikle bunun yerine spesifik bir elementin görünmesini beklemeyi (isVisible) önerir.

        }

        @Step("{message}")
        public void allureStep(String message) {
            // Bu metodun gövdesi boş kalır, @Step notasyonu işi yapar.
        }
}