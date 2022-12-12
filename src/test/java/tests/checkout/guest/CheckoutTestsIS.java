package tests.checkout.guest;

import org.junit.jupiter.api.*;
import tests.checkout.TestBase;
import tests.user.UserTestData;

import static io.restassured.RestAssured.baseURI;
import static com.codeborne.selenide.Configuration.*;
import static tests.data.StaticAPIMethods.apiAddToCart;
import static tests.data.StaticAPIMethods.openBrowserWithCookies;
import static tests.data.StaticData.*;
import static tests.checkout.websteps.CheckoutWebSteps.*;

@DisplayName("-IS- / GUEST / PAYMENT METHODS TEST SUITE")
@Tags({@Tag("Checkout"), @Tag("WEB"), @Tag("IS")})
public class CheckoutTestsIS extends TestBase {
    static void configureUrlsIS() {
        String mainUrl = urlIS;
        baseUrl = mainUrl;
        baseURI = mainUrl;
    }

    @Test
    @Tag("Netgiro")
    @DisplayName("Netgiro")
    void netgiroTestGuestIS() {
        configureUrlsIS();
        UserTestData data = new UserTestData();
        apiAddToCart();
        openBrowserWithCookies("/checkout");
        fillShippingForm(data.firstName, data.lastName, data.email, data.mobileNumber);
        fillShippingMethod();
        fillPaymentMethod(netgiroPay);
        fillNetgiroPay();
        checkOrderSuccess(data.firstName, netgiroPay, data.email);
    }

    @Test
    @Tag("Valitor")
    @DisplayName("Valitor")
    void valitorPayTestGuestIS() {
        configureUrlsIS();
        UserTestData data = new UserTestData();
        apiAddToCart();
        openBrowserWithCookies("/checkout");
        fillShippingForm(data.firstName, data.lastName, data.email, data.mobileNumber);
        fillShippingMethod();
        fillPaymentMethod(valitorPay);
        fillValitorPay();
        checkOrderSuccess(data.firstName, valitorPay, data.email);
    }

    @Test
    @Tag("BankTransfer")
    @DisplayName("Bank Transfer")
    void bankPayTestGuestIS() {
        configureUrlsIS();
        UserTestData data = new UserTestData();
        apiAddToCart();
        openBrowserWithCookies("/checkout");
        fillShippingForm(data.firstName, data.lastName, data.email, data.mobileNumber);
        fillShippingMethod();
        fillPaymentMethod(bankPay);
        checkOrderSuccess(data.firstName, bankPay, data.email);
    }

    @Disabled
    @Test
    @Tag("SparkXpress")
    @DisplayName("SparkXpress")
    void sparkXpressPayTestGuestIS() {
        configureUrlsIS();
        UserTestData data = new UserTestData();
        apiAddToCart();
        openBrowserWithCookies("/checkout");
        fillShippingForm(data.firstName, data.lastName, data.email, data.mobileNumber);
        fillShippingMethod();
        fillPaymentMethod(sparkXpressPay);
        checkOrderSuccess(data.firstName, sparkXpressPay, data.email);
    }
}
