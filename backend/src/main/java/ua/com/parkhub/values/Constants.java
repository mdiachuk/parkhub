package ua.com.parkhub.values;

import io.jsonwebtoken.SignatureAlgorithm;

public class Constants {

    public static final String TOKEN_HEADER = "X-AUTH";
    public static final SignatureAlgorithm JWT_SIGNATURE_ALGORITHM = SignatureAlgorithm.HS512;
    public static final String USERNOTFOUND = "User was not found";

}
