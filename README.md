
## 简介

[![License](https://img.shields.io/badge/license-Apache%202-4EB1BA.svg)](https://www.apache.org/licenses/LICENSE-2.0.html)
[![Build Status](https://api.travis-ci.com/LianjiaTech/retrofit-spring-boot-starter.svg?branch=master)](https://travis-ci.com/github/LianjiaTech/retrofit-spring-boot-starter)
[![Maven central](https://maven-badges.herokuapp.com/maven-central/com.github.lianjiatech/retrofit-spring-boot-starter/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.github.lianjiatech/retrofit-spring-boot-starter)
[![GitHub release](https://img.shields.io/github/v/release/lianjiatech/retrofit-spring-boot-starter.svg)](https://github.com/LianjiaTech/retrofit-spring-boot-starter/releases)
[![License](https://img.shields.io/badge/JDK-1.8+-4EB1BA.svg)](https://docs.oracle.com/javase/8/docs/index.html)
[![License](https://img.shields.io/badge/SpringBoot-1.x+-green.svg)](https://docs.spring.io/spring-boot/docs/2.1.5.RELEASE/reference/htmlsingle/)

work-tool项目主要是工作中脚手架或工具的汇总

🚀项目持续优化迭代，欢迎大家提ISSUE和PR！麻烦大家能给一颗star✨，您的star是我们持续更新的动力！

## 子模块介绍
- [x] [web服务脚手架：springboot-web-base](#web服务脚手架：springboot-web-base)

### web服务脚手架：springboot-web-base
简介：
- 结合idea实现web快速开发
- swagger实现接口文档
- retrofit网络请求快速开发
- 业务代码快速生成
- 简化对象转换
- 日志增加链路追踪ID，并且支持动态修改日志级别

技术桟介绍：
- springboot
- swagger
- retrofit(使用了开源框架：[retrofit-spring-boot-starter](https://github.com/lianjiatech/retrofit-spring-boot-starter))
- mapstruct(用于对象间的转换)
- mybatis-plus
- easyCode（代码生成，项目doc目录下包含相关模板，定义创建数据库后，快速生产业务代码）
- redis
- druid

使用说明：
1. 拉取代码，修改包名
2. 定义和创建数据库
3. idea配置数据库和easyCode插件
4. 配置文件配置数据库等信息
5. 使用easyCode插件生成业务层