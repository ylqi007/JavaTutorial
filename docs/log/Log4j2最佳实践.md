## 最佳实践(Maven)
### Step 1. 必须同时有 API 和 Core
* `log4j-api` 里是接口（编译需要）
* `log4j-core` 里是实现（运行需要）


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


### Step 2. 配置文件(不配置也能运行，但是建议配置)
在 `src/main/resources/log4j2.xml` 里定义日志格式，比如：
```xml
<?xml version="1.0" encoding="UTF-8"?>
<Configuration status="WARN">
    <Appenders>
        <Console name="Console" target="SYSTEM_OUT">
            <PatternLayout pattern="%d{yyyy-MM-dd HH:mm:ss} %-5level %logger{36} - %msg%n"/>
        </Console>
    </Appenders>
    <Loggers>
        <Root level="info">
            <AppenderRef ref="Console"/>
        </Root>
    </Loggers>
</Configuration>
```


### Step 3. 使用 Lombok `@Log4j2`
```java
import lombok.extern.log4j.Log4j2;

@Log4j2
public class Example {
    public void test() {
        log.info("This is a test message.");
    }
}
```
