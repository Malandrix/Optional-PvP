package com.bunnehrealm.gmail.optionalPvP;

import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ItemSpawnEvent;

public class RemoveItem implements Listener{
	@EventHandler
	public void itemDrop(ItemSpawnEvent e){
		if(e.getEntity() instanceof Item){
			
		}
	}
}
