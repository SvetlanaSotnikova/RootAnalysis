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
     * Константы для логирования в файл.
     * Лог файл для функции A, используемый для записи хода вычислений.
     */
    private static final String LOG_FILE_FUNCTION_A = "src/main/resources/methodsFunctionA.txt";
    private static final LogWriter logWriterA = msg -> Writer.writeObject(msg, LOG_FILE_FUNCTION_A);

    /**
     * Константные значения интервала [alfa, beta], в котором ищется корень.
     * Значения получаются из класса RealRoots.
     */
    private static final double alfa = RealRoots.alfa;
    private static final double beta = RealRoots.beta;

    /**
     * Значение точности (ε) для метода последовательных приближений.
     * Используется для критерия остановки метода (разница между итерациями).
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
     * Инициализирует метод последовательных приближений для функции A и выводит результат.
     * Проводится логирование описания метода и найденного корня.
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
        String log = "метод итераций\n".toUpperCase(); // заголовок

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
     * Метод для нахождения корня функции методом итераций
     * @return приближенное значение корня для функции из пункта (а)
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
            xPrev = xNext; // присваиваем начальному значению вычисленное следующее
            xNext = (0.5 - Math.pow(2, xPrev)) / 3; // следующее значение

            logBuilder.append(String.format("x%d. f(%f) = (0.5 -2^(%f))/3 = %f\n",
                    iteration, xPrev, xPrev, xNext));
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
        double functionValue = (0.5 - Math.pow(2, root)) / 3;
        System.out.printf("Проверка корня: f(%f) = %f\n", root, functionValue);
    }


}
