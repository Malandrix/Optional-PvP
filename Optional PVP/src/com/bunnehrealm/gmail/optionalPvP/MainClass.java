package com.bunnehrealm.gmail.optionalPvP;

import java.io.File;
import java.io.IOException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class MainClass extends JavaPlugin{

public FileConfiguration players;
public File playersFile;

public JoinEvent eventJoin = new JoinEvent(this);
public PvpCommand pvpCmd = new PvpCommand(this);
public PreventDamage preventDmg = new PreventDamage(this);

@Override
public void onDisable(){
	getLogger().info("Optional PvP has been Disabled!");
	if(playersFile.exists())
	savePlayers();
	else{
		return;
	}
}
@Override
public void onEnable(){	
	getLogger().info("Optional PvP has been Enabled!");
	
	PluginManager pm = getServer().getPluginManager();
	
	pm.registerEvents(this.eventJoin, this);
	pm.registerEvents(this.preventDmg, this);
	
	getCommand("pvp").setExecutor(pvpCmd);
	
	playersFile = new File(getDataFolder(), "players.yml");
	players = new YamlConfiguration();
	try{
		firstRun();
	}
	catch(Exception e){
		return;
	}
	loadPlayers();
	
}
public void firstRun() throws Exception{
	if(!playersFile.exists()){
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
public void savePlayers(){
	try{
	players.save(playersFile);
}
	catch(IOException e){
		return;
	}
}
}
