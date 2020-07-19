package com.hackday.angelhack.assessment;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AssessmentRepository extends CrudRepository<Assessment, Long> {

    List<Assessment> findAllByWorkspaceId(Long workspaceId);
}
