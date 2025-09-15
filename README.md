# sangeng-blog

## 依赖管理

```xml
<dependencyManagement>
    <dependencies>
        <!-- https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-dependencies -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-dependencies</artifactId>
            <version>${spring-boot.version}</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>
    </dependencies>
</dependencyManagement>
```

## springdoc 零注解生成文档

### 依赖：

```xml
<!-- https://springdoc.org/ -->
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
    <version>2.8.12</version>
</dependency>
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-starter-webmvc-api</artifactId>
    <version>2.8.12</version>
</dependency>
<!-- https://springdoc.org/#javadoc-support -->
<!-- 读取 Javadoc 注释，springdoc-openapi 根据注释生成文档 -->
<dependency>
    <groupId>com.github.therapi</groupId>
    <artifactId>therapi-runtime-javadoc</artifactId>
    <version>0.15.0</version>
</dependency>
<!-- Javadoc 导致 lombok 失效 https://juejin.cn/post/7377230203153727498#heading-9 -->
<dependency>
    <groupId>io.github.linpeilie</groupId>
    <artifactId>mapstruct-plus-spring-boot-starter</artifactId>
    <version>${mapstruct-plus.version}</version>
</dependency>
```

### lombok 插件配置

> [参考地址](https://juejin.cn/post/7377230203153727498#heading-9)

```xml
<annotationProcessorPaths>
    <path>
        <groupId>com.github.therapi</groupId>
        <artifactId>therapi-runtime-javadoc-scribe</artifactId>
        <version>${therapi-javadoc.version}</version>
    </path>
    <!-- Javadoc 导致 lombok 失效 https://juejin.cn/post/7377230203153727498#heading-9 -->
    <path>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <version>${lombok.version}</version>
    </path>
    <path>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-configuration-processor</artifactId>
        <version>${spring-boot.version}</version>
    </path>
    <path>
        <groupId>io.github.linpeilie</groupId>
        <artifactId>mapstruct-plus-processor</artifactId>
        <version>${mapstruct-plus.version}</version>
    </path>
    <path>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok-mapstruct-binding</artifactId>
        <version>${mapstruct-plus.lombok.version}</version>
    </path>
</annotationProcessorPaths>
```

## 接口参数值无法获取

> 参考：
>
> [https://www.oldmoon.top/post/191](https://www.oldmoon.top/post/191)
>
> [https://blog.csdn.net/ly1347889755/article/details/139470609](https://blog.csdn.net/ly1347889755/article/details/139470609)

报错：

```java
2025-03-25T16:23:21.218+08:00 ERROR 16112 --- [nio-8888-exec-1] o.a.c.c.C.[.[.[/].[dispatcherServlet]    : Servlet.service() for servlet [dispatcherServlet] in context with path [] threw exception [Request processing failed: java.lang.IllegalArgumentException: Name for argument of type [java.lang.Integer] not specified, and parameter name information not available via reflection. Ensure that the compiler uses the '-parameters' flag.] with root cause
```

## 集成 Fastjson2

> [官方库 alibaba/fastjson2](https://github.com/alibaba/fastjson2)
>
> [在 Spring 中集成 Fastjson2](https://github.com/alibaba/fastjson2/blob/main/docs/spring_support_cn.md)
