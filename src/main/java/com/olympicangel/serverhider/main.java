package com.olympicangel.serverhider;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.CachedServerIcon;

import java.io.File;

public final class main extends JavaPlugin {

    public static main ref;
    public static File iconPath;

    @Override
    public void onEnable() {
        main.ref = this;
        Bukkit.getPluginManager().registerEvents(new Events(),this);

        iconPath = new File("./server-icon.png");
        //if default icon exists rename it to correct way
        if(iconPath.exists())
            iconPath.renameTo(this.iconPath);

    }

    @Override
    public void onDisable() {
        main.ref = null;
    }
}
