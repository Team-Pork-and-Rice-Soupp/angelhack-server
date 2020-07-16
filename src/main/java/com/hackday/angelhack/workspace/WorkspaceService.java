package com.hackday.angelhack.workspace;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.hackday.angelhack.domain.ProjectRole;
import com.hackday.angelhack.domain.Workspace;
import com.hackday.angelhack.domain.WorkspaceUser;
import com.hackday.angelhack.security.SecurityConst;
import com.hackday.angelhack.user.UserProfile;
import com.hackday.angelhack.user.UserRepository;
import com.hackday.angelhack.workspace.dto.WorkspaceSaveRequestDto;
import com.hackday.angelhack.workspace.dto.WorkspaceUserSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class WorkspaceService {

    private final WorkspaceRepository workspaceRepository;
    private final UserRepository userRepository;
    private final WorkspaceUserRepository workspaceUserRepository;

    public List<Workspace> findAllByUserId(HttpServletRequest request) {
        String email = decodeJWT(request);

        Iterable<Workspace> workspaces = workspaceRepository.findAll();
        UserProfile user = userRepository.findByEmail(email);
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
            UserProfile user = userRepository.findByEmail(dto.getEmail());
            WorkspaceUser workspaceUser = dto.toEntity(user, workspace);
            workspaceUserRepository.save(workspaceUser);
        }

        return workspace.getId();
    }

    public Workspace getWorkspaceInfoByWorkspaceId(HttpServletRequest request, Long workspaceId) throws NullPointerException{
        String email = decodeJWT(request);
        Optional<Workspace> optional = workspaceRepository.findById(workspaceId);
        Workspace workspace = optional.orElseThrow(NullPointerException::new);

        boolean flag = false;
        for(WorkspaceUser member : workspace.getWorkspaceUsers()){
            if(member.getUser().getEmail().equals(email)){
                flag = true;
            }
        }

        if(!flag){
            throw new NullPointerException();
        }

        return workspace;
    }

    @Transactional
    public Long deleteById(HttpServletRequest request, Long workspaceId){
        String email = decodeJWT(request);
        UserProfile user = userRepository.findByEmail(email);
        List<WorkspaceUser> members = workspaceUserRepository.findAllByUser(user);
        WorkspaceUser member = null;

        for(WorkspaceUser workspaceUser : members){
            if(workspaceUser.getWorkspace().getId().equals(workspaceId)){
                member = workspaceUser;
                break;
            }
        }


        if(member == null || !member.getRole().equals(ProjectRole.MANAGER)){
            return 0L;
        }

        workspaceRepository.deleteById(workspaceId);

        return workspaceId;
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
