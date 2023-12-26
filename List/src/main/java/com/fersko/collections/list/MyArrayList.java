package com.fersko.collections.list;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.OptionalInt;
import java.util.stream.IntStream;

/**
 * Implementation of the MyList interface using an array-based data structure.
 * The array is dynamically resized when needed to accommodate elements.
 *
 * @param <T> the type of elements stored in the list.
 */
public class MyArrayList<T> implements MyList<T> {

    private final static int DEFAULT_CAPACITY = 10;

    private final static String OUT_OF_RANGE_ERROR = "Index out of range!";

    private Object[] data;

    private int size;

    private int capacity = DEFAULT_CAPACITY;


    /**
     * Searches for the first occurrence of the specified element in the list.
     *
     * @param elm the element to search for.
     * @return an OptionalInt containing the index of the first occurrence of the specified element,
     * or an empty OptionalInt if the element is not found.
     */
    private OptionalInt findElm(T elm) {
        return IntStream.range(0, size)
                .filter(i -> elm.equals(data[i]))
                .findFirst();
    }


    /**
     * Constructs an empty MyArrayList with an initial capacity of 10.
     */
    public MyArrayList() {
        data = new Object[capacity];
    }

    /**
     * Constructs an empty MyArrayList with the specified initial capacity.
     *
     * @param sizeCapacity the initial capacity of the list.
     * @throws IllegalArgumentException if the specified initial capacity is non-positive.
     */
    public MyArrayList(int sizeCapacity) {
        if (sizeCapacity <= 0) {
            throw new IllegalArgumentException("Error size capacity");
        }
        data = new Object[sizeCapacity];
    }

    /**
     * Compares this MyArrayList with the specified object for equality.
     *
     * @param o the object to be compared for equality with this MyArrayList.
     * @return true if the specified object is equal to this MyArrayList.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!( o instanceof MyArrayList ))
            return false;
        MyArrayList<?> that = (MyArrayList<?>) o;
        return size == that.size && capacity == that.capacity && Arrays.equals(data, that.data);
    }

    /**
     * Resizes the internal array to twice its current size.
     */
    @SuppressWarnings("unchecked")
    private void resize() {
        T[] newData = (T[]) new Object[capacity *= 2];
        System.arraycopy(data, 0, newData, 0, data.length);
        data = newData;
    }

    /**
     * Removes an element at the specified index and shifts the remaining elements accordingly.
     *
     * @param idxToRemove the index of the element to be removed.
     */
    private void delElm(int idxToRemove) {
        IntStream.range(idxToRemove, size - 1)
                .forEach(i -> data[i] = data[i + 1]);
        data[size - 1] = null;
        size--;
    }

    /**
     * Inserts an element at the specified index in the list.
     *
     * @param idx the index at which the specified element is to be inserted.
     * @param elm the element to be inserted.
     * @throws IndexOutOfBoundsException if the index is out of range (idx < 0 || idx > size).
     */
    @Override
    public void add(int idx, T elm) {
        if (idx < 0 || idx > size) {
            throw new IndexOutOfBoundsException(OUT_OF_RANGE_ERROR);
        }
        if (size == data.length) {
            resize();
        }
        if (size - idx >= 0) {
            System.arraycopy(data, idx, data, idx + 1, size - idx);
        }
        data[idx] = elm;
        size++;
    }

    /**
     * Appends the specified element to the end of the list.
     *
     * @param elm the element to be appended to the list.
     */
    @Override
    public void add(T elm) {
        if (size == data.length) {
            resize();
        }
        data[size++] = elm;
    }

    /**
     * Returns the element at the specified index in the list.
     *
     * @param idx the index of the element to return.
     * @return the element at the specified index.
     * @throws IndexOutOfBoundsException if the index is out of range (idx < 0 || idx >= size).
     */
    @Override
    @SuppressWarnings("unchecked")
    public T get(int idx) {
        if (idx < 0 || idx >= size) {
            throw new IndexOutOfBoundsException(OUT_OF_RANGE_ERROR);
        }
        return (T) data[idx];
    }

    /**
     * Removes all elements from the list, leaving it empty.
     */
    @Override
    public void clean() {
        data = new Object[capacity];
        size = 0;
    }

    /**
     * Removes the element at the specified index in the list.
     *
     * @param idx the index of the element to be removed.
     * @return the element that was removed from the list.
     * @throws IndexOutOfBoundsException if the index is out of range (idx < 0 || idx >= size).
     */
    @Override
    @SuppressWarnings("unchecked")
    public T remove(int idx) {
        if (idx < 0 || idx >= size) {
            throw new IndexOutOfBoundsException(OUT_OF_RANGE_ERROR);
        }

        T removedElement = (T) data[idx];
        delElm(idx);
        return removedElement;
    }

    /**
     * Returns a string representation of the MyArrayList.
     *
     * @return a string representation of the MyArrayList.
     */
    @Override
    public String toString() {
        return Arrays.toString(data);
    }

    /**
     * Returns a hash code value for this MyArrayList.
     *
     * @return a hash code value for this MyArrayList.
     */
    @Override
    public int hashCode() {
        int result = Objects.hash(size, capacity);
        result = 31 * result + Arrays.hashCode(data);
        return result;
    }

    /**
     * Removes the first occurrence of the specified element from the list, if it is present.
     *
     * @param elm the element to be removed from the list, if present.
     * @return true if the list contained the specified element.
     */
    @Override
    public boolean remove(T elm) {
        OptionalInt idxToRemove = findElm(elm);
        if (idxToRemove.isPresent()) {
            delElm(idxToRemove.getAsInt());
            return true;
        }
        return false;
    }

    /**
     * Sorts the elements of the list according to the specified comparator.
     *
     * @param comparator the comparator to determine the order of the list.
     */
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

    /**
     * Returns the number of elements in the list.
     *
     * @return the number of elements in the list.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Checks if the list contains the specified element.
     *
     * @param elm the element to be checked for presence in the list.
     * @return true if the list contains the specified element.
     */
    @Override
    public boolean contains(T elm) {
        return findElm(elm).isPresent();
    }

    /**
     * Returns an array containing all of the elements in the list in proper sequence.
     *
     * @return an array containing all of the elements in the list.
     */
    @Override
    @SuppressWarnings("unchecked")
    public T[] toArray() {
        return (T[]) data;
    }

    /**
     * @return an iterator over the elements in this MyArrayList in proper sequence.
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int currentIdx;  // Index of the current element in the iteration.

            /**
             * Returns true if there is a next element in the iteration.
             *
             * @return true if there is a next element, false otherwise.
             */
            @Override
            public boolean hasNext() {
                return currentIdx < size;
            }

            /**
             * Returns the next element in the iteration and advances the iterator.
             *
             * @return the next element in the iteration.
             * @throws NoSuchElementException if there is no next element.
             */
            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("No more elements in the iteration.");
                }
                return get(currentIdx++);
            }
        };
    }

}

