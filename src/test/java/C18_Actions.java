import com.microsoft.playwright.*;

import java.awt.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class C18_Actions {

    public static void main(String[] args) throws InterruptedException {

        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screenSize.width;
        int height = screenSize.height;
        page.setViewportSize(width,height);

        page.navigate("https://www.testotomasyonu.com");

        page.fill("//*[@class='search-label']","phone");
        // keyboard action
        page.keyboard().press("Enter");
        Thread.sleep(2000);

        Locator firstItemLink = page.locator(".prod-img").first();
        assertThat(firstItemLink).isVisible();

        // Hover
        firstItemLink.hover();
        Thread.sleep(1000);
        assertThat(page.getByText("Quick View").first()).isVisible();

        firstItemLink.click();
        Thread.sleep(2000);

        // Mouse scroll
        page.mouse().wheel(0, 600);
        Thread.sleep(1500);


       page.getByText("Buy Now").last().click();


        page.close();
        browser.close();
        playwright.close();

    }
}
