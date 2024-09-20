package org.utm.logger;

import java.util.Map;
import java.util.function.Consumer;

public class LogFunctionMapper {
    // Словарь для связи функции и методов логирования
    public static final Map<String, Consumer<String>> logFunctionMap = Map.of(
            "A", Logger::logFunctionA,
            "B", Logger::logFunctionB
    );
    public static void logFunction(String functionName, String log) {
        logFunctionMap.getOrDefault(functionName, Logger::logFunctionB).accept(log);
    }
}
