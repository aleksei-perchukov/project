package tests.checkout.user;

import org.junit.jupiter.api.*;
import tests.api.APIAdmin;
import tests.api.APIMethods;
import tests.checkout.TestBase;
import tests.user.UserTestData;

import static io.restassured.RestAssured.baseURI;
import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.open;
import static tests.data.StaticData.*;
import static tests.checkout.websteps.CheckoutWebSteps.*;
import static tests.data.StaticMethods.openBrowserWithCookiesLogin;

@Tags({@Tag("Checkout"), @Tag("WEB"), @Tag("DK")})
@DisplayName("-DK- / USER / PAYMENT METHODS TEST SUITE")
public class CheckoutTestsDKUser extends TestBase {
    void configureUrlsDK() {
        String mainUrl = urlDK;
        baseUrl = mainUrl;
        baseURI = mainUrl;
    }
    UserTestData data = new UserTestData();
    APIMethods api = new APIMethods();
    APIAdmin admin = new APIAdmin();

    @Disabled
    @Tag("PaypalExpress")
    @DisplayName("PaypalExpress")
    @Test
    void paypalExpressPayTestUser() {
        configureUrlsDK();
        String userId = admin.createUserAPI(data.firstName, data.lastName, data.email, data.password);
        api.AddToCart(baseUrl);
        openBrowserWithCookiesLogin("/customer/account/login/");
        acceptCookies();
        login(data.email, data.password);
        open("/checkout");
        fillShippingForm(data.firstName, data.lastName, data.email, data.mobileNumber);
        fillShippingMethod();
        fillPaymentMethod(paypalExpressPay);
        checkOrderSuccess(data.firstName, paypalExpressPay, data.email);
    }

    @Tag("QuickPay")
    @DisplayName("QuickPay")
    @Test
    void quickPayTestUser() {
        configureUrlsDK();
        String userId = admin.createUserAPI(data.firstName, data.lastName, data.email, data.password);
        api.AddToCart(baseUrl);
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

    @Tag("BankTransfer")
    @DisplayName("Bank Transfer")
    @Test
    void bankPayTestUser() {
        configureUrlsDK();
        String userId = admin.createUserAPI(data.firstName, data.lastName, data.email, data.password);
        api.AddToCart(baseUrl);
        openBrowserWithCookiesLogin("/customer/account/login/");
        acceptCookies();
        login(data.email, data.password);
        open("/checkout");
        fillShippingForm(data.firstName, data.lastName, data.email, data.mobileNumber);
        fillShippingMethod();
        fillPaymentMethod(bankPay);
        checkOrderSuccess(data.firstName, bankPay, data.email);
    }

    @Tag("SparkXpress")
    @DisplayName("SparkXpress")
    @Test
    void sparkXpressPayTestUser() {
        configureUrlsDK();
        String userId = admin.createUserAPI(data.firstName, data.lastName, data.email, data.password);
        api.AddToCart(baseUrl);
        openBrowserWithCookiesLogin("/customer/account/login/");
        acceptCookies();
        login(data.email, data.password);
        open("/checkout");
        fillShippingForm(data.firstName, data.lastName, data.email, data.mobileNumber);
        fillShippingMethod();
        fillPaymentMethod(sparkXpressPay);
        checkOrderSuccess(data.firstName, sparkXpressPay, data.email);
    }

    @Tag("ViaBill")
    @DisplayName("ViaBill")
    @Test
    void viaBillPayTestUser() {
        configureUrlsDK();
        String userId = admin.createUserAPI(data.firstName, data.lastName, data.email, data.password);
        api.AddToCart(baseUrl);
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
