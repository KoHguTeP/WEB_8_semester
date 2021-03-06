drop table if exists album cascade;
drop table if exists composition cascade;
drop table if exists singer cascade;
create table album (id_album int4 not null, genre varchar(255) not null, id_singer int4 not null, name varchar(255) not null, primary key (id_album));
create table composition (id_composition int4 not null, duration date not null, id_album int4 not null, name varchar(255) not null, primary key (id_composition));
create table singer (id_singer int4 not null, name varchar(255) not null, primary key (id_singer));
