import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import java.awt.*;

public class C11_ElementBilgisiAlma_isVisible_isChecked {

    public static void main(String[] args) throws InterruptedException {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screenSize.width;
        int height = screenSize.height;
        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();
        page.setViewportSize(width,height);

        page.navigate("https://the-internet.herokuapp.com/checkboxes");

        if (page.isVisible("(//input[@type='checkbox'])[1]")){
            System.out.println("Checkbox1 görüntülendi");
        }else{
            System.out.println("Checkbox1 görüntülenemedi");
        }

        if (page.isVisible("(//input[@type='checkbox'])[2]")){
            System.out.println("Checkbox2 görüntülendi");
        }else{
            System.out.println("Checkbox2 görüntülenemedi");
        }

        if (page.isChecked("(//input[@type='checkbox'])[1]")){
            System.out.println("Checkbox1 halihazırda işaretli");
        } else {
            page.check("(//input[@type='checkbox'])[1]");
            System.out.println("Checkbox1 işaretlendi");
        }

        if (page.isChecked("(//input[@type='checkbox'])[2]")){
            System.out.println("Checkbox2 halihazırda işaretli");
        } else {
            page.check("(//input[@type='checkbox'])[2]");
            System.out.println("Checkbox2 işaretlendi");
        }


        page.close();
        browser.close();
        playwright.close();
    }
}
