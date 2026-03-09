import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class C01_AutoClosure {

    public static void main(String[] args) {
        try(Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(true));
            Page page = browser.newPage(); // Selenium'da driver olusturmaya esdeger

            page.navigate("https://www.testotomasyonu.com");
            System.out.println(page.title() + "\n" + page.url());

        }
    }

}
