package com.m3z0id.gunGame.papi;

import com.m3z0id.gunGame.GunGame;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public class killCountPlayer extends PlaceholderExpansion {

    @Override
    public @NotNull String getIdentifier() {
        return "gungamekillcount";
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
        if(params.isEmpty()){
            return String.valueOf(GunGame.database.getPlayerKills(player.getName()));
        }
        try {
            int n = Integer.parseInt(params);
            Map<Integer, String> nLargest = GunGame.database.getTopNKillers(n);
            if(nLargest.size() < n) return "Blank";
            return nLargest.get(n);
        } catch (NumberFormatException e) {
            if(GunGame.database.playerExists(params)) return String.valueOf(GunGame.database.getPlayerKills(params));
            return null;
        }
    }
}
