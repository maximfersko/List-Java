package com.fersko.collections.list;

import java.util.Comparator;

public interface MyList<T> {

    void add(int idx, T elm);

    void add(T elm);

    T get(int idx);

    void clean();

    T remove(int idx);

    boolean remove(T elm);

    void sort(Comparator<T> comparator);

    int size();

    boolean contains(T elm);

    T[] toArray();

}
