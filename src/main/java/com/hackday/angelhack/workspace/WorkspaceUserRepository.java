package com.hackday.angelhack.workspace;

import com.hackday.angelhack.domain.WorkspaceUser;
import org.springframework.data.repository.CrudRepository;

public interface WorkspaceUserRepository extends CrudRepository<WorkspaceUser, Long> {
}
