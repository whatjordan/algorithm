package com.jordan.datastructure.list;

import com.jordan.datastructure.adt.AbstractLink;
import com.jordan.datastructure.adt.ListADT;

public abstract class AbstractLinkedList<T> extends AbstractLink implements ListADT<T> {

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }


}
