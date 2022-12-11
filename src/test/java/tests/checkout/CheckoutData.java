package tests.checkout;

import com.codeborne.selenide.Configuration;

import static utils.StaticData.*;

public class CheckoutData {

    public String paypalExpressPay = "PayPal Express";
    public String quickPay = "Quick Pay";
    public String bankPay = "Bank Transfer";
    public String viaBillPay = "ViaBill";
    public String sparkXpressPay = "SparkXpress Pay";
    public String klarnaPay = "Klarna";
    public String netgiroPay = "Netgiro";
    public String valitorPay = "Valitor";

    //ADD PRODUCT BODY
    public String getAddToCartBody() {
        String addToCartBody = null;
        if (Configuration.baseUrl.equals(urlDK)) {
            addToCartBody = "product=4404&selected_configurable_option=&related_product=&item=4404&form_key=" + cookieFormKeyStatic + "&estimated_delivery_time=%5B8%2C10%5D&width=190&height=120&qty=1&options%5B60380%5D=190&options%5B60381%5D=120&options%5B60382%5D=%7B%22height%22%3A120%2C%22width%22%3A190%2C%22width1%22%3A95%2C%22fieldwidth2%22%3A95%7D&options%5B48328%5D=2131878&special_color_ral%5B5234147%5D=none&options%5B48326%5D=305016&special_color_ral%5B5222597%5D=none&options%5B48327%5D=305021&options%5B48330%5D=305036&options%5B860699%5D=5193080&options%5B57298%5D=361683&options%5B53127%5D=337474&options%5B859559%5D=5190887&options%5B245170%5D=1100313&options%5B352864%5D=2232309";
        } else if (Configuration.baseUrl.equals(urlNO)) {
            addToCartBody = "product=5550&selected_configurable_option=&related_product=&item=5550&form_key=" + cookieFormKeyStatic + "&estimated_delivery_time=%5B9%2C11%5D&width=190&height=120&qty=1&options%5B857520%5D=190&options%5B857521%5D=120&options%5B857522%5D=%7B%22height%22%3A120%2C%22width%22%3A190%2C%22width1%22%3A95%2C%22fieldwidth2%22%3A95%7D&options%5B857515%5D=5178598&special_color_ral%5B5242631%5D=none&options%5B857513%5D=5178566&special_color_ral%5B5229695%5D=none&options%5B857514%5D=5178579&options%5B857517%5D=5178602&options%5B860886%5D=5193454&options%5B859782%5D=5191313&options%5B857518%5D=5178610&options%5B859849%5D=5191461&options%5B857523%5D=5178618&options%5B857524%5D=5178619";
        } else if (Configuration.baseUrl.equals(urlIS)) {
            addToCartBody = "product=4404&selected_configurable_option=&related_product=&item=4404&form_key=" + cookieFormKeyStatic + "&estimated_delivery_time=%5B12%2C13%5D&width=190&height=120&qty=1&options%5B60380%5D=190&options%5B60381%5D=120&options%5B60382%5D=%7B%22height%22%3A120%2C%22width%22%3A190%2C%22width1%22%3A95%2C%22fieldwidth2%22%3A95%7D&options%5B48328%5D=2131878&special_color_ral%5B5234147%5D=none&options%5B48326%5D=305016&special_color_ral%5B5222597%5D=none&options%5B48327%5D=305021&options%5B48330%5D=305037&options%5B860699%5D=5193080&options%5B57298%5D=361683&options%5B53127%5D=337474&options%5B859559%5D=5190887&options%5B856653%5D=5176592&options%5B245170%5D=1100313&options%5B352864%5D=2232309";
        } else if (Configuration.baseUrl.equals(urlDE)) {
            addToCartBody = "product=4404&selected_configurable_option=&related_product=&item=4404&form_key=" + cookieFormKeyStatic + "&estimated_delivery_time=%5B9%2C10%5D&width=190&height=120&qty=1&options%5B60380%5D=190&options%5B60381%5D=120&options%5B60382%5D=%7B%22height%22%3A120%2C%22width%22%3A190%2C%22width1%22%3A95%2C%22fieldwidth2%22%3A95%7D&options%5B873549%5D=5323901&special_color_ral%5B5323913%5D=none&options%5B48326%5D=305016&special_color_ral%5B5222597%5D=none&options%5B48327%5D=305021&options%5B48330%5D=305036&options%5B860699%5D=5193080&options%5B57298%5D=361683&options%5B53127%5D=337474&options%5B859559%5D=5190887&options%5B856653%5D=5176592&options%5B245170%5D=1100313&options%5B352864%5D=2232309";
        } else if (Configuration.baseUrl.equals(urlSE)) {
            addToCartBody = "";
        }
        return addToCartBody;
    }

    public String getZipCode(){
        String zipCode = null;
        if(Configuration.baseUrl.equals(urlDK)) {
            zipCode = "8000";
        } else if(Configuration.baseUrl.equals(urlNO)) {
            zipCode = "5555";
        } else if(Configuration.baseUrl.equals(urlIS)) {
            zipCode = "155";
        } else if(Configuration.baseUrl.equals(urlDE)) {
            zipCode = "12345";
        } else if(Configuration.baseUrl.equals(urlSE)) {
            zipCode = "";
        }
        return zipCode;
    }
}
