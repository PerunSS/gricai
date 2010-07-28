package com.gricai.central.client.gui.components;

import javax.swing.JLabel;
import javax.swing.JList;

import com.gricai.central.client.gui.utils.GricaiComponentCreator;

public class GricaIList extends JList {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel label;
	
	public GricaIList(String name, double x, double y, double width,
			double height) {
		GricaiComponentCreator.setParameters(this, name, x, y, width, height);
		label = new JLabel(name);
		GricaiComponentCreator.setParameters(label, name, x, y-0.1d, width, 0.075d);
	}

	public JLabel getLabel() {
		return label;
	}
}
