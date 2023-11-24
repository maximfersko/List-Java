package com.fersko.collections.list;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;
import java.util.Optional;

public class MyLinkedList<T> implements MyList<T>{


    private Node<T> head;
    private Node<T> tail;
    private int size;

    private static class Node<T> {
        private T data;

        private Node<T> next;
        private Node<T> prev;

        public Node(T elm) {
            data = elm;
            next = null;
            prev = null;
        }
    }

    @Override
    public void add(int idx, T elm) {
        if (idx >= size || idx < 0) {
            throw new IndexOutOfBoundsException("Index out of range!");
        }

        Node<T> node = new Node<>(elm);

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MyLinkedList)) return false;
        MyLinkedList<?> that = (MyLinkedList<?>) o;
        return size == that.size && Objects.equals(head, that.head) && Objects.equals(tail, that.tail);
    }

    @Override
    public String toString() {
        return Arrays.toString(toArray());
    }

    @Override
    public int hashCode() {
        return Objects.hash(head, tail, size);
    }

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


    @Override
    public T get(int idx) {
        if (idx >= size) {
            throw new IndexOutOfBoundsException("Index out of range");
        }
        return getNodeAtIndex(idx).data;
    }

    @Override
    public void clean() {
        if (head != null) {
            while (head.next != null) {
                head.next = null;
            }
            head = null;
        }
    }

    private Node<T> getNodeAtIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of range!");
        }

        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }

        return current;
    }

    @Override
    public T remove(int idx) {
        if (idx < 0 || idx >= size) {
            throw new IndexOutOfBoundsException("Index out of range!");
        }
        Node<T> current = getNodeAtIndex(idx);
        delete(current);
        return current.data;
    }

    @Override
    public boolean remove(T elm) {
        Optional<Node<T>> node = findNode(elm);
        if (node.isPresent()) {
            delete(node.get());
            return true;
        }
        return false;
    }

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

    private Optional<Node<T>> findNode(T elm) {
        Node<T> iter = head;
        for (; iter != null; iter = iter.next) {
            if (iter.data.equals(elm)) {
                return Optional.of(iter);
            }
        }
        return Optional.empty();
    }


    @Override
    public void sort(Comparator<T> comparator) {
        if (size > 1) {
            for (Node<T> i = head; i != null; i = i.next) {
                for (Node<T> j = i.next; j != null; j = j.next) {
                    if (comparator.compare(i.data,j.data) > 0) {
                        T tmp = i.data;
                        i.data = j.data;
                        j.data = tmp;
                    }
                }
            }
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean contains(T elm) {
        return findNode(elm).isPresent();
    }

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
