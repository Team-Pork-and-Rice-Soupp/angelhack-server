package com.hackday.angelhack.user;

import com.hackday.angelhack.common.constant.SecurityRole;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.Assert.assertEquals;

@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void userSaveTest() {
        UserProfile userProfile = new UserProfile();
        userProfile.setEmail("hahava@naver.com");
        userProfile.setPw("{noop}12345");
        userProfile.setSecurityRole(SecurityRole.PROJECT_MANAGER);
        userRepository.save(userProfile);
    }

    @Test
    public void getUserTest() {
        UserProfile mockUserProfile = userRepository.findByEmail("hahava@naer.com");
        assertEquals(mockUserProfile.getSecurityRole(), SecurityRole.PROJECT_MANAGER);
    }

    @Test
    public void findAllByEmailStartingWithTest(){
        List<UserProfile> userProfiles = userRepository.findAllByEmailStartingWith("haha");
        UserProfile userProfile = userProfiles.get(0);
        assertEquals(userProfile.getEmail(), "hahava@naver.com");
    }

}
