package com.m3z0id.gunGame.config;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.bukkit.Bukkit;

import java.io.*;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class Stats {
    public static Map<String, Integer> loadStat(File file) {
        if(!file.exists()) {
            try {
                file.createNewFile();
                return new HashMap<>();
            } catch (IOException e) {
                Bukkit.getLogger().severe("Could not create file: " + file.getName());
            }
        }
        Gson gson = new Gson();

        try(Reader reader = new FileReader(file)) {
            Type type = new TypeToken<Map<String, Integer>>() {}.getType();
            return gson.fromJson(reader, type);
        } catch (IOException e) {
            Bukkit.getLogger().severe("Could not load stats file: " + file.getName());
        }
        return null;
    }
    public static void saveStat(File file, Map<String, Integer> stats) {
        Gson gson = new Gson();
        String json = gson.toJson(stats);

        try (FileWriter writer = new FileWriter(file)){
            writer.write(json);
        } catch (IOException e){
            Bukkit.getLogger().severe("Could not save stats file: " + file.getName());
        }
    }
}
