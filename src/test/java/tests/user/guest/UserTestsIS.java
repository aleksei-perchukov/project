package tests.user.guest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import tests.user.UserTestData;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.$;
import static tests.data.StaticData.*;
import static tests.data.StaticMethods.openPage;
import static io.qameta.allure.Allure.step;

@DisplayName("-IS- / USER TEST SUITE")
@Tags({@Tag("UserAccount"), @Tag("WEB"), @Tag("IS")})
public class UserTestsIS {
    void configureUrlsIS() {
        mainUrl = urlIS;
        baseUrl = mainUrl;
    }

    @DisplayName("Create user test")
    @Test
    void createUserIS() {
        UserTestData userTestData = new UserTestData();
        configureUrlsIS();
        step("Opening page: " + createUserPage, () -> {
            openPage(createUserPage);
        });
        step("Entering first name: " + userTestData.firstName, () -> {
            $("#firstname").setValue(userTestData.firstName);
        });
        step("Entering last name: " + userTestData.lastName, () -> {
            $("#lastname").setValue(userTestData.lastName);
        });
        step("Entering e-mail: " + userTestData.email, () -> {
            $("#email_address").setValue(userTestData.email);
        });
        step("Entering password: " + userTestData.password, () -> {
            $("#password").setValue(userTestData.password);
        });
        step("Entering confirm password: " + userTestData.password, () -> {
            $("#password-confirmation").setValue(userTestData.password);
        });
        step("Submitting form", () -> {
            $(".submit").click();
        });
        step("Confirming that user is registered by checking e-mail which should be shown in account menu", () ->
        {
            $(".block-dashboard-info").shouldHave(text(userTestData.email));
        });

    }
}
