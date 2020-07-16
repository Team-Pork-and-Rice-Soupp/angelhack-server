package com.hackday.angelhack.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public List<UserProfile> findUserByEmail(String keyword){
        return userRepository.findAllByEmailStartingWith(keyword);
    }
}
