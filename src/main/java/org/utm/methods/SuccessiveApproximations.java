package org.utm.methods;

import org.utm.logger.LogFunctionMapper;
import org.utm.utils.Epsilons;
import org.utm.utils.RealRoots;

import java.util.function.Function;

/**
 * Метод последовательных приближений
 */
public class SuccessiveApproximations extends RootGeneralMethods {

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
        double xPrev = (beta + alfa) / 2; // начальное значение
        double xNext; // следующее значение

        xNext = computeNextValue(functionName, xPrev);


        logBuilder.append("Формула: " + getFormula(functionName));
        logBuilder.append("Результат метода итераций: \n");
        logBuilder.append(String.format(functionName.equals("A")
                        ? "x%d. f(%f) = (0.5 -2^(%f))/3 = %f\n"
                        : "x%d. f(%f) = cbrt(37 * %f + 52) = %f\n",
                iteration, xPrev, xPrev, xNext));

        // Итерации до тех пор, пока разность между текущим и предыдущим значениями не станет меньше ε.
        while (Math.abs(xNext - xPrev) >= epsilon) {
            iteration++;
            xPrev = xNext; // присваиваем начальному значению вычисленное следующее
            xNext = computeNextValue(functionName, xPrev); // следующее значение

            logBuilder.append(String.format(functionName.equals("A")
                            ? "x%d. f(%f) = (0.5 -2^(%f))/3 = %f\n"
                            : "x%d. f(%f) = cbrt(37 * %f + 52) = %f\n",
                    iteration, xPrev, xPrev, xNext));
        }
        LogFunctionMapper.logFunction(functionName, logBuilder.toString());
        return xNext;
    }

    private double computeNextValue(String functionName, double xPrev) {
        if (functionName.equals("A")) {
            return (0.5 - Math.pow(2, xPrev)) / 3; // следующее значение
        } else {
            return Math.cbrt(37 * xPrev + 52); // следующее значение для метода B
        }
    }

    private String getFormula(String functionName) {
        return functionName.equals("A") ? "x = (0.5 - 2^x)/3\n" : "x = cbrt(37 * x + 52)\n";
    }

    @Override
    protected String getDescription() {
        String log = "метод итераций\n".toUpperCase(); // заголовок

        log += "alfa = " + alfa + ", betta = " + beta + "\n";
        log += String.format("a = alfa - (beta - alfa) = %.2f\n" +
                        "b = beta + (beta - alfa) = %.2f\n",
                getValueA(), getValueB());
        log += String.format("%.2f < x < %.2f\n", getValueA(), getValueB());
        return log;
    }

}
