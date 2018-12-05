Baobei ![alt tag](https://api.travis-ci.org/phishman3579/java-algorithms-implementation.svg?branch=master)
==============================

This is spring cloud micro server project. 这是一个Spring Cloud微服务项目，该项目技术点主要有：Spring Cloud微服务模式，服务之间调用，Hystrix熔断器，Mybatis代码生成器，Elasticsearch大数据检索分析，多数据源连接，Excel通用导入导出及单元格合并功能，Maven聚合项目。(后续会引入Mybatis Plus工具，加速单表开发效率)

## Table of Contents
+ [Environment](https://github.com/loveisontheway/baobei#Environment)
+ [Start](https://github.com/loveisontheway/baobei#Start)
+ [Project](https://github.com/loveisontheway/baobei#Project)
+ [Multi-datasource](https://github.com/loveisontheway/baobei#Multi-datasource)
+ [CodeGenerator](https://github.com/loveisontheway/baobei#CodeGenerator)

## Environment
+ `JDK:` 1.8+
+ `Tomcat:` 8.5.x
+ `Spring Boot:` 2.1.0
+ `Spring Cloud:` Finchley.SR2
+ `Elasticsearch:` 6.4.2

## Start
项目启动顺序:
1. 先启动 `baobei-register` (注册中心)，访问地址：http://localhost:8085/.

2. 然后再依次启动其它服务模块项目
  + baobei-article
  + baobei-base
  + baobei-cache
  + baobei-child
  + baobei-order

## Project
| name | description |
| :------ | :------ |
| baobei-article | 活动服务模块 |
| baobei-base | 基础服务模块 |
| baobei-cache | 缓存服务模块(Elasticsearch) |
| baobei-child | 儿童服务模块(服务之间调用,调用baobei-cache接口，加入熔断器) |
| baobei-order | 订单服务模块 |
| baobei-register | 注册中心 |

## Multi-datasource
多数据源实现，可以直接参考`baobei-child`项目

## CodeGenerator
在test包下，`CodeGenerator`类是MyBatis Generator自动生成代码器，直接运行Main方法即可。
```java
public static void main(String[] args) {
        /**
         * 单表名
         * 元素1: 表名 [不可为空，与MySQL表名保持一致]
         * 元素2: 自定义名称 [""或null 表示直接引用'元素1']
         * 元素3: 类注释描述 [""或null 表示无注释]
         * 元素4: 主键数据类型 [不可为空，默认Integer]
         */
        String[][] arrNames = {{"user", "", "用户", "String"}};
        // 多表名
/*        String[][] arrNames = {
                {"help_topic", "", "主题", "Integer"},
                {"func", "", "函数", "String"},
                {"plugin", "", "插件", "String"},
                {"tables_priv", "", "表", "String"}
        };*/
        genCode(arrNames);
    }
```
