package com.hackday.angelhack.workspace;

import com.hackday.angelhack.domain.Workspace;
import com.hackday.angelhack.domain.WorkspaceUser;
import com.hackday.angelhack.user.UserAuth;
import com.hackday.angelhack.user.UserRepository;
import com.hackday.angelhack.workspace.dto.WorkspaceSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class WorkspaceService {

    private final WorkspaceRepository workspaceRepository;
    private final UserRepository userRepository;

    public List<Workspace> findAllByUserId(Long userId){
        return null;
    }

    public Long save(WorkspaceSaveRequestDto requestDto){
        Workspace workspace = requestDto.toEntity();
        return workspaceRepository.save(workspace).getId();
    }
}
