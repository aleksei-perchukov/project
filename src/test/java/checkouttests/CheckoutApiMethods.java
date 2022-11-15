package checkouttests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;
import io.restassured.response.ResponseBodyExtractionOptions;
import org.openqa.selenium.Cookie;

import static checkouttests.CheckoutData.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.WebDriverRunner.driver;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static specs.Specs.*;
import static usertests.Components.openPage;
import static utils.RandomUtils.getCookieExpirationDate;

public class CheckoutApiMethods {
    @Step("Getting PHPSESSID cookie via API")
    static String PhpSessIdCookieGetter() {
        String phpSessIdCookie = null;
        if(Configuration.baseUrl.equals(urlDK)) {
            phpSessIdCookie = given()
                    .spec(requestSpecification1)
                    .get("/")
                    .then()
                    .spec(responseSpecification1)
                    .log().headers()
                    .extract().cookie("PHPSESSID");
        } else if(Configuration.baseUrl.equals(urlNO)) {
           openPage("/tr/1-fags-toppsving-vindu-med-1-glassfelt");
           Selenide.sleep(1000);
           $("#height").setValue("100");
           $(".item.home").click();
            Selenide.sleep(5000);
           phpSessIdCookie = WebDriverRunner.getWebDriver().manage().getCookieNamed("PHPSESSID").getValue();

        }
        return phpSessIdCookie;
    }

    static String form_keyCookieGetter() {
        String form_keyCookie = given()
                .spec(requestSpecification1)
                .cookie("PHPSESSID", PhpSessIdCookieGetter())
                .body("url=https%3A%2F%2Fskanva.dk%2Fbelvg_raptor%2Fproducts%2Fdata%2FisAjax%2F1%2F&raptor_type=GetUserRecommendations&raptor_qty=5&raptor_user_dependent=1&raptor_category=&product_id=&cart_items_ids=")
                .get("/")
                .then()
                .spec(responseSpecification1)
                .extract().cookie("form_key");
        return form_keyCookie;
    }
    static void openBrowserWithCookies(String phpSessIdCookie, String url) {
        openPage("/static/version1668170969/frontend/BelVG/vinduesgrossisten/da_DK/images/logo.svg");
        Selenide.sleep(1000);
        Cookie authCookie = new Cookie("PHPSESSID", phpSessIdCookie);
        Cookie form_keyCookie = new Cookie("form_key", "x2OdeHWwSION73Xc");
        WebDriverRunner.getWebDriver().manage().addCookie(authCookie);
        WebDriverRunner.getWebDriver().manage().addCookie(form_keyCookie);
        openPage(url);
    }
    @Step("Adding product to cart by API")
    static ResponseBodyExtractionOptions apiAddToCart(String phpSessIdCookie) {
        String productId = null;
        if(Configuration.baseUrl.equals(urlDK)) {
            productId = "4404";
        } else if(Configuration.baseUrl.equals(urlNO)) {
            productId = "5550";
        } else if(Configuration.baseUrl.equals(urlIS)) {
            productId = "";
        } else if(Configuration.baseUrl.equals(urlDE)) {
            productId = "";
        } else if(Configuration.baseUrl.equals(urlSE)) {
            productId = "";
        }

        ResponseBodyExtractionOptions cart_id_json = given()
                .spec(requestSpecification1)
                .cookie("PHPSESSID", phpSessIdCookie)
                .cookie("form_key", "x2OdeHWwSION73Xc")
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .header("x-requested-with", "XMLHttpRequest")
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/107.0.0.0 Safari/537.36")
                .body(getAddToCartBody()).when()
                .post("/checkout/cart/add/product/" + productId + "/")
                .then()
                .spec(responseSpecification1)
                .log().status()
                .log().body()
           //     .statusCode(200)
                .extract().body();
        return cart_id_json;
    }

    @Step("Checking cart number increased")
    static ResponseBodyExtractionOptions apiCheckCartNumberUpdate(String phpSessIdCookie) {
        ResponseBodyExtractionOptions cart_update_json = given().spec(requestSpecification1)
                .cookie("PHPSESSID", phpSessIdCookie)
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