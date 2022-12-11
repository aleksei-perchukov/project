package tests.checkout.guest;


import org.junit.jupiter.api.*;
import tests.checkout.TestBase;
import data.StaticData;
import tests.user.UserTestData;

import static data.StaticAPIMethods.apiAddToCart;
import static data.StaticAPIMethods.openBrowserWithCookies;
import static data.StaticData.*;
import static com.codeborne.selenide.Configuration.*;
import static tests.checkout.websteps.CheckoutWebSteps.*;

@Tag("Checkout")
@Tag("DK")
@DisplayName("-DK- / GUEST / PAYMENT METHODS TEST SUITE")
public class CheckoutTestsDK extends TestBase {

    void configureUrlsDK() {
        mainUrl = urlDK;
        baseUrl = mainUrl;
    }

    @Test
    @Tag("PaypalExpress")
    @DisplayName("PaypalExpress")
    void paypalExpressPayTestGuest() {
        configureUrlsDK();
        UserTestData testData = new UserTestData();
        apiAddToCart(phpSessId, StaticData.cookieFormKeyStatic);
        openBrowserWithCookies(phpSessId, cookieFormKeyStatic, "/checkout");
        acceptCookies();
        fillShippingForm(testData.firstName, testData.lastName, testData.email, testData.mobileNumber);
        fillShippingMethod();
        fillPaymentMethod(paypalExpressPay);
        fillQuickPay();
        checkOrderSuccess(testData.firstName, paypalExpressPay, testData.email);
    }

    @Test
    @Tag("QuickPay")
    @DisplayName("QuickPay")
    void quickPayTestGuest() {
        configureUrlsDK();
        UserTestData testData = new UserTestData();
        apiAddToCart(phpSessId, cookieFormKeyStatic);
        openBrowserWithCookies(phpSessId, cookieFormKeyStatic, "/checkout");
        acceptCookies();
        fillShippingForm(testData.firstName, testData.lastName, testData.email, testData.mobileNumber);
        fillShippingMethod();
        fillPaymentMethod(quickPay);
        fillQuickPay();
        checkOrderSuccess(testData.firstName, quickPay, testData.email);
    }

    @Test
    @Tag("BankTransfer")
    @DisplayName("Bank Transfer")
    void bankPayTestGuest() {
        configureUrlsDK();
        UserTestData testData = new UserTestData();
        apiAddToCart(phpSessId, cookieFormKeyStatic);
        openBrowserWithCookies(phpSessId, cookieFormKeyStatic, "/checkout");
        acceptCookies();
        fillShippingForm(testData.firstName, testData.lastName, testData.email, testData.mobileNumber);
        fillShippingMethod();
        fillPaymentMethod(bankPay);
        fillQuickPay();
        checkOrderSuccess(testData.firstName, bankPay, testData.email);
    }

    @Test
    @Tag("SparkXpress")
    @DisplayName("SparkXpress")
    void sparkXpressPayTestGuest() {
        configureUrlsDK();
        UserTestData testData = new UserTestData();
        apiAddToCart(phpSessId, cookieFormKeyStatic);
        openBrowserWithCookies(phpSessId, cookieFormKeyStatic, "/checkout");
        acceptCookies();
        fillShippingForm(testData.firstName, testData.lastName, testData.email, testData.mobileNumber);
        fillShippingMethod();
        fillPaymentMethod(sparkXpressPay);
        fillQuickPay();
        checkOrderSuccess(testData.firstName, sparkXpressPay, testData.email);
    }

    @Test
    @Tag("ViaBill")
    @DisplayName("ViaBill")
    void viaBillPayTestGuest() {
        configureUrlsDK();
        UserTestData testData = new UserTestData();
        apiAddToCart(phpSessId, cookieFormKeyStatic);
        openBrowserWithCookies(phpSessId, cookieFormKeyStatic, "/checkout");
        acceptCookies();
        fillShippingForm(testData.firstName, testData.lastName, testData.email, testData.mobileNumber);
        fillShippingMethod();
        fillPaymentMethod(viaBillPay);
        fillQuickPay();
        checkOrderSuccess(testData.firstName, viaBillPay, testData.email);
    }
}
