package tests.checkout;


import com.codeborne.selenide.Configuration;
import io.restassured.RestAssured;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Configuration.*;
import static io.restassured.RestAssured.baseURI;
import static tests.checkout.CheckoutApiMethods.*;
import static tests.checkout.CheckoutApiMethods.apiAddToCart;
import static tests.checkout.CheckoutApiMethods.openBrowserWithCookies;
import static tests.checkout.CheckoutData.*;
import static tests.checkout.CheckoutWebSteps.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static tests.user.TestData.*;

@Tag("Checkout")
@Tag("DK")
@DisplayName("-DK- / PAYMENT METHODS TEST SUITE / GUEST")
public class CheckoutTestsDK extends TestBase {

    static String url = urlDK;

    @Test
    @DisplayName("-DK- / PAYMENT METHOD / GUEST / PaypalExpress")
    void paypalExpressPayTestGuest() {
        baseUrl = url;
        baseURI = url;
        String phpSessIdCookie = PhpSessIdCookieGetter(url);
        apiAddToCart(phpSessIdCookie, cookieFormKeyStatic, url);
        openBrowserWithCookies(phpSessIdCookie, cookieFormKeyStatic, url + "/checkout");
        acceptCookies();
        fillShippingForm(url);
        fillShippingMethod();
        fillPaymentMethod(paypalExpressPay);
        checkOrderSuccess(firstName, paypalExpressPay);
    }

    @Test
    @DisplayName("-DK- / PAYMENT METHOD / GUEST / QuickPay")
    void quickPayTestGuest() {
        baseUrl = url;
        baseURI = url;
        String phpSessIdCookie = PhpSessIdCookieGetter(url);
        apiAddToCart(phpSessIdCookie, cookieFormKeyStatic, url);
        openBrowserWithCookies(phpSessIdCookie, cookieFormKeyStatic, url + "/checkout");
        acceptCookies();
        fillShippingForm(url);
        fillShippingMethod();
        fillPaymentMethod(quickPay);
        fillQuickPay();
        checkOrderSuccess(firstName, quickPay);
    }

    @Test
    @DisplayName("-DK- / PAYMENT METHOD / GUEST / Bank Transfer")
    void bankPayTestGuest() {
        baseUrl = url;
        baseURI = url;
        String phpSessIdCookie = PhpSessIdCookieGetter(url);
        apiAddToCart(phpSessIdCookie, cookieFormKeyStatic, url);
        openBrowserWithCookies(phpSessIdCookie, cookieFormKeyStatic, url + "/checkout");
        acceptCookies();
        fillShippingForm(url);
        fillShippingMethod();
        fillPaymentMethod(bankPay);
        checkOrderSuccess(firstName, bankPay);
    }


    @Test
    @DisplayName("-DK- / PAYMENT METHOD / GUEST / SparkXpress")
    void sparkXpressPayTestGuest() {
        String phpSessIdCookie = PhpSessIdCookieGetter(url);
        apiAddToCart(phpSessIdCookie, cookieFormKeyStatic, url);
        openBrowserWithCookies(phpSessIdCookie, cookieFormKeyStatic, url + "/checkout");
        acceptCookies();
        fillShippingForm(url);
        fillShippingMethod();
        fillPaymentMethod(sparkXpressPay);
        checkOrderSuccess(firstName, sparkXpressPay);
    }

    @Test
    @DisplayName("-DK- / PAYMENT METHOD / GUEST / ViaBill")
    void viaBillPayTestGuest() {
        String phpSessIdCookie = PhpSessIdCookieGetter(url);
        apiAddToCart(phpSessIdCookie, cookieFormKeyStatic, url);
        openBrowserWithCookies(phpSessIdCookie, cookieFormKeyStatic, url + "/checkout");
        acceptCookies();
        fillShippingForm(url);
        fillShippingMethod();
        fillPaymentMethod(viaBillPay);
        $(byText("APPROVED")).click();
        checkOrderSuccess(firstName, viaBillPay);
    }
}
