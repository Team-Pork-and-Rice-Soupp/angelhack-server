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

}