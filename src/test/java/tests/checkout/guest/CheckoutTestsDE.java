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

@DisplayName("-DE- / GUEST / PAYMENT METHODS TEST SUITE")
@Tags({@Tag("Checkout"), @Tag("WEB"), @Tag("DE")})
public class CheckoutTestsDE extends TestBase {
    static void configureUrlsDE() {
        String mainUrl = urlDE;
        baseUrl = mainUrl;
        baseURI = mainUrl;
    }

    @Test
    @Tag("QuickPay")
    @DisplayName("QuickPay")
    void quickPayGuestDE() {
        configureUrlsDE();
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
    @DisplayName("BankTransfer")
    void bankPayTestGuestDE() {
        configureUrlsDE();
        UserTestData data = new UserTestData();
        apiAddToCart();
        openBrowserWithCookies("/checkout");
        fillShippingForm(data.firstName, data.lastName, data.email, data.mobileNumber);
        fillShippingMethod();
        fillPaymentMethod(bankPay);
        checkOrderSuccess(data.firstName, bankPay, data.email);
    }
}
