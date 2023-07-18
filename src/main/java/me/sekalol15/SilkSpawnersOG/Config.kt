package me.sekalol15.SilkSpawnersOG

import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.configuration.file.YamlConfiguration
import java.io.File

    object Config {
        private lateinit var config: FileConfiguration
        private lateinit var file: File

        fun load() {
            file = File(SilkSpawnersOG.plugin.dataFolder, "config.yml")
            if (!file.exists()) {
                SilkSpawnersOG.plugin.saveDefaultConfig()
            }

            config = YamlConfiguration.loadConfiguration(file)

            this.save()
        }

        private fun save() {
            config.save(file)
        }


        fun getSpawnerDropChance(): Double {
            return config.get("spawnerDropChance") as Double
        }
    }