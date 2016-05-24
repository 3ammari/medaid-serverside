# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table users (
  id                        bigint auto_increment not null,
  name                      varchar(255),
  email                     varchar(255),
  sha_password              varbinary(64) not null,
  created_at                timestamp,
  auth_token                varchar(255),
  constraint pk_users primary key (id))
;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists users;

SET REFERENTIAL_INTEGRITY TRUE;

