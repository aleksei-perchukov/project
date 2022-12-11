package tests.data;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;
import io.restassured.response.ResponseBodyExtractionOptions;
import org.openqa.selenium.Cookie;
import tests.checkout.CheckoutTestData;

import static tests.data.StaticData.*;
import static tests.data.StaticData.mainUrl;
import static tests.data.StaticMethods.openPage;
import static io.restassured.RestAssured.given;
import static specs.Specs.requestSpecification1;
import static specs.Specs.responseSpecification1;

public class StaticAPIMethods {
    @Step("Opening browser with session cookies (PHPSESSID & form_key)")
    public static void openBrowserWithCookies(String url) {
        openPage("/static/version1668170969/frontend/BelVG/vinduesgrossisten/da_DK/images/logo.svg");
        Selenide.sleep(2000);
        Cookie authCookie = new Cookie("PHPSESSID", phpSessId, "." + mainUrl.substring(8),"/", null);
        Cookie form_keyCookie = new Cookie("form_key", formKey);
        WebDriverRunner.getWebDriver().manage().addCookie(authCookie);
        WebDriverRunner.getWebDriver().manage().addCookie(form_keyCookie);
        openPage(url);
    }

    @Step("Opening browser with 'form_key' session cookie")
    public static void openBrowserWithCookiesLogin(String url) {
        openPage("/static/version1668170969/frontend/BelVG/vinduesgrossisten/da_DK/images/logo.svg");
        Selenide.sleep(2000);
        Cookie authCookie = new Cookie("PHPSESSID", phpSessId, "." + mainUrl.substring(8),"/", null);
        Cookie form_keyCookie = new Cookie("form_key", formKey);
        WebDriverRunner.getWebDriver().manage().addCookie(authCookie);
        WebDriverRunner.getWebDriver().manage().addCookie(form_keyCookie);
        openPage(url);
    }

    @Step("Adding product to cart by API")
    public static ResponseBodyExtractionOptions apiAddToCart() {
        CheckoutTestData testData = new CheckoutTestData();
        String productId = null;
        Selenide.sleep(2000);
        if (Configuration.baseUrl.equals(urlDK)) {
            productId = "4404";
        } else if (Configuration.baseUrl.equals(urlNO)) {
            productId = "5550";
        } else if (Configuration.baseUrl.equals(urlIS)) {
            productId = "4404";
        } else if (Configuration.baseUrl.equals(urlDE)) {
            productId = "4404";
        } else if (Configuration.baseUrl.equals(urlSE)) {
            productId = "";
        }

        io.restassured.http.Cookie phpSessIdDomain = new io.restassured.http.Cookie.Builder("PHPSESSID", phpSessId).setDomain("." + mainUrl.substring(8)).build();
        ResponseBodyExtractionOptions cart_id_json = given()
                .spec(requestSpecification1)
                .cookie(phpSessIdDomain)
                .cookie("form_key", formKey)
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .body(testData.getAddToCartBody()).when()
                .post(mainUrl + "/checkout/cart/add/product/" + productId + "/")
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
    static ResponseBodyExtractionOptions apiCheckCartNumberUpdate(String phpSessId) {
        ResponseBodyExtractionOptions cart_update_json = given().spec(requestSpecification1)
                .cookie("PHPSESSID", phpSessId)
                .cookie("form_key", "x2OdeHWwSION73Xc")
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .header("x-requested-with", "XMLHttpRequest")
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/107.0.0.0 Safari/537.36")
                .when()
                .get(mainUrl + "customer/section/load/?sections=cart&force_new_section_timestamp=true")
                .then()
                .spec(responseSpecification1)
                .statusCode(200)
                .extract().body();
        return cart_update_json;
    }
}
