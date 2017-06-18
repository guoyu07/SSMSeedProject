INSERT INTO USER (userName, passWord)
VALUES ('admin', '9cf3e758a497c6274bd066d0b2168432f8a34aad95f63a65677a9a56acec94a7');
INSERT INTO USER (userName, passWord, accountExpired, locked, credentialsExpired) VALUES ('testUser', 'test', 1, 1, 0);

INSERT INTO ROLE (roleName) VALUES ('ROLE_ADMIN');
INSERT INTO ROLE (roleName) VALUES ('ROLE_USER');

INSERT INTO USER_ROLE (userName, roleName) VALUES ('admin', 'ROLE_ADMIN');
INSERT INTO USER_ROLE (userName, roleName) VALUES ('admin', 'ROLE_USER');

INSERT INTO USER_ROLE (userName, roleName) VALUES ('testUser', 'ROLE_USER');

INSERT INTO ADDRESS (street, city, state, zip, country) VALUES ('street', 'city', 'state', 'zip', 'country');
UPDATE USER
SET addrId = 1
WHERE userName = 'admin';

INSERT INTO ADDRESS (street, city, state, zip, country)
VALUES ('testUser_street', 'testUser_city', 'testUser_state', 'testUser_zip', 'testUser_country');
UPDATE USER
SET addrId = 2
WHERE userName = 'testUser';