package com.jordan.datastructure.sort;

public class SortFactory {
    public static <T> Sort<T> getSort(SortType sortType) {
        switch (sortType) {
            case BUBBLE:
                return new BubbleSort<>();
            case SIMPLE_SELECT:
                return new SimpleSelectSort<>();
            case STRAIGHT_INSERTION:
                return new StraightInsertionSort<>();
            case SHELL:
                return new ShellSort<>();
            case HEAP:
                return new HeapSort<>();
            case RECURSIVE_MERGING:
                return new RecursiveMergingSort<>();
            case LOOP_MERGING:
                return new LoopMergingSort<>();
            case QUICK:
                return new QuickSort<>();
        }
        throw new IllegalArgumentException("Can't found " + sortType);
    }

    public enum SortType {
        BUBBLE, SIMPLE_SELECT, STRAIGHT_INSERTION, SHELL, HEAP, RECURSIVE_MERGING, LOOP_MERGING, QUICK
    }

}
