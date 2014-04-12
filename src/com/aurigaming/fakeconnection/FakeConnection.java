package com.aurigaming.fakeconnection;

import java.io.File;
import java.io.IOException;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;

public class FakeConnection extends JavaPlugin {
	
	private ProtocolManager protocolManager;
    private HidePlayerList hidePlayer;
	
	@Override
	public void onEnable() {
	hidePlayer = new HidePlayerList(this);
	protocolManager = ProtocolLibrary.getProtocolManager();
	File config = new File(getDataFolder(), "config.yml");
	if(!config.exists()) {
		this.saveDefaultConfig();
	}
	if(this.getConfig().getString("Others.mcstats")=="true") {
		try {
			Metrics metrics = new Metrics(this);
			metrics.start();
		} catch (IOException e) {
			// Failed to submit the stats :-(
		}
	}
	System.out.println("FakeConnection charge avec succes !");
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
		hidePlayer.showPlayer(player);
		for(Player p : getServer().getOnlinePlayers()) {
			player.showPlayer(player);
		}
		player.sendMessage(ChatColor.GRAY + "[" + ChatColor.GOLD + "FakeConnection" + ChatColor.GRAY + "] " + ChatColor.GREEN + "Hello :D");
	}
	if(cmd.getName().equalsIgnoreCase("fdeco")) {
		this.getServer().broadcastMessage(ChatColor.YELLOW + player.getName() + " " + this.getConfig().getString("Messages.Deco"));
		hidePlayer.hidePlayer(player);
		for(Player p : getServer().getOnlinePlayers()) {
			player.hidePlayer(player);
		}
		player.sendMessage(ChatColor.GRAY + "[" + ChatColor.GOLD + "FakeConnection" + ChatColor.GRAY + "] " + ChatColor.GREEN + "Bye bye ;)");
	}
	if(cmd.getName().equalsIgnoreCase("fafk")) {
		this.getServer().broadcastMessage(ChatColor.WHITE + player.getDisplayName() + " " + this.getConfig().getString("Messages.AFK"));
	}
	if(cmd.getName().equalsIgnoreCase("fnafk")) {
		this.getServer().broadcastMessage(ChatColor.WHITE + player.getDisplayName() + " " + this.getConfig().getString("Messages.nAFK"));
	}
	return true;
	}

}
