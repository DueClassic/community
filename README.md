# 提问社区

## 部署

- Git
- JDK
- Maven
- MySql

## 步骤

- yum update 
- yum install
- mkdir App
- cd App
- git clone https://github.com/DueClassic/community.git
- yum install maven
- mvn -v
- mvn compile package
- cp src/main/resources/application.properties src/main/resources/application-production.properties 
- java -jar target/community-0.0.1-SNAPSHOT.jar --spring.profiles.active=production


## 资料
[Spring 文档](https://spring.io/guides/)

[GitHub oAuth文档](https://developer.github.com/apps/building-oauth-apps/authorizing-oauth-apps/)

[okHttp](https://square.github.io/okhttp/)

[菜鸟教程](https://www.runoob.com/)

[Spring DataSource](https://docs.spring.io/spring-boot/docs/2.0.0.RC1/reference/htmlsingle/#boot-features-embedded-database-support)

[UFile SDK](https://docs.ucloud.cn/storage_cdn/ufile/api_reference)


## 工具
[flyway数据库管理](https://flywaydb.org/getstarted/firststeps/maven)

[Lombok](https://projectlombok.org/)

[MarkDown编辑器](https://pandao.github.io/editor.md/)

## 脚本

```sql
CREATE TABLE USER
(
    ID int AUTO_INCREMENT PRIMARY KEY NOT NULL,
    ACCOUNT_ID varchar(100),
    NAME varchar(50),
    TOKEN varchar(36),
    GMT_CREATE bigint,
    GMT_MODIFIED bigint
);
```

```bash
#更新数据库
mvn flyway:migrate
#mybatis Generator自动生成
mvn -Dmybatis.generator.overwrite=true mybatis-generator:generate
```
