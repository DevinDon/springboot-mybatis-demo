# Spring Boot + MyBatis 入门系列（一）： 优雅的配置方案

初学 MyBatis 感到无从下手？

面对一坨坨的配置文件无所适从？

翻阅教程发现完全看不懂？

没关系，有这几篇文章就够了。

跟着我，一步一步教你如何驯服放荡不羁的 MyBatis。

# 基础概念

在阅读这篇文章之前，你需要对一些基础概念有所了解。

## 1. [Java SE & Java EE](http://www.runoob.com/java/java-intro.html)

   Java SE 是我们常用的 Java 环境，包括 JVM 虚拟机以及 JDK 开发套件。

   而 Java EE 则是一套常用于企业应用的**标准**，需要各企业自行实现。

## 2. [What is ORM](https://zhuanlan.zhihu.com/p/27188788)?

   ORM 即 Object Relational Mapping，对象关系映射。

   简单理解，将*数据库数据库中的**表和字段***映射为*面向对象语言中的**对象***。

## 3. [What is Maven](http://www.runoob.com/maven/maven-tutorial.html)?

   Maven 是一个项目管理工具，可以对 Java 项目进行构建、依赖管理。

   有了它，不需要再将各种依赖包复制来复制去。只需要连接网络，所有事情它都会帮你处理。

## 4. [What is Spring Boot](https://jimmysong.io/posts/spring-boot-quick-start-guide/)?

   Spring Boot 是一套 Java 开发框架，使用注解代替了繁琐的 XML 配置文件。

## 5. [What is MyBatis](http://www.mybatis.org/mybatis-3/zh/index.html)?

   MyBatis 是一款优秀的持久层框架，它支持定制化 SQL、存储过程以及高级映射。

   MyBatis 使用简单的 XML 或注解来配置和映射原生信息，将接口和 Java 的 POJOs (Plain Old Java Objects) 映射成数据库中的记录。

## 6. [What is MySQL](http://www.runoob.com/mysql/mysql-tutorial.html)?

   MySQL 是最流行的关系型数据库管理系统。嗯，就这么多。

## 7. Anything else?

   以下为可选了解：

