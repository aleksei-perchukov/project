package tests.user.guest;

import org.junit.jupiter.api.*;
import tests.user.TestBase;
import tests.user.TestData;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.*;
import static utils.StaticData.*;
import static tests.user.Components.openPage;
import static io.qameta.allure.Allure.step;

@DisplayName("-DK- / USER TEST SUITE")
@Tags({@Tag("UserAccount"), @Tag("WEB"), @Tag("DK")})
public class UserTestsDK extends TestBase {
    void configureUrlsDK() {
        mainUrl = urlDK;
        baseUrl = mainUrl;
        TestData testData = new TestData();
    }

    @DisplayName("Create user test")
    @Test
    void createUserDK() {
        configureUrlsDK();
        step("Opening page: " + createUserPage, () -> {
            openPage(createUserPage);
        });
        step("Clicking on 'agree with all cookies' button ", () -> {
            $(".coi-banner__accept").click();
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

