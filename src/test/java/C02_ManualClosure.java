import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class C02_ManualClosure {

    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(true));
        // seatHeadless(true) hic pencere acmadan testi tamamlar
        // seatHeadless(false) pencere acarak testi tamamlar
        Page page = browser.newPage();
        page.navigate("https://www.testotomasyonu.com");
        System.out.println(page.title());

        page.close();
        browser.close();
        playwright.close();

        // Testin sonlanip pencerenin otomatik kapanmasi icin ya try{} içine almaliyiz
        // yada test sonunda page'i browser'i ve pplaywright'i .close() komutu ile kapatmaliyiz

    }
}