- [Git](https://cn.bing.com/search?q=Git) 源代码管理
- [OpenJDK](https://cn.bing.com/search?q=OpenJDK) 开源版本的 JDK
- [VSCode](https://cn.bing.com/search?q=VSCode) 社区最流行的开源编辑器<del>之一</del>

# 环境配置

## 1. 安装 JDK

1. 下载

   打开 [Oracle JDK](https://www.oracle.com/technetwork/java/javase/downloads/jdk11-downloads-5066655.html) 下载页面，根据操作系统选择对应版本的 JDK 下载。

2. 安装

3. 测试安装是否成功

   打开终端（Windows：`CMD`  或 `Powershell`，MAC：`Shell`）输入 `java -version`，如果有如下输出：

   ```shell
   C:\>java -version
   openjdk version "11.0.2-redhat" 2019-01-15 LTS
   OpenJDK Runtime Environment (build 11.0.2-redhat+7-LTS)
   OpenJDK 64-Bit Server VM (build 11.0.2-redhat+7-LTS, mixed mode)
   ```

   则表明安装成功。

   > 详细方法可参阅 [RUNNOOB](www.runnoob.com) 的 [Java 安装教程](http://www.runoob.com/java/java-environment-setup.html)。

## 2. 安装 Maven

1. 下载

   打开 [Maven Project](https://maven.apache.org/download.cgi) 下载页面，选择对应版本的 Maven 下载。

2. 安装

 详细方法可参阅 [RUNNOOB](www.runnoob.com) 的 [Maven 安装教程](http://www.runoob.com/maven/maven-setup.html)。

## 3. 安装 VSCode

1. 下载

   打开 [VSCode](https://code.visualstudio.com/download) 下载页面，选择对应版本的 VSCode 下载。

2. 安装

3. 安装基础插件

   点击左侧的插件选项卡，搜索 `Chinese` 安装中文语言包：

   ![VSCode Install Plugin Chinese](https://raw.githubusercontent.com/DevinDon/springboot-mybatis-demo/master/docs/image/VSCode-Install-Plugin-Chinese.png)

   搜索 `Java` 安装 `Java Extension Pack` 开发环境：

   ![VSCode Install Plugin Java](https://raw.githubusercontent.com/DevinDon/springboot-mybatis-demo/master/docs/image/VSCode-Install-Plugin-Java.png)



## 4. 安装或使用数据库

1. MySQL

   本机安装 MySQL 请参阅 [RUNNOOB](www.runnoob.com) 的 [MySQL 安装教程](http://www.runoob.com/mysql/mysql-install.html)。

   或使用如下公共数据库配置

   ```properties
   spring.datasource.url=jdbc:mysql://cdb-mnalfxb8.bj.tencentcdb.com:10018/database
   spring.datasource.username=database
   spring.datasource.password=database
   spring.datasource.driver-class-name=com.mysql.jdbc.Driver
   ```

2. 安装 Navicat 数据库管理软件

   下载地址：[简体中文](https://www.navicat.com.cn) | [繁体中文](https://navicat.com/cht)

# 正片开始

## 1. 使用 Spring Initializr 创建项目

**本教程所使用的包名为：com.example.demo**

### 1. 打开 [Spring Initializr](https://start.spring.io/)

![Spring Initializr](https://raw.githubusercontent.com/DevinDon/springboot-mybatis-demo/master/docs/image/Spring-Initializr.png)

属性详解：

1. Project：项目使用 Maven 还是 Gradle 进行管理
2. Language：编程语言，Java、Kotlin 或 Groovy
3. Spring Boot：Spring Boot 版本，选择最新无后缀的版本
4. Project Metadata：项目信息
   1. Group：你的项目组名称，通常以倒置网址命名（唯一性）
   2. Artifact：项目 ID，用于在 Maven 网络中定位你的项目
   3. Name：项目名称，用于显示
   4. Description：项目描述
   5. Package Name：项目包名，自动生成
   6. Packaging：打包时的格式，Jar 或 War
   7. Java Version：Java 版本，根据本地版本自行选择

### 2. 添加依赖包

![Spring Initializr Package](https://raw.githubusercontent.com/DevinDon/springboot-mybatis-demo/master/docs/image/Spring-Initializr-Package.png)

在下面的搜索框输入 MyBatis 和 MySQL，将其加入依赖。

### 3. 构建并下载

点击页面下方的 `Generate Project` 按钮，将打包好的项目下载到本地并解压。

### 4. 操作预览

![Create New Spring Boot Project](https://raw.githubusercontent.com/DevinDon/springboot-mybatis-demo/master/docs/image/Create-New-Spring-Boot-Project.gif)

## 2. 使用 VSCode 打开项目

打开项目目录，右键使用 VSCode 打开 <small>Open with Code</small>。

![Open with VSCode](https://code.aliyun.com/iinfinity/api/raw/master/Open%20with%20VSCode.png)

### 1. 在数据库中创建表

1. 打开 Navicat 并创建新连接（本文将以提供的公共数据库为例，使用 `database` 数据库）。

   ![Create New Connection](https://raw.githubusercontent.com/DevinDon/springboot-mybatis-demo/master/docs/image/Create-New-Connection.png)

2. 新建查询，执行如下代码：

   ```sql
   CREATE TABLE `user` (
   `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
   `name` char(64) NOT NULL,
   `email` char(64) NOT NULL,
   PRIMARY KEY (`id`)
   ) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8
   ```

   成功后将会在数据库中创建一个名为 `name` 的表，如图：

   ![Create Table Named user](https://raw.githubusercontent.com/DevinDon/springboot-mybatis-demo/master/docs/image/Create-Table-Named-user.png)

至此，数据库已经准备就绪了。

### 2. 配置数据库连接

文件位置：`src/main/resources/application.properties`

按照如下代码的格式，<span style="color: red;">**将等号后的内容替换为你的项目信息**</span>：

```properties
mybatis.type-aliases-package=com.example.demo # 填入你的包名

spring.datasource.url=jdbc:mysql://cdb-mnalfxb8.bj.tencentcdb.com:10018/database # 数据库 URL 地址，将你的数据库信息填入
# 简单格式为：jdbc:数据库类型://数据库主机地址:端口/库名
spring.datasource.username=database # 数据库用户名
spring.datasource.password=database # 数据库密码
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver # JDBC 驱动
```

`#` 号后的内容是注释，可以删除。

这些配置将会使：

1. Spring Boot 自动加载数据库配置，连接至数据库；
2. DataSource 自动注入到 SqlSessionFactory 中；
3. SqlSessionFactory 自动映射 Mapper；

至此，我们已经能够连接至数据库了。

### 3. 使用四层结构编写代码

在 Java 框架中，我们常用四层结构 <small>`Entity, Mapper, Service & Controller`</small> 来进行分层解耦。

这四层结构分别是：

1. Entity / Model

   实体层 / 模型层，存放实体类，用于描述数据库结构。

2. Mapper / Data access object

   映射 / 持久访问层，对数据库进行持久化操作，将基础的增删改查操作与代码一一映射。

3. Service

   业务层，将 Mapper 中的基础操作封装为高阶业务方法，以便在 Controller 层中调用。

4. Controller / Web

   控制层，接收前端数据并调用 Service 进行处理，将处理后的数据返回前端。

在本教程中，我们只需要编写 `Entity` 以及 `Mapper` 即可。

#### 1. 建立实体 `Entity`

1. 首先在包目录 <small>`src/main/java/com/example/demo`</small> 下创建一个名为 `entity` 的文件夹；

2. 在 `entity` 文件夹下新建一个名为 `UserEntity.java` 的文件；

3. 将数据库转换为 Java 对象，代码如下：

   ```java
   package com.example.demo.entity;

   public class UserEntity {

     private int id;

     private String name;

     private String email;

     public UserEntity() {
       this(0, null, null);
     }

     public UserEntity(String name, String email) {
       this(0, name, email);
     }

     public UserEntity(int id, String name, String email) {
       this.id = id;
       this.name = name;
       this.email = email;
     }

     /**
      * @return the id
      */
     public int getId() {
       return id;
     }

     /**
      * @param id the id to set
      */
     public void setId(int id) {
       this.id = id;
     }

     /**
      * @return the name
      */
     public String getName() {
       return name;
     }

     /**
      * @param name the name to set
      */
     public void setName(String name) {
       this.name = name;
     }

     /**
      * @return the email
      */
     public String getEmail() {
       return email;
     }

     /**
      * @param email the email to set
      */
     public void setEmail(String email) {
       this.email = email;
     }

     @Override
     public String toString() {
       return "Name: " + this.name + "/nEmail: " + this.email + "/n";
     }

   }
   ```

   该类 <small>`UserEntity`</small> 使用 Java 代码描述了数据库中 `user` 表的结构。

4. 最终结果如下图所示：

   ![Add User Entity](https://raw.githubusercontent.com/DevinDon/springboot-mybatis-demo/master/docs/image/Add-User-Entity.png)

#### 2. 建立映射 Mapper

1. 首先在包目录 <small>`src/main/java/com/example/demo`</small> 下创建一个名为 `mapper` 的文件夹；

2. 在 `mapper` 文件夹下新建一个名为 `UserMapper.java` 的文件；

3. 在本教程中，我们只映射三个基本操作：

   - `insertOne(user)` 新增一条数据
   - `selectAll()` 查询所有数据
   - `deleteAll()` 删除所有数据

   向该接口 <small>`UserMapper`</small> 中添加这三个基本操作：

   ```java
   package com.example.demo.mapper;

   import java.util.List;

   import com.example.demo.entity.UserEntity;

   import org.apache.ibatis.annotations.Delete;
   import org.apache.ibatis.annotations.Insert;
   import org.apache.ibatis.annotations.Select;

   public interface UserMapper {

     /**
      * Insert one user.
      *
      * @param user {User} User information.
      * @return {void} Nothing.
      */
     @Insert("INSERT INTO `database`.`user`(`name`, `email`) VALUES (#{name}, #{email})")
     public void insertOne(UserEntity user);

     /**
      * Delete all users.
      *
      * @return {void} Nothing.
      */
     @Delete("TRUNCATE `user`")
     public void deleteAll();

     /**
      * Select all users.
      *
      * @return {List<UserEntity>} All users.
      */
     @Select("SELECT * FROM `user`")
     public List<UserEntity> selectAll();

   }
   ```

   Mapper 均为接口 <small>`interface`</small> ，使用注解标注操作语句，由 Spring 自动生成方法。

4. 最终结果如下所示：

   ![Add User Mapper](https://raw.githubusercontent.com/DevinDon/springboot-mybatis-demo/master/docs/image/Add-User-Mapper.png)

#### 3. 添加 Mapper Scan

我们需要为 Spring 指定 mapper 目录，来使 Spring 自动扫描并生成 Mapper 映射操作。

为入口类 <small>`src/main/java/com/example/demo/DemoApplication.java`</small> 添加 `MapperScan` 注解：

```java
package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.demo.mapper") // 填入你的包名.mapper，即 mapper 接口所在的包
public class DemoApplication {

  public static void main(String[] args) {
    SpringApplication.run(DemoApplication.class, args);
  }

}
```

现在，Spring 将会自动扫描并注册 mapper 映射器。

### 4. 测试 Mapper

在配置完成之后，我们需要测试数据库是否可以正常连接使用。

1. 打开测试类 <small>`DemoApplicationTests`</small> ，位于 `src/test/java/com/example/demo/DemoApplicationTests.java` ；

2. 在该测试类 <small>`DemoApplicationTests`</small> 中添加一个带有 `Autowired` 注解的 `UserMapper` 成员，如下所示：

   ```java
   @Autowired
   private UserMapper userMapper;
   ```

   Spring 会根据 `UserMapper` 接口的注解自动生成映射操作。

3. 为 `UserMapper` 的映射操作添加测试：

   ```java
   @Test
   public void allInOne() {
     userMapper.deleteAll(); // 删除所有记录
     Assert.assertEquals(1, userMapper.selectAll().size()); // 数据表 `user` 应为空
     UserEntity user = new UserEntity("test", "test@test.com");
     userMapper.insertOne(user); // 添加一条新纪录
     UserEntity checkUser = userMapper.selectAll().get(0); // 查询记录
     Assert.assertNotNull(checkUser); // 该记录应当存在（不为空）
     Assert.assertEquals(user.getName(), checkUser.getName()); // 查询记录与插入记录应当相同
     Assert.assertEquals(user.getEmail(), checkUser.getEmail()); // 查询记录与插入记录应当相同
   }
   ```

   :heavy_exclamation_mark:注意：该测试方法只是为了便于理解，存在很大的缺陷，请勿在生产代码中这样做。

4. 点击该方法上的 `Run Test` 按钮，即可执行测试.

   如果测试通过，那么 `Run Test` 按钮右侧将会出现​ :heavy_check_mark: ；

   如果出现错误，将会出现 :x: ，这时可以点击该 :x: 来查看详细错误信息。

5. 预览如下：

   ![Run Test](https://raw.githubusercontent.com/DevinDon/springboot-mybatis-demo/master/docs/image/Run-Test.gif)

至此，测试任务全部完成。

# 总结

在本章教程中，我们学习了如何配置开发环境、创建 Spring Boot 项目以及使用 MyBatis 连接数据库，迈出了进入 Spring MVC 世界的第一步。我们来回顾一下本章内容的基本步骤：

1. 安装开发环境
2. 创建 Spring Boot 项目
3. 在项目中配置 application.properties
4. 建立实体 <small>`Entity`</small> 类
5. 建立映射 <small>`Mapper`</small> 接口
6. 添加 `Mapper Scan` 注解
7. 编写并执行测试

如果你能回想起来其中的重要步骤，那你就基本掌握了 Spring Boot 的入门配置。

在接下来的学习中，我们将会深入了解 ORM 框架的工作原理，学习如何结合 Spring Boot 与 MyBatis 进行业务开发，进入生产实践阶段。路漫漫其修远兮，共勉。

# 致谢

[Spring Initializr](https://start.spring.io/)

[如何优雅的使用 Mybatis](http://www.ityouknow.com/springboot/2016/11/06/spring-boot-mybatis.html)

[有多少人在滥用 service+serviceImpl，又有多少人在误用myBatis](https://my.oschina.net/HuQingmiao/blog/636161)

[解析Java框架中entity层，mapper层，service层，controller各层作用](https://blog.csdn.net/u011095110/article/details/86088976)

# LICENSE

## Software License

[THE MIT LICENSE](https://github.com/DevinDon/springboot-mybatis-demo/blob/master/LICENSE)

> Copyright © 2019+ IInfinity
>
> Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the “Software”), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
>
> The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
>
> THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

# Media License

[THE CC BY-NC 4.0 LICENSE](https://github.com/DevinDon/springboot-mybatis-demo/blob/master/docs/LICENSE)

> Copyright © 2019+ IInfinity
>
> LICENSE: CC BY-NC 4.0
>
> This work is licensed under the Creative Commons Attribution-NonCommercial 4.0 International License.
>
> To view a copy of this license, visit http://creativecommons.org/licenses/by-nc/4.0/
>
> or send a letter to Creative Commons, PO Box 1866, Mountain View, CA 94042, USA.
