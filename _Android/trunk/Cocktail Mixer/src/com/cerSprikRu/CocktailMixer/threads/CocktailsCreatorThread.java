package com.cerSprikRu.CocktailMixer.threads;

import com.cerSprikRu.CocktailMixer.model.drink.CocktailCreator;

public class CocktailsCreatorThread implements Runnable {
	  public String name; // name of thread

	  public Thread t;
	  boolean fullRandom;

	  public CocktailsCreatorThread(String threadname, boolean fullRandom) {
	    name = threadname;
	    this.fullRandom = fullRandom;
	    t = new Thread(this, name);
	    
	    t.start();
	  }

	  public void run() {
	      CocktailCreator.getInstance().createCocktails(fullRandom);
	  }
	}