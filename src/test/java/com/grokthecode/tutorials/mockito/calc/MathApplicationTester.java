package com.grokthecode.tutorials.mockito.calc;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class) //https://stackoverflow.com/questions/40961057/how-to-use-mockito-with-junit5
public class MathApplicationTester {

    @InjectMocks
    MathApplication mathApplication = new MathApplication();

    @Mock
    CalculatorService calculatorService;

    @Test
    public void testAdd(){
        when(calculatorService.add(10.0, 20.0)).thenReturn(30.00);
        assertEquals(mathApplication.add(10.0, 20.0), 30.0, 0);
    }
}
