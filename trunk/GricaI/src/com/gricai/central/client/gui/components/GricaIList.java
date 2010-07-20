package com.gricai.central.client.gui.components;

import javax.swing.JList;

import com.gricai.central.client.gui.utils.GricaiComponentCreator;

public class GricaIList extends JList {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GricaIList(String name, double x, double y, double width,
			double height) {
		GricaiComponentCreator.setParameters(this, name, x, y, width, height);
	}
}
