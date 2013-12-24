package com.bunnehrealm.gmail.optionalPvP;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinEvent implements Listener{
	MainClass MainClass;

	public PlayerJoinEvent e;
	public Player p;
	Notification Notification;
	
	JoinEvent(Notification Notification){
		this.Notification = Notification;
	}
	
	JoinEvent(MainClass MainClass){
		this.MainClass = MainClass;
	}
	@EventHandler
	public void onJoin(PlayerJoinEvent e){
		@SuppressWarnings("unused")
		Player p = e.getPlayer();
		if(!(MainClass.players.contains("players." + e.getPlayer().getName()))){
			MainClass.players.createSection("players." + e.getPlayer().getName() + ".options.PvPEnabled");
			MainClass.players.set("players." + e.getPlayer().getName() + ".options.PvPEnabled", false);
			MainClass.savePlayers();
		}
		else{
			return;
		}
	}
}
