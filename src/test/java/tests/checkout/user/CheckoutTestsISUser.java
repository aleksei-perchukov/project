package tests.checkout.user;

import org.junit.jupiter.api.*;
import tests.api.Admin;
import tests.checkout.TestBase;
import tests.user.UserTestData;

import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.open;
import static tests.data.StaticAPIMethods.apiAddToCart;
import static tests.data.StaticAPIMethods.openBrowserWithCookiesLogin;
import static tests.data.StaticData.*;
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
        Admin admin = new Admin();
        UserTestData testData = new UserTestData();
        String userId = admin.createUserAPI(testData.firstName, testData.lastName, testData.email, testData.password);
        apiAddToCart();
        openBrowserWithCookiesLogin("/customer/account/login/");
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
        Admin admin = new Admin();
        UserTestData testData = new UserTestData();
        String userId = admin.createUserAPI(testData.firstName, testData.lastName, testData.email, testData.password);
        apiAddToCart();
        openBrowserWithCookiesLogin("/customer/account/login/");
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
        Admin admin = new Admin();
        UserTestData testData = new UserTestData();
        String userId = admin.createUserAPI(testData.firstName, testData.lastName, testData.email, testData.password);
        apiAddToCart();
        openBrowserWithCookiesLogin("/customer/account/login/");
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
        Admin admin = new Admin();
        UserTestData testData = new UserTestData();
        String userId = admin.createUserAPI(testData.firstName, testData.lastName, testData.email, testData.password);
        apiAddToCart();
        openBrowserWithCookiesLogin("/customer/account/login/");
        login(testData.email, testData.password);
        open("/checkout");
        fillShippingForm(testData.firstName, testData.lastName, testData.email, testData.mobileNumber);
        fillShippingMethod();
        fillPaymentMethod(sparkXpressPay);
        fillQuickPay();
        checkOrderSuccess(testData.firstName, sparkXpressPay, testData.email);
    }
}
