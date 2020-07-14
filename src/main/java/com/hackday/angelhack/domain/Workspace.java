package com.hackday.angelhack.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "workspace")
public class Workspace extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private String title;

    private String description;

    @Builder
    public Workspace(Long userId, String title, String description) {
        this.userId = userId;
        this.title = title;
        this.description = description;
    }
}
