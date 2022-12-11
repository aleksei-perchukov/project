package api;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import tests.checkout.CheckoutTestData;
import tests.user.UserTestData;
import tests.user.models.UserModel;

import static com.codeborne.selenide.Configuration.baseUrl;
import static data.StaticData.*;
import static io.restassured.RestAssured.given;
import static specs.Specs.*;

public class AdminAPIMethods {
    CheckoutTestData checkoutTestData = new CheckoutTestData();
    UserTestData userTestData = new UserTestData();

    @Step("Get admin token / API")
    public static String getAdminTokenAPI() {
        Response response = given()
                .spec(adminRequestSpecification)
                .formParam("username", adminLogin)
                .formParam("password", adminPassword)
                .post(baseUrl + "/rest/V1/integration/admin/token")
                .then()
                .spec(responseSpecification1)
                .extract()
                .response();
        String token = response.getBody().toString();
        System.out.printf("Admin token: " + token);
        return token;
    }

    @Step("Create user / API")
    public static String createUserAPI(String firstName, String lastName, String email, String password) {
        String customerId;
        int website_id = 0;
        if (baseUrl.equals(urlDK)) {
            website_id = 1;
        } else if (baseUrl.equals(urlNO)) {
            website_id = 2;
        } else if (baseUrl.equals(urlIS)) {
            website_id = 5;
        } else if (baseUrl.equals(urlDE)) {
            website_id = 6;
        } else if (baseUrl.equals(urlSE)) {
            website_id = 7;
        }

        UserModel userBody = new UserModel();
        userBody.setPassword(password);
        UserModel.Customer userBodyCustomer = new UserModel.Customer();
        userBodyCustomer.setFirstname(firstName);
        userBodyCustomer.setLastname(lastName);
        userBodyCustomer.setEmail(email);
        userBodyCustomer.setWebsite_id(website_id);
        userBody.setCustomer(userBodyCustomer);

        Response response = given()
                .spec(adminRequestSpecification)
                .cookie("admin", adminToken)
                .body(userBody)
                .post(baseUrl + "/rest/V1/customers/")
                .then()
                .spec(adminResponseSpecification)
                .extract()
                .response();
        customerId = response.jsonPath().getString("id");
        System.out.println(customerId);
        return customerId;
    }

    @Step("Get user token / API")
    String getUserTokenAPI() {
        Response response = given()
                .spec(adminRequestSpecification)
                .cookie("admin", adminToken)
                .formParam("username", userTestData.email)
                .formParam("password", userTestData.password)
                .post(baseUrl + "/rest/V1/integration/customer/token")
                .then()
                .spec(adminResponseSpecification)
                .contentType("application/json")
                .extract()
                .response();
        String userToken = response.getBody().toString();
    return userToken;
    }

    @Step("Delete user / API")
    public void deleteUserAPI(String customerId) {
        String adminToken = getAdminTokenAPI();
        String userToken = getUserTokenAPI();
        given()
                .spec(adminRequestSpecification)
                .cookie("admin", adminToken)
                .body("")
                .delete(baseUrl + "/rest/V1/customers/" + customerId)
                .then()
                .spec(adminResponseSpecification)
                .statusCode(204);
        System.out.println(userTestData.email + " user is successfully deleted");
    }

    @Step("Deleting order / API")
    static void deleteOrderFromDB() {
        String adminToken = getAdminTokenAPI();

    }
}
