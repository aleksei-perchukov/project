package checkouttests;

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
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.qameta.allure.Allure.step;
import static usertests.Components.openPage;
import static usertests.TestData.*;

public class CheckoutTests extends TestBase {
    @Test
    void quickPayTestGuest() throws InterruptedException {
        step("Opening /tr/topstyret-vindue-2-fags page", () -> {openPage("/tr/topstyret-vindue-2-fags");});
//        DevTools chromeDevTools;
//        ChromeDriver driver = (ChromeDriver) getWebDriver();
//        chromeDevTools = driver.getDevTools();
//        chromeDevTools.createSession();
        step("Clicking 'Accept all cookies' button", () -> {$(".coi-banner__accept").click();});
        step("Clicking to 'add to cart' button", () -> {$("#width").sendKeys(Keys.BACK_SPACE, Keys.BACK_SPACE, Keys.BACK_SPACE);});
        step("Clicking to 'add to cart' button", () -> {$("#width").setValue("250");});
        step("Clicking to 'add to cart' button", () -> {$("#height").sendKeys(Keys.BACK_SPACE, Keys.BACK_SPACE, Keys.BACK_SPACE);});
        step("Clicking to 'add to cart' button", () -> {$("#height").setValue("130");});
        step("Clicking to 'add to cart' button", () -> {$(".incr").click();});
 //       Thread.sleep(10000);
 //     step("Clicking to 'add to cart' button", () -> {$(".price").shouldHave(text("8.759,94 kr"));});
        step("Clicking to 'add to cart' button", () -> {$("#product-addtocart-button").shouldBe(enabled).submit();});
  //    step("Clicking to 'minicart' button ", () -> {$(".minicart-wrapper").click();});
        step("Clicking to 'proceed to cart' button", () -> {$(".action.primary").click();});
        step("Clicking to 'proceed to shipping' button", () -> {$(".action.primary.checkout").click();});
        step("Entering " + email + " in email field", () -> {$(".customer-email").setValue(email);});
        step("Entering " + firstName + " in first name field", () -> {$(".firstname").setValue(firstName);});
        step("Entering " + lastName + " in last name field", () -> {$(".lastname").setValue(lastName);});
        step("Entering 'Test str.' in address field", () -> {$("[name=street[0]]").setValue("Test str.");});
        step("Entering '42' in house number field", () -> {$("[name=custom_attributes[house_number]]").setValue("42");});
        step("Entering '8000' in postnummer field", () -> {$("[name=postcode]").setValue("8000");});
        step("Entering '' in mobile phone field", () -> {$(".telephone-input__telephone.input-text").setValue(getRandomLong(00000000L, 99999999L).toString());});

        step("Click on 'Proceed to payment' button", () -> {$(".button.action.continue.primary").click();});
        step("Clicking on the first shipping method", () -> {$(".shipping-method-item").click();});
        step("Clicking on 'confirm shipping method' checkbox", () -> {$(".confirm-shipping-method").click();});
        step("Clicking 'Proceed to payment' button", () -> {$(".button.action.continue.primary").click();});
        step("Clicking on 'Quick Pay' payment method'", () -> {$("#quickpay").click();});
        step("Clicking on 'confirm payment method' checkbox", () -> {$("[for=agreement_quickpay_1]").click();});
        step("", () -> {$("").click();});
        step("Clicking on 'Proceed to payment window' button", () -> {$(".action.primary.checkout").click();});

        step("Entering 1000 0000 0000 0008 card number", () -> {$("#cardnumber").setValue("1000000000000008");});
        step("Entering expiration month", () -> {$("#expiration-month").setValue("0625");});
        step("Entering expiration year", () -> {$("#expiration-year").setValue("26");});
        step("Entering '123' CVC", () -> {$("#cvd").setValue("123");});
        step("Clicking on 'Confirm payment' button", () -> {$(".btn.btn-info").click();});
        step("Checking thank you page title", () -> {$("#success-page-custom").shouldHave(text("Mange tak for din ordre, " + firstName));});
        step("", () -> {$("").click();});

    }
}
