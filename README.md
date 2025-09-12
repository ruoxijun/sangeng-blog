# sangeng-blog

## 集成 Fastjson2

> [官方库 alibaba/fastjson2](https://github.com/alibaba/fastjson2)
>
> [在 Spring 中集成 Fastjson2](https://github.com/alibaba/fastjson2/blob/main/docs/spring_support_cn.md)

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
