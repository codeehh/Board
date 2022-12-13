DROP DATABASE IF EXISTS `board`;
CREATE DATABASE `board`;
USE `board`;

CREATE TABLE `user` (
	`user_id`	int	NOT NULL AUTO_INCREMENT,
	`id`	varchar(20)	NOT NULL,
	`nickname`	varchar(20)	NOT NULL,
	`hashing_password`	varchar(255)	NOT NULL,
	`email`	varchar(50)	NOT NULL,
    PRIMARY KEY(`user_id`)
);

CREATE TABLE `post` (
	`post_id`	int	NOT NULL AUTO_INCREMENT,
	`title`	varchar(50)	NOT NULL,
	`content`	text	NULL,
	`time`	datetime	NOT NULL,
	`view_count`	int	NOT NULL,
	`like`	int	NOT NULL,
	`unlike`	int	NOT NULL,
	`user_id`	int	NOT NULL,
    PRIMARY KEY(`post_id`)
);

CREATE TABLE `comment` (
	`comment_id`	int	NOT NULL AUTO_INCREMENT,
	`content`	text	NOT NULL,
	`time`	datetime	NOT NULL,
	`like`	int	NOT NULL,
	`unlike`	int	NOT NULL,
	`user_id`	int	NOT NULL,
	`post_id`	int	NOT NULL,
    PRIMARY KEY(`comment_id`)
);

CREATE TABLE `user_like_post` (
	`user_id`	int	NOT NULL,
	`post_id`	int	NOT NULL
);

CREATE TABLE `user_like_comment` (
	`user_id`	int	NOT NULL,
	`comment_id`	int	NOT NULL
);

ALTER TABLE `post` ADD CONSTRAINT `FK_user_TO_post_1` FOREIGN KEY (
	`user_id`
)
REFERENCES `user` (
	`user_id`
);

ALTER TABLE `comment` ADD CONSTRAINT `FK_user_TO_comment_1` FOREIGN KEY (
	`user_id`
)
REFERENCES `user` (
	`user_id`
);

ALTER TABLE `comment` ADD CONSTRAINT `FK_post_TO_comment_1` FOREIGN KEY (
	`post_id`
)
REFERENCES `post` (
	`post_id`
);

ALTER TABLE `user_like_post` ADD CONSTRAINT `FK_user_TO_user_like_post_1` FOREIGN KEY (
	`user_id`
)
REFERENCES `user` (
	`user_id`
);

ALTER TABLE `user_like_post` ADD CONSTRAINT `FK_post_TO_user_like_post_1` FOREIGN KEY (
	`post_id`
)
REFERENCES `post` (
	`post_id`
);

ALTER TABLE `user_like_comment` ADD CONSTRAINT `FK_user_TO_user_like_comment_1` FOREIGN KEY (
	`user_id`
)
REFERENCES `user` (
	`user_id`
);

ALTER TABLE `user_like_comment` ADD CONSTRAINT `FK_comment_TO_user_like_comment_1` FOREIGN KEY (
	`comment_id`
)
REFERENCES `comment` (
	`comment_id`
);

