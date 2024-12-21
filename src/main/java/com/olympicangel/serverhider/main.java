package com.olympicangel.serverhider;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class main extends JavaPlugin {

    public static main ref;
    public static File iconPath;

    @Override
    public void onEnable() {
        main.ref = this;
        //def new icon location
        iconPath = new File("./server-icon2.png");
        //register events
        Bukkit.getPluginManager().registerEvents(new Events(),this);

        //if default icon exists rename it to correct way
        File defaultIconPath = new File("./server-icon.png");
        if(defaultIconPath.exists()) {
            Logger("Changing default icon(server-icon.png) => 'server-icon2.png'..");
            boolean fileNameChanged = defaultIconPath.renameTo(iconPath);
            if(fileNameChanged)
                Logger("Icon renaming succeeded! (please do not change manually)");
            else
                Logger("Icon renaming FAILED! please rename manually..");
        }
        else if(iconPath.exists())
            Logger("Found hidden server icon (server-icon2.png)");

        Logger("Server is well hidden from scanners / unknown players :)");
    }

    @Override
    public void onDisable() {
        iconPath = null;
        main.ref = null;
    }

    public static void Logger(String msg){
        Bukkit.getLogger().info("[ServerHider]: " + msg);
    }
}
