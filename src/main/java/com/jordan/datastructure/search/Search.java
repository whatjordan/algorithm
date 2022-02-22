package com.jordan.datastructure.search;

import com.jordan.datastructure.sort.Sort;
import com.jordan.datastructure.sort.SortFactory;

import java.util.Arrays;

public class Search {

    public static <T> int sentinelLinearSearch(T[] elements, T key) {
        if (elements == null || elements.length == 0) {
            return -1;
        }
        T lastEle = elements[elements.length - 1];
        elements[elements.length - 1] = key;
        int index = 0;
        while (true) {
            if (elements[index].equals(key)) {
                elements[elements.length - 1] = lastEle;
                if (index == elements.length - 1) {
                    if (lastEle.equals(key)) {
                        return index;
                    } else {
                        return -1;
                    }
                } else {
                    return index;
                }
            }
            index++;
        }
    }

    public static int sequentialSearchWithSentinel(Object[] array, Object key) {
        if (array == null || array.length == 0) {
            return -1;
        }
        Object sentinel = array[array.length - 1];
        int i = 0;
        while (!sentinel.equals(array[i])) {
            if (array[i].equals(key)) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public static <T extends Comparable> int binarySearch(T[] array, T key) {
        if (array == null || array.length == 0) {
            return -1;
        }
        WrapperElement[] wrapperArray = new WrapperElement[array.length];
        WrapperElement<T> wrapperKey = new WrapperElement<>(key, 0);
        for (int i = 0; i < array.length; i++) {
            wrapperArray[i] = new WrapperElement<>(array[i], i);
        }
        Sort<WrapperElement> sort = SortFactory.getSort(SortFactory.SortType.QUICK);
        WrapperElement[] sortedArray = sort.sort(wrapperArray, Comparable::compareTo);
        int high = array.length - 1;
        int low = 0;
        while (high >= low) {
            int middle = (high + low) / 2;
            if (sortedArray[middle].compareTo(wrapperKey) == 0) {
                return sortedArray[middle].getIndex();
            }
            if (sortedArray[middle].compareTo(wrapperKey) > 0) {
                high = middle - 1;
            } else {
                low = middle + 1;
            }
        }
        return -1;
    }

    public static <T extends Number> int interpolationSearch(T[] array, T key) {
        if (array == null || array.length == 0) {
            return -1;
        }
        WrapperNumber[] wrapperNumberArray = new WrapperNumber[array.length];
        for (int i = 0; i < array.length; i++) {
            wrapperNumberArray[i] = new WrapperNumber<>(array[i], i);
        }
        Sort<WrapperNumber> sort = SortFactory.getSort(SortFactory.SortType.QUICK);
        WrapperNumber[] sortedArray = sort.sort(wrapperNumberArray, Comparable::compareTo);
        int high = sortedArray.length - 1;
        int low = 0;
        while (high >= low) {
            int middle = low +
                    Math.round((key.floatValue() - sortedArray[low].getElement().floatValue())
                            / (sortedArray[high].getElement().floatValue() - sortedArray[low].getElement().floatValue())
                            * (high - low)); // Interpolation method
            if (sortedArray[middle].getElement().equals(key)) {
                return sortedArray[middle].getIndex();
            }
            if (sortedArray[middle].getElement().floatValue() - key.floatValue() > 0) {
                high = middle - 1;
            } else {
                low = middle + 1;
            }
        }
        return -1;
    }

    public static <T extends Comparable> int fibonacciSearch(T[] array, T key) {
        if (array == null || array.length == 0) {
            return -1;
        }
        WrapperElement[] wrapperArray = new WrapperElement[array.length];
        WrapperElement<T> wrapperKey = new WrapperElement<>(key, 0);
        for (int i = 0; i < array.length; i++) {
            wrapperArray[i] = new WrapperElement<>(array[i], i);
        }
        Sort<WrapperElement> sort = SortFactory.getSort(SortFactory.SortType.QUICK);
        sort.sort(wrapperArray, Comparable::compareTo);
        int low = 1, high = array.length, mid, k = 0;
        while (array.length > getFibonacci(k)) {
            k++;
        }
        int[] fibonacciArray = getFibonacciArray(k + 1);
        wrapperArray = Arrays.copyOf(wrapperArray, fibonacciArray[k]);
        for (int i = array.length; i < fibonacciArray[k] ; i++) {
            // The given array needs to be aligned.
            // Because the closest fibonacci number will lager than or equal to the size of given array.
            wrapperArray[i] = wrapperArray[array.length -1];
        }
        while (low <= high) {
            // |low|------------|mid|-----|high|
            // <******F[k-1]-1**><1><--F[k-2]-1>
            mid = low + fibonacciArray[k - 1] - 1 ;
            if (wrapperKey.compareTo(wrapperArray[mid]) < 0) {
                high = mid - 1;
                k = k - 1;
            } else if (wrapperKey.compareTo(wrapperArray[mid]) > 0) {
                low = mid + 1;
                k = k - 2;
            } else {
                return wrapperArray[mid].getIndex();
            }
        }
        return -1;
    }

    private static class WrapperElement<E extends Comparable> implements Comparable<WrapperElement> {
        private E element;
        private int index;

        public WrapperElement(E element, int index) {
            this.element = element;
            this.index = index;
        }

        public E getElement() {
            return element;
        }

        public void setElement(E element) {
            this.element = element;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        @Override
        public int compareTo(WrapperElement o) {
            if (element == null || o == null || o.getElement() == null) {
                return -1;
            }
            return element.compareTo(o.getElement());
        }
    }

    private static class WrapperNumber<E extends Number> implements Comparable<WrapperNumber> {
        private E element;
        private int index;

        public WrapperNumber(E element, int index) {
            this.element = element;
            this.index = index;
        }

        public E getElement() {
            return element;
        }

        public void setElement(E element) {
            this.element = element;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        @Override
        public int compareTo(WrapperNumber o) {
            if (element == null || o == null || o.getElement() == null) {
                return -1;
            }
            if (element.floatValue() - o.getElement().floatValue() > 0) {
                return 1;
            } else if (element.floatValue() - o.getElement().floatValue() < 0) {
                return -1;
            } else {
                return 0;
            }
        }
    }

    private static int[] getFibonacciArray(int number) {
        int[] array = new int[number];
        for (int i = 0; i < number; i++) {
            array[i] = getFibonacci(i);
        }
        return array;
    }

    private static int getFibonacci(int n) {
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else {
            return getFibonacci(n - 1) + getFibonacci(n - 2);
        }
    }

}
