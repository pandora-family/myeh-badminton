DROP TABLE IF EXISTS user_info;
CREATE TABLE user_info
(
    `id`        bigint PRIMARY KEY AUTO_INCREMENT COMMENT '用户主键id',
    `user_name` varchar(64) DEFAULT NULL COMMENT '用户名',
    `create_at` datetime    DEFAULT NULL COMMENT '创建日期',
    `create_by` bigint      DEFAULT NULL COMMENT '创建人',
    `update_at` datetime    DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `update_by` bigint      DEFAULT NULL COMMENT '更新人',
    `version`   bigint NOT NULL COMMENT '乐观锁版本号'
);

DROP TABLE IF EXISTS user_third_party_info;
CREATE TABLE user_third_party_info
(
    `id`           bigint PRIMARY KEY AUTO_INCREMENT COMMENT '用户主键id',
    `user_info_id` bigint NOT NULL COMMENT '用户主键id',
    `out_code`     varchar(64) DEFAULT NULL COMMENT '外部编码',
    `create_at`    datetime    DEFAULT NULL COMMENT '创建日期',
    `create_by`    bigint      DEFAULT NULL COMMENT '创建人',
    `update_at`    datetime    DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `update_by`    bigint      DEFAULT NULL COMMENT '更新人',
    `version`      bigint NOT NULL COMMENT '乐观锁版本号'
);
