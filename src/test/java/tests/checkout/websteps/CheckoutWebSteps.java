package tests.checkout.websteps;

import com.codeborne.selenide.ClickOptions;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;
import org.openqa.selenium.Cookie;
import tests.checkout.CheckoutTestData;
import tests.user.UserTestData;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;
import static tests.data.StaticData.*;
import static tests.data.StaticMethods.openPage;

public class CheckoutWebSteps {

    @Step("Login")
    public static void login(String email, String password){
        $("#email").setValue(email);
        $("#pass").setValue(password);
        $("[for=show-password]").click();
//        $("#login-form").submit();
        $("#send2").click(ClickOptions.usingJavaScript());
        $(".block.block-dashboard-info").shouldHave(text(email));
    }
    @Step("Clicking 'Accept all cookies' button")
    public static void acceptCookies() {
        $(".coi-banner__accept").click();
    }

    @Step("-SHIPPING FORM-")
    public static void fillShippingForm(String firstName, String lastName, String email, String mobileNumber) {
        CheckoutTestData data = new CheckoutTestData();
        String zipCode = data.getZipCode();
        if (Configuration.baseUrl.equals(urlIS)) {
            step("Shipping form -> Kennitala = '1234567890'", () ->
                    $$(".field._required.full-width").first().$(".input-text").setValue("1234567890")
            );
        }
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
        if (Configuration.baseUrl.equals(urlNO) || Configuration.baseUrl.equals(urlIS) || Configuration.baseUrl.equals(urlDE)) {
            $("[name=city]").setValue("Test City");
        }
        step("Shipping form -> Telefonnummer = " + mobileNumber + " in mobile phone field", () -> {
            $(".telephone-input__telephone.input-text").setValue(mobileNumber);
            refresh();
            Selenide.sleep(5000);
        });
        step("Shipping form -> E-mail = " + email, () -> {
            if ($("#customer-email").isDisplayed()) {
                $("#customer-email").sendKeys(email);
        }
        });
        step("Click on 'Proceed to shipping method' button", () -> {
//            $("#shipping-address-step").submit();
            $(".button.action.continue.primary").submit();
        });
    }

    @Step("-SHIPPING METHOD-")
    public static void fillShippingMethod() {
        step("Clicking on the first shipping method", () -> {
            Selenide.sleep(2000);
            refresh();
            $(".shipping-method-item").click();
        });
        step("Clicking on 'confirm the selected shipping method' checkbox", () -> {
            $("[for=confirm-shipping-method]").click();
        });
        step("Submitting shipping method form", () -> {
            $("#co-shipping-method-form").submit();
        });
    }

