package com.hackday.angelhack.meetinglog;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hackday.angelhack.user.UserProfile;
import com.hackday.angelhack.workspace.Workspace;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class MeetingLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Lob
    private String content;

    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "user_profile_id")
    private UserProfile userProfile;

    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "workspace_id")
    private Workspace workspace;

}
