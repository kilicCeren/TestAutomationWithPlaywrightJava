import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import java.awt.*;

public class C05_ElementMethods {

    public static void main(String[] args) throws InterruptedException {

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screenSize.width;
        int height = screenSize.height;

        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

        Page page = browser.newPage();
        page.navigate("https://www.testotomasyonu.com");
        page.setViewportSize(width,height);
        Thread.sleep(3000);
        System.out.println(page.url());
        page.click("//*[text()='Sign Up Now']");
        Thread.sleep(3000);
        System.out.println(page.url());


        page.close();
        browser.close();
        playwright.close();

    }
}
