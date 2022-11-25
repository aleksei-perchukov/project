package tests.user;

import static com.codeborne.selenide.Selenide.open;

public class Components {
    public static String basicAuthLogin = "admin";
    public static String basicAuthPassword = "BilarIsgreaT2020!%23";
    public static String adminLogin = "n.brevnov%40belvg.com";
    public static String adminPassword = "n.brevnovBelVG%24ecuRiTy2021";
    public static String authHeaderValue = "Basic YWRtaW46QmlsYXJJc2dyZWFUMjAyMCEj";


    public static void openPage(String url) {
        open(url, "", basicAuthLogin, basicAuthPassword);
    }
}
