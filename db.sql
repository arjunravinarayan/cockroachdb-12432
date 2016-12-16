create database test12432;
set database = 'test12432';
create table test1 (pk string primary key, value timestamptz);
insert into test1 values ('mykey', TIMESTAMPTZ '2016-03-26 10:10:10-05:00');