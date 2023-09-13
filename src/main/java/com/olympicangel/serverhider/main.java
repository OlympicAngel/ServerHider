package com.olympicangel.serverhider;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class main extends JavaPlugin {

    public static main ref;

    @Override
    public void onEnable() {
        main.ref = this;

        Bukkit.getPluginManager().registerEvents(new Events(),this);
    }

    @Override
    public void onDisable() {
        main.ref = null;
    }
}
