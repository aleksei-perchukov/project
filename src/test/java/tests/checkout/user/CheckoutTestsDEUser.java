package tests.checkout.user;

import tests.api.APIAdmin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import tests.api.APIMethods;
import tests.checkout.TestBase;
import tests.user.UserTestData;

import static io.restassured.RestAssured.baseURI;
import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.open;
import static tests.data.StaticData.*;
import static tests.checkout.websteps.CheckoutWebSteps.*;
import static tests.data.StaticMethods.openBrowserWithCookiesLogin;

@DisplayName("-DE- / USER / PAYMENT METHODS TEST SUITE")
@Tags({@Tag("Checkout"), @Tag("WEB"), @Tag("DE")})
public class CheckoutTestsDEUser extends TestBase {
    static void configureUrlsDE() {
        String mainUrl = urlDE;
        baseUrl = mainUrl;
        baseURI = mainUrl;
    }

    @Tag("QuickPay")
    @DisplayName("QuickPay")
    @Test
    void quickPayGuestDE() {
        configureUrlsDE();
        UserTestData data = new UserTestData();
        APIAdmin admin = new APIAdmin();
        String userId = admin.createUserAPI(data.firstName, data.lastName, data.email, data.password);
        APIMethods api = new APIMethods();
        api.AddToCart();
        openBrowserWithCookiesLogin("/customer/account/login/");
        login(data.email, data.password);
        open("/checkout");
        fillShippingForm(data.firstName, data.lastName, data.email, data.mobileNumber);
        fillShippingMethod();
        fillPaymentMethod(quickPay);
        fillQuickPay();
        checkOrderSuccess(data.firstName, sparkXpressPay, data.email);
    }

    @Tag("BankTransfer")
    @DisplayName("BankTransfer")
    @Test
    void bankPayTestGuestDE() {
        configureUrlsDE();
        UserTestData data = new UserTestData();
        APIAdmin admin = new APIAdmin();
        String userId = admin.createUserAPI(data.firstName, data.lastName, data.email, data.password);
        APIMethods api = new APIMethods();
        api.AddToCart();
        openBrowserWithCookiesLogin("/customer/account/login/");
        login(data.email, data.password);
        open("/checkout");
        fillShippingForm(data.firstName, data.lastName, data.email, data.mobileNumber);
        fillShippingMethod();
        fillPaymentMethod(bankPay);
        checkOrderSuccess(data.firstName, bankPay, data.email);
    }
}
