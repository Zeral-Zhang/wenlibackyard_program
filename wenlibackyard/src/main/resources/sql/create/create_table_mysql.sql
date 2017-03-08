-- 创建收藏夹表
CREATE TABLE `favorite` (
  `favorite_id` VARCHAR(32) NOT NULL COMMENT '收藏夹编号',
  `product_id` VARCHAR(32) NOT NULL COMMENT '商品编号',
  `user_id` varchar(60) NOT NULL COMMENT '用户编号',
  `create_date` date NOT NULL COMMENT '收藏时间',
  `context` varchar(50) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`favorite_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='收藏夹表';

-- 创建订单明细表
CREATE TABLE `order_detail` (
  `order_detail_id` VARCHAR(32) NOT NULL COMMENT '订单明细表编号',
  `order_main_id` VARCHAR(32) NOT NULL COMMENT '订单主表编号',
  `product_id` VARCHAR(32) NOT NULL COMMENT '商品编号',
  `num` int(11) NOT NULL COMMENT '订购数量',
  `sum_price` float(7,2) DEFAULT '0.00' COMMENT '单个商品总价',
  PRIMARY KEY (`order_detail_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单明细表';

CREATE TABLE FILE_INFO
(
   ID                   VARCHAR(50) NOT NULL COMMENT 'file_id',
   NAME                 VARCHAR(200) COMMENT 'file_name',
   ORIGINAL_FORMAT      VARCHAR(20) COMMENT 'original_format',
   PATH                 VARCHAR(200) COMMENT 'path',
   PUBLISH_TIME         DATETIME COMMENT 'publish_time',
   FILE_SIZE            DOUBLE COMMENT 'file_size',
   STATUS               INT(11) COMMENT 'status',
   PRIMARY KEY (ID)
);
ALTER TABLE FILE_INFO COMMENT '附件';

-- 创建订单主表
CREATE TABLE `order_main` (
  `order_main_id` VARCHAR(32) NOT NULL COMMENT '订单主表编号',
  `user_id` varchar(60) NOT NULL COMMENT '用户编号',
  `state` int(11) DEFAULT '0' COMMENT '销售单状态（0-未处理，1-已处理，2-以发货，3-已收货）',
  `buy_date` date DEFAULT NULL COMMENT '购买日期',
  `pay_date` date DEFAULT NULL COMMENT '付款日期',
  `confirm_date` date DEFAULT NULL COMMENT '确认日期',
  `sum_price` float(7,2) DEFAULT '0.00' COMMENT '总价',
  `context` varchar(50) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`order_main_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单主表';

-- 创建商品表
CREATE TABLE `product_info` (
  `product_id` VARCHAR(32) NOT NULL COMMENT '商品编号',
  `product_name` varchar(20) NOT NULL COMMENT '商品名称',
  `product_type_id` VARCHAR(32) NOT NULL COMMENT '商品类别编号',
  `user_id` varchar(60) NOT NULL COMMENT '用户编号',
  `number` int(11) NOT NULL COMMENT '商品数量',
  `brand` varchar(20) DEFAULT NULL COMMENT '商品品牌',
  `context` text  COMMENT '商品描述',
  `imgs` text NOT NULL COMMENT '商品图片',
  `price` float(7,2)  DEFAULT '0.00' COMMENT '商品价格',
  `buy_date` date NOT NULL COMMENT '购买日期',
  `pb_date` date NOT NULL COMMENT '发布日期',
  `state` int(11) NOT NULL COMMENT '商品状态（0-未上架，1-已上架，2-缺货）',
  PRIMARY KEY (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品表';

-- 创建商品类别表
CREATE TABLE `product_type` (
  `product_type_id` VARCHAR(32) NOT NULL COMMENT '商品类别编号',
  `parent_id` VARCHAR(32) NOT NULL COMMENT '父编号',
  `product_type_name` varchar(20) NOT NULL COMMENT '商品类别名称',
  `is_Delete` int(11) NOT NULL DEFAULT '1' COMMENT '是否可用,默认可用',
  `context` varchar(50) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`product_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品类别表';

-- 创建院系信息表
CREATE TABLE `school_info` (
  `school_info_id` VARCHAR(32) NOT NULL COMMENT '校内信息编号',
  `code` varchar(20) DEFAULT NULL COMMENT '院系编码',
  `name` varchar(20) DEFAULT NULL COMMENT '院系名称',
  `p_code` varchar(20) DEFAULT NULL COMMENT '父级编码',
  `level` int(11) DEFAULT '0' COMMENT '级别',
  PRIMARY KEY (`school_info_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='院系信息表';

-- 创建用户详细信息表
CREATE TABLE `user_detail_info` (
  `user_detail_id` VARCHAR(32) NOT NULL COMMENT '用户详细信息编号',
  `user_tel` varchar(13) DEFAULT NULL COMMENT '用户电话号',
  `user_age` int(11) NOT NULL DEFAULT '0' COMMENT '用户年龄',
  `school_info_id` int(11) DEFAULT NULL COMMENT '校内信息编号',
  `user_class` varchar(10) DEFAULT NULL COMMENT '学生班级',
  `user_grade` varchar(4) DEFAULT NULL COMMENT '学生年级',
  `user_Id` varchar(60) NOT NULL COMMENT '用户信息编号',
  PRIMARY KEY (`user_detail_id`),
  UNIQUE KEY `user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户详细信息表';

-- 创建区域地址表
CREATE TABLE `regions` (
  `id` VARCHAR(32) NOT NULL,
  `code` int(50) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `p_code` int(50) DEFAULT NULL,
  `level` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='区域地址表';

-- 创建基本配置表
CREATE TABLE BASIC_CONFIG
(
   ID                   VARCHAR(32) NOT NULL,
   BASIC_CONFIG_ID      VARCHAR(50) NOT NULL,
   NAME                 VARCHAR(50) COMMENT '名称',
   VALUE                VARCHAR(400) COMMENT '值',
   PRIMARY KEY (ID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='基本信息配置表';


ALTER TABLE favorite ADD CONSTRAINT `fk_favorite_productId` FOREIGN KEY (`product_id`) REFERENCES `product_info` (`product_id`) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE order_detail ADD CONSTRAINT `fk_orderDetail_orderMainId` FOREIGN KEY (`order_main_id`) REFERENCES `order_main` (`order_main_id`) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE order_detail ADD CONSTRAINT `fk_orderDetail_productId` FOREIGN KEY (`product_id`) REFERENCES `product_info` (`product_id`) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE product_info ADD CONSTRAINT `fk_procuctInfo_productTypeId` FOREIGN KEY (`product_type_id`) REFERENCES `product_type` (`product_type_id`) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE user_detail_info ADD CONSTRAINT `fk_userDetailInfo_schoolInfoId` FOREIGN KEY (`school_info_id`) REFERENCES `school_info` (`school_info_id`) ON DELETE RESTRICT ON UPDATE RESTRICT;
