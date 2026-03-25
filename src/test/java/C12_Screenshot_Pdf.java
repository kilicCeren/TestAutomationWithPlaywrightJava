import com.microsoft.playwright.*;

import java.awt.*;
import java.nio.file.Paths;

public class C12_Screenshot_Pdf {

    public static void main(String[] args) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screenSize.width;
        int height = screenSize.height;
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();
        page.setViewportSize(width,height);

        page.navigate("https://www.testotomasyonu.com");

       page.pdf(new Page.PdfOptions().setPath(Paths.get("src/test/java/testInputAndOutput\\P12_TestOtomasyonuFullPage.pdf")));
       page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("src/test/java/testInputAndOutput\\P12.png")));

        // page.pdf() sayfanin PDF ciktisini alir
        // page.screenshot() sayfanin ekran goruntusunu alir


        page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("src/test/java/testInputAndOutput\\P12_TestOtomasyonuFullPage_1.png")).setFullPage(true));

        // page.screenshot() komutunu .setFullPage(true) ile birlikte kullanirsak
        // sayfanin sadece gorunen kisminin degil tamaminin ekran goruntusunu alir


        // sayfada bir elementin ekran goruntusunu almak icin
        String dosyaYolu = "src/test/java/testInputAndOutput/TestOtomasyonuPage_logo.png";
        Locator element = page.locator(".logo").first();
        element.screenshot(new Locator.ScreenshotOptions().setPath(Paths.get(dosyaYolu)));


        page.close();
        browser.close();
        playwright.close();


    }
}
