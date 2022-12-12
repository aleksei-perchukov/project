package tests.data;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.open;
import static tests.data.StaticData.*;
import static tests.data.StaticData.formKey;

public class StaticMethods {
    public static void openPage(String url) {
        open(url, "", basicAuthLogin, basicAuthPassword);
    }

    @Step("Opening browser with session cookies (PHPSESSID & form_key)")
    public static void openBrowserWithCookies(String url) {
        openPage("/static/version1668170969/frontend/BelVG/vinduesgrossisten/da_DK/images/logo.svg");
        Selenide.sleep(2000);
        Cookie authCookie = new Cookie("PHPSESSID", phpSessId, "." + baseUrl.substring(8),"/", null);
        Cookie form_keyCookie = new Cookie("form_key", formKey);
        WebDriverRunner.getWebDriver().manage().addCookie(authCookie);
        WebDriverRunner.getWebDriver().manage().addCookie(form_keyCookie);
        openPage(url);
    }

    @Step("Opening browser with 'form_key' session cookie")
    public static void openBrowserWithCookiesLogin(String url) {
        openPage("/static/version1668170969/frontend/BelVG/vinduesgrossisten/da_DK/images/logo.svg");
        Selenide.sleep(2000);
        Cookie authCookie = new Cookie("PHPSESSID", phpSessId, "." + baseUrl.substring(8),"/", null);
        Cookie form_keyCookie = new Cookie("form_key", formKey);
        WebDriverRunner.getWebDriver().manage().addCookie(authCookie);
        WebDriverRunner.getWebDriver().manage().addCookie(form_keyCookie);
        openPage(url);
    }
}
