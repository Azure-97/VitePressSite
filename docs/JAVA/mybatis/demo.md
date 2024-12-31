## 1 引入依赖

```xml
<dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-tomcat</artifactId>
            <scope>provided</scope>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>

        <dependency>
            <groupId>com.mysql</groupId>
            <artifactId>mysql-connector-j</artifactId>
            <!-- <version>8.0.32</version> -->
        </dependency>
        <!-- 数据库驱动 -->
        <dependency>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
            <version>3.0.3</version>
        </dependency>


    </dependencies>
```

## 2 配置文件

```yml
spring:
  datasource:
    # 数据库连接驱动
    driver-class-name: com.mysql.cj.jdbc.Driver
    # 数据源类型： 默认的是 Hikari
    type: com.zaxxer.hikari.HikariDataSource
    # 数据库连接地址
    url: jdbc:mysql://localhost:3306/wang?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=Asia/Shanghai
    # 数据库连接用户名
    username: root
    # 数据库连接密码
    password: root

# mybatis 的配置
mybatis:
  # 配置 mybatis 的xml文件的扫描路径
  mapper-locations: classpath:mybatis/**/*.xml
  # 配置实体类的扫描路径
  type-aliases-package: com.example.demo.mybatis.DO
  configuration:
    # 开启驼峰命名转换
    map-underscore-to-camel-case: true
    # 开启日志
    #log-impl: org.apache.ibatis.logging.stdout.StdOutImpl
    log-impl: org.apache.ibatis.logging.slf4j.Slf4jImpl

# 指定日志级别 : 对mybatis的日志输出
logging:
  level:
    com.example.demo.mybatis: debug


```


## 3 实体类


```java
package com.example.demo.mybatis.DO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    private int id;
    private String name;
    private int age;
    private String otherMessage;

}


```


## 4 Mapper 接口

```java
package com.example.demo.mybatis.mapper;

import com.example.demo.mybatis.DO.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface StudentMapper {
    // 根据id查询student的方法
    Student getStudentById(@Param("id") int id);
}

```

## 5 Mapper XML
resources/mybatis/StudentMapper.xml
```
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="com.example.demo.mybatis.mapper.StudentMapper">


    <select id="getStudentById" resultType="com.example.demo.mybatis.DO.Student">
        select * from student where id = #{id}
    </select>

</mapper>

```

## 6 controller


```java
package com.example.demo.mybatis.controller;

import com.example.demo.mybatis.DO.Student;
import com.example.demo.mybatis.mapper.StudentMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

    /**
     * 通过构造方法的方式注入 StudentMapper
     */
    private final StudentMapper studentMapper;

    public StudentController(StudentMapper studentMapper) {
        this.studentMapper = studentMapper;
    }

    @GetMapping("/getStudentById/{id}")
    public Student getStudentById(@PathVariable("id") int id){
        Student student = null;
        student = studentMapper.getStudentById(id);
        return student;
    }
}
```

## 7 测试

```java
package com.example.demo;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@MapperScan("com.example.demo.mybatis.mapper")
public class DemoApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(DemoApplication.class, args);

        // 获取所有Bean的名字
        String[] beanNames = run.getBeanDefinitionNames();
        for (String name : beanNames) {
            System.out.println(name);
        }
    }

}

```
通过浏览器访问 localhost:8080/getStudentById/1
