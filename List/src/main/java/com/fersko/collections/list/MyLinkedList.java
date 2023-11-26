package com.fersko.collections.list;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;
import java.util.Optional;

/**
 * Implementation of the MyList interface using a doubly-linked list.
 *
 * @param <T> the type of elements stored in the list.
 */
public class MyLinkedList<T> implements MyList<T> {

    private final static String OUT_OF_RANGE_ERROR = "Index out of range!";

    private Node<T> head;
    private Node<T> tail;
    private int size;

    /**
     * Node class representing elements in the linked list.
     *
     * @param <T> the type of elements stored in the node.
     */
    private static class Node<T> {
        private T data;
        private Node<T> next;
        private Node<T> prev;

        /**
         * Constructs a node with the specified element.
         *
         * @param elm the element to be stored in the node.
         */
        public Node(T elm) {
            data = elm;
            next = null;
            prev = null;
        }
    }

    /**
     * Returns the node at the specified index in the linked list.
     *
     * @param index the index of the node to be retrieved.
     * @return the node at the specified index.
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size).
     */
    private Node<T> getNodeAtIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(OUT_OF_RANGE_ERROR);
        }

        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }

        return current;
    }

    /**
     * Deletes the specified node from the linked list.
     *
     * @param node the node to be deleted.
     */
    private void delete(Node<T> node) {
        if (head == node) {
            head = head.next;
            if (head != null) {
                head.prev = null;
            } else {
                tail = null;
            }
        } else if (tail == node) {
            tail = tail.prev;
            tail.next = null;
        } else {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }
        --size;
    }

    /**
     * Finds the node containing the specified element in the linked list.
     *
     * @param elm the element to search for.
     * @return an Optional containing the node with the specified element, or an empty Optional if not found.
     */
    private Optional<Node<T>> findNode(T elm) {
        for (Node<T> iter = head; iter != null; iter = iter.next) {
            if (iter.data.equals(elm)) {
                return Optional.of(iter);
            }
        }
        return Optional.empty();
    }

    /**
     * Inserts an element at the specified index in the linked list.
     *
     * @param idx the index at which the specified element is to be inserted.
     * @param elm the element to be inserted.
     * @throws IndexOutOfBoundsException if the index is out of range (idx < 0 || idx > size).
     */
    @Override
    public void add(int idx, T elm) {
        if (idx > size || idx < 0) {
            throw new IndexOutOfBoundsException(OUT_OF_RANGE_ERROR);
        }

        if (idx == size) {
            add(elm);
        } else {
            Node<T> newNode = new Node<>(elm);

            if (idx == 0) {
                newNode.next = head;
                if (head != null) {
                    head.prev = newNode;
                }
                head = newNode;
            } else {
                Node<T> prevNode = getNodeAtIndex(idx - 1);

                newNode.next = prevNode.next;
                newNode.prev = prevNode;
                prevNode.next = newNode;

                if (newNode.next != null) {
                    newNode.next.prev = newNode;
                } else {
                    tail = newNode;
                }
            }

            size++;
        }
    }

    /**
     * Checks if this MyLinkedList is equal to another object.
     *
     * @param o the object to compare for equality.
     * @return true if the specified object is equal to this MyLinkedList.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MyLinkedList)) return false;
        MyLinkedList<?> that = (MyLinkedList<?>) o;
        return size == that.size && Objects.equals(head, that.head) && Objects.equals(tail, that.tail);
    }

    /**
     * Returns a string representation of this MyLinkedList.
     *
     * @return a string representation of this MyLinkedList.
     */
    @Override
    public String toString() {
        return Arrays.toString(toArray());
    }

    /**
     * Computes a hash code for this MyLinkedList.
     *
     * @return a hash code value for this MyLinkedList.
     */
    @Override
    public int hashCode() {
        return Objects.hash(head, tail, size);
    }

    /**
     * Appends the specified element to the end of this MyLinkedList.
     *
     * @param elm the element to be appended to this MyLinkedList.
     */
    @Override
    public void add(T elm) {
        Node<T> newNode = new Node<>(elm);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
    }

    /**
     * Returns the element at the specified index in this MyLinkedList.
     *
     * @param idx the index of the element to return.
     * @return the element at the specified index in this MyLinkedList.
     * @throws IndexOutOfBoundsException if the index is out of range (idx >= size).
     */
    @Override
    public T get(int idx) {
        if (idx >= size) {
            throw new IndexOutOfBoundsException(OUT_OF_RANGE_ERROR);
        }
        return getNodeAtIndex(idx).data;
    }

    /**
     * Removes all elements from this MyLinkedList, leaving it empty.
     */
    @Override
    public void clean() {
        if (head != null) {
            while (head.next != null) {
                head.next = null;
            }
            head = null;
        }
    }

    /**
     * Removes the element at the specified index in this MyLinkedList.
     *
     * @param idx the index of the element to be removed.
     * @return the element that was removed from the MyLinkedList.
     * @throws IndexOutOfBoundsException if the index is out of range (idx < 0 || idx >= size).
     */
    @Override
    public T remove(int idx) {
        if (idx < 0 || idx >= size) {
            throw new IndexOutOfBoundsException(OUT_OF_RANGE_ERROR);
        }
        Node<T> current = getNodeAtIndex(idx);
        delete(current);
        return current.data;
    }

    /**
     * Removes the first occurrence of the specified element from this MyLinkedList.
     *
     * @param elm the element to be removed.
     * @return true if the MyLinkedList contained the specified element and it was removed, false otherwise.
     */
    @Override
    public boolean remove(T elm) {
        Optional<Node<T>> node = findNode(elm);
        if (node.isPresent()) {
            delete(node.get());
            return true;
        }
        return false;
    }

    /**
     * Sorts the elements of this MyLinkedList according to the specified comparator.
     *
     * @param comparator the comparator to determine the order of the elements.
     */
    @Override
    public void sort(Comparator<T> comparator) {
        if (size > 1) {
            for (Node<T> i = head; i != null; i = i.next) {
                for (Node<T> j = i.next; j != null; j = j.next) {
                    if (comparator.compare(i.data, j.data) > 0) {
                        T tmp = i.data;
                        i.data = j.data;
                        j.data = tmp;
                    }
                }
            }
        }
    }

    /**
     * Returns the number of elements in this MyLinkedList.
     *
     * @return the number of elements in this MyLinkedList.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Checks if this MyLinkedList contains the specified element.
     *
     * @param elm the element to be checked for presence in this MyLinkedList.
     * @return true if this MyLinkedList contains the specified element, false otherwise.
     */
    @Override
    public boolean contains(T elm) {
        return findNode(elm).isPresent();
    }

    /**
     * Returns an array containing all of the elements in this MyLinkedList in proper sequence.
     *
     * @return an array containing all of the elements in this MyLinkedList.
     */
    @Override
    @SuppressWarnings("unchecked")
    public T[] toArray() {
        T[] array = (T[]) new Object[size];
        int i = 0;
        for (Node<T> elm = head; elm != null; elm = elm.next) {
            array[i++] = elm.data;
        }
        return array;
    }
}
