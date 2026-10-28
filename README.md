# Assignment 5 Strategy Pattern

A small Java calculator demonstrating the Strategy pattern with addition,
subtraction, and multiplication. No external libraries are needed.

## Run

Requires JDK 17 or newer. Run these commands from the project folder:

```sh
javac --release 17 -d out src/*.java
java -cp out Main
```

In IntelliJ IDEA, open this folder, select JDK 17 or newer, mark `src` as
Sources Root if needed, and run `Main`.

Expected output:

```text
10 + 5 = 15.0
10 - 5 = 5.0
10 * 5 = 50.0
2 + 3 = 5.0
```

## Files

- `src/OperationStrategy.java`: shared algorithm interface.
- `src/AddStrategy.java`, `src/SubtractStrategy.java`, `src/MultiplyStrategy.java`: algorithms.
- `src/Calculator.java`: context that delegates to the selected strategy.
- `src/Main.java`: client that switches strategies at runtime.
- `tests/CalculatorTest.java`: four independent tests with one concept each.
- `REPORT.docx`: Word report with UML and five clean code principles drawn
  from Chapters 9, 10, and 11, each justified with an annotated code excerpt.
- `REPORT.md`: editable Markdown version of the reflection.

## Tests

Run from the project folder:

```sh
javac --release 17 -d out src/*.java tests/*.java
java -cp out CalculatorTest
```

Expected output: `All 4 tests passed.`

## Submission

The folder includes a local Git repository with incremental commits and the
Word report. The Markdown version also contains a Mermaid diagram.

## Defense notes

- Strategy groups interchangeable algorithms behind one interface.
- `Calculator` calls `execute` without checking the concrete strategy type.
- `setStrategy` changes the operation on the same calculator object at runtime.
- To add division, create `DivideStrategy implements OperationStrategy` and
  select it in the client. The calculator and existing strategies stay unchanged.
- The tradeoff is a few extra classes for a calculator that could otherwise
  use simple arithmetic directly.
