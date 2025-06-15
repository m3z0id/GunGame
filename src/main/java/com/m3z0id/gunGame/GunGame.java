package com.m3z0id.gunGame;

import com.m3z0id.gunGame.commands.MainCommand;
import com.m3z0id.gunGame.config.*;
import com.m3z0id.gunGame.database.Database;
import com.m3z0id.gunGame.events.*;
import com.m3z0id.gunGame.papi.*;
import com.m3z0id.gunGame.shop.ShopFunctionality;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import java.util.*;

public final class GunGame extends JavaPlugin {
    public static Map<String, Integer> levels = new HashMap<>();

    public List<World> loadedWorlds = new ArrayList<>();

    public static World currentWorld;
    public static GunGame instance;
    public static Config config;
    public static Levels itemLevels;
    public static Lang lang;
    public static Database database;

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
                if(!p.getWorld().equals(currentWorld)) continue;
                if(p.getLocation().getBlock().getType() == Material.WATER){
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

        this.getLifecycleManager().registerEventHandler(LifecycleEvents.COMMANDS, commands -> {
            commands.registrar().register("gungame", new MainCommand());
        });
    }
    public void load(){
        getDataFolder().mkdirs();
        database = Database.get();
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
            currentWorld = loadedWorlds.getLast();
        }

        if (getServer().getPluginManager().getPlugin("PlaceholderAPI") != null) {
            new Stats().register();
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
