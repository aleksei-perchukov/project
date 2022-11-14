package checkouttests;

import com.codeborne.selenide.ClickOptions;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;

import java.time.Duration;

import static checkouttests.CheckoutData.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.refresh;
import static io.qameta.allure.Allure.step;
import static usertests.TestData.*;

public class CheckoutWebSteps {
    @Step("Clicking 'Accept all cookies' button")
    static void acceptCookies() {
        $(".coi-banner__accept").click();
    }

    @Step(value = "\"Shipping form -> First name = \" + firstName")
    static void shippingEnterFirstName() {
        $("[name=firstname]").setValue(firstName);
    }

    @Step(value = "\"Shipping form -> Last Name = \" + lastName")
    static void shippingEnterLastName() {
        $("[name=lastname]").setValue(lastName);
    }

    @Step("-=Shipping form=-")
    static void fillShippingForm() {
        step("Shipping form -> First name = " + firstName, () -> {
            $("[name=firstname]").setValue(firstName);
        });
        step("Shipping form -> Last Name = " + lastName, () -> {
            $("[name=lastname]").setValue(lastName);
        });
        step("Shipping form -> Address = 'Test str.'", () -> {
            $("[name='street[0]']").setValue("Test str.");
        });
        step("Shipping form -> House number = '42'", () -> {
            $("[name='custom_attributes[house_number]']").setValue("42");
        });
        step("Shipping form -> Postnummer = '8000'", () -> {
            $("[name=postcode]").setValue("8000");
        });
        step("Shipping form -> Telefonnummer = " + mobileNumber + " in mobile phone field", () -> {
            $(".telephone-input__telephone.input-text").setValue(mobileNumber);
        });
        if (!$("#customer-email").exists()) {
            refresh();
        }
        step("Shipping form -> E-mail = " + email, () -> {
            $("#customer-email").setValue(email);
        });
        step("Click on 'Proceed to shipping method' button", () -> {
            $("#shipping-address-step").submit();
        });
    }

    @Step("-=SHIPPING METHOD=-")
    static void fillShippingMethod() {
        step("Clicking on the first shipping method", () -> {
            if ($(byText("Desværre, ingen produkter er tilgængelige for denne ordre på nuværende tidspunkt.")).exists()) {
                refresh();
            }
            $(".shipping-method-item").click();
        });
        step("Clicking on 'confirm the selected shipping method' checkbox", () -> {
            $("[for=confirm-shipping-method]").click();
        });
        step("Submitting shipping method form", () -> {
            $("#co-shipping-method-form").submit();
        });
    }

    @Step("-=PAYMENT METHOD FORM=-")
    static void fillPaymentMethod(String paymentMethod) {
            step("Clicking on 'Quick Pay' payment method'", () -> {
                $(paymentMethod).click();
            });
            step("Submitting payment form", () -> {
                $("#co-payment-form").submit();
            });
//            $(".action.primary.checkout").shouldBe(enabled, interactable);
            Selenide.sleep(10000);
            if(paymentMethod.equals(paypalExpressPay)) {
                step("Clicking on 'Accept terms of conditions' checkbox", () -> {
                    $("label.label[for=paypal_express_1]").click(ClickOptions.usingJavaScript().offsetX(-100));
                    Selenide.sleep(5000);
                });
            }
            else if (paymentMethod.equals(quickPay)) {
                step("Clicking on 'Accept terms of conditions' checkbox", () -> {
                    $("label.label[for=agreement_quickpay_1]").click(ClickOptions.usingJavaScript().offsetX(-100));
                });
                step("Clicking on 'confirm 50% payment' checkbox", () -> {
                    $("[for=agreement_quickpay_2]").click();
                    Selenide.sleep(5000);
                });
            } else if (paymentMethod.equals(bankPay)) {
                step("Clicking on 'Accept terms of conditions' checkbox", () -> {
                    $("label.label[for=agreement_banktransfer_1]").click(ClickOptions.usingJavaScript().offsetX(-100));
                    Selenide.sleep(10000);
                });
            } else if (paymentMethod.equals(viaBillPay)) {
                step("Clicking on 'Accept terms of conditions' checkbox", () -> {
                    $("label.label[for=agreement_viabill_1]").click(ClickOptions.usingJavaScript().offsetX(-100));
                    Selenide.sleep(5000);
                });
            } else if (paymentMethod.equals(sparkXpressPay)) {
                step("Clicking on 'Accept terms of conditions' checkbox", () -> {
                    $("label.label[for=agreement_sparxpres_1]").click(ClickOptions.usingJavaScript().offsetX(-100));
                    Selenide.sleep(5000);
                });
            }
 //           $(byText("Godkend og betal")).click();
                step("Clicking on 'Place order' button", () -> {
                    $("[title='Godkend og betal']").shouldBe(enabled, interactable).click(ClickOptions.withTimeout(Duration.ofSeconds(3)));
                    Selenide.sleep(5000);
                    $("[title='Godkend og betal']").shouldBe(enabled, interactable).click(ClickOptions.withTimeout(Duration.ofSeconds(3)));
                });
        }

        @Step("-=QUICK PAY FORM=-")
        static void fillQuickPay () {
            step("Quick Pay -> Card number = 1000 0000 0000 0008", () -> {
                $("#cardnumber").setValue("1000000000000008");
            });
            step("Quick Pay -> Month = 06", () -> {
                $("#expiration-month").setValue("06");
            });
            step("Quick Pay -> Year = 26", () -> {
                $("#expiration-year").setValue("26");
            });
            step("Quick Pay -> CVC = 123", () -> {
                $("#cvd").setValue("123");
            });
            step("Clicking on 'Confirm payment' button", () -> {
                $(".btn.btn-info").click();
            });
        }

        @Step("Check order success")
        static void checkOrderSuccess (String firstName){
            $("#success-page-custom").shouldHave(text("Mange tak for din ordre, " + firstName));
        }


    }
