package com.foulox.pluggy;

import com.foulox.pluggy.manager.NametagManager;
import com.foulox.pluggy.manager.RankManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {
    /*
    Rank System:
    - /rank command
    - save in .yml file
    - custom permissions
    - Nametags ^ chat display
     */
    private RankManager rankManager;
    private NametagManager nametagManager;

    @Override
    public void onEnable() {
        getCommand("rank").setExecutor(new RankCommand(this));

        rankManager = new RankManager(this);
        nametagManager = new NametagManager(this);

        Bukkit.getPluginManager().registerEvents(new RankListener(this),this);
    }
    public RankManager getRankManager() { return rankManager;}
    public NametagManager getNametagManager() {return nametagManager; }
}
