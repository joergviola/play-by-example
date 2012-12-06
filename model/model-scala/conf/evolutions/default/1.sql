# --- !Ups

create table BLOG_ENTRY (
  AUTH_ID					int,
  SUBJECT              		varchar(255),
  TEXT                   	varchar(255)
);


create table AUTHOR (
  ID						int NOT NULL AUTO_INCREMENT,
  NAME              		varchar(255),
  PWD                   	varchar(255),
  PRIMARY KEY (ID)
);

# --- !Downs

drop table if exists BLOG_ENTRY;
drop table if exists AUTHOR;
