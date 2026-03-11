import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import java.awt.*;


public class C03_ScreenSize {

        public static void main(String[] args) throws InterruptedException {


            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            int width = screenSize.width;
            int height = screenSize.height;

            Playwright playwright = Playwright.create();
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

            Page page = browser.newPage();
            page.navigate("https://www.testotomasyonu.com");

            page.setViewportSize(width,height);
            // page.setViewportSize(); ile test icin acilan wwindowun boyutunu istedigimiz olculere ayarlayabiliz

            // Oncesinde Dimension class'i ile kendi ekranimizin olculerini alip int olarak tanimlayip
            // page.setViewportSize(); komutununda bu int degerleri kullanarak acilan window'un boyutunu
            // ekran olcumuze uygun boyutlara getirebiliriz


            Thread.sleep(3000);

            System.out.println(page.url());

            page.close();
            browser.close();
            playwright.close();
        }

}
