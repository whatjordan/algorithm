package com.jordan.datastrcture;

import java.util.Arrays;

import org.junit.Test;

import com.jordan.datastructure.ArrayBubbleSort;

public class BubbleSortTest {
	private Integer[] array = new Integer[] { 2, 4, 1, 3 };

	@Test
	public void testSort() {
		Integer[] result = ArrayBubbleSort
				.sort(array, (Integer data1, Integer data2) -> data1.compareTo(data2));
		System.out.println(Arrays.toString(result));
	}

	@Test
	public void testInsert() {
		ArrayBubbleSort<Integer> bubbleSort = new ArrayBubbleSort<>(array);
		bubbleSort.insert(0, new Integer(5));
		System.out.println(Arrays.toString(bubbleSort.getArray()));
	}
	
	@Test
	public void testRemove(){
		ArrayBubbleSort<Integer> bubbleSort = new ArrayBubbleSort<>(array);
		bubbleSort.remove(-1);
		System.out.println(Arrays.toString(bubbleSort.getArray()));
	}

}
