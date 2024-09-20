package org.utm.methods;

import org.utm.logger.LogFunctionMapper;
import org.utm.logger.LogWriter;
import org.utm.logger.Logger;
import org.utm.utils.Epsilons;
import org.utm.utils.RealRoots;

import java.util.function.Function;

/**
 * Метод последовательных приближений
 */
public class SuccessiveApproximations extends RootGeneralMethods{

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
     * Метод для нахождения корня функции методом итераций
     *
     * @return приближенное значение корня для функции из пункта (а)
     */
    @Override
    protected double findRoot(Function<Double, Double> function, String functionName) {
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
        LogFunctionMapper.logFunction(functionName, logBuilder.toString());
        return xNext;
    }

    @Override
    protected String getDescription() {
        String log = "метод итераций\n".toUpperCase(); // заголовок

        log += "alfa = " + alfa + ", betta = " + beta + "\n";
        log += String.format("a = alfa - (beta - alfa) = %.2f\n" +
                        "b = beta + (beta - alfa) = %.2f\n",
                getValueA(), getValueB());
        log += String.format("%.2f < x < %.2f\n", getValueA(), getValueB());
        log += "Формула: x = (0.5 - 2^x)/3\n";
        return log;
    }

}
