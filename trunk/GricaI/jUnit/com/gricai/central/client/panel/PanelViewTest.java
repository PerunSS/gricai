package com.gricai.central.client.panel;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.gricai.central.client.gui.panel.CreateGamePanel;
import com.gricai.central.client.gui.utils.GricaIResolutionManager;

public class PanelViewTest extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PanelViewTest(JPanel testingPannel){
		setSize((Dimension)GricaIResolutionManager.getGameResolution());
		setLocation(0, 0);
		setLayout(null);
		add(testingPannel);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new PanelViewTest(new CreateGamePanel());
	}
}
