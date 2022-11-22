package tests.checkout;

import com.codeborne.selenide.Configuration;
import io.restassured.authentication.FormAuthConfig;
import io.restassured.response.Response;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static com.codeborne.selenide.Selenide.$;
import static io.restassured.RestAssured.*;
import static tests.checkout.CheckoutApiMethods.*;
import static tests.checkout.CheckoutData.*;
import static tests.checkout.CheckoutWebSteps.*;

@Disabled
@DisplayName("-DE- / PAYMENT METHODS TEST SUITE / USER")
public class CheckoutTestsDEUser extends TestBase {
    static String url = urlDE;

    @Test
    void usertest() {

    }


    @Test
    @DisplayName("-DE- / PAYMENT METHOD / USER / QuickPay")
    void quickPayUserDE() {
        Map map = createUserAndLoginAPI();
        String firstName = getFromResponseFirstName(map);
        String lastName = getFromResponseLastName(map);
        String email = getFromResponseEmail(map);
        String password = getFromResponsePassword(map);
        Response response = getFromResponseResponse(map);
        String cookieFormKey = response.getCookie("form_key");
        String phpSessIdCookie = response.getCookie("PHPSESSID");
        String privateContentVersionCookie = response.getCookie("private_content_version");
        given()
                .formParam("a.perchukov@belvg.com", "Mambas123", new FormAuthConfig("/authentication", "a.perchukov@belvg.com", "Mambas123"))
                .formParam("");
        openBrowserWithCookies(phpSessIdCookie, cookieFormKey, "/customer/account/login");
        $("#email").setValue(email);
        $("#pass").setValue(password);
        $("#send2").click();

        apiAddToCart(phpSessIdCookie, cookieFormKeyStatic, url);
        openBrowserWithCookies(phpSessIdCookie, cookieFormKey, url + "/checkout");
//        acceptCookies();
        fillShippingForm();
        fillShippingMethod();
        fillPaymentMethod(quickPay);
        fillQuickPay();
        checkOrderSuccess(firstName, quickPay);
    }

    @Test
    @DisplayName("-DE- / PAYMENT METHOD / USER / BankTransfer")
    void bankPayTestGuestDE() {
        Map map = createUserAndLoginAPI();
        String firstName = getFromResponseFirstName(map);
        String lastName = getFromResponseLastName(map);
        String email = getFromResponseEmail(map);
        String password = getFromResponsePassword(map);
        Response response = getFromResponseResponse(map);
        String cookieFormKey = response.getCookie("form_key");
        String phpSessIdCookie = response.getCookie("PHPSESSID");
        apiAddToCart(phpSessIdCookie, cookieFormKeyStatic, url);
        openBrowserWithCookies(phpSessIdCookie, cookieFormKey, url + "/checkout");
//        acceptCookies();
        fillShippingForm();
        fillShippingMethod();
        fillPaymentMethod(bankPay);
        checkOrderSuccess(firstName, bankPay);
    }
}
