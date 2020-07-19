package com.hackday.angelhack.assessment;

import com.hackday.angelhack.user.UserProfile;
import com.hackday.angelhack.workspace.Workspace;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Assessment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String step;

    private int score;

    @OneToOne
    @JoinColumn(name = "work_space_id")
    private Workspace workspace;

    @OneToOne
    @JoinColumn(name = "evaluated_user")
    private UserProfile evaluatedUser;

    @OneToOne
    @JoinColumn(name = "estimator")
    private UserProfile estimator;


}
