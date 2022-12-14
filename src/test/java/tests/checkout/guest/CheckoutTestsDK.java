package tests.checkout.guest;


import org.junit.jupiter.api.*;
import tests.TestBase;
import tests.api.APIMethods;
import tests.data.UserTestData;

import static io.restassured.RestAssured.baseURI;
import static tests.data.StaticData.*;
import static com.codeborne.selenide.Configuration.*;
import static tests.checkout.websteps.CheckoutWebSteps.*;
import static tests.data.StaticMethods.openBrowserWithCookies;

@Tags({@Tag("Checkout"), @Tag("WEB"), @Tag("DK")})
@DisplayName("-DK- / GUEST / PAYMENT METHODS TEST SUITE")
public class CheckoutTestsDK extends TestBase {

    void configureUrlsDK() {
        String mainUrl = urlDK;
        baseUrl = mainUrl;
        baseURI = mainUrl;
    }
    UserTestData data = new UserTestData();
    APIMethods api = new APIMethods();

    @Disabled
    @Tag("PaypalExpress")
    @DisplayName("PaypalExpress")
    @Test
    void paypalExpressPayTestGuest() {
        configureUrlsDK();
        api.AddToCart(baseUrl);
        openBrowserWithCookies("/checkout");
        acceptCookies();
        fillShippingForm(data.firstName, data.lastName, data.email, data.mobileNumber);
        fillShippingMethod();
        fillPaymentMethod(paypalExpressPay);
        checkOrderSuccess(data.firstName, paypalExpressPay, data.email);
    }

    @Tag("QuickPay")
    @DisplayName("QuickPay")
    @Test
    void quickPayTestGuest() {
        configureUrlsDK();
        api.AddToCart(baseUrl);
        openBrowserWithCookies("/checkout");
        acceptCookies();
        fillShippingForm(data.firstName, data.lastName, data.email, data.mobileNumber);
        fillShippingMethod();
        fillPaymentMethod(quickPay);
        fillQuickPay();
        checkOrderSuccess(data.firstName, quickPay, data.email);
    }

    @Tag("BankTransfer")
    @DisplayName("Bank Transfer")
    @Test
    void bankPayTestGuest() {
        configureUrlsDK();
        api.AddToCart(baseUrl);
        openBrowserWithCookies("/checkout");
        acceptCookies();
        fillShippingForm(data.firstName, data.lastName, data.email, data.mobileNumber);
        fillShippingMethod();
        fillPaymentMethod(bankPay);
        checkOrderSuccess(data.firstName, bankPay, data.email);
    }

    @Disabled
    @Tag("SparkXpress")
    @DisplayName("SparkXpress")
    @Test
    void sparkXpressPayTestGuest() {
        configureUrlsDK();
        api.AddToCart(baseUrl);
        openBrowserWithCookies("/checkout");
        acceptCookies();
        fillShippingForm(data.firstName, data.lastName, data.email, data.mobileNumber);
        fillShippingMethod();
        fillPaymentMethod(sparkXpressPay);
        checkOrderSuccess(data.firstName, sparkXpressPay, data.email);
    }

    @Tag("ViaBill")
    @DisplayName("ViaBill")
    @Test
    void viaBillPayTestGuest() {
        configureUrlsDK();
        api.AddToCart(baseUrl);
        openBrowserWithCookies("/checkout");
        acceptCookies();
        fillShippingForm(data.firstName, data.lastName, data.email, data.mobileNumber);
        fillShippingMethod();
        fillPaymentMethod(viaBillPay);
        checkOrderSuccess(data.firstName, viaBillPay, data.email);
    }
}
