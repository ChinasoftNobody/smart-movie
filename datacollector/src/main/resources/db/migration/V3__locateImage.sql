drop table if exists t_locate_img;
create table t_locate_img(
  id varchar(64) not null ,
  image blob,
  createTime timestamp default current_timestamp ,
  primary key (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;