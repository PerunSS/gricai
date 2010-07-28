package com.gricai.central.client.panel;

import javax.swing.JPanel;

import com.gricai.central.client.gui.components.GricaIComboBox;
import com.gricai.central.client.gui.components.GricaIList;
import com.gricai.central.client.gui.components.GricaITextArea;
import com.gricai.central.client.gui.components.GricaITextField;
import com.gricai.central.client.gui.utils.GricaIFontManager;
import com.gricai.central.client.gui.utils.GricaIResolutionManager;

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
	
	private static final double fieldRelativeHeight = 0.025d;	
	private static final double gameNameRelativeWidth = 0.6d;
	private static final double mapListRelativeWidth = 0.5d;
	private static final double mapListRelativeHeight = 0.75d;
	private static final double comboBoxRelativeWidth = 0.25d;
	
	private static final double gameNameRelativePosX = 0.3d;
	private static final double gameNameRelativePosY = 0.01d;
	private static final double mapListRelativePosX = 0.05d;
	private static final double mapListRelativePosY = 0.1d;
	private static final double gameTypeRelativePosX = 0.6d;
	private static final double gameTypeRelativePosY = 0.1d;
	private static final double teamNumberRelativePosX = 0.6d;
	private static final double teamNumberRelativePosY = 0.2d;
	private static final double playersPerTeamRelativePosX = 0.6d;
	private static final double playersPerTeamRelativePosY = 0.3d;
	
	public CreateGamePanel(){
		createPanel();
	}
	
	public void createPanel(){
		removeAll();
		setFont(GricaIFontManager.getFont());
		setLayout(null);
		gameNameField = new GricaITextField("game name",gameNameRelativePosX,gameNameRelativePosY,gameNameRelativeWidth,fieldRelativeHeight);
		mapList = new GricaIList("map list",mapListRelativePosX,mapListRelativePosY,mapListRelativeWidth,mapListRelativeHeight);
		gameTypeComboBox = new GricaIComboBox("game type", gameTypeRelativePosX, gameTypeRelativePosY, comboBoxRelativeWidth, fieldRelativeHeight);
		teamNumberComboBox = new GricaIComboBox("teams", teamNumberRelativePosX, teamNumberRelativePosY, comboBoxRelativeWidth, fieldRelativeHeight);
		playersPerTeamComboBox = new GricaIComboBox("players per team", playersPerTeamRelativePosX, playersPerTeamRelativePosY, comboBoxRelativeWidth, fieldRelativeHeight);
		add(gameNameField.getLabel());
		add(gameNameField);
		add(mapList.getLabel());
		add(mapList);
		setSize((int)GricaIResolutionManager.getGameResolution().getWidth(), (int)GricaIResolutionManager.getGameResolution().getHeight()-30);
		setLocation(0, 30);
	}

}
