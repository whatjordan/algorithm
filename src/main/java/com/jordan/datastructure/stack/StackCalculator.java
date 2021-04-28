package com.jordan.datastructure.stack;

import com.jordan.datastructure.list.LinkedList;
import org.apache.commons.lang3.math.NumberUtils;

import java.math.BigDecimal;

public class StackCalculator {

    public static Double calculate(String formula) {
        Double result = null;
        LinkedList postfix = toPostfix(formula);
        LinkedStack linkedStack = new LinkedStack();
        for (int i = 0; i < postfix.size(); i++) {
            String value = (String) postfix.get(i);
            if (NumberUtils.isCreatable(value)) {
                linkedStack.push(value);
            } else {
                BigDecimal decimal2 = new BigDecimal((String) linkedStack.pop());
                BigDecimal decimal1 = new BigDecimal((String) linkedStack.pop());
                BigDecimal tmp = new BigDecimal(0);
                switch (value) {
                    case "+":
                        tmp = decimal1.add(decimal2);
                        break;
                    case "-":
                        tmp = decimal1.subtract(decimal2);
                        break;
                    case "*":
                        tmp = decimal1.multiply(decimal2);
                        break;
                    case "/":
                        tmp = decimal1.divide(decimal2);
                        break;
                }
                linkedStack.push(tmp.toString());
            }
        }
        return Double.parseDouble((String) linkedStack.pop());
    }


    public static LinkedList toPostfix(String formula) {
        LinkedStack linkedStack = new LinkedStack();
        formula = formula.replaceAll("\\s+", "");
        LinkedList result = new LinkedList();
        StringBuilder numberTmp = new StringBuilder();
        for (int i = 0; i < formula.length(); i++) {
            String s = String.valueOf(formula.charAt(i));
            if (NumberUtils.isCreatable(s)) {
                numberTmp.append(s);
            } else {
                if (s.equals(".")) {
                    numberTmp.append(s);
                    continue;
                }
                if (numberTmp.length() > 0) {
                    result.add(numberTmp.toString());
                    numberTmp = new StringBuilder();
                }
                if (linkedStack.isEmpty()) {
                    linkedStack.push(s);
                } else {
                    String tmpSymbol;
                    switch (s) {
                        case "+":
                        case "-":
                            while ((tmpSymbol = (String) linkedStack.peek()) != null) {
                                if (tmpSymbol.equals("*") || tmpSymbol.equals("/")) {
                                    result.add(linkedStack.pop());
                                } else {
                                    break;
                                }
                            }
                            linkedStack.push(s);
                            break;
                        case "*":
                        case "/":
                        case "(":
                            linkedStack.push(s);
                            break;
                        case ")":
                            while ((tmpSymbol = (String) linkedStack.pop()) != null) {
                                if (!tmpSymbol.equals("(")) {
                                    result.add(tmpSymbol);
                                } else {
                                    break;
                                }
                            }
                            break;
                    }
                }
            }
        }
        if (numberTmp.length() > 0) {
            result.add(numberTmp.toString());
        }
        while (linkedStack.peek() != null) {
            result.add(linkedStack.pop());
        }
        return result;
    }

}
