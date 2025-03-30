package com.m3z0id.gunGame.papi;

import com.m3z0id.gunGame.GunGame;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;

public class level extends PlaceholderExpansion {
    @Override
    public @NotNull String getIdentifier() {
        return "gungamelevel";
    }

    @Override
    public @NotNull String getAuthor() {
        return String.join(", ", GunGame.instance.getDescription().getAuthors());
    }

    @Override
    public @NotNull String getVersion() {
        return GunGame.instance.getDescription().getVersion();
    }

    @Override
    public @Nullable String onRequest(OfflinePlayer player, @NotNull String params) {
        if(GunGame.levels == null) GunGame.levels = new HashMap<>();
        if(params.isEmpty()){
            return String.valueOf(GunGame.levels.getOrDefault(player.getName(), 1));
        } else if(params.equals("record")){
            if(GunGame.records == null) GunGame.records = new HashMap<>();
            return String.valueOf(GunGame.records.getOrDefault(player.getName(), 1));
        } else {
            return "1";
        }
    }
}
