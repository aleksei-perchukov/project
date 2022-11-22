package tests.checkout;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.response.ResponseBodyExtractionOptions;
import models.CreateUserPojoModel;
import models.LoginUserPojoModel;
import org.junit.jupiter.api.DisplayName;
import org.openqa.selenium.Cookie;
import tests.user.TestData;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.get;
import static tests.checkout.CheckoutData.*;
import static com.codeborne.selenide.Selenide.$;
import static io.restassured.RestAssured.given;
import static specs.Specs.*;
import static tests.user.Components.openPage;
import static tests.user.TestData.*;

public class CheckoutApiMethods {
    static Map<String, Object> createUserAndLoginAPI() {
        String formKey = "2BBExp9Om9X3VF2x";
        String firstName = TestData.firstName;
        String lastName = TestData.lastName;
        String email = TestData.email;
        String password = TestData.password;
        CreateUserPojoModel requestCreateUser = new CreateUserPojoModel();
        requestCreateUser.setForm_key(formKey);
        requestCreateUser.setSuccess_url("");
        requestCreateUser.setError_url("");
        requestCreateUser.setFirstname(firstName);
        requestCreateUser.setLastname(lastName);
        requestCreateUser.setEmail(email + "@test.com");
        requestCreateUser.setPassword(password);
        requestCreateUser.setPassword_confirmation(password);
        Response response = given()
                .spec(requestSpecification1)
                .redirects().follow(false)
                .cookie("form_key", formKey)
                .log().all()
                .body(requestCreateUser)
                .post(createUserPage)
                .then()
                .spec(responseSpecification1)
                .extract()
                .response();
        String cookiePhpSessId = response.getCookie("PHPSESSID");

        String requestLogin = "form_key=" + formKey + "&login[username]=" + email + "&login[password]=" + password;

        response = given()
                .spec(requestSpecification1)
                .cookie("PHPSESSID", cookiePhpSessId)
                .cookie("form_key", formKey)
                .contentType("text/html; charset=UTF-8")
                .body(requestLogin)
                .post("/customer/account/loginPost/")
                .then()
                .spec(responseSpecification1)
                .statusCode(302)
                .extract().response();

        System.out.println(response.toString());
//        cookiePhpSessId = response.getCookie("PHPSESSID");
//        String location = response.getHeader("Location");
//        String xMagentoVaryCookie = response.getCookie("X-Magento-Vary");
//        String privateContentVersionCookie = response.getCookie("private_content_version");
//
//        response = given()
//                .spec(requestSpecification1)
//                .cookie("PHPSESSID", cookiePhpSessId)
//                .cookie("X-Magento-Vary", xMagentoVaryCookie)
//                .cookie("private_content_verion", privateContentVersionCookie)
//                .cookie("form_key", formKey)
//                .contentType("application/x-www-form-urlencoded")
//                .body(requestLogin)
//                .post(location)
//                .then()
//                .spec(responseSpecification1)
//                .log().body()
//                .statusCode(200)
//                .extract().response();

        Map<String, Object> map = new HashMap<>();
        map.put("First Name", firstName);
        map.put("Last Name", lastName);
        map.put("E-mail", email);
        map.put("Password", password);
        map.put("Response", response);
        return map;
    }

    static String getFromResponseFirstName(Map<String, Object> map) {
        Object obj = map.get("First Name");
        String firstName = obj.toString();
        return firstName;
    }

    static String getFromResponseLastName(Map<String, Object> map) {
        Object obj = map.get("Last Name");
        String lastName = obj.toString();
        return lastName;
    }

    static String getFromResponseEmail(Map<String, Object> map) {
        Object obj = map.get("E-mail");
        String email = obj.toString();
        return email;
    }

    static String getFromResponsePassword(Map<String, Object> map) {
        Object obj = map.get("Password");
        String password = obj.toString();
        return password;
    }

    static Response getFromResponseResponse(Map<String, Object> map) {
        Object obj = map.get("Response");
        Response response = (Response) obj;
        return response;
    }

    @Step("Getting PHPSESSID cookie via API")
    static String PhpSessIdCookieGetter() {
        String phpSessIdCookie = null;
        if (Configuration.baseUrl.equals(urlDK)) {
            phpSessIdCookie = given()
                    .spec(requestSpecification1)
                    .contentType("text/html; charset=UTF-8")
                    .get("/tr/topstyret-vindue-2-fags")
                    .then()
                    .spec(responseSpecification1)
                    .log().headers()
                    .extract().cookie("PHPSESSID");
        } else if (Configuration.baseUrl.equals(urlNO) || Configuration.baseUrl.equals(urlIS) || Configuration.baseUrl.equals(urlDE)) {
            String productUrl = null;
            if (Configuration.baseUrl.equals(urlNO)) {
                productUrl = "/tr/1-fags-toppsving-vindu-med-1-glassfelt";
            }
            if (Configuration.baseUrl.equals(urlIS)) {
                productUrl = "/tr/toppstyrdur-gluggi-2-fags";
            }
            if (Configuration.baseUrl.equals(urlDE)) {
                productUrl = "/tr/2-teiliges-klappfenster";
            }
            openPage(productUrl);

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

    @Step("Opening browser with session cookies (PHPSESSID & form_key)")
    static void openBrowserWithCookies(String phpSessIdCookie, String cookieFormKey, String url) {
        openPage("/static/version1668170969/frontend/BelVG/vinduesgrossisten/da_DK/images/logo.svg");
        Selenide.sleep(1000);
        Cookie authCookie = new Cookie("PHPSESSID", phpSessIdCookie);
        Cookie form_keyCookie = new Cookie("form_key", cookieFormKey);
        WebDriverRunner.getWebDriver().manage().addCookie(authCookie);
        WebDriverRunner.getWebDriver().manage().addCookie(form_keyCookie);
        openPage(url);
    }

    @Step("Adding product to cart by API")
    static ResponseBodyExtractionOptions apiAddToCart(String phpSessIdCookie, String formKeyCookie) {
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

        ResponseBodyExtractionOptions cart_id_json = given()
                .spec(requestSpecification1)
                .cookie("PHPSESSID", phpSessIdCookie)
                .cookie("form_key", formKeyCookie)
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .body(getAddToCartBody()).when()
                .post("/checkout/cart/add/product/" + productId + "/")
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