![在这里插入图片描述](https://imgconvert.csdnimg.cn/aHR0cHM6Ly93d3cuZGxibG9nLmNsdWIvZmlsZS8yMDIwMDQyNS9welBtaUhzay5wbmc?x-oss-process=image/format,png)
# BlogMail 
##  基于spring-mail/thymeleaf
- 支持文本邮件
- 支持html模板邮件
- 支持cc发送
- 支持文件上传
- 支持自定义邮箱配置

## 核心依赖
``` xml
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-mail</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-thymeleaf</artifactId>
		</dependency>
```

## 基础配置

``` yml
# 邮箱配置
spring:
  mail:
    host: smtp.163.com
    port:
    username: medicialbbs@163.com
    password: *********
    protocol: smtp
    default-encoding: utf-8
    test-connection: true
```

## 核心类 - MailContentBuilder
``` java
    /**
     * 内容
     */
    private String content;

    /**
     * 接收者
     */
    private String to;

    /**
     * 发送者
     */
    private String from;

    /**
     * 主题
     */
    private String subject;

    /**
     * cc
     */
    private String[] cc;

    /**
     * 链接文件
     */
    private Map<String, File> linkFiles;


    /**
     * 是否类型为html
     */
    private boolean isHtml = false;
 ``` 
 #  代码一览
 ![在这里插入图片描述](https://img-blog.csdnimg.cn/20200726134001500.png)



