import com.microsoft.playwright.*;
import java.awt.*;

public class C06_ElementMethods {

    public static void main(String[] args) throws InterruptedException {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screenSize.width;
        int height = screenSize.height;

        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(50));
        // .set.SlowMo(); icine yazdigimiz yuzde orani kadar testi yavaslatarak test sirasinda islemleri gorebilmemizi saglar
        Page page = browser.newPage();

        page.navigate("https://www.testotomasyonu.com");
        page.setViewportSize(width,height);

        page.fill("//*[@class='search-label']","iphone");
        // page.fill(); ile bir input box'a metin girisi yapabiliyoruz
        // page.fill(); icerisinde string halde istedigimiz locate'i ve yine string halde girmek istedigimiz metni veriyoruz

        page.keyboard().press("Enter");
        //  page.keyboard().press(); belirtilen elemente bir klavye tusu gonderir

        page.hover("//*[@class='lazy']");
        // page.hover(); belirtilen elementin uzeirne gelir

        Thread.sleep(3000);
        page.waitForLoadState();
        System.out.println(page.title());

        page.close();
        browser.close();
        playwright.close();

    }

}
