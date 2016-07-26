CREATE TABLE `favorite` (
  `favoriteId` int(11) NOT NULL AUTO_INCREMENT COMMENT '�ղؼб��',
  `productId` int(11) NOT NULL COMMENT '��Ʒ���',
  `userId` varchar(60) NOT NULL COMMENT '�û����',
  `createDate` date NOT NULL COMMENT '�ղ�ʱ��',
  `context` varchar(50) DEFAULT NULL COMMENT '��ע',
  PRIMARY KEY (`favoriteId`),
  UNIQUE KEY `userId` (`userId`),
  KEY `fk_favorite_productId` (`productId`),
  CONSTRAINT `fk_favorite_productId` FOREIGN KEY (`productId`) REFERENCES `productinfo` (`productId`),
  CONSTRAINT `fk_favorite_userId` FOREIGN KEY (`userId`) REFERENCES `userinfo` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `orderdetail` (
  `orderDetailId` int(11) NOT NULL AUTO_INCREMENT COMMENT '������ϸ����',
  `orderMainId` int(11) NOT NULL COMMENT '����������',
  `productId` int(11) NOT NULL COMMENT '��Ʒ���',
  `num` int(11) NOT NULL COMMENT '��������',
  `sumPrice` float DEFAULT NULL COMMENT '������Ʒ�ܼ�',
  PRIMARY KEY (`orderDetailId`),
  KEY `fk_orderDetail_orderMainId` (`orderMainId`),
  KEY `fk_orderDetail_productId` (`productId`),
  CONSTRAINT `fk_orderDetail_orderMainId` FOREIGN KEY (`orderMainId`) REFERENCES `ordermain` (`orderMainId`),
  CONSTRAINT `fk_orderDetail_productId` FOREIGN KEY (`productId`) REFERENCES `productinfo` (`productId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `ordermain` (
  `orderMainId` int(11) NOT NULL AUTO_INCREMENT COMMENT '����������',
  `userId` varchar(60) NOT NULL COMMENT '�û����',
  `state` int(11) DEFAULT NULL COMMENT '���۵�״̬��0-δ����1-�Ѵ���2-�Է�����3-���ջ���',
  `buyDate` date DEFAULT NULL COMMENT '��������',
  `payDate` date DEFAULT NULL COMMENT '��������',
  `confirmDate` date DEFAULT NULL COMMENT 'ȷ������',
  `sumPrice` float DEFAULT '0' COMMENT '�ܼ�',
  `context` varchar(50) DEFAULT NULL COMMENT '��ע',
  PRIMARY KEY (`orderMainId`),
  UNIQUE KEY `userId` (`userId`),
  CONSTRAINT `fk_orderMain_userId` FOREIGN KEY (`userId`) REFERENCES `userinfo` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `productinfo` (
  `productId` int(11) NOT NULL AUTO_INCREMENT COMMENT '��Ʒ���',
  `productName` varchar(20) NOT NULL COMMENT '��Ʒ����',
  `productTypeId` int(11) NOT NULL COMMENT '��Ʒ�����',
  `brand` varchar(20) DEFAULT NULL COMMENT '��ƷƷ��',
  `context` text NOT NULL COMMENT '��Ʒ����',
  `imgs` text NOT NULL COMMENT '��ƷͼƬ',
  `price` float NOT NULL COMMENT '��Ʒ�۸�',
  `pbdate` date NOT NULL COMMENT '��������',
  `state` int(11) NOT NULL COMMENT '��Ʒ״̬',
  PRIMARY KEY (`productId`),
  KEY `fk_procuctInfo_productTypeId` (`productTypeId`),
  CONSTRAINT `fk_procuctInfo_productTypeId` FOREIGN KEY (`productTypeId`) REFERENCES `producttype` (`productTypeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `producttype` (
  `productTypeId` int(11) NOT NULL AUTO_INCREMENT COMMENT '��Ʒ�����',
  `parentId` int(11) NOT NULL COMMENT '�����',
  `productTypeName` varchar(20) NOT NULL COMMENT '��Ʒ�������',
  `isDelete` int(11) NOT NULL DEFAULT '1' COMMENT '�Ƿ����,Ĭ�Ͽ���',
  `context` varchar(50) DEFAULT NULL COMMENT '��ע',
  PRIMARY KEY (`productTypeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `schoolinfo` (
  `schoolInfoId` int(11) NOT NULL AUTO_INCREMENT COMMENT 'У����Ϣ���',
  `college` varchar(20) DEFAULT NULL COMMENT 'Ժ',
  `department` varchar(20) DEFAULT NULL COMMENT 'ϵ',
  `classes` varchar(2) DEFAULT NULL COMMENT '��',
  `grade` varchar(10) DEFAULT NULL COMMENT '��',
  PRIMARY KEY (`schoolInfoId`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

CREATE TABLE `userdetailinfo` (
  `userDetailId` int(11) NOT NULL AUTO_INCREMENT COMMENT '�û���ϸ��Ϣ���',
  `userTel` varchar(13) DEFAULT NULL COMMENT '�û��绰��',
  `userCity` varchar(20) DEFAULT NULL COMMENT '�û�����',
  `userProvince` varchar(20) DEFAULT NULL COMMENT '�û�ʡ��',
  `userLanguage` varchar(20) DEFAULT NULL COMMENT '΢��ʹ������',
  `userGender` varchar(1) DEFAULT '0' COMMENT '�û��Ա�',
  `userAge` int(11) NOT NULL DEFAULT '0' COMMENT '�û�����',
  `schoolInfoId` int(11) DEFAULT NULL COMMENT 'У����Ϣ���',
  `userId` varchar(60) NOT NULL COMMENT '�û���Ϣ���',
  PRIMARY KEY (`userDetailId`),
  UNIQUE KEY `userId` (`userId`),
  UNIQUE KEY `schoolInfoId` (`schoolInfoId`),
  CONSTRAINT `fk_userDetailInfo_schoolInfoId` FOREIGN KEY (`schoolInfoId`) REFERENCES `schoolinfo` (`schoolInfoId`),
  CONSTRAINT `fk_userDetailInfo_userId` FOREIGN KEY (`userId`) REFERENCES `userinfo` (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

CREATE TABLE `userinfo` (
  `userId` varchar(60) NOT NULL COMMENT '�û����',
  `userNickName` varchar(20) NOT NULL COMMENT '�û��ǳ�',
  `userHeadImgUrl` varchar(300) NOT NULL COMMENT '�û�ͷ��',
  PRIMARY KEY (`userId`),
  UNIQUE KEY `userId` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE BASIC_CONFIG
(
   ID                   VARCHAR(32) NOT NULL,
   BASIC_CONFIG_ID      VARCHAR(50) NOT NULL,
   NAME                 VARCHAR(50) COMMENT '名称',
   VALUE                VARCHAR(100) COMMENT '值',
   PRIMARY KEY (ID)
);

ALTER TABLE BASIC_CONFIG COMMENT '基本信息配置表';