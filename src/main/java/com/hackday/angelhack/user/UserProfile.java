package com.hackday.angelhack.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hackday.angelhack.domain.WorkspaceUser;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

import static com.hackday.angelhack.user.ROLE.USER;

@Getter
@Setter
@Entity
@Table(name = "user_profile")
public class UserProfile {

    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String name;

    @JsonIgnore
    private String pw;

    @JsonIgnore
    @Enumerated(EnumType.STRING)
    private ROLE role;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<WorkspaceUser> workspaceUsers;

    public static UserProfile of(UserAuthDTO userAuthDTO) {
        UserProfile userProfile = new UserProfile();
        userProfile.setEmail(userAuthDTO.getEmail());
        userProfile.setName(userAuthDTO.getName());
        userProfile.setPw(userAuthDTO.getPw());
        userProfile.setRole(USER);
        return userProfile;
    }

}
