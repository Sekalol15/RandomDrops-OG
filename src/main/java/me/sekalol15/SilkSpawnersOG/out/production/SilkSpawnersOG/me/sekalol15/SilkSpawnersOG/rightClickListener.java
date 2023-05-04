package me.sekalol15.SilkSpawnersOG.out.production.SilkSpawnersOG.me.sekalol15.SilkSpawnersOG;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;




public class rightClickListener implements Listener {
    @EventHandler
    //set
    public void BlockPlaceEvent(BlockPlaceEvent event){
        if (event.getBlock().getType() == Material.SPAWNER) {
            //gets spawner meta, grabs mob type, and sets it as type
            ItemStack spawnerItem = event.getItemInHand();

            if (spawnerItem.getType() == Material.SPAWNER && spawnerItem.hasItemMeta()){
                BlockStateMeta meta = (BlockStateMeta) spawnerItem.getItemMeta();

                CreatureSpawner getter = (CreatureSpawner) meta.getBlockState();
                getter.getSpawnedType();

                meta.setBlockState(getter);
                spawnerItem.setItemMeta(meta);
                EntityType entitySpawnerType = getter.getSpawnedType();



                Block spawnerBlock = event.getBlock();



                CreatureSpawner spawner = (CreatureSpawner) spawnerBlock.getState();
                spawner.setSpawnedType(entitySpawnerType);
                spawner.update();
            }
        }}


}
