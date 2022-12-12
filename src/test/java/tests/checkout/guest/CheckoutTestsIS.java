package tests.checkout.guest;

import org.junit.jupiter.api.*;
import tests.api.APIMethods;
import tests.checkout.TestBase;
import tests.user.UserTestData;

import static io.restassured.RestAssured.baseURI;
import static com.codeborne.selenide.Configuration.*;
import static tests.data.StaticData.*;
import static tests.checkout.websteps.CheckoutWebSteps.*;
import static tests.data.StaticMethods.openBrowserWithCookies;

@DisplayName("-IS- / GUEST / PAYMENT METHODS TEST SUITE")
@Tags({@Tag("Checkout"), @Tag("WEB"), @Tag("IS")})
public class CheckoutTestsIS extends TestBase {
    static void configureUrlsIS() {
        String mainUrl = urlIS;
        baseUrl = mainUrl;
        baseURI = mainUrl;
    }

    @Tag("Netgiro")
    @DisplayName("Netgiro")
    @Test
    void netgiroTestGuestIS() {
        configureUrlsIS();
        UserTestData data = new UserTestData();
        APIMethods api = new APIMethods();
        api.AddToCart();
        openBrowserWithCookies("/checkout");
        fillShippingForm(data.firstName, data.lastName, data.email, data.mobileNumber);
        fillShippingMethod();
        fillPaymentMethod(netgiroPay);
        fillNetgiroPay();
        checkOrderSuccess(data.firstName, netgiroPay, data.email);
    }

    @Tag("Valitor")
    @DisplayName("Valitor")
    @Test
    void valitorPayTestGuestIS() {
        configureUrlsIS();
        UserTestData data = new UserTestData();
        APIMethods api = new APIMethods();
        api.AddToCart();
        openBrowserWithCookies("/checkout");
        fillShippingForm(data.firstName, data.lastName, data.email, data.mobileNumber);
        fillShippingMethod();
        fillPaymentMethod(valitorPay);
        fillValitorPay();
        checkOrderSuccess(data.firstName, valitorPay, data.email);
    }

    @Tag("BankTransfer")
    @DisplayName("Bank Transfer")
    @Test
    void bankPayTestGuestIS() {
        configureUrlsIS();
        UserTestData data = new UserTestData();
        APIMethods api = new APIMethods();
        api.AddToCart();
        openBrowserWithCookies("/checkout");
        fillShippingForm(data.firstName, data.lastName, data.email, data.mobileNumber);
        fillShippingMethod();
        fillPaymentMethod(bankPay);
        checkOrderSuccess(data.firstName, bankPay, data.email);
    }

    @Disabled
    @Tag("SparkXpress")
    @DisplayName("SparkXpress")
    @Test
    void sparkXpressPayTestGuestIS() {
        configureUrlsIS();
        UserTestData data = new UserTestData();
        APIMethods api = new APIMethods();
        api.AddToCart();
        openBrowserWithCookies("/checkout");
        fillShippingForm(data.firstName, data.lastName, data.email, data.mobileNumber);
        fillShippingMethod();
        fillPaymentMethod(sparkXpressPay);
        checkOrderSuccess(data.firstName, sparkXpressPay, data.email);
    }
}
