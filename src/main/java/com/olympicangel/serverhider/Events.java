package com.olympicangel.serverhider;

import com.destroystokyo.paper.event.server.PaperServerListPingEvent;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.server.ServerListPingEvent;

import java.net.InetAddress;
import java.util.Iterator;

public class Events implements Listener {

    @EventHandler
    public void ListEvent(PaperServerListPingEvent e) {
        String ip = extractIp(e.getAddress());
        if(ip.isEmpty())
            return;
        boolean whitelisted = main.ref.getConfig().contains("address."+ip);
        if(!whitelisted){ //if not whitelisted
            e.motd(Component.text("A Minecraft Server")); //set default  motd
            e.setMaxPlayers(20); //set default player count
            e.setNumPlayers(0); //set current
            e.getPlayerSample().clear();

//            //remove all players for list
//            Iterator<Player> players = e.iterator();
//            try{
//                players.remove(); //removs pleayer
//            }catch(Exception ex){}
//
//            while(players.hasNext()){
//                players.next(); //move to next one
//                players.remove(); //removs pleayer
//            }
        }
        else {
            //load correct logo
            try {
                e.setServerIcon(Bukkit.loadServerIcon(main.iconPath));
            } catch (Exception ignored) {
            }
        }
    }


    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPing(ServerListPingEvent e){
        String ip = extractIp(e.getAddress());
        if(ip.equals(""))
            return;
        boolean whitelisted = main.ref.getConfig().contains("address."+ip);
        if(!whitelisted){ //if not whitelisted
            e.setMotd("A Minecraft Server"); //set default  motd
            e.setMaxPlayers(20); //set default player count

            //remove all players for list
            Iterator<Player> players = e.iterator();

            try{
                players.remove(); //removs pleayer
            }catch(Exception ex){}

            while(players.hasNext()){
                players.next(); //move to next one
                players.remove(); //removs pleayer
            }
        }
        else {
            //load correct logo
            try {
                e.setServerIcon(Bukkit.loadServerIcon(main.iconPath));
            } catch (Exception ex) {
            }
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        String ip = extractIp(e.getPlayer().getAddress().getAddress());
        if(ip.equals(""))
            return;
        main.ref.getConfig().set("address." + ip,e.getPlayer().getName());
        main.ref.saveConfig();
    }

    private String extractIp(InetAddress e){
        if(e == null)
            return "";
        return e.getHostAddress().replaceAll("\\.","/");
    }
}

