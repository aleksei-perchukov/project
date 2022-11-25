package utils;

import com.github.javafaker.Faker;
import com.mifmif.common.regex.Generex;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class RandomUtils {
    public static String getRandomString(int length) {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder result = new StringBuilder();
        Random rnd = new Random();
        while (result.length() < length) {
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            result.append(SALTCHARS.charAt(index));
        }

        return result.toString();
    }
    public static String getCity(){
        Faker faker = new Faker();
        String city = faker.address().city();
        return city;
    }

    public static String getRandomAddress(){
        Faker faker = new Faker();
        String fullAddress = faker.address().streetAddress();
        return fullAddress;
    }
    public static String getRandomCity(){
        Faker faker = new Faker();
        String fullAddress = faker.address().city();
        return fullAddress;
    }

    public static String getRandomString1(int length) {
        return RandomStringUtils.randomAlphabetic(length);
    }

    public static String getRandomEmail() {
        return getRandomString(10) + "@email.com";
    }

    public static Long getRandomLong(Long min, Long max) {
        return ThreadLocalRandom.current().nextLong(min, max);
    }

    public static String getRandomPhone() {
        return "+382" + getRandomLong(1111111111L, 9999999999L).toString();
    }
    public static String generateRegexSymbolsNumbers(int symbolsNumber){
        Generex generex = new Generex("[A-Za-z0-9]{" + symbolsNumber + "}");
        String string = generex.random();
        return string;
    }


    public static Date getCookieExpirationDate() {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 6);

        java.util.Date cookieExpirationDate = calendar.getTime();
        return cookieExpirationDate;
    }
}