package tests.checkout;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;
import utils.StaticData;

import static api.AdminAPIMethods.createUserAPI;
import static api.AdminAPIMethods.deleteUserAPI;
import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.refresh;
import static tests.checkout.CheckoutApiMethods.*;
import static tests.checkout.CheckoutData.*;
import static tests.checkout.CheckoutData.viaBillPay;
import static tests.checkout.CheckoutWebSteps.*;
import static tests.checkout.CheckoutWebSteps.checkOrderSuccess;
import static tests.user.TestData.createUserPage;
import static tests.user.TestData.firstName;
import static utils.StaticData.*;

@Tag("Checkout")
@Tag("DK")
@DisplayName("-DK- / USER / PAYMENT METHODS TEST SUITE")
public class CheckoutTestsDKUser extends TestBase {
    void configureUrlsDK() {
        mainUrl = urlDK;
        baseUrl = mainUrl;
    }

@Disabled
    @Test
    @DisplayName("PaypalExpress")
    void paypalExpressPayTestUser() {
        configureUrlsDK();
        String userId = createUserAPI();
        apiAddToCart(phpSessId, cookieFormKeyStatic);
        openBrowserWithCookiesLogin(cookieFormKeyStatic, "/customer/account/login/");
        acceptCookies();
        login();
        open("/checkout");
        fillShippingForm();
        fillShippingMethod();
        fillPaymentMethod(paypalExpressPay);
        checkOrderSuccess(firstName, paypalExpressPay);
        deleteUserAPI(userId);
    }
    @Tag("Debug")
    @Test
    @DisplayName("QuickPay")
    void quickPayTestUser() {
        configureUrlsDK();
        String userId = createUserAPI();
        apiAddToCart(phpSessId, cookieFormKeyStatic);
        openBrowserWithCookiesLogin(cookieFormKeyStatic, "/customer/account/login/");
        acceptCookies();
        login();
        open("/checkout");
        fillShippingForm();
        fillShippingMethod();
        fillPaymentMethod(quickPay);
        fillQuickPay();
        checkOrderSuccess(firstName, quickPay);
        deleteUserAPI(userId);
    }

    @Test
    @DisplayName("Bank Transfer")
    void bankPayTestUser() {
        configureUrlsDK();
        String userId = createUserAPI();
        apiAddToCart(phpSessId, cookieFormKeyStatic);
        openBrowserWithCookiesLogin(cookieFormKeyStatic, "/customer/account/login/");
        acceptCookies();
        login();
        open("/checkout");
        fillShippingForm();
        fillShippingMethod();
        fillPaymentMethod(bankPay);
        checkOrderSuccess(firstName, bankPay);
    }

    @Test
    @DisplayName("SparkXpress")
    void sparkXpressPayTestUser() {
        configureUrlsDK();
        String userId = createUserAPI();
        apiAddToCart(phpSessId, cookieFormKeyStatic);
        openBrowserWithCookiesLogin(cookieFormKeyStatic, "/customer/account/login/");
        acceptCookies();
        login();
        open("/checkout");
        fillShippingForm();
        fillShippingMethod();
        fillPaymentMethod(sparkXpressPay);
        checkOrderSuccess(firstName, sparkXpressPay);
    }

    @Test
    @DisplayName("ViaBill")
    void viaBillPayTestUser() {
        configureUrlsDK();
        String userId = createUserAPI();
        apiAddToCart(phpSessId, cookieFormKeyStatic);
        openBrowserWithCookiesLogin(cookieFormKeyStatic, "/customer/account/login/");
        acceptCookies();
        login();
        open("/checkout");
        fillShippingForm();
        fillShippingMethod();
        fillPaymentMethod(viaBillPay);
        checkOrderSuccess(firstName, viaBillPay);
    }
}
