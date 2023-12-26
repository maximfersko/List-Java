package com.fersko.collections.list;

import java.util.List;

public class UtilsListForTest<T> {
	public static <T> boolean isEqualsList(List<T> list, MyList<T> myList) {
		if (list.size() != myList.size()) {
			return false;
		}

		for (int i = 0; i < list.size(); i++) {
			if (!list.get(i).equals(myList.get(i))) {
				return false;
			}
		}

		return true;
	}

	public static void fillList(List<Integer> list, MyList<Integer> myList) {
		for (int i = 0; i < 10; i++) {
			list.add(i);
			myList.add(i);
		}
	}

}
