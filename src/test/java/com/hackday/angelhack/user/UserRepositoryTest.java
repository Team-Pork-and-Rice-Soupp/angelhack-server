package com.hackday.angelhack.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;

@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void userSaveTest() {
        UserAuth userAuth = new UserAuth();
        userAuth.setEmail("hahava@naver.com");
        userAuth.setPw("{noop}12345");
        userAuth.setRole(ROLE.PROJECT_MANAGER);
        userRepository.save(userAuth);
    }

    @Test
    public void getUserTest() {
        UserAuth mockUserAuth = userRepository.findByEmail("hahava@naer.com");
        assertEquals(mockUserAuth.getRole(), ROLE.PROJECT_MANAGER);
    }

}
