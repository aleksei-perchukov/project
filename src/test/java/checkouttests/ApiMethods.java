package checkouttests;

import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;
import io.restassured.response.ResponseBodyExtractionOptions;
import org.apache.commons.io.IOUtils;
import org.openqa.selenium.Cookie;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static specs.Specs.*;
import static usertests.Components.openPage;

public class ApiMethods {
    @Step("Getting PHPSESSID cookie via API")
    static String PhpSessIdCookieGetter() {
        String phpSessIdCookie = given()
                .spec(requestSpecification1)
                .get("/")
                .then()
                .spec(responseSpecification1)
                .log().headers()
                .extract().cookie("PHPSESSID");
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
        Cookie authCookie = new Cookie("PHPSESSID", phpSessIdCookie);
        Cookie form_keyCookie = new Cookie("form_key", "x2OdeHWwSION73Xc");
        WebDriverRunner.getWebDriver().manage().addCookie(authCookie);
        WebDriverRunner.getWebDriver().manage().addCookie(form_keyCookie);
        openPage(url);
    }
    @Step("Adding product to cart by API")
    static ResponseBodyExtractionOptions apiAddToCart(String phpSessIdCookie) {
        ResponseBodyExtractionOptions cart_id_json = given()
                .spec(requestSpecification1)
                .cookie("PHPSESSID", phpSessIdCookie)
                .cookie("form_key", "x2OdeHWwSION73Xc")
                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                .header("x-requested-with", "XMLHttpRequest")
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/107.0.0.0 Safari/537.36")
                .body("product=4404&selected_configurable_option=&related_product=&item=1277812&item=4404&form_key=x2OdeHWwSION73Xc&estimated_delivery_time=%5B8%2C10%5D&width=190&height=120&qty=1&options%5B60380%5D=190&options%5B60381%5D=120&options%5B60382%5D=%7B%22height%22%3A120%2C%22width%22%3A190%2C%22width1%22%3A95%2C%22fieldwidth2%22%3A95%7D&options%5B48328%5D=2131878&special_color_ral%5B5234147%5D=none&options%5B48326%5D=305016&special_color_ral%5B5222597%5D=none&options%5B48327%5D=305021&options%5B48330%5D=305036&options%5B860699%5D=5193080&options%5B57298%5D=361683&options%5B53127%5D=337474&options%5B859559%5D=5190887&options%5B245170%5D=1100313&options%5B352864%5D=2232309&")
                .when()
                .post("/checkout/cart/add/product/4404/")
                .then()
                .spec(responseSpecification1)
                .log().status()
                .log().body()
                .statusCode(200)
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