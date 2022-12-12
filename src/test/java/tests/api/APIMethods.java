package tests.api;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import io.restassured.response.ResponseBodyExtractionOptions;
import tests.checkout.CheckoutTestData;

import static tests.data.StaticData.*;
import static io.restassured.RestAssured.given;
import static specs.Specs.requestSpecification1;
import static specs.Specs.responseSpecification1;

public class APIMethods {

    @Step("Adding product to cart by API")
    public ResponseBodyExtractionOptions AddToCart(String baseUrl) {
        CheckoutTestData data = new CheckoutTestData();
        String productId = null;
        Selenide.sleep(2000);
        if (baseUrl.equals(urlDK)) {
            productId = "4404";
        } else if (baseUrl.equals(urlNO)) {
            productId = "5550";
        } else if (baseUrl.equals(urlIS)) {
            productId = "4404";
        } else if (baseUrl.equals(urlDE)) {
            productId = "4404";
        } else if (baseUrl.equals(urlSE)) {
            productId = "";
        }

        io.restassured.http.Cookie phpSessIdDomain = new io.restassured.http.Cookie.Builder("PHPSESSID", phpSessId).setDomain("." + baseUrl.substring(8)).build();
        ResponseBodyExtractionOptions cart_id_json = given()
                .spec(requestSpecification1)
                .cookie(phpSessIdDomain)
                .cookie("form_key", formKey)
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .body(data.getAddToCartBody()).when()
                .post(baseUrl + "/checkout/cart/add/product/" + productId + "/")
                .then()
                .spec(responseSpecification1)
                .log().status()
                .log().body()
                //     .statusCode(200)
                .extract().body();
        Selenide.sleep(3000);
        return cart_id_json;
    }

    @Step("Checking cart number increased")
    ResponseBodyExtractionOptions apiCheckCartNumberUpdate(String phpSessId) {
        ResponseBodyExtractionOptions cart_update_json = given().spec(requestSpecification1)
                .cookie("PHPSESSID", phpSessId)
                .cookie("form_key", "x2OdeHWwSION73Xc")
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .header("x-requested-with", "XMLHttpRequest")
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/107.0.0.0 Safari/537.36")
                .when()
                .get("customer/section/load/?sections=cart&force_new_section_timestamp=true")
                .then()
                .spec(responseSpecification1)
                .statusCode(200)
                .extract().body();
        return cart_update_json;
    }
}
