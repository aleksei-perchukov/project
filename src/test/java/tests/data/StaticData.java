package tests.data;

public class StaticData {

    static RandomUtils randomUtils = new RandomUtils();
    public static String basicAuthLogin = System.getProperty("basicAuthLogin");
    //System.getProperty("basicAuthLogin");
    public static String basicAuthPassword = System.getProperty("basicAuthPassword");
    //System.getProperty("basicAuthPassword");
    public static String adminLogin = System.getProperty("adminLogin");
    public static String adminPassword = System.getProperty("adminPassword");
    public static String authHeaderValue = System.getProperty("authHeaderValue");
    public static String mainUrl;
    public static String urlDK = "https://skanva.dk";
    public static String urlNO = "https://no.skanva.dk";
    public static String urlIS = "https://is.skanva.dk";
    public static String urlDE = "https://de.skanva.dk";
    public static String urlSE = "https://se.skanva.dk";
    public static String formKey = randomUtils.generateRegexSymbolsNumbers(16);
    public static String phpSessId = randomUtils.generateRegexSymbolsNumbers(26);
    public static String customerId;
    //PAGES
    public static String homePage = "/";
    public static String createUserPage = "/customer/account/create";
    public static String loginPage = "/customer/account/login";
//    public static String contactPage = "/info/kontakt";
//    public static String vinduerCatalogPage = "/vinduer";
//    public static String doreCatalogPage = "/doere";
//    public static String terrassedoreCatalogPage = "/terrassedoere";
//    public static String skydedoreCatalogPage = "/skydedoere";
    public static String adminPage = "/skanvacms/admin";
    public static String addToCartPage = "/tr/topstyret-vindue-2-fags";
    public static String paypalExpressPay = "PayPal Express";
    public static String quickPay = "Quick Pay";
    public static String bankPay = "Bank Transfer";
    public static String viaBillPay = "ViaBill";
    public static String sparkXpressPay = "SparkXpress Pay";
    public static String klarnaPay = "Klarna";
    public static String netgiroPay = "Netgiro";
    public static String valitorPay = "Valitor";
}
