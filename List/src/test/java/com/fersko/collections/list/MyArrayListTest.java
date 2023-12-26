package com.fersko.collections.list;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static com.fersko.collections.list.UtilsListForTest.fillList;
import static com.fersko.collections.list.UtilsListForTest.isEqualsList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MyArrayListTest {

	@Test
	void defConstructorTest() {
		List<Byte> byteList = new ArrayList<>();
		MyList<Byte> byteMyList = new MyArrayList<>();
		assertEquals(byteList.size(), byteMyList.size());
	}

	@Test
	void resizeArrayListTest() {
		List<Integer> list = new ArrayList<>(5);
		MyList<Integer> myList = new MyArrayList<>(5);
		fillList(list, myList);
		assertTrue(isEqualsList(list, myList));
	}

	@Test
	void addArrayList() {
		List<String> names = new ArrayList<>(15);
		MyList<String> namesMyList = new MyArrayList<>(15);
		names.add("Max");
		namesMyList.add("Max");
		names.add(1, "Bob");
		namesMyList.add(1, "Bob");
	}

	@Test
	void cleanArrayTest() {
		List<Integer> list = new ArrayList<>();
		MyList<Integer> myList = new MyArrayList<>();
		fillList(list, myList);
		list.clear();
		myList.clean();
		assertEquals(list.size(), myList.size());
	}

	@Test
	void removeElmByIdxArrayList() {
		List<Integer> list = new ArrayList<>();
		MyList<Integer> myList = new MyArrayList<>();
		fillList(list, myList);
		list.remove(7);
		myList.remove(7);
		assertTrue(isEqualsList(list, myList));
	}

	@Test
	void removeElmArrayList() {
		List<String> list = new ArrayList<>();
		MyList<String> myList = new MyArrayList<>();
		for (int i = 0; i < 10; i++) {
			StringBuilder stringBuilder = new StringBuilder("str ");
			stringBuilder.append(i);
			list.add(stringBuilder.toString());
			myList.add(stringBuilder.toString());
		}
		list.remove("str 6");
		myList.remove("str 6");
		assertTrue(isEqualsList(list, myList));
	}

	@Test
	void containsArrayList() {
		List<Integer> list = new ArrayList<>();
		MyList<Integer> myList = new MyArrayList<>();
		fillList(list, myList);
		assertEquals(list.contains(5), myList.contains(5));
		assertEquals(list.contains(227), myList.contains(788));
	}

	@Test
	void sortArrayList() {
		List<Integer> list = new ArrayList<>();
		MyList<Integer> myList = new MyArrayList<>();
		for (int i = 12; i >= 0; --i) {
			list.add(i + 3);
			myList.add(i + 3);
		}
		list.sort(Comparator.naturalOrder());
		myList.sort(Comparator.naturalOrder());
	}

}
