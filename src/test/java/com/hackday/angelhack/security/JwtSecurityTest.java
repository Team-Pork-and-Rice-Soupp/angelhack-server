package com.hackday.angelhack.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.hackday.angelhack.common.constant.SecurityConst;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.time.LocalDate;

@Slf4j
public class JwtSecurityTest {

    private static final String userEmail = "root";

    @Test
    public void createJwtTest() {
        final String jwt = JWT
                .create()
                .withSubject(userEmail)
                .withExpiresAt(Date.valueOf(LocalDate.now().plusDays(10000)))
                .sign(Algorithm.HMAC512(SecurityConst.SECRET_KEY));
        log.info(jwt);
    }

}
