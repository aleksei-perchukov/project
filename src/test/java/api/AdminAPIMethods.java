package api;

import io.qameta.allure.Step;
import io.restassured.http.Cookies;
import io.restassured.response.Response;
import tests.user.UserModel;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.put;
import static specs.Specs.*;
import static tests.user.TestData.*;
import static utils.RandomUtils.getCity;
import static utils.StaticData.customerId;

public class AdminAPIMethods {

    @Step("Create user / API")
    static String createUserAPI(){
//        UserModel userBody = new UserModel();
//        userBody.setPassword(password);
//        UserModel.customer userBodyCustomer = new UserModel.customer();
//        userBodyCustomer.setFirstname(firstName);
//        userBodyCustomer.setLastname(lastName);
//        UserModel.customer.addresses userBodyCustomerAddresses = new UserModel.customer.addresses();
//        userBodyCustomerAddresses.setFirstname(firstName);
//        userBodyCustomerAddresses.setFirstname(lastName);
//        userBodyCustomerAddresses.setDefaultBilling(true);
//        userBodyCustomerAddresses.setDefaultShipping(true);
//        userBodyCustomerAddresses.setCountryId("DK");
//        userBodyCustomerAddresses.setPostcode("8000");
//        userBodyCustomerAddresses.setCity(getCity());
//        userBodyCustomerAddresses.setTelephone(mobileNumber);
//        UserModel.customer.addresses.region userCustomerAddressesRegion = new UserModel.customer.addresses.region();
//        userCustomerAddressesRegion.setRegion("");
//        userCustomerAddressesRegion.setRegionId("");
//        userCustomerAddressesRegion.setRegionCode("");
//        UserModel.customer.addresses.street userCustomerAddressesStreet = new UserModel.customer.addresses.street();
 //       userCustomerAddressesStreet.setStrings(String[""] streetStringsArray = new String[]);


        Response response = given().spec(requestSpecification1)
                .contentType("application/json")
                .header("Authorization", "Bearer " + getAdminTokenAPI())
                .body("'{\"username\":\"" + adminLogin + "\", \"password\":\"" + adminPassword + "}'")
                .post("https://skanva.dk/index.php/rest/V1/integration/customer/token/")
                .then()
                .spec(responseSpecification1)
                .extract()
                .response();
        String cookie = response.cookie("admin");
        return cookie;
    }

    @Step("Get admin token / API")
    static String getAdminTokenAPI() {
        Response response = given()
                .spec(requestSpecification1)
                .formParam("username", adminLogin)
                .formParam("password", adminPassword)
                .post("https://skanva.dk/rest/V1/integration/admin/token")
                .then()
                .spec(responseSpecification1)
                .extract()
                .response();
        String token = response.getBody().toString();
        System.out.printf("Admin token: " + token);
        return token;
    }

    @Step("Delete user / API")
    static void deleteUserFromDB() {
        String adminToken = getAdminTokenAPI();
        given()
                .spec(adminRequestSpecification)
                .header("Authorization", "Bearer " + adminToken)
                .body("")
                .delete("/rest/V1/customers/" + customerId)
                .then()
                .spec(adminResponseSpecification)
                .statusCode(204);
        System.out.println(email +" user is deleted successfully");
    }

    @Step("Deleting order / API")
    static void deleteOrderFromDB() {
        String adminToken = getAdminTokenAPI();

    }
}
