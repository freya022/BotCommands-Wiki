The library uses a logger, if no logger is present it will only print errors

**It is strongly advised you use a logger**, it doesn't take you much time to add a logback dependency and some generic logback.xml

For a logging tutorial, you can follow [JDA's Logging tutorial](https://jda.wiki/setup/logging/)

And then you can choose a `logback.xml` file, which you have to put in the root of your resources (so in Maven at `src/main/resources`)

My personal choice for a `logback.xml` is as such:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<configuration>
    <timestamp key="bySecond" datePattern="yyyyMMdd'T'HHmmss"/>

    <appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
        <encoder>
            <!-- Debug pattern -->
            <!--<pattern>%d{HH:mm:ss.SSS} %boldCyan(%-26.-26thread) %boldRed(%-36.-36class{36}) %boldRed(#%-24.-24method{24}) %boldRed(L%-5.-5line) %boldYellow(%-20.-20logger{0}) %highlight(%-6level) %msg%n%throwable</pattern>-->
            <!-- Normal pattern, no stack frames -->
            <pattern>%d{HH:mm:ss.SSS} %boldCyan(%-26.-26thread) %boldYellow(%-20.-20logger{0}) %highlight(%-6level) %msg%n%throwable</pattern>
        </encoder>
    </appender>

    <appender name="FILE" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
            <!-- daily rollover -->
            <fileNamePattern>logs/logs-%d{yyyy-MM-dd}.log</fileNamePattern>

            <!-- keep 90 days' worth of history capped at 3GB total size -->
            <maxHistory>90</maxHistory>
            <totalSizeCap>3GB</totalSizeCap>
        </rollingPolicy>

        <encoder>
            <pattern>%d{HH:mm:ss.SSS} %-26.-26thread %-36.-36class{36} #%-24.-24method{24} L%-5.-5line %-20.-20logger{0} %-6level %msg%n%throwable</pattern>
        </encoder>
    </appender>

    <!-- You can remove these comments if you want JDA to log only on debug while the library itself is on the trace level, for debugging purposes -->
<!--    <logger name="net.dv8tion.jda" level="debug" additivity="false">-->
<!--        <appender-ref ref="STDOUT"/>-->
<!--        <appender-ref ref="FILE"/>-->
<!--    </logger>-->

    <root level="debug">
        <appender-ref ref="STDOUT"/>
        <appender-ref ref="FILE"/>
    </root>
</configuration>
```
