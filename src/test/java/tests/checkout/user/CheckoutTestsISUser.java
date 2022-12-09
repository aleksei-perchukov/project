package tests.checkout.user;

import org.junit.jupiter.api.*;
import tests.checkout.TestBase;
import tests.user.TestData;

import static api.AdminAPIMethods.createUserAPI;
import static com.codeborne.selenide.Configuration.*;
import static tests.checkout.websteps.CheckoutApiMethods.*;
import static tests.checkout.CheckoutData.*;
import static tests.checkout.CheckoutData.bankPay;
import static utils.StaticData.*;
import static tests.checkout.websteps.CheckoutWebSteps.*;

@DisplayName("-IS- / GUEST / PAYMENT METHODS TEST SUITE")
@Tags({@Tag("Checkout"), @Tag("WEB"), @Tag("IS")})
public class CheckoutTestsISUser extends TestBase {
    void configureUrlsIS() {
        mainUrl = urlIS;
        baseUrl = mainUrl;
    }

    @Test
    @Tag("Netgiro")
    @DisplayName("Netgiro")
    void netgiroTestGuestIS() {
        configureUrlsIS();
        String userId = createUserAPI();
        apiAddToCart(phpSessId, cookieFormKeyStatic);
        openBrowserWithCookiesLogin(cookieFormKeyStatic, "/customer/account/login/");
        login();
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
        String userId = createUserAPI();
        apiAddToCart(phpSessId, cookieFormKeyStatic);
        openBrowserWithCookiesLogin(cookieFormKeyStatic, "/customer/account/login/");
        login();
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
        String userId = createUserAPI();
        apiAddToCart(phpSessId, cookieFormKeyStatic);
        openBrowserWithCookiesLogin(cookieFormKeyStatic, "/customer/account/login/");
        login();
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
        String userId = createUserAPI();
        apiAddToCart(phpSessId, cookieFormKeyStatic);
        openBrowserWithCookiesLogin(cookieFormKeyStatic, "/customer/account/login/");
        login();
        fillShippingForm();
        fillShippingMethod();
        fillPaymentMethod(sparkXpressPay);
        checkOrderSuccess(TestData.firstName, sparkXpressPay);
    }
}
