package com.foulox.pluggy;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RankCommand  implements CommandExecutor {
    private Main main;

    public RankCommand (Main main) {
        this.main=main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if(player.isOp()) {
                System.out.println("eggz");
                if (args.length == 2) {
                    if(Bukkit.getOfflinePlayer(args[0])!= null) {
                        OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);

                        for(Rank rank : Rank.values()) {
                            if(rank.name().equalsIgnoreCase(args[1])) {
                                System.out.println("Typed " + args[0] + args[1]);
                                main.getRankManager().setRank(target.getUniqueId(),rank, false);
                                player.sendMessage(ChatColor.GOLD + "You changed " + target.getName() + "'s rank to " + rank.getDisplay() + ChatColor.GOLD + ".");
                                if(target.isOnline()) {
                                    target.getPlayer().sendMessage(ChatColor.DARK_PURPLE + player.getName() + " set your rank to " + rank.getDisplay() + ".");
                                }
                                return false;
                            }
                        }
                        player.sendMessage(ChatColor.RED + "You did not specify a valid rank! Options are Guest, Member, Admin, Owner");
                    } else {
                        player.sendMessage(ChatColor.RED + "This user has never joined the server before!");
                    }
                } else {
                    player.sendMessage(ChatColor.RED + "Invalid usage! Please usage! Please use /rank <player> <rank>.");
                }

            } else {
                player.sendMessage(ChatColor.RED + "You must be OP to use this command!");
            }
        }
        return false;
    }
}
