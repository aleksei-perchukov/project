package tests.checkout.guest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import tests.checkout.TestBase;
import tests.user.UserTestData;

import static io.restassured.RestAssured.baseURI;
import static com.codeborne.selenide.Configuration.baseUrl;
import static tests.data.StaticAPIMethods.apiAddToCart;
import static tests.data.StaticAPIMethods.openBrowserWithCookies;
import static tests.data.StaticData.*;
import static tests.checkout.websteps.CheckoutWebSteps.*;

@DisplayName("-NO- / GUEST / PAYMENT METHODS TEST SUITE")
@Tags({@Tag("Checkout"), @Tag("WEB"), @Tag("IS")})
public class CheckoutTestsNO extends TestBase {
    static void configureUrlsNO() {
        mainUrl = urlNO;
        baseUrl = mainUrl;
        baseURI = mainUrl;
    }

    @Test
    @Tag("Klarna")
    @DisplayName("Klarna")
    void klarnaPayTestGuestNO() {
        configureUrlsNO();
        UserTestData data = new UserTestData();
        apiAddToCart();
        openBrowserWithCookies("/checkout");
        fillShippingForm(data.firstName, data.lastName, data.email, data.mobileNumber);
        fillShippingMethod();
        fillPaymentMethod(klarnaPay);
        fillKlarnaPay();
        checkOrderSuccess(data.firstName, klarnaPay, data.email);
    }

    @Test
    @DisplayName("QuickPay")
    @Tag("QuickPay")
    void quickPayTestGuestNO() {
        configureUrlsNO();
        UserTestData data = new UserTestData();
        apiAddToCart();
        openBrowserWithCookies("/checkout");
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
        UserTestData data = new UserTestData();
        apiAddToCart();
        openBrowserWithCookies("/checkout");
        fillShippingForm(data.firstName, data.lastName, data.email, data.mobileNumber);
        fillShippingMethod();
        fillPaymentMethod(bankPay);
        checkOrderSuccess(data.firstName, bankPay, data.email);
    }
}
