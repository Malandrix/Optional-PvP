package com.bunnehrealm.gmail.optionalPvP;

import java.lang.reflect.Field;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.util.Vector;


public class Notification{
	Field field;
	public Player player;
	public Location headthing;
	public int id;
	public Player p;
	public Item item;
	public JoinEvent eventJoin = new JoinEvent(this);
	MainClass MainClass;
	ItemStack is = new ItemStack(Material.NETHER_STAR, 1);
	ItemMeta im = is.getItemMeta();
	public JoinEvent JoinEvent;
	public Notification(JoinEvent JoinEvent) {
		this.JoinEvent = JoinEvent;
	}
		public void name(final Player player){
		BukkitScheduler bs = Bukkit.getServer().getScheduler();
		bs.scheduleSyncRepeatingTask(MainClass, new Runnable() {
			@Override
			public void run() {
				headthing = player.getLocation().add(0,2,0);
				im.setDisplayName("Notification Item");
				if (MainClass.players.getString(
						"players." + p.getName()
								+ ".options.PvPEnabled").equals("true")) {
					final Item item = p.getWorld().dropItemNaturally(headthing, is);				
					Vector vector = new Vector(0,0,0);
					item.setVelocity(vector);
                    item.setPickupDelay(Integer.MAX_VALUE);
                    MainClass.getServer().getScheduler().scheduleSyncRepeatingTask(MainClass, new Runnable(){
            			@Override
            			public void run() {
            				item.remove();
            				
            				
            			}
                    	
                    }, 0L, 1L);
				}

			}
		}, 20L, 50L);
        
	}
}
