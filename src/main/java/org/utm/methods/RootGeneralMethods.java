package org.utm.methods;

import org.utm.logger.LogFunctionMapper;
import org.utm.utils.Epsilons;

import java.util.function.Function;

public abstract class RootGeneralMethods {

    public void initMethod(String functionName, Function<Double, Double> function) {
        writeDescriptionMethodToFile(functionName);
        double root = findRoot(function, functionName);
        System.out.println("Приближенное значение корня: " + root);
        verifyRoot(root, function);
    }
    protected abstract double findRoot(Function<Double, Double> function, String functionName);

    protected void writeDescriptionMethodToFile(String functionName) {
        // Запись описания метода в лог
        String log = getDescription();
        LogFunctionMapper.logFunction(functionName, log);
    }

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
