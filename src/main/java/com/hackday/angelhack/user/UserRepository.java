package com.hackday.angelhack.user;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<UserProfile, Long> {
    UserProfile findByEmail(String email);

    List<UserProfile> findAllByEmailStartingWith(String keyword);
}
