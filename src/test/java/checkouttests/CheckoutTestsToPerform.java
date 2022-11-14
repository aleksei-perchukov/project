package checkouttests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.baseURI;

public class CheckoutTestsToPerform {
    @Test
    @Tag("API")
    @Tag("UI")
    @Tag("NO")
    void quickPayTestNO(){
        Configuration.baseUrl = "https://skanva.no";
        baseURI = "https://skanva.no";
    }
    @Test
    @Tag("API")
    @Tag("UI")
    @Tag("DK")
    void quickPayTestDK(){
        Configuration.baseUrl = "https://skanva.dk";
        baseURI = "https://skanva.dk";
    }
    @Test
    @Tag("API")
    @Tag("UI")
    @Tag("IS")
    void quickPayTestIS(){
        Configuration.baseUrl = "https://skanva.is";
        baseURI = "https://skanva.is";
    }
}
