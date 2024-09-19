package org.utm.methods;

import org.utm.logger.LogWriter;
import org.utm.logger.Writer;
import org.utm.utils.Epsilons;
import org.utm.utils.RealRoots;

/**
 * Метод последовательных приближений
 */
public class SuccessiveApproximations {
    /**
     * необходиммые константы для логирования
     */
    private static final String LOG_FILE_FUNCTION_A = "src/main/resources/methodsFunctionA.txt";
    private static final LogWriter logWriterA = msg -> Writer.writeObject(msg, LOG_FILE_FUNCTION_A);

    /**
     * Константные значения интервала [alfa, beta], в котором ищется корень.
     * alfa - начальная граница интервала.
     * beta - конечная граница интервала.
     */
    private static final double alfa = RealRoots.alfa;
    private static final double beta = RealRoots.beta;

    /**
     * Точность (ε) для метода последовательных приближений.
     */
    private static final double epsilon = Epsilons.epsilonSuccessive; //1e-6


    /**
     * Вычисляет значение A = alfa - (beta - alfa), которое используется в итерации.
     *
     * @return Значение A.
     */
    private static double getValueA() {
        return alfa - (beta - alfa);
    }

    /**
     * Вычисляет значение B = beta + (beta - alfa), которое используется в итерации.
     *
     * @return Значение B.
     */
    private static double getValueB() {
        return beta + (beta - alfa);
    }

    /**
     * Инициализирует метод последовательных приближений и выводит результат.
     */
    public static void initSuccessiveApproximations() {
        // записываем описание метода в файл
        writeDescriptionMethodToFile();
        // решение
        double root = successiveApproximationsForFunctionA();
        System.out.println("Прилеженное значение корня: " + root);
        // проверка корня
        verifyResult(root);
    }

    /**
     * описание метода и запись в файл
     */
    private static void writeDescriptionMethodToFile() {
        String log = "метод итераций\n".toUpperCase();
        // запись нашего интервала в файла

        log += "alfa = " + alfa + ", betta = " + beta + "\n";
        log += String.format("a = alfa - (beta - alfa) = %.2f\n" +
                        "b = beta + (beta - alfa) = %.2f\n",
                getValueA(), getValueB());
        log += String.format("%.2f < x < %.2f\n", getValueA(), getValueB());
        log += "Формула: x = (0.5 - 2^x)/3\n";

        // запись информации о методе в файл
        logWriterA.log(log);
    }

    /**
     * * Метод для нахождения корня функции методом итераций
     * * @return приближенное значение корня для функции из пункта (а)
     */
    private static double successiveApproximationsForFunctionA() {
        StringBuilder logBuilder = new StringBuilder();

        int iteration = 1;
        double xPrev = getValueB(); // начальное значение
        double xNext = (0.5 - Math.pow(2, xPrev)) / 3; // следующее значение
        logBuilder.append("Результат метода итераций: \n");
        logBuilder.append(String.format("x%d. f(%f) = (0.5 -2^(%f))/3 = %f\n",
                iteration, xPrev, xPrev, xNext));

        // Итерации до тех пор, пока разность между текущим и предыдущим значениями не станет меньше ε.
        while (Math.abs(xNext - xPrev) >= epsilon) {
            iteration++;
            xPrev = xNext;
            xNext = (0.5 - Math.pow(2, xPrev)) / 3; // следующее значение

            logBuilder.append(String.format("x%d. f(%f) = (0.5 -2^(%f))/3 = %f\n",
                    iteration, xPrev, xPrev, xNext));
        }
        logWriterA.log(logBuilder.toString());
        return xNext;
    }

    /**
     * Проверка корректности найденного корня.
     * @param root найденное приближенное значение корня.
     */
    private static void verifyResult(double root) {
        double functionValue = (0.5 - Math.pow(2, root)) / 3;
        System.out.printf("Проверка корня: f(%f) = %f\n", root, functionValue);
    }


}
