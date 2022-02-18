# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table actor (
  id                        bigint not null,
  firstname                 varchar(255),
  lastname                  varchar(255),
  age                       integer,
  role                      varchar(255),
  constraint pk_actor primary key (id))
;

create sequence actor_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists actor;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists actor_seq;

