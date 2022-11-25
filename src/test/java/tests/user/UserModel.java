package tests.user;

import lombok.Data;

@Data
public class UserModel {
    @Data
    class customer {
        String firstname;
        String lastname;

        @Data
        class addresses {
            String defaultShipping;
            String defaultBilling;
            String firstname;
            String lastname;

            @Data
            class region {
                String regionCode;
                String region;
                String regionId;
            }

            String postcode;
            @Data
            class street {
                String[] strings;
            }

            String city;
            String telephone;
            String countryId;
        }
    }
    String password;
}
