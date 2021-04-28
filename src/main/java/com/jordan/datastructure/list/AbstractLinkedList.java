package com.jordan.datastructure.list;

import com.jordan.datastructure.adt.AbstractLink;
import com.jordan.datastructure.adt.ListADT;

public abstract class AbstractLinkedList extends AbstractLink implements ListADT {

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }


}
