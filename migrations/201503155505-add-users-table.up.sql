CREATE TABLE users
(id VARCHAR(40) PRIMARY KEY,
 name VARCHAR(60),
 email VARCHAR(60),
 github_username VARCHAR(30),
 created_at TIME,
 updated_at TIME,
 last_login TIME,
 admin BOOLEAN,
 pass VARCHAR(100));
