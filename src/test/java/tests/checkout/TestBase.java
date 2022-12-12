package tests.checkout;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com.codeborne.selenide.WebDriverRunner.*;

public class TestBase {
    static String remote = System.getProperty("selenide.remote");
    @BeforeAll
    static void configure() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        DesiredCapabilities capabilities = new DesiredCapabilities();
        if (System.getProperty("selenide.remote") != null) {
            Configuration.remote = System.getProperty("selenide.remote");
            capabilities.setCapability("enableVNC", true);
            capabilities.setCapability("enableVideo", true);
        }
        Configuration.browserCapabilities = capabilities;
        Configuration.browser = System.getProperty("browser_name", "chrome");
        Configuration.browserVersion = System.getProperty("browser_version", "108.0");
        Configuration.browserSize = System.getProperty("browser_size", "1920x1080");
        Configuration.pageLoadStrategy = "none";
        Configuration.timeout = 30000;
        if(remote != null) {
            Configuration.remote = remote;
        }
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        if (remote != null) {
            Attach.addVideo();
        }
        clearBrowserCache();
        driver().clearCookies();
        driver().getWebDriver().quit();
    }

    @AfterAll
    static void Bye() {
        System.out.println("All tests have been performed.");
    }
}