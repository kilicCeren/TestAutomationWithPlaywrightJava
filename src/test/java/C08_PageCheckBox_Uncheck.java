import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import java.awt.*;

public class C08_PageCheckBox_Uncheck {

    public static void main(String[] args) throws InterruptedException {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screenSize.width;
        int height = screenSize.height;
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();
        page.setViewportSize(width,height);

        page.navigate("https://the-internet.herokuapp.com/checkboxes");
        page.check("(//input[@type='checkbox'])[1]");
        Thread.sleep(2000);
        page.uncheck("(//input[@type='checkbox'])[2]");
        Thread.sleep(2000);

        page.close();
        browser.close();
        playwright.close();
    }
}
