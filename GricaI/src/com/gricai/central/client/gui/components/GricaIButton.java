package com.gricai.central.client.gui.components;

import javax.swing.JButton;

import com.gricai.central.client.gui.utils.GricaiComponentCreator;

public class GricaIButton extends JButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GricaIButton(String name, double x, double y, double width,
			double height) {
		GricaiComponentCreator.setParameters(this, name, x, y, width, height);
	}
}
