package tests.checkout.user;

import org.junit.jupiter.api.*;
import tests.checkout.TestBase;

import static api.AdminAPIMethods.createUserAPI;
import static com.codeborne.selenide.Configuration.*;
import static com.codeborne.selenide.Selenide.open;
import static tests.checkout.websteps.CheckoutApiMethods.*;
import static tests.checkout.CheckoutData.*;
import static tests.checkout.websteps.CheckoutWebSteps.*;
import static utils.StaticData.*;
import static tests.user.TestData.*;

@DisplayName("-DE- / USER / PAYMENT METHODS TEST SUITE")
@Tags({@Tag("Checkout"), @Tag("WEB"), @Tag("DE")})
public class CheckoutTestsDEUser extends TestBase {
    void configureUrlsDE() {
        mainUrl = urlDE;
        baseUrl = mainUrl;
    }

    @Test
    @Tag("QuickPay")
    @DisplayName("QuickPay")
    void quickPayGuestDE() {
        configureUrlsDE();
        String userId = createUserAPI();
        apiAddToCart(phpSessId, cookieFormKeyStatic);
        openBrowserWithCookiesLogin(cookieFormKeyStatic, "/customer/account/login/");
        login();
        open("/checkout");
        fillShippingForm();
        fillShippingMethod();
        fillPaymentMethod(quickPay);
        fillQuickPay();
        checkOrderSuccess(firstName, quickPay);
    }

    @Test
    @Tag("BankTransfer")
    @DisplayName("BankTransfer")
    void bankPayTestGuestDE() {
        configureUrlsDE();
        String userId = createUserAPI();
        apiAddToCart(phpSessId, cookieFormKeyStatic);
        openBrowserWithCookiesLogin(cookieFormKeyStatic, "/customer/account/login/");
        login();
        open("/checkout");
        fillShippingForm();
        fillShippingMethod();
        fillPaymentMethod(bankPay);
        checkOrderSuccess(firstName, bankPay);
    }
}
