import com.microsoft.playwright.*;

import java.awt.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class C16_LocatorAssertions {

    public static void main(String[] args) {

        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screenSize.width;
        int height = screenSize.height;
        page.setViewportSize(width,height);

        page.navigate("https://www.ebay.com/");

        // 1. ADIM: Pop-up butonunu tanımlıyoruz (Loglardaki aria-label'ı kullanmak,çok daha sağlamdır)
        Locator closeButton = page.getByLabel("Close dialogue");

        // 2. ADIM: Eğer buton varsa ve görünürse tıkla (Zorlamadan!)
        if (closeButton.isVisible()) {
            closeButton.click();
            System.out.println("Pop-up yakalandı ve kapatıldı.");
        } else {
            // Eğer buton o an görünmüyorsa ama aslında oradaysa, biraz beklemesini söyleyebiliriz
            try {
                closeButton.waitFor(new Locator.WaitForOptions().setTimeout(3000));
                closeButton.click();
                System.out.println("Pop-up biraz gecikmeli geldi ama kapatıldı.");
            } catch (Exception e) {
                System.out.println("Pop-up bu sefer çıkmadı, teste devam ediyorum...");
            }
        }

       // Locators Assertions

        Locator signInButton = page.getByText("Sign in").first();
        assertThat(signInButton).containsText("Sign");

        Locator searchbox = page.getByPlaceholder("Search for anything");
        assertThat(searchbox).hasAttribute("type","text");

        assertThat(searchbox).isEditable();
        // isEditable() elementin yazi yazilabilir olup olmadigini kontrol etmek icin kullanilir
        System.out.println(searchbox.isEditable());

        page.close();
        browser.close();
        playwright.close();


    }
}
