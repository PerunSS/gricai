package com.gricai.central.client.gui.components;

import javax.swing.JTextField;

import com.gricai.central.client.gui.utils.GricaiComponentCreator;

public class GricaITextField extends JTextField {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GricaITextField(String name, double x, double y, double width,
			double height) {
		GricaiComponentCreator.setParameters(this, name, x, y, width, height);
	}

}
