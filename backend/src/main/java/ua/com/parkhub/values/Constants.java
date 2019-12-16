package ua.com.parkhub.values;

import io.jsonwebtoken.SignatureAlgorithm;

public class Constants {

    public static final String TOKEN_HEADER = "X-AUTH";
    public static final SignatureAlgorithm jwtSignatureAlgorithm = SignatureAlgorithm.HS512;

}