    @Step("-PAYMENT METHOD FORM-")
    public static void fillPaymentMethod(String paymentMethod) {
        step("Clicking on " + paymentMethod + " payment method", () -> {
            if(paymentMethod.equals("paypalExpressPay")) {
                Selenide.sleep(7000);
         //       $("[for=paypal_express]").click();
                $(byText("PayPal Express Checkout")).doubleClick();
            } else if(paymentMethod.equals(quickPay)) {
                $("[for=quickpay]").click();
            } else if(paymentMethod.equals(bankPay)) {
                $("[for=banktransfer]").click();
            } else if(paymentMethod.equals(viaBillPay)) {
                $("[for=viabill]").click();
            } else if(paymentMethod.equals(sparkXpressPay)) {
                $("[for=sparxpres]").click();
            } else if(paymentMethod.equals(klarnaPay)) {
                $("[for=klarna_pay_later]").click();
            } else if(paymentMethod.equals(netgiroPay)) {
                $("#netgiro").click();
            } else if(paymentMethod.equals(valitorPay)) {
                $("#valitor").click();
            } else if(paymentMethod.equals("")) {
                $(paymentMethod).click();
            } else if(paymentMethod.equals("")) {
                $(paymentMethod).click();
            }
        });
        step("Submitting payment form", () -> {
            $("#co-payment-form").submit();
        });
        Selenide.sleep(5000);
        if (paymentMethod.equals(paypalExpressPay)) {
            step("Clicking on 'Accept terms of conditions' checkbox", () -> {
                $("[for=agreement_paypal_express_1]").click(ClickOptions.usingJavaScript().offsetX(-100));
                Selenide.sleep(5000);
            });
            step("Clicking on 'Place order' button", () -> {
                if (Configuration.baseUrl.equals(urlDK)) {
                    $$(".action.primary.checkout").get(0).click(ClickOptions.usingJavaScript());
                } else if (Configuration.baseUrl.equals(urlNO)) {
                    $$(".action.primary.checkout").get(1).click(ClickOptions.usingJavaScript());
                } else if (Configuration.baseUrl.equals(urlIS)) {

                } else if (Configuration.baseUrl.equals(urlDE)) {

                } else if (Configuration.baseUrl.equals(urlSE)) {

                }
            });
        } else if (paymentMethod.equals(quickPay)) {
            step("Clicking on 'Accept terms of conditions' checkbox", () -> {
                if (Configuration.baseUrl.equals(urlDK)) {
                    $("[for=agreement_quickpay_1]").click(ClickOptions.usingJavaScript().offsetX(-100));
                } else if (Configuration.baseUrl.equals(urlNO)) {
                    $("[for=agreement_quickpay_3]").click(ClickOptions.usingJavaScript().offsetX(-100));
                } else if (Configuration.baseUrl.equals(urlIS)) {
                    $("[for=agreement_quickpay_5]").click(ClickOptions.usingJavaScript().offsetX(-100));
                } else if (Configuration.baseUrl.equals(urlDE)) {
                    $("[for=agreement_quickpay_5]").click(ClickOptions.usingJavaScript().offsetX(100));
                } else if (Configuration.baseUrl.equals(urlSE)) {

                }

            });
            step("Clicking on 'confirm 50% payment' checkbox", () -> {
                if (Configuration.baseUrl.equals(urlDK)) {
                    $("[for=agreement_quickpay_2]").click(ClickOptions.usingJavaScript().offsetX(-100));
                } else if (Configuration.baseUrl.equals(urlNO)) {
                    $("[for=agreement_quickpay_4]").click(ClickOptions.usingJavaScript().offsetX(-100));
                } else if (Configuration.baseUrl.equals(urlIS)) {

                } else if (Configuration.baseUrl.equals(urlDE)) {
                    $("[for=agreement_quickpay_7]").click(ClickOptions.usingJavaScript().offsetX(-100));
                } else if (Configuration.baseUrl.equals(urlSE)) {

                }
                Selenide.sleep(10000);
            });
            step("Clicking on 'Place order' button", () -> {
                if (Configuration.baseUrl.equals(urlDK)) {
                    $$(".action.primary.checkout").get(1).click(ClickOptions.usingJavaScript());
                } else if (Configuration.baseUrl.equals(urlNO)) {
                    $$(".action.primary.checkout").get(2).click(ClickOptions.usingJavaScript());
                } else if (Configuration.baseUrl.equals(urlIS)) {
                    $$(".action.primary.checkout").get(2).click(ClickOptions.usingJavaScript());
                } else if (Configuration.baseUrl.equals(urlDE)) {
                    $$(".action.primary.checkout").get(1).click(ClickOptions.usingJavaScript());
                } else if (Configuration.baseUrl.equals(urlSE)) {

                }
            });
        } else if (paymentMethod.equals(bankPay)) {
            step("Clicking on 'Accept terms of conditions' checkbox", () -> {
                if (Configuration.baseUrl.equals(urlDK)) {
                    $("[for=agreement_banktransfer_1]").click(ClickOptions.usingJavaScript().offsetX(-100));
                } else if (Configuration.baseUrl.equals(urlNO)) {
                    $("[for=agreement_banktransfer_3]").click(ClickOptions.usingJavaScript().offsetX(-100));
                } else if (Configuration.baseUrl.equals(urlIS)) {
                    $("[for=agreement_banktransfer_6]").click(ClickOptions.usingJavaScript().offsetX(-100));
                } else if (Configuration.baseUrl.equals(urlDE)) {
                    $("[for=agreement_banktransfer_5]").click(ClickOptions.usingJavaScript().offsetX(-100));
                } else if (Configuration.baseUrl.equals(urlSE)) {

                }
                Selenide.sleep(10000);
            });
            step("Clicking on 'Place order' button", () -> {
                if (Configuration.baseUrl.equals(urlDK)) {
                    $$(".action.primary.checkout").get(2).click(ClickOptions.usingJavaScript());
                } else if (Configuration.baseUrl.equals(urlNO)) {
                    $$(".action.primary.checkout").get(3).click(ClickOptions.usingJavaScript());
                } else if (Configuration.baseUrl.equals(urlIS)) {
                    $$(".action.primary.checkout").get(2).click(ClickOptions.usingJavaScript());
                } else if (Configuration.baseUrl.equals(urlDE)) {
                    $$(".action.primary.checkout").get(2).click(ClickOptions.usingJavaScript());
                } else if (Configuration.baseUrl.equals(urlSE)) {

                }
            });
        } else if (paymentMethod.equals(viaBillPay)) {
            step("Clicking on 'Accept terms of conditions' checkbox", () -> {
                $("[for=agreement_viabill_1]").click(ClickOptions.usingJavaScript().offsetX(-100));
            });
            Selenide.sleep(10000);
            step("Clicking on 'Place order' button", () -> {
                if (Configuration.baseUrl.equals(urlDK)) {
                    $$(".action.primary.checkout").get(3).click(ClickOptions.usingJavaScript());
                    $(byText("APPROVED")).click();
                } else if (Configuration.baseUrl.equals(urlNO)) {
                    $$(".action.primary.checkout").get(5).click(ClickOptions.usingJavaScript());
                } else if (Configuration.baseUrl.equals(urlIS)) {

                } else if (Configuration.baseUrl.equals(urlDE)) {

                } else if (Configuration.baseUrl.equals(urlSE)) {

                }
            });
        } else if (paymentMethod.equals(sparkXpressPay)) {
            step("Clicking on 'Accept terms of conditions' checkbox", () -> {
                if (Configuration.baseUrl.equals(urlDK)) {
                    $("[for=agreement_sparxpres_1]").click(ClickOptions.usingJavaScript().offsetX(-100));
                } else if (Configuration.baseUrl.equals(urlNO)) {

                } else if (Configuration.baseUrl.equals(urlIS)) {
                    $("[for=agreement_sparxpres_6]").click(ClickOptions.usingJavaScript().offsetX(-100));
                } else if (Configuration.baseUrl.equals(urlDE)) {

                } else if (Configuration.baseUrl.equals(urlSE)) {

                }
            });
            Selenide.sleep(5000);
            step("Clicking on 'Place order' button", () -> {

                if (Configuration.baseUrl.equals(urlDK)) {
                    $$(".action.primary.checkout").get(4).click(ClickOptions.usingJavaScript());
                } else if (Configuration.baseUrl.equals(urlNO)) {

                } else if (Configuration.baseUrl.equals(urlIS)) {
                    $$(".action.primary.checkout").get(3).click(ClickOptions.usingJavaScript());
                } else if (Configuration.baseUrl.equals(urlDE)) {

                } else if (Configuration.baseUrl.equals(urlSE)) {

                }
            });
        } else if (paymentMethod.equals(klarnaPay)) {
            step("Clicking on 'Accept terms of conditions' checkbox", () -> {
                $("[for=agreement_klarna_pay_later_3]").click(ClickOptions.usingJavaScript().offsetX(-100));
            });
            Selenide.sleep(5000);
            step("Clicking on 'Place order' button", () -> {
                if (Configuration.baseUrl.equals(urlDK)) {
                    $$(".action.primary.checkout").get(0).click(ClickOptions.usingJavaScript());
                } else if (Configuration.baseUrl.equals(urlNO)) {
                    $$(".action.primary.checkout").get(0).click(ClickOptions.usingJavaScript());
                } else if (Configuration.baseUrl.equals(urlIS)) {

                } else if (Configuration.baseUrl.equals(urlDE)) {

                } else if (Configuration.baseUrl.equals(urlSE)) {

                }
            });
        } else if (paymentMethod.equals(netgiroPay)) {
            step("Clicking on 'Accept terms of conditions' checkbox", () -> {
                $("[for=agreement_netgiro_6]").click(ClickOptions.usingJavaScript().offsetX(-100));
            });
            Selenide.sleep(5000);
            step("Clicking on 'Place order' button", () -> {
                if (Configuration.baseUrl.equals(urlDK)) {

                } else if (Configuration.baseUrl.equals(urlNO)) {

                } else if (Configuration.baseUrl.equals(urlIS)) {
                    $$(".action.primary.checkout").get(0).click(ClickOptions.usingJavaScript());
                } else if (Configuration.baseUrl.equals(urlDE)) {

                } else if (Configuration.baseUrl.equals(urlSE)) {

                }
            });
        } else if (paymentMethod.equals(valitorPay)) {
            step("Clicking on 'Accept terms of conditions' checkbox", () -> {
                $("[for=agreement_valitor_6]").click(ClickOptions.usingJavaScript().offsetX(-100));
            });
        }
        Selenide.sleep(3000);
    }

