package tests.checkout.guest;


import org.junit.jupiter.api.*;
import tests.checkout.TestBase;
import utils.StaticData;

import static com.codeborne.selenide.Selenide.open;
import static utils.StaticData.*;
import static com.codeborne.selenide.Configuration.*;
import static tests.checkout.websteps.CheckoutApiMethods.apiAddToCart;
import static tests.checkout.websteps.CheckoutApiMethods.openBrowserWithCookies;
import static tests.checkout.CheckoutData.*;
import static tests.checkout.websteps.CheckoutWebSteps.*;
import static tests.user.TestData.*;

@Tag("Checkout")
@Tag("DK")
@DisplayName("-DK- / GUEST / PAYMENT METHODS TEST SUITE")
public class CheckoutTestsDK extends TestBase {

    void configureUrlsDK() {
        mainUrl = urlDK;
        baseUrl = mainUrl;
    }

    @Test
    @Tag("PaypalExpress")
    @DisplayName("PaypalExpress")
    void paypalExpressPayTestGuest() {
        configureUrlsDK();
        apiAddToCart(phpSessId, StaticData.cookieFormKeyStatic);
        openBrowserWithCookies(phpSessId, cookieFormKeyStatic, "/checkout");
        acceptCookies();
        fillShippingForm();
        fillShippingMethod();
        fillPaymentMethod(paypalExpressPay);
        checkOrderSuccess(firstName, paypalExpressPay);
    }
    @Tag("Debug")
    @Test
    @Tag("QuickPay")
    @DisplayName("QuickPay")
    void quickPayTestGuest() {
        configureUrlsDK();
        apiAddToCart(phpSessId, cookieFormKeyStatic);
        openBrowserWithCookies(phpSessId, cookieFormKeyStatic, "/checkout");
        acceptCookies();
        fillShippingForm();
        fillShippingMethod();
        fillPaymentMethod(quickPay);
        fillQuickPay();
        checkOrderSuccess(firstName, quickPay);
    }

    @Test
    @Tag("Bank Transfer")
    @DisplayName("Bank Transfer")
    void bankPayTestGuest() {
        configureUrlsDK();
        apiAddToCart(phpSessId, cookieFormKeyStatic);
        openBrowserWithCookies(phpSessId, cookieFormKeyStatic, "/checkout");
        acceptCookies();
        fillShippingForm();
        fillShippingMethod();
        fillPaymentMethod(bankPay);
        checkOrderSuccess(firstName, bankPay);
    }

    @Test
    @Tag("SparkXpress")
    @DisplayName("SparkXpress")
    void sparkXpressPayTestGuest() {
        configureUrlsDK();
        apiAddToCart(phpSessId, cookieFormKeyStatic);
        openBrowserWithCookies(phpSessId, cookieFormKeyStatic, "/checkout");
        acceptCookies();
        fillShippingForm();
        fillShippingMethod();
        fillPaymentMethod(sparkXpressPay);
        checkOrderSuccess(firstName, sparkXpressPay);
    }

    @Test
    @Tag("ViaBill")
    @DisplayName("ViaBill")
    void viaBillPayTestGuest() {
        configureUrlsDK();
        apiAddToCart(phpSessId, cookieFormKeyStatic);
        openBrowserWithCookies(phpSessId, cookieFormKeyStatic, "/checkout");
        acceptCookies();
        fillShippingForm();
        fillShippingMethod();
        fillPaymentMethod(viaBillPay);
        checkOrderSuccess(firstName, viaBillPay);
    }
}
