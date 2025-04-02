package com.m3z0id.gunGame.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.m3z0id.gunGame.GunGame;
import com.m3z0id.gunGame.config.serializers.EnchantmentSerializer;
import com.m3z0id.gunGame.config.serializers.ItemFlagSerializer;
import com.m3z0id.gunGame.config.serializers.MaterialSerializer;
import com.m3z0id.gunGame.config.subclasses.level.LevelEntry;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.util.List;
import java.util.Objects;

public class Levels {
    private List<LevelEntry> levels;
    public List<LevelEntry> getLevels() {
        return levels;
    }

    public static Levels load() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Material.class, new MaterialSerializer())
                .registerTypeAdapter(Enchantment.class, new EnchantmentSerializer())
                .registerTypeAdapter(ItemFlag.class, new ItemFlagSerializer())
                .create();
        File levelsFile = new File(GunGame.instance.getDataFolder(), "levels.json");
        if (!levelsFile.exists()) {
            try {
                Files.copy(Objects.requireNonNull(GunGame.class.getResourceAsStream("/levels.json")), levelsFile.toPath());
            } catch (IOException e) {
                Bukkit.getLogger().severe("Failed to drop GunGame levels: " + levelsFile.getName());
                return null;
            }
        }
        try (Reader reader = new FileReader(levelsFile)) {
            return gson.fromJson(reader, Levels.class);
        } catch (IOException e){
            Bukkit.getLogger().severe("Failed to load GunGame levels: " + levelsFile.getName());
            return null;
        }
    }
}
