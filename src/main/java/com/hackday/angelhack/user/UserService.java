package com.hackday.angelhack.user;

import com.hackday.angelhack.user.dto.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public List<UserResponseDto> findUserByKeyword(String keyword){
        return userRepository.findAllByEmailStartingWith(keyword).stream()
                .map(UserResponseDto::new)
                .collect(Collectors.toList());
    }
}
