package com.hackday.angelhack.workspace;

import com.hackday.angelhack.domain.Workspace;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class WorkspaceService {

    private final WorkspaceRepository workspaceRepository;

    public List<Workspace> findAllByUserId(Long userId){
        return workspaceRepository.findAllByUserId(userId);
    }
}
