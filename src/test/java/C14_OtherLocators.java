import com.microsoft.playwright.*;

import java.awt.*;

public class C14_OtherLocators {

    public static void main(String[] args) {
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screenSize.width;
        int height = screenSize.height;
        page.setViewportSize(width,height);

        page.navigate("https://www.getir.com");


        // CSS : matching by text

        // 1- has-text
        Locator loginText = page.locator("h5:has-text('Giriş yap veya kayıt ol')");
        System.out.println("1. text yöntemi ile : " + loginText.innerText());

        // 2- text
        System.out.println("2. text yöntemi ile : " + page.locator("h5:text('Giriş yap veya kayıt ol')").innerText());




        // CSS : elements matching

        Locator loginButton = page.locator("button:has-text('Telefon numarası ile devam et'),button:has-text('login button')");
        System.out.println(loginButton.innerText());


        // Xpath
        Locator LoginBox =page.locator("(//button[@type='button'])[5]");
        System.out.println(LoginBox.innerText());

        // xpath + Playwright nth()
        Locator LoginBox2 =page.locator("(//button[@type='button'])").nth(4);
        System.out.println(LoginBox2.innerText());


        page.close();
        browser.close();
        playwright.close();

    }
}

