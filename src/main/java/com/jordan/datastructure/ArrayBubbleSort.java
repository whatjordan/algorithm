package com.jordan.datastructure;

import java.util.Comparator;

import com.jordan.datastructure.adt.ArrayADT;

public class ArrayBubbleSort <T> extends ArrayADT<T>{
	
	public ArrayBubbleSort(T[] array){
		super(array);
	}

	public static <T> T[] sort(T[] array, Comparator<T> comparator) {

		if (array.length < 1) {
			return array;
		}

		for (int i = array.length - 1; i > 0; i--) {
			boolean hasSwap = false;
			for (int j = 0; j < i; j++) {
				if (comparator.compare(array[j], array[j + 1]) > 0) {
					hasSwap = true;
					swap(array, j, j + 1);
				}
			}
			if (!hasSwap) {
				return array;
			}
		}

		return array;

	}

	private static <T> void swap(T[] array, int index1, int index2) {
		T temp = array[index1];
		array[index1] = array[index2];
		array[index2] = temp;
	}

	

}
