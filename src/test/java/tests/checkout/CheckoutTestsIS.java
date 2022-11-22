package tests.checkout;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tests.user.TestData;

import static tests.checkout.CheckoutApiMethods.*;
import static tests.checkout.CheckoutData.*;
import static tests.checkout.CheckoutData.bankPay;
import static io.restassured.RestAssured.baseURI;
import static tests.checkout.CheckoutWebSteps.*;

@DisplayName("-IS- / PAYMENT METHODS TEST SUITE / GUEST")
public class CheckoutTestsIS extends TestBase {
    void configureUrlsIS() {
        Configuration.baseUrl = urlIS;
        baseURI = urlIS;
    }

    @Disabled
    @Test
    @DisplayName("-IS- / PAYMENT METHOD / Netgiro / Guest")
    void netgiroTestGuestIS() {
        configureUrlsIS();
        String phpSessIdCookie = PhpSessIdCookieGetter();
        apiAddToCart(phpSessIdCookie, cookieFormKeyStatic);
        openBrowserWithCookies(phpSessIdCookie, cookieFormKeyStatic, "/checkout");
        fillShippingForm();
        fillShippingMethod();
        fillPaymentMethod(netgiroPay);
        fillNetgiroPay();
        checkOrderSuccess(TestData.firstName, netgiroPay);
    }

    @Test
    @DisplayName("-IS- / PAYMENT METHOD / Valitor / Guest")
    void valitorPayTestGuestIS() {
        configureUrlsIS();
        String phpSessIdCookie = PhpSessIdCookieGetter();
        apiAddToCart(phpSessIdCookie, cookieFormKeyStatic);
        openBrowserWithCookies(phpSessIdCookie, cookieFormKeyStatic, "/checkout");
        fillShippingForm();
        fillShippingMethod();
        fillPaymentMethod(valitorPay);
        fillValitorPay();
        checkOrderSuccess(TestData.firstName, valitorPay);
    }

    @Test
    @DisplayName("-IS- / PAYMENT METHOD / Bank Transfer / Guest")
    void bankPayTestGuestIS() {
        configureUrlsIS();
        String phpSessIdCookie = PhpSessIdCookieGetter();
        apiAddToCart(phpSessIdCookie, cookieFormKeyStatic);
        openBrowserWithCookies(phpSessIdCookie, cookieFormKeyStatic, "/checkout");
        fillShippingForm();
        fillShippingMethod();
        fillPaymentMethod(bankPay);
        checkOrderSuccess(TestData.firstName, bankPay);
    }

    @Disabled
    @Test
    @DisplayName("-IS- / PAYMENT METHOD / SparkXpress / Guest")
    void sparkXpressPayTestGuestIS() {
        configureUrlsIS();
        String phpSessIdCookie = PhpSessIdCookieGetter();
        apiAddToCart(phpSessIdCookie, cookieFormKeyStatic);
        openBrowserWithCookies(phpSessIdCookie, cookieFormKeyStatic, "/checkout");
        //       acceptCookies();
        fillShippingForm();
        fillShippingMethod();
        fillPaymentMethod(sparkXpressPay);
        checkOrderSuccess(TestData.firstName, sparkXpressPay);
    }
}
