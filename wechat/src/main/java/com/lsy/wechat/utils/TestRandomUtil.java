package com.lsy.wechat.utils;

import java.util.HashSet;
import java.util.Set;

public class TestRandomUtil {
	public static Set<Integer> get(int cap, int size) {
		HashSet set = new HashSet();
		do {
			set.add((int) (Math.random() * size));
			//如果容量等于100  跳出循环
		} while (set.size() != cap);
		return set;
	}
}
