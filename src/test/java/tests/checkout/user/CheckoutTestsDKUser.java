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

@Tags({@Tag("Checkout"), @Tag("WEB"), @Tag("DK")})
@DisplayName("-DK- / USER / PAYMENT METHODS TEST SUITE")
public class CheckoutTestsDKUser extends TestBase {
    static void configureUrlsDK() {
        String mainUrl = urlDK;
        baseUrl = mainUrl;
        baseURI = mainUrl;
    }

    @Test
    @Tag("PaypalExpress")
    @DisplayName("PaypalExpress")
    void paypalExpressPayTestUser() {
        configureUrlsDK();
        Admin admin = new Admin();
        UserTestData data = new UserTestData();
        String userId = admin.createUserAPI(data.firstName, data.lastName, data.email, data.password);
        apiAddToCart();
        openBrowserWithCookiesLogin("/customer/account/login/");
        acceptCookies();
        login(data.email, data.password);
        open("/checkout");
        fillShippingForm(data.firstName, data.lastName, data.email, data.mobileNumber);
        fillShippingMethod();
        fillPaymentMethod(paypalExpressPay);
        checkOrderSuccess(data.firstName, paypalExpressPay, data.email);
    }

    @Test
    @Tag("QuickPay")
    @DisplayName("QuickPay")
    void quickPayTestUser() {
        configureUrlsDK();
        Admin admin = new Admin();
        UserTestData data = new UserTestData();
        String userId = admin.createUserAPI(data.firstName, data.lastName, data.email, data.password);
        apiAddToCart();
        openBrowserWithCookiesLogin("/customer/account/login/");
        acceptCookies();
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
    void bankPayTestUser() {
        configureUrlsDK();
        Admin admin = new Admin();
        UserTestData data = new UserTestData();
        String userId = admin.createUserAPI(data.firstName, data.lastName, data.email, data.password);
        apiAddToCart();
        openBrowserWithCookiesLogin("/customer/account/login/");
        acceptCookies();
        login(data.email, data.password);
        open("/checkout");
        fillShippingForm(data.firstName, data.lastName, data.email, data.mobileNumber);
        fillShippingMethod();
        fillPaymentMethod(bankPay);
        checkOrderSuccess(data.firstName, bankPay, data.email);
    }

    @Test
    @Tag("SparkXpress")
    @DisplayName("SparkXpress")
    void sparkXpressPayTestUser() {
        configureUrlsDK();
        Admin admin = new Admin();
        UserTestData data = new UserTestData();
        String userId = admin.createUserAPI(data.firstName, data.lastName, data.email, data.password);
        apiAddToCart();
        openBrowserWithCookiesLogin("/customer/account/login/");
        acceptCookies();
        login(data.email, data.password);
        open("/checkout");
        fillShippingForm(data.firstName, data.lastName, data.email, data.mobileNumber);
        fillShippingMethod();
        fillPaymentMethod(sparkXpressPay);
        checkOrderSuccess(data.firstName, sparkXpressPay, data.email);
    }

    @Test
    @Tag("ViaBill")
    @DisplayName("ViaBill")
    void viaBillPayTestUser() {
        configureUrlsDK();
        Admin admin = new Admin();
        UserTestData data = new UserTestData();
        String userId = admin.createUserAPI(data.firstName, data.lastName, data.email, data.password);
        apiAddToCart();
        openBrowserWithCookiesLogin("/customer/account/login/");
        acceptCookies();
        login(data.email, data.password);
        open("/checkout");
        fillShippingForm(data.firstName, data.lastName, data.email, data.mobileNumber);
        fillShippingMethod();
        fillPaymentMethod(viaBillPay);
        checkOrderSuccess(data.firstName, viaBillPay, data.email);
    }
}
