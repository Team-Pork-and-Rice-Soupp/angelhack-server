package com.hackday.angelhack.workspace;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.hackday.angelhack.domain.Workspace;
import com.hackday.angelhack.domain.WorkspaceUser;
import com.hackday.angelhack.security.SecurityConst;
import com.hackday.angelhack.user.UserAuth;
import com.hackday.angelhack.user.UserRepository;
import com.hackday.angelhack.workspace.dto.WorkspaceSaveRequestDto;
import com.hackday.angelhack.workspace.dto.WorkspaceUserSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class WorkspaceService {

    private final WorkspaceRepository workspaceRepository;
    private final UserRepository userRepository;
    private final WorkspaceUserRepository workspaceUserRepository;

    public List<Workspace> findAllByUserId(HttpServletRequest request) {
        String email = decodeJWT(request);

        Iterable<Workspace> workspaces = workspaceRepository.findAll();
        UserAuth user = userRepository.findByEmail(email);
        List<Workspace> result = new ArrayList<>();
        for (Workspace workspace : workspaces) {
            for (WorkspaceUser workspaceUser : workspace.getWorkspaceUsers()) {
                if (workspaceUser.getId().equals(user.getId())) {
                    result.add(workspace);
                }
            }
        }
        return result;
    }

    @Transactional
    public Long save(WorkspaceSaveRequestDto requestDto) {
        Workspace workspace = requestDto.toEntity();
        workspaceRepository.save(workspace).getId();

        //workspaceUser save
        for (WorkspaceUserSaveRequestDto dto : requestDto.getMembers()) {
            UserAuth user = userRepository.findByEmail(dto.getEmail());
            WorkspaceUser workspaceUser = dto.toEntity(user, workspace);
            workspaceUserRepository.save(workspaceUser);
        }

        return workspace.getId();
    }

    private String decodeJWT(HttpServletRequest request){
        String token = request.getHeader(SecurityConst.HEADER_STRING);

        return JWT
                .require(Algorithm.HMAC512(SecurityConst.SECRET_KEY))
                .build()
                .verify(token.replace(SecurityConst.TOKEN_PREFIX, ""))
                .getSubject();
    }
}
