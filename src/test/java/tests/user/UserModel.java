package tests.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserModel {
    public Customer customer;
    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Customer {
        public String firstname;
        public String lastname;
        public String email;
        public int website_id;
        public Addresses addresses;

        @Data
        @JsonIgnoreProperties(ignoreUnknown = true)
        public class Addresses {
            public Boolean defaultShipping;
            public Boolean defaultBilling;
            public String firstname;
            public String lastname;
            public Region region;

            @Data
            @JsonIgnoreProperties(ignoreUnknown = true)
            public class Region {
                public String regionCode;
                public String region;
                public String regionId;
            }

            private String postcode;
            private Street street;

            @Data
            @JsonIgnoreProperties(ignoreUnknown = true)
            public class Street {
                public String[] strings;
            }

            public String city;
            public String telephone;
            public String countryId;
        }
    }
    public String password;
}
