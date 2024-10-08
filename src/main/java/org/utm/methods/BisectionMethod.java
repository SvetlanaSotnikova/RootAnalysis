package org.utm.methods;

import org.utm.logger.LogFunctionMapper;
import org.utm.utils.Epsilons;
import org.utm.utils.Interval;

import java.util.function.Function;

/**
 * Класс, реализующий метод половинного деления для нахождения корней функции.
 * Метод разделяет интервал [a, b] и ищет точку, где функция меняет знак,
 * постепенно уменьшая интервал до достижения заданной точности.
 */
public class BisectionMethod extends RootGeneralMethods {
    /**
     * Точность вычислений (epsilon)
     */
    private static final double epsilon = Epsilons.epsilonBisection; //1e-2

    private double alfa;
    private double beta;

    public BisectionMethod(Interval interval) {
        this.alfa = interval.alfa();
        this.beta = interval.beta();
    }

    /**
     * алгоритм метода секущих
     * @param function - функция, которую мы на данный момент вычиляем A or B
     * @param functionName - буквы нашей функции
     * @return возвращает последний приблизительный корень уравлнения
     */
    @Override
    protected double findRoot(Function<Double, Double> function, String functionName) {
        double fa = function.apply(alfa);
        double fb = function.apply(beta);

        if (fa * fb >= 0) {
            // логируем исключение
            LogFunctionMapper.logFunction(functionName, "Функция не меняет знак на конца интервала");
            // ловим исключение
            throw new IllegalArgumentException("Функция не меняет знак на конца интервала");
        }
        StringBuilder logBuilder = new StringBuilder();
        logBuilder.append("\nРезультаты половинного деления:\n");

        double c = 0; //значение результата итераций
        int iteration = 0; // counter

        // начало итераций вычисления корня
        while ((beta - alfa) / 2 >= epsilon) {
            c = (alfa + beta) / 2;
            double fc = function.apply(c);
            iteration++;

            // Проверка близости значения к корню
            if (Math.abs(fc) < epsilon) {
                break;
            }

            // последовательная запись итераций в StingBuilder
            logBuilder.append(String.format("x%d: a: %.4f, b: %.4f, c: %.4f, f(c) = %.4f\n",
                    iteration, alfa, beta, c, function.apply(c)));

            // Определяем новый интервал
            if (fc * fa < 0) {  // [alfa, c]
                beta = c;
            } else {            // [c, beta]
                alfa = c;
            }
        }
        // логирование
        LogFunctionMapper.logFunction(functionName, logBuilder.toString());
        // возврат конечного результата
        return c;
    }


    /**
     * запись описания
     * @return возвращает описание метода
     */
    @Override
    protected String getDescription() {
        // объяснение что это за метод
        String log = "метод половинного деления\n".toUpperCase();
        // запись нашего интервала в файла

        log += String.format("Формула: (a + b)/2, где a - начало интервала [%f], b - конец интевала [%f]",
                alfa, beta);
        return log;
    }
}
