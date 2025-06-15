package com.m3z0id.gunGame.commands;

import com.m3z0id.gunGame.GunGame;
import com.m3z0id.gunGame.database.GunGamePlayer;
import io.papermc.paper.command.brigadier.BasicCommand;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class MainCommand implements BasicCommand {
    @Override
    public void execute(CommandSourceStack commandSourceStack, String[] args) {
        if(args.length < 1){
            commandSourceStack.getSender().sendMessage(LegacyComponentSerializer.legacyAmpersand().deserialize(GunGame.lang.getServerPrefix() + GunGame.lang.getInvalidSubcommand()).decoration(TextDecoration.ITALIC, false));
            return;
        }
        if(args[0].equalsIgnoreCase("stats")) {
            if(args.length != 5){
                commandSourceStack.getSender().sendMessage(LegacyComponentSerializer.legacyAmpersand().deserialize(GunGame.lang.getServerPrefix() + GunGame.lang.getInvalidSubcommand()).decoration(TextDecoration.ITALIC, false));
                return;
            }
            Player player = (Player) Bukkit.getOfflinePlayer(args[1]);
            String method = args[2];

            int amount;
            try {
                amount = Integer.parseInt(args[3]);
            } catch (NumberFormatException e) {
                commandSourceStack.getSender().sendMessage(LegacyComponentSerializer.legacyAmpersand().deserialize(GunGame.lang.getServerPrefix() + GunGame.lang.getInvalidSubcommand()).decoration(TextDecoration.ITALIC, false));
                return;
            }
            amount = Math.abs(amount);
            String field = args[4];
            if(method.equalsIgnoreCase("add")){
                if(field.equalsIgnoreCase("kills")){
                    GunGamePlayer.fromPlayer(player).addKills(amount);
                    commandSourceStack.getSender().sendMessage(LegacyComponentSerializer.legacyAmpersand().deserialize(GunGame.lang.getServerPrefix() + GunGame.lang.getSuccessMessage()).decoration(TextDecoration.ITALIC, false));
                    return;
                }
                else if(field.equalsIgnoreCase("deaths")){
                    GunGamePlayer.fromPlayer(player).addDeaths(amount);
                    commandSourceStack.getSender().sendMessage(LegacyComponentSerializer.legacyAmpersand().deserialize(GunGame.lang.getServerPrefix() + GunGame.lang.getSuccessMessage()).decoration(TextDecoration.ITALIC, false));
                    return;
                }
                else if(field.equalsIgnoreCase("levels")){
                    GunGamePlayer.fromPlayer(player).addLevels(amount);
                    commandSourceStack.getSender().sendMessage(LegacyComponentSerializer.legacyAmpersand().deserialize(GunGame.lang.getServerPrefix() + GunGame.lang.getSuccessMessage()).decoration(TextDecoration.ITALIC, false));
                    return;
                }
                else {
                    commandSourceStack.getSender().sendMessage(LegacyComponentSerializer.legacyAmpersand().deserialize(GunGame.lang.getServerPrefix() + GunGame.lang.getInvalidSubcommand()).decoration(TextDecoration.ITALIC, false));
                    return;
                }
            }
            if(method.equalsIgnoreCase("set")){
                if(field.equalsIgnoreCase("kills")){
                    GunGamePlayer.fromPlayer(player).setKills(amount);
                    commandSourceStack.getSender().sendMessage(LegacyComponentSerializer.legacyAmpersand().deserialize(GunGame.lang.getServerPrefix() + GunGame.lang.getSuccessMessage()).decoration(TextDecoration.ITALIC, false));
                }
                else if(field.equalsIgnoreCase("deaths")){
                    GunGamePlayer.fromPlayer(player).setDeaths(amount);
                    commandSourceStack.getSender().sendMessage(LegacyComponentSerializer.legacyAmpersand().deserialize(GunGame.lang.getServerPrefix() + GunGame.lang.getSuccessMessage()).decoration(TextDecoration.ITALIC, false));
                }
                else if(field.equalsIgnoreCase("levels")){
                    if(GunGame.levels == null) GunGame.levels = new HashMap<>();
                    GunGamePlayer.fromPlayer(player).setLevels(Math.max(amount, 1));
                    commandSourceStack.getSender().sendMessage(LegacyComponentSerializer.legacyAmpersand().deserialize(GunGame.lang.getServerPrefix() + GunGame.lang.getSuccessMessage()).decoration(TextDecoration.ITALIC, false));
                }
                else {
                    commandSourceStack.getSender().sendMessage(LegacyComponentSerializer.legacyAmpersand().deserialize(GunGame.lang.getServerPrefix() + GunGame.lang.getInvalidSubcommand()).decoration(TextDecoration.ITALIC, false));
                }
            }
        }
        else if(args[0].equalsIgnoreCase("reset")){
            GunGame.database.reset();
            GunGame.levels.clear();

            for(Player p : Bukkit.getOnlinePlayers()){
                GunGamePlayer.fromPlayer(p).setLevels(1);
            }

            commandSourceStack.getSender().sendMessage(LegacyComponentSerializer.legacyAmpersand().deserialize(GunGame.lang.getServerPrefix() + GunGame.lang.getSuccessMessage()).decoration(TextDecoration.ITALIC, false));
        } else if(args[0].equalsIgnoreCase("reload")){
            GunGame.instance.load();
            commandSourceStack.getSender().sendMessage(LegacyComponentSerializer.legacyAmpersand().deserialize(GunGame.lang.getServerPrefix() +  GunGame.lang.getSuccessMessage()).decoration(TextDecoration.ITALIC, false));
        }
        else {
            commandSourceStack.getSender().sendMessage(LegacyComponentSerializer.legacyAmpersand().deserialize(GunGame.lang.getServerPrefix() + GunGame.lang.getInvalidSubcommand()).decoration(TextDecoration.ITALIC, false));
        }
    }

    @Override
    public Collection<String> suggest(CommandSourceStack commandSourceStack, String[] args) {
        if(args.length == 0) {
            return List.of("reset", "stats", "reload");
        } else if(args.length == 1) {

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
            return Bukkit.getOnlinePlayers().stream().map(Player::getName).toList();
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
        return Bukkit.getOnlinePlayers().stream().map(Player::getName).toList();
    }

    @Override
    public boolean canUse(CommandSender sender) {
        return sender.hasPermission(permission()) || sender.isOp() || sender instanceof ConsoleCommandSender;
    }

    @Override
    public String permission() {
        return "gungame.admin";
    }
}
