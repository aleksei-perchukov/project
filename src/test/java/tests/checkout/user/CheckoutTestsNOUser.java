package tests.checkout.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import tests.api.Admin;
import tests.checkout.TestBase;
import tests.user.UserTestData;

import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.open;
import static tests.data.StaticAPIMethods.apiAddToCart;
import static tests.data.StaticAPIMethods.openBrowserWithCookiesLogin;
import static tests.data.StaticData.*;
import static tests.checkout.websteps.CheckoutWebSteps.*;

@DisplayName("-NO- / USER / PAYMENT METHODS TEST SUITE")
@Tags({@Tag("Checkout"), @Tag("WEB"), @Tag("IS")})
public class CheckoutTestsNOUser extends TestBase {
    void configureUrlsNO() {
        mainUrl = urlNO;
        baseUrl = mainUrl;
    }

    @Test
    @Tag("Klarna")
    @DisplayName("Klarna")
    void klarnaPayTestGuestNO() {
        configureUrlsNO();
        Admin admin = new Admin();
        UserTestData testData = new UserTestData();
        String userId = admin.createUserAPI(testData.firstName, testData.lastName, testData.email, testData.password);
        apiAddToCart();
        openBrowserWithCookiesLogin("/customer/account/login/");
        login(testData.email, testData.password);
        open("/checkout");
        fillShippingForm(testData.firstName, testData.lastName, testData.email, testData.mobileNumber);
        fillShippingMethod();
        fillPaymentMethod(klarnaPay);
        fillQuickPay();
        checkOrderSuccess(testData.firstName, klarnaPay, testData.email);
    }

    @Test
    @Tag("QuickPay")
    @DisplayName("QuickPay")
    void quickPayTestGuestNO() {
        configureUrlsNO();
        Admin admin = new Admin();
        UserTestData testData = new UserTestData();
        String userId = admin.createUserAPI(testData.firstName, testData.lastName, testData.email, testData.password);
        apiAddToCart();
        openBrowserWithCookiesLogin("/customer/account/login/");
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
    void bankPayTestGuestNO() {
        configureUrlsNO();
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
}
