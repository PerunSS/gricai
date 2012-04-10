package com.cerspri.games.tapit.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cerspri.games.tapit.R;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class TapITGame {
	
	private Level levle;

	private static final int POSITIVE_1 = 1, POSITIVE_2 = 2, POSITIVE_3 = 5;
	private static final int NEGATIVE_1 = -2, NEGATIVE_2 = -5,
			NEGATIVE_3 = -10;

	private static final double NEGATIVE_3_WEIGHTS[] = { 0.035, 0.037, 0.04,
			0.043, 0.046, 0.05, 0.053, 0.057, 0.061, 0.065, 0.075, 0.085 };
	private static final double NEGATIVE_2_WEIGHTS[] = { 0.16, 0.165, 0.17,
			0.175, 0.18, 0.185, 0.192, 0.20, 0.21, 0.22, 0.245, 0.27 };
	private static final double NEGATIVE_1_WEIGHTS[] = { 0.5, 0.515, 0.53,
			0.55, 0.57, 0.6, 0.615, 0.63, 0.66, 0.68, 0.73, 0.8 };
	private static final long PLT_VALUES[] = { 500, 485, 470, 450, 425, 400,
			375, 350, 325, 300, 250, 200 };
	private static final long PLTM_VALUES[] = { 1500, 1460, 1420, 1380, 1340,
			1300, 1250, 1200, 1150, 1100, 950, 800 };
	private static final long NLT_VALUES[] = { 400, 415, 430, 450, 475, 500,
			550, 600, 650, 700, 850, 1000 };
	private static final long NLTM_VALUES[] = { 1300, 1340, 1380, 1420, 1460,
			1500, 1575, 1650, 1725, 1800, 1900, 2000 };
	private static final long SPT_VALUES[] = { 300, 315, 330, 350, 375, 400,
			425, 450, 475, 500, 600, 700 };
	private static final long SPTM_VALUES[] = { 200, 185, 170, 150, 125, 100,
			75, 50, 75, 100, 150, 200 };

	private List<TapITObject> graphics = new ArrayList<TapITObject>();
	private static TapITGame instance = new TapITGame();
	private Map<Integer, Double> weights;
	private long positiveLifetime, positiveLifetimeModifikator,
			negativeLifetime, negativeLifetimeModifikator;
	
	private int lvl = 0;
	private int negativeCount = 0;

	private TapITGame() {
		weights = new HashMap<Integer, Double>();
		init();
	}

	public void generateRandomObject(int width, int height, Resources res) {
		double random = Math.random();
		int value = POSITIVE_1;
		if (random < weights.get(NEGATIVE_3)) {
			value = NEGATIVE_3;
		} else if (random < weights.get(POSITIVE_3)) {
			value = POSITIVE_3;
		} else if (random < weights.get(NEGATIVE_2)) {
			value = NEGATIVE_2;
		} else if (random < weights.get(POSITIVE_2)) {
			value = POSITIVE_2;
		} else if (random < weights.get(NEGATIVE_1)) {
			value = NEGATIVE_1;
		}
		long lifeTime = 0;
		if (value < 0) {
			negativeCount++;
			lifeTime = negativeLifetime
					+ (long) (Math.random() * negativeLifetimeModifikator);
		} else {
			lifeTime = positiveLifetime
					+ (long) (Math.random() * positiveLifetimeModifikator);
			negativeCount--;
		}
		if(negativeCount > lvl * 2){
			value = POSITIVE_1;
			negativeCount -= lvl/2;
		}

		Bitmap icon = null;
		switch (value) {
		case NEGATIVE_3:
			icon = BitmapFactory.decodeResource(res, R.drawable.value_n_10);
			break;
		case NEGATIVE_2:
			icon = BitmapFactory.decodeResource(res, R.drawable.value_n_5);
			break;
		case NEGATIVE_1:
			icon = BitmapFactory.decodeResource(res, R.drawable.value_n_2);
			break;
		case POSITIVE_2:
			icon = BitmapFactory.decodeResource(res, R.drawable.value_p_2);
			break;
		case POSITIVE_3:
			icon = BitmapFactory.decodeResource(res, R.drawable.value_p_5);
			break;
		default:
			icon = BitmapFactory.decodeResource(res, R.drawable.value_p_1);
		}
		int imageWidth = icon.getWidth();
		int imageHeight = icon.getHeight();
		int x = imageWidth / 2 + (int) (Math.random() * (width - imageWidth));
		int y = imageHeight / 2 + 50
				+ (int) (Math.random() * (height - imageHeight - 50));
		TapITObject object = new TapITObject(icon, lifeTime, 1000 * value);
		object.getCoordinates().setX(x);
		object.getCoordinates().setY(y);
		graphics.add(object);
	}

	public void removeObjects() {
		List<TapITObject> existingObjects = new ArrayList<TapITObject>();
		for (TapITObject graphic : graphics) {
			if (graphic.getLifeTime() > 0 && !graphic.isClicked()) {
				existingObjects.add(graphic);
			}
		}
		graphics = existingObjects;
	}

	public void updateObjects(long time) {
		for (TapITObject object : graphics) {
			object.updateLifeTime(time);
		}
	}

	public static TapITGame getInstance() {
		return instance;
	}

	public void lvlUp() {
		lvl++;
		if(lvl >= NEGATIVE_1_WEIGHTS.length)
			lvl = NEGATIVE_1_WEIGHTS.length - 1;
		init();
	}

	public long getSpawnTime() {
		return SPT_VALUES[lvl] + (long) (Math.random() * SPTM_VALUES[lvl]);
	}

	public List<TapITObject> getGraphics() {
		return graphics;
	}
	
	private void init(){
		weights.put(NEGATIVE_1, NEGATIVE_1_WEIGHTS[lvl]);
		weights.put(NEGATIVE_2, NEGATIVE_2_WEIGHTS[lvl]);
		weights.put(NEGATIVE_3, NEGATIVE_3_WEIGHTS[lvl]);
		weights.put(POSITIVE_2, 0.3);
		weights.put(POSITIVE_3, 0.1);
		positiveLifetime = PLT_VALUES[lvl];
		positiveLifetimeModifikator = PLTM_VALUES[lvl];
		negativeLifetime = NLT_VALUES[lvl];
		negativeLifetimeModifikator = NLTM_VALUES[lvl];
	}

	public void clear() {
		graphics.clear();
		weights.clear();
		lvl = 0;
		init();
	}

}
