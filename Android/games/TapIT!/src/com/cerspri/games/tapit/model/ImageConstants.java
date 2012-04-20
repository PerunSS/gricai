package com.cerspri.games.tapit.model;

import java.util.HashMap;
import java.util.Map;

import com.cerspri.games.tapit.R;

public class ImageConstants {
	public static final int NEGATIVE_10 = -10;
	public static final int NEGATIVE_5 = -5;
	public static final int NEGATIVE_2 = -2;
	public static final int POSITIVE_1 = 1;
	public static final int POSITIVE_2 = 2;
	public static final int POSITIVE_5 = 5;

	private static Map<Integer, Map<Integer, Integer>> iconResources;
	// TODO fill with resources
	static {
		iconResources = new HashMap<Integer, Map<Integer, Integer>>();
		Map<Integer, Integer> lvl1 = new HashMap<Integer, Integer>();
		lvl1.put(NEGATIVE_10, R.drawable.value_n_10);
		lvl1.put(NEGATIVE_5, R.drawable.value_n_5);
		lvl1.put(NEGATIVE_2, R.drawable.value_n_2);
		lvl1.put(POSITIVE_1, R.drawable.value_p_1);
		lvl1.put(POSITIVE_2, R.drawable.value_p_2);
		lvl1.put(POSITIVE_5, R.drawable.value_p_5);
		for (int i = 1; i <= 10; i++)
			iconResources.put(i, lvl1);
	}

	public static int getResource(int level, int value) {
		return iconResources.get(level).get(value);
	}
}
