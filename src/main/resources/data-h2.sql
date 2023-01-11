INSERT INTO TB_USERS (id, username, password, account_non_expired, account_non_locked, credentials_non_expired, enabled)
VALUES (RANDOM_UUID(), 'admin', '$2a$10$Ur1lrNCyFpD/7MLA7KkO.eE9VDR4IklCLbl0PfKzbCOPdW6zxSB66', true, true, true, true);
INSERT INTO TB_USERS (id, username, password, account_non_expired, account_non_locked, credentials_non_expired, enabled)
VALUES (RANDOM_UUID(), 'user', '$2a$10$Ur1lrNCyFpD/7MLA7KkO.eE9VDR4IklCLbl0PfKzbCOPdW6zxSB66', true, true, true, true);

INSERT INTO TB_ROLES (id, role_name)
VALUES (RANDOM_UUID(), 'ROLE_ADMIN');
INSERT INTO TB_ROLES (id, role_name)
VALUES (RANDOM_UUID(), 'ROLE_USER');

INSERT INTO TB_USERS_ROLES (user_id, role_id)
VALUES (SELECT id FROM TB_USERS WHERE username = 'admin', SELECT id FROM TB_ROLES WHERE role_name = 'ROLE_ADMIN');
INSERT INTO TB_USERS_ROLES (user_id, role_id)
VALUES (SELECT id FROM TB_USERS WHERE username = 'user', SELECT id FROM TB_ROLES WHERE role_name = 'ROLE_USER');



