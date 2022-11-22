package tests.checkout;


import com.codeborne.selenide.Configuration;
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
@DisplayName("-DK- / PAYMENT METHODS TEST SUITE / GUEST")
public class CheckoutTestsDK extends TestBase {

    void configureUrlsDK() {
        Configuration.baseUrl = urlDK;
        baseURI = urlDK;
    }


    @Test
    @DisplayName("-DK- / PAYMENT METHOD / PaypalExpress / Guest")
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
    @DisplayName("-DK- / PAYMENT METHOD / QuickPay / Guest")
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
    @DisplayName("-DK- PAYMENT METHOD / Bank Transfer / Guest")
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
    @DisplayName("-DK- PAYMENT METHOD / SparkXpress / Guest")
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
    @DisplayName("-DK- PAYMENT METHOD / ViaBill / Guest")
    void viaBillPayTestGuest() {
        configureUrlsDK();
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
