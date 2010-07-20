package com.gricai.central.client.gui.utils;

import javax.swing.JComponent;

public class GricaiComponentCreator {

	public static void setParameters(JComponent component, String name, double x, double y, double width, double height){
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
