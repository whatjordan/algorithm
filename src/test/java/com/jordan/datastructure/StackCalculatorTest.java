package com.jordan.datastructure;

import com.jordan.datastructure.list.SimpleLinkedList;
import com.jordan.datastructure.stack.StackCalculator;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

public class StackCalculatorTest {
    @Test
    public void test() {
        String formula = "2+(1.3+2.5)*3+4.1*3.25*(5.1+2)";
        print(StackCalculator.toPostfix(formula));
        print(StackCalculator.toBetterPostfix(formula));
        Assert.assertEquals(new BigDecimal("108.0075").doubleValue(),
                StackCalculator.calculate(formula).doubleValue(),0.001);
    }

    private void print(SimpleLinkedList simpleLinkedList) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < simpleLinkedList.size(); i++) {
            result.append(simpleLinkedList.get(i) + " ");
        }
        System.out.println(result.toString());
    }
}
