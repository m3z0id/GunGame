package com.m3z0id.gunGame.commands;

import com.m3z0id.gunGame.GunGame;
import com.m3z0id.gunGame.Updater;
import com.m3z0id.gunGame.utils.Saver;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainCommand implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(args.length < 1){
            commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', GunGame.config.getServerPrefix() + GunGame.config.getInvalidSubcommand()));
            return true;
        }
        if(args[0].equalsIgnoreCase("stats")){
            if(args.length != 5){
                commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', GunGame.config.getServerPrefix() + GunGame.config.getInvalidSubcommand()));
                return true;
            }
            OfflinePlayer player = Bukkit.getOfflinePlayer(args[1]);
            String method = args[2];
            Integer amount;
            try {
                amount = Integer.parseInt(args[3]);
            } catch (NumberFormatException e) {
                commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', GunGame.config.getServerPrefix() + GunGame.config.getInvalidSubcommand()));
                return true;
            }
            String field = args[4];

            if(method.equalsIgnoreCase("add")){
                if(field.equalsIgnoreCase("kills")){
                    if(GunGame.kills == null) GunGame.kills = new HashMap<>();
                    GunGame.kills.put(player.getName(), GunGame.kills.getOrDefault(player.getName(), 0) + amount);
                    Saver.saveMap(GunGame.kills, GunGame.instance.killsFile);
                    commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', GunGame.config.getServerPrefix() + GunGame.config.getSuccessMessage()));
                    return true;
                }
                else if(field.equalsIgnoreCase("deaths")){
                    if(GunGame.deaths == null) GunGame.deaths = new HashMap<>();
                    GunGame.deaths.put(player.getName(), GunGame.deaths.getOrDefault(player.getName(), 0) + amount);
                    Saver.saveMap(GunGame.deaths, GunGame.instance.deathFile);
                    commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', GunGame.config.getServerPrefix() + GunGame.config.getSuccessMessage()));
                    return true;
                }
                else if(field.equalsIgnoreCase("levels")){
                    if(GunGame.levels == null) GunGame.levels = new HashMap<>();
                    GunGame.levels.put(player.getName(), GunGame.levels.getOrDefault(player.getName(), 0) + amount);
                    Updater.updatePlayer(player.getUniqueId(), GunGame.levels.getOrDefault(player.getName(), 1));
                    commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', GunGame.config.getServerPrefix() + GunGame.config.getSuccessMessage()));
                    return true;
                }
                else {
                    commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', GunGame.config.getServerPrefix() + GunGame.config.getInvalidSubcommand()));
                    return true;
                }
            }
            if(method.equalsIgnoreCase("set")){
                if(field.equalsIgnoreCase("kills")){
                    if(GunGame.kills == null) GunGame.kills = new HashMap<>();
                    GunGame.kills.put(player.getName(), amount);
                    Saver.saveMap(GunGame.kills, GunGame.instance.killsFile);
                    commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', GunGame.config.getServerPrefix() + GunGame.config.getSuccessMessage()));
                    return true;
                }
                else if(field.equalsIgnoreCase("deaths")){
                    if(GunGame.deaths == null) GunGame.deaths = new HashMap<>();
                    GunGame.deaths.put(player.getName(), amount);
                    Saver.saveMap(GunGame.deaths, GunGame.instance.deathFile);
                    commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', GunGame.config.getServerPrefix() + GunGame.config.getSuccessMessage()));
                    return true;
                }
                else if(field.equalsIgnoreCase("levels")){
                    if(GunGame.levels == null) GunGame.levels = new HashMap<>();
                    GunGame.levels.put(player.getName(), Math.max(1, amount));
                    Updater.updatePlayer(player.getUniqueId(), GunGame.levels.getOrDefault(player.getName(), 1));
                    commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', GunGame.config.getServerPrefix() + GunGame.config.getSuccessMessage()));
                    return true;
                }
                else {
                    commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', GunGame.config.getServerPrefix() + GunGame.config.getInvalidSubcommand()));
                    return true;
                }
            }
            if(method.equalsIgnoreCase("remove")){
                if(field.equalsIgnoreCase("kills")){
                    if(GunGame.kills == null) GunGame.kills = new HashMap<>();
                    GunGame.kills.put(player.getName(), Math.max(GunGame.kills.getOrDefault(player.getName(), 0) - amount, 0));
                    Saver.saveMap(GunGame.kills, GunGame.instance.killsFile);
                    commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', GunGame.config.getServerPrefix() + GunGame.config.getSuccessMessage()));
                    return true;
                }
                else if(field.equalsIgnoreCase("deaths")){
                    if(GunGame.deaths == null) GunGame.deaths = new HashMap<>();
                    GunGame.deaths.put(player.getName(), Math.max(GunGame.deaths.getOrDefault(player.getName(), 0) - amount, 0));
                    Saver.saveMap(GunGame.deaths, GunGame.instance.deathFile);
                    commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', GunGame.config.getServerPrefix() + GunGame.config.getSuccessMessage()));
                    return true;
                }
                else if(field.equalsIgnoreCase("levels")){
                    if(GunGame.levels == null) GunGame.levels = new HashMap<>();
                    GunGame.levels.put(player.getName(), Math.max(GunGame.levels.getOrDefault(player.getName(), 1) - amount, 1));
                    Updater.updatePlayer(player.getUniqueId(), GunGame.levels.getOrDefault(player.getName(), 1));
                    commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', GunGame.config.getServerPrefix() + GunGame.config.getSuccessMessage()));
                    return true;
                }
                else {
                    commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', GunGame.config.getServerPrefix() + GunGame.config.getInvalidSubcommand()));
                    return true;
                }
            }
        }
        else if(args[0].equalsIgnoreCase("reset")){
            GunGame.records.clear();
            GunGame.deaths.clear();
            GunGame.kills.clear();
            GunGame.levels.clear();

            for(Player p : Bukkit.getOnlinePlayers()){
                Updater.updatePlayer(p.getUniqueId(), 1);
            }

            Saver.saveMap(GunGame.deaths, GunGame.instance.deathFile);
            Saver.saveMap(GunGame.kills, GunGame.instance.killsFile);
            Saver.saveMap(GunGame.records, GunGame.instance.recordsFile);

            commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', GunGame.config.getServerPrefix() + GunGame.config.getSuccessMessage()));
            return true;
        } else {
            commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', GunGame.config.getServerPrefix() + GunGame.config.getInvalidSubcommand()));
            return true;
        }
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(args.length == 1){
            List<String> list = new ArrayList<>();
            if("reset".startsWith(args[0])){
                list.add("reset");
            }
            if("stats".startsWith(args[0])){
               list.add("stats");
            }
            return list;
        }
        else if(args.length == 2){
            return null;
        }
        else if(args.length == 3){
            List<String> list = new ArrayList<>();
            if("set".startsWith(args[2])){
                list.add("set");
            }
            if("remove".startsWith(args[2])){
                list.add("remove");
            }
            if("add".startsWith(args[2])){
                list.add("add");
            }
            return list;
        }
        else if(args.length == 4){
            List<String> list = new ArrayList<>();
            if("1".startsWith(args[3])){
                list.add("1");
            }
            if("2".startsWith(args[3])){
                list.add("2");
            }
            if("3".startsWith(args[3])){
                list.add("3");
            }
            return list;
        }
        else if(args.length == 5){
            List<String> list = new ArrayList<>();
            if("kills".startsWith(args[4])){
                list.add("kills");
            }
            if("deaths".startsWith(args[4])){
                list.add("deaths");
            }
            if("levels".startsWith(args[4])){
                list.add("levels");
            }
            return list;
        }
        return null;
    }
}
