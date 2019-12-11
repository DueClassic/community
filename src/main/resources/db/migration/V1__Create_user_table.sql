CREATE TABLE USER
(
    ID BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL,
    ACCOUNT_ID varchar(100),
    NAME varchar(50),
    TOKEN varchar(36),
    GMT_CREATE bigint,
    GMT_MODIFIED bigint,
    bio VARCHAR(256) NULL,
    avatar_url VARCHAR(100) NULL
);