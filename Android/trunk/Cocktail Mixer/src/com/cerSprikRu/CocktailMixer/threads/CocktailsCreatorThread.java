package com.cerSprikRu.CocktailMixer.threads;

import com.cerSprikRu.CocktailMixer.model.drink.CocktailCreator;

public class CocktailsCreatorThread implements Runnable {
	  public String name; // name of thread

	  public Thread t;

	  public CocktailsCreatorThread(String threadname) {
	    name = threadname;
	    t = new Thread(this, name);
	    t.start();
	  }

	  public void run() {
	      CocktailCreator.getInstance().createCocktails();
	  }
	}