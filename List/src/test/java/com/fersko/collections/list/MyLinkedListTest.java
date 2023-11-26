package com.fersko.collections.list;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import static com.fersko.collections.list.UtilsListForTest.fillList;
import  static  com.fersko.collections.list.UtilsListForTest.isEqualsList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MyLinkedListTest {

    @Test
    void addLinkedList() {
        List<Integer> list = new LinkedList<>();
        MyList<Integer> myList = new MyLinkedList<>();
        list.add(99);
        myList.add(99);
        assertTrue(isEqualsList(list, myList));
    }

    @Test
     void getLinkedList() {
        List<Integer> list = new LinkedList<>();
        MyList<Integer> myList = new MyLinkedList<>();
        fillList(list, myList);
        assertTrue(isEqualsList(list, myList));
    }

    @Test
    void  removeElmAtIdxLinkedList() {
        List<Integer> list = new LinkedList<>();
        MyList<Integer> myList = new MyLinkedList<>();
        fillList(list, myList);
        list.remove(8);
        myList.remove(8);
        assertTrue(isEqualsList(list, myList));
        list.remove(3);
        myList.remove(3);
        assertTrue(isEqualsList(list, myList));
    }

    @Test
    void removeElmElmLinkedList() {
        List<String> list = new LinkedList<>();
        MyList<String> myList = new MyLinkedList<>();
        for (int i = 0; i < 10; i++) {
            StringBuilder stringBuilder = new StringBuilder("string ");
            stringBuilder.append(i);
            list.add(stringBuilder.toString());
            myList.add(stringBuilder.toString());
        }
        list.remove("string 8");
        myList.remove("string 8");
        assertTrue(isEqualsList(list, myList));
    }

    @Test
    void containsElmLinkedList() {
        List<Integer> list = new ArrayList<>();
        MyList<Integer> myList = new MyLinkedList<>();
        fillList(list, myList);
        assertEquals(list.contains(666), myList.contains(666));
        assertEquals(list.contains(9), myList.contains(9));
    }

    @Test
     void sortLinkedList() {
        List<Integer> list = new ArrayList<>();
        MyList<Integer> myList = new MyLinkedList<>();
        for (int i = 12; i >= 0; --i) {
            list.add(i + 2);
            myList.add(i + 2);
        }
        list.sort(Comparator.naturalOrder());
        myList.sort(Comparator.naturalOrder());
        assertTrue(isEqualsList(list, myList));
    }

    @Test
     void addElmAtIndex() {
        List<Integer> list = new ArrayList<>();
        MyList<Integer> myList = new MyLinkedList<>();
        fillList(list, myList);
        list.add(0, 1000);
        myList.add(0, 1000);
        assertTrue(isEqualsList(list, myList));
        list.add(5, 77);
        myList.add(5, 77);
        assertTrue(isEqualsList(list, myList));
        list.add(9,655);
        myList.add(9, 655);
        assertTrue(isEqualsList(list, myList));
    }

}
