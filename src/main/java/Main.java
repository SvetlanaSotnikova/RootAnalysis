import org.utm.logger.Logger;
import org.utm.methods.BisectionMethod;
import org.utm.methods.NewtonMethod;
import org.utm.methods.SecantMethod;
import org.utm.methods.SuccessiveApproximations;
import org.utm.utils.Interval;
import org.utm.utils.RealRoots;

public class Main {

    public static void main(String[] args) {
        // 1. Поиск интервала для функции A
        Interval intervalA = RealRoots.findInterval(RealRoots::functionA, -20, 20, 1);
        String intervalAStr = RealRoots.getInterval(); // Получаем интервал для A
        System.out.println("Интервал функции A: " + intervalAStr);
        Logger.logFunctionA("Интервал: " + intervalAStr);

        // Создаем объекты методов для функции A
        BisectionMethod bisectionMethodA = new BisectionMethod(intervalA);
        SuccessiveApproximations successiveApproximationsA = new SuccessiveApproximations(intervalA);
        NewtonMethod newtonMethodA = new NewtonMethod(intervalA);
        SecantMethod secantMethodA = new SecantMethod(intervalA);

        // 1) Метод половинного деления для A
        bisectionMethodA.initMethod("A", RealRoots::functionA);
        // 2.1) Метод итераций для A
        successiveApproximationsA.initMethod("A", RealRoots::functionA);
        // 2.2) Метод Ньютона для A
        newtonMethodA.initMethod("A", RealRoots::functionA);
        // 2.3) Метод секущих для A
        secantMethodA.initMethod("A", RealRoots::functionA);

        // 2. Поиск интервала для функции B
        Interval intervalB = RealRoots.findInterval(RealRoots::functionB, -20, 20, 1);
        String intervalBStr = RealRoots.getInterval(); // Получаем интервал для B
        System.out.println("Интервал функции B: " + intervalBStr);
        Logger.logFunctionB("Интервал: " + intervalBStr);

        // Создаем объекты методов для функции B
        BisectionMethod bisectionMethodB = new BisectionMethod(intervalB);
        SuccessiveApproximations successiveApproximationsB = new SuccessiveApproximations(intervalB);
        NewtonMethod newtonMethodB = new NewtonMethod(intervalB);
        SecantMethod secantMethodB = new SecantMethod(intervalB);

        // 1) Метод половинного деления для B
        bisectionMethodB.initMethod("B", RealRoots::functionB);
        // 2.1) Метод итераций для B
        successiveApproximationsB.initMethod("B", RealRoots::functionB);
        // 2.2) Метод Ньютона для B
        newtonMethodB.initMethod("B", RealRoots::functionB);
        // 2.3) Метод секущих для B
        secantMethodB.initMethod("B", RealRoots::functionB);
    }
}
