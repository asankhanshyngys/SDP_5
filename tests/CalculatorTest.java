public class CalculatorTest {
    public static void main(String[] args) {
        addsTwoNumbers();
        subtractsTwoNumbers();
        multipliesTwoNumbers();
        switchesStrategyAtRuntime();
        System.out.println("All 4 tests passed.");
    }

    private static void addsTwoNumbers() {
        Calculator calculator = new Calculator(new AddStrategy());
        assertEquals(15.0, calculator.calculate(10, 5));
    }

    private static void subtractsTwoNumbers() {
        Calculator calculator = new Calculator(new SubtractStrategy());
        assertEquals(5.0, calculator.calculate(10, 5));
    }

    private static void multipliesTwoNumbers() {
        Calculator calculator = new Calculator(new MultiplyStrategy());
        assertEquals(50.0, calculator.calculate(10, 5));
    }

    private static void switchesStrategyAtRuntime() {
        Calculator calculator = new Calculator(new AddStrategy());
        calculator.setStrategy(new MultiplyStrategy());
        assertEquals(50.0, calculator.calculate(10, 5));
    }

    private static void assertEquals(double expected, double actual) {
        if (Double.compare(expected, actual) != 0) {
            throw new AssertionError("Expected " + expected + " but got " + actual);
        }
    }
}
