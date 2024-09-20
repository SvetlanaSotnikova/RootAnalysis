import org.utm.logger.Logger;
import org.utm.methods.BisectionMethod;
import org.utm.methods.NewtonMethod;
import org.utm.methods.SecantMethod;
import org.utm.methods.SuccessiveApproximations;
import org.utm.utils.RealRoots;

public class Main {

    public static void main(String[] args) {
        // для начала необходимо найти интервал для дальнейших вычислений
        RealRoots.findInterval(RealRoots::functionA, -20, 20, 1);
        System.out.println("Интервал: " + RealRoots.getInterval());
        Logger.logFunctionA("Интервал" + RealRoots.getInterval());
        // 1) метод половинного деления
        BisectionMethod.initBisectionMethod("A",RealRoots::functionA);
        // 2.1) метод итераций
        SuccessiveApproximations.initSuccessiveApproximations();
        // 2.2) метод Ньютона
        NewtonMethod.initNewtonMethod();
        // 2.3) Метод Секущих
        SecantMethod.initSecantMethod();
    }
}
