import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import java.awt.*;

public class C04_NavigationMethods {

    public static void main(String[] args) throws InterruptedException {

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screenSize.width;
        int height = screenSize.height;

        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

        Page page = browser.newPage();
        page.navigate("https://www.google.com");
        page.setViewportSize(width,height);
        Thread.sleep(3000);
        System.out.println(page.title());

        page.navigate("https://www.testotomasyonu.com");
        // page.navigate(); belirtilen url'e gider
        Thread.sleep(3000);
        System.out.println(page.title());

        /*
        Not: Javascript ve Typescriptte daha hızlı çalışıyor; çünkü Playwright native olarak Node.js üzerinde geliştirilmiştir,
        Java gibi diğer diller bu kütüphaneyi bir iletişim katmanı üzerinden kullanır.
         */

        page.goBack();
        // page.goBack(); bir onceki sayfaya geri gider
        Thread.sleep(3000);
        System.out.println(page.title());

        page.reload();
        // page.reload(); sayfayi yeniden yukler
        page.goForward();
        // page.goForward(); goBack() ile geri gelinen sayfadan tekrar ileri sayfaya gecer
        Thread.sleep(3000);
        System.out.println(page.title());


        page.close();
        browser.close();
        playwright.close();

    }
}
