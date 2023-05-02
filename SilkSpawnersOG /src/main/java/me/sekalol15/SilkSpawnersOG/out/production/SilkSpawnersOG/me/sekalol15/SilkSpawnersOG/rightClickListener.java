package me.sekalol15.SilkSpawnersOG.out.production.SilkSpawnersOG.me.sekalol15.SilkSpawnersOG;


import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import static org.bukkit.Material.SPAWNER;


public class rightClickListener implements Listener {
    @EventHandler
    //set
    public void BlockPlaceEvent(BlockPlaceEvent event){
        if (event.getBlock().getType() == SPAWNER) {

            //gets spawner name, subtracts 8 from the string, and sets remaining as spawner type
            ItemStack spawnerItem = event.getItemInHand();
            if (spawnerItem.getType() == Material.SPAWNER && spawnerItem.hasItemMeta())
            {
                ItemMeta meta = spawnerItem.getItemMeta();

                String name = ChatColor.stripColor(meta.getDisplayName());
                int entityTypeLength = name.length() - 8;

                String entityTypeString = name.substring(0, entityTypeLength);
                EntityType entityType = EntityType.valueOf(entityTypeString);

                Block spawnerBlock = event.getBlock();

                CreatureSpawner spawner = (CreatureSpawner) spawnerBlock.getState();
                spawner.setSpawnedType(entityType);
                spawner.update();
            }
        }}}
