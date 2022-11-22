package tests.checkout;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Configuration.*;
import static tests.checkout.CheckoutApiMethods.*;
import static tests.checkout.CheckoutData.*;
import static tests.checkout.CheckoutWebSteps.*;
import static io.restassured.RestAssured.baseURI;
import static tests.user.TestData.*;

@DisplayName("-DE- / PAYMENT METHODS TEST SUITE - GUEST")
public class CheckoutTestsDE extends TestBase {
    void configureUrlsDE() {
        baseUrl = urlDE;
        baseURI = urlDE;
    }

    @Disabled
    @Test
    @DisplayName("-DE- / PAYMENT METHOD / QuickPay / Guest")
    void quickPayGuestDE() {
        baseUrl = urlDE;
        baseURI = urlDE;
        String phpSessIdCookie = PhpSessIdCookieGetter();
        apiAddToCart(phpSessIdCookie, cookieFormKeyStatic);
        openBrowserWithCookies(phpSessIdCookie, cookieFormKeyStatic, "/checkout");
//        acceptCookies();
        fillShippingForm();
        fillShippingMethod();
        fillPaymentMethod(quickPay);
        fillQuickPay();
        checkOrderSuccess(firstName, quickPay);
    }

    @Test
    @DisplayName("-DE- / PAYMENT METHOD / BankTransfer / Guest")
    void bankPayTestGuestDE() {
        baseUrl = urlDE;
        baseURI = urlDE;
        String phpSessIdCookie = PhpSessIdCookieGetter();
        apiAddToCart(phpSessIdCookie, cookieFormKeyStatic);
        openBrowserWithCookies(phpSessIdCookie, cookieFormKeyStatic, "/checkout");
//        acceptCookies();
        fillShippingForm();
        fillShippingMethod();
        fillPaymentMethod(bankPay);
        checkOrderSuccess(firstName, bankPay);
    }
}
