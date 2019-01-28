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
		getLogger().info("�޼�������Ѿ�������");
		//���������ļ�
		if (!f.exists()) {
		      saveDefaultConfig();
		 }
		//��������
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
	}

	
	//ȡ����ұ�ʳ���¼�
	@EventHandler
	public void changeFood(FoodLevelChangeEvent f) {
		Player player = (Player) f.getEntity();
		int hunger = player.getFoodLevel();
		int change = f.getFoodLevel();
		//���ַ������д���
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
