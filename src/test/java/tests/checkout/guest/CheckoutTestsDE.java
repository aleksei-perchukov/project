package tests.checkout.guest;

import org.junit.jupiter.api.*;
import tests.checkout.TestBase;

import static com.codeborne.selenide.Configuration.*;
import static tests.checkout.websteps.CheckoutApiMethods.*;
import static tests.checkout.CheckoutData.*;
import static tests.checkout.websteps.CheckoutWebSteps.*;
import static utils.StaticData.*;
import static tests.user.TestData.*;

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
        apiAddToCart(phpSessId, cookieFormKeyStatic);
        openBrowserWithCookies(phpSessId, cookieFormKeyStatic, "/checkout");
//        acceptCookies();
        fillShippingForm();
        fillShippingMethod();
        fillPaymentMethod(quickPay);
        fillQuickPay();
        checkOrderSuccess(firstName, quickPay);
    }

    @Test
    @Tag("Bank Transfer")
    @DisplayName("BankTransfer")
    void bankPayTestGuestDE() {
        configureUrlsDE();
        apiAddToCart(phpSessId, cookieFormKeyStatic);
        openBrowserWithCookies(phpSessId, cookieFormKeyStatic, "/checkout");
//        acceptCookies();
        fillShippingForm();
        fillShippingMethod();
        fillPaymentMethod(bankPay);
        checkOrderSuccess(firstName, bankPay);
    }
}
