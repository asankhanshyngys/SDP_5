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

The implementation applies five principles from Chapters 9, 10, and 11.

### Principle 1 Single concept per test from Chapter 9

Each test checks one behavior: addition, subtraction, multiplication, or runtime
strategy switching. Tests create their own calculator and have one assertion,
so they do not depend on shared state or execution order. This keeps failures
easy to understand. The tests use a small assertion helper with no libraries.

```java
private static void addsTwoNumbers() {
    Calculator calculator = new Calculator(new AddStrategy());
    assertEquals(15.0, calculator.calculate(10, 5)); // Checks only addition.
}
```

### Principle 2 Single Responsibility Principle from Chapter 10

Each class has one responsibility. Strategies perform arithmetic, `Calculator`
delegates execution, and `Main` configures the strategies and prints the demo.
Changing console output does not require changing an arithmetic class.

```java
// Calculator delegates; it does not select algorithms or print results.
public double calculate(double firstNumber, double secondNumber) {
    return strategy.execute(firstNumber, secondNumber);
}
```

### Principle 3 Cohesion from Chapter 10

The methods in `Calculator` work with the same private strategy field. The
constructor initializes it through `setStrategy`, the setter updates it, and
`calculate` uses it. These related methods belong together because they support
the calculator's focused responsibility. No unrelated data or behavior is added.

```java
private OperationStrategy strategy; // Shared state for the related methods.

public void setStrategy(OperationStrategy strategy) {
    this.strategy = Objects.requireNonNull(strategy, "Strategy is required");
}

public double calculate(double firstNumber, double secondNumber) {
    return strategy.execute(firstNumber, secondNumber); // Uses the same field.
}
```

### Principle 4 Isolating from change from Chapter 10

`Calculator` depends on the `OperationStrategy` interface, so a change to an
arithmetic implementation does not require changing the context. For example,
division can be added by implementing this interface in a new class and selecting
it in `Main`. The existing context and strategies stay unchanged. The client
still needs to configure the new operation.

```java
// The context depends on the contract, not a concrete arithmetic class.
private OperationStrategy strategy;

// Every algorithm supplies this same interface method.
double execute(double firstNumber, double secondNumber);
```

### Principle 5 Separate construction from use from Chapter 11

`Main` constructs the concrete strategies and supplies them to `Calculator`.
The calculator only uses the supplied interface and does not create its own
arithmetic algorithms. Passing the strategy through the constructor or setter
is a simple form of dependency injection; no framework is needed.

```java
// Main constructs and supplies the concrete algorithm.
Calculator calculator = new Calculator(new AddStrategy());

// Calculator uses the supplied algorithm through the interface.
public double calculate(double firstNumber, double secondNumber) {
    return strategy.execute(firstNumber, secondNumber);
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
