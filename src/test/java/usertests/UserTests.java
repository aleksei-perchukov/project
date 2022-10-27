package usertests;

import com.codeborne.selenide.WebDriverRunner;
import models.CreateUserPojoModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static io.restassured.RestAssured.given;
import static specs.Specs.requestSpecification;
import static specs.Specs.responseSpecification;
import static usertests.Components.openPage;
import static usertests.TestData.*;
import static io.qameta.allure.Allure.step;

@Tag("User account tests")
public class UserTests extends TestBase {

    @Tag ("UI")
    @DisplayName("Create user test")
    @Test
    void createUser() {
        step("Opening page: " + createUserPage, () -> {openPage(createUserPage);});
        step("Clicking on 'agree with all cookies' button ", () -> {$(".coi-banner__accept").click();});
        step("Entering first name: " + firstName, () -> {$("#firstname").setValue(firstName);});
        step("Entering last name: " + lastName, () -> {$("#lastname").setValue(lastName);});
        step("Entering e-mail: " + email, () -> {$("#email_address").setValue(email);});
        step("Entering password: " + password, () -> {$("#password").setValue(password);});
        step("Entering confirm password: " + password, () -> {$("#password-confirmation").setValue(password);});
        step("Submitting form", () -> {$(".submit").click();});
        step("Confirming that user is registered by checking e-mail which should be shown in account menu", () ->
        {$(".block-dashboard-info").shouldHave(text(email));});
//        step("Opening admin panel", () -> {;});
//        step("Entering admin login", () -> {$("#username").sendKeys(adminLogin);});
//        step("Entering admin password", () -> {$("#login").sendKeys(adminPassword);});
//        step("Click 'login' button", () -> {$("#action-login").click();});
//        step("", () -> {;});
    }
    @Tag("API")
    @DisplayName("Test: create user by API, check by UI")
    @Test
    void createUserAPI() {
        CreateUserPojoModel request = new CreateUserPojoModel();
        request.setForm_key("2BBExp9Om9X3VF2x");
        request.setSuccess_url("");
        request.setError_url("");
        request.setFirstname("Test");
        request.setLastname("Test");
        request.setEmail(email + "@test.com");
        request.setPassword("Nanana88");
        request.setPassword_confirmation("Nanana88");
        String cookie;
        cookie = given()
                .spec(requestSpecification)
                .log().all()
                .body(request)
                .post(createUserPage)
                .then()
                .spec(responseSpecification)
                .extract()
                .cookie("form_key");
    }
        @Test
                void newTest(){
        openPage("/static/version1663912349/frontend/BelVG/vinduesgrossisten/da_DK/images/logo.svg");
        Cookie userCookie = new Cookie("rsa", "320859AB-6434-99E1-673F-49761D88377C");
        WebDriverRunner.getWebDriver().manage().addCookie(userCookie);
        openPage("/customer/account/");
        $(".block-dashboard-info").shouldHave(text(email));
    }
}

