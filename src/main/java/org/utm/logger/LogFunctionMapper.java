package org.utm.logger;

import java.util.Map;
import java.util.function.Consumer;

/**
 * общее логироване, автоматическое определение лог файла
 */
public class LogFunctionMapper {
    // Словарь для связи функции и методов логирования
    public static final Map<String, Consumer<String>> logFunctionMap = Map.of(
            "A", Logger::logFunctionA,
            "B", Logger::logFunctionB
    );

    /**
     * метод для логированея результата в лог файл
     * @param functionName - буква функции A, B
     * @param log - текст лога
     */
    public static void logFunction(String functionName, String log) {
        logFunctionMap.getOrDefault(functionName, Logger::logFunctionB).accept(log);
    }
}
