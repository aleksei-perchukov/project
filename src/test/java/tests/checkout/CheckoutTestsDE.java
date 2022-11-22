package tests.checkout;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static tests.checkout.CheckoutApiMethods.*;
import static tests.checkout.CheckoutData.*;
import static tests.checkout.CheckoutWebSteps.*;
import static tests.user.TestData.*;

@DisplayName("-DE- / PAYMENT METHODS TEST SUITE - GUEST")
public class CheckoutTestsDE extends TestBase {
    static String url = urlDE;

    @Test
    @DisplayName("-DE- / PAYMENT METHOD / GUEST / QuickPay")
    void quickPayGuestDE() {
        String phpSessIdCookie = PhpSessIdCookieGetter(url);
        apiAddToCart(phpSessIdCookie, cookieFormKeyStatic, url);
        openBrowserWithCookies(phpSessIdCookie, cookieFormKeyStatic, url + "/checkout");
//        acceptCookies();
        fillShippingForm(url);
        fillShippingMethod();
        fillPaymentMethod(quickPay);
        fillQuickPay();
        checkOrderSuccess(firstName, quickPay);
    }

    @Test
    @DisplayName("-DE- / PAYMENT METHOD / GUEST / BankTransfer")
    void bankPayTestGuestDE() {
        String phpSessIdCookie = PhpSessIdCookieGetter(url);
        apiAddToCart(phpSessIdCookie, cookieFormKeyStatic, url);
        openBrowserWithCookies(phpSessIdCookie, cookieFormKeyStatic, url + "/checkout");
//        acceptCookies();
        fillShippingForm(url);
        fillShippingMethod();
        fillPaymentMethod(bankPay);
        checkOrderSuccess(firstName, bankPay);
    }
}
