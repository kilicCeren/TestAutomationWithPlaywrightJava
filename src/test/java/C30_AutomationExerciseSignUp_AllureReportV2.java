import com.github.javafaker.Faker;
import io.qameta.allure.*;
import org.junit.jupiter.api.Test;
import utilities.BaseTest;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

/**
 * ==============================================================================
 * ALLURE V2 (LAMBDA & NESTED STEPS) YAPILANDIRMASI
 * ==============================================================================
 * Bu versiyonda dışarıdan bir 'Hook' metoda ihtiyaç duyulmaz.
 * Avantajları:
 * 1. Sub-steps: Adımlar iç içe geçebilir (Örn: Kayıt Formu -> Adres Bilgileri).
 * 2. Hata İzole Etme: Hangi Lambda bloğu patlarsa raporda sadece orası kırmızı yanar.
 * 3. Parametre Desteği: Allure.parameter() ile dinamik veriler rapora eklenir.
 * ==============================================================================
 */

@Epic("Kullanıcı Yönetimi")
@Feature("Uçtan Uca Üyelik Döngüsü")
public class C30_AutomationExerciseSignUp_AllureReportV2 extends BaseTest {

    @Test
    @Story("Kullanıcı Kaydı ve Hesap Silme")
    @Description("V2: Lambda fonksiyonları ile modüler hale getirilmiş E2E test senaryosu")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("Ceren KILIÇ")
    @TmsLink("TC-026")
    public void test() {

        Faker faker = new Faker();

        // ANA ADIM 1: Navigasyon ve Giriş
        Allure.step("1. Hazırlık: Ana sayfa ziyareti ve Kayıt ekranına geçiş", () -> {
            page.navigate("https://automationexercise.com/");
            page.getByText(" Signup / Login").click();
        });

        // 2. ANA ADIM: Kayıt Formu (İç içe adımlar örneği)
        Allure.step("2. Yeni kullanıcı kayıt bilgileri dolduruluyor", () -> {

            String email = faker.internet().emailAddress();
            String password = faker.internet().password(8, 15, true, true, true);
            Allure.parameter("Kayıt Edilen Email", email); // Rapora email bilgisini ekler
            Allure.parameter("Kayıt Edilen Password", password); // Rapora password bilgisini ekler

            page.getByPlaceholder("Name").fill(faker.name().fullName());
            page.getByPlaceholder("Email Address").nth(1).fill(email);
            page.locator("(//button[@type='submit'])[2]").click();

            // ANA ADIM 3: Detaylı Profil Formu
            Allure.step("3. Form Doldurma: Kişisel bilgiler ve Adres detayları", () -> {

                // Alt Adım (Sub-step) - Kişisel Bilgiler
                Allure.step("3a. Hesap Bilgileri (Şifre, Cinsiyet, Doğum Tarihi)", () -> {
                    page.locator("#id_gender2").click();
                    page.locator("#password").fill(password);
                    page.selectOption("#days", "5");
                    page.selectOption("#months", "July");
                    page.selectOption("#years", "1975");
                    page.locator("#newsletter").check();
                });

                // Alt Adım (Sub-step) - Adres Bilgileri
                Allure.step("3b. Adres ve İletişim Detayları", () -> {
                    page.locator("#first_name").fill(faker.name().firstName());
                    page.locator("#last_name").fill(faker.name().lastName());
                    page.locator("#address1").fill(faker.address().fullAddress());
                    page.selectOption("#country", "Canada");
                    page.locator("#state").fill(faker.address().state());
                    page.locator("#city").fill(faker.address().city());
                    page.locator("#zipcode").fill(faker.address().zipCode());
                    page.locator("#mobile_number").fill(faker.phoneNumber().cellPhone());
                });
            });

            // ANA ADIM 4: Hesabı Oluştur ve Doğrula
            Allure.step("4. Onay: Hesabın oluşturulması ve Başarı mesajının doğrulanması", () -> {
                page.getByText("Create Account").click();
                assertThat(page).hasTitle("Automation Exercise - Account Created");
                assertThat(page.locator(".title.text-center")).containsText("Created");
            });

            // 5. ANA ADIM
            Allure.step("5. Kapanış: Hesabın silinmesi ve URL kontrolü", () -> {
                page.getByText("Continue").click();
                page.getByText(" Delete Account").click();

                assertThat(page.locator(".title.text-center")).containsText("Deleted");
                assertThat(page).hasURL("https://automationexercise.com/delete_account");
            });
        });
    }
}


