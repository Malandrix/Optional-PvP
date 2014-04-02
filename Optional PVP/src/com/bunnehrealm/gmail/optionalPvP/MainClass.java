package com.bunnehrealm.gmail.optionalPvP;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.util.Vector;

public class MainClass extends JavaPlugin {
	ItemStack is = new ItemStack(Material.NETHER_STAR, 1);
	ItemMeta im = is.getItemMeta();
	MainClass MainClass;
	public MainClass plugin;
	Field field;
	public Player player;
	public Location headthing;
	public int id;
	public Player p;
	public Player p1;
	public Item item;

	public static FileConfiguration players;
	public File playersFile;

	public JoinEvent eventJoin = new JoinEvent(this);
	public PvpCommand pvpCmd = new PvpCommand(this);
	public PreventDamage preventDmg = new PreventDamage(this);

	@Override
	public void onDisable() {
		getLogger().info("Optional PvP has been Disabled!");
		if (playersFile.exists())
			savePlayers();
		else {
			return;
		}
	}

	@Override
	public void onEnable() {
		this.MainClass = plugin;
		getLogger().info("Optional PvP has been Enabled!");

		PluginManager pm = getServer().getPluginManager();

		pm.registerEvents(this.eventJoin, this);
		pm.registerEvents(this.preventDmg, this);

		getCommand("pvp").setExecutor(pvpCmd);

		playersFile = new File(getDataFolder(), "players.yml");
		players = new YamlConfiguration();
		try {
			firstRun();
		} catch (Exception e) {
			return;
		}
		loadPlayers();

	for(final Player p1 : Bukkit.getOnlinePlayers()){
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
			@Override
			public void run() {
				headthing = p1.getLocation().add(0,2,0);
				im.setDisplayName("Notification Item");
				if (MainClass.players.getString(
						"players." + p1.getName()
								+ ".options.PvPEnabled").equals("true")) {
					ArrayList<String> pvplore = new ArrayList<String>();
					pvplore.add(ChatColor.BOLD
							+ "PvP Pyah");
					im.setLore(pvplore);
					final Item item = p1.getWorld().dropItemNaturally(headthing, is);				
					Vector vector = new Vector(0,0,0);
					item.setVelocity(vector);
	                item.setPickupDelay(Integer.MAX_VALUE);
	                MainClass = plugin;
	                p1.sendMessage("swag");
	                
				}
				

			}
		
		}, 20L, 20L);
		getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable(){
			@Override
			public void run() {
				item.remove();
				p1.sendMessage("sweg");
				
			}
        	
        }, 20L, 30L);
		}
		}
			public void name(){
			BukkitScheduler bs = Bukkit.getServer().getScheduler();
			bs.scheduleSyncRepeatingTask(this, new Runnable() {
				@Override
				public void run() {
					headthing = p1.getLocation().add(0,2,0);
					im.setDisplayName("Notification Item");
					if (MainClass.players.getString(
							"players." + p1.getName()
									+ ".options.PvPEnabled").equals("true")) {
						final Item item = p1.getWorld().dropItemNaturally(headthing, is);				
						Vector vector = new Vector(0,0,0);
						item.setVelocity(vector);
	                    item.setPickupDelay(Integer.MAX_VALUE);
	                    getServer().getScheduler().scheduleSyncRepeatingTask(MainClass, new Runnable(){
	            			@Override
	            			public void run() {
	            				item.remove();
	            				
	            				
	            			}
	                    	
	                    }, 0L, 1L);
					}

				}
			}, 20L, 50L);
	        
		}



	public void firstRun() throws Exception {
		if (!playersFile.exists()) {
			getLogger().info("Creating a players.yml file");
			playersFile.getParentFile().mkdirs();
			playersFile.createNewFile();
		}
	}

	public void loadPlayers() {
		try {
			players.load(playersFile);
		} catch (Exception e) {
			return;
		}
	}

	public void savePlayers() {
		try {
			players.save(playersFile);
		} catch (IOException e) {
			return;
		}
	}
}
