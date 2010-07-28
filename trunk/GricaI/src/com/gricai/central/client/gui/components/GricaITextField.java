package com.gricai.central.client.gui.components;

import javax.swing.JLabel;
import javax.swing.JTextField;

import com.gricai.central.client.gui.utils.GricaiComponentCreator;

public class GricaITextField extends JTextField {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel label;

	public GricaITextField(String name, double x, double y, double width,
			double height) {
		GricaiComponentCreator.setParameters(this, name, x, y, width, height);
		label = new JLabel(name);
		GricaiComponentCreator.setParameters(label, name, x-0.3d, y, 0.3d, height);
	}
	
	public JLabel getLabel(){
		return label;
	}

}
