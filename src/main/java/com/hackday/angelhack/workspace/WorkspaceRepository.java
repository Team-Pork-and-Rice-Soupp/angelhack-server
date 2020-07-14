package com.hackday.angelhack.workspace;

import com.hackday.angelhack.domain.Workspace;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface WorkspaceRepository extends CrudRepository<Workspace, Long> {

    List<Workspace> findAllByUserId(Long userId);
}
