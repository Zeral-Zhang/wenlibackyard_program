# 文理后院代码
[![GitHub Stars](https://img.shields.io/github/stars/IgorAntun/node-chat.svg)](https://github.com/Zeral-Zhang/wenlibackyard_program/stargazers) [![GitHub Issues](https://img.shields.io/github/issues/IgorAntun/node-chat.svg)](https://github.com/Zeral-Zhang/wenlibackyard_program/issues) [![Current Version](https://img.shields.io/badge/version-1.0.7-green.svg)](https://github.com/Zeral-Zhang/wenlibackyard_program/node-chat) [![Live Demo](https://img.shields.io/badge/demo-online-green.svg)](https://igorantun.com/chat) " [![Gitter](https://badges.gitter.im/Join%20Chat.svg)](https://gitter.im/IgorAntun/node-chat?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge)

这是一个使用JavaSSH做为后端框架的Maven项目，项目设计被用来作为微信公众号版的一个校园二手购物网站，旨在给学生提供平台来进行旧物交易，或者二手物品转手操作，现在项目还处于完善阶段。

![Chat Preview](http://i.imgur.com/lgRe8z4.png)

## Demo
你可以使用该链接访问网页独立版的文理后院，建议使用手机访问。 ~~https://zeral.site/wenlibackyard~~ *(demo 现在不可用)*
- 微信测试号暂时不可用，项目处于维护期

---

## 特性
- Maven 项目，企业级开发标准
- 自动建表，项目上线升级功能
- SSH后端框架，全注解开发
- Maven插件，自动发布远程项目
- 数据库连接池，eachche缓存，前台图片优化
- 移动先行，响应式前台设计
- 更多特性，请自行下载查看

# 文理后院二手购物平台

一个不断升级中的JavaSSH购物平台网站

## 项目搭建

下面将介绍如何搭建该项目，使项目能够正常发布和运行

### 环境要求

请确认已经安装了下面工具：

* JDK1.5及以上
* Maven
* Tomcat7及以上
* Mysql数据库


### 安装

下面将介绍如何使用MyEclipse IDE搭建项目：

1. 从Git导入项目：
![image](http://img.hoop8.com/1608B/jaQASMTi.png)
![image](http://img.hoop8.com/1608B/rdauaUkp.png)
2. 请选择主分支：
![image](http://img.hoop8.com/1608B/t5yCqvZB.png)
3. 请确保选择该项目文件：
![image](http://img.hoop8.com/1608B/9Ynwnp0j.png)
4. 将项目转化为Maven项目，右键项目->configure->convert to Maven Project
5. 等待项目下载Jar包完成；
6. 使用c3p0数据库连接池配置，请在tomcat/context.xml 中添加，具体用户名和密码请自行替换： 
`<Resource acquireIncrement="10" 
		auth="Container" driverClass="com.mysql.jdbc.Driver" factory="org.apache.naming.factory.BeanFactory" 
		jdbcUrl="jdbc:mysql://localhost:3306/wenlibackyard?autoReconnect=false&amp;useUnicode=true&amp;characterEncoding=utf8" 
		maxIdleTime="25000" maxPoolSize="300" minPoolSize="5" name="jdbc/ZeralDS" 
		password="root" type="com.mchange.v2.c3p0.ComboPooledDataSource" user="root"/>`
7. Mysql中新建数据库：wenlibackyard，charSet:utf-8
8. 启动项目

```
启动项目时请确保调整tomcat启动时间限制
```


## 使用Maven测试，发布项目

项目介绍至此结束，如有如何疑问，请提交

## 如何贡献

请阅读 [CONTRIBUTING.md](CONTRIBUTING.md) 查看咨询代码详情, 然后给我发送pull request请求。

## 版本

我们使用 [SemVer](http://semver.org/) 进行版本控制。查看具体版本， 请查看 [tags on this repository](https://github.com/Zeral-Zhang/wenlibackyard_program/tags). 

## 作者

* **Zeral** - *Initial work* - [PurpleBooth](https://zeral.site)

请查看该列表 [contributors](https://github.com/Zeral-Zhang/wenlibackyard_program/contributors) 参与该项目人员。

## License

该项目遵守 MIT License - 查看 [LICENSE.md](LICENSE.md) 查看详情
