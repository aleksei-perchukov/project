package tests.checkout;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static tests.checkout.CheckoutApiMethods.*;
import static tests.checkout.CheckoutData.*;
import static tests.checkout.CheckoutWebSteps.*;
import static io.restassured.RestAssured.baseURI;
import static tests.user.TestData.*;


@DisplayName("-=DE=- PAYMENT METHODS TEST SUITE")
public class CheckoutTestsDE extends TestBase{
    void configureUrlsDE(){
        Configuration.baseUrl = urlDE;
        baseURI = urlDE;
    }


    @Test
    @DisplayName("-=DE=- PAYMENT METHOD -> QuickPay - Guest")
    void quickPayGuestDE() {
        configureUrlsDE();
        String phpSessIdCookie = PhpSessIdCookieGetter();
        apiAddToCart(phpSessIdCookie, cookieFormKeyStatic);
        openBrowserWithCookies(phpSessIdCookie, cookieFormKeyStatic,"/checkout");
//        acceptCookies();
        fillShippingForm();
        fillShippingMethod();
        fillPaymentMethod(quickPay);
        fillQuickPay();
        checkOrderSuccess(firstName, quickPay);
    }

    @Test
    @DisplayName("-=DE=- PAYMENT METHOD -> BankTransfer - Guest")
    void bankPayTestGuestDE() {
        configureUrlsDE();
        String phpSessIdCookie = PhpSessIdCookieGetter();
        apiAddToCart(phpSessIdCookie, cookieFormKeyStatic);
        openBrowserWithCookies(phpSessIdCookie, cookieFormKeyStatic, "/checkout");
//        acceptCookies();
        fillShippingForm();
        fillShippingMethod();
        fillPaymentMethod(bankPay);
        checkOrderSuccess(firstName, bankPay);
    }
}
