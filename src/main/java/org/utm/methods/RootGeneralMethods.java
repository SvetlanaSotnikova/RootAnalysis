package org.utm.methods;

import org.utm.logger.LogFunctionMapper;

import java.util.function.Function;

public abstract class RootGeneralMethods {

    /**
     * инициализация метода
     * @param functionName буква/имя нашей функции A, B
     * @param function вычисляемая функция
     */
    public void initMethod(String functionName, Function<Double, Double> function) {
        writeDescriptionMethodToFile(functionName);
        double root = findRoot(function, functionName);
        System.out.println("Приближенное значение корня: " + root);
        verifyRoot(root, function);
    }

    /**
     * общий метод для поиска корней
     * @param function вычисляемая функция
     * @param functionName буква функции A, B
     * @return последний приблизительный корень уравнения
     */
    protected abstract double findRoot(Function<Double, Double> function, String functionName);

    /**
     * метод для записи логов в файл
     * @param functionName имя/буква функции для опредления лог файла
     */
    protected void writeDescriptionMethodToFile(String functionName) {
        // Запись описания метода в лог
        String log = getDescription();
        // запись непосредственно в файл
        LogFunctionMapper.logFunction(functionName, log);
    }

    /**
     * запись описания метода
     * @return описание в фрмате String
     */
    protected abstract String getDescription();

    /**
     * Проверка корректности найденного корня.
     * @param root – найденное приближенное значение корня.
     * @param function - функция для проверки
     */
    protected void verifyRoot(double root, Function<Double, Double> function) {
        double functionValue = function.apply(root);
        System.out.printf("Проверка корня: f(%f) = %f\n", root, functionValue);
    }
}
