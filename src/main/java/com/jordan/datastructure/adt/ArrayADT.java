package com.jordan.datastructure.adt;

import java.lang.reflect.Array;

public class ArrayADT <T>{
	private T[] array = null;
	private Class<?> clazz;
	private int length;
	public ArrayADT(Class<?> clazz, int length) {
		if(null == clazz){
			throw new NullPointerException(" ArrayADT's class can't not be null");
		}
		this.clazz = clazz;
		this.length = length;
	}
	
	public ArrayADT(T[] array) {
		if(null == array){
			throw new NullPointerException(" ArrayADT's array can't not be null");
		}
		this.array = array;
		clazz = array.getClass().getComponentType();
	}

	/** 
	 * @param index
	 * @param element
	 */
	public void insert(int index, T element) {
		if (index > array.length) {
			index = array.length;
		} else if (index < 0) {
			index = 0;
		}

		@SuppressWarnings("unchecked")
		final T[] newArray = (T[]) Array.newInstance(clazz, array.length + 1);
		for (int i = 0; i < index; i++) {
			newArray[i] = array[i];
		}
		newArray[index] = element;
		for (int i = index; i < array.length; i++) {
			newArray[i + 1] = array[i];
		}
		this.array = newArray;
	}

	public void remove(int index) {
		if(array.length == 0){
			return;
		}
		if (index >= array.length) {
			index = array.length -1;
		} else if (index < 0) {
			index = 0;
		}

		@SuppressWarnings("unchecked")
		T[] newArray = (T[]) Array.newInstance(clazz, array.length - 1);
		for (int i = 0; i < index ; i++) {
			newArray[i] = array[i];
		}

		for (int i = index + 1; i < array.length; i++) {
			newArray[i - 1] = array[i];
		}
		this.array = newArray;

	}
	
	public int length(){
		return array.length;
	}
	
	public T get(int index){
		return array[index];
	}
	
	
	public boolean isExist(T element){
		for(int i = 0; i < array.length ; i ++){
			if(array[i].equals(element)){
				return true;
			}
		}
		return false;
	}
	
	
	public T[] getArray() {
		return array;
	}

	public void setArray(T[] array) {
		this.array = array;
	}

}
