package com.bunnehrealm.gmail.optionalPvP;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class PreventDamage implements Listener {
	MainClass MainClass;

	PreventDamage(MainClass MainClass){
		this.MainClass = MainClass;
	}

	@EventHandler
	public void onDamage(EntityDamageByEntityEvent e){
		e.setCancelled(true);
		if(e.getEntity() instanceof Player){
			if(e.getDamager() instanceof Player){
				Player enemy =  (Player) e.getDamager();
				if(MainClass.players.getString("players." + enemy.getName() + ".options.PvPEnabled").equalsIgnoreCase("true") && MainClass.players.getString("players." + ((Player) e.getEntity()).getName() + ".options.PvPEnabled").equalsIgnoreCase("true") ){
				e.setCancelled(false);	
				}
				else
					e.setCancelled(true);
				
			}
		}
	}
}

