INSERT INTO authorities (name) values ('User_Write');
INSERT INTO authorities (name) values ('User_Read');
INSERT INTO authorities (name) values ('User_Delete');

INSERT INTO authorities (name) values ('Role_Write');
INSERT INTO authorities (name) values ('Role_Read');
INSERT INTO authorities (name) values ('Role_Delete');

INSERT INTO authorities (name) values ('Authority_Write');
INSERT INTO authorities (name) values ('Authority_Read');
INSERT INTO authorities (name) values ('Authority_Delete');

INSERT INTO roles (name) values ('ROLE_ADMIN');
INSERT INTO roles (name) values ('ROLE_ASESOR');
INSERT INTO roles (name) values ('ROLE_FACTURADOR');

INSERT INTO roles_authorities (role_id, authority_id) VALUES ((SELECT id FROM roles where name = 'ROLE_ADMIN'), (SELECT id FROM authorities WHERE name = 'User_Write'));
INSERT INTO roles_authorities (role_id, authority_id) VALUES ((SELECT id FROM roles where name = 'ROLE_ADMIN'), (SELECT id FROM authorities WHERE name = 'User_Read'));
INSERT INTO roles_authorities (role_id, authority_id) VALUES ((SELECT id FROM roles where name = 'ROLE_ADMIN'), (SELECT id FROM authorities WHERE name = 'User_Delete'));
INSERT INTO roles_authorities (role_id, authority_id) VALUES ((SELECT id FROM roles where name = 'ROLE_ADMIN'), (SELECT id FROM authorities WHERE name = 'Role_Write'));
INSERT INTO roles_authorities (role_id, authority_id) VALUES ((SELECT id FROM roles where name = 'ROLE_ADMIN'), (SELECT id FROM authorities WHERE name = 'Role_Read'));
INSERT INTO roles_authorities (role_id, authority_id) VALUES ((SELECT id FROM roles where name = 'ROLE_ADMIN'), (SELECT id FROM authorities WHERE name = 'Role_Delete'));
INSERT INTO roles_authorities (role_id, authority_id) VALUES ((SELECT id FROM roles where name = 'ROLE_ADMIN'), (SELECT id FROM authorities WHERE name = 'Authority_Write'));
INSERT INTO roles_authorities (role_id, authority_id) VALUES ((SELECT id FROM roles where name = 'ROLE_ADMIN'), (SELECT id FROM authorities WHERE name = 'Authority_Read'));
INSERT INTO roles_authorities (role_id, authority_id) VALUES ((SELECT id FROM roles where name = 'ROLE_ADMIN'), (SELECT id FROM authorities WHERE name = 'Authority_Delete'));

INSERT INTO users (name, username, password, looked, expired, enabled) VALUES ('Admin', 'admin', '$2a$10$TwROhi2MZsOTt8igkE7Yyec0WfjK7NlgdX9apOu0b6cY4SxzHLvCq', false, false, true);

INSERT INTO users_roles (user_id, role_id) VALUES ((SELECT id FROM users where username = 'admin'), (SELECT id FROM roles where name = 'ROLE_ADMIN'));