drop table if exists t_collect_module;
create table t_collect_module(
  `id` varchar (64) not null ,
  `name` varchar(64) not null ,
  `description` text ,
  createTime timestamp default current_timestamp ,
  `creator` VARCHAR(256),
  primary key (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

drop table if exists t_collect_plan;
create table t_collect_plan (
  `id` varchar(64) not null,
  `moduleId` varchar(64) not null,
  `name` varchar(64) not null ,
  `description` text,
  `cron` varchar(64),
  `createTime` timestamp default current_timestamp ,
  `creator` varchar(256),
  primary key (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;