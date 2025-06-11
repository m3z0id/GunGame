package com.m3z0id.gunGame.commands;

import com.m3z0id.gunGame.GunGame;
import com.m3z0id.gunGame.database.GunGamePlayer;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.Bukkit;
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
            commandSender.sendMessage(LegacyComponentSerializer.legacySection().deserialize(GunGame.lang.getServerPrefix() + GunGame.lang.getInvalidSubcommand()));
            return true;
        }
        if(args[0].equalsIgnoreCase("stats")){
            if(args.length != 5){
                commandSender.sendMessage(LegacyComponentSerializer.legacySection().deserialize(GunGame.lang.getServerPrefix() + GunGame.lang.getInvalidSubcommand()));
                return true;
            }
            Player player = (Player) Bukkit.getOfflinePlayer(args[1]);
            String method = args[2];
            int amount;
            try {
                amount = Integer.parseInt(args[3]);
            } catch (NumberFormatException e) {
                commandSender.sendMessage(LegacyComponentSerializer.legacySection().deserialize(GunGame.lang.getServerPrefix() + GunGame.lang.getInvalidSubcommand()));
                return true;
            }
            amount = Math.abs(amount);
            String field = args[4];

            if(method.equalsIgnoreCase("add")){
                if(field.equalsIgnoreCase("kills")){
                    GunGamePlayer.fromPlayer(player).addKills(amount);
                    commandSender.sendMessage(LegacyComponentSerializer.legacySection().deserialize(GunGame.lang.getServerPrefix() + GunGame.lang.getSuccessMessage()));
                    return true;
                }
                else if(field.equalsIgnoreCase("deaths")){
                    GunGamePlayer.fromPlayer(player).addDeaths(amount);
                    commandSender.sendMessage(LegacyComponentSerializer.legacySection().deserialize(GunGame.lang.getServerPrefix() + GunGame.lang.getSuccessMessage()));
                    return true;
                }
                else if(field.equalsIgnoreCase("levels")){
                    GunGamePlayer.fromPlayer(player).addLevels(amount);
                    commandSender.sendMessage(LegacyComponentSerializer.legacySection().deserialize(GunGame.lang.getServerPrefix() + GunGame.lang.getSuccessMessage()));
                    return true;
                }
                else {
                    commandSender.sendMessage(LegacyComponentSerializer.legacySection().deserialize(GunGame.lang.getServerPrefix() + GunGame.lang.getInvalidSubcommand()));
                    return true;
                }
            }
            if(method.equalsIgnoreCase("set")){
                if(field.equalsIgnoreCase("kills")){
                    GunGamePlayer.fromPlayer(player).setKills(amount);
                    commandSender.sendMessage(LegacyComponentSerializer.legacySection().deserialize(GunGame.lang.getServerPrefix() + GunGame.lang.getSuccessMessage()));
                    return true;
                }
                else if(field.equalsIgnoreCase("deaths")){
                    GunGamePlayer.fromPlayer(player).setDeaths(amount);
                    commandSender.sendMessage(LegacyComponentSerializer.legacySection().deserialize(GunGame.lang.getServerPrefix() + GunGame.lang.getSuccessMessage()));
                    return true;
                }
                else if(field.equalsIgnoreCase("levels")){
                    if(GunGame.levels == null) GunGame.levels = new HashMap<>();
                    GunGamePlayer.fromPlayer(player).setLevels(Math.max(amount, 1));
                    commandSender.sendMessage(LegacyComponentSerializer.legacySection().deserialize(GunGame.lang.getServerPrefix() + GunGame.lang.getSuccessMessage()));
                    return true;
                }
                else {
                    commandSender.sendMessage(LegacyComponentSerializer.legacySection().deserialize(GunGame.lang.getServerPrefix() + GunGame.lang.getInvalidSubcommand()));
                    return true;
                }
            }
        }
        else if(args[0].equalsIgnoreCase("reset")){
            GunGame.database.reset();
            GunGame.levels.clear();

            for(Player p : Bukkit.getOnlinePlayers()){
                GunGamePlayer.fromPlayer(p).setLevels(1);
            }

            commandSender.sendMessage(LegacyComponentSerializer.legacySection().deserialize(GunGame.lang.getServerPrefix() + GunGame.lang.getSuccessMessage()));
            return true;
        } else if(args[0].equalsIgnoreCase("reload")){
            GunGame.instance.load();
            commandSender.sendMessage(LegacyComponentSerializer.legacySection().deserialize(GunGame.lang.getServerPrefix() +  GunGame.lang.getSuccessMessage()));
            return true;
        }
        else {
            commandSender.sendMessage(LegacyComponentSerializer.legacySection().deserialize(GunGame.lang.getServerPrefix() + GunGame.lang.getInvalidSubcommand()));
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
            if("reload".startsWith(args[0])){
                list.add("reload");
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
            list.add(args[3]);
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
