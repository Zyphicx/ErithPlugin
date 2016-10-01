package com.gmail.zyphicxs;

import entities.EntityInfo;
import entities.MobFile;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{

    public static Plugin instance;

    @Override
    public void onEnable(){
        instance = this;
        getServer().getPluginManager().registerEvents(new Events(), this);

        Commands commands = new Commands();
        getCommand("grapple").setExecutor(commands);
        getCommand("homingarrow").setExecutor(commands);
        getCommand("grenade").setExecutor(commands);
        getCommand("cogdrive").setExecutor(commands);
        getCommand("turret").setExecutor(commands);
        getCommand("doFriend").setExecutor(commands);
        getCommand("friend").setExecutor(commands);
        getCommand("spawnZombie").setExecutor(commands);
        getCommand("giveItem").setExecutor(commands);
        getCommand("test").setExecutor(commands);

        getLogger().info("Loading mobs...");
        EntityInfo.mobs = MobFile.loadAllMobs();
        getLogger().info("Finished loading mobs!");

        getLogger().info("ErithPlugin is up and running!");
    }

    @Override
    public void onDisable(){
        getLogger().info("ErithPlugin is shutting down and running!");
    }
}
