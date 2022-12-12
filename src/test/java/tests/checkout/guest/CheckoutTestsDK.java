package tests.checkout.guest;


import io.restassured.RestAssured;
import org.junit.jupiter.api.*;
import tests.checkout.TestBase;
import tests.data.StaticData;
import tests.user.UserTestData;

import static io.restassured.RestAssured.baseURI;
import static tests.data.StaticAPIMethods.apiAddToCart;
import static tests.data.StaticAPIMethods.openBrowserWithCookies;
import static tests.data.StaticData.*;
import static com.codeborne.selenide.Configuration.*;
import static tests.checkout.websteps.CheckoutWebSteps.*;

@Tag("Checkout")
@Tag("DK")
@DisplayName("-DK- / GUEST / PAYMENT METHODS TEST SUITE")
public class CheckoutTestsDK extends TestBase {

    static void configureUrlsDK() {
        mainUrl = urlDK;
        baseUrl = mainUrl;
        baseURI = mainUrl;
    }

    @Disabled
    @Test
    @Tag("PaypalExpress")
    @DisplayName("PaypalExpress")
    void paypalExpressPayTestGuest() {
        configureUrlsDK();
        UserTestData data = new UserTestData();
        apiAddToCart();
        openBrowserWithCookies("/checkout");
        acceptCookies();
        fillShippingForm(data.firstName, data.lastName, data.email, data.mobileNumber);
        fillShippingMethod();
        fillPaymentMethod(paypalExpressPay);
        checkOrderSuccess(data.firstName, paypalExpressPay, data.email);
    }

    @Test
    @Tag("QuickPay")
    @DisplayName("QuickPay")
    void quickPayTestGuest() {
        configureUrlsDK();
        UserTestData data = new UserTestData();
        apiAddToCart();
        openBrowserWithCookies("/checkout");
        acceptCookies();
        fillShippingForm(data.firstName, data.lastName, data.email, data.mobileNumber);
        fillShippingMethod();
        fillPaymentMethod(quickPay);
        fillQuickPay();
        checkOrderSuccess(data.firstName, quickPay, data.email);
    }

    @Test
    @Tag("BankTransfer")
    @DisplayName("Bank Transfer")
    void bankPayTestGuest() {
        configureUrlsDK();
        UserTestData data = new UserTestData();
        apiAddToCart();
        openBrowserWithCookies("/checkout");
        acceptCookies();
        fillShippingForm(data.firstName, data.lastName, data.email, data.mobileNumber);
        fillShippingMethod();
        fillPaymentMethod(bankPay);
        checkOrderSuccess(data.firstName, bankPay, data.email);
    }

    @Test
    @Tag("SparkXpress")
    @DisplayName("SparkXpress")
    void sparkXpressPayTestGuest() {
        configureUrlsDK();
        UserTestData data = new UserTestData();
        apiAddToCart();
        openBrowserWithCookies("/checkout");
        acceptCookies();
        fillShippingForm(data.firstName, data.lastName, data.email, data.mobileNumber);
        fillShippingMethod();
        fillPaymentMethod(sparkXpressPay);
        checkOrderSuccess(data.firstName, sparkXpressPay, data.email);
    }

    @Test
    @Tag("ViaBill")
    @DisplayName("ViaBill")
    void viaBillPayTestGuest() {
        configureUrlsDK();
        UserTestData data = new UserTestData();
        apiAddToCart();
        openBrowserWithCookies("/checkout");
        acceptCookies();
        fillShippingForm(data.firstName, data.lastName, data.email, data.mobileNumber);
        fillShippingMethod();
        fillPaymentMethod(viaBillPay);
        checkOrderSuccess(data.firstName, viaBillPay, data.email);
    }
}
