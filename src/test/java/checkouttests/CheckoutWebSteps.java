package checkouttests;

import com.codeborne.selenide.ClickOptions;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;

import java.time.Duration;

import static checkouttests.CheckoutData.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
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
        String zipCode = getZipCode();
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
        step("Shipping form -> Postnummer = '" + zipCode + "'", () -> {
            $("[name=postcode]").setValue(zipCode);
        });
        if(Configuration.baseUrl.equals(urlNO)) {
            $("[name=city]").setValue("Test City");
        }
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
            step("Clicking on " + paymentMethod + " payment method'", () -> {
                $(paymentMethod).click();
            });
            step("Submitting payment form", () -> {
                $("#co-payment-form").submit();
            });
        //           $(".action.primary.checkout").shouldBe(enabled);
            Selenide.sleep(10000);
            if(paymentMethod.equals(paypalExpressPay)) {
                step("Clicking on 'Accept terms of conditions' checkbox", () -> {
                    $("[for=agreement_paypal_express_1]").click(ClickOptions.usingJavaScript().offsetX(-100));
                    Selenide.sleep(10000);
                });
                step("Clicking on 'Place order' button", () -> {
                    if(Configuration.baseUrl.equals(urlDK)) {
                        $$(".action.primary.checkout").get(0).click();
                    } else if(Configuration.baseUrl.equals(urlNO)) {
                        $$(".action.primary.checkout").get(1).click();
                    } else if(Configuration.baseUrl.equals(urlIS)) {

                    } else if(Configuration.baseUrl.equals(urlDE)) {

                    } else if(Configuration.baseUrl.equals(urlSE)) {

                    }
                });
            }
            else if (paymentMethod.equals(quickPay)) {
                step("Clicking on 'Accept terms of conditions' checkbox", () -> {
                    if(Configuration.baseUrl.equals(urlDK)) {
                        $("[for=agreement_quickpay_1]").click(ClickOptions.usingJavaScript().offsetX(-100));
                    } else if(Configuration.baseUrl.equals(urlNO)) {
                        $("[for=agreement_quickpay_3]").click(ClickOptions.usingJavaScript().offsetX(-100));
                    } else if(Configuration.baseUrl.equals(urlIS)) {

                    } else if(Configuration.baseUrl.equals(urlDE)) {

                    } else if(Configuration.baseUrl.equals(urlSE)) {

                    }

                });
                step("Clicking on 'confirm 50% payment' checkbox", () -> {
                    if(Configuration.baseUrl.equals(urlDK)) {
                        $("[for=agreement_quickpay_2]").click();
                    } else if(Configuration.baseUrl.equals(urlNO)) {
                        $("[for=agreement_quickpay_4]").click();
                    } else if(Configuration.baseUrl.equals(urlIS)) {

                    } else if(Configuration.baseUrl.equals(urlDE)) {

                    } else if(Configuration.baseUrl.equals(urlSE)) {

                    }
                    Selenide.sleep(10000);
                });
                step("Clicking on 'Place order' button", () -> {
                    if(Configuration.baseUrl.equals(urlDK)) {
                        $$(".action.primary.checkout").get(1).click();
                    } else if(Configuration.baseUrl.equals(urlNO)) {
                        $$(".action.primary.checkout").get(2).click();
                    } else if(Configuration.baseUrl.equals(urlIS)) {

                    } else if(Configuration.baseUrl.equals(urlDE)) {

                    } else if(Configuration.baseUrl.equals(urlSE)) {

                    }
                });
            }
            else if (paymentMethod.equals(bankPay)) {
                step("Clicking on 'Accept terms of conditions' checkbox", () -> {
                    if(Configuration.baseUrl.equals(urlDK)) {
                        $("[for=agreement_banktransfer_1]").click(ClickOptions.usingJavaScript().offsetX(-100));
                    } else if(Configuration.baseUrl.equals(urlNO)) {
                        $("[for=agreement_banktransfer_3]").click(ClickOptions.usingJavaScript().offsetX(-100));
                    } else if(Configuration.baseUrl.equals(urlIS)) {

                    } else if(Configuration.baseUrl.equals(urlDE)) {

                    } else if(Configuration.baseUrl.equals(urlSE)) {

                    }
                    Selenide.sleep(10000);
                   });
                step("Clicking on 'Place order' button", () -> {
                    if(Configuration.baseUrl.equals(urlDK)) {
                        $$(".action.primary.checkout").get(2).click();
                    } else if(Configuration.baseUrl.equals(urlNO)) {
                        $$(".action.primary.checkout").get(3).click();
                    } else if(Configuration.baseUrl.equals(urlIS)) {

                    } else if(Configuration.baseUrl.equals(urlDE)) {

                    } else if(Configuration.baseUrl.equals(urlSE)) {

                    }
                });
            } else if (paymentMethod.equals(viaBillPay)) {
                step("Clicking on 'Accept terms of conditions' checkbox", () -> {
                    $("[for=agreement_viabill_1]").click(ClickOptions.usingJavaScript().offsetX(-100));
                });
                Selenide.sleep(10000);
                step("Clicking on 'Place order' button", () -> {
                    if(Configuration.baseUrl.equals(urlDK)) {
                        $$(".action.primary.checkout").get(3).click();
                    } else if(Configuration.baseUrl.equals(urlNO)) {
                        $$(".action.primary.checkout").get(5).click();
                    } else if(Configuration.baseUrl.equals(urlIS)) {

                    } else if(Configuration.baseUrl.equals(urlDE)) {

                    } else if(Configuration.baseUrl.equals(urlSE)) {

                    }
                });
            } else if (paymentMethod.equals(sparkXpressPay)) {
                step("Clicking on 'Accept terms of conditions' checkbox", () -> {
                    $("[for=agreement_sparxpres_1]").click(ClickOptions.usingJavaScript().offsetX(-100));
                });
                Selenide.sleep(10000);
                step("Clicking on 'Place order' button", () -> {
                    if(Configuration.baseUrl.equals(urlDK)) {
                        $$(".action.primary.checkout").get(4).click();
                    } else if(Configuration.baseUrl.equals(urlNO)) {

                    } else if(Configuration.baseUrl.equals(urlIS)) {

                    } else if(Configuration.baseUrl.equals(urlDE)) {

                    } else if(Configuration.baseUrl.equals(urlSE)) {

                    }
                });
            } else if (paymentMethod.equals(klarnaPay)) {
                step("Clicking on 'Accept terms of conditions' checkbox", () -> {
                            $("[for=agreement_klarna_pay_later_3]").click(ClickOptions.usingJavaScript().offsetX(-100));
                });
                Selenide.sleep(5000);
                step("Clicking on 'Place order' button", () -> {
                    if (Configuration.baseUrl.equals(urlDK)) {
                        $$(".action.primary.checkout").get(0).click();
                    } else if (Configuration.baseUrl.equals(urlNO)) {
                        $$(".action.primary.checkout").get(0).click();
                    } else if (Configuration.baseUrl.equals(urlIS)) {

                    } else if (Configuration.baseUrl.equals(urlDE)) {

                    } else if (Configuration.baseUrl.equals(urlSE)) {

                    }
                });
            }
        Selenide.sleep(3000);
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

        @Step("-=KLARNA PAY FORM=-")
        static void fillKlarnaPay () {
            step("KLARNA PAY -> Entering '01087000571' personal number", () -> {
                Selenide.sleep(5000);
  //              $("#invoice_kp-purchase-approval-form-national-identification-number__container").click();
                $("[for=invoice_kp-purchase-approval-form-national-identification-number]").sendKeys("01087000571");
            });
            step("KLARNA PAY -> Clicking 'Buy' button", () -> {
                $("#invoice_kp-purchase-approval-form-continue-button").click();
            });
        }

        @Step("Check order success")
        static void checkOrderSuccess (String firstName, String paymentMethod) {
            if (paymentMethod.equals(paypalExpressPay)) {
                $("#headerText").shouldHave(text("Pay with PayPal"));
            } else if (paymentMethod.equals(quickPay) || paymentMethod.equals(bankPay)){
                if(Configuration.baseUrl.equals(urlDK)) {
                    $("#success-page-custom").shouldHave(text("Mange tak for din ordre, " + firstName));
                } else if(Configuration.baseUrl.equals(urlNO)) {
                    $("#success-page-custom").shouldHave(text("Takk for at du handlet hos oss, " + firstName));
                } else if(Configuration.baseUrl.equals(urlIS)) {

                } else if(Configuration.baseUrl.equals(urlDE)) {

                } else if(Configuration.baseUrl.equals(urlSE)) {

                }

            } else if (paymentMethod.equals(bankPay)) {
                $("#success-page-custom").shouldHave(text("Mange tak for din ordre, " + firstName));
            } else if (paymentMethod.equals(viaBillPay)) {
                $(byText("Indkøbskurv")).shouldBe(exist);
            } else if (paymentMethod.equals(sparkXpressPay)) {
                $(byText("sparxpres.dk")).shouldBe(exist);
            } else if (paymentMethod.equals(klarnaPay)) {
                $(".checkout-success__agreement").shouldHave(text("Bestillingen din er nå registrert, og du vil snart motta en e-post med bestillingsbekreftelsen på: " + email));

            }
        }


    }
