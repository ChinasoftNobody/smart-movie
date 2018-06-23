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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

