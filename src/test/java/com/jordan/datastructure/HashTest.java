package com.jordan.datastructure;

import com.jordan.datastructure.hash.OpenAddressingLinkedHashTable;
import org.junit.Assert;
import org.junit.Test;

public class HashTest {
    @Test
    public void testOpenAddressingLinkedHashTable() {
        OpenAddressingLinkedHashTable<Integer, Integer> table = new OpenAddressingLinkedHashTable<>();
        for(int i = 0; i < 1000; i++){
            table.put(i, i);
        }
        Assert.assertEquals(1000, table.size());
        Assert.assertTrue(table.containsKey(100));
        Assert.assertFalse(table.containsKey(1000));
        table.put(5, 1000);
        Assert.assertTrue(table.containsValue(1000));
    }
}
