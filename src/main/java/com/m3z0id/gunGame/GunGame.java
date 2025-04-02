package com.m3z0id.gunGame;

import com.m3z0id.gunGame.commands.MainCommand;
import com.m3z0id.gunGame.config.*;
import com.m3z0id.gunGame.events.*;
import com.m3z0id.gunGame.papi.deathCountPlayer;
import com.m3z0id.gunGame.papi.killCountPlayer;
import com.m3z0id.gunGame.papi.level;
import com.m3z0id.gunGame.shop.ShopFunctionality;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import java.io.*;
import java.util.*;

public final class GunGame extends JavaPlugin {
    public static Map<String, Integer> levels = new HashMap<>();
    public static Map<String, Integer> kills = new HashMap<>();
    public static Map<String, Integer> deaths = new HashMap<>();
    public static Map<String, Integer> records = new HashMap<>();

    public File deathFile = new File(getDataFolder(), "deaths.json");
    public File killsFile = new File(getDataFolder(), "kills.json");
    public File recordsFile = new File(getDataFolder(), "records.json");

    public List<World> loadedWorlds = new ArrayList<>();

    public static World currentWorld;
    public static GunGame instance;
    public static Config config;
    public static Levels itemLevels;
    public static Lang lang;

    public Economy economy;

    private int worldIndex = 0;

    private BukkitTask worldChanger;

    @Override
    public void onEnable() {
        instance = this;
        load();

        if(!setupEconomy()) {
            Bukkit.getLogger().warning("Disabling due to economy error.");
            Bukkit.getPluginManager().disablePlugin(this);
        }

        Bukkit.getScheduler().runTaskTimer(this, () -> {
            for (Player p : Bukkit.getOnlinePlayers()) {
                if (!p.getWorld().equals(currentWorld)) continue;
                if (p.getLocation().getBlock().getType() == Material.WATER) {
                    Bukkit.getPluginManager().callEvent(new PlayerMoveEvent(p, p.getLocation(), p.getLocation()));
                }
            }
        }, 0, 20);

        Bukkit.getPluginManager().registerEvents(new OnJoin(), this);
        Bukkit.getPluginManager().registerEvents(new OnKill(), this);
        Bukkit.getPluginManager().registerEvents(new OnWater(), this);
        Bukkit.getPluginManager().registerEvents(new OnDisconnect(), this);
        Bukkit.getPluginManager().registerEvents(new OnInteract(), this);
        Bukkit.getPluginManager().registerEvents(new OnInventoryMove(), this);
        Bukkit.getPluginManager().registerEvents(new ShopFunctionality(), this);

        getCommand("gungame").setExecutor(new MainCommand());
        getCommand("gungame").setTabCompleter(new MainCommand());
    }
    public void load(){
        deaths = Stats.loadStat(deathFile);
        kills = Stats.loadStat(killsFile);
        records = Stats.loadStat(recordsFile);
        itemLevels = Levels.load();
        config = Config.loadConfig();
        lang = Lang.loadLang();

        for(String worldName : config.getWorldNames()) {
            loadedWorlds.add(Bukkit.getWorld(worldName));
        }

        if(worldChanger != null){
            if(config.getMapChangeInterval() != 0 && config.getWorldNames().size() > 1){
                worldChanger = Bukkit.getScheduler().runTaskTimer(this, () -> {
                    update(loadedWorlds);
                }, 0, (long) config.getMapChangeInterval()*20*60);
            }
        } else if(!loadedWorlds.isEmpty()) {
            currentWorld = loadedWorlds.get(loadedWorlds.size()-1);
        }

        if (getServer().getPluginManager().getPlugin("PlaceholderAPI") != null) {
            new deathCountPlayer().register();
            new killCountPlayer().register();
            new level().register();
        }
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            Bukkit.getLogger().warning("Vault plugin not found. Disabling...");
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = Bukkit.getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            Bukkit.getLogger().warning("RSP not found. Disabling...");
            return false;
        }
        economy = rsp.getProvider();
        return economy != null;
    }

    public void update(List<World> spawnLocations) {
        if(spawnLocations.isEmpty()) return;
        currentWorld = spawnLocations.get(worldIndex);
        for(Player player : Bukkit.getOnlinePlayers()){
            player.teleport(currentWorld.getSpawnLocation());
        }
        worldIndex++;
        if(worldIndex >= spawnLocations.size()){
            worldIndex = 0;
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
