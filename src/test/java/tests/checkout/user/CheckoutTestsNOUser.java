package tests.checkout.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import tests.TestBase;
import tests.api.APIAdmin;
import tests.api.APIMethods;
import tests.data.UserTestData;

import static io.restassured.RestAssured.baseURI;
import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.open;
import static tests.data.StaticData.*;
import static tests.checkout.websteps.CheckoutWebSteps.*;
import static tests.data.StaticMethods.openBrowserWithCookiesLogin;

@DisplayName("-NO- / USER / PAYMENT METHODS TEST SUITE")
@Tags({@Tag("Checkout"), @Tag("WEB"), @Tag("IS")})
public class CheckoutTestsNOUser extends TestBase {
    void configureUrlsNO() {
        String mainUrl = urlNO;
        baseUrl = mainUrl;
        baseURI = mainUrl;
    }
    UserTestData data = new UserTestData();
    APIAdmin admin = new APIAdmin();
    APIMethods api = new APIMethods();
    @Tag("Klarna")
    @DisplayName("Klarna")
    @Test
    void klarnaPayTestGuestNO() {
        configureUrlsNO();
        String userId = admin.createUserAPI(data.firstName, data.lastName, data.email, data.password);
        api.AddToCart(baseUrl);
        openBrowserWithCookiesLogin("/customer/account/login/");
        login(data.email, data.password);
        open("/checkout");
        fillShippingForm(data.firstName, data.lastName, data.email, data.mobileNumber);
        fillShippingMethod();
        fillPaymentMethod(klarnaPay);
        fillKlarnaPay();
        checkOrderSuccess(data.firstName, klarnaPay, data.email);
    }

    @Tag("QuickPay")
    @DisplayName("QuickPay")
    @Test
    void quickPayTestGuestNO() {
        configureUrlsNO();
        String userId = admin.createUserAPI(data.firstName, data.lastName, data.email, data.password);
        api.AddToCart(baseUrl);
        openBrowserWithCookiesLogin("/customer/account/login/");
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
    void bankPayTestGuestNO() {
        configureUrlsNO();
        String userId = admin.createUserAPI(data.firstName, data.lastName, data.email, data.password);
        api.AddToCart(baseUrl);
        openBrowserWithCookiesLogin("/customer/account/login/");
        login(data.email, data.password);
        open("/checkout");
        fillShippingForm(data.firstName, data.lastName, data.email, data.mobileNumber);
        fillShippingMethod();
        fillPaymentMethod(bankPay);
        checkOrderSuccess(data.firstName, bankPay, data.email);
    }
}
