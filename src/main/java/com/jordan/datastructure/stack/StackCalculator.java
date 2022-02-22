package com.jordan.datastructure.stack;

import com.jordan.datastructure.list.SimpleLinkedList;
import org.apache.commons.lang3.math.NumberUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class StackCalculator {

    public static Double calculate(String formula) {
        SimpleLinkedList postfix = toBetterPostfix(formula);
        LinkedStack linkedStack = new LinkedStack();
        for (int i = 0; i < postfix.size(); i++) {
            String value = (String) postfix.get(i);
            if (NumberUtils.isCreatable(value)) {
                linkedStack.push(value);
            } else {
                BigDecimal decimal2 = new BigDecimal((String) linkedStack.pop());
                BigDecimal decimal1 = new BigDecimal((String) linkedStack.pop());
                BigDecimal tmp;
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
                        tmp = decimal1.divide(decimal2, 5, RoundingMode.HALF_UP);
                        break;
                    default:
                        throw new IllegalArgumentException("unsupported operator: " + value);
                }
                linkedStack.push(tmp.toString());
            }
        }
        return Double.parseDouble((String) linkedStack.pop());
    }

    public static SimpleLinkedList toPostfix(String formula) {
        LinkedStack operatorStack = new LinkedStack();
        formula = formula.replaceAll("\\s+", "");
        SimpleLinkedList result = new SimpleLinkedList();
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
                if (operatorStack.isEmpty()) {
                    operatorStack.push(s);
                } else {
                    String tmpSymbol;
                    switch (s) {
                        case "+":
                        case "-":
                            while ((tmpSymbol = (String) operatorStack.peek()) != null) {
                                if (tmpSymbol.equals("*") || tmpSymbol.equals("/")) {
                                    result.add(operatorStack.pop());
                                } else {
                                    break;
                                }
                            }
                            operatorStack.push(s);
                            break;
                        case "*":
                        case "/":
                        case "(":
                            operatorStack.push(s);
                            break;
                        case ")":
                            while ((tmpSymbol = (String) operatorStack.pop()) != null) {
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
        while (operatorStack.peek() != null) {
            result.add(operatorStack.pop());
        }
        return result;
    }

    public static SimpleLinkedList toBetterPostfix(String formula) {
        LinkedStack operatorStack = new LinkedStack();
        formula = formula.replaceAll("\\s+", "");
        SimpleLinkedList result = new SimpleLinkedList();
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
                if (operatorStack.isEmpty()) {
                    operatorStack.push(s);
                } else {
                    String tmpSymbol;
                    switch (s) {
                        case "+":
                        case "-":
                            if ((tmpSymbol = (String) operatorStack.peek()) != null
                                    && (tmpSymbol.equals("*") || tmpSymbol.equals("/"))) {
                                while (tmpSymbol != null) {
                                    if (tmpSymbol.equals("*") || tmpSymbol.equals("/")) {
                                        result.add(operatorStack.pop());
                                        tmpSymbol = (String) operatorStack.peek();
                                    } else if (tmpSymbol.equals("+") || tmpSymbol.equals("-")) {
                                        result.add(operatorStack.pop());
                                        break;
                                    } else {
                                        break;
                                    }
                                }
                            }
                            operatorStack.push(s);
                            break;
                        case "*":
                        case "/":
                        case "(":
                            operatorStack.push(s);
                            break;
                        case ")":
                            while ((tmpSymbol = (String) operatorStack.pop()) != null) {
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
        while (operatorStack.peek() != null) {
            result.add(operatorStack.pop());
        }
        return result;
    }

}
