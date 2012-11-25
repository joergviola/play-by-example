# --- !Ups

create table BLOG_ENTRY (
  SUBJECT              		varchar(255),
  TEXT                   	varchar(255)
);

# --- !Downs

drop table if exists BLOG_ENTRY;