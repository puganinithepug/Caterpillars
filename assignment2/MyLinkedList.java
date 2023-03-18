package assignment2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public abstract class MyLinkedList<E> implements MyList<E> {
    protected int size;

    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {return size==0;}


}
