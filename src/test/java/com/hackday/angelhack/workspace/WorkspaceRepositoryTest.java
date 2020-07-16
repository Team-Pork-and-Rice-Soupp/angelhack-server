package com.hackday.angelhack.workspace;

import com.hackday.angelhack.domain.ProjectRole;
import com.hackday.angelhack.domain.Workspace;
import com.hackday.angelhack.domain.WorkspaceUser;
import com.hackday.angelhack.user.UserProfile;
import com.hackday.angelhack.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class WorkspaceRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WorkspaceRepository workspaceRepository;

    @Autowired
    private WorkspaceUserRepository workspaceUserRepository;

    @Test
    public void addWorkSpaceTest() {
        UserProfile userOne = userRepository.findByEmail("hahava1@naver.com");
        UserProfile userTwo = userRepository.findByEmail("hahava@naver.com");

        Workspace workspace = new Workspace();
        workspace.setTitle("hello workspace title");
        workspace.setDescription("hello workspace description");
        workspaceRepository.save(workspace);

        WorkspaceUser workspaceUser = new WorkspaceUser();
        workspaceUser.setUser(userOne);
        workspaceUser.setDescription("hello workspace user description");
        workspaceUser.setRole(ProjectRole.CREW);
        workspaceUser.setWorkspace(workspace);
        workspaceUserRepository.save(workspaceUser);

        WorkspaceUser workspaceUser2 = new WorkspaceUser();
        workspaceUser2.setUser(userTwo);
        workspaceUser2.setDescription("hello workspace user2 description");
        workspaceUser2.setRole(ProjectRole.MANAGER);
        workspaceUser2.setWorkspace(workspace);
        workspaceUserRepository.save(workspaceUser2);
    }

    @Test
    public void getUserByWorkspaceTest() {
        Workspace workspace = workspaceRepository.findById(1L).get();
        System.out.println(workspace.getWorkspaceUsers().get(0).getUser().getEmail());
        System.out.println(workspace.getWorkspaceUsers().get(1).getUser().getEmail());
    }

}