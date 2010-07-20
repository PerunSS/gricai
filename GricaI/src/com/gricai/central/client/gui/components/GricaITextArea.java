package com.gricai.central.client.gui.components;

import javax.swing.JTextArea;

import com.gricai.central.client.gui.utils.GricaiComponentCreator;

public class GricaITextArea extends JTextArea {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GricaITextArea(String name, double x, double y, double width,
			double height) {
		GricaiComponentCreator.setParameters(this, name, x, y, width, height);
	}
}
