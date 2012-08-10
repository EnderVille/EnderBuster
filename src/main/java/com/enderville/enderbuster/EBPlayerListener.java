package com.enderville.enderbuster;

import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.ItemSpawnEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class EBPlayerListener implements Listener {
    EnderBuster plugin;

    public EBPlayerListener(EnderBuster plugin) {
        this.plugin = plugin;
    }

    @EventHandler public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Action action = event.getAction();
        World world = (event.getPlayer().getWorld());

        for (String worlds : plugin.getConfig().getString("worlds").split(", ")) {
            if ((action.equals(Action.RIGHT_CLICK_AIR) || action.equals(Action.RIGHT_CLICK_BLOCK)) && world.getName().equals(worlds)) {
                for (String item : plugin.getConfig().getString("items").split(", ")) {
                    if (player.getItemInHand().getTypeId() == Integer.valueOf(item)) {
                        event.setCancelled(true);
                    }
                }   
            }
        }
    }
    
    @EventHandler public void onItemSpawnEvent(ItemSpawnEvent event) {
        World world = (event.getEntity().getWorld());
        
        for (String worlds : plugin.getConfig().getString("worlds").split(",")) {
            if ((world.getName().equals(worlds) && plugin.getConfig().getString("block-itemdrops").equals("true"))){
                event.getEntity().setFireTicks(20);
            }
        }
           
        
    
    
    }
}