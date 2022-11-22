package tests.checkout;


import com.codeborne.selenide.*;
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

@Tag("Checkout")
@Tag("DK")
@DisplayName("-DK- / GUEST / PAYMENT METHODS TEST SUITE")
public class CheckoutTestsDK extends TestBase {

//    void configureUrlsDK() {
//        WebDriverRunner.setWebDriver();
//        WebDriverRunner.driver().config().baseUrl(urlDK) = urlDK;
//        WebDriverRunner.driver().config().browserCapabilities();
//    }


    @Test
    @DisplayName("-DK- / GUEST / PAYMENT METHOD / PaypalExpress")
    void paypalExpressPayTestGuest() {
        baseUrl = urlDK;
        baseURI = urlDK;
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
    @DisplayName("-DK- / GUEST / PAYMENT METHOD / QuickPay")
    void quickPayTestGuest() {
        baseUrl = urlDK;
        baseURI = urlDK;
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
    @DisplayName("-DK- / GUEST / PAYMENT METHOD / Bank Transfer")
    void bankPayTestGuest() {
        baseUrl = urlDK;
        baseURI = urlDK;
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
    @DisplayName("-DK- / GUEST / PAYMENT METHOD / SparkXpress")
    void sparkXpressPayTestGuest() {
        baseUrl = urlDK;
        baseURI = urlDK;
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
    @DisplayName("-DK- / GUEST / PAYMENT METHOD / ViaBill")
    void viaBillPayTestGuest() {
        baseUrl = urlDK;
        baseURI = urlDK;
        String phpSessIdCookie = PhpSessIdCookieGetter();
        apiAddToCart(phpSessIdCookie, cookieFormKeyStatic);
        openBrowserWithCookies(phpSessIdCookie, cookieFormKeyStatic, "/checkout");
        acceptCookies();
        fillShippingForm();
        fillShippingMethod();
        fillPaymentMethod(viaBillPay);
        $(byText("APPROVED")).click();
        checkOrderSuccess(firstName, viaBillPay);
    }
}
