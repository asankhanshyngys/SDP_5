public class Main {
    public static void main(String[] args) {
        Calculator calculator = new Calculator(new AddStrategy());
        System.out.println("10 + 5 = " + calculator.calculate(10, 5));

        calculator.setStrategy(new SubtractStrategy());
        System.out.println("10 - 5 = " + calculator.calculate(10, 5));

        calculator.setStrategy(new MultiplyStrategy());
        System.out.println("10 * 5 = " + calculator.calculate(10, 5));

        calculator.setStrategy(new AddStrategy());
        System.out.println("2 + 3 = " + calculator.calculate(2, 3));
    }
}
