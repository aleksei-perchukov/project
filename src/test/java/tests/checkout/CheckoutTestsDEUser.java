package tests.checkout;

import io.restassured.authentication.FormAuthConfig;
import io.restassured.response.Response;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static com.codeborne.selenide.Configuration.*;
import static com.codeborne.selenide.Selenide.$;
import static io.restassured.RestAssured.*;
import static tests.checkout.CheckoutApiMethods.*;
import static tests.checkout.CheckoutData.*;
import static tests.checkout.CheckoutWebSteps.*;
import static utils.StaticData.*;

@Disabled
@DisplayName("-DE- / USER / PAYMENT METHODS TEST SUITE")
public class CheckoutTestsDEUser extends TestBase {
    void configureUrlsDE() {
        mainUrl = urlDE;
        baseUrl = mainUrl;
    }

    @Test
    void usertest() {

    }


    @Test
    @DisplayName("-DE- / USER / PAYMENT METHOD / QuickPay")
    void quickPayUserDE() {
        configureUrlsDE();
        Map map = createUserAndLoginAPI();
        String firstName = getFromResponseFirstName(map);
        String lastName = getFromResponseLastName(map);
        String email = getFromResponseEmail(map);
        String password = getFromResponsePassword(map);
        Response response = getFromResponseResponse(map);
        String cookieFormKey = response.getCookie("form_key");
        String phpSessId = response.getCookie("PHPSESSID");
        String privateContentVersionCookie = response.getCookie("private_content_version");
        given()
                .formParam("a.perchukov@belvg.com", "Mambas123", new FormAuthConfig("/authentication", "a.perchukov@belvg.com", "Mambas123"))
                .formParam("");
        openBrowserWithCookies(phpSessId, cookieFormKey, "/customer/account/login");
        $("#email").setValue(email);
        $("#pass").setValue(password);
        $("#send2").click();

        apiAddToCart(phpSessId, cookieFormKeyStatic);
        openBrowserWithCookies(phpSessId, cookieFormKey, "/checkout");
//        acceptCookies();
        fillShippingForm();
        fillShippingMethod();
        fillPaymentMethod(quickPay);
        fillQuickPay();
        checkOrderSuccess(firstName, quickPay);
    }

    @Test
    @DisplayName("-DE- / USER / PAYMENT METHOD / BankTransfer")
    void bankPayTestGuestDE() {
        configureUrlsDE();
        Map map = createUserAndLoginAPI();
        String firstName = getFromResponseFirstName(map);
        String lastName = getFromResponseLastName(map);
        String email = getFromResponseEmail(map);
        String password = getFromResponsePassword(map);
        Response response = getFromResponseResponse(map);
        String cookieFormKey = response.getCookie("form_key");
        String phpSessId = response.getCookie("PHPSESSID");
        apiAddToCart(phpSessId, cookieFormKey);
        openBrowserWithCookies(phpSessId, cookieFormKey, "/checkout");
//        acceptCookies();
        fillShippingForm();
        fillShippingMethod();
        fillPaymentMethod(bankPay);
        checkOrderSuccess(firstName, bankPay);
    }
}
