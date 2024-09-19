package org.utm.utils;

/**
 * Выделить все действительные корни уравнения f(x)=0,
 * где y=f(x) — действительная функция действительной переменной.
 * A - 2^x+3x-0.5
 */
public class RealRoots {
    public static double alfa;
    public static double beta;

    // f(x) = 2^x + 3x - 0.5
    public static double functionA(double x) {
        return Math.pow(2, x) + 3 * x - 0.5;
    }

    public double functionB(double x) {
        return 0;
    }

    public static void findInterval(double start, double end, double step) {
        double value = functionA(start);
        for (double i = start + step; i <= end; i += step) {
            double currentValue = functionA(i);

            // изменение знака функции
            if (value * currentValue < 0) {
                alfa = i - step;
                beta = i;
            }
            value = currentValue;
        }
    }
    public static String getInterval() {
        return String.format("[%.2f, %.2f]", alfa, beta);
    }
}
