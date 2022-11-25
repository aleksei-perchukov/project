package utils;

import static utils.RandomUtils.generateRegexSymbolsNumbers;

public class StaticData {
    public static String mainUrl;
    public static String urlDK = "https://skanva.dk";
    public static String urlNO = "https://no.skanva.dk";
    public static String urlIS = "https://is.skanva.dk";
    public static String urlDE = "https://de.skanva.dk";
    public static String urlSE = "https://se.skanva.dk";
    public static String cookieFormKeyStatic = generateRegexSymbolsNumbers(16);
    public static  String phpSessId = generateRegexSymbolsNumbers(26);
    public static  String customerId;
    static {

    }
}
