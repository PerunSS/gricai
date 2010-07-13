package com.gricai.central.client.panel;

import java.awt.Dimension;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class CreateGamePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JTextField gameNameField;
	private JList mapList;
	private JComboBox teamNumberComboBox;
	private JComboBox playersPerTeamComboBox;
	private JRadioButton ffaGameRadioButton, teamGameRadioButton;
	private JTextArea mapDescriptionArea;
	private int width, height;
	
	public CreateGamePanel(int width, int height){
		setLayout(null);
		this.width = width;
		this.height = height;
		
	}
	

}
