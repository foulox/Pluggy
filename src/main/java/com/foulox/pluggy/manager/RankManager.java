package com.foulox.pluggy.manager;

import com.foulox.pluggy.Main;
import com.foulox.pluggy.Rank;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

public class RankManager {
    private Main main;
    private File file;
    private YamlConfiguration config;

    private HashMap<UUID, PermissionAttachment> perms = new HashMap<>();

    public RankManager (Main main) {
        this.main = main;
        if (!main.getDataFolder().exists()) {
            main.getDataFolder().mkdir();
        }
        file = new File(main.getDataFolder(), "ranks.yml");
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        config = YamlConfiguration.loadConfiguration(file);
    }
    public void setRank(UUID uuid, Rank rank, boolean firstJoin) {
        System.out.println("setRank");
        if(Bukkit.getOfflinePlayer(uuid).isOnline() && !firstJoin) {
            System.out.println("getofflineplayer");
            Player player = Bukkit.getPlayer(uuid);
            PermissionAttachment attachment;
            if(perms.containsKey(uuid)) {
                System.out.println("if perms");
                attachment = perms.get(uuid);
            } else {
                System.out.println("else perms");
                attachment = player.addAttachment(main);
                perms.put(uuid, attachment);
            }
            for (String perm : getRank(uuid).getPermissions()) {
                if(player.hasPermission(perm)) {
                    attachment.unsetPermission(perm);
                }
            }

            for (String perm : rank.getPermissions()) {
                attachment.setPermission(perm,true);
            }
        }

        config.set(uuid.toString(), rank.name());
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(Bukkit.getOfflinePlayer(uuid).isOnline()) {
            Player player = Bukkit.getPlayer(uuid);
            main.getNametagManager().removeTag(player);
            main.getNametagManager().newTag(player);
        }

    }

    public Rank getRank(UUID uuid) {
        return Rank.valueOf(config.getString(uuid.toString()));
    }
    public HashMap<UUID, PermissionAttachment> getPerms() {return perms; }
}
