package checkouttests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import static checkouttests.CheckoutData.*;
import static io.restassured.RestAssured.baseURI;

public class CheckoutTestsToPerform {
    @Test
    @Tag("API")
    @Tag("UI")
    @Tag("NO")
    void quickPayTestNO(){
        Configuration.baseUrl = urlNO;
        baseURI = urlNO;
    }
    @Test
    @Tag("API")
    @Tag("UI")
    @Tag("DK")
    void quickPayTestDK(){
        Configuration.baseUrl = urlDK;
        baseURI = urlDK;
    }
    @Test
    @Tag("API")
    @Tag("UI")
    @Tag("IS")
    void quickPayTestIS(){
        Configuration.baseUrl = urlIS;
        baseURI = urlIS;
    }
}
