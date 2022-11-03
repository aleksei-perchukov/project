package checkouttests;

import com.codeborne.selenide.ClickOptions;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import usertests.TestBase;

import static Utils.RandomUtils.getRandomLong;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.*;
import static io.qameta.allure.Allure.step;
import static usertests.Components.openPage;
import static usertests.TestData.*;

public class CheckoutTests extends TestBase {
    @Test
    void quickPayTestGuest() {
        step("Opening /tr/topstyret-vindue-2-fags page", () -> {openPage("/tr/topstyret-vindue-2-fags");});
        step("Clicking 'Accept all cookies' button", () -> {$(".coi-banner__accept").click();});
              step("Clicking to 'add to cart' button", () -> {$("#product_addtocart_form").submit();});
        step("Clicking 'Accept all cookies' button", () -> {$("#transparent-button").shouldBe(visible, enabled);});
        step("Clicking to 'add to cart' button", () -> {$("#product_addtocart_form").submit();});
        step("Clicking to 'add to cart' button", () -> {$("#product-addtocart-button").doubleClick();});
        step("Clicking to 'proceed to cart' button", () -> {$("[data-action=close]").click();});

        step("Clicking to 'proceed to cart' button", () -> {$("#shipping-zip-form").submit();});
        step("Clicking to 'proceed to cart' button", () -> {$(".cart-container").click();});
        //      step("Clicking to 'proceed to cart' button", () -> {$("#form-validate").submit();});
        step("Clicking 'Accept all cookies' button", () -> {$("#transparent-button").shouldBe(visible, enabled);});
        step("Clicking to 'proceed to cart' button", () -> {$("#form-validate").submit();});
        step("Clicking to 'proceed to shipping' button", () -> {$("[data-role=proceed-to-checkout]").shouldNotBe(disabled).doubleClick();});

        step("Entering " + email + " in email field", () -> {$("#customer-email").setValue(email);});
        step("Entering " + firstName + " in first name field", () -> {$("[name=firstname]").setValue(firstName);});
        step("Entering " + lastName + " in last name field", () -> {$("[name=lastname]").setValue(lastName);});
        step("Entering 'Test str.' in address field", () -> {$("[name='street[0]']").setValue("Test str.");});
        step("Entering '42' in house number field", () -> {$("[name='custom_attributes[house_number]']").setValue("42");});
        step("Entering '8000' in postnummer field", () -> {$("[name=postcode]").setValue("8000");});
        step("Entering '' in mobile phone field", () -> {$(".telephone-input__telephone.input-text").setValue(getRandomLong(00000000L, 99999999L).toString());});
        step("Click on 'Proceed to shipping method' button", () -> {$("#shipping-address-step").submit();});
  //      step("Click on 'Proceed to shipping method' button", () -> {$(".button.action.continue.primary").click();});

        step("Clicking on the first shipping method", () -> {$(".shipping-method-item").click();});
        step("Clicking on 'confirm shipping method' checkbox", () -> {$("[for=confirm-shipping-method]").click();});
        $("#co-shipping-method-form").submit();
  //      step("Clicking 'Proceed to payment' button", () -> {$(".button.action.continue.primary").doubleClick();});

        step("Clicking on 'Quick Pay' payment method'", () -> {$("[for=quickpay]").click();});
        step("Clicking on 'confirm payment method' checkbox", () -> {$("[for=agreement_quickpay_1]").click(ClickOptions.usingDefaultMethod().offset(-400, 0));});
        step("Clicking on 'confirm payment method' checkbox", () -> {$("#co-payment-form").click();});
  //      step("Clicking on 'Proceed to payment window' button", () -> {$(".action.primary.checkout").click();});

        step("Entering 1000 0000 0000 0008 card number", () -> {$("#cardnumber").setValue("1000000000000008");});
        step("Entering expiration month", () -> {$("#expiration-month").setValue("0625");});
        step("Entering expiration year", () -> {$("#expiration-year").setValue("26");});
        step("Entering '123' CVC", () -> {$("#cvd").setValue("123");});
        step("Clicking on 'Confirm payment' button", () -> {$(".btn.btn-info").click();});
        step("Checking thank you page title", () -> {$("#success-page-custom").shouldHave(text("Mange tak for din ordre, " + firstName));});
//        step("", () -> {$("").click();});
//        step("", () -> {$("").click();});
    }
}
