package org.utm.utils;

import java.util.function.Function;

/**
 * Выделить все действительные корни уравнения f(x)=0,
 * где y=f(x) — действительная функция действительной переменной.
 * <br>
 * A - 2^x+3x-0.5
 * <br>
 * B - x^3 - 37x - 52
 */
public class RealRoots {
    // Границы интервала для поиска корней
    public static double alfa; // начало интервала
    public static double beta; // конец интервала

    /**
     * Реализация функции A: f(x) = 2^x + 3x - 0.5.
     * Используется для нахождения корней уравнения f(x) = 0.
     *
     * @param x значение аргумента x
     * @return значение функции f(x) при данном x
     */
    public static double functionA(double x) {
        return Math.pow(2, x) + 3 * x - 0.5;
    }

    /**
     * Реализация функции A: f(x) = x^3 - 37x - 52
     * Используется для нахождения корней уравнения f(x) = 0.
     *
     * @param x значение аргумента x
     * @return значение функции f(x) при данном x
     */
    public static double functionB(double x) {
        return Math.pow(x, 3) - 37 * x - 52;
    }

    /**
     * Поиск интервала, где функция меняет знак, что указывает на наличие корня.
     * Метод проверяет значения функции на шаге, определяя, где происходит смена знака.
     * Если обнаружено изменение знака, то устанавливаются значения alfa и beta,
     * как границы интервала, в котором лежит корень.
     *
     * @param function вставляемая функция
     * @param start начало диапазона поиска
     * @param end   конец диапазона поиска
     * @param step  шаг между точками на интервале
     */
    public static Interval findInterval(Function<Double, Double> function, double start, double end, double step) {
        alfa = 0; // начало интервала
        beta = 0;

        double value = function.apply(start);
        for (double i = start + step; i <= end; i += step) {
            double currentValue = function.apply(i);

            // изменение знака функции
            if (value * currentValue < 0) {
                alfa = i - step;
                beta = i;
            }
            value = currentValue;
        }
        return new Interval(alfa, beta);
    }

    /**
     * Возвращает строковое представление интервала, где был найден корень.
     *
     * @return интервал в формате [alfa, beta]
     */
    public static String getInterval() {
        return String.format("[%.2f, %.2f]", alfa, beta);
    }
}
