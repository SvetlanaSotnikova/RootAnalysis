package org.utm.methods;

import org.utm.logger.LogFunctionMapper;
import org.utm.utils.Epsilons;
import org.utm.utils.RealRoots;

import java.util.Objects;
import java.util.function.Function;

/**
 * Метод Ньютона (касательных) для нахождения корней функции.
 */
public class NewtonMethod  extends RootGeneralMethods {
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
     * Производная функции f(x) = x^3 - 37x - 52
     * Производная f'(x) =  3x^2 - 37
     *
     * @param x аргумент
     * @return значение производной
     */
    private static double derivativeB(double x) {
        return 3 * Math.pow(x, 2) - 37;
    }

    /**
     * Метод для нахождения корня функции методом Ньютона.
     *
     * @return приближенное значение корня для функции.
     */
    @Override
    protected double findRoot(Function<Double, Double> function, String functionName) {
        StringBuilder logBuilder = new StringBuilder();
        int iteration = 1;
        double xPrev = (alfa + beta) / 2; // начальное приблеженное значение
        double xNext = 0;

        logBuilder.append("\nРезультат метода Ньютона:\n");

        while (Math.abs(function.apply(xNext)) >= epsilon) {
            // Вычисляем значение функции и ее производной в текущей точке
            double fx = function.apply(xPrev);
            double fxPrime = Objects.equals(functionName, "A") ? derivativeA(xPrev) : derivativeB(xPrev);


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
        // логирование
        LogFunctionMapper.logFunction(functionName, logBuilder.toString());
        return xNext;
    }

    /**
     * @return описание метода
     */
    @Override
    protected String getDescription() {
        // объяснение что это за метод
        String log = "метод Ньютона(Касательных)\n".toUpperCase();
        log += "Формула:x(n+1) = x(n) - f(x(n)) / f'(x(n))\\n";
        return log;
    }
}
