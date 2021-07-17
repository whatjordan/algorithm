package com.jordan.datastructure;

import com.jordan.datastructure.string.ExhaustedMatch;
import org.junit.Assert;
import org.junit.Test;

public class StringTest {
    @Test
    public void testExhaustedMatch() {
        Assert.assertEquals(-1, ExhaustedMatch.indexOf("abcba", "bac"));
        Assert.assertEquals(1, ExhaustedMatch.indexOf("abcba", "bcb"));
    }

}
