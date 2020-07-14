package com.hackday.angelhack.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.hackday.angelhack.user.UserAuth;
import com.hackday.angelhack.user.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

public class JwtAuthorizationFilter extends BasicAuthenticationFilter {

    public JwtAuthorizationFilter(AuthenticationManager authenticationManager, UserRepository userRepository) {
        super(authenticationManager);
        this.userRepository = userRepository;
    }

    private UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String token = request.getHeader(SecurityConst.HEADER_STRING);

        if (token == null) {
            chain.doFilter(request, response);
            return;
        }

        String email = JWT
                .require(Algorithm.HMAC512(SecurityConst.SECRET_KEY))
                .build()
                .verify(token.replace(SecurityConst.TOKEN_PREFIX, ""))
                .getSubject();

        if (email != null) {
            UserAuth userAuth = userRepository.findByEmail(email);
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userAuth.getEmail(), userAuth.getPw(), Set.of(new SimpleGrantedAuthority(userAuth.getRole().name())));
            SecurityContextHolder.getContext().setAuthentication(auth);
        }

        chain.doFilter(request, response);
    }
}
