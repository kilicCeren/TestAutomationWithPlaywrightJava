import com.microsoft.playwright.*;
import com.microsoft.playwright.options.WaitUntilState;

import java.awt.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class C17_Iframe {

    public static void main(String[] args) throws InterruptedException {

        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screenSize.width;
        int height = screenSize.height;
        page.setViewportSize(width,height);

        // 1. ADIM: Timeout süresini biraz esnetelim ve yükleme stratejisini değiştirelim
        page.navigate("https://www.w3schools.com/html/tryit.asp?filename=tryhtml_textarea",
                new Page.NavigateOptions().setWaitUntil(WaitUntilState.DOMCONTENTLOADED).setTimeout(60000));

        // 2. ADIM: Iframe'in gerçekten orada olduğundan emin olalım + iframe'in icine girelim
        FrameLocator iframe = page.frameLocator("#iframeResult");

        // 3. ADIM: Textarea'yı bul ve işlem yapmadan önce "hazır olmasını" bekleyelim.
        Locator textarea = iframe.locator("textarea");
        textarea.waitFor();

        assertThat(textarea).isVisible();
        System.out.println("Iframe içindeki textarea görüldü!");

        textarea.clear();
        textarea.fill("Merhaba Ceren! Playwright öğreniyorum.");

        page.close();
        browser.close();
        playwright.close();

    }
}
