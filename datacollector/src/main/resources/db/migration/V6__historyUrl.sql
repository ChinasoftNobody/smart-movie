drop table if exists t_collect_url_history;
create table t_collect_url_history(
  `id` varchar (64) not null ,
  threadId varchar(64) not null ,
  url varchar(512) not null ,
  createTime timestamp default current_timestamp ,
  primary key (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;