package com.hackday.angelhack.security;

import com.hackday.angelhack.user.UserProfile;
import com.hackday.angelhack.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class SecurityDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserProfile userProfile = userRepository.findByEmail(email);
        return new User(userProfile.getEmail(), userProfile.getPw(), Set.of(new SimpleGrantedAuthority(userProfile.getRole().name())));
    }
}
