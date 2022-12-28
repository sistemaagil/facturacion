INSERT INTO authorities (name, endpoint) values ('User_Write', 'POST:/api/user/');
INSERT INTO authorities (name, endpoint) values ('User_Read', 'GET:/api/user/');
INSERT INTO authorities (name, endpoint) values ('User_Delete', 'DELETE:/api/user/');

INSERT INTO authorities (name, endpoint) values ('Role_Write', 'POST:/api/role/');
INSERT INTO authorities (name, endpoint) values ('Role_Read', 'GET:/api/role/');
INSERT INTO authorities (name, endpoint) values ('Role_Delete', 'DELETE:/api/role/');

INSERT INTO authorities (name, endpoint) values ('Authority_Write', 'POST:/api/authority/');
INSERT INTO authorities (name, endpoint) values ('Authority_Read', 'GET:/api/authority/');
INSERT INTO authorities (name, endpoint) values ('Authority_Delete', 'DELETE:/api/authority/');
INSERT INTO authorities (name, endpoint) values ('AuthorityByUser_Read', 'GET:/api/authority/byuser/{id}/');

INSERT INTO authorities (name, endpoint) values ('Customer_Write', 'POST:/api/cliente/');
INSERT INTO authorities (name, endpoint) values ('Customer_Read', 'GET:/api/cliente/');
INSERT INTO authorities (name, endpoint) values ('Customer_Delete', 'DELETE:/api/cliente/');
INSERT INTO authorities (name, endpoint) values ('Customer_Update', 'PATCH:/api/cliente/');

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
INSERT INTO roles_authorities (role_id, authority_id) VALUES ((SELECT id FROM roles where name = 'ROLE_ADMIN'), (SELECT id FROM authorities WHERE name = 'AuthorityByUser_Read'));
INSERT INTO roles_authorities (role_id, authority_id) VALUES ((SELECT id FROM roles where name = 'ROLE_ADMIN'), (SELECT id FROM authorities WHERE name = 'Customer_Read'));
INSERT INTO roles_authorities (role_id, authority_id) VALUES ((SELECT id FROM roles where name = 'ROLE_ADMIN'), (SELECT id FROM authorities WHERE name = 'Customer_Write'));
INSERT INTO roles_authorities (role_id, authority_id) VALUES ((SELECT id FROM roles where name = 'ROLE_ADMIN'), (SELECT id FROM authorities WHERE name = 'Customer_Delete'));
INSERT INTO roles_authorities (role_id, authority_id) VALUES ((SELECT id FROM roles where name = 'ROLE_ADMIN'), (SELECT id FROM authorities WHERE name = 'Customer_Update'));

INSERT INTO users (name, username, password, looked, expired, enabled) VALUES ('Admin', 'admin', '$2a$10$TwROhi2MZsOTt8igkE7Yyec0WfjK7NlgdX9apOu0b6cY4SxzHLvCq', false, false, true);

INSERT INTO users_roles (user_id, role_id) VALUES ((SELECT id FROM users where username = 'admin'), (SELECT id FROM roles where name = 'ROLE_ADMIN'));