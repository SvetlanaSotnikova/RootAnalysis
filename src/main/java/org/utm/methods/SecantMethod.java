package org.utm.methods;

import org.utm.logger.LogWriter;
import org.utm.logger.Logger;
import org.utm.utils.Epsilons;
import org.utm.utils.RealRoots;

/**
 * Метод секущих для нахождения корней функции.
 */
public class SecantMethod {
    /**
     * Точность (ε) для метода секущих.
     */
    private static final double epsilon = Epsilons.epsilonSuccessive; //1e-6

    /**
     * Константные значения интервала [alfa, beta] и точности ε для метода секущих.
     */
    private static final double alfa = RealRoots.alfa;
    private static final double beta = RealRoots.beta;

    /**
     * Метод для инициализации метода и поиска корней.
     */
    public static void initSecantMethod() {
        // Записываем описание метода в файл
        writeDescriptionMethodToFile();
        // Решение
        double root = secantMethodForFunctionA();
        System.out.println("Приближенное значение корня: " + root);
        // Проверка корня
        verifyResult(root);
    }

    /**
     * Краткое описание метода для лог файла результатов.
     */
    private static void writeDescriptionMethodToFile() {
        // Описание метода
        String log = "Метод секущих\n".toUpperCase();
        log += "Формула: x(n+1) = x(n) - f(x(n)) * (x(n) - x(n-1)) / (f(x(n)) - f(x(n-1)))\n";

        // Запись информации о методе в файл
        Logger.logFunctionA(log);
    }

    /**
     * Метод для нахождения корня функции методом секущих.
     *
     * @return приближенное значение корня для функции.
     */
    private static double secantMethodForFunctionA() {
        StringBuilder logBuilder = new StringBuilder();
        int iteration = 1;

        // Начальные приближения
        double x0 = alfa;
        double x1 = beta;
        double xNext;

        logBuilder.append("Результат метода секущих:\n");
        while ((Math.abs(x1 - x0) >= epsilon)) {
            double fx0 = RealRoots.functionA(x0);
            double fx1 = RealRoots.functionA(x1);

            if (fx1 - fx0 == 0) {
                throw new ArithmeticException("Ошибка: разность значений функции равна нулю.");
            }

            // Вычисление следующего приближения
            xNext = x1 - fx1 * (x1 -x0) / (fx1 - fx0);

            // Логирование результатов
            logBuilder.append(String.format("x%d: x(%.6f) = %.6f - f(%.6f) * (%.6f - %.6f) / (f(%.6f) - f(%.6f)) = %.6f\n",
                    iteration, x1, x1, fx1, x1, x0, fx1, fx0, xNext));

            x0 = x1;
            x1 = xNext;
            iteration++;
        }
        // Запись логов в файл
        Logger.logFunctionA(logBuilder.toString());
        return x1;
    }

    /**
     * Проверка корректности найденного корня.
     *
     * @param root найденное приближенное значение корня.
     */
    private static void verifyResult(double root) {
        double functionValue = RealRoots.functionA(root);
        System.out.printf("Проверка корня: f(%f) = %f\n", root, functionValue);
    }
}
