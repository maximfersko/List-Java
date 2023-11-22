package com.fersko.collections.list;

public interface List<T> {

    void add(int idx, T elm);

    void add(T elm);

    T get(int idx);

    void cleanAll();

    T remove(int idx);

    void sort();

}
