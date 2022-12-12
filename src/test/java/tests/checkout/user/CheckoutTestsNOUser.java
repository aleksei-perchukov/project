package tests.checkout.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import tests.api.Admin;
import tests.checkout.TestBase;
import tests.user.UserTestData;

import static io.restassured.RestAssured.baseURI;
import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.open;
import static tests.data.StaticAPIMethods.apiAddToCart;
import static tests.data.StaticAPIMethods.openBrowserWithCookiesLogin;
import static tests.data.StaticData.*;
import static tests.checkout.websteps.CheckoutWebSteps.*;

@DisplayName("-NO- / USER / PAYMENT METHODS TEST SUITE")
@Tags({@Tag("Checkout"), @Tag("WEB"), @Tag("IS")})
public class CheckoutTestsNOUser extends TestBase {
    static void configureUrlsNO() {
        String mainUrl = urlNO;
        baseUrl = mainUrl;
        baseURI = mainUrl;
    }

    @Test
    @Tag("Klarna")
    @DisplayName("Klarna")
    void klarnaPayTestGuestNO() {
        configureUrlsNO();
        Admin admin = new Admin();
        UserTestData data = new UserTestData();
        String userId = admin.createUserAPI(data.firstName, data.lastName, data.email, data.password);
        apiAddToCart();
        openBrowserWithCookiesLogin("/customer/account/login/");
        login(data.email, data.password);
        open("/checkout");
        fillShippingForm(data.firstName, data.lastName, data.email, data.mobileNumber);
        fillShippingMethod();
        fillPaymentMethod(klarnaPay);
        fillKlarnaPay();
        checkOrderSuccess(data.firstName, klarnaPay, data.email);
    }

    @Test
    @Tag("QuickPay")
    @DisplayName("QuickPay")
    void quickPayTestGuestNO() {
        configureUrlsNO();
        Admin admin = new Admin();
        UserTestData data = new UserTestData();
        String userId = admin.createUserAPI(data.firstName, data.lastName, data.email, data.password);
        apiAddToCart();
        openBrowserWithCookiesLogin("/customer/account/login/");
        login(data.email, data.password);
        open("/checkout");
        fillShippingForm(data.firstName, data.lastName, data.email, data.mobileNumber);
        fillShippingMethod();
        fillPaymentMethod(quickPay);
        fillQuickPay();
        checkOrderSuccess(data.firstName, quickPay, data.email);
    }

    @Test
    @Tag("BankTransfer")
    @DisplayName("Bank Transfer")
    void bankPayTestGuestNO() {
        configureUrlsNO();
        Admin admin = new Admin();
        UserTestData data = new UserTestData();
        String userId = admin.createUserAPI(data.firstName, data.lastName, data.email, data.password);
        apiAddToCart();
        openBrowserWithCookiesLogin("/customer/account/login/");
        login(data.email, data.password);
        open("/checkout");
        fillShippingForm(data.firstName, data.lastName, data.email, data.mobileNumber);
        fillShippingMethod();
        fillPaymentMethod(bankPay);
        checkOrderSuccess(data.firstName, bankPay, data.email);
    }
}
