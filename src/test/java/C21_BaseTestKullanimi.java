import org.junit.jupiter.api.Test;
import utilities.BaseTest;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class C21_BaseTestKullanimi extends BaseTest {

    @Test
    public void test(){
        page.navigate("http://www.testotomasyonu.com");
        assertThat(page).hasTitle("Test Otomasyonu - Test Otomasyonu");

    }

}
