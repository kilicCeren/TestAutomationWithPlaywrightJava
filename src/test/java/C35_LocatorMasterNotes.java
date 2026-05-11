import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

/**
 * 🏆 PLAYWRIGHT LOCATOR STRATEJİSİ & KARAR REHBERİ
 * ---------------------------------------------------
 * Bu sınıf, Playwright'ta element bulma işleminin
 * Kurallarını ve Teknik Detaylarını adım adım açıklar.
 * * 📌 TEMEL FELSEFE: 
 * "Teknik detayı (id, class) değil, kullanıcıyı takip et."
 */
public class C35_LocatorMasterNotes {

    /*
     * 🟢 ADIM 1: KULLANICI ODAKLI (BUILT-IN) LOCATORS
     * ---------------------------------------------------------
     * Öncelik her zaman buradadır. Çünkü HTML değişse de kullanıcı deneyimi değişmez.
     */
    public void builtInLocators(Page page) {

        // 1. getByRole(): Elementin 'işlevine' bakar (Buton mu, Başlık mı?). En sağlamıdır.
        page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Giriş Yap"));

        // 2. getByLabel(): Formlar için. <label> içindeki yazıya bakarak inputu bulur.
        page.getByLabel("Email adresi");

        // 3. getByPlaceholder(): Input kutusunun içindeki 'silik' yardımcı yazıya bakar.
        page.getByPlaceholder("Şifrenizi giriniz...");

        // 4. getByText(): Ekranda net şekilde görünen herhangi bir metne bakar.
        page.getByText("Hoş geldiniz!");

        // 5. getByTestId(): Geliştiricinin test için koyduğu özel damga (data-testid).
        page.getByTestId("submit-button");
    }

    /*
     * 🟡 ADIM 2: TEKNİK SEMBOLLER (CSS & SELECTOR)
     * ---------------------------------------------------------
     * Eğer Built-in yöntemler yetmiyorsa, CSS'i kullanırız.
     */
    public void technicalLocators(Page page) {

        // # (Hashtag) -> ID (Kimlik) demektir. Tekildir, güvenilirdir.
        page.locator("#login-btn");

        // . (Nokta) -> Class (Sınıf) demektir. Genelde stil için kullanılır.
        page.locator(".primary-button");

        // [] (Köşeli) -> Attribute (Özellik) demektir.
        page.locator("[type='submit']");

        // >> (Oklar) -> Anne-Çocuk ilişkisi. "Şu div'in içindeki şu butonu bul."
        page.locator(".login-container >> .submit-btn");
    }

    /*
     * 🟠 ADIM 3: FİLTRELEME VE ZİNCİRLEME (ADVANCED)
     * ---------------------------------------------------------
     * "Çok fazla sonuç var, nokta atışı yapmak istiyorum" dediğin an:
     */
    public void filteringAndChaining(Page page) {

        // filter(): Belirli bir metni İÇEREN elementi seçer.
        // Örn: Bir tablodaki 'Picasso' yazan satırı bul ve oradaki 'Sil' butonuna bas.
        page.locator("tr")
                .filter(new Locator.FilterOptions().setHasText("Picasso"))
                .getByRole(AriaRole.BUTTON).click();

        // Sıralama: first(), last(), nth(index)
        page.locator("ul > li").first();  // İlk eleman
        page.locator("ul > li").nth(2);   // 3. eleman (Saymaya 0'dan başlar)
    }

    /*
     * 🔴 ADIM 4: KRİTİK AYRIMLAR (KARIŞTIRILMAMALI!)
     * ---------------------------------------------------------
     */
    public void criticalDifferences(Page page) {

        // 1. has-text vs text:
        // :has-text('Giriş') -> "Giriş yap", "Giriş Paneli" gibi içinde geçen her şeyi bulur.
        // :text('Giriş')     -> SADECE ve SADECE "Giriş" yazanı bulur.

        // 2. innerText vs innerHTML vs textContent:
        // innerText   -> Kullanıcının gözüyle gördüğü tertemiz metin.
        // innerHTML   -> Elementin içindeki tüm HTML kodları (<span> vs.) ile beraber.
        // textContent -> Gizli (hidden) metinleri bile okuyan ham veri.
    }

    /*
     * ⚡ ADIM 5: ASSERTIONS (DOĞRULAMALAR)
     * ---------------------------------------------------------
     * Playwright Assertions'ları 'Auto-wait' (Otomatik Bekleme) özelliğine sahiptir.
     */
    public void locatorAssertions(Locator locator) {

        assertThat(locator).isVisible();       // Görünüyor mu?
        assertThat(locator).isEnabled();       // Tıklanabilir mi?
        assertThat(locator).hasText("Giriş");  // Metni içeriyor mu?
        assertThat(locator).hasAttribute("type", "password"); // Özelliği doğru mu?
    }

    /**
     * 🚀 KARAR AĞACI :
     * 1. Sayfada ne görüyorsun? (Buton, Yazı vs) -> getByRole / getByText kullan.
     * 2. Birden fazla mı çıktı? (Strict Mode Error) -> .filter() ile özelleştir.
     * 3. Kodda ID/Class çok mu temiz? -> #id veya .class kullan.
     * 4. Hiçbiri olmuyor mu? -> En son çare XPath ama alışmamak gerekir! :)
     */
}