package com.hackday.angelhack.user;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserAuth, Long> {
    UserAuth findByEmail(String email);
}
