package com.jordan.datastructure;

import com.jordan.algorithm.string.ExhaustedMatch;
import com.jordan.algorithm.string.LongestCommonSubSequence;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class StringTest {
    @Test
    public void testExhaustedMatch() {
        Assert.assertEquals(-1, ExhaustedMatch.indexOf("abcba", "bac"));
        Assert.assertEquals(1, ExhaustedMatch.indexOf("abcba", "bcb"));
    }

    @Test
    public void testFindLcsTable() {
        String s1 = "GTACCGTCA";
        String s2 = "CATCGA";
        System.out.println(LongestCommonSubSequence.findLCS(s1, s2));
    }

}
