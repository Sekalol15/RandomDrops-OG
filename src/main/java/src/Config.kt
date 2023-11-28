package src

import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.configuration.file.YamlConfiguration
import java.io.File

    object Config {
        private lateinit var config: FileConfiguration
        private lateinit var file: File

        fun load() {
            file = File(`RandomDrops-OG`.plugin.dataFolder, "config.yml")
            if (!file.exists()) {
                `RandomDrops-OG`.plugin.saveDefaultConfig()
            }

            config = YamlConfiguration.loadConfiguration(file)

            save()
        }

        private fun save() {
            config.save(file)
        }

        fun getSpawnerDropChance(): Double {
            return config.get("spawnerDropChance") as Double
        }
        fun getDragonEggDropChance(): Double{
            return config.get("dragonEggDropChance") as Double
        }
    }