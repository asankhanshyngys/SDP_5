# Assignment 5 Strategy Pattern

## 1 Introduction

This project uses Option B, a calculator. Addition, subtraction, and
multiplication form a family of interchangeable algorithms. Each operation
implements `OperationStrategy`. The `Calculator` stores a strategy and delegates
the calculation to it. `Main` selects the operation and changes it at runtime.

## 2 UML class diagram

```mermaid
classDiagram
    class OperationStrategy {
        <<interface>>
        +execute(double firstNumber, double secondNumber) double
    }
    class AddStrategy {
        +execute(double firstNumber, double secondNumber) double
    }
    class SubtractStrategy {
        +execute(double firstNumber, double secondNumber) double
    }
    class MultiplyStrategy {
        +execute(double firstNumber, double secondNumber) double
    }
    class Calculator {
        -OperationStrategy strategy
        +Calculator(OperationStrategy strategy)
        +setStrategy(OperationStrategy strategy) void
        +calculate(double firstNumber, double secondNumber) double
    }
    class Main {
        +main(String[] args) void
    }
    OperationStrategy <|.. AddStrategy
    OperationStrategy <|.. SubtractStrategy
    OperationStrategy <|.. MultiplyStrategy
    Calculator o--> OperationStrategy : holds and delegates
    Main ..> Calculator : uses
    Main ..> AddStrategy : selects
    Main ..> SubtractStrategy : selects
    Main ..> MultiplyStrategy : selects
```

## 3 Clean code principles

### Classes should be small

Each strategy has one short method. For example, `AddStrategy` contains only
the addition algorithm, so its behavior can be understood immediately.

```java
public class AddStrategy implements OperationStrategy {
    @Override
    public double execute(double firstNumber, double secondNumber) {
        return firstNumber + secondNumber; // One small algorithm.
    }
}
```

### Single Responsibility Principle

Each class has one responsibility. Strategies perform arithmetic, `Calculator`
delegates execution, and `Main` configures the strategies and prints the demo.
Changing console output does not require changing an arithmetic class.

```java
// Calculator delegates; it does not select algorithms or print results.
public double calculate(double firstNumber, double secondNumber) {
    return strategy.execute(firstNumber, secondNumber);
}
```

### Cohesion

The calculator's methods work with the same private strategy field. The
constructor initializes it through `setStrategy`, the setter replaces it,
and `calculate` uses it. These methods support the same focused purpose.

```java
private OperationStrategy strategy;

public void setStrategy(OperationStrategy strategy) {
    // Update the same field used by calculate.
    this.strategy = Objects.requireNonNull(strategy, "Strategy is required");
}
```

## 4 Conclusion

The same calculator object can add, subtract, multiply, and switch back to
addition. Its context contains no `if` or `switch` for operation types because
polymorphism selects the implementation of `execute`.

A new operation needs another implementation of `OperationStrategy` and client
code that selects it. Existing algorithms and `Calculator` do not need to change.
This flexibility adds several small classes. For a basic calculator that is
extra structure, but it clearly demonstrates how Strategy separates algorithms
from the object that uses them.

## 5 GitHub repository

Repository link: **Add the GitHub URL after publishing this project.**
