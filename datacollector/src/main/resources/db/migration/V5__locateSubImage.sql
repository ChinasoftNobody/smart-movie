drop table if exists t_locate_sub_img;
create table t_locate_sub_img(
  id varchar(64) not null ,
  movieId varchar(64) not null ,
  image longblob,
  createTime timestamp default current_timestamp ,
  primary key (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;