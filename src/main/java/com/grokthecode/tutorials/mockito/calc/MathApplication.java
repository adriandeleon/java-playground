package com.grokthecode.tutorials.mockito.calc;

public class MathApplication {

    //https://www.tutorialspoint.com/mockito/mockito_junit_integration.htm
    private CalculatorService calculatorService;

    public void setCalculatorService(final CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    public double add(final double input1, final double input2) {
        return calculatorService.add(input1, input2);
    }

    public double subtract(final double input1, final double input2) {
        return calculatorService.subtract(input1, input2);
    }

    public double multiply(final double input1, final double input2) {
        return calculatorService.multiply(input1, input2);
    }

    public double divide(final double input1, final double input2) {
        return calculatorService.divide(input1, input2);
    }
}
