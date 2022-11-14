package models;

public class AddToCartModel {
        private String product,
                width,
                height,
                qty,
                options,
                email,
                password,
                password_confirmation;

        //form_key
        public String getProduct(){;
            return product;
        }
        public void setProduct(final String product){
            this.product=product;
        }

        //success_url
        public String getWidth(){;
            return width;
        }
        public void setWidth(final String width){
            this.width=width;
        }

        //error_url
        public String getHeight(){;
            return height;
        }
        public void setHeight(final String error_url){
            this.height=height;
        }

        //firstname
        public String getQty(){;
            return qty;
        }
        public void setQty(final String qty){
            this.qty=qty;
        }

        //lastname
//        public String getOptions60382(){;
//            return options60382;
//        }
//        public void setOptions60382(final String options60382){
//            this.options60382=options60382;
//        }

//        //email
//        public String getEmail(){;
//            return email;
//        }
//        public void setEmail(final String email){
//            this.email=email;
//        }
//
//        //password
//        public String getPassword(){;
//            return password;
//        }
//        public void setPassword(final String password){
//            this.password=password;
//        }
//
//        //password_confirmation
//        public String getPassword_confirmation(){;
//            return password_confirmation;
//        }
//        public void setPassword_confirmation(final String password_confirmation){
//            this.password_confirmation=password_confirmation;
//        }
    }