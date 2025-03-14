package pe.seek.structuredlogs.logging;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.LayoutBase;
import org.springframework.boot.json.JsonWriter;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class CustomJsonLayout extends LayoutBase<ILoggingEvent> {

    @Override
    public String doLayout(ILoggingEvent event) {
        return this.writer.writeToString(event);
    }

    private final JsonWriter<ILoggingEvent> writer = JsonWriter.<ILoggingEvent>of((members) -> {
        members.add("timestamp", event -> FORMATTER.format(event.getInstant()));
        members.add("message", ILoggingEvent::getFormattedMessage);
        members.add("level", ILoggingEvent::getLevel);
        members.add("event", CustomJsonLayout::getCallerMethod);
        members.add("tag", CustomJsonLayout::getCallerClass);
        members.add("extra", ILoggingEvent::getMDCPropertyMap);
    }).withNewLineAtEnd();

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")
            .withZone(ZoneId.systemDefault());

    private static String getCallerClass(ILoggingEvent event) {
        StackTraceElement[] callerData = event.getCallerData();
        return (callerData != null && callerData.length > 0) ? callerData[0].getClassName() : "Unknown";
    }

    private static String getCallerMethod(ILoggingEvent event) {
        StackTraceElement[] callerData = event.getCallerData();
        return (callerData != null && callerData.length > 0) ? callerData[0].getMethodName() : "Unknown";
    }

}