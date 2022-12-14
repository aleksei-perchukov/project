package tests.checkout.guest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import tests.TestBase;
import tests.api.APIMethods;
import tests.data.UserTestData;

import static io.restassured.RestAssured.baseURI;
import static com.codeborne.selenide.Configuration.baseUrl;
import static tests.data.StaticData.*;
import static tests.checkout.websteps.CheckoutWebSteps.*;
import static tests.data.StaticMethods.openBrowserWithCookies;

@DisplayName("-NO- / GUEST / PAYMENT METHODS TEST SUITE")
@Tags({@Tag("Checkout"), @Tag("WEB"), @Tag("IS")})
public class CheckoutTestsNO extends TestBase {
    void configureUrlsNO() {
        String mainUrl = urlNO;
        baseUrl = mainUrl;
        baseURI = mainUrl;
    }
    UserTestData data = new UserTestData();
    APIMethods api = new APIMethods();

    @Tag("Debug")
    @Tag("Klarna")
    @DisplayName("Klarna")
    @Test
    void klarnaPayTestGuestNO() {
        configureUrlsNO();
        api.AddToCart(baseUrl);
        openBrowserWithCookies("/checkout");
        fillShippingForm(data.firstName, data.lastName, data.email, data.mobileNumber);
        fillShippingMethod();
        fillPaymentMethod(klarnaPay);
        fillKlarnaPay();
        checkOrderSuccess(data.firstName, klarnaPay, data.email);
    }

    @DisplayName("QuickPay")
    @Tag("QuickPay")
    @Test
    void quickPayTestGuestNO() {
        configureUrlsNO();
        api.AddToCart(baseUrl);
        openBrowserWithCookies("/checkout");
        fillShippingForm(data.firstName, data.lastName, data.email, data.mobileNumber);
        fillShippingMethod();
        fillPaymentMethod(quickPay);
        fillQuickPay();
        checkOrderSuccess(data.firstName, quickPay, data.email);
    }

    @Tag("Debug")
    @Tag("BankTransfer")
    @DisplayName("Bank Transfer")
    @Test
    void bankPayTestGuestNO() {
        configureUrlsNO();
        api.AddToCart(baseUrl);
        openBrowserWithCookies("/checkout");
        fillShippingForm(data.firstName, data.lastName, data.email, data.mobileNumber);
        fillShippingMethod();
        fillPaymentMethod(bankPay);
        checkOrderSuccess(data.firstName, bankPay, data.email);
    }
}
