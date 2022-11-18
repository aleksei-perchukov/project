package tests.checkout;

import com.codeborne.selenide.Configuration;
import io.restassured.response.Response;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static com.codeborne.selenide.Selenide.$;
import static tests.checkout.CheckoutApiMethods.*;
import static tests.checkout.CheckoutData.*;
import static tests.checkout.CheckoutWebSteps.*;
import static io.restassured.RestAssured.baseURI;
import static tests.user.TestData.*;
@Disabled
public class CheckoutTestsDEUser extends TestBase{
    void configureUrls(){
        Configuration.baseUrl = urlDE;
        baseURI = urlDE;

    }


    @Test
    @DisplayName("-=DE=- PAYMENT METHOD -> QuickPay - User")
    void quickPayUserDE() {
        configureUrls();
        Map map = createUserAndLoginAPI();
        String firstName = getFromResponseFirstName(map);
        String lastName = getFromResponseLastName(map);
        String email = getFromResponseEmail(map);
        String password = getFromResponsePassword(map);
        Response response = getFromResponseResponse(map);
        String cookieFormKey = response.getCookie("form_key");
        String phpSessIdCookie = response.getCookie("PHPSESSID");
        String privateContentVersionCookie = response.getCookie("private_content_version");
        openBrowserWithCookies(phpSessIdCookie, cookieFormKey, "/customer/account/login");
        $("#email").setValue(email);
        $("#pass").setValue(password);
        $("#send2").click();

        apiAddToCart(phpSessIdCookie,cookieFormKeyStatic);
        openBrowserWithCookies(phpSessIdCookie, cookieFormKey, "/checkout");
//        acceptCookies();
        fillShippingForm();
        fillShippingMethod();
        fillPaymentMethod(quickPay);
        fillQuickPay();
        checkOrderSuccess(firstName, quickPay);
    }

    @Test
    @DisplayName("-=DE=- PAYMENT METHOD -> BankTransfer - User")
    void bankPayTestGuestDE() {
        configureUrls();
        Map map = createUserAndLoginAPI();
        String firstName = getFromResponseFirstName(map);
        String lastName = getFromResponseLastName(map);
        String email = getFromResponseEmail(map);
        String password = getFromResponsePassword(map);
        Response response = getFromResponseResponse(map);
        String cookieFormKey = response.getCookie("form_key");
        String phpSessIdCookie = response.getCookie("PHPSESSID");
        apiAddToCart(phpSessIdCookie, cookieFormKey);
        openBrowserWithCookies(phpSessIdCookie, cookieFormKey, "/checkout");
//        acceptCookies();
        fillShippingForm();
        fillShippingMethod();
        fillPaymentMethod(bankPay);
        checkOrderSuccess(firstName, bankPay);
    }
}
