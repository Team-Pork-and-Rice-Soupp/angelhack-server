select 1;
-- user
insert into user_profile(id, email, name, pw, security_role) values (default, 'root', 'root', '{noop}12345', 'USER');
insert into user_profile(id, email, name, pw, security_role) values (default, 'hahava@naver.com', 'hahava', '{noop}12345', 'USER');
insert into user_profile(id, email, name, pw, security_role) values (default, 'yoonjae@gmail.com', 'yoonjae', '{noop}12345', 'USER');
insert into user_profile(id, email, name, pw, security_role) values (default, 'jaehoon@hanmail.net', 'jaehoon', '{noop}12345', 'USER');

-- workspace
select * from workspace;
insert into workspace(id, created_date, modified_date, description, title) values (default, sysdate(), sysdate(), '동양철학의 이해(매트릭스 참고)', 'workspace');
insert into workspace(id, created_date, modified_date, description, title) values (default, sysdate(), sysdate(), '메모리 설계 팀프로젝트', '컴퓨터 구조론');
insert into workspace(id, created_date, modified_date, description, title) values (default, sysdate(), sysdate(), '유닉스 컴파일 후 나만의 운영체제 개발하기', '운영체제');
insert into workspace(id, created_date, modified_date, description, title) values (default, sysdate(), sysdate(), '창의학습동공동체를 위한 자료', '2020-1학기 창의학기제');

-- workspaceuser
insert into workspace_user(id, description, role, user_profile_id, workspace_id) values(default, '문서 편집과 회의록 담당', 'CREW', 1, 1);
insert into workspace_user(id, description, role, user_profile_id, workspace_id) values(default, '자료 조사 및 보고서 작성', 'MANAGER', 2, 1);
insert into workspace_user(id, description, role, user_profile_id, workspace_id) values(default, '2학년 디지털 미디어 학과', 'MANAGER', 3, 2);
insert into workspace_user(id, description, role, user_profile_id, workspace_id) values(default, '3학년 경영학과', 'CREW', 2, 2);
insert into workspace_user(id, description, role, user_profile_id, workspace_id) values(default, 'Unix 분석', 'MANAGER', 3, 3);
insert into workspace_user(id, description, role, user_profile_id, workspace_id) values(default, '팀장', 'MANAGER', 4, 4);
insert into workspace_user(id, description, role, user_profile_id, workspace_id) values(default, '부팀장', 'CREW', 1, 4);

-- meetinglog
insert into meeting_log (id, title, content, user_profile_id, workspace_id) values (default, '제목1', 'hello world', 1, 1);
insert into meeting_log (id, title, content, user_profile_id, workspace_id) values (default, '제목2', '2번째 회의록', 1, 1);
insert into meeting_log (id, title, content, user_profile_id, workspace_id) values (default, '제목3', '<p>첫번째 회의록입니다. ㅎㅎㅎㅎ</p>', 2, 2);
insert into meeting_log (id, title, content, user_profile_id, workspace_id) values (default, '제목4', '<p>회의록입니다. ㅎㅎㅎㅎ</p>', 3, 2);