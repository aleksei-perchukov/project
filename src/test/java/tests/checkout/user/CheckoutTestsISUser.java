package tests.checkout.user;

import org.junit.jupiter.api.*;
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

@DisplayName("-IS- / USER / PAYMENT METHODS TEST SUITE")
@Tags({@Tag("Checkout"), @Tag("WEB"), @Tag("IS")})
public class CheckoutTestsISUser extends TestBase {
    static void configureUrlsIS() {
        mainUrl = urlIS;
        baseUrl = mainUrl;
        baseURI = mainUrl;
    }

    @Test
    @Tag("Netgiro")
    @DisplayName("Netgiro")
    void netgiroTestGuestIS() {
        configureUrlsIS();
        Admin admin = new Admin();
        UserTestData data = new UserTestData();
        String userId = admin.createUserAPI(data.firstName, data.lastName, data.email, data.password);
        apiAddToCart();
        openBrowserWithCookiesLogin("/customer/account/login/");
        login(data.email, data.password);
        open("/checkout");
        fillShippingForm(data.firstName, data.lastName, data.email, data.mobileNumber);
        fillShippingMethod();
        fillPaymentMethod(netgiroPay);
        fillQuickPay();
        checkOrderSuccess(data.firstName, netgiroPay, data.email);
    }

    @Test
    @Tag("Valitor")
    @DisplayName("Valitor")
    void valitorPayTestGuestIS() {
        configureUrlsIS();
        Admin admin = new Admin();
        UserTestData data = new UserTestData();
        String userId = admin.createUserAPI(data.firstName, data.lastName, data.email, data.password);
        apiAddToCart();
        openBrowserWithCookiesLogin("/customer/account/login/");
        login(data.email, data.password);
        open("/checkout");
        fillShippingForm(data.firstName, data.lastName, data.email, data.mobileNumber);
        fillShippingMethod();
        fillPaymentMethod(valitorPay);
        fillQuickPay();
        checkOrderSuccess(data.firstName, valitorPay, data.email);
    }

    @Test
    @Tag("BankTransfer")
    @DisplayName("Bank Transfer")
    void bankPayTestGuestIS() {
        configureUrlsIS();
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
        fillQuickPay();
        checkOrderSuccess(data.firstName, bankPay, data.email);
    }

    @Disabled
    @Test
    @Tag("SparkXpress")
    @DisplayName("SparkXpress")
    void sparkXpressPayTestGuestIS() {
        configureUrlsIS();
        Admin admin = new Admin();
        UserTestData data = new UserTestData();
        String userId = admin.createUserAPI(data.firstName, data.lastName, data.email, data.password);
        apiAddToCart();
        openBrowserWithCookiesLogin("/customer/account/login/");
        login(data.email, data.password);
        open("/checkout");
        fillShippingForm(data.firstName, data.lastName, data.email, data.mobileNumber);
        fillShippingMethod();
        fillPaymentMethod(sparkXpressPay);
        fillQuickPay();
        checkOrderSuccess(data.firstName, sparkXpressPay, data.email);
    }
}
