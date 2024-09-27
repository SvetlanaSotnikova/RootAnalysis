package org.utm.logger;

import java.io.*;

/**
 * класс для логирования результатов
 */
public class Logger implements LogWriter {
    private static final String LOG_FILE_FUNCTION_A = "src/main/resources/methodsFunctionA.txt";
    private static final String LOG_FILE_FUNCTION_B = "src/main/resources/methodsFunctionB.txt";

    private static final LogWriter logWriterA = msg -> writeToFile(msg, LOG_FILE_FUNCTION_A);
    private static final LogWriter logWriterB = msg -> writeToFile(msg, LOG_FILE_FUNCTION_B);

    public static void logFunctionA(String message) {
        logWriterA.log(message);
    }

    public static void logFunctionB(String message) {
        logWriterB.log(message);
    }

    public static void writeToFile(String msg, String file) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write(msg);
            writer.newLine(); // Добавляет новую строку после записи
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void log(String msg) {

    }
}
