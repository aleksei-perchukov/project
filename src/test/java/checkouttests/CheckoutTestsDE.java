package checkouttests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static checkouttests.CheckoutApiMethods.*;
import static checkouttests.CheckoutData.*;
import static checkouttests.CheckoutWebSteps.*;
import static io.restassured.RestAssured.baseURI;
import static usertests.TestData.firstName;

public class CheckoutTestsDE extends TestBase{
    void configureUrls(){
        Configuration.baseUrl = urlDE;
        baseURI = urlDE;
    };

    @Test
    @DisplayName("-=DE=- PAYMENT METHOD -> QuickPay - Guest")
    void quickPayGuestDE() {
        configureUrls();
        String phpSessIdCookie = PhpSessIdCookieGetter();
        apiAddToCart(phpSessIdCookie);
        openBrowserWithCookies(phpSessIdCookie, "/checkout");
//        acceptCookies();
        fillShippingForm();
        fillShippingMethod();
        fillPaymentMethod(quickPay);
        fillNetgiroPay();
        checkOrderSuccess(firstName, quickPay);
    }

    @Test
    @DisplayName("-=DE=- PAYMENT METHOD -> BankTranfer - Guest")
    void bankPayTestGuestDE() {
        configureUrls();
        String phpSessIdCookie = PhpSessIdCookieGetter();
        apiAddToCart(phpSessIdCookie);
        openBrowserWithCookies(phpSessIdCookie, "/checkout");
//        acceptCookies();
        fillShippingForm();
        fillShippingMethod();
        fillPaymentMethod(bankPay);
        fillNetgiroPay();
        checkOrderSuccess(firstName, bankPay);
    }
}
