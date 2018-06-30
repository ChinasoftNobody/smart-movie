truncate table t_movie;
truncate table t_movie_image;
alter table t_movie modify column `name` varchar(512);
alter table t_movie_image modify column `image` varchar(512);

alter table t_movie add constraint uk_movie_name unique (`name`);
alter table t_movie_image add constraint uk_movie_image unique (`movie_id`,`image`);
