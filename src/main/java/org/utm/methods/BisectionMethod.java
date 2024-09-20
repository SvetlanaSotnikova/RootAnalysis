package org.utm.methods;

import org.utm.logger.Logger;
import org.utm.utils.Epsilons;
import org.utm.utils.RealRoots;
import org.utm.logger.LogWriter;

import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

/**
 * Класс, реализующий метод половинного деления для нахождения корней функции.
 * Метод разделяет интервал [a, b] и ищет точку, где функция меняет знак,
 * постепенно уменьшая интервал до достижения заданной точности.
 */
public class BisectionMethod {
    /**
     * Точность вычислений (epsilon)
     */
    private static final double epsilon = Epsilons.epsilonBisection; //1e-2

    // Словарь для связи функции и методов логирования
    private static final Map<String, Runnable> logFunctionMap = Map.of(
            "A", () -> Logger.logFunctionA("Логирование для функции A"),
            "B", () -> Logger.logFunctionB("Логирование для функции B")
    );

    /**
     * Инициализация метода половинного деления. Осуществляет запись
     * описания метода в лог и выполняет сам метод.
     */
    public static void initBisectionMethod(String functionName, Function<Double, Double> function) {
        // запись описания метода
        writeDescriptionMethodToFile(functionName);
        // определение корня
        double root = bisectionMethod(function, functionName);
        System.out.println("Приблеженное значение корня: " + root);
        // проверка корня
        verifyRoot(root, function);
    }

    /**
     * Краткое описание метода для лог файла результатов
     */
    private static void writeDescriptionMethodToFile(String functionName) {
        // объяснение что это за метод
        String log = "метод половинного деления\n".toUpperCase();
        // запись нашего интервала в файла

        log += String.format("Формула: (a + b)/2, где a - начало интервала [%f], b - конец интевала [%f]",
                RealRoots.alfa, RealRoots.beta);

        // запись информации о методе в файл
//        logFunctionMap.getOrDefault(functionName, (msg, unused) -> Logger.logFunctionB(msg)).accept(log, "");
        if (Objects.equals(functionName, "A")) {
            Logger.logFunctionA(log);
        } else {
            Logger.logFunctionB(log);
        }
    }

    /**
     * Метод для нахождения корня функции методом половинного деления
     *
     * @return приближенное значение корня для функции из пункта (а)
     */
    private static double bisectionMethod(Function<Double, Double> function, String functionName) {

//      констнатные значения нашего интервала [a, b]
//      a - alfa
//      b - betta
//      переменные должны менятся поэтому их значения перезаписанны
//      внутри функции а не в виде константы в этом классе
        double alfa = RealRoots.alfa;
        double beta = RealRoots.beta;

        double fa = function.apply(alfa);
        double fb = function.apply(beta);

        if (fa * fb >= 0) {
            // логируем исключение
            if (functionName.equals("A")) {
                Logger.logFunctionA("Функция не меняет знак на конца интервала!!\n");
            } else {
                Logger.logFunctionB("Функция не меняет знак на конца интервала!!\n");
            }
            // ловим исключение
            throw new IllegalArgumentException("Функция не меняет знак на конца интервала");
        }
        StringBuilder logBuilder = new StringBuilder();
        logBuilder.append("\nРезультаты половинного деления:\n");

        double c = 0; //значение результата итераций
        int iteration = 0; // counter

        // начало итераций вычисления корня
        while ((beta - alfa) / 2 >= epsilon) {
            c = (alfa + beta) / 2;
            double fc = function.apply(c);
            iteration++;

            // Проверка близости значения к корню
            if (Math.abs(fc) < epsilon) {
                break;
            }

            // последовательная запись итераций
            logBuilder.append(String.format("x%d: a: %.4f, b: %.4f, c: %.4f, f(c) = %.4f\n",
                    iteration, alfa, beta, c, function.apply(c)));

            // Определяем новый интервал
            if (fc * fa < 0) {  // [alfa, c]
                beta = c;
            } else {            // [c, beta]
                alfa = c;
            }
        }
        if (functionName.equals("A")) {
            Logger.logFunctionA(logBuilder.toString()); // логирование
        } else {
            Logger.logFunctionB(logBuilder.toString()); // логирование
        }
        return c;
    }

    /**
     * Проверка корректности найденного корня.
     *
     * @param root найденное приближенное значение корня.
     */
    private static void verifyRoot(double root, Function<Double, Double> function) {
        double functionValue = function.apply(root);
        System.out.printf("Проверка корня: f(%f) = %.10f < %f\n", root, functionValue, epsilon);
    }

}
