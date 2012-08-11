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

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class EnderBuster extends JavaPlugin {
    PluginManager pm;
    PluginDescriptionFile pdfFile;

    public void onEnable() {
        pdfFile = getDescription();
        pm = getServer().getPluginManager();

        checkConfig();

        pm.registerEvents(new EBPlayerListener(this), this);

        System.out.println("[" + pdfFile.getName() + "]" + " version " + pdfFile.getVersion() + " is enabled!");
    }
  
    public void onDisable() {
        System.out.println("[" + pdfFile.getName() + "]" + " version " + pdfFile.getVersion() + " is disabled!");
    }

    void checkConfig() {
        checkOption("items", "332, 341, 381");
        checkOption("worlds", "world, world_nether, world_the_end");
        checkOption("block-itemdrops", "true");
    }

    void checkOption(String option, Object defValue) {
        if (!getConfig().isSet(option)) {
            getConfig().set(option, defValue);
            saveConfig();
        }
    }
}