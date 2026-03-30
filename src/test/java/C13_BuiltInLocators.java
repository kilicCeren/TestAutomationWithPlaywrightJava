import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.LoadState;

import java.awt.*;

public class C13_BuiltInLocators {

    public static void main(String[] args) throws InterruptedException {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screenSize.width;
        int height = screenSize.height;

        Playwright playwright = Playwright.create();
        Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        Page page = browser.newPage();
        page.setViewportSize(width,height);
        page.setDefaultTimeout(60000);

        page.navigate("https://www.getir.com");
        page.waitForLoadState(LoadState.NETWORKIDLE);
        System.out.println("Site başlığı: " + page.title());

        // getByText()
        // getByText() elementi sayfa uzerinde gorunen metne gore bulur
        Locator loginText = page.getByText("Giriş yap veya kayıt ol");
        System.out.println("1. Text: " + loginText.innerText());

        // getByRole()
        // getByRole() elementi erisilebilirlik rolune gore (buton, link, heading gibi) bulur
        Locator loginText2 = page.getByRole(AriaRole.HEADING,
                new Page.GetByRoleOptions().setName("Giriş yap veya kayıt ol").setExact(false));
        if (loginText2.isVisible()) {
            System.out.println("2. Text (Heading): " + loginText2.innerText());
        } else {
            System.out.println("Heading olarak bulunamadı, düz text deneniyor...");
            System.out.println("2. Text (Simple): " + page.getByText("Giriş yap veya kayıt ol").first().innerText());
        }

        // getByPlaceholder()
        // getByPlaceholder() input alanini placeholder metnine gore bulur
        page.getByPlaceholder("Telefon Numarası").fill("456-12-23");

        // getByTestId()
        // getByTestId() elementi data-testid attribute'una gore bulur
        page.getByTestId("button").last().click();
        // eger ayni testId'ye sahip birden fazla element varsa .last ile sonuncusunu
        // ya da .first ile ilkini secebiliriz
        // page.getByTestId("button").nth(5).click();
        // ya da .nth() ile istedigimiz elementin indexini girerek secebiliriz
        Locator errorMessage=page.getByText("Lütfen geçerli bir telefon numarası gir.");
        System.out.println("ErrorMessage:"+errorMessage.innerText());


        page.close();
        browser.close();
        playwright.close();

    }
}