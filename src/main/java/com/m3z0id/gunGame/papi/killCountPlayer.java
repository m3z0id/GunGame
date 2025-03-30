package com.m3z0id.gunGame.papi;

import com.m3z0id.gunGame.GunGame;
import com.m3z0id.gunGame.utils.Utils;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;

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
        if(GunGame.kills == null){
            GunGame.kills = new HashMap<>();
        }
        if(params.isEmpty()){
            return GunGame.kills.getOrDefault(player.getName(), 0).toString();
        }
        return switch (params) {
            case "1" -> Utils.getKeyForNthLargestValue(GunGame.kills, 1) + ": " + GunGame.kills.get(Utils.getKeyForNthLargestValue(GunGame.kills, 1));
            case "2" -> Utils.getKeyForNthLargestValue(GunGame.kills, 2) + ": " + GunGame.kills.get(Utils.getKeyForNthLargestValue(GunGame.kills, 2));
            case "3" -> Utils.getKeyForNthLargestValue(GunGame.kills, 3) + ": " + GunGame.kills.get(Utils.getKeyForNthLargestValue(GunGame.kills, 3));
            case "4" -> Utils.getKeyForNthLargestValue(GunGame.kills, 4) + ": " + GunGame.kills.get(Utils.getKeyForNthLargestValue(GunGame.kills, 4));
            case "5" -> Utils.getKeyForNthLargestValue(GunGame.kills, 5) + ": " + GunGame.kills.get(Utils.getKeyForNthLargestValue(GunGame.kills, 5));
            case "6" -> Utils.getKeyForNthLargestValue(GunGame.kills, 6) + ": " + GunGame.kills.get(Utils.getKeyForNthLargestValue(GunGame.kills, 6));
            case "7" -> Utils.getKeyForNthLargestValue(GunGame.kills, 7) + ": " + GunGame.kills.get(Utils.getKeyForNthLargestValue(GunGame.kills, 7));
            case "8" -> Utils.getKeyForNthLargestValue(GunGame.kills, 8) + ": " + GunGame.kills.get(Utils.getKeyForNthLargestValue(GunGame.kills, 8));
            case "9" -> Utils.getKeyForNthLargestValue(GunGame.kills, 9) + ": " + GunGame.kills.get(Utils.getKeyForNthLargestValue(GunGame.kills, 9));
            case "10" -> Utils.getKeyForNthLargestValue(GunGame.kills, 10) + ": " + GunGame.kills.get(Utils.getKeyForNthLargestValue(GunGame.kills, 10));
            default -> GunGame.kills.getOrDefault(params, 0).toString();
        };
    }
}
