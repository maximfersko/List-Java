package com.fersko.collections.list;

import java.util.Comparator;
import java.util.Iterator;

/**
 * Interface representing a generic list of elements.
 *
 * @param <T> the type of elements stored in the list.
 */
public interface MyList<T> extends Iterable<T> {

	/**
	 * Inserts the specified element at the specified position in the list.
	 *
	 * @param idx the index at which the specified element is to be inserted.
	 * @param elm the element to be inserted.
	 * @throws IndexOutOfBoundsException if the index is out of range (idx < 0 || idx > size).
	 */
	void add(int idx, T elm);

	/**
	 * Appends the specified element to the end of the list.
	 *
	 * @param elm the element to be appended to the list.
	 */
	void add(T elm);

	/**
	 * Returns the element at the specified index in the list.
	 *
	 * @param idx the index of the element to return.
	 * @return the element at the specified index in the list.
	 * @throws IndexOutOfBoundsException if the index is out of range (idx >= size).
	 */
	T get(int idx);

	/**
	 * Removes all elements from the list, leaving it empty.
	 */
	void clean();

	/**
	 * Removes the element at the specified index in the list.
	 *
	 * @param idx the index of the element to be removed.
	 * @return the element that was removed from the list.
	 * @throws IndexOutOfBoundsException if the index is out of range (idx < 0 || idx >= size).
	 */
	T remove(int idx);

	/**
	 * Removes the first occurrence of the specified element from the list.
	 *
	 * @param elm the element to be removed.
	 * @return true if the list contained the specified element and it was removed, false otherwise.
	 */
	boolean remove(T elm);

	/**
	 * Sorts the elements of the list according to the specified comparator.
	 *
	 * @param comparator the comparator to determine the order of the elements.
	 */
	void sort(Comparator<T> comparator);

	/**
	 * Returns the number of elements in the list.
	 *
	 * @return the number of elements in the list.
	 */
	int size();

	/**
	 * Checks if the list contains the specified element.
	 *
	 * @param elm the element to be checked for presence in the list.
	 * @return true if the list contains the specified element, false otherwise.
	 */
	boolean contains(T elm);

	/**
	 * Returns an array containing all of the elements in the list in proper sequence.
	 *
	 * @return an array containing all of the elements in the list.
	 */
	T[] toArray();

	/**
	 * Returns an iterator over the elements in this list in proper sequence.
	 * The elements are returned in the order they are stored in the list.
	 *
	 * @return an iterator over the elements in this list in proper sequence.
	 */
	Iterator<T> iterator();

}
