package tests.checkout.user;

import api.AdminAPIMethods;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import tests.checkout.TestBase;
import tests.user.UserTestData;

import static api.AdminAPIMethods.createUserAPI;
import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.open;
import static data.StaticAPIMethods.apiAddToCart;
import static data.StaticAPIMethods.openBrowserWithCookiesLogin;
import static data.StaticData.*;
import static tests.checkout.websteps.CheckoutWebSteps.*;

@DisplayName("-DE- / USER / PAYMENT METHODS TEST SUITE")
@Tags({@Tag("Checkout"), @Tag("WEB"), @Tag("DE")})
public class CheckoutTestsDEUser extends TestBase {
    void configureUrlsDE() {
        mainUrl = urlDE;
        baseUrl = mainUrl;
        AdminAPIMethods adminAPIMethods = new AdminAPIMethods();
    }

    @Test
    @Tag("QuickPay")
    @DisplayName("QuickPay")
    void quickPayGuestDE() {
        configureUrlsDE();
        UserTestData testData = new UserTestData();
        String userId = createUserAPI(testData.firstName, testData.lastName, testData.email, testData.password);
        apiAddToCart(phpSessId, cookieFormKeyStatic);
        openBrowserWithCookiesLogin(cookieFormKeyStatic, "/customer/account/login/");
        login(testData.email, testData.password);
        open("/checkout");
        fillShippingForm(testData.firstName, testData.lastName, testData.email, testData.mobileNumber);
        fillShippingMethod();
        fillPaymentMethod(quickPay);
        fillQuickPay();
        checkOrderSuccess(testData.firstName, sparkXpressPay, testData.email);
    }

    @Test
    @Tag("BankTransfer")
    @DisplayName("BankTransfer")
    void bankPayTestGuestDE() {
        configureUrlsDE();
        UserTestData testData = new UserTestData();
        String userId = createUserAPI(testData.firstName, testData.lastName, testData.email, testData.password);
        apiAddToCart(phpSessId, cookieFormKeyStatic);
        openBrowserWithCookiesLogin(cookieFormKeyStatic, "/customer/account/login/");
        login(testData.email, testData.password);
        open("/checkout");
        fillShippingForm(testData.firstName, testData.lastName, testData.email, testData.mobileNumber);
        fillShippingMethod();
        fillPaymentMethod(bankPay);
        fillQuickPay();
        checkOrderSuccess(testData.firstName, bankPay, testData.email);
    }
}
