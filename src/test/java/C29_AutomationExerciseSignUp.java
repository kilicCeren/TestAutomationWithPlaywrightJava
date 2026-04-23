import com.github.javafaker.Faker;
import io.qameta.allure.*;
import org.junit.jupiter.api.Test;
import utilities.BaseTest;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;


/**
 * ==============================================================================
 * ALLURE V1 RAPORLAMA NOTLARI
 * ==============================================================================
 * 1. @Step notasyonu içeren 'allureStep' yardımcı metodu kullanılır.
 * 2. Test adımları doğrusal bir liste halinde raporlanır.
 * 3. Her önemli işlemden önce allureStep çağrılarak rapor beslenir.
 * ==============================================================================
 */

@Epic("Kullanıcı Yönetimi")
@Feature("Üyelik İşlemleri")
public class C29_AutomationExerciseSignUp extends BaseTest {



    @Test
    @Story("Kullanıcı yeni hesap oluşturabilmeli ve oluşturduğu hesabı silebilmeli")
    @Description("Faker kütüphanesi ile dinamik veri üreterek kayıt ve hesap silme testi")
    @Severity(SeverityLevel.CRITICAL)
    public void signUpTest() {

        allureStep("Automation Exercise ana sayfası ziyaret ediliyor");
        page.navigate("https://automationexercise.com/");

        Faker faker = new Faker();

        allureStep("Kayıt (Signup) sayfasına gidiliyor");
        page.getByText(" Signup / Login").click();

        allureStep("İsim ve E-posta bilgileri Faker ile dolduruluyor");
        page.getByPlaceholder("Name").fill(faker.name().fullName());
        page.getByPlaceholder("Email Address").nth(1).fill(faker.internet().emailAddress());
        page.locator("(//button[@type='submit'])[2]").click();

        allureStep("Kişisel bilgiler ve adres detayları formuna veri girişi yapılıyor");
        page.locator("#id_gender2").click();
        page.locator("#password").fill(faker.internet().password(8,15,true,true,true));
        page.selectOption("#days","5");
        page.selectOption("#months","July");
        page.selectOption("#years","1975");
        page.locator("#newsletter").check();

        page.locator("#first_name").fill(faker.name().firstName());
        page.locator("#last_name").fill(faker.name().lastName());
        page.locator("#address1").fill(faker.address().fullAddress());
        page.selectOption("#country","Canada");
        page.locator("#state").fill(faker.address().state());
        page.locator("#city").fill(faker.address().city());
        page.locator("#zipcode").fill(faker.address().zipCode());
        page.locator("#mobile_number").fill(faker.phoneNumber().cellPhone());

        allureStep("Hesap oluşturma butonu tetikleniyor");
        page.getByText("Create Account").click();

        allureStep("Hesabın başarıyla oluşturulduğu doğrulanıyor");
        assertThat(page).hasTitle("Automation Exercise - Account Created");
        assertThat(page.locator(".title.text-center")).containsText("Created");

        allureStep("Hesap silme işlemine başlanıyor");
        page.getByText("Continue").click();
        page.getByText(" Delete Account").click();

        allureStep("Hesabın kalıcı olarak silindiği doğrulanıyor");
        assertThat(page.locator(".title.text-center")).containsText("Deleted");
        assertThat(page).hasURL("https://automationexercise.com/delete_account");
    }

    @Step("{message}")
    public void allureStep(String message) {
        // Bu method : Allure interceptor'ını tetikleyerek parametreyi rapor adımı olarak işler.
        // V1 yapısında bu 'Hook' metodu her class'ta (veya BaseTest'te) bulunmalıdır.
    }
}
