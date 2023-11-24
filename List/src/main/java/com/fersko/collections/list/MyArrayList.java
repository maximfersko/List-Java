package com.fersko.collections.list;

import java.util.*;
import java.util.stream.IntStream;

public class MyArrayList<T> implements MyList<T> {

    private Object[] data;

    private int size;

    private int capacity = 10;

    @SuppressWarnings("unchecked")
    public MyArrayList() {
        data = (T[]) new Object[capacity];
    }

    public MyArrayList(int sizeCapacity) {
        if (sizeCapacity <= 0) {
            throw new IllegalArgumentException("Error size capacity");
        }
        data = new Object[sizeCapacity];
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        capacity *= 2;
        T[] newData = (T[]) new Object[capacity];
        System.arraycopy(data, 0, newData, 0, data.length);
        data = newData;
    }

    @Override
    public void add(int idx, T elm) {
        if (idx < 0 || idx > size) {
            throw new IndexOutOfBoundsException("Index out of range!");
        }
        if (size == data.length) {
            resize();
        }
        for (int i = size; i > idx; i--) {
            data[i] = data[i - 1];
        }
        data[idx] = elm;
        size++;
    }

    @Override
    public void add(T elm) {
        if (data.length == size) {
            resize();
        }
        data[size++] = elm;
    }


    @Override
    @SuppressWarnings("unchecked")
    public T get(int idx) {
        if (idx < 0 || idx > size) {
            throw new IndexOutOfBoundsException("Index out of range");
        }
        return (T) data[idx];
    }

    @Override
    @SuppressWarnings("unchecked")
    public void clean() {
        data = (T[]) new Object[capacity];
        size = 0;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T remove(int idx) {
        if (idx < 0 || idx >= size) {
            throw new IndexOutOfBoundsException("Index out of range!");
        }

        T removedElement = (T) data[idx];
        delElm(idx);
        return removedElement;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MyArrayList)) return false;
        MyArrayList<?> that = (MyArrayList<?>) o;
        return size == that.size && capacity == that.capacity && Arrays.equals(data, that.data);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(size, capacity);
        result = 31 * result + Arrays.hashCode(data);
        return result;
    }

    @Override
    public boolean remove(T elm) {
        OptionalInt idxToRemove = findElm(elm);
        if (idxToRemove.isPresent()) {
           delElm(idxToRemove.getAsInt());
           return true;
        }
        return false;
    }

    private void delElm(int idxToRemove) {
        IntStream.range(idxToRemove, size - 1)
                .forEach(i -> data[i] = data[i + 1]);
        data[size - 1] = null;
        size--;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void sort(Comparator<T> comparator) {
        if (size > 1) {
            for (int i = 0; i < size - 1; i++) {
                for (int j = 0; j < size - 1; j++) {
                    if (comparator.compare((T) data[j], (T) data[j + 1]) > 0) {
                        T tmp = (T) data[j];
                        data[j] = data[j + 1];
                        data[j + 1] = tmp;
                    }
                }
            }
        }
    }

    @Override
    public int size() {
        return size;
    }

    private OptionalInt findElm(T elm) {
        return IntStream.range(0, size)
                .filter(i -> elm.equals(data[i]))
                .findFirst();
    }

    @Override
    public boolean contains(T elm) {
        return findElm(elm).isPresent();
    }

    @Override
    @SuppressWarnings("unchecked")
    public T[] toArray() {
        return (T[]) data;
    }

    @Override
    public String toString() {
        return Arrays.toString(data);
    }

}
