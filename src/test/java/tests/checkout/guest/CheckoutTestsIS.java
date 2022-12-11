package tests.checkout.guest;

import org.junit.jupiter.api.*;
import tests.checkout.CheckoutData;
import tests.checkout.TestBase;
import tests.user.TestData;

import static com.codeborne.selenide.Configuration.*;
import static tests.checkout.websteps.CheckoutApiMethods.*;
import static tests.checkout.CheckoutData.*;
import static tests.checkout.CheckoutData.bankPay;
import static utils.StaticData.*;
import static tests.checkout.websteps.CheckoutWebSteps.*;

@DisplayName("-IS- / GUEST / PAYMENT METHODS TEST SUITE")
@Tags({@Tag("Checkout"), @Tag("WEB"), @Tag("IS")})
public class CheckoutTestsIS extends TestBase {
    void configureUrlsIS() {
        mainUrl = urlIS;
        baseUrl = mainUrl;
        CheckoutData checkoutData = new CheckoutData();

    }

    @Test
    @Tag("Netgiro")
    @DisplayName("Netgiro")
    void netgiroTestGuestIS() {
        configureUrlsIS();
        apiAddToCart(phpSessId, cookieFormKeyStatic);
        openBrowserWithCookies(phpSessId, cookieFormKeyStatic, "/checkout");
        fillShippingForm();
        fillShippingMethod();
        fillPaymentMethod(netgiroPay);
        fillNetgiroPay();
        checkOrderSuccess(TestData.firstName, netgiroPay);
    }

    @Test
    @Tag("Valitor")
    @DisplayName("Valitor")
    void valitorPayTestGuestIS() {
        configureUrlsIS();
        apiAddToCart(phpSessId, cookieFormKeyStatic);
        openBrowserWithCookies(phpSessId, cookieFormKeyStatic, "/checkout");
        fillShippingForm();
        fillShippingMethod();
        fillPaymentMethod(valitorPay);
        fillValitorPay();
        checkOrderSuccess(TestData.firstName, valitorPay);
    }

    @Test
    @Tag("Bank Transfer")
    @DisplayName("Bank Transfer")
    void bankPayTestGuestIS() {
        configureUrlsIS();
        apiAddToCart(phpSessId, cookieFormKeyStatic);
        openBrowserWithCookies(phpSessId, cookieFormKeyStatic, "/checkout");
        fillShippingForm();
        fillShippingMethod();
        fillPaymentMethod(bankPay);
        checkOrderSuccess(TestData.firstName, bankPay);
    }

    @Disabled
    @Test
    @Tag("SparkXpress")
    @DisplayName("SparkXpress")
    void sparkXpressPayTestGuestIS() {
        configureUrlsIS();
        apiAddToCart(phpSessId, cookieFormKeyStatic);
        openBrowserWithCookies(phpSessId, cookieFormKeyStatic, "/checkout");
        //       acceptCookies();
        fillShippingForm();
        fillShippingMethod();
        fillPaymentMethod(sparkXpressPay);
        checkOrderSuccess(TestData.firstName, sparkXpressPay);
    }
}
