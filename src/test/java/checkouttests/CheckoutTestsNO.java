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
        Configuration.baseUrl = urlNO;
        baseURI = urlNO;
    };

    @Test
    @DisplayName("-=NO=- PAYMENT METHOD -> Klarna - Guest")
    void klarnaPayTestGuestNO() {
        configureUrls();
        String phpSessIdCookie = PhpSessIdCookieGetter();
        apiAddToCart(phpSessIdCookie);
        openBrowserWithCookies(phpSessIdCookie, "/checkout");
        fillShippingForm();
        fillShippingMethod();
        fillPaymentMethod(klarnaPay);
        fillKlarnaPay();
        checkOrderSuccess(firstName, klarnaPay);
    }

    @Test
    @DisplayName("-=NO=- PAYMENT METHOD -> QuickPay - Guest")
    void quickPayTestGuestNO() {
        configureUrls();
        String phpSessIdCookie = PhpSessIdCookieGetter();
        apiAddToCart(phpSessIdCookie);
        openBrowserWithCookies(phpSessIdCookie, "/checkout");
        fillShippingForm();
        fillShippingMethod();
        fillPaymentMethod(quickPay);
        fillQuickPay();
        checkOrderSuccess(firstName, quickPay);
    }

    @Test
    @DisplayName("-=NO=- PAYMENT METHOD -> Bank Transfer - Guest")
    void bankPayTestGuestNO() {
        configureUrls();
        String phpSessIdCookie = PhpSessIdCookieGetter();
        apiAddToCart(phpSessIdCookie);
        openBrowserWithCookies(phpSessIdCookie, "/checkout");
        fillShippingForm();
        fillShippingMethod();
        fillPaymentMethod(bankPay);
        checkOrderSuccess(firstName, bankPay);
    }
}
