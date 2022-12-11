package tests.checkout.guest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import tests.checkout.TestBase;
import tests.user.UserTestData;

import static com.codeborne.selenide.Configuration.baseUrl;
import static data.StaticAPIMethods.apiAddToCart;
import static data.StaticAPIMethods.openBrowserWithCookies;
import static data.StaticData.*;
import static tests.checkout.websteps.CheckoutWebSteps.*;

@DisplayName("-DE- / GUEST / PAYMENT METHODS TEST SUITE")
@Tags({@Tag("Checkout"), @Tag("WEB"), @Tag("DE")})
public class CheckoutTestsDE extends TestBase {
    void configureUrlsDE() {
        mainUrl = urlDE;
        baseUrl = mainUrl;
    }

    @Test
    @Tag("QuickPay")
    @DisplayName("QuickPay")
    void quickPayGuestDE() {
        configureUrlsDE();
        UserTestData testData = new UserTestData();
        apiAddToCart(phpSessId, cookieFormKeyStatic);
        openBrowserWithCookies(phpSessId, cookieFormKeyStatic, "/checkout");
        fillShippingForm(testData.firstName, testData.lastName, testData.email, testData.mobileNumber);
        fillShippingMethod();
        fillPaymentMethod(quickPay);
        fillQuickPay();
        checkOrderSuccess(testData.firstName, quickPay, testData.email);
    }

    @Test
    @Tag("BankTransfer")
    @DisplayName("BankTransfer")
    void bankPayTestGuestDE() {
        configureUrlsDE();
        UserTestData testData = new UserTestData();
        apiAddToCart(phpSessId, cookieFormKeyStatic);
        openBrowserWithCookies(phpSessId, cookieFormKeyStatic, "/checkout");
        fillShippingForm(testData.firstName, testData.lastName, testData.email, testData.mobileNumber);
        fillShippingMethod();
        fillPaymentMethod(bankPay);
        fillQuickPay();
        checkOrderSuccess(testData.firstName, bankPay, testData.email);
    }
}
