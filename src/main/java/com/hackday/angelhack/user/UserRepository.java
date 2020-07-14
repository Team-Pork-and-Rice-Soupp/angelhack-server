package com.hackday.angelhack.user;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<UserAuth, Long> {
    UserAuth findByEmail(String email);

    List<UserAuth> findAllByEmailStartingWith(String keyword);
}
