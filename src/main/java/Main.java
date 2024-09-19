import org.utm.logger.Writer;
import org.utm.methods.BisectionMethod;
import org.utm.methods.SuccessiveApproximations;
import org.utm.utils.RealRoots;

public class Main {
    private static final String LOG_FILE_FUNCTION_A = "src/main/resources/methodsFunctionA.txt";

    public static void main(String[] args) {
        // для начала необходимо найти интервал для дальнейших вычислений
        RealRoots.findInterval(-20, 20, 1);
        System.out.println("Интервал: " + RealRoots.getInterval());
        Writer.writeObject("Интервал" + RealRoots.getInterval(), LOG_FILE_FUNCTION_A);
        // 1) метод половинного деления
        BisectionMethod.initBisectionMethod();
        // 2.1) метод итераций
        SuccessiveApproximations.initSuccessiveApproximations();
    }
}
