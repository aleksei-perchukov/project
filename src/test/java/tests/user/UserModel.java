package tests.user;

import lombok.Data;

@Data
public class UserModel {
    @Data
    public static class customer {
        String firstname;
        String lastname;

        @Data
        public static class addresses {
            Boolean defaultShipping;
            Boolean defaultBilling;
            String firstname;
            String lastname;

            @Data
            public static class region {
                String regionCode;
                String region;
                String regionId;
            }

            String postcode;
            @Data
            public static class street {
                String[] strings;
            }

            String city;
            String telephone;
            String countryId;
        }
    }
    String password;
}
