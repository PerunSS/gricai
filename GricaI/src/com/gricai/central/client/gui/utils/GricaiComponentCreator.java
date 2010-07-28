package com.gricai.central.client.gui.utils;

import javax.swing.JComponent;

/**
 * Util class that have methods for setting private data of component
 * @author aleksandarvaricak
 *
 */

public final class GricaiComponentCreator {

	/**
	 * method receives component, relative chords and size, and sets real chords and size according to
	 * game resolution, and screen size
	 * @param component
	 * @param name
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 */
	public static final void setParameters(JComponent component, String name, double x, double y, double width, double height){
		component.setName(name);
		double widthModifier = GricaIResolutionManager.getGameResolution()
				.getWidth();
		double heightModifier = GricaIResolutionManager.getGameResolution()
				.getHeight();
		double ratio = 1;
		if (GricaIResolutionManager.isFullScrean()) {
			double ratioWidth = GricaIResolutionManager.getScreanSize()
					.getWidth()
					/ widthModifier;
			double ratioHeight = GricaIResolutionManager.getScreanSize()
					.getHeight()
					/ heightModifier;
			if (ratioWidth > ratioHeight)
				ratio = ratioHeight;
			else
				ratio = ratioWidth;
		}
		component.setLocation((int) (x * widthModifier * ratio), (int) (y
				* heightModifier * ratio));
		component.setSize((int) (width * widthModifier * ratio), (int) (height
				* heightModifier * ratio));
	}
	
}
