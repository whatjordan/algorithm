package com.jordan.datastructure;

import com.jordan.datastructure.adt.StackADT;
import com.jordan.datastructure.stack.ArrayStack;
import com.jordan.datastructure.stack.LinkedStack;
import org.junit.Assert;
import org.junit.Test;

public class StackTest {
    @Test(expected = IndexOutOfBoundsException.class)
    public void testArrayStack() {
        StackADT stackADT = new ArrayStack(2);
        stackADT.push(1);
        stackADT.push(2);
        Assert.assertEquals(2, stackADT.size());
        Assert.assertEquals(2, stackADT.pop());
        Assert.assertEquals(1, stackADT.pop());
        stackADT.push(1);
        stackADT.push(2);
        stackADT.push(3);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testLinkedStack() {
        LinkedStack linkedStack = new LinkedStack();
        linkedStack.push(1);
        linkedStack.push(2);
        linkedStack.push(3);
        Assert.assertEquals(3, linkedStack.size());
        Assert.assertEquals(3, linkedStack.pop());
        Assert.assertEquals(2, linkedStack.pop());
        Assert.assertEquals(1, linkedStack.pop());
        linkedStack.pop();
    }
}
