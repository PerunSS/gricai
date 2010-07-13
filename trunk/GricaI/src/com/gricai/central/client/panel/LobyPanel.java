package com.gricai.central.client.panel;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import com.gricai.central.client.MainGui;

public class LobyPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	
	private JList roomList;
	private JButton exitButton;
	private JTextField roomNameField;
	
	public LobyPanel(){
				
		setLayout(new GridLayout(1,3));
		add(new JLabel("Select Room: "));
		roomList = new JList(); //data has type Object[]
		roomList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		roomList.setLayoutOrientation(JList.VERTICAL);
		roomList.setVisibleRowCount(25);
		JScrollPane listScroller = new JScrollPane(roomList);
		listScroller.setPreferredSize(new Dimension(250, 80));
		add(roomList);
		roomNameField = new JTextField();
		add(roomNameField);
		
		exitButton = new JButton(MainGui.getInstance().getActionManager().getExitAction());
		add(exitButton);
	}
	
}
