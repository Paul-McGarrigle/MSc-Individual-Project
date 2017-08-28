DROP DATABASE if exists individual_project;
CREATE DATABASE individual_project;
USE individual_project;

CREATE TABLE users (
    username VARCHAR(45) NOT NULL,
    password VARCHAR(100) NOT NULL,
    email VARCHAR(45) NOT NULL,
    age int NOT NULL,
    -- gender VARCHAR(45) NOT NULL,
    birthday VARCHAR(45) NOT NULL,
    phone VARCHAR(45) NOT NULL,
    country VARCHAR(45) NOT NULL,
    enabled TINYINT NOT NULL DEFAULT 1 ,
    id int NOT NULL,
	PRIMARY KEY (username)
);

CREATE TABLE user_roles (
  user_role_id int(11) NOT NULL AUTO_INCREMENT,
  username varchar(45) NOT NULL,
  role varchar(45) NOT NULL,
  PRIMARY KEY (user_role_id),
  UNIQUE KEY uni_username_role (role,username),
  KEY fk_username_idx (username),
  CONSTRAINT fk_username FOREIGN KEY (username) REFERENCES users (username));
  
-- friendship_status refers to the numeric representation of the status of the friend request
-- 0 = Pending
-- 1 = Accepted
-- 2 = Declined
-- 3 = Blocked
-- 4 = Unblocked
CREATE TABLE friendship (
  friendship_id int(11) NOT NULL AUTO_INCREMENT,
  user_one_id varchar(45) NOT NULL,
  user_two_id varchar(45) NOT NULL,
  friendship_status INT(3) UNSIGNED NOT NULL DEFAULT '0',
  action_user_id varchar(45) NOT NULL,
  PRIMARY KEY (friendship_id),
  FOREIGN KEY (`user_one_id`) REFERENCES users(`username`),
  FOREIGN KEY (`user_two_id`) REFERENCES users(`username`),
  FOREIGN KEY (`action_user_id`) REFERENCES users(`username`)
);

insert into friendship(user_one_id,user_two_id,friendship_status, action_user_id)
values('user3','user1',0,'user1');
insert into friendship(user_one_id,user_two_id,friendship_status, action_user_id)
values('user4','user1',0,'user1');
insert into friendship(user_one_id,user_two_id,friendship_status, action_user_id)
values('user5','user1',0,'user1');
insert into friendship(user_one_id,user_two_id,friendship_status, action_user_id)
values('user6','user1',0,'user1');
insert into friendship(user_one_id,user_two_id,friendship_status, action_user_id)
values('user7','user1',0,'user1');
insert into friendship(user_one_id,user_two_id,friendship_status, action_user_id)
values('user8','user1',0,'user1');
insert into friendship(user_one_id,user_two_id,friendship_status, action_user_id)
values('user9','user1',0,'user1');

-- This ensures that duplicate friendship records will not be kept
-- at the moment duplicate records throws a server 500, this needs to be fixed
ALTER TABLE friendship
ADD UNIQUE KEY unique_users_id (`user_one_id`,`user_two_id`);

CREATE TABLE wall (
  wall_id int(11) NOT NULL AUTO_INCREMENT,
  wall_owner varchar(45) NOT NULL,
  comment_owner varchar(45) NOT NULL,
  wall_comment varchar(45) NOT NULL,
  PRIMARY KEY (wall_id),
  FOREIGN KEY (wall_owner) REFERENCES users(username),
  FOREIGN KEY (comment_owner) REFERENCES users(username)
);

-- Password is 123456
INSERT INTO users(username,password,enabled, email, age, birthday, phone, country, id)
VALUES ('paul','$2a$10$04TVADrR6/SPLBjsK0N30.Jf5fNjBugSACeGv1S69dZALR7lSov0y', true,
"test@email.com", 55, "01/01/1990", "5353535353", "Ireland", 1);

