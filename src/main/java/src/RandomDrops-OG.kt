package src

import org.bukkit.plugin.java.JavaPlugin


//todo list
//add option to change spawner type with head
//add option to change drop chance
//possibly add a option to make different spawner quality levels
//possibly add messages when a spawner doesn't drop and vice versa
class `RandomDrops-OG` : JavaPlugin() {
    companion object {
        lateinit var plugin: JavaPlugin
    }

    override fun onEnable() {
        plugin = this
        server.pluginManager.registerEvents(Listeners(), this)
        Config.load()


    }

    }
