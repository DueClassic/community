CREATE TABLE comment
(
  id bigint AUTO_INCREMENT PRIMARY KEY ,
  parent_id bigint not null,
  type INT not null,
  commentator BIGINT not null,
  gmt_create bigint not NULL ,
  gmt_modified bigint not null,
  like_count bigint DEFAULT 0,
  content VARCHAR(1024) NOT NULL,
  comment_count INT DEFAULT 0
);