package com.m3z0id.gunGame.papi;

import com.m3z0id.gunGame.GunGame;
import com.m3z0id.gunGame.database.GunGamePlayer;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

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
        if(params.isEmpty()){
            return String.valueOf(GunGamePlayer.fromPlayer((Player) player).getCurrentLevel());
        }
        List<String> args = new ArrayList<>(Arrays.asList(params.split("_")));
        if(args.isEmpty()){
            return null;
        }

        if(args.get(0).equals("records") && args.size() == 2) {
            try {
                int n = Integer.parseInt(args.get(1));
                Map<Integer, String> nLargest = GunGame.database.getTopNHighestLevels(n);
                if (nLargest.size() < n) return "Blank";
                return nLargest.get(n);
            } catch (NumberFormatException e) {
                if (GunGame.database.playerExists(params)) return String.valueOf(GunGame.database.getPlayerKills(params));
                return null;
            }
        } else if(args.get(0).equals("records") && args.size() == 1) {
            GunGamePlayer p = GunGamePlayer.fromPlayer((Player) player);
            if(p != null) return String.valueOf(p.getHighestLevel());
            return null;
        }

        GunGamePlayer p = GunGamePlayer.fromPlayer((Player) player);
        if(p != null) return String.valueOf(p.getCurrentLevel());
        return null;
    }
}
