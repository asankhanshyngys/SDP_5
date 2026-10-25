import java.util.Objects;

public class Calculator {
    private OperationStrategy strategy;

    public Calculator(OperationStrategy strategy) {
        setStrategy(strategy);
    }

    public void setStrategy(OperationStrategy strategy) {
        this.strategy = Objects.requireNonNull(strategy, "Strategy is required");
    }

    public double calculate(double firstNumber, double secondNumber) {
        return strategy.execute(firstNumber, secondNumber);
    }
}
