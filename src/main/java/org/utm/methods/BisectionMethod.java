package org.utm.methods;

import org.utm.logger.Writer;
import org.utm.utils.Epsilons;
import org.utm.utils.RealRoots;
import org.utm.logger.LogWriter;

/**
 * Класс, реализующий метод половинного деления для нахождения корней функции.
 * Метод разделяет интервал [a, b] и ищет точку, где функция меняет знак,
 * постепенно уменьшая интервал до достижения заданной точности.
 */
public class BisectionMethod {
    /**
     * переменные для логирования
     */
    private static final String LOG_FILE_FUNCTION_A = "src/main/resources/methodsFunctionA.txt";
    private static final LogWriter logWriterA = msg -> Writer.writeObject(msg, LOG_FILE_FUNCTION_A);

    /**
     * Точность вычислений (epsilon)
     */
    private static final double epsilon = Epsilons.epsilonBisection; //1e-2

    /**
     * Инициализация метода половинного деления. Осуществляет запись
     * описания метода в лог и выполняет сам метод.
     */
    public static void initBisectionMethod() {
        // запись описания метода
        writeDescriptionMethodToFile();
        // определение корня
        double root = bisectionMethodForFunctionA();
        System.out.println("Приблеженное значение корня: " + root);
        // проверка корня
        verifyRoot(root);
    }

    /**
     * Краткое описание метода для лог файла результатов
     */
    private static void writeDescriptionMethodToFile() {
        // объяснение что это за метод
        String log = "метод половинного деления\n".toUpperCase();
        // запись нашего интервала в файла

        log += String.format("Формула: (a + b)/2, где a - начало интервала [%f], b - конец интевала [%f]",
                RealRoots.alfa, RealRoots.beta);

        // запись информации о методе в файл
        logWriterA.log(log);
    }

    /**
     * Метод для нахождения корня функции методом половинного деления
     *
     * @return приближенное значение корня для функции из пункта (а)
     */
    private static double bisectionMethodForFunctionA() {

//      констнатные значения нашего интервала [a, b]
//      a - alfa
//      b - betta
//      переменные должны менятся поэтому их значения перезаписанны
//      внутри функции а не в виде константы в этом классе
        double alfa = RealRoots.alfa;
        double beta = RealRoots.beta;
        double fa = RealRoots.functionA(alfa);
        double fb = RealRoots.functionA(beta);

        if (fa * fb >= 0) {
            logWriterA.log("Функция не меняет знак на конца интервала!!\n");
            // ловим исключение
            throw new IllegalArgumentException("Функция не меняет знак на конца интервала");
        }
        StringBuilder logBuilder = new StringBuilder();
        logBuilder.append("Результаты половинного деления:\n");

        double c = 0;
        int iteration = 0;

        // начало итераций вычисления корня
        while ((beta - alfa) / 2 >= epsilon) {
            c = (alfa + beta) / 2;
            double fc = RealRoots.functionA(c);
            iteration++;

            // Проверка близости значения к корню
            if (Math.abs(fc) < epsilon) {
                break;
            }

            // последовательная запись итераций
            logBuilder.append(String.format("x%d: a: %.4f, b: %.4f, c: %.4f, f(c) = %.4f\n",
                    iteration, alfa, beta, c, RealRoots.functionA(c)));

            // Определяем новый интервал
            if (fc * fa < 0) {  // [alfa, c]
                beta = c;
                fb = fc;
            } else {            // [c, beta]
                alfa = c;
                fa = fc;
            }
        }
        logWriterA.log(logBuilder.toString());
        return c;
    }

    /**
     * Проверка корректности найденного корня.
     * @param root найденное приближенное значение корня.
     */
    private static void verifyRoot(double root) {
        double functionValue = RealRoots.functionA(root);
        System.out.printf("Проверка корня: f(%f) = %.10f < %f\n", root, functionValue, epsilon);
    }

}
