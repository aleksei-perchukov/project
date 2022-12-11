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

@DisplayName("-IS- / USER / PAYMENT METHODS TEST SUITE")
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
        UserTestData testData = new UserTestData();
        String userId = createUserAPI(testData.firstName, testData.lastName, testData.email, testData.password);
        apiAddToCart(phpSessId, cookieFormKeyStatic);
        openBrowserWithCookiesLogin(cookieFormKeyStatic, "/customer/account/login/");
        login(testData.email, testData.password);
        open("/checkout");
        fillShippingForm(testData.firstName, testData.lastName, testData.email, testData.mobileNumber);
        fillShippingMethod();
        fillPaymentMethod(netgiroPay);
        fillQuickPay();
        checkOrderSuccess(testData.firstName, netgiroPay, testData.email);
    }

    @Test
    @Tag("Valitor")
    @DisplayName("Valitor")
    void valitorPayTestGuestIS() {
        configureUrlsIS();
        UserTestData testData = new UserTestData();
        String userId = createUserAPI(testData.firstName, testData.lastName, testData.email, testData.password);
        apiAddToCart(phpSessId, cookieFormKeyStatic);
        openBrowserWithCookiesLogin(cookieFormKeyStatic, "/customer/account/login/");
        login(testData.email, testData.password);
        open("/checkout");
        fillShippingForm(testData.firstName, testData.lastName, testData.email, testData.mobileNumber);
        fillShippingMethod();
        fillPaymentMethod(valitorPay);
        fillQuickPay();
        checkOrderSuccess(testData.firstName, valitorPay, testData.email);
    }

    @Test
    @Tag("BankTransfer")
    @DisplayName("Bank Transfer")
    void bankPayTestGuestIS() {
        configureUrlsIS();
        UserTestData testData = new UserTestData();
        String userId = createUserAPI(testData.firstName, testData.lastName, testData.email, testData.password);
        apiAddToCart(phpSessId, cookieFormKeyStatic);
        openBrowserWithCookiesLogin(cookieFormKeyStatic, "/customer/account/login/");
        login(testData.email, testData.password);
        open("/checkout");
        fillShippingForm(testData.firstName, testData.lastName, testData.email, testData.mobileNumber);
        fillShippingMethod();
        fillPaymentMethod(bankPay);
        fillQuickPay();
        checkOrderSuccess(testData.firstName, bankPay, testData.email);
    }

    @Disabled
    @Test
    @Tag("SparkXpress")
    @DisplayName("SparkXpress")
    void sparkXpressPayTestGuestIS() {
        configureUrlsIS();
        UserTestData testData = new UserTestData();
        String userId = createUserAPI(testData.firstName, testData.lastName, testData.email, testData.password);
        apiAddToCart(phpSessId, cookieFormKeyStatic);
        openBrowserWithCookiesLogin(cookieFormKeyStatic, "/customer/account/login/");
        login(testData.email, testData.password);
        open("/checkout");
        fillShippingForm(testData.firstName, testData.lastName, testData.email, testData.mobileNumber);
        fillShippingMethod();
        fillPaymentMethod(sparkXpressPay);
        fillQuickPay();
        checkOrderSuccess(testData.firstName, sparkXpressPay, testData.email);
    }
}
