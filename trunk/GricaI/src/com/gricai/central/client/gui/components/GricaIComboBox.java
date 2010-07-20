package com.gricai.central.client.gui.components;

import javax.swing.JComboBox;

import com.gricai.central.client.gui.utils.GricaiComponentCreator;

public class GricaIComboBox extends JComboBox {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GricaIComboBox(String name, double x, double y, double width,
			double height) {
		GricaiComponentCreator.setParameters(this, name, x, y, width, height);
	}
}
