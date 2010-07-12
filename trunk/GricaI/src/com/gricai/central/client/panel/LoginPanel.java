package com.gricai.central.client.panel;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.gricai.central.client.MainGui;

public class LoginPanel extends JPanel {

	private JButton loginButton;
	private JButton exitButton;
	private JTextField usernameField;
	private JPasswordField passwordField;
	
	public LoginPanel(){
		setLayout(new GridLayout(3,2));
		add(new JLabel("Username: "));
		usernameField = new JTextField();
		add(usernameField);
		add(new JLabel("Password: "));
		passwordField = new JPasswordField();
		add(passwordField);
		
		loginButton = new JButton(MainGui.getInstance().getActionManager().getLoginAction());
		add(loginButton);
		exitButton = new JButton(MainGui.getInstance().getActionManager().getExitAction());
		add(exitButton);
	}
	
	public String getUserName(){
		return usernameField.getText();
	}
	
	public String getPassword(){
		return new String(passwordField.getPassword());
	}
}
