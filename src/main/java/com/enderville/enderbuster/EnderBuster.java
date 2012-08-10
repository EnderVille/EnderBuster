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