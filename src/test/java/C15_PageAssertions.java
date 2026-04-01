import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import java.awt.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class C15_PageAssertions {

    public static void main(String[] args) {

        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screenSize.width;
        int height = screenSize.height;
        page.setViewportSize(width,height);

        page.navigate("https://www.ebay.com/");
        assertThat(page).hasURL("https://www.ebay.com/");

        assertThat(page).hasTitle("Electronics, Cars, Fashion, Collectibles & More | eBay");
        assertThat(page).not().hasTitle("Amazon");

        page.close();
        browser.close();
        playwright.close();

    }
}
