package org.utm.methods;

import org.utm.logger.LogWriter;
import org.utm.logger.Writer;
import org.utm.utils.Epsilons;
import org.utm.utils.RealRoots;

/**
 * Метод Ньютона (касательных) для нахождения корней функции.
 */
public class NewtonMethod {
    /**
     * Файл для логирования результатов метода Ньютона для функции A.
     */
    private static final String LOG_FILE_FUNCTION_A = "src/main/resources/methodsFunctionA.txt";
    private static final LogWriter logWriterA = msg -> Writer.writeObject(msg, LOG_FILE_FUNCTION_A);

    /**
     * Константные значения интервала [alfa, beta] и точности ε для метода Ньютона.
     */
    private static final double alfa = RealRoots.alfa;
    private static final double beta = RealRoots.beta;

    /**
     * Точность (ε) для метода Ньютона.
     */
    private static final double epsilon = Epsilons.epsilonSuccessive; //1e-6

    /**
     * Метод для инициализации метода и поиска корней
     */
    public static void initNewtonMethod() {
        // Записываем описание метода в файл
        writeDescriptionMethodToFile();
        // Решение
        double root = newtonMethodForFunctionA();
        System.out.println("Приближенное значение корня: " + root);
        // Проверка корня
        verifyResult(root);
    }

    /**
     * Краткое описание метода для лог файла результатов
     */
    private static void writeDescriptionMethodToFile() {
        // объяснение что это за метод
        String log = "метод Ньютона(Касательных)\n".toUpperCase();
        log += "Формула:x(n+1) = x(n) - f(x(n)) / f'(x(n))\\n";

        // запись информации о методе в файл
        logWriterA.log(log);
    }

    /**
     * Производная функции f(x) = 2^x + 3x - 0.5
     * Производная f'(x) = 2^x * ln(2) + 3
     *
     * @param x аргумент
     * @return значение производной
     */
    private static double derivativeA(double x) {
        return Math.log(2) * Math.pow(2, x) + 3;
    }

    /**
     * Метод для нахождения корня функции методом Ньютона.
     *
     * @return приближенное значение корня для функции.
     */
    private static double newtonMethodForFunctionA() {
        StringBuilder logBuilder = new StringBuilder();
        int iteration = 1;
        double xPrev = (alfa + beta) / 2; // начальное приблеженное значение
        double xNext = 0;

        logBuilder.append("\nРезультат метода Ньютона:\n");

        while (Math.abs(RealRoots.functionA(xNext)) >= epsilon) {
            // Вычисляем значение функции и ее производной в текущей точке
            double fx = RealRoots.functionA(xPrev);
            double fxPrime = derivativeA(xPrev);

            // Проверяем, что производная не равна нулю, чтобы избежать деления на ноль
            if (fxPrime == 0) {
                throw new ArithmeticException("Error");
            }

            // Вычисляем следующее приближение
            xNext = xPrev - (fx / fxPrime);

            // логирование результатов
            logBuilder.append(String.format("x%d: x(%.6f) = %.6f - f(%.6f) / f'(%.6f) = %.6f\n",
                    iteration, xPrev, xPrev, fx, fxPrime, xNext));

            // Подготовка к следующей итерации
            xPrev = xNext;
            iteration++;
        }
        logWriterA.log(logBuilder.toString());
        return xNext;
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
