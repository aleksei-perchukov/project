package checkouttests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static checkouttests.CheckoutApiMethods.*;
import static checkouttests.CheckoutData.*;
import static checkouttests.CheckoutWebSteps.*;
import static io.restassured.RestAssured.baseURI;
import static usertests.TestData.firstName;

public class CheckoutTestsNO extends TestBase{
    void configureUrls(){
        Configuration.baseUrl = "https://no.skanva.dk";
        baseURI = "https://no.skanva.dk";
    };

    @Test
    @DisplayName("PAYMENT METHOD -> Klarna - Guest")
    void klarnaPayTestGuestNO() {
        configureUrls();
        String phpSessIdCookie = PhpSessIdCookieGetter();
        apiAddToCart(phpSessIdCookie);
        openBrowserWithCookies(phpSessIdCookie, "/checkout");
//        acceptCookies();
        fillShippingForm();
        fillShippingMethod();
        fillPaymentMethod(klarnaPay);
        fillKlarnaPay();
        checkOrderSuccess(firstName, klarnaPay);
    }

    @Test
    @DisplayName("PAYMENT METHOD -> QuickPay - Guest")
    void quickPayTestGuestNO() {
        configureUrls();
        String phpSessIdCookie = PhpSessIdCookieGetter();
        apiAddToCart(phpSessIdCookie);
        openBrowserWithCookies(phpSessIdCookie, "/checkout");
    //    acceptCookies();
        fillShippingForm();
        fillShippingMethod();
        fillPaymentMethod(quickPay);
        fillQuickPay();
        checkOrderSuccess(firstName, quickPay);
    }

    @Test
    @DisplayName("PAYMENT METHOD -> Bank Transfer - Guest")
    void bankPayTestGuestNO() {
        configureUrls();
        String phpSessIdCookie = PhpSessIdCookieGetter();
        apiAddToCart(phpSessIdCookie);
        openBrowserWithCookies(phpSessIdCookie, "/checkout");
//        acceptCookies();
        fillShippingForm();
        fillShippingMethod();
        fillPaymentMethod(bankPay);
        checkOrderSuccess(firstName, bankPay);
    }
}
