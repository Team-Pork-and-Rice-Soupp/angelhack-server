package com.hackday.angelhack.workspace;

import com.hackday.angelhack.domain.WorkspaceUser;
import com.hackday.angelhack.user.UserAuth;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface WorkspaceUserRepository extends CrudRepository<WorkspaceUser, Long> {

    List<WorkspaceUser> findAllByUser(UserAuth user);
}
