drop table if exists user;
create table user(
  id int not null primary key AUTO_INCREMENT,
  username varchar(40),
  password varchar(40),
  age int(3),
  balance double(10,2) not null
) ENGINE=INNODB AUTO_INCREMENT=1000 default CHARSET=utf8;

insert into user(id,username,password,age,balance) values(1,'张三','123',22,40.00);
insert into user(id,username,password,age,balance) values(2,'李四','123',22,40.00);
insert into user(id,username,password,age,balance) values(3,'王五','123',22,40.00);
insert into user(id,username,password,age,balance) values(4,'赵六','123',22,40.00);