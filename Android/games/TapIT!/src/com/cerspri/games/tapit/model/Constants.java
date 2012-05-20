package com.cerspri.games.tapit.model;

import java.util.HashMap;
import java.util.Map;

import com.cerspri.games.tapit.R;

public class Constants {
	public static final int NEGATIVE_10 = -10;
	public static final int NEGATIVE_5 = -5;
	public static final int NEGATIVE_2 = -2;
	public static final int POSITIVE_1 = 1;
	public static final int POSITIVE_2 = 2;
	public static final int POSITIVE_5 = 5;

	public static final String NEGATIVE_ODD = "negativeOdd";
	public static final String NEGATIVE_MEDIUM = "negativeMedium";
	public static final String NEGATIVE_HIGH = "negativeHigh";
	public static final String POSITIVE_MEDIUM = "positiveMedium";
	public static final String POSITIVE_HIGH = "positiveHigh";
	public static final String LIFE_TIME = "lifeTime";
	public static final String SPAWN_TIME = "spawnTime";
	public static final String NEXT_LVL = "nextLvl";

	private static Map<Integer, Map<String, Double>> levelParameters;
	private static Map<Integer, Map<Integer, Integer>> iconResources;

	/* filling level data */
	static {
		levelParameters = new HashMap<Integer, Map<String, Double>>();
		// level: 01, neg = 0.30, neg(m,h) = (0.20,0.000),
		// pos(m,h) = (0.600,0.21), lt = 1.00, spawn = 0.500
		Map<String, Double> lvl = new HashMap<String, Double>();
		lvl.put(NEGATIVE_ODD, 0.30);
		lvl.put(NEGATIVE_MEDIUM, 0.20);
		lvl.put(NEGATIVE_HIGH, 0.000);
		lvl.put(POSITIVE_MEDIUM, 0.600);
		lvl.put(POSITIVE_HIGH, 0.21);
		lvl.put(LIFE_TIME, 1.00);
		lvl.put(SPAWN_TIME, 0.500);
		lvl.put(NEXT_LVL, 30.0);
		levelParameters.put(1, lvl);
		// level: 02, neg = 0.39, neg(m,h) = (0.26,0.000),
		// pos(m,h) = (0.556,0.27), lt = 0.92, spawn = 0.465
		lvl = new HashMap<String, Double>();
		lvl.put(NEGATIVE_ODD, 0.39);
		lvl.put(NEGATIVE_MEDIUM, 0.26);
		lvl.put(NEGATIVE_HIGH, 0.000);
		lvl.put(POSITIVE_MEDIUM, 0.556);
		lvl.put(POSITIVE_HIGH, 0.27);
		lvl.put(LIFE_TIME, 0.92);
		lvl.put(SPAWN_TIME, 0.465);
		lvl.put(NEXT_LVL, 60.0);
		levelParameters.put(2, lvl);
		// level: 03, neg: 0.47, neg(m,h) = (0.32,0.000),
		// pos(m,h) = (0.512,0.30), lt = 0.86, spawn = 0.430
		lvl = new HashMap<String, Double>();
		lvl.put(NEGATIVE_ODD, 0.47);
		lvl.put(NEGATIVE_MEDIUM, 0.32);
		lvl.put(NEGATIVE_HIGH, 0.000);
		lvl.put(POSITIVE_MEDIUM, 0.512);
		lvl.put(POSITIVE_HIGH, 0.30);
		lvl.put(LIFE_TIME, 0.86);
		lvl.put(SPAWN_TIME, 0.430);
		lvl.put(NEXT_LVL, 85.0);
		levelParameters.put(3, lvl);
		// level: 04, neg: 0.54, neg(m,h) = (0.38,0.085),
		// pos(m,h) = (0.468,0.30), lt = 0.82, spawn = 0.395
		lvl = new HashMap<String, Double>();
		lvl.put(NEGATIVE_ODD, 0.54);
		lvl.put(NEGATIVE_MEDIUM, 0.38);
		lvl.put(NEGATIVE_HIGH, 0.085);
		lvl.put(POSITIVE_MEDIUM, 0.468);
		lvl.put(POSITIVE_HIGH, 0.30);
		lvl.put(LIFE_TIME, 0.82);
		lvl.put(SPAWN_TIME, 0.395);
		levelParameters.put(4, lvl);
		lvl.put(NEXT_LVL, 110.0);
		// level: 05, neg: 0.60, neg(m,h) = (0.44,0.170),
		// pos(m,h) = (0.424,0.27), lt = 0.80, spawn = 0.360
		lvl = new HashMap<String, Double>();
		lvl.put(NEGATIVE_ODD, 0.60);
		lvl.put(NEGATIVE_MEDIUM, 0.44);
		lvl.put(NEGATIVE_HIGH, 0.170);
		lvl.put(POSITIVE_MEDIUM, 0.424);
		lvl.put(POSITIVE_HIGH, 0.27);
		lvl.put(LIFE_TIME, 0.80);
		lvl.put(SPAWN_TIME, 0.360);
		levelParameters.put(5, lvl);
		lvl.put(NEXT_LVL, 130.0);
		// level: 06, neg: 0.65, neg(m,h) = (0.50,0.255),
		// pos(m,h) = (0.380,0.21), lt = 0.80, spawn = 0.325
		lvl = new HashMap<String, Double>();
		lvl.put(NEGATIVE_ODD, 0.65);
		lvl.put(NEGATIVE_MEDIUM, 0.50);
		lvl.put(NEGATIVE_HIGH, 0.255);
		lvl.put(POSITIVE_MEDIUM, 0.380);
		lvl.put(POSITIVE_HIGH, 0.21);
		lvl.put(LIFE_TIME, 0.80);
		lvl.put(SPAWN_TIME, 0.325);
		levelParameters.put(6, lvl);
		lvl.put(NEXT_LVL, 150.0);
		// level: 07, neg: 0.69, neg(m,h) = (0.56,0.340),
		// pos(m,h) = (0.336,0.12), lt = 0.82, spawn = 0.290
		lvl = new HashMap<String, Double>();
		lvl.put(NEGATIVE_ODD, 0.69);
		lvl.put(NEGATIVE_MEDIUM, 0.56);
		lvl.put(NEGATIVE_HIGH, 0.340);
		lvl.put(POSITIVE_MEDIUM, 0.336);
		lvl.put(POSITIVE_HIGH, 0.12);
		lvl.put(LIFE_TIME, 0.82);
		lvl.put(SPAWN_TIME, 0.290);
		lvl.put(NEXT_LVL, 165.0);
		levelParameters.put(7, lvl);
		// level: 08, neg: 0.72, neg(m,h) = (0.62,0.425),
		// pos(m,h) = (0.292,0.03), lt = 0.86, spawn = 0.255
		lvl = new HashMap<String, Double>();
		lvl.put(NEGATIVE_ODD, 0.72);
		lvl.put(NEGATIVE_MEDIUM, 0.62);
		lvl.put(NEGATIVE_HIGH, 0.425);
		lvl.put(POSITIVE_MEDIUM, 0.292);
		lvl.put(POSITIVE_HIGH, 0.03);
		lvl.put(LIFE_TIME, 0.86);
		lvl.put(SPAWN_TIME, 0.255);
		lvl.put(NEXT_LVL, 180.0);
		levelParameters.put(8, lvl);
		// level: 09, neg: 0.74, neg(m,h) = (0.68,0.510),
		// pos(m,h) = (0.248,0.02), lt = 0.92, spawn = 0.220
		lvl = new HashMap<String, Double>();
		lvl.put(NEGATIVE_ODD, 0.74);
		lvl.put(NEGATIVE_MEDIUM, 0.68);
		lvl.put(NEGATIVE_HIGH, 0.510);
		lvl.put(POSITIVE_MEDIUM, 0.248);
		lvl.put(POSITIVE_HIGH, 0.02);
		lvl.put(LIFE_TIME, 0.92);
		lvl.put(SPAWN_TIME, 0.220);
		lvl.put(NEXT_LVL, 190.0);
		levelParameters.put(9, lvl);
		// level: 10, neg: 0.75, neg(m,h) = (0.74,0.595),
		// pos(m,h) = (0.204,0.01), lt = 1.00, spawn = 0.150
		lvl = new HashMap<String, Double>();
		lvl.put(NEGATIVE_ODD, 0.75);
		lvl.put(NEGATIVE_MEDIUM, 0.74);
		lvl.put(NEGATIVE_HIGH, 0.595);
		lvl.put(POSITIVE_MEDIUM, 0.204);
		lvl.put(POSITIVE_HIGH, 0.01);
		lvl.put(LIFE_TIME, 1.00);
		lvl.put(SPAWN_TIME, 0.150);
		lvl.put(NEXT_LVL, 100000.0);
		levelParameters.put(10, lvl);

	}
	
	static {
		iconResources = new HashMap<Integer, Map<Integer, Integer>>();
		Map<Integer, Integer> lvl1 = new HashMap<Integer, Integer>();
		lvl1.put(NEGATIVE_10, R.drawable.value01_n_10);
		lvl1.put(NEGATIVE_5, R.drawable.value01_n_05);
		lvl1.put(NEGATIVE_2, R.drawable.value01_n_02);
		lvl1.put(POSITIVE_1, R.drawable.value01_p_01);
		lvl1.put(POSITIVE_2, R.drawable.value01_p_02);
		lvl1.put(POSITIVE_5, R.drawable.value01_p_05);
		
		Map<Integer, Integer> lvl2 = new HashMap<Integer, Integer>();
		lvl2.put(NEGATIVE_10, R.drawable.value02_n_10);
		lvl2.put(NEGATIVE_5, R.drawable.value02_n_05);
		lvl2.put(NEGATIVE_2, R.drawable.value02_n_02);
		lvl2.put(POSITIVE_1, R.drawable.value02_p_01);
		lvl2.put(POSITIVE_2, R.drawable.value02_p_02);
		lvl2.put(POSITIVE_5, R.drawable.value02_p_05);
		
		Map<Integer, Integer> lvl3 = new HashMap<Integer, Integer>();
		lvl3.put(NEGATIVE_10, R.drawable.value03_n_10);
		lvl3.put(NEGATIVE_5, R.drawable.value03_n_05);
		lvl3.put(NEGATIVE_2, R.drawable.value03_n_02);
		lvl3.put(POSITIVE_1, R.drawable.value03_p_01);
		lvl3.put(POSITIVE_2, R.drawable.value03_p_02);
		lvl3.put(POSITIVE_5, R.drawable.value03_p_05);
		
		Map<Integer, Integer> lvl4 = new HashMap<Integer, Integer>();
		lvl4.put(NEGATIVE_10, R.drawable.value04_n_10);
		lvl4.put(NEGATIVE_5, R.drawable.value04_n_05);
		lvl4.put(NEGATIVE_2, R.drawable.value04_n_02);
		lvl4.put(POSITIVE_1, R.drawable.value04_p_01);
		lvl4.put(POSITIVE_2, R.drawable.value04_p_02);
		lvl4.put(POSITIVE_5, R.drawable.value04_p_05);
		
		Map<Integer, Integer> lvl5 = new HashMap<Integer, Integer>();
		lvl5.put(NEGATIVE_10, R.drawable.value05_n_10);
		lvl5.put(NEGATIVE_5, R.drawable.value05_n_05);
		lvl5.put(NEGATIVE_2, R.drawable.value05_n_02);
		lvl5.put(POSITIVE_1, R.drawable.value05_p_01);
		lvl5.put(POSITIVE_2, R.drawable.value05_p_02);
		lvl5.put(POSITIVE_5, R.drawable.value05_p_05);
		
		Map<Integer, Integer> lvl6 = new HashMap<Integer, Integer>();
		lvl6.put(NEGATIVE_10, R.drawable.value06_n_10);
		lvl6.put(NEGATIVE_5, R.drawable.value06_n_05);
		lvl6.put(NEGATIVE_2, R.drawable.value06_n_02);
		lvl6.put(POSITIVE_1, R.drawable.value06_p_01);
		lvl6.put(POSITIVE_2, R.drawable.value06_p_02);
		lvl6.put(POSITIVE_5, R.drawable.value06_p_05);
		
		iconResources.put(1, lvl1);
		iconResources.put(2, lvl1);
		iconResources.put(3, lvl2);
		iconResources.put(4, lvl2);
		iconResources.put(5, lvl3);
		iconResources.put(6, lvl3);
		iconResources.put(7, lvl4);
		iconResources.put(8, lvl4);
		iconResources.put(9, lvl5);
		iconResources.put(10, lvl6);
		
	}

	public static Level getLevel(int level) {
		Map<String, Double> lvl = levelParameters.get(level);
		return new Level(lvl.get(NEGATIVE_ODD), level,
				lvl.get(NEGATIVE_MEDIUM), lvl.get(NEGATIVE_HIGH),
				lvl.get(POSITIVE_MEDIUM), lvl.get(POSITIVE_HIGH),
				lvl.get(LIFE_TIME), lvl.get(SPAWN_TIME), lvl.get(NEXT_LVL));
	}

	public static int getResource(int level, int value) {
		return iconResources.get(level).get(value);
	}
	
	public static int getEffectResource(Class<?> effect){
		return 0;
	}
}
