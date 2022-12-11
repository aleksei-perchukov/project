package tests.checkout.user;

import org.junit.jupiter.api.*;
import tests.checkout.TestBase;
import tests.user.UserTestData;

import static api.AdminAPIMethods.createUserAPI;
import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.open;
import static data.StaticAPIMethods.apiAddToCart;
import static data.StaticAPIMethods.openBrowserWithCookiesLogin;
import static data.StaticData.*;
import static tests.checkout.websteps.CheckoutWebSteps.*;

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
        UserTestData testData = new UserTestData();
        String userId = createUserAPI(testData.firstName, testData.lastName, testData.email, testData.password);
        apiAddToCart(phpSessId, cookieFormKeyStatic);
        openBrowserWithCookiesLogin(cookieFormKeyStatic, "/customer/account/login/");
        acceptCookies();
        login(testData.email, testData.password);
        open("/checkout");
        fillShippingForm(testData.firstName, testData.lastName, testData.email, testData.mobileNumber);
        fillShippingMethod();
        fillPaymentMethod(paypalExpressPay);
        fillQuickPay();
        checkOrderSuccess(testData.firstName, paypalExpressPay, testData.email);
    }

    @Test
    @Tag("QuickPay")
    @DisplayName("QuickPay")
    void quickPayTestUser() {
        configureUrlsDK();
        UserTestData testData = new UserTestData();
        String userId = createUserAPI(testData.firstName, testData.lastName, testData.email, testData.password);
        apiAddToCart(phpSessId, cookieFormKeyStatic);
        openBrowserWithCookiesLogin(cookieFormKeyStatic, "/customer/account/login/");
        acceptCookies();
        login(testData.email, testData.password);
        open("/checkout");
        fillShippingForm(testData.firstName, testData.lastName, testData.email, testData.mobileNumber);
        fillShippingMethod();
        fillPaymentMethod(quickPay);
        fillQuickPay();
        checkOrderSuccess(testData.firstName, quickPay, testData.email);
    }

    @Test
    @Tag("BankTransfer")
    @DisplayName("Bank Transfer")
    void bankPayTestUser() {
        configureUrlsDK();
        UserTestData testData = new UserTestData();
        String userId = createUserAPI(testData.firstName, testData.lastName, testData.email, testData.password);
        apiAddToCart(phpSessId, cookieFormKeyStatic);
        openBrowserWithCookiesLogin(cookieFormKeyStatic, "/customer/account/login/");
        acceptCookies();
        login(testData.email, testData.password);
        open("/checkout");
        fillShippingForm(testData.firstName, testData.lastName, testData.email, testData.mobileNumber);
        fillShippingMethod();
        fillPaymentMethod(bankPay);
        fillQuickPay();
        checkOrderSuccess(testData.firstName, bankPay, testData.email);
    }

    @Test
    @Tag("SparkXpress")
    @DisplayName("SparkXpress")
    void sparkXpressPayTestUser() {
        configureUrlsDK();
        UserTestData testData = new UserTestData();
        String userId = createUserAPI(testData.firstName, testData.lastName, testData.email, testData.password);
        apiAddToCart(phpSessId, cookieFormKeyStatic);
        openBrowserWithCookiesLogin(cookieFormKeyStatic, "/customer/account/login/");
        acceptCookies();
        login(testData.email, testData.password);
        open("/checkout");
        fillShippingForm(testData.firstName, testData.lastName, testData.email, testData.mobileNumber);
        fillShippingMethod();
        fillPaymentMethod(sparkXpressPay);
        fillQuickPay();
        checkOrderSuccess(testData.firstName, sparkXpressPay, testData.email);
    }

    @Test
    @Tag("ViaBill")
    @DisplayName("ViaBill")
    void viaBillPayTestUser() {
        configureUrlsDK();
        UserTestData testData = new UserTestData();
        String userId = createUserAPI(testData.firstName, testData.lastName, testData.email, testData.password);
        apiAddToCart(phpSessId, cookieFormKeyStatic);
        openBrowserWithCookiesLogin(cookieFormKeyStatic, "/customer/account/login/");
        acceptCookies();
        login(testData.email, testData.password);
        open("/checkout");
        fillShippingForm(testData.firstName, testData.lastName, testData.email, testData.mobileNumber);
        fillShippingMethod();
        fillPaymentMethod(viaBillPay);
        fillQuickPay();
        checkOrderSuccess(testData.firstName, viaBillPay, testData.email);
    }
}