    @Step("-QUICK PAY FORM-")
    public static void fillQuickPay() {
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

    @Step("-KLARNA PAY FORM-")
    public static void fillKlarnaPay() {
        step("KLARNA PAY -> Entering '01087000571' personal number", () -> {
            Selenide.sleep(5000);
            switchTo().innerFrame("klarna-pay-later-fullscreen");
            $("#invoice_kp-purchase-approval-form-national-identification-number").setValue("01087000571");
        });
        step("KLARNA PAY -> Clicking 'Buy' button", () -> {
            $("#invoice_kp-purchase-approval-form-continue-button").click();
        });
    }

    @Step("-NETGIRO PAY FORM-")
    public static void fillNetgiroPay() {
        UserTestData data = new UserTestData();
        step("NETGIRO PAY -> Entering '1231231' mobile number", () -> {
            Selenide.sleep(5000);
            $("#customerPaymentRequestGSM").sendKeys("1231231");
        });
        step("NETGIRO PAY -> Clicking 'Buy' button", () -> {
            $(".tab-header.p-r-5.custom-link.ssn-login-tab").click();
        });
        step("NETGIRO PAY -> Entering '1234567890' in Kennitala field", () -> {
            $("#customerSSN").setValue("1234567890");
        });
        step("NETGIRO PAY -> Entering password", () -> {
            $("#customerPassword").setValue(data.password);
        });
        step("NETGIRO PAY -> Clicking 'Buy' button", () -> {
            $(".tab-header.custom-link.payment-request-tab").click();
        });
    }

    @Step("-VALITOR PAY FORM-")
    public static void fillValitorPay() {
        step("VALITOR PAY -> Entering '2223000010246699' credit card number", () -> {
            Selenide.sleep(5000);
            $("#valitor_cc_number").sendKeys("2223000010246699");
        });
        step("VALITOR PAY -> Entering '12' month", () -> {
            $("#valitor_expiration").selectOptionContainingText("12");
        });
        step("VALITOR PAY -> Entering '25' year", () -> {
            $("#valitor_expiration_yr").selectOptionContainingText("2025");
        });
        step("VALITOR PAY -> Entering '123' CVC", () -> {
            $("#valitor_cc_cid").setValue("123");
        });
        step("VALITOR PAY -> Clicking 'Buy' button", () -> {
            $$(".action.primary.checkout").get(1).click(ClickOptions.usingJavaScript());
            $("#webform0").submit();
            $("[name=submitBtn]").submit();
        });
    }

    @Step("Check order success")
    public static void checkOrderSuccess(String firstName, String paymentMethod, String email) {
        if (paymentMethod.equals(paypalExpressPay)) {
            $("#headerText").shouldHave(text("PayPal"));
        } else if (paymentMethod.equals(quickPay) || paymentMethod.equals(bankPay)) {
            if (Configuration.baseUrl.equals(urlDK)) {
                $("#success-page-custom").shouldHave(text("Mange tak for din ordre, " + firstName));
            } else if (Configuration.baseUrl.equals(urlNO)) {
                $("#success-page-custom").shouldHave(text("Takk for at du handlet hos oss, " + firstName));
            } else if (Configuration.baseUrl.equals(urlIS)) {

            } else if (Configuration.baseUrl.equals(urlDE)) {

            } else if (Configuration.baseUrl.equals(urlSE)) {

            }

        } else if (paymentMethod.equals(bankPay)) {
            if (Configuration.baseUrl.equals(urlDK)) {
                $("#success-page-custom").shouldHave(text("Mange tak for din ordre, " + firstName));
            } else if (Configuration.baseUrl.equals(urlNO)) {
                $("#success-page-custom").shouldHave(text("Mange tak for din ordre, " + firstName));
            } else if (Configuration.baseUrl.equals(urlIS)) {
                $("#success-page-custom").shouldHave(text("Þakka þér fyrir kaupin, " + firstName));
                Selenide.sleep(60000);
            } else if (Configuration.baseUrl.equals(urlDE)) {

                $("#success-page-custom").shouldHave(text("Vielen Dank für Ihre Bestellung, " + firstName));

            } else if (Configuration.baseUrl.equals(urlSE)) {

            }
        } else if (paymentMethod.equals(viaBillPay)) {
            if (Configuration.baseUrl.equals(urlDK)) {
                $("#success-page-custom").shouldHave(text("Mange tak for din ordre, "));
            } else if (Configuration.baseUrl.equals(urlNO)) {

            } else if (Configuration.baseUrl.equals(urlIS)) {

            } else if (Configuration.baseUrl.equals(urlDE)) {

            } else if (Configuration.baseUrl.equals(urlSE)) {

            }

        } else if (paymentMethod.equals(sparkXpressPay)) {
            if (Configuration.baseUrl.equals(urlDK)) {
                $$("h4").get(0).shouldHave(text("Er du registreret i RKI, Debitor Registret eller har du lønindhold?"));
            } else if (Configuration.baseUrl.equals(urlNO)) {

            } else if (Configuration.baseUrl.equals(urlIS)) {
                $(byText("sparxpres.dk")).shouldBe(exist);
            } else if (Configuration.baseUrl.equals(urlDE)) {

            } else if (Configuration.baseUrl.equals(urlSE)) {

            }

        } else if (paymentMethod.equals(klarnaPay)) {
            if (Configuration.baseUrl.equals(urlDK)) {

            } else if (Configuration.baseUrl.equals(urlNO)) {
                $(".checkout-success__agreement").shouldHave(text("Bestillingen din er nå registrert, og du vil snart motta en e-post med bestillingsbekreftelsen på: " + email));

            } else if (Configuration.baseUrl.equals(urlIS)) {

            } else if (Configuration.baseUrl.equals(urlDE)) {

            } else if (Configuration.baseUrl.equals(urlSE)) {

            }
        } else if (paymentMethod.equals(valitorPay)) {
            if (Configuration.baseUrl.equals(urlDK)) {

            } else if (Configuration.baseUrl.equals(urlNO)) {

            } else if (Configuration.baseUrl.equals(urlIS)) {
                $(".checkout-success__agreement").shouldHave(text(email));
            } else if (Configuration.baseUrl.equals(urlDE)) {

            } else if (Configuration.baseUrl.equals(urlSE)) {

            }

        }
    }
}
