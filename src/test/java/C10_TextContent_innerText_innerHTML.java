import com.microsoft.playwright.*;

import java.awt.*;

public class C10_TextContent_innerText_innerHTML {

    public static void main(String[] args) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screenSize.width;
        int height = screenSize.height;
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();
        page.setViewportSize(width,height);
        page.navigate("https://www.testotomasyonu.com");

        // textContent() bir elementin icindeki metni dondurur
        System.out.println(page.textContent(".heading-lg"));
        // id icin #, class icin . kullaniyoruz

        // elementin icindeki metni getByText ve inner Text ile de alabiliriz
        // burada locate almak yerine direkt kullanicinin gordugu metni kullaniyoruz
        Locator topText = page.getByText("Top Selling Products");
        System.out.println(topText.innerText());



        page.click(".menu-icon-text");

        // innerText() HTML icindeki gorunen metni alir
        System.out.println(page.innerText("#submitlogin"));


        // innerHTML() belirtilen ogenin HTML icerigini alir
        System.out.println(page.innerHTML("#submitlogin"));



        page.close();
        browser.close();
        playwright.close();


    }
}
