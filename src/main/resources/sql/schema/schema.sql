DROP TABLE IF EXISTS user_info;
CREATE TABLE user_info
(
    `id`        bigint PRIMARY KEY AUTO_INCREMENT COMMENT '用户主键id',
    `code`      varchar(64) DEFAULT NOT NULL COMMENT '编码',
    `user_name` varchar(64) DEFAULT NOT NULL COMMENT '用户名',
    `create_at` datetime    DEFAULT NULL COMMENT '创建日期',
    `create_by` bigint      DEFAULT NULL COMMENT '创建人',
    `update_at` datetime    DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `update_by` bigint      DEFAULT NULL COMMENT '更新人',
    `version`   bigint      DEFAULT 1 NOT NULL COMMENT '乐观锁版本号'
);

DROP TABLE IF EXISTS user_third_party_info;
CREATE TABLE user_third_party_info
(
    `id`           bigint PRIMARY KEY AUTO_INCREMENT COMMENT '用户主键id',
    `user_info_id` bigint                NOT NULL COMMENT '用户主键id',
    `out_code`     varchar(64) DEFAULT NULL COMMENT '外部编码',
    `create_at`    datetime    DEFAULT NULL COMMENT '创建日期',
    `create_by`    bigint      DEFAULT NULL COMMENT '创建人',
    `update_at`    datetime    DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `update_by`    bigint      DEFAULT NULL COMMENT '更新人',
    `version`      bigint      DEFAULT 1 NOT NULL COMMENT '乐观锁版本号'
);

DROP TABLE IF EXISTS brand;
CREATE TABLE brand
(
    `id`        bigint PRIMARY KEY AUTO_INCREMENT COMMENT '用户主键id',
    `code`      varchar(64)  DEFAULT NOT NULL COMMENT '编码',
    `name`      varchar(64)  DEFAULT NOT NULL COMMENT '名称',
    `logo`      varchar(256) DEFAULT NULL COMMENT 'logo',
    `country`   varchar(64)  DEFAULT NULL COMMENT '国家',
    `create_at` datetime     DEFAULT NULL COMMENT '创建日期',
    `create_by` bigint       DEFAULT NULL COMMENT '创建人',
    `update_at` datetime     DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `update_by` bigint       DEFAULT NULL COMMENT '更新人',
    `version`   bigint       DEFAULT 1 NOT NULL COMMENT '乐观锁版本号'
);

DROP TABLE IF EXISTS item;
CREATE TABLE item
(
    `id`             bigint PRIMARY KEY AUTO_INCREMENT COMMENT '用户主键id',
    `code`           varchar(64) DEFAULT NOT NULL COMMENT '编码',
    `brand_id`       bigint      DEFAULT NOT NULL COMMENT '品牌',
    `name`           varchar(64) DEFAULT NOT NULL COMMENT '名称',
    `item_type_dict` varchar(64) DEFAULT NOT NULL COMMENT '商品类型',
    `item_sku_id`    bigint      DEFAULT NULL COMMENT '商品型号',
    `create_at`      datetime    DEFAULT NULL COMMENT '创建日期',
    `create_by`      bigint      DEFAULT NULL COMMENT '创建人',
    `update_at`      datetime    DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `update_by`      bigint      DEFAULT NULL COMMENT '更新人',
    `version`        bigint      DEFAULT 1 NOT NULL COMMENT '乐观锁版本号'
);

DROP TABLE IF EXISTS item_sku;
CREATE TABLE item_sku
(
    `id`        bigint PRIMARY KEY AUTO_INCREMENT COMMENT '用户主键id',
    `code`      varchar(64)  DEFAULT NOT NULL COMMENT '编码',
    `item_id`   bigint       DEFAULT NOT NULL COMMENT '商品',
    `desc`      varchar(256) DEFAULT NULL COMMENT '详细描述',
    `create_at` datetime     DEFAULT NULL COMMENT '创建日期',
    `create_by` bigint       DEFAULT NULL COMMENT '创建人',
    `update_at` datetime     DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `update_by` bigint       DEFAULT NULL COMMENT '更新人',
    `version`   bigint       DEFAULT 1 NOT NULL COMMENT '乐观锁版本号'
);

DROP TABLE IF EXISTS item_price;
CREATE TABLE item_price
(
    `id`          bigint PRIMARY KEY AUTO_INCREMENT COMMENT '用户主键id',
    `item_sku_id` bigint         DEFAULT NOT NULL COMMENT '商品型号',
    `price_amt`   decimal(12, 2) DEFAULT NOT NULL COMMENT '商品型号',
    `price_at`    datetime       DEFAULT NULL COMMENT '价格日期',
    `create_at`   datetime       DEFAULT NULL COMMENT '创建日期',
    `create_by`   bigint         DEFAULT NULL COMMENT '创建人',
    `update_at`   datetime       DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `update_by`   bigint         DEFAULT NULL COMMENT '更新人',
    `version`     bigint         DEFAULT 1 NOT NULL COMMENT '乐观锁版本号'
);

DROP TABLE IF EXISTS item_recommend_record;
CREATE TABLE item_recommend_record
(
    `id`           bigint PRIMARY KEY AUTO_INCREMENT COMMENT '用户主键id',
    `item_id`      bigint   DEFAULT NOT NULL COMMENT '商品',
    `item_sku_id`  bigint   DEFAULT NOT NULL COMMENT '商品型号',
    `recommender`  bigint   DEFAULT NOT NULL COMMENT '推荐人',
    `recommend_at` datetime DEFAULT NOT NULL COMMENT '推荐时间',
    `create_at`    datetime DEFAULT NULL COMMENT '创建日期',
    `create_by`    bigint   DEFAULT NULL COMMENT '创建人',
    `update_at`    datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `update_by`    bigint   DEFAULT NULL COMMENT '更新人',
    `version`      bigint   DEFAULT 1 NOT NULL COMMENT '乐观锁版本号'
);
