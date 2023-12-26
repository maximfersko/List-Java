# List-Java

# How to Run Tests:

```shell
    cd List
    mvn test
```
# MyList<T> Interface

Defines methods that should be implemented by classes representing list data structures.

## Методы

- `add(int idx, T elm)`: Inserts an element at the specified index.
- `add(T elm)`: Appends an element to the end of the list.
- `get(int idx)`: Returns the element at the specified index.
- `clean()`:  Removes all elements from the list, making it empty.
- `remove(int idx)`: Removes the element at the specified index.
- `remove(T elm)`: Removes the first occurrence of the specified element from the list.
- `sort(Comparator<T> comparator)`: Sorts the elements of the list using the specified comparator.
- `size()`: Returns the current size of the list.
- `contains(T elm)`:  Checks if the specified element is present in the list.
- `toArray()`:  Returns an array containing all elements of the list.

# Класс MyArrayList<T>

Implements the MyList<T> interface using an array to store elements.

# Класс MyLinkedList<T>

Implements the MyList<T> interface using a doubly-linked list.