package com.lsy.wechat.utils;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestRandomUtil {
	public static Set<Integer> get(int cap, int size) {
		HashSet set = new HashSet();
		if (cap > size)
			throw new RuntimeException();
		if (cap == size) {
			set = (HashSet) Stream.iterate(0, item -> item + 1).limit(size).collect(Collectors.toSet());
		}

		do {
			set.add((int) (Math.random() * size));
			//如果容量等于100  跳出循环
		} while (set.size() != cap);
		return set;
	}
}
