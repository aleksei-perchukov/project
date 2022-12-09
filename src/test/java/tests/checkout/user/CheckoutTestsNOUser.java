package tests.checkout.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import tests.checkout.TestBase;
import tests.user.TestData;

import static api.AdminAPIMethods.createUserAPI;
import static com.codeborne.selenide.Configuration.baseUrl;
import static tests.checkout.websteps.CheckoutApiMethods.*;
import static tests.checkout.CheckoutData.*;
import static tests.checkout.CheckoutData.bankPay;
import static tests.checkout.websteps.CheckoutWebSteps.*;
import static utils.StaticData.*;
import static utils.StaticData.cookieFormKeyStatic;

@DisplayName("-NO- / GUEST / PAYMENT METHODS TEST SUITE")
@Tags({@Tag("Checkout"), @Tag("WEB"), @Tag("IS")})
public class CheckoutTestsNOUser extends TestBase {
    void configureUrlsNO() {
        mainUrl = urlNO;
        baseUrl = mainUrl;
    }

    @Test
    @Tag("Klarna")
    @DisplayName("Klarna")
    void klarnaPayTestGuestNO() {
        configureUrlsNO();
        String userId = createUserAPI();
        apiAddToCart(phpSessId, cookieFormKeyStatic);
        openBrowserWithCookiesLogin(cookieFormKeyStatic, "/customer/account/login/");
        login();
        fillShippingForm();
        fillShippingMethod();
        fillPaymentMethod(klarnaPay);
        fillKlarnaPay();
        checkOrderSuccess(TestData.firstName, klarnaPay);
    }

    @Test
    @Tag("Debug")
    @Tag("QuickPay")
    @DisplayName("QuickPay")
    void quickPayTestGuestNO() {
        configureUrlsNO();
        String userId = createUserAPI();
        apiAddToCart(phpSessId, cookieFormKeyStatic);
        openBrowserWithCookiesLogin(cookieFormKeyStatic, "/customer/account/login/");
        login();
        fillShippingForm();
        fillShippingMethod();
        fillPaymentMethod(quickPay);
        fillQuickPay();
        checkOrderSuccess(TestData.firstName, quickPay);
    }

    @Test
    @Tag("Bank Transfer")
    @DisplayName("Bank Transfer")
    void bankPayTestGuestNO() {
        configureUrlsNO();
        String userId = createUserAPI();
        apiAddToCart(phpSessId, cookieFormKeyStatic);
        openBrowserWithCookiesLogin(cookieFormKeyStatic, "/customer/account/login/");
        login();
        fillShippingForm();
        fillShippingMethod();
        fillPaymentMethod(bankPay);
        checkOrderSuccess(TestData.firstName, bankPay);
    }
}
