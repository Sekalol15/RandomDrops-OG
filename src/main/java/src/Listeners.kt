package src

import io.papermc.paper.event.block.DragonEggFormEvent
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.TextComponent
import org.bukkit.Material
import org.bukkit.block.CreatureSpawner
import org.bukkit.enchantments.Enchantment
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.block.BlockBreakEvent
import org.bukkit.event.block.BlockPlaceEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.BlockStateMeta
import java.util.*
import kotlin.random.Random
class Listeners : Listener {
    //dragon egg drop chance
    @EventHandler
    fun onDragonEggDrop(event: DragonEggFormEvent) {
        val dragonEggDropChance = Config.getDragonEggDropChance()
        // 10% chance
        if (Random.nextDouble() <= dragonEggDropChance) {
            event.isCancelled = false
        }
        else {
            event.isCancelled = true
        }
    }
    @EventHandler
    fun onBlockBreakEvent(event: BlockBreakEvent) {
        if (event.block.type != Material.SPAWNER) return
        val spawnerDropChance = Config.getSpawnerDropChance()

        // check for silk touch and random chance.
        if (event.player.inventory.itemInMainHand.containsEnchantment(Enchantment.SILK_TOUCH) && Random.nextDouble() <= spawnerDropChance) {

            //find spawner type
            val droppedSpawnerState = event.block.state as CreatureSpawner

            //get spawner newSpawnerMeta and set lore to use as mob identifier
            val newSpawner = ItemStack(Material.SPAWNER)
            val newSpawnerMeta = newSpawner.itemMeta as BlockStateMeta?
            val newSpawnerState = newSpawnerMeta!!.blockState as CreatureSpawner
            newSpawnerState.spawnedType = droppedSpawnerState.spawnedType
            newSpawnerMeta.blockState = newSpawnerState
            newSpawner.setItemMeta(newSpawnerMeta)
            val lore = newSpawnerState.spawnedType.toString()
            val loreTranslate: TextComponent = Component.text(lore)
            newSpawner.lore(Arrays.asList(loreTranslate) as List<Component>?)
            //message for debugging
            //event.getPlayer().sendMessage(ChatColor.GRAY + "Spawner gotten");
            event.block.world.dropItemNaturally(event.block.location, newSpawner)
        }
    }

    @EventHandler
    fun onBlockPlaceEvent(event: BlockPlaceEvent) {
        if (event.block.type != Material.SPAWNER) return
        val spawnerItem = event.itemInHand
        if (spawnerItem.type == Material.SPAWNER && spawnerItem.hasItemMeta()) {
            val spawnerItemMeta = spawnerItem.itemMeta as BlockStateMeta?
            val spawnerItemState = spawnerItemMeta!!.blockState as CreatureSpawner
            val entitySpawnerType = spawnerItemState.spawnedType
            val spawnerBlock = event.block
            val spawnerBlockState = spawnerBlock.state as CreatureSpawner
            spawnerBlockState.spawnedType = entitySpawnerType
            spawnerBlockState.update()
        }
    }
}
