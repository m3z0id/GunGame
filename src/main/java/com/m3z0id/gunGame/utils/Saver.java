package com.m3z0id.gunGame.utils;

import com.m3z0id.gunGame.GunGame;
import org.bukkit.Bukkit;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class Saver {
    public static void saveMap(Map<String, Integer> map, File file) {
        Yaml yaml = new Yaml();
        try (FileWriter writer = new FileWriter(file)) {
            yaml.dump(map, writer);
        } catch (IOException e) {
            Bukkit.getLogger().warning("Could not save map to " + file.getAbsolutePath());
            Bukkit.getPluginManager().disablePlugin(GunGame.instance);
        }
    }
}
