package com.hackday.angelhack.security;

import com.hackday.angelhack.common.constant.SecurityConst;
import com.hackday.angelhack.common.constant.SecurityRole;
import com.hackday.angelhack.user.UserProfile;
import com.hackday.angelhack.user.UserRepository;
import com.hackday.angelhack.util.JWTUtil;
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

        String email = JWTUtil.decodeJWT(token);

        // FIXME change only for dev profile
        if (email.equals("root")) {
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(null, null, Set.of(new SimpleGrantedAuthority(SecurityRole.ADMIN.name())));
            SecurityContextHolder.getContext().setAuthentication(auth);
            chain.doFilter(request, response);
            return;
        }

        if (email != null) {
            UserProfile userProfile = userRepository.findByEmail(email);
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userProfile.getEmail(), userProfile.getPw(), Set.of(new SimpleGrantedAuthority(userProfile.getSecurityRole().name())));
            SecurityContextHolder.getContext().setAuthentication(auth);
        }

        chain.doFilter(request, response);
    }
}
