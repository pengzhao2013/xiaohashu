create database if not exists xiaohashu
CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
use xiaohashu;
# 用户服务相关表
CREATE TABLE `t_user` (
                          `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
                          `xiaohashu_id` varchar(15) NOT NULL COMMENT '小哈书号(唯一凭证)',
                          `password` varchar(64) DEFAULT NULL COMMENT '密码',
                          `nickname` varchar(24) NOT NULL COMMENT '昵称',
                          `avatar` varchar(120) DEFAULT NULL COMMENT '头像',
                          `birthday` date DEFAULT NULL COMMENT '生日',
                          `background_img` varchar(120) DEFAULT NULL COMMENT '背景图',
                          `phone` varchar(11) NOT NULL COMMENT '手机号',
                          `sex` tinyint DEFAULT '0' COMMENT '性别(0：女 1：男)',
                          `status` tinyint NOT NULL DEFAULT '0' COMMENT '状态(0：启用 1：禁用)',
                          `introduction` varchar(100) DEFAULT NULL COMMENT '个人简介',
                          `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                          `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
                          `is_deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '逻辑删除(0：未删除 1：已删除)',
                          PRIMARY KEY (`id`) USING BTREE,
                          UNIQUE KEY `uk_xiaohashu_id` (`xiaohashu_id`),
                          UNIQUE KEY `uk_phone` (`phone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户表';

CREATE TABLE `t_role` (
                          `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
                          `role_name` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '角色名',
                          `role_key` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '角色唯一标识',
                          `status` tinyint NOT NULL DEFAULT '0' COMMENT '状态(0：启用 1：禁用)',
                          `sort` int unsigned NOT NULL DEFAULT 0 COMMENT '管理系统中的显示顺序',
                          `remark` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '备注',
                          `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                          `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后一次更新时间',
                          `is_deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '逻辑删除(0：未删除 1：已删除)',
                          PRIMARY KEY (`id`) USING BTREE,
                          UNIQUE KEY `uk_role_key` (`role_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='角色表';

CREATE TABLE `t_permission` (
                                `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
                                `parent_id` bigint unsigned NOT NULL DEFAULT '0' COMMENT '父ID',
                                `name` varchar(16) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '权限名称',
                                `type` tinyint unsigned NOT NULL COMMENT '类型(1：目录 2：菜单 3：按钮)',
                                `menu_url` varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '菜单路由',
                                `menu_icon` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '菜单图标',
                                `sort` int unsigned NOT NULL DEFAULT 0 COMMENT '管理系统中的显示顺序',
                                `permission_key` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '权限标识',
                                `status` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '状态(0：启用；1：禁用)',
                                `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
                                `is_deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '逻辑删除(0：未删除 1：已删除)',
                                PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='权限表';

CREATE TABLE `t_user_role_rel` (
                                   `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
                                   `user_id` bigint unsigned NOT NULL COMMENT '用户ID',
                                   `role_id` bigint unsigned NOT NULL COMMENT '角色ID',
                                   `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                   `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
                                   `is_deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '逻辑删除(0：未删除 1：已删除)',
                                   PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户角色表';

CREATE TABLE `t_role_permission_rel` (
                                         `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
                                         `role_id` bigint unsigned NOT NULL COMMENT '角色ID',
                                         `permission_id` bigint unsigned NOT NULL COMMENT '权限ID',
                                         `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                         `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
                                         `is_deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '逻辑删除(0：未删除 1：已删除)',
                                         PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户权限表';

INSERT INTO `xiaohashu`.`t_permission` (`id`, `parent_id`, `name`,
                                        `type`, `menu_url`, `menu_icon`, `sort`, `permission_key`, `status`,
                                        `create_time`, `update_time`, `is_deleted`) VALUES (1, 0, '发布笔记',
                                                                                            3, '', '', 1, 'app:note:publish', 0, now(), now(), b'0');

INSERT INTO `xiaohashu`.`t_permission` (`id`, `parent_id`, `name`,
                                        `type`, `menu_url`, `menu_icon`, `sort`, `permission_key`,
                                        `status`, `create_time`, `update_time`, `is_deleted`) VALUES
    (2, 0, '发布评论', 3, '', '', 2, 'app:comment:publish', 0, now(), now(), b'0');

INSERT INTO `xiaohashu`.`t_role` (`id`, `role_name`,
                                  `role_key`, `status`, `sort`, `remark`, `create_time`,
                                  `update_time`, `is_deleted`) VALUES (1, '普通用户', 'common_user',
                                                                       0, 1, '', now(), now(), b'0');

INSERT INTO `xiaohashu`.`t_role_permission_rel` (`id`, `role_id`,
                                                 `permission_id`, `create_time`, `update_time`, `is_deleted`) VALUES
    (1, 1, 1, now(), now(), b'0');

INSERT INTO `xiaohashu`.`t_role_permission_rel` (`id`, `role_id`,
                                                 `permission_id`, `create_time`, `update_time`, `is_deleted`) VALUES
    (2, 1, 2, now(), now(), b'0');

# 号段模式分布式id生成服务
create database if not exists leaf
CHARACTER set utf8mb4 collate utf8mb4_unicode_ci;

use leaf;

CREATE TABLE `leaf_alloc` (
                              `biz_tag` varchar(128)  NOT NULL DEFAULT '' COMMENT '区分业务，例如生成用户ID、生成笔记ID',
                              `max_id` bigint(20) NOT NULL DEFAULT '1' COMMENT '该biz_tag目前所被分配的ID号段的最大值',
                              `step` int(11) NOT null COMMENT '创建时间' COMMENT '每次分配的号段长度',
                              `description` varchar(256)  DEFAULT NULL,
                              `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                              PRIMARY KEY (`biz_tag`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='美团Leaf-segment数据库方案分布式ID生成器';

# 插入测试业务号段数据
insert into leaf_alloc(biz_tag, max_id, step, description)
values('leaf-segment-test', 1, 2000, 'Test leaf Segment Mode Get Id');
# 为小哈书ID业务单独创建一条号段模式分布式ID生成记录
INSERT INTO `leaf`.`leaf_alloc` (`biz_tag`, `max_id`, `step`, `description`, `update_time`)
VALUES ('leaf-segment-xiaohashu-id', 10100, 2000, '小哈书 ID', now());
# 为用户ID业务单独创建一条号段模式分布式ID生成记录
INSERT INTO `leaf`.`leaf_alloc` (`biz_tag`, `max_id`, `step`, `description`, `update_time`)
VALUES ('leaf-segment-user-id', 100, 2000, '用户 ID', now());
# 为笔记评论ID业务单独创建一条号段模式分布式ID生成记录
INSERT INTO `leaf`.`leaf_alloc` (`biz_tag`, `max_id`, `step`, `description`, `update_time`)
VALUES ('leaf-segment-comment-id', 1, 2000, '评论 ID', NOW());

# 笔记服务相关表
use xiaohashu;

CREATE TABLE `t_channel` (
                             `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
                             `name` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '频道名称',
                             `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                             `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
                             `is_deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '逻辑删除(0：未删除 1：已删除)',
                             PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='频道表';


CREATE TABLE `t_topic` (
                           `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
                           `name` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '话题名称',
                           `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                           `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
                           `is_deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '逻辑删除(0：未删除 1：已删除)',
                           PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='话题表';


CREATE TABLE `t_channel_topic_rel` (
                                       `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
                                       `channel_id` bigint(11) unsigned NOT NULL COMMENT '频道ID',
                                       `topic_id` bigint(11) unsigned NOT NULL COMMENT '话题ID',
                                       `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                       `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
                                       PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='频道-话题关联表';


CREATE TABLE `t_note` (
                          `id` bigint(11) unsigned NOT NULL COMMENT '主键ID',
                          `title` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '标题',
                          `is_content_empty` bit(1) NOT NULL DEFAULT b'0' COMMENT '内容是否为空(0：不为空 1：空)',
                          `creator_id` bigint(11) unsigned NOT NULL COMMENT '发布者ID',
                          `topic_id` bigint(11) unsigned DEFAULT NULL COMMENT '话题ID',
                          `topic_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '话题名称',
                          `is_top` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否置顶(0：未置顶 1：置顶)',
                          `type` tinyint(2) DEFAULT '0' COMMENT '类型(0：图文 1：视频)',
                          `img_uris` varchar(660) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '笔记图片链接(逗号隔开)',
                          `video_uri` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '视频链接',
                          `visible` tinyint(2) DEFAULT '0' COMMENT '可见范围(0：公开,所有人可见 1：仅对自己可见)',
                          `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                          `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
                          `status` tinyint(2) NOT NULL DEFAULT '0' COMMENT '状态(0：待审核 1：正常展示 2：被删除(逻辑删除) 3：被下架)',
                          PRIMARY KEY (`id`) USING BTREE,
                          KEY `idx_creator_id` (`creator_id`),
                          KEY `idx_topic_id` (`topic_id`),
                          KEY `idx_status` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='笔记表';

ALTER table t_note add column `content_uuid` varchar(36) CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '笔记内容UUID';

# 初始化频道及话题
INSERT INTO `xiaohashu`.`t_channel` (`name`, `create_time`, `update_time`, `is_deleted`)
VALUES ('美食', now(), now(), 0);
INSERT INTO `xiaohashu`.`t_channel` (`name`, `create_time`, `update_time`, `is_deleted`)
VALUES ('娱乐', now(), now(), 0);

INSERT INTO `xiaohashu`.`t_topic` (`name`, `create_time`, `update_time`, `is_deleted`)
VALUES ('高分美剧推荐', now(), now(), 0);
INSERT INTO `xiaohashu`.`t_topic` (`name`, `create_time`, `update_time`, `is_deleted`)
VALUES ('下饭综艺推荐', now(), now(), 0);

INSERT INTO `xiaohashu`.`t_channel_topic_rel` (`channel_id`, `topic_id`, `create_time`, `update_time`)
VALUES (2, 1, now(), now());
INSERT INTO `xiaohashu`.`t_channel_topic_rel` (`channel_id`, `topic_id`, `create_time`, `update_time`)
VALUES (2, 2, now(), now());

# 用户关系相关表
CREATE TABLE `t_following` (
                               `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
                               `user_id` bigint unsigned NOT NULL COMMENT '用户ID',
                               `following_user_id` bigint unsigned NOT NULL COMMENT '关注的用户ID',
                               `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                               PRIMARY KEY (`id`) USING BTREE,
                               KEY `idx_user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户关注表';

CREATE TABLE `t_fans` (
                          `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
                          `user_id` bigint unsigned NOT NULL COMMENT '用户ID',
                          `fans_user_id` bigint unsigned NOT NULL COMMENT '粉丝的用户ID',
                          `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                          PRIMARY KEY (`id`) USING BTREE,
                          KEY `idx_user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户粉丝表';

alter table `t_following` drop index `idx_user_id`;
ALTER TABLE t_following ADD constraint uk_user_id_following_user_id UNIQUE(user_id, following_user_id);

alter table `t_fans` drop index `idx_user_id`;
ALTER TABLE `t_fans` ADD constraint uk_user_id_fans_user_id UNIQUE(user_id, fans_user_id);

CREATE TABLE `t_note_like` (
                               `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
                               `user_id` bigint(11) NOT NULL COMMENT '用户ID',
                               `note_id` bigint(11) NOT NULL COMMENT '笔记ID',
                               `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                               `status` tinyint(2) NOT NULL DEFAULT '0' COMMENT '点赞状态(0：取消点赞 1：点赞)',
                               PRIMARY KEY (`id`) USING BTREE,
                               UNIQUE KEY `uk_user_id_note_id` (`user_id`,`note_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='笔记点赞表';

CREATE TABLE `t_note_collection` (
                                     `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
                                     `user_id` bigint(11) NOT NULL COMMENT '用户ID',
                                     `note_id` bigint(11) NOT NULL COMMENT '笔记ID',
                                     `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                     `status` tinyint(2) NOT NULL DEFAULT '0' COMMENT '收藏状态(0：取消收藏 1：收藏)',
                                     PRIMARY KEY (`id`) USING BTREE,
                                     UNIQUE KEY `uk_user_id_note_id` (`user_id`,`note_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='笔记收藏表';

CREATE TABLE `t_note_count` (
                                `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
                                `note_id` bigint unsigned NOT NULL COMMENT '笔记ID',
                                `like_total` bigint DEFAULT '0' COMMENT '获得点赞总数',
                                `collect_total` bigint DEFAULT '0' COMMENT '获得收藏总数',
                                `comment_total` bigint DEFAULT '0' COMMENT '被评论总数',
                                PRIMARY KEY (`id`) USING BTREE,
                                UNIQUE KEY `uk_note_id` (`note_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='笔记计数表';

CREATE TABLE `t_user_count` (
                                `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
                                `user_id` bigint(11) unsigned NOT NULL COMMENT '用户ID',
                                `fans_total` bigint(11) DEFAULT '0' COMMENT '粉丝总数',
                                `following_total` bigint(11) DEFAULT '0' COMMENT '关注总数',
                                `note_total` bigint(11) DEFAULT '0' COMMENT '发布笔记总数',
                                `like_total` bigint(11) DEFAULT '0' COMMENT '获得点赞总数',
                                `collect_total` bigint(11) DEFAULT '0' COMMENT '获得收藏总数',
                                PRIMARY KEY (`id`) USING BTREE,
                                UNIQUE KEY `uk_user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户计数表';

show VARIABLES like 'log_bin'; -- ON

CREATE USER canal IDENTIFIED BY 'canal';
GRANT SELECT, REPLICATION SLAVE, REPLICATION CLIENT ON *.* TO 'canal'@'%';
-- GRANT ALL PRIVILEGES ON *.* TO 'canal'@'%' ;
FLUSH PRIVILEGES;

CREATE TABLE `t_comment` (
                             `id` bigint (20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
                             `note_id` bigint (20) unsigned NOT NULL COMMENT '关联的笔记ID',
                             `user_id` bigint (20) unsigned NOT NULL COMMENT '发布者用户ID',
                             `content_uuid` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT '' COMMENT '评论内容UUID',
                             `is_content_empty` bit(1) NOT NULL DEFAULT b'0' COMMENT '内容是否为空(0：不为空 1：为空)',
                             `image_url` varchar(80) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '评论附加图片URL',
                             `level` tinyint (2) NOT NULL DEFAULT '1' COMMENT '级别(1：一级评论 2：二级评论)',
                             `reply_total` bigint (20) unsigned DEFAULT 0 COMMENT '评论被回复次数，仅一级评论需要',
                             `like_total` bigint (20) DEFAULT 0 COMMENT '评论被点赞次数',
                             `parent_id` bigint (20) unsigned DEFAULT 0 COMMENT '父ID (若是对笔记的评论，则此字段存储笔记ID; 若是二级评论，则此字段存储一级评论的ID)',
                             `reply_comment_id` bigint (20) unsigned DEFAULT 0 COMMENT '回复哪个的评论 (0表示是对笔记的评论，若是对他人评论的回复，则存储回复评论的ID)',
                             `reply_user_id` bigint (20) unsigned DEFAULT 0 COMMENT '回复的哪个用户, 存储用户ID',
                             `is_top` tinyint (2) NOT NULL DEFAULT '0' COMMENT '是否置顶(0：不置顶 1：置顶)',
                             `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                             `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
                             PRIMARY KEY (`id`) USING BTREE,
                             KEY `idx_note_id` (`note_id`) USING BTREE,
                             KEY `idx_user_id` (`user_id`) USING BTREE,
                             KEY `idx_parent_id` (`parent_id`) USING BTREE,
                             KEY `idx_create_time` (`create_time`) USING BTREE,
                             KEY `idx_reply_comment_id` (`reply_comment_id`) USING BTREE,
                             KEY `idx_reply_user_id` (`reply_user_id`) USING BTREE
) ENGINE = InnoDB default CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci COMMENT = '评论表';

CREATE TABLE `t_comment_like` (
                                  `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
                                  `user_id` bigint NOT NULL COMMENT '用户ID',
                                  `comment_id` bigint NOT NULL COMMENT '评论ID',
                                  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
                                  PRIMARY KEY (`id`),
                                  UNIQUE KEY `uk_user_id_comment_id` (`user_id`,`comment_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='评论点赞表';

alter table t_comment add column `child_comment_total` bigint(20)
unsigned DEFAULT 0 COMMENT '二级评论总数（只有一级评论才需要统计）';

ALTER TABLE t_comment ADD COLUMN heat DECIMAL(10, 2) DEFAULT 0 COMMENT '评论热度';

alter table t_comment add column first_reply_comment_id bigint(20) unsigned default 0 COMMENT '最早回复的评论ID (只有一级评论需要)';