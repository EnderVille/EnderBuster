/**
 * EnderBuster - Bukkit Plugin for anti item drops and throws.
 * Copyright (C) 2012, EnderVille.com
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

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