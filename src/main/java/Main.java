import org.utm.logger.Logger;
import org.utm.methods.BisectionMethod;
import org.utm.methods.NewtonMethod;
import org.utm.methods.SecantMethod;
import org.utm.methods.SuccessiveApproximations;
import org.utm.utils.RealRoots;

public class Main {

    public static void main(String[] args) {
//         для начала необходимо найти интервал для дальнейших вычислений
//        RealRoots.findInterval(RealRoots::functionA, -20, 20, 1);
//        System.out.println("Интервал функции A: " + RealRoots.getInterval());
//        Logger.logFunctionA("Интервал" + RealRoots.getInterval());

        // для начала необходимо найти интервал для дальнейших вычислений
        RealRoots.findInterval(RealRoots::functionB, -20, 20, 1);
        System.out.println("Интервал функции B: " + RealRoots.getInterval());
        Logger.logFunctionB("Интервал" + RealRoots.getInterval());

        BisectionMethod bisectionMethod = new BisectionMethod();
        SuccessiveApproximations successiveApproximations = new SuccessiveApproximations();
        NewtonMethod newtonMethod = new NewtonMethod();
        SecantMethod secantMethod = new SecantMethod();

//        // 1) метод половинного деления
//        bisectionMethod.initMethod("A", RealRoots::functionA);
//        // 2.1) метод итераций
//        successiveApproximations.initMethod("A", RealRoots::functionA);
//        // 2.2) метод Ньютона
//        newtonMethod.initMethod("A", RealRoots::functionA);
//        // 2.3) Метод Секущих
//        secantMethod.initMethod("A", RealRoots::functionA);

        // 1) метод половинного деления
        bisectionMethod.initMethod("B", RealRoots::functionB);
        // 2.1) метод итераций
        successiveApproximations.initMethod("B", RealRoots::functionB);
        // 2.2) метод Ньютона
        newtonMethod.initMethod("B", RealRoots::functionB);
        // 2.3) Метод Секущих
        secantMethod.initMethod("B", RealRoots::functionB);
    }
}
