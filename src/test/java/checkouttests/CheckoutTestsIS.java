package checkouttests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static checkouttests.CheckoutApiMethods.*;
import static checkouttests.CheckoutData.*;
import static checkouttests.CheckoutData.bankPay;
import static checkouttests.CheckoutWebSteps.*;
import static io.restassured.RestAssured.baseURI;
import static usertests.TestData.firstName;

public class CheckoutTestsIS extends TestBase {
    void configureUrls(){
        Configuration.baseUrl = urlIS;
        baseURI = urlIS;
    };

    @Disabled
    @Test
    @DisplayName("-=IS=- PAYMENT METHOD -> Netgiro - Guest")
    void netgiroTestGuestIS() {
        configureUrls();
        String phpSessIdCookie = PhpSessIdCookieGetter();
        apiAddToCart(phpSessIdCookie);
        openBrowserWithCookies(phpSessIdCookie, "/checkout");
        fillShippingForm();
        fillShippingMethod();
        fillPaymentMethod(netgiroPay);
        fillNetgiroPay();
        checkOrderSuccess(firstName, netgiroPay);
    }

    @Test
    @DisplayName("-=IS=- PAYMENT METHOD -> Valitor - Guest")
    void valitorPayTestGuestIS() {
        configureUrls();
        String phpSessIdCookie = PhpSessIdCookieGetter();
        apiAddToCart(phpSessIdCookie);
        openBrowserWithCookies(phpSessIdCookie, "/checkout");
        fillShippingForm();
        fillShippingMethod();
        fillPaymentMethod(valitorPay);
        fillValitorPay();
        checkOrderSuccess(firstName, valitorPay);
    }

    @Test
    @DisplayName("-=IS=- PAYMENT METHOD -> Bank Transfer - Guest")
    void bankPayTestGuestIS() {
        configureUrls();
        String phpSessIdCookie = PhpSessIdCookieGetter();
        apiAddToCart(phpSessIdCookie);
        openBrowserWithCookies(phpSessIdCookie, "/checkout");
        fillShippingForm();
        fillShippingMethod();
        fillPaymentMethod(bankPay);
        checkOrderSuccess(firstName, bankPay);
    }

    @Disabled
    @Test
    @DisplayName("-=IS=- PAYMENT METHOD -> SparkXpress - Guest")
    void sparkXpressPayTestGuestIS() {
        configureUrls();
        String phpSessIdCookie = PhpSessIdCookieGetter();
        apiAddToCart(phpSessIdCookie);
        openBrowserWithCookies(phpSessIdCookie, "/checkout");
 //       acceptCookies();
        fillShippingForm();
        fillShippingMethod();
        fillPaymentMethod(sparkXpressPay);
        checkOrderSuccess(firstName, sparkXpressPay);
    }
}