-- Random users
INSERT INTO users(username,password,enabled, email, age, birthday, phone, country, id)
VALUES ('user1','$2a$10$04TVADrR6/SPLBjsK0N30.Jf5fNjBugSACeGv1S69dZALR7lSov0y', true,
"test@email.com", 55, "01/01/1990", "5353535353", "Ireland", 1);
INSERT INTO users(username,password,enabled, email, age, birthday, phone, country, id)
VALUES ('user2','$2a$10$04TVADrR6/SPLBjsK0N30.Jf5fNjBugSACeGv1S69dZALR7lSov0y', true,
"test@email.com", 55, "01/01/1990", "5353535353", "Ireland", 1);
INSERT INTO users(username,password,enabled, email, age, birthday, phone, country, id)
VALUES ('user3','$2a$10$04TVADrR6/SPLBjsK0N30.Jf5fNjBugSACeGv1S69dZALR7lSov0y', true,
"test@email.com", 55, "01/01/1990", "5353535353", "Ireland", 1);
INSERT INTO users(username,password,enabled, email, age, birthday, phone, country, id)
VALUES ('user4','$2a$10$04TVADrR6/SPLBjsK0N30.Jf5fNjBugSACeGv1S69dZALR7lSov0y', true,
"test@email.com", 55, "01/01/1990", "5353535353", "Ireland", 1);
INSERT INTO users(username,password,enabled, email, age, birthday, phone, country, id)
VALUES ('user5','$2a$10$04TVADrR6/SPLBjsK0N30.Jf5fNjBugSACeGv1S69dZALR7lSov0y', true,
"test@email.com", 55, "01/01/1990", "5353535353", "Ireland", 1);
INSERT INTO users(username,password,enabled, email, age, birthday, phone, country, id)
VALUES ('user6','$2a$10$04TVADrR6/SPLBjsK0N30.Jf5fNjBugSACeGv1S69dZALR7lSov0y', true,
"test@email.com", 55, "01/01/1990", "5353535353", "Ireland", 1);
INSERT INTO users(username,password,enabled, email, age, birthday, phone, country, id)
VALUES ('user7','$2a$10$04TVADrR6/SPLBjsK0N30.Jf5fNjBugSACeGv1S69dZALR7lSov0y', true,
"test@email.com", 55, "01/01/1990", "5353535353", "Ireland", 1);
INSERT INTO users(username,password,enabled, email, age, birthday, phone, country, id)
VALUES ('user8','$2a$10$04TVADrR6/SPLBjsK0N30.Jf5fNjBugSACeGv1S69dZALR7lSov0y', true,
"test@email.com", 55, "01/01/1990", "5353535353", "Ireland", 1);
INSERT INTO users(username,password,enabled, email, age, birthday, phone, country, id)
VALUES ('user9','$2a$10$04TVADrR6/SPLBjsK0N30.Jf5fNjBugSACeGv1S69dZALR7lSov0y', true,
"test@email.com", 55, "01/01/1990", "5353535353", "Ireland", 1);
INSERT INTO users(username,password,enabled, email, age, birthday, phone, country, id)
VALUES ('user10','$2a$10$04TVADrR6/SPLBjsK0N30.Jf5fNjBugSACeGv1S69dZALR7lSov0y', true,
"test@email.com", 55, "01/01/1990", "5353535353", "Ireland", 1);

INSERT INTO user_roles (username, role)
VALUES ('paul', 'ROLE_ADMIN');

INSERT INTO user_roles (username, role)
VALUES ('user1', 'ROLE_USER');
INSERT INTO user_roles (username, role)
VALUES ('user2', 'ROLE_USER');
INSERT INTO user_roles (username, role)
VALUES ('user3', 'ROLE_USER');
INSERT INTO user_roles (username, role)
VALUES ('user4', 'ROLE_USER');
INSERT INTO user_roles (username, role)
VALUES ('user5', 'ROLE_USER');
INSERT INTO user_roles (username, role)
VALUES ('user6', 'ROLE_USER');
INSERT INTO user_roles (username, role)
VALUES ('user7', 'ROLE_USER');
INSERT INTO user_roles (username, role)
VALUES ('user8', 'ROLE_USER');
INSERT INTO user_roles (username, role)
VALUES ('user9', 'ROLE_USER');
INSERT INTO user_roles (username, role)
VALUES ('user10', 'ROLE_USER');


INSERT INTO wall (wall_owner, comment_owner, wall_comment)
VALUES('user1','user10','User10 commented on user1s wall');
INSERT INTO wall (wall_owner, comment_owner, wall_comment)
VALUES('user10','user2','User2 commented on user10s wall');
INSERT INTO wall (wall_owner, comment_owner, wall_comment)
VALUES('user2','user3','User3 commented on user2s wall');



