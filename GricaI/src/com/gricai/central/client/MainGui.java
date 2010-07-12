package com.gricai.central.client;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import com.gricai.central.client.action.ActionManager;
import com.gricai.central.client.panel.LoginPanel;

public class MainGui extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static MainGui instance = new MainGui();
	private ActionManager actionManager = new ActionManager();
	private Client client;
	private LoginPanel loginPanel;
	

	public ActionManager getActionManager(){
		return actionManager;
	}
	
	public static MainGui getInstance(){
		return instance;
	}
	
	public MainGui(){
			
		setSize(800, 600);
		setLocation(100, 100);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public void prepare(){
		client = new Client();
		Thread thread = new Thread(client);
		thread.start();
		loginPanel= new LoginPanel();
		getContentPane().add(loginPanel,BorderLayout.CENTER);
		pack();
//		cards.addLayoutComponent(new LoginPanel(), LOGIN_PANEL);
//		cards.show(getContentPane(), LOGIN_PANEL);
	}
	
	public static void main(String[] args) {
		MainGui.getInstance().prepare();
	}

	public LoginPanel getLoginPanel() {
		return loginPanel;
	}

	public Client getClient() {
		return client;
	}
}
	
