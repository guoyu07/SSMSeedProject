INSERT INTO USER (userName, passWord) VALUES ('admin', 'admin');
INSERT INTO USER (userName, passWord,accountExpired,locked,credentialsExpired) VALUES ('testUser', 'test',1,0,1);

INSERT INTO ROLE(roleName) VALUES ('ROLE_ADMIN');
INSERT INTO ROLE(roleName) VALUES ('ROLE_USER');

INSERT INTO USER_ROLE(userName,roleName) VALUES ('admin','ROLE_ADMIN');
INSERT INTO USER_ROLE(userName,roleName) VALUES ('admin','ROLE_USER');