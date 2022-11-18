package models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class LoginUserPojoModel {
    @JsonProperty("form_key")
    String formKey;
    @JsonProperty("login[username]")
    String loginUserName;
    @JsonProperty("login[password]")
    String loginPassword;
}
