package tests.user.guest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import tests.data.UserTestData;

import static io.restassured.RestAssured.baseURI;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.$;
import static tests.data.StaticData.*;
import static tests.data.StaticMethods.openPage;
import static io.qameta.allure.Allure.step;

@DisplayName("-NO- / USER TEST SUITE")
@Tags({@Tag("UserAccount"), @Tag("WEB"), @Tag("NO")})
public class UserTestsNO {
    void configureUrlsNO() {
        String mainUrl = urlNO;
        baseUrl = mainUrl;
        baseURI = mainUrl;
    }

    @DisplayName("Create user test")
    @Test
    void createUserNO() {
        configureUrlsNO();
        UserTestData data = new UserTestData();
        step("Opening page: " + createUserPage, () -> {
            openPage(createUserPage);
        });
        step("Entering first name: " + data.firstName, () -> {
            $("#firstname").setValue(data.firstName);
        });
        step("Entering last name: " + data.lastName, () -> {
            $("#lastname").setValue(data.lastName);
        });
        step("Entering e-mail: " + data.email, () -> {
            $("#email_address").setValue(data.email);
        });
        step("Entering password: " + data.password, () -> {
            $("#password").setValue(data.password);
        });
        step("Entering confirm password: " + data.password, () -> {
            $("#password-confirmation").setValue(data.password);
        });
        step("Submitting form", () -> {
            $(".submit").click();
        });
        step("Confirming that user is registered by checking e-mail which should be shown in account menu", () ->
        {
            $(".block-dashboard-info").shouldHave(text(data.email));
        });
    }
}
