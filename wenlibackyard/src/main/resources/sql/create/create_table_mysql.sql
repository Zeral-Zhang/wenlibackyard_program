CREATE TABLE `favorite` (
  `favoriteId` int(11) NOT NULL AUTO_INCREMENT COMMENT '收藏夹编号',
  `productId` int(11) NOT NULL COMMENT '商品编号',
  `userId` varchar(60) NOT NULL COMMENT '用户编号',
  `createDate` date NOT NULL COMMENT '收藏时间',
  `context` varchar(50) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`favoriteId`),
  UNIQUE KEY `userId` (`userId`)
);

CREATE TABLE `orderdetail` (
  `orderDetailId` int(11) NOT NULL AUTO_INCREMENT COMMENT '订单明细表编号',
  `orderMainId` int(11) NOT NULL COMMENT '订单主表编号',
  `productId` int(11) NOT NULL COMMENT '商品编号',
  `num` int(11) NOT NULL COMMENT '订购数量',
  `sumPrice` float DEFAULT NULL COMMENT '单个商品总价',
  PRIMARY KEY (`orderDetailId`)
);

CREATE TABLE `ordermain` (
  `orderMainId` int(11) NOT NULL AUTO_INCREMENT COMMENT '订单主表编号',
  `userId` varchar(60) NOT NULL COMMENT '用户编号',
  `state` int(11) DEFAULT NULL COMMENT '销售单状态（0-未处理，1-已处理，2-以发货，3-已收货）',
  `buyDate` date DEFAULT NULL COMMENT '购买日期',
  `payDate` date DEFAULT NULL COMMENT '付款日期',
  `confirmDate` date DEFAULT NULL COMMENT '确认日期',
  `sumPrice` float DEFAULT '0' COMMENT '总价',
  `context` varchar(50) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`orderMainId`),
  UNIQUE KEY `userId` (`userId`)
);

CREATE TABLE `productinfo` (
  `productId` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品编号',
  `productName` varchar(20) NOT NULL COMMENT '商品名称',
  `productTypeId` int(11) NOT NULL COMMENT '商品类别编号',
  `brand` varchar(20) DEFAULT NULL COMMENT '商品品牌',
  `context` text NOT NULL COMMENT '商品描述',
  `imgs` text NOT NULL COMMENT '商品图片',
  `price` float NOT NULL COMMENT '商品价格',
  `pbdate` date NOT NULL COMMENT '发布日期',
  `state` int(11) NOT NULL COMMENT '商品状态',
  PRIMARY KEY (`productId`)
);

CREATE TABLE `producttype` (
  `productTypeId` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品类别编号',
  `parentId` int(11) NOT NULL COMMENT '父编号',
  `productTypeName` varchar(20) NOT NULL COMMENT '商品类别名称',
  `isDelete` int(11) NOT NULL DEFAULT '1' COMMENT '是否可用,默认可用',
  `context` varchar(50) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`productTypeId`)
);

CREATE TABLE `schoolinfo` (
  `schoolInfoId` int(11) NOT NULL AUTO_INCREMENT COMMENT '校内信息编号',
  `college` varchar(20) DEFAULT NULL COMMENT '院',
  `department` varchar(20) DEFAULT NULL COMMENT '系',
  `classes` varchar(2) DEFAULT NULL COMMENT '班',
  `grade` varchar(10) DEFAULT NULL COMMENT '级',
  PRIMARY KEY (`schoolInfoId`)
);

CREATE TABLE `userdetailinfo` (
  `userDetailId` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户详细信息编号',
  `userTel` varchar(13) DEFAULT NULL COMMENT '用户电话号',
  `userCity` varchar(20) DEFAULT NULL COMMENT '用户城市',
  `userProvince` varchar(20) DEFAULT NULL COMMENT '用户省份',
  `userLanguage` varchar(20) DEFAULT NULL COMMENT '微信使用语言',
  `userGender` varchar(1) DEFAULT '0' COMMENT '用户性别',
  `userAge` int(11) NOT NULL DEFAULT '0' COMMENT '用户年龄',
  `schoolInfoId` int(11) DEFAULT NULL COMMENT '校内信息编号',
  `userId` varchar(60) NOT NULL COMMENT '用户信息编号',
  PRIMARY KEY (`userDetailId`),
  UNIQUE KEY `userId` (`userId`),
  UNIQUE KEY `schoolInfoId` (`schoolInfoId`)
);

CREATE TABLE `userinfo` (
  `userId` varchar(60) NOT NULL COMMENT '用户编号',
  `userNickName` varchar(20) NOT NULL COMMENT '用户昵称',
  `userHeadImgUrl` varchar(500) NOT NULL COMMENT '用户头像',
  PRIMARY KEY (`userId`),
  UNIQUE KEY `userId` (`userId`)
);

CREATE TABLE BASIC_CONFIG
(
   ID                   VARCHAR(32) NOT NULL,
   BASIC_CONFIG_ID      VARCHAR(50) NOT NULL,
   NAME                 VARCHAR(50) COMMENT '名称',
   VALUE                VARCHAR(100) COMMENT '值',
   PRIMARY KEY (ID)
);

ALTER TABLE BASIC_CONFIG COMMENT '基本信息配置表';
ALTER TABLE favorite ADD CONSTRAINT `fk_favorite_productId` FOREIGN KEY (`productId`) REFERENCES `productinfo` (`productId`) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE favorite ADD CONSTRAINT `fk_favorite_userId` FOREIGN KEY (`userId`) REFERENCES `userinfo` (`userId`) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE orderdetail ADD CONSTRAINT `fk_orderDetail_orderMainId` FOREIGN KEY (`orderMainId`) REFERENCES `ordermain` (`orderMainId`) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE orderdetail ADD CONSTRAINT `fk_orderDetail_productId` FOREIGN KEY (`productId`) REFERENCES `productinfo` (`productId`) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE ordermain ADD CONSTRAINT `fk_orderMain_userId` FOREIGN KEY (`userId`) REFERENCES `userinfo` (`userId`) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE productinfo ADD CONSTRAINT `fk_procuctInfo_productTypeId` FOREIGN KEY (`productTypeId`) REFERENCES `producttype` (`productTypeId`) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE userdetailinfo ADD CONSTRAINT `fk_userDetailInfo_schoolInfoId` FOREIGN KEY (`schoolInfoId`) REFERENCES `schoolinfo` (`schoolInfoId`) ON DELETE RESTRICT ON UPDATE RESTRICT;
ALTER TABLE userdetailinfo ADD CONSTRAINT `fk_userDetailInfo_userId` FOREIGN KEY (`userId`) REFERENCES `userinfo` (`userId`) ON DELETE RESTRICT ON UPDATE RESTRICT;
