//package tests.checkout;
//
//import com.codeborne.selenide.ClickOptions;
//import com.codeborne.selenide.Selenide;
//import io.restassured.response.ResponseBodyExtractionOptions;
//import org.junit.jupiter.api.Disabled;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.RepeatedTest;
//import org.junit.jupiter.api.Test;
//import tests.user.Components;
//
//import static com.codeborne.selenide.Selenide.$;
//import static com.codeborne.selenide.Selenide.refresh;
//import static io.qameta.allure.Allure.step;
//import static io.restassured.RestAssured.given;
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//import static specs.Specs.requestSpecification1;
//import static specs.Specs.responseSpecification1;
//import static tests.checkout.CheckoutApiMethods.phpSessIdGetter;
//import static tests.checkout.CheckoutApiMethods.openBrowserWithCookies;
//import static tests.user.TestData.*;
//import static utils.RandomUtils.getRandomLong;
//
//@Disabled
//public class BufferTests {
//
//    @Test
//    @DisplayName("-=DK=- Initial selenide payment test")
//    @RepeatedTest(10)
//    void quickPayTest() {
//        Components.openPage("/tr/topstyret-vindue-2-fags");
//        //product page
//        $(".coi-banner__accept").click();
//        Selenide.sleep(10000);
//        $("#product-addtocart-button").click(ClickOptions.usingJavaScript());
//        $(".action.primary").click(ClickOptions.usingJavaScript());
//        //cart page
//        $(".action.primary.checkout").click(ClickOptions.usingJavaScript());
//        //shipping form
//        $("[name=firstname]").setValue(firstName);
//        $("[name=lastname]").setValue(lastName);
//        $("[name='street[0]']").setValue("Test str.");
//        $("[name='custom_attributes[house_number]']").setValue("42");
//        $("[name=postcode]").setValue("8000");
//        $(".telephone-input__telephone.input-text").setValue(getRandomLong(00000000L, 99999999L).toString());
//        $("#shipping-address-step").submit();
//        refresh();
//        $("#customer-email").setValue(email);
//        $(".button.action.continue.primary").click();
//        //shipping method
//        $(".shipping-method-item").click();
//        $("[for=confirm-shipping-method]").click();
//        $("#co-shipping-method-form").submit();
//        //      step("Clicking 'Proceed to payment' button", () -> {$(".button.action.continue.primary").doubleClick();});
//        //payment method
//        $("#co-payment-form").submit();
//        $("[for=quickpay]").click();
//        $("label.label[for=agreement_quickpay_1]").click(ClickOptions.usingJavaScript().offsetX(-100));
//        $("[for=agreement_quickpay_2]").click();
//        $(".action.primary.checkout").click();
//        //QuickPay form
//        $("#cardnumber").setValue("1000000000000008");
//        $("#expiration-month").setValue("0625");
//        $("#expiration-year").setValue("26");
//        $("#cvd").setValue("123");
//        $(".btn.btn-info").click();
//        //Thank you page assert
//    }
//
//    @Test
//    void checkUpdateCartNumber() {
//        
//        ResponseBodyExtractionOptions cart_id_json = apiAddToCart(phpSessId);
//        assertThat(cart_id_json.jsonPath().get("messages.type").equals("success"));
//        openBrowserWithCookies(phpSessId, formKeyCookieStatic, "");
//
//        step("Checking cart number increased", () -> {
//            ResponseBodyExtractionOptions cart_update_json = given().spec(requestSpecification1)
//                    .cookie("PHPSESSID", phpSessId)
//                    .cookie("form_key", "x2OdeHWwSION73Xc")
//                    .contentType("application/x-www-form-urlencoded; charset=UTF-8")
//                    .header("x-requested-with", "XMLHttpRequest")
//                    .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/107.0.0.0 Safari/537.36")
//                    .when()
//                    .get("customer/section/load/?sections=cart&force_new_section_timestamp=true")
//                    .then()
//                    .spec(responseSpecification1)
//                    .statusCode(200)
//                    .extract().body();
//            assertThat(cart_update_json.jsonPath().get("cart.summary_count").equals("1"));
//            System.out.println(cart_update_json.jsonPath().get("cart.summary_count").toString());
//        });
//
//        step("Adding product to cart & getting PHPSESSID cookie by API", () -> {
//            cart_id_json = given()
//                    .spec(requestSpecification1)
//                    .cookie("PHPSESSID", phpSessId)
//                    .cookie("form_key", "x2OdeHWwSION73Xc")
//                    .contentType("application/x-www-form-urlencoded; charset=UTF-8")
//                    .header("x-requested-with", "XMLHttpRequest")
//                    .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/107.0.0.0 Safari/537.36")
//                    .body("product=4404&selected_configurable_option=&related_product=&item=1277812&item=4404&form_key=x2OdeHWwSION73Xc&estimated_delivery_time=%5B8%2C10%5D&width=190&height=120&qty=1&options%5B60380%5D=190&options%5B60381%5D=120&options%5B60382%5D=%7B%22height%22%3A120%2C%22width%22%3A190%2C%22width1%22%3A95%2C%22fieldwidth2%22%3A95%7D&options%5B48328%5D=2131878&special_color_ral%5B5234147%5D=none&options%5B48326%5D=305016&special_color_ral%5B5222597%5D=none&options%5B48327%5D=305021&options%5B48330%5D=305036&options%5B860699%5D=5193080&options%5B57298%5D=361683&options%5B53127%5D=337474&options%5B859559%5D=5190887&options%5B245170%5D=1100313&options%5B352864%5D=2232309&")
//                    .when()
//                    .post("/checkout/cart/add/product/4404/")
//                    .then()
//                    .spec(responseSpecification1)
//                    .log().status()
//                    .log().body()
//                    .statusCode(200)
//                    .extract().body();
//            assertThat(cart_id_json.jsonPath().get("messages.type").equals("success"));
//            String cart_id = cart_id_json.jsonPath().get("raptor.cart_id").toString();
//            System.out.println("Add to cart action is " + cart_id_json.jsonPath().get("messages.type").toString());
//            System.out.println("Cart ID is " + cart_id);
//        });
//
//        step("Checking cart number increased", () -> {
//            ResponseBodyExtractionOptions cart_update_json = given().spec(requestSpecification1)
//                    .cookie("PHPSESSID", phpSessId)
//                    .cookie("form_key", "x2OdeHWwSION73Xc")
//                    .contentType("application/x-www-form-urlencoded; charset=UTF-8")
//                    .header("x-requested-with", "XMLHttpRequest")
//                    .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/107.0.0.0 Safari/537.36")
//                    .when()
//                    .get("customer/section/load/?sections=cart&force_new_section_timestamp=true")
//                    .then()
//                    .spec(responseSpecification1)
//                    .statusCode(200)
//                    .extract().body();
//            assertThat(cart_update_json.jsonPath().get("cart.summary_count").equals("2"));
//            System.out.println(cart_update_json.jsonPath().get("cart.summary_count").toString());
//        });
//
//        step("Adding product to cart & getting PHPSESSID cookie by API", () -> {
//            ResponseBodyExtractionOptions cart_id_json = given()
//                    .spec(requestSpecification1)
//                    .cookie("PHPSESSID", phpSessId)
//                    .cookie("form_key", "x2OdeHWwSION73Xc")
//                    .contentType("application/x-www-form-urlencoded; charset=UTF-8")
//                    .header("x-requested-with", "XMLHttpRequest")
//                    .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/107.0.0.0 Safari/537.36")
//                    .body("product=4404&selected_configurable_option=&related_product=&item=1277812&item=4404&form_key=x2OdeHWwSION73Xc&estimated_delivery_time=%5B8%2C10%5D&width=190&height=120&qty=1&options%5B60380%5D=190&options%5B60381%5D=120&options%5B60382%5D=%7B%22height%22%3A120%2C%22width%22%3A190%2C%22width1%22%3A95%2C%22fieldwidth2%22%3A95%7D&options%5B48328%5D=2131878&special_color_ral%5B5234147%5D=none&options%5B48326%5D=305016&special_color_ral%5B5222597%5D=none&options%5B48327%5D=305021&options%5B48330%5D=305036&options%5B860699%5D=5193080&options%5B57298%5D=361683&options%5B53127%5D=337474&options%5B859559%5D=5190887&options%5B245170%5D=1100313&options%5B352864%5D=2232309&")
//                    .when()
//                    .post("/checkout/cart/add/product/4404/")
//                    .then()
//                    .spec(responseSpecification1)
//                    .log().status()
//                    .log().body()
//                    .statusCode(200)
//                    .extract().body();
//            assertThat(cart_id_json.jsonPath().get("messages.type").equals("success"));
//            String cart_id = cart_id_json.jsonPath().get("raptor.cart_id").toString();
//            System.out.println("Add to cart action is " + cart_id_json.jsonPath().get("messages.type").toString());
//            System.out.println("Cart ID is " + cart_id);
//        });
//
//        step("Checking cart number increased", () -> {
//            ResponseBodyExtractionOptions cart_update_json = given().spec(requestSpecification1)
//                    .cookie("PHPSESSID", phpSessId)
//                    .cookie("form_key", "x2OdeHWwSION73Xc")
//                    .contentType("application/x-www-form-urlencoded; charset=UTF-8")
//                    .header("x-requested-with", "XMLHttpRequest")
//                    .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/107.0.0.0 Safari/537.36")
//                    .when()
//                    .get("customer/section/load/?sections=cart&force_new_section_timestamp=true")
//                    .then()
//                    .spec(responseSpecification1)
//                    .statusCode(200)
//                    .log().body()
//                    .extract().body();
//            assertThat(cart_update_json.jsonPath().getString("cart.summary_count").toString().matches("1"));
//            System.out.println(cart_update_json.jsonPath().get("cart.summary_count").toString());
//        });
//    }
//
//}
//}
