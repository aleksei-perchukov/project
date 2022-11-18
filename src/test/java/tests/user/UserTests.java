package tests.user;

import com.codeborne.selenide.WebDriverRunner;
import io.restassured.response.Response;
import models.CreateUserPojoModel;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static io.restassured.RestAssured.given;
import static specs.Specs.requestSpecification1;
import static specs.Specs.responseSpecification1;
import static tests.user.TestData.*;
import static io.qameta.allure.Allure.step;
@DisplayName("USER TESTS SUITE")
@Tag("UserAccount")
public class UserTests extends TestBase {

    @Tag ("UI")
    @DisplayName("Create user test")
    @Test
    void createUser() {
        step("Opening page: " + createUserPage, () -> {
            Components.openPage(createUserPage);});
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
    @Disabled
    @Tag("API")
    @DisplayName("Test: create user by API, check by UI")
    @Test
    void createUserAPI() {
        CreateUserPojoModel request = new CreateUserPojoModel();
        request.setForm_key("2BBExp9Om9X3VF2x");
        request.setSuccess_url("");
        request.setError_url("");
        request.setFirstname(firstName);
        request.setLastname(lastName);
        request.setEmail(email + "@test.com");
        request.setPassword(password);
        request.setPassword_confirmation(password);
        Response response = given()
                .spec(requestSpecification1)
                .log().all()
                .body(request)
                .post(createUserPage)
                .then()
                .spec(responseSpecification1)
                .statusCode(200)
                .extract()
                .response();
        String cookieFormKey = response.getDetailedCookie("form_key").toString();
        String cookiePHPSESSID = response.getDetailedCookie("PHPSESSID").toString();
        System.out.println(cookieFormKey);
        System.out.println(cookiePHPSESSID);
    }
        @Disabled
        @Test
        void newTest(){
        Components.openPage("/static/version1663912349/frontend/BelVG/vinduesgrossisten/da_DK/images/logo.svg");
        Cookie userCookie = new Cookie("rsa", "320859AB-6434-99E1-673F-49761D88377C");
        WebDriverRunner.getWebDriver().manage().addCookie(userCookie);
        Components.openPage("/customer/account/");
        $(".block-dashboard-info").shouldHave(text(email));
    }
}

