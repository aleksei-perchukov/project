package tests.checkout;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tests.user.TestData;

import static tests.checkout.CheckoutApiMethods.*;
import static tests.checkout.CheckoutData.*;
import static tests.checkout.CheckoutData.bankPay;
import static tests.checkout.CheckoutWebSteps.*;

@DisplayName("-IS- / PAYMENT METHODS TEST SUITE / GUEST")
public class CheckoutTestsIS extends TestBase {
    static String url = urlIS;

    @Disabled
    @Test
    @DisplayName("-IS- / PAYMENT METHOD / GUEST / Netgiro")
    void netgiroTestGuestIS() {
        String phpSessIdCookie = PhpSessIdCookieGetter(url);
        apiAddToCart(phpSessIdCookie, cookieFormKeyStatic, url);
        openBrowserWithCookies(phpSessIdCookie, cookieFormKeyStatic, url + "/checkout");
        fillShippingForm();
        fillShippingMethod();
        fillPaymentMethod(netgiroPay);
        fillNetgiroPay();
        checkOrderSuccess(TestData.firstName, netgiroPay);
    }

    @Test
    @DisplayName("-IS- / PAYMENT METHOD / GUEST / Valitor")
    void valitorPayTestGuestIS() {
        String phpSessIdCookie = PhpSessIdCookieGetter(url);
        apiAddToCart(phpSessIdCookie, cookieFormKeyStatic, url);
        openBrowserWithCookies(phpSessIdCookie, cookieFormKeyStatic, url + "/checkout");
        fillShippingForm();
        fillShippingMethod();
        fillPaymentMethod(valitorPay);
        fillValitorPay();
        checkOrderSuccess(TestData.firstName, valitorPay);
    }

    @Test
    @DisplayName("-IS- / PAYMENT METHOD / GUEST /Bank Transfer")
    void bankPayTestGuestIS() {
        String phpSessIdCookie = PhpSessIdCookieGetter(url);
        apiAddToCart(phpSessIdCookie, cookieFormKeyStatic, url);
        openBrowserWithCookies(phpSessIdCookie, cookieFormKeyStatic, url + "/checkout");
        fillShippingForm();
        fillShippingMethod();
        fillPaymentMethod(bankPay);
        checkOrderSuccess(TestData.firstName, bankPay);
    }

    @Disabled
    @Test
    @DisplayName("-IS- / PAYMENT METHOD / GUEST / SparkXpress")
    void sparkXpressPayTestGuestIS() {
        String phpSessIdCookie = PhpSessIdCookieGetter(url);
        apiAddToCart(phpSessIdCookie, cookieFormKeyStatic, url);
        openBrowserWithCookies(phpSessIdCookie, cookieFormKeyStatic, url + "/checkout");
        //       acceptCookies();
        fillShippingForm();
        fillShippingMethod();
        fillPaymentMethod(sparkXpressPay);
        checkOrderSuccess(TestData.firstName, sparkXpressPay);
    }
}
