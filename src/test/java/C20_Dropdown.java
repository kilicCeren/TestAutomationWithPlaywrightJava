import com.microsoft.playwright.*;
import com.microsoft.playwright.options.SelectOption;

import java.awt.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class C20_Dropdown {

    public static void main(String[] args) throws InterruptedException {

        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screenSize.width;
        int height = screenSize.height;
        page.setViewportSize(width,height);

        page.navigate("https://www.ebay.com/");

        Locator selectCategory = page.getByLabel("Select a category for search");
        Thread.sleep(1000);

        // selectByValue
        selectCategory.selectOption("1");
        Thread.sleep(1000);
        assertThat(selectCategory).hasValue("1");


        // selectByLabel
        selectCategory.selectOption(new SelectOption().setLabel("Travel"));
        Thread.sleep(1000);
        assertThat(selectCategory.locator("option:checked")).hasText("Travel");



        page.close();
        browser.close();
        playwright.close();

    }
}
