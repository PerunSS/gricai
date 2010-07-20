package com.gricai.central.client.panel;

import javax.swing.JPanel;

import com.gricai.central.client.gui.components.GricaIComboBox;
import com.gricai.central.client.gui.components.GricaIList;
import com.gricai.central.client.gui.components.GricaITextArea;
import com.gricai.central.client.gui.components.GricaITextField;

public class CreateGamePanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private GricaITextField gameNameField;
	private GricaIList mapList;
	private GricaIComboBox teamNumberComboBox;
	private GricaIComboBox playersPerTeamComboBox;
	private GricaIComboBox gameTypeComboBox;
	private GricaITextArea mapDescriptionArea;
	
	private static final double fieldRelativeHeight = 0.05d;	
	private static final double gameNameRelativeWidth = 0.6d;
	
	
	private static final double gameNameRelativePosX = 0.3d;
	private static final double gameNameRelativePosY = 0.01d;
	
	
	public CreateGamePanel(){
		setLayout(null);
		gameNameField = new GricaITextField("game name",gameNameRelativePosX,gameNameRelativePosY,gameNameRelativeWidth,fieldRelativeHeight);
	}

}
