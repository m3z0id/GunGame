package com.m3z0id.gunGame.papi;

import com.m3z0id.gunGame.GunGame;
import com.m3z0id.gunGame.utils.Utils;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;

public class deathCountPlayer extends PlaceholderExpansion {

    @Override
    public @NotNull String getIdentifier() {
        return "gungamedeathcount";
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
        if(GunGame.deaths == null) GunGame.deaths = new HashMap<>();
        if(params.isEmpty()){
            return GunGame.deaths.getOrDefault(player.getName(), 0).toString();
        }
        return switch (params) {
            case "1" -> Utils.getKeyForNthLargestValue(GunGame.deaths, 1) + ": " + GunGame.deaths.get(Utils.getKeyForNthLargestValue(GunGame.deaths, 1));
            case "2" -> Utils.getKeyForNthLargestValue(GunGame.deaths, 2) + ": " + GunGame.deaths.get(Utils.getKeyForNthLargestValue(GunGame.deaths, 2));
            case "3" -> Utils.getKeyForNthLargestValue(GunGame.deaths, 3) + ": " + GunGame.deaths.get(Utils.getKeyForNthLargestValue(GunGame.deaths, 3));
            case "4" -> Utils.getKeyForNthLargestValue(GunGame.deaths, 4) + ": " + GunGame.deaths.get(Utils.getKeyForNthLargestValue(GunGame.deaths, 4));
            case "5" -> Utils.getKeyForNthLargestValue(GunGame.deaths, 5) + ": " + GunGame.deaths.get(Utils.getKeyForNthLargestValue(GunGame.deaths, 5));
            case "6" -> Utils.getKeyForNthLargestValue(GunGame.deaths, 6) + ": " + GunGame.deaths.get(Utils.getKeyForNthLargestValue(GunGame.deaths, 6));
            case "7" -> Utils.getKeyForNthLargestValue(GunGame.deaths, 7) + ": " + GunGame.deaths.get(Utils.getKeyForNthLargestValue(GunGame.deaths, 7));
            case "8" -> Utils.getKeyForNthLargestValue(GunGame.deaths, 8) + ": " + GunGame.deaths.get(Utils.getKeyForNthLargestValue(GunGame.deaths, 8));
            case "9" -> Utils.getKeyForNthLargestValue(GunGame.deaths, 9) + ": " + GunGame.deaths.get(Utils.getKeyForNthLargestValue(GunGame.deaths, 9));
            case "10" -> Utils.getKeyForNthLargestValue(GunGame.deaths, 10) + ": " + GunGame.deaths.get(Utils.getKeyForNthLargestValue(GunGame.deaths, 10));
            default -> GunGame.deaths.getOrDefault(params, 0).toString();
        };
    }
}
