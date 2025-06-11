package com.m3z0id.gunGame.papi;

import com.m3z0id.gunGame.GunGame;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Stats extends PlaceholderExpansion {
    @Override
    public @NotNull String getIdentifier() {
        return "ggstats";
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
        List<String> args = new ArrayList<>(Arrays.asList(params.split("_")));
        if(args.isEmpty()) {
            return "Blank";
        }
        if(args.get(0).equals("kills")){
            if(GunGame.database.playerExists(player.getName())) return String.valueOf(GunGame.database.getPlayerKills(player.getName()));
            if(args.size() == 2 && GunGame.database.playerExists(args.get(1))) return String.valueOf(GunGame.database.getPlayerKills(args.get(1)));
            if(args.get(1).startsWith("top") && args.size() == 3){
                try {
                    int top = Integer.parseInt(args.get(1).replaceAll("top", ""));
                    if(args.get(2).equals("name")) return GunGame.database.getNthKiller(top);
                    if(args.get(2).equals("value")) return String.valueOf(GunGame.database.getPlayerKills(GunGame.database.getNthKiller(top)));
                    return "Blank";
                } catch (NumberFormatException e) {
                    return "Blank";
                }
            }
        }
        if(args.get(0).equals("deaths")){
            if(GunGame.database.playerExists(player.getName())) return String.valueOf(GunGame.database.getPlayerDeaths(player.getName()));
            if(args.size() == 2 && GunGame.database.playerExists(args.get(1))) return String.valueOf(GunGame.database.getPlayerDeaths(args.get(1)));
            if(args.get(1).startsWith("top") && args.size() == 3){
                try {
                    int top = Integer.parseInt(args.get(1).replaceAll("top", ""));
                    GunGame.instance.getLogger().info(args.get(2));
                    GunGame.instance.getLogger().info("Name: " + args.get(2).equals("name"));
                    GunGame.instance.getLogger().info("Deaths: " + args.get(2).equals("deaths"));
                    if(args.get(2).equals("name")) return GunGame.database.getNthDead(top);
                    if(args.get(2).equals("value")) return String.valueOf(GunGame.database.getPlayerDeaths(GunGame.database.getNthDead(top)));
                    return "Blank";
                } catch (NumberFormatException e) {
                    return "Blank";
                }
            }
        }
        if(args.get(0).equals("toplevels")){
            if(GunGame.database.playerExists(player.getName())) return String.valueOf(GunGame.database.getPlayerHighestLevel(player.getName()));
            if(args.size() == 2 && GunGame.database.playerExists(args.get(1))) return String.valueOf(GunGame.database.getPlayerHighestLevel(args.get(1)));
            if(args.get(1).startsWith("top") && args.size() == 3){
                try {
                    int top = Integer.parseInt(args.get(1).replaceAll("top", ""));
                    if(args.get(2).equals("name")) return GunGame.database.getNthHighestLevel(top);
                    if(args.get(2).equals("value")) return String.valueOf(GunGame.database.getPlayerHighestLevel(GunGame.database.getNthHighestLevel(top)));
                    return "Blank";
                } catch (NumberFormatException e) {
                    return "Blank";
                }
            }
        }
        if(args.get(0).equals("levels")){
            if(GunGame.levels.containsKey(player.getName())) return String.valueOf(GunGame.levels.get(player.getName()));
            if(args.size() == 2 && GunGame.levels.containsKey(args.get(1))) return String.valueOf(GunGame.levels.get(args.get(1)));
            return "Blank";
        }
        return "Blank";
    }
}
