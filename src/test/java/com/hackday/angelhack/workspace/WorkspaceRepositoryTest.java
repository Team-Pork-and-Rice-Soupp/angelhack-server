package com.hackday.angelhack.workspace;

import com.hackday.angelhack.domain.Workspace;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
public class WorkspaceRepositoryTest {

    @Autowired
    WorkspaceRepository workspaceRepository;

    @Test
    public void findAllByUserId() {

        Workspace workspace = Workspace.builder()
                .userId(1L)
                .title("테스트1")
                .description("테스트 설명 1")
                .build();

        workspaceRepository.save(workspace);

        List<Workspace> workspaces = workspaceRepository.findAllByUserId(1L);
        assertEquals(workspaces.get(0).getTitle(), "테스트1");
    }
}