package tests.checkout.user;

import org.junit.jupiter.api.*;
import tests.checkout.TestBase;

import static api.AdminAPIMethods.createUserAPI;
import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.open;
import static tests.checkout.websteps.CheckoutApiMethods.*;
import static tests.checkout.CheckoutData.*;
import static tests.checkout.CheckoutData.viaBillPay;
import static tests.checkout.websteps.CheckoutWebSteps.*;
import static tests.checkout.websteps.CheckoutWebSteps.checkOrderSuccess;
import static tests.user.TestData.firstName;
import static utils.StaticData.*;

@Tags({@Tag("Checkout"), @Tag("WEB"), @Tag("DK")})
@DisplayName("-DK- / USER / PAYMENT METHODS TEST SUITE")
public class CheckoutTestsDKUser extends TestBase {
    void configureUrlsDK() {
        mainUrl = urlDK;
        baseUrl = mainUrl;
    }

    @Disabled
    @Test
    @Tag("PaypalExpress")
    @DisplayName("PaypalExpress")
    void paypalExpressPayTestUser() {
        configureUrlsDK();
        String userId = createUserAPI();
        apiAddToCart(phpSessId, cookieFormKeyStatic);
        openBrowserWithCookiesLogin(cookieFormKeyStatic, "/customer/account/login/");
        acceptCookies();
        login();
        open("/checkout");
        fillShippingForm();
        fillShippingMethod();
        fillPaymentMethod(paypalExpressPay);
        checkOrderSuccess(firstName, paypalExpressPay);
    }

    @Tag("Debug")
    @Test
    @Tag("QuickPay")
    @DisplayName("QuickPay")
    void quickPayTestUser() {
        configureUrlsDK();
        String userId = createUserAPI();
        apiAddToCart(phpSessId, cookieFormKeyStatic);
        openBrowserWithCookiesLogin(cookieFormKeyStatic, "/customer/account/login/");
        acceptCookies();
        login();
        open("/checkout");
        fillShippingForm();
        fillShippingMethod();
        fillPaymentMethod(quickPay);
        fillQuickPay();
        checkOrderSuccess(firstName, quickPay);
    }

    @Test
    @Tag("Bank Transfer")
    @DisplayName("Bank Transfer")
    void bankPayTestUser() {
        configureUrlsDK();
        String userId = createUserAPI();
        apiAddToCart(phpSessId, cookieFormKeyStatic);
        openBrowserWithCookiesLogin(cookieFormKeyStatic, "/customer/account/login/");
        acceptCookies();
        login();
        open("/checkout");
        fillShippingForm();
        fillShippingMethod();
        fillPaymentMethod(bankPay);
        checkOrderSuccess(firstName, bankPay);
    }

    @Test
    @Tag("SparkXpress")
    @DisplayName("SparkXpress")
    void sparkXpressPayTestUser() {
        configureUrlsDK();
        String userId = createUserAPI();
        apiAddToCart(phpSessId, cookieFormKeyStatic);
        openBrowserWithCookiesLogin(cookieFormKeyStatic, "/customer/account/login/");
        acceptCookies();
        login();
        open("/checkout");
        fillShippingForm();
        fillShippingMethod();
        fillPaymentMethod(sparkXpressPay);
        checkOrderSuccess(firstName, sparkXpressPay);
    }

    @Test
    @Tag("ViaBill")
    @DisplayName("ViaBill")
    void viaBillPayTestUser() {
        configureUrlsDK();
        String userId = createUserAPI();
        apiAddToCart(phpSessId, cookieFormKeyStatic);
        openBrowserWithCookiesLogin(cookieFormKeyStatic, "/customer/account/login/");
        acceptCookies();
        login();
        open("/checkout");
        fillShippingForm();
        fillShippingMethod();
        fillPaymentMethod(viaBillPay);
        checkOrderSuccess(firstName, viaBillPay);
    }
}
