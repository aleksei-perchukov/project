package tests.checkout;


import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Configuration.*;
import static tests.checkout.CheckoutApiMethods.*;
import static tests.checkout.CheckoutApiMethods.apiAddToCart;
import static tests.checkout.CheckoutApiMethods.openBrowserWithCookies;
import static tests.checkout.CheckoutData.*;
import static tests.checkout.CheckoutWebSteps.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static io.restassured.RestAssured.baseURI;
import static tests.user.TestData.*;
import static utils.RandomUtils.getRandomAddress;

@Tag("Checkout")
@Tag("DK")
@DisplayName("-DK- / GUEST / PAYMENT METHODS TEST SUITE")
public class CheckoutTestsDK extends TestBase {

    void configureUrlsDK() {
        mainUrl = urlDK;
        baseUrl = mainUrl;
    }

    @Test
    @DisplayName("PaypalExpress")
    void paypalExpressPayTestGuest() {
        configureUrlsDK();
        String phpSessIdCookie = PhpSessIdCookieGetter();
        apiAddToCart(phpSessIdCookie, cookieFormKeyStatic);
        openBrowserWithCookies(phpSessIdCookie, cookieFormKeyStatic, "/checkout");
        acceptCookies();
        fillShippingForm();
        fillShippingMethod();
        fillPaymentMethod(paypalExpressPay);
        checkOrderSuccess(firstName, paypalExpressPay);
    }

    @Test
    @DisplayName("QuickPay")
    void quickPayTestGuest() {
        configureUrlsDK();
        String phpSessIdCookie = PhpSessIdCookieGetter();
        apiAddToCart(phpSessIdCookie, cookieFormKeyStatic);
        openBrowserWithCookies(phpSessIdCookie, cookieFormKeyStatic, "/checkout");
        acceptCookies();
        fillShippingForm();
        fillShippingMethod();
        fillPaymentMethod(quickPay);
        fillQuickPay();
        checkOrderSuccess(firstName, quickPay);
    }

    @Test
    @DisplayName("Bank Transfer")
    void bankPayTestGuest() {
        configureUrlsDK();
        String phpSessIdCookie = PhpSessIdCookieGetter();
        apiAddToCart(phpSessIdCookie, cookieFormKeyStatic);
        openBrowserWithCookies(phpSessIdCookie, cookieFormKeyStatic, "/checkout");
        acceptCookies();
        fillShippingForm();
        fillShippingMethod();
        fillPaymentMethod(bankPay);
        checkOrderSuccess(firstName, bankPay);
    }

    @Test
    @DisplayName("SparkXpress")
    void sparkXpressPayTestGuest() {
        configureUrlsDK();
        String phpSessIdCookie = PhpSessIdCookieGetter();
        apiAddToCart(phpSessIdCookie, cookieFormKeyStatic);
        openBrowserWithCookies(phpSessIdCookie, cookieFormKeyStatic, "/checkout");
        acceptCookies();
        fillShippingForm();
        fillShippingMethod();
        fillPaymentMethod(sparkXpressPay);
        checkOrderSuccess(firstName, sparkXpressPay);
    }

    @Test
    @DisplayName("ViaBill")
    void viaBillPayTestGuest() {
        configureUrlsDK();
        String phpSessIdCookie = PhpSessIdCookieGetter();
        apiAddToCart(phpSessIdCookie, cookieFormKeyStatic);
        openBrowserWithCookies(phpSessIdCookie, cookieFormKeyStatic, "/checkout");
        acceptCookies();
        fillShippingForm();
        fillShippingMethod();
        fillPaymentMethod(viaBillPay);
        checkOrderSuccess(firstName, viaBillPay);
    }
}
