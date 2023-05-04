package me.sekalol15.SilkSpawnersOG;

import org.bukkit.plugin.java.JavaPlugin;

//todo list
//add option to change spawner type with head
//add option to change drop chance
//possibly add a option to make different spawner quality levels
public class SilkSpawnersOG extends JavaPlugin {

    @Override
    public void onEnable() {
        this.getServer().getPluginManager().registerEvents(new BlockEventListener(), this);
        System.out.println("Ready!");
    }

}

