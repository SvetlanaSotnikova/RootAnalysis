package org.utm.logger;

import java.io.*;

public class Writer implements LogWriter {

    public static void writeObject(Object o, String file) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write(o.toString());
            writer.newLine(); // Добавляет новую строку после записи
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void log(String msg) {

    }
}
