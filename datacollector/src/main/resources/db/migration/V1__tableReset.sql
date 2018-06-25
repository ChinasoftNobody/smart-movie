drop table if exists t_movie;

CREATE TABLE `t_movie` (
  `id` varchar(64) NOT NULL,
  `name` text,
  `translateName` text,
  `age` text,
  `region` text,
  `type` text,
  `language` text,
  `words` text,
  `onlineDate` text,
  `iMDBScore` text,
  `doubanScore` text,
  `fileType` text,
  `fileSize` text,
  `fileDimension` text,
  `fileTimeLength` text,
  `director` text,
  `actors` text,
  `description` text,
  `award` text,
  `ftpUrl` text,
  `mainImage` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

drop table if exists t_movie_image;

create table `t_movie_image` (
  `id` varchar(64) not null,
  `movie_id` varchar(64) not null ,
  `image` text,
  `index` integer(10) ,
  primary key (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

