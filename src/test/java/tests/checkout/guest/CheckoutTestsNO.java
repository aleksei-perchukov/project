package tests.checkout.guest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import tests.checkout.websteps.CheckoutWebSteps;
import tests.checkout.TestBase;
import tests.user.TestData;

import static com.codeborne.selenide.Configuration.*;
import static tests.checkout.websteps.CheckoutApiMethods.*;
import static tests.checkout.CheckoutData.*;
import static utils.StaticData.*;
import static tests.checkout.websteps.CheckoutWebSteps.*;

@DisplayName("-NO- / GUEST / PAYMENT METHODS TEST SUITE")
@Tags({@Tag("Checkout"), @Tag("WEB"), @Tag("IS")})
public class CheckoutTestsNO extends TestBase {
    void configureUrlsNO() {
        mainUrl = urlNO;
        baseUrl = mainUrl;
    }

    @Test
    @DisplayName("Klarna")
    void klarnaPayTestGuestNO() {
        configureUrlsNO();
        apiAddToCart(phpSessId, cookieFormKeyStatic);
        openBrowserWithCookies(phpSessId, cookieFormKeyStatic, "/checkout");
        fillShippingForm();
        fillShippingMethod();
        fillPaymentMethod(klarnaPay);
        CheckoutWebSteps.fillKlarnaPay();
        checkOrderSuccess(TestData.firstName, klarnaPay);
    }

    @Test
    @Tag("Debug")
    @DisplayName("QuickPay")
    void quickPayTestGuestNO() {
        configureUrlsNO();
        apiAddToCart(phpSessId, cookieFormKeyStatic);
        openBrowserWithCookies(phpSessId, cookieFormKeyStatic, "/checkout");
        fillShippingForm();
        fillShippingMethod();
        fillPaymentMethod(quickPay);
        fillQuickPay();
        checkOrderSuccess(TestData.firstName, quickPay);
    }
    
    @Test
    @DisplayName("Bank Transfer")
    void bankPayTestGuestNO() {
        configureUrlsNO();
        apiAddToCart(phpSessId, cookieFormKeyStatic);
        openBrowserWithCookies(phpSessId, cookieFormKeyStatic, "/checkout");
        fillShippingForm();
        fillShippingMethod();
        fillPaymentMethod(bankPay);
        checkOrderSuccess(TestData.firstName, bankPay);
    }
}
