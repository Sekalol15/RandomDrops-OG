package me.sekalol15.SilkSpawnersOG.out.production.SilkSpawnersOG.me.sekalol15.SilkSpawnersOG;


import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
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


import java.util.List;

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
                List<Component> spawnerLore = meta.lore();

                String mobTypeString = spawnerLore.toString();

                EntityType entityType = EntityType.valueOf(mobTypeString);

                Block spawnerBlock = event.getBlock();



                CreatureSpawner spawner = (CreatureSpawner) spawnerBlock.getState();
                //spawner.setSpawnedType(entityType);
                spawner.update();
            }
        }}


}
