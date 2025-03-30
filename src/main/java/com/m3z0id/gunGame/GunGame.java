package com.m3z0id.gunGame;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.m3z0id.gunGame.commands.MainCommand;
import com.m3z0id.gunGame.config.*;
import com.m3z0id.gunGame.events.*;
import com.m3z0id.gunGame.papi.deathCountPlayer;
import com.m3z0id.gunGame.papi.killCountPlayer;
import com.m3z0id.gunGame.papi.level;
import com.m3z0id.gunGame.shop.ShopFunctionality;
import com.m3z0id.gunGame.utils.Utils;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

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

    public Economy economy;

    private int worldIndex = 0;

    @Override
    public void onEnable() {
        instance = this;
        load();
    }
    public void load(){
        if (!deathFile.exists()) {
            try {
                Files.createDirectories(deathFile.getParentFile().toPath());
                Files.createFile(deathFile.toPath());
                deaths = new HashMap<>();
            } catch (IOException e) {
                Bukkit.getLogger().severe("Could not create death file: " + deathFile.getAbsolutePath());
                Bukkit.getPluginManager().disablePlugin(this);
            }
        } else {
            Gson gson = new Gson();
            try(InputStream inputStream = new FileInputStream(deathFile)) {
                Type type = new TypeToken<Map<String, Integer>>() {}.getType();
                deaths = gson.fromJson(Utils.is2s(inputStream), type);
            } catch (IOException e) {
                Bukkit.getLogger().severe("Could not load death file: " + deathFile.getAbsolutePath());
                Bukkit.getPluginManager().disablePlugin(this);
            }
        }

        if (!killsFile.exists()) {
            try {
                Files.createDirectories(killsFile.getParentFile().toPath());
                Files.createFile(killsFile.toPath());
                kills = new HashMap<>();
            } catch (IOException e) {
                Bukkit.getLogger().severe("Could not create kill file: " + killsFile.getAbsolutePath());
                Bukkit.getPluginManager().disablePlugin(this);
            }
        } else {
            Gson gson = new Gson();
            try(InputStream inputStream = new FileInputStream(killsFile)) {
                Type type = new TypeToken<Map<String, Integer>>() {}.getType();
                kills = gson.fromJson(Utils.is2s(inputStream), type);
            } catch (IOException e) {
                Bukkit.getLogger().severe("Could not load kill file: " + killsFile.getAbsolutePath());
                Bukkit.getPluginManager().disablePlugin(this);
            }
        }

        if (!recordsFile.exists()) {
            try {
                Files.createDirectories(recordsFile.getParentFile().toPath());
                Files.createFile(recordsFile.toPath());
            } catch (IOException e) {
                Bukkit.getLogger().severe("Could not create records file: " + recordsFile.getAbsolutePath());
                Bukkit.getPluginManager().disablePlugin(this);
            }
        } else {
            Gson gson = new Gson();
            try(InputStream inputStream = new FileInputStream(recordsFile)) {
                Type type = new TypeToken<Map<String, Integer>>() {}.getType();
                records = gson.fromJson(Utils.is2s(inputStream), type);
            } catch (IOException e) {
                Bukkit.getLogger().severe("Could not load kill file: " + recordsFile.getAbsolutePath());
                Bukkit.getPluginManager().disablePlugin(this);
            }
        }

        File configFile = new File("plugins/GunGame/config.json");
        if (!configFile.exists()) {
            try {
                Files.createDirectories(configFile.getParentFile().toPath());
                Files.copy(Objects.requireNonNull(GunGame.class.getResourceAsStream("/config.json")), configFile.toPath());
            } catch (IOException e) {
                Bukkit.getLogger().warning("Disabling due to config file error.");
                Bukkit.getPluginManager().disablePlugin(this);
            }
        }

        config = Config.loadConfig();
        if(config == null) {
            Bukkit.getLogger().warning("Disabling due to config file error.");
            Bukkit.getPluginManager().disablePlugin(this);
        }

        if(!setupEconomy()) {
            Bukkit.getLogger().warning("Disabling due to economy error.");
            Bukkit.getPluginManager().disablePlugin(this);
        }

        if(config.getMapChangeInterval() != 0 && config.getWorldNames().size() > 1){
            Bukkit.getScheduler().runTaskTimer(this, () -> {
                update(loadedWorlds);
            }, 0, (long) config.getMapChangeInterval()*20*60);
        }
        Bukkit.getScheduler().runTaskTimer(this, () -> {
            for (Player p : Bukkit.getOnlinePlayers()) {
                if(!p.getWorld().equals(currentWorld)) continue;
                if(p.getLocation().getBlock().getType() == Material.WATER){
                    Bukkit.getPluginManager().callEvent(new PlayerMoveEvent(p, p.getLocation(), p.getLocation()));
                }
            }
        }, 0, 20);
        if (getServer().getPluginManager().getPlugin("PlaceholderAPI") != null) {
            new deathCountPlayer().register();
            new killCountPlayer().register();
            new level().register();
        }

        Bukkit.getPluginManager().registerEvents(new OnJoin(), this);
        Bukkit.getPluginManager().registerEvents(new OnKill(), this);
        Bukkit.getPluginManager().registerEvents(new OnWater(), this);
        Bukkit.getPluginManager().registerEvents(new OnDisconnect(), this);
        Bukkit.getPluginManager().registerEvents(new OnInteract(), this);
        Bukkit.getPluginManager().registerEvents(new OnInventoryMove(), this);
        Bukkit.getPluginManager().registerEvents(new ShopFunctionality(), this);
        Bukkit.getPluginManager().registerEvents(new OnWorldLoad(), this);

        getCommand("gungame").setExecutor(new MainCommand());
        getCommand("gungame").setTabCompleter(new MainCommand());
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
