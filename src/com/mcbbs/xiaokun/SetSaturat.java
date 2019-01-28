package com.mcbbs.xiaokun;

import java.io.File;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class SetSaturat extends JavaPlugin implements Listener{
	File f = new File(getDataFolder(), "config.yml");
	YamlConfiguration config = YamlConfiguration.loadConfiguration(f);
	@Override
	public void onEnable() {
		getLogger().info("无饥饿插件已经开启！");
		//保存配置文件
		if (!f.exists()) {
		      saveDefaultConfig();
		 }
		//启动监听
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
	}

	
	//取消玩家饱食度事件
	@EventHandler
	public void changeFood(FoodLevelChangeEvent f) {
		Player player = (Player) f.getEntity();
		int hunger = player.getFoodLevel();
		int change = f.getFoodLevel();
		//对字符串进行处理
		String world = String.valueOf(player.getWorld());
		world = world.replace("CraftWorld{name=", "");
		world = world.replace("}", "");
		if (config.getString("world").contains(world + ",") || config.getString("world").contains(world + "]")) {
			if (hunger > change) {
				f.setCancelled(true);
			}
		}
	}
}
