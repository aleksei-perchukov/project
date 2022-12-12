package models;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties
@Data
public class CreateUserPojoModel {
    private String form_key,
            success_url,
            error_url,
            firstname,
            lastname,
            email,
            password,
            password_confirmation;
}