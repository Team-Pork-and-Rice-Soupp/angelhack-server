package com.hackday.angelhack.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.hackday.angelhack.common.constant.SecurityConst;

import java.sql.Date;
import java.time.LocalDate;

public final class JWTUtil {
    private JWTUtil() {
    }

    public static String decodeJWT(String token) {
        return JWT
                .require(Algorithm.HMAC512(SecurityConst.SECRET_KEY))
                .build()
                .verify(token.replace(SecurityConst.TOKEN_PREFIX, ""))
                .getSubject();
    }

    public static String encodeJWT(String value) {
        return JWT
                .create()
                .withSubject(value)
                .withExpiresAt(Date.valueOf(LocalDate.now().plusDays(2)))
                .sign(Algorithm.HMAC512(SecurityConst.SECRET_KEY));
    }

}
