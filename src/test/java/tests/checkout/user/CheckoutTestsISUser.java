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

@DisplayName("-IS- / USER / PAYMENT METHODS TEST SUITE")
@Tags({@Tag("Checkout"), @Tag("WEB"), @Tag("IS")})
public class CheckoutTestsISUser extends TestBase {
    static void configureUrlsIS() {
        String mainUrl = urlIS;
        baseUrl = mainUrl;
        baseURI = mainUrl;
    }

    @Tag("Netgiro")
    @DisplayName("Netgiro")
    @Test
    void netgiroTestGuestIS() {
        configureUrlsIS();
        UserTestData data = new UserTestData();
        APIAdmin admin = new APIAdmin();
        String userId = admin.createUserAPI(data.firstName, data.lastName, data.email, data.password, baseUrl);
        APIMethods api = new APIMethods();
        openBrowserWithCookiesLogin("/customer/account/login/");
        api.AddToCart(baseUrl);
        login(data.email, data.password);
        open("/checkout");
        fillShippingForm(data.firstName, data.lastName, data.email, data.mobileNumber);
        fillShippingMethod();
        fillPaymentMethod(netgiroPay);
        fillNetgiroPay();
        checkOrderSuccess(data.firstName, netgiroPay, data.email);
    }

    @Tag("Valitor")
    @DisplayName("Valitor")
    @Test
    void valitorPayTestGuestIS() {
        configureUrlsIS();
        UserTestData data = new UserTestData();
        APIAdmin admin = new APIAdmin();
        String userId = admin.createUserAPI(data.firstName, data.lastName, data.email, data.password, baseUrl);
        APIMethods api = new APIMethods();
        openBrowserWithCookiesLogin("/customer/account/login/");
        api.AddToCart(baseUrl);
        login(data.email, data.password);
        open("/checkout");
        fillShippingForm(data.firstName, data.lastName, data.email, data.mobileNumber);
        fillShippingMethod();
        fillPaymentMethod(valitorPay);
        fillValitorPay();
        checkOrderSuccess(data.firstName, valitorPay, data.email);
    }

    @Tag("BankTransfer")
    @DisplayName("Bank Transfer")
    @Test
    void bankPayTestGuestIS() {
        configureUrlsIS();
        UserTestData data = new UserTestData();
        APIAdmin admin = new APIAdmin();
        String userId = admin.createUserAPI(data.firstName, data.lastName, data.email, data.password, baseUrl);
        APIMethods api = new APIMethods();
        openBrowserWithCookiesLogin("/customer/account/login/");
        api.AddToCart(baseUrl);
        login(data.email, data.password);
        open("/checkout");
        fillShippingForm(data.firstName, data.lastName, data.email, data.mobileNumber);
        fillShippingMethod();
        fillPaymentMethod(bankPay);
        checkOrderSuccess(data.firstName, bankPay, data.email);
    }

    @Disabled
    @Tag("SparkXpress")
    @DisplayName("SparkXpress")
    @Test
    void sparkXpressPayTestGuestIS() {
        configureUrlsIS();
        UserTestData data = new UserTestData();
        APIAdmin admin = new APIAdmin();
        String userId = admin.createUserAPI(data.firstName, data.lastName, data.email, data.password, baseUrl);
        APIMethods api = new APIMethods();
        openBrowserWithCookiesLogin("/customer/account/login/");
        api.AddToCart(baseUrl);
        login(data.email, data.password);
        open("/checkout");
        fillShippingForm(data.firstName, data.lastName, data.email, data.mobileNumber);
        fillShippingMethod();
        fillPaymentMethod(sparkXpressPay);
        checkOrderSuccess(data.firstName, sparkXpressPay, data.email);
    }
}
