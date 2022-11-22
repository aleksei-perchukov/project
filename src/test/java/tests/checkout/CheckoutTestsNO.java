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

@DisplayName("-NO- / PAYMENT METHODS TEST SUITE / GUEST")
public class CheckoutTestsNO extends TestBase {
    void configureUrlsNO() {
        Configuration.baseUrl = urlNO;
        baseURI = urlNO;
    }

    @Test
    @DisplayName("-NO- / PAYMENT METHOD / Klarna / Guest")
    void klarnaPayTestGuestNO() {
        configureUrlsNO();
        String phpSessIdCookie = PhpSessIdCookieGetter();
        apiAddToCart(phpSessIdCookie, cookieFormKeyStatic);
        openBrowserWithCookies(phpSessIdCookie, cookieFormKeyStatic, "/checkout");
        fillShippingForm();
        fillShippingMethod();
        fillPaymentMethod(klarnaPay);
        CheckoutWebSteps.fillKlarnaPay();
        checkOrderSuccess(TestData.firstName, klarnaPay);
    }

    @Test
    @DisplayName("-NO- / PAYMENT METHOD / QuickPay / Guest")
    void quickPayTestGuestNO() {
        configureUrlsNO();
        String phpSessIdCookie = PhpSessIdCookieGetter();
        apiAddToCart(phpSessIdCookie, cookieFormKeyStatic);
        openBrowserWithCookies(phpSessIdCookie, cookieFormKeyStatic, "/checkout");
        fillShippingForm();
        fillShippingMethod();
        fillPaymentMethod(quickPay);
        fillQuickPay();
        checkOrderSuccess(TestData.firstName, quickPay);
    }

    @Test
    @DisplayName("-NO- / PAYMENT METHOD / Bank Transfer / Guest")
    void bankPayTestGuestNO() {
        configureUrlsNO();
        String phpSessIdCookie = PhpSessIdCookieGetter();
        apiAddToCart(phpSessIdCookie, cookieFormKeyStatic);
        openBrowserWithCookies(phpSessIdCookie, cookieFormKeyStatic, "/checkout");
        fillShippingForm();
        fillShippingMethod();
        fillPaymentMethod(bankPay);
        checkOrderSuccess(TestData.firstName, bankPay);
    }
}
