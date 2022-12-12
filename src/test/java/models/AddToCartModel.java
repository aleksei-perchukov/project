package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties
public class AddToCartModel {
        private String product,
                width,
                height,
                qty,
                options,
                email,
                password,
                password_confirmation;

    }