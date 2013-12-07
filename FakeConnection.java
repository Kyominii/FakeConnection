package com.aurigaming.fakeconnection;

import java.io.File;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class FakeConnection extends JavaPlugin {
	
	@Override
	public void onEnable() {
	System.out.println("FakeConnection charge avec succes !");
	File config = new File(getDataFolder(), "config.yml");
	if(!config.exists()) {
		this.saveDefaultConfig();
	}
	}

	@Override
	public boolean onCommand(final CommandSender sender, Command cmd, String label,
	String[] args) {
	Player player = null;
	if(sender instanceof Player) {
	player = (Player)sender;
	}
	else {
	return false;
	}
	if(cmd.getName().equalsIgnoreCase("freco")) {
		this.getServer().broadcastMessage(ChatColor.YELLOW + player.getName() + " " + this.getConfig().getString("Messages.Co"));
	}
	if(cmd.getName().equalsIgnoreCase("fdeco")) {
		this.getServer().broadcastMessage(ChatColor.YELLOW + player.getName() + " " + this.getConfig().getString("Messages.Deco"));
	}
	if(cmd.getName().equalsIgnoreCase("fafk")) {
		this.getServer().broadcastMessage(ChatColor.WHITE + player.getDisplayName() + " " + this.getConfig().getString("Messages.AFK"));
	}
	return true;
	}

}
