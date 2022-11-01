package usertests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.restassured.RestAssured.baseURI;

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
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--auto-open-devtools-for-tabs");
        Configuration.browser = System.getProperty("browser_name", "chrome");
        Configuration.browserVersion = System.getProperty("browser_version", "95.0");
        Configuration.browserSize = System.getProperty("browser_size", "1920x1080");
        Configuration.pageLoadStrategy = "none";
        Configuration.baseUrl = "https://skanva.dk";


//        capabilities.setCapability("devtools", true);
//        System.setProperty("noExit", "true");
//        System.setProperty("moon_debugged", "true");
//        System.setProperty("devtools", "true");
        baseURI = "https://skanva.dk";
        Configuration.timeout = 60000;

    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        if (remote != null) {
            Attach.addVideo();
        }
    }

    @AfterAll
    static void Bye() {
        System.out.println("Bye-bye!");
    }
}