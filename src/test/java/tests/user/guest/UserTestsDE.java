package tests.user.guest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.$;
import static io.qameta.allure.Allure.step;
import static tests.user.Components.openPage;
import static tests.user.TestData.*;
import static utils.StaticData.*;

@DisplayName("-DE- / USER TEST SUITE")
@Tags({@Tag("UserAccount"), @Tag("WEB"), @Tag("DE")})
public class UserTestsDE {
    void configureUrlsDE() {
        mainUrl = urlDE;
        baseUrl = mainUrl;
    }

    @DisplayName("Create user test")
    @Test
    void createUserDE() {
        configureUrlsDE();
        step("Opening page: " + createUserPage, () -> {
            openPage(createUserPage);
        });
        step("Entering first name: " + firstName, () -> {
            $("#firstname").setValue(firstName);
        });
        step("Entering last name: " + lastName, () -> {
            $("#lastname").setValue(lastName);
        });
        step("Entering e-mail: " + email, () -> {
            $("#email_address").setValue(email);
        });
        step("Entering password: " + password, () -> {
            $("#password").setValue(password);
        });
        step("Entering confirm password: " + password, () -> {
            $("#password-confirmation").setValue(password);
        });
        step("Submitting form", () -> {
            $(".submit").click();
        });
        step("Confirming that user is registered by checking e-mail which should be shown in account menu", () ->
        {
            $(".block-dashboard-info").shouldHave(text(email));
        });

    }
}