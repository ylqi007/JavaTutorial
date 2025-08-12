# 使用 Log 时的常见异常、错误

## java: package org.apache.logging.log4j does not exist
在 pom.xml 文件中添加了 Lombok 依赖，然后在类上添加 `@Log4j2` 注释，但是程序运行时报错
```
java: package org.apache.logging.log4j does not exist
```
项目的 Log4j 依赖没有正确导入，所以编译器找不到 `org.apache.logging.log4j` 这个包。


1. **在 Maven 项目的解决方法**

    在 `pom.xml` 中添加 `Log4j2` 的依赖（Lombok 的 `@Log4j2` 注解只是帮你生成 `log` 字段，但它不会自动带上 `Log4j` 的库）：
    ```xml
    <dependencies>
        <!-- Lombok (代码生成) -->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>1.18.34</version> <!-- 版本根据需要 -->
            <scope>provided</scope>
        </dependency>
    
        <!-- Log4j2 API -->
        <dependency>
            <groupId>org.apache.logging.log4j</groupId>
            <artifactId>log4j-api</artifactId>
            <version>2.23.1</version>
        </dependency>
    
        <!-- Log4j2 Core (实现) -->
        <dependency>
            <groupId>org.apache.logging.log4j</groupId>
            <artifactId>log4j-core</artifactId>
            <version>2.23.1</version>
        </dependency>
    </dependencies>
    ```
2. **额外注意**
   1. 必须同时有 API 和 Core：
      * `log4j-api` 里是接口（编译需要）
      * `log4j-core` 里是实现（运行需要） 
      * 缺一个都会出错。
   2. 配置文件（不配也能运行，但建议配置）：在 `src/main/resources/log4j2.xml` 里定义日志格式。
   3. 使用 Lombok `@Log4j2`


### 有无 `log4j-core` 的对比
如果你想确认当前运行时到底用的哪个**日志实现**，可以在代码里加：
```java
System.out.println(org.apache.logging.log4j.LogManager.getContext().getClass().getName());
```


#### 1. 只添加`log4j-api`
```java
org.apache.logging.log4j.simple.SimpleLoggerContext
ERROR CompletableFutureAPI2Demo Thread was interrupted while waiting for completion
```
由于没有 `log4j-core`，`LoggerManager` 在运行时找不到真正的 `Log4jContext` 实现，所以会回退到内置的简单实现，即 `org.apache.logging.log4j.simple.SimpleLoggerContext`, which is "A simple LoggerContext implementation."
* https://logging.apache.org/log4j/2.x/javadoc/log4j-api/org/apache/logging/log4j/simple/SimpleLoggerContext.html

**运行机制:**
1. `log4j-api.jar` 启动时会尝试通过 `ProviderUtil` 加载实现（Core 或其他）。
2. 如果发现没有 `log4j-core`，就用内置的 `SimpleLoggerContext`。
3. `SimpleLoggerContext` 的特点：
   * 不解析 `log4j2.xml/log4j2.properties` 配置
   * 日志输出非常简陋（默认输出到控制台）
   * 功能受限，没有异步日志、多 Appender 支持等

**对比:**

| 情况           | `getContext()` 返回                                     | 特性            |
| ------------ | ----------------------------------------------------- | ------------- |
| 有 log4j-core | `org.apache.logging.log4j.core.LoggerContext`         | 完整功能，支持配置文件   |
| 无 log4j-core | `org.apache.logging.log4j.simple.SimpleLoggerContext` | 简单输出，无法使用高级功能 |


#### 2. 添加`log4j-api` + `log4j-core`
```java
org.apache.logging.log4j.core.LoggerContext
2025-08-08 22:00:22 ERROR com.ylqi007.CompletableFutureAPI2Demo - Thread was interrupted while waiting for completion
```


### ✅ 总结说明
1. 编译阶段：只有 `log4j-api` 就够了，因为它包含了 `Logger` 接口定义。
2. 运行阶段：
   1. 需要一个实现(`log4j-core`, 或者桥接到 SLF4J，Logback 等)
   2. 没有实现时，有可能日志静默不输出。
