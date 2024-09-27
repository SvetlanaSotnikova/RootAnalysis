package org.utm.methods;

import org.utm.logger.LogFunctionMapper;
import org.utm.utils.Epsilons;
import org.utm.utils.RealRoots;

import java.util.function.Function;

/**
 * Метод секущих для нахождения корней функции.
 */
public class SecantMethod extends RootGeneralMethods{
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
     * алгоритм метода секущих
     * @param function - функция, которую мы на данный момент вычиляем A or B
     * @param functionName - буквы нашей функции
     * @return возвращает последний приблизительный корень уравлнения
     */
    @Override
    protected double findRoot(Function<Double, Double> function, String functionName) {
        StringBuilder logBuilder = new StringBuilder();
        int iteration = 1;

        // Начальные приближения
        double x0 = alfa;
        double x1 = beta;
        double xNext;

        logBuilder.append("Результат метода секущих:\n");
        // вычисления
        while ((Math.abs(x1 - x0) >= epsilon)) {
            double fx0 = function.apply(x0);
            double fx1 = function.apply(x1);

            if (fx1 - fx0 == 0) {
                LogFunctionMapper.logFunction(functionName, "Ошибка: разность значений функции равна нулю.");
                throw new ArithmeticException("Ошибка: разность значений функции равна нулю.");
            }

            // Вычисление следующего приближения
            xNext = x1 - fx1 * (x1 - x0) / (fx1 - fx0);

            // сохрнение результатов в StingBuilder
            logBuilder.append(String.format("x%d: x(%.6f) = %.6f - f(%.6f) * (%.6f - %.6f) / (f(%.6f) - f(%.6f)) = %.6f\n",
                    iteration, x1, x1, fx1, x1, x0, fx1, fx0, xNext));

            x0 = x1;
            x1 = xNext;
            iteration++;
        }

        LogFunctionMapper.logFunction(functionName, logBuilder.toString()); // Запись логов в файл

        return x1;
    }

    /**
     * записывает информацию о методе
     * @return описание метода
     */
    @Override
    protected String getDescription() {
        // Описание метода
        String log = "Метод секущих\n".toUpperCase();
        log += "Формула: x(n+1) = x(n) - f(x(n)) * (x(n) - x(n-1)) / (f(x(n)) - f(x(n-1)))\n";
        return log;
    }
}
