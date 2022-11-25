package api;

import io.qameta.allure.Step;
import io.restassured.http.Cookies;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static specs.Specs.*;
import static tests.user.TestData.*;

public class AdminAPIMethods {
    @Step("Get admin token")
    static String getAdminTokenAPI(){
        Response response = given().spec(requestSpecification1)
                .contentType("application/json")
                .body("'{\"username\":\"" + adminLogin + "\", \"password\":\"" + adminPassword + "}'")
                .post("https://skanva.dk/index.php/rest/V1/integration/customer/token/")
                .then()
                .spec(responseSpecification1)
                .extract()
                .response();
        String cookie = response.cookie("admin");
        return cookie;
    }

    @Step("Get admin token by API")
    static String loginAdminPanelAPI() {
        Response response = given()
                .spec(requestSpecification1)
                .formParam("username", adminLogin)
                .formParam("password", adminPassword)
  //              .body("form_key=" + cookieFormKeyStatic + "&login%5Busername%5D=" + adminLogin +"&login%5Bpassword%5D=" + adminPassword)
                .post("https://skanva.dk/rest/V1/integration/admin/token")
                .then()
                .spec(responseSpecification1)
                .extract()
                .response();
        String token = response.getBody().toString();
        System.out.printf("Admin token: " + token);
        return token;
    }

    @Step("Deleting test user from database")
    static void deleteUserFromDB() {
        String adminToken = loginAdminPanelAPI();
        given()
                .spec(adminRequestSpecification)
                .header("Authorization", "Bearer " + adminToken)
                .body("")
                .delete("/")
                .then()
                .spec(adminResponseSpecification)
                .statusCode(204);
        System.out.println(email +" user is deleted successfully");
    }

    @Step("Deleting test order from database")
    static void deleteOrderFromDB() {
        Cookies adminCookies = loginAdminPanelAPI();

    }
}
