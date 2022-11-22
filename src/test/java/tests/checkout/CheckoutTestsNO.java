package tests.checkout;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tests.user.TestData;

import static tests.checkout.CheckoutApiMethods.*;
import static tests.checkout.CheckoutData.*;
import static io.restassured.RestAssured.baseURI;
import static tests.checkout.CheckoutWebSteps.*;

@Disabled
@DisplayName("-NO- / PAYMENT METHODS TEST SUITE / GUEST")
public class CheckoutTestsNO extends TestBase {
    static String url = urlNO;

    @Test
    @DisplayName("-NO- / PAYMENT METHOD / Klarna / Guest")
    void klarnaPayTestGuestNO() {
        String phpSessIdCookie = PhpSessIdCookieGetter(url);
        apiAddToCart(phpSessIdCookie, cookieFormKeyStatic, url);
        openBrowserWithCookies(phpSessIdCookie, cookieFormKeyStatic, url + "/checkout");
        fillShippingForm();
        fillShippingMethod();
        fillPaymentMethod(klarnaPay);
        CheckoutWebSteps.fillKlarnaPay();
        checkOrderSuccess(TestData.firstName, klarnaPay);
    }

    @Test
    @DisplayName("-NO- / PAYMENT METHOD / QuickPay / Guest")
    void quickPayTestGuestNO() {
        String phpSessIdCookie = PhpSessIdCookieGetter(url);
        apiAddToCart(phpSessIdCookie, cookieFormKeyStatic, url);
        openBrowserWithCookies(phpSessIdCookie, cookieFormKeyStatic, url + "/checkout");
        fillShippingForm();
        fillShippingMethod();
        fillPaymentMethod(quickPay);
        fillQuickPay();
        checkOrderSuccess(TestData.firstName, quickPay);
    }

    @Test
    @DisplayName("-NO- / PAYMENT METHOD / Bank Transfer / Guest")
    void bankPayTestGuestNO() {
        String phpSessIdCookie = PhpSessIdCookieGetter(url);
        apiAddToCart(phpSessIdCookie, cookieFormKeyStatic, url);
        openBrowserWithCookies(phpSessIdCookie, cookieFormKeyStatic, url + "/checkout");
        fillShippingForm();
        fillShippingMethod();
        fillPaymentMethod(bankPay);
        checkOrderSuccess(TestData.firstName, bankPay);
    }
}
