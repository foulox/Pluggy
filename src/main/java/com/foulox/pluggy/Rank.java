package com.foulox.pluggy;

import org.bukkit.ChatColor;

public enum Rank {
    OWNER(ChatColor.DARK_AQUA + "Owner", new String[]{}),
    ADMIN(ChatColor.RED + "Admin", new String[]{}),
    MEMBER(ChatColor.BLUE + "Member", new String[]{}),
    GUEST(ChatColor.GOLD + "Guest", new String[]{});

    private String display;
    private String[] permissions;

    Rank(String display, String[] permissions) {
        this.display = display;
        this.permissions = permissions;
    }
    public String getDisplay() {return display;}

    public String[] getPermissions() {return permissions;
    }
}
