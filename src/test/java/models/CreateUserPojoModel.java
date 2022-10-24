package models;

public class CreateUserPojoModel {
    private String form_key,
    success_url,
    error_url,
    firstname,
    lastname,
    email,
    password,
    password_confirmation;

    //form_key
    public String getForm_key(){;
        return form_key;
    }
    public void setForm_key(final String form_key){
        this.form_key=form_key;
    }

    //success_url
    public String getSuccess_url(){;
        return success_url;
    }
    public void setSuccess_url(final String success_url){
        this.success_url=success_url;
    }

    //error_url
    public String getError_url(){;
        return error_url;
    }
    public void setError_url(final String error_url){
        this.error_url=error_url;
    }

    //firstname
    public String getFirstname(){;
        return firstname;
    }
    public void setFirstname(final String firstname){
        this.firstname=firstname;
    }

    //lastname
    public String getLastname(){;
        return lastname;
    }
    public void setLastname(final String lastname){
        this.lastname=lastname;
    }

    //email
    public String getEmail(){;
        return email;
    }
    public void setEmail(final String email){
        this.email=email;
    }

    //password
    public String getPassword(){;
        return password;
    }
    public void setPassword(final String password){
        this.password=password;
    }

    //password_confirmation
    public String getPassword_confirmation(){;
        return password_confirmation;
    }
    public void setPassword_confirmation(final String password_confirmation){
        this.password_confirmation=password_confirmation;
    }
}
