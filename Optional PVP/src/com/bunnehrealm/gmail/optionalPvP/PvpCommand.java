package com.bunnehrealm.gmail.optionalPvP;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PvpCommand implements CommandExecutor{
	public MainClass MainClass;

	public PvpCommand(MainClass MainClass){
		this.MainClass = MainClass;
	}
	
	public boolean pvpOption;

	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String string,
			String[] args) {
		if(string.equalsIgnoreCase("pvp")){
			if(!(cs instanceof Player)){
				cs.sendMessage("This is a player only command!");
				return false;
			}
			else{
				Player player = (Player) cs;
				player.sendMessage("leed");
			if(args.length > 0){
				return false;
			}
			else{
				if(MainClass.players.getString("players." + player.getName() + ".options.PvPEnabled").equalsIgnoreCase("false")){
					player.sendMessage("woo");
					MainClass.players.set("players." + player.getName() + ".options.PvPEnabled", "true");
					MainClass.savePlayers();
					player.sendMessage(ChatColor.GOLD + "Pvp Enabled!");
				}
				else if(MainClass.players.getString("players." + player.getName() + ".options.PvPEnabled").equalsIgnoreCase("true")){
					player.sendMessage("hehe");
					MainClass.players.set("players." + player.getName() + ".options.PvPEnabled", "false");
					MainClass.savePlayers();
					player.sendMessage(ChatColor.GOLD + "Pvp Disabled!");
				}
			}
		}
		return false;
	}
		return false;

}
	}
