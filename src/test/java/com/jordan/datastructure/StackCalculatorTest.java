package com.jordan.datastructure;

import com.jordan.datastructure.list.LinkedList;
import com.jordan.datastructure.stack.StackCalculator;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class StackCalculatorTest {
    @Test
    public void test() {
        String formula = "(1.3+2.5)*3+4.1*3.25*(5.1+2)";
        print(StackCalculator.toPostfix(formula));
        Assert.assertEquals(new BigDecimal("106.0075").doubleValue(),
                StackCalculator.calculate(formula).doubleValue(),0.001);
    }

    private void print(LinkedList linkedList) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < linkedList.size(); i++) {
            result.append(linkedList.get(i));
        }
        System.out.println(result.toString());
    }
}
