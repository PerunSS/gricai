package com.gricai.game;

import old.messages.Message;

import com.gricai.common.message.common.ChatMessage;
import com.gricai.common.message.game.ChangePositionMessage;
import com.gricai.common.message.game.ChangeTeamMessage;
import com.gricai.common.message.game.CommandsMessage;
import com.gricai.common.message.game.DeniedMessage;
import com.gricai.common.message.game.JoinGameMessage;
import com.gricai.common.message.game.LeaveGameMessage;
import com.gricai.game.server.MessageReceiver;
import com.gricai.game.server.User;

public class GameServer implements MessageReceiver {

	private Game game;
	
	public GameServer(Game game){
		this.game = game;
	}
	
	@Override
	public void connected(User user) {
		// TODO Auto-generated method stub

	}

	@Override
	public void receive(User from, Message msg) {

		if(msg instanceof JoinGameMessage){
			JoinGameMessage joinMsg = (JoinGameMessage)msg;
			String username = joinMsg.getUsername();
			from.getPlayer().setUsername(username);
			int index = game.joinGame(from.getPlayer());
			if(index == Game.FAIL_COMMAND){
				from.send(new DeniedMessage());
			} else {
				from.send(new CommandsMessage());
				game.sendInfoToAll();
			}
		} else if (msg instanceof ChangeTeamMessage){
			ChangeTeamMessage teamMsg = (ChangeTeamMessage)msg;
			int teamNumber = teamMsg.getTeamNumber();
			int index = game.changeTeam(from.getPlayer(), teamNumber);
			if ( index == Game.FAIL_COMMAND){
				from.send(new DeniedMessage());
			} else {
				game.sendInfoToAll();		
			}
		} else if (msg instanceof ChangePositionMessage){
			ChangePositionMessage positionMsg = (ChangePositionMessage)msg;
			int teamPosition = positionMsg.getPosition();
			int index = game.changePosition(from.getPlayer(), teamPosition);
			if ( index == Game.FAIL_COMMAND){
				from.send( new DeniedMessage());
			} else {
				game.sendInfoToAll();
			}
		} else if (msg instanceof LeaveGameMessage){
			Player player = from.getPlayer();
			game.removePlayer(player);
			game.sendInfoToAll();
		} else if (msg instanceof ChatMessage){
			ChatMessage chatMsg = (ChatMessage)msg;
			game.sendChatToAll( chatMsg);
		}
		
	}

}
