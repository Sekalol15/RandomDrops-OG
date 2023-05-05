package me.sekalol15.SilkSpawnersOG;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;
import java.util.Arrays;
import java.util.Random;

public class BlockEventListener implements Listener {

    @EventHandler
    public void onBlockBreakEvent(BlockBreakEvent event) {
        if (event.getBlock().getType() != Material.SPAWNER)
            return;

        Random random = new Random();
        int randNum = random.nextInt(2)+1;

        // check for silk touch and random chance.
        if(event.getPlayer().getInventory().getItemInMainHand().containsEnchantment(Enchantment.SILK_TOUCH) && randNum == 1){

            //find spawner type
            CreatureSpawner droppedSpawnerState = (CreatureSpawner) event.getBlock().getState();

            //get spawner newSpawnerMeta and set lore to use as mob identifier
            ItemStack newSpawner = new ItemStack(Material.SPAWNER);
            BlockStateMeta newSpawnerMeta = (BlockStateMeta) newSpawner.getItemMeta();

            CreatureSpawner newSpawnerState = (CreatureSpawner) newSpawnerMeta.getBlockState();
            newSpawnerState.setSpawnedType(droppedSpawnerState.getSpawnedType());

            newSpawnerMeta.setBlockState(newSpawnerState);
            newSpawner.setItemMeta(newSpawnerMeta);

            String lore = String.valueOf(newSpawnerState.getSpawnedType());
            TextComponent loreTranslate = Component.text(lore);
            newSpawner.lore(Arrays.asList(loreTranslate));

            event.getPlayer().sendMessage(ChatColor.GRAY + "Spawner gotten");
            event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), newSpawner);
        }
    }

    @EventHandler
    public void onBlockPlaceEvent(BlockPlaceEvent event){
        if (event.getBlock().getType() != Material.SPAWNER)
            return;

        ItemStack spawnerItem = event.getItemInHand();

        if (spawnerItem.getType() == Material.SPAWNER && spawnerItem.hasItemMeta()){

            BlockStateMeta spawnerItemMeta = (BlockStateMeta) spawnerItem.getItemMeta();
            CreatureSpawner spawnerItemState = (CreatureSpawner) spawnerItemMeta.getBlockState();
            EntityType entitySpawnerType = spawnerItemState.getSpawnedType();

            Block spawnerBlock = event.getBlock();

            CreatureSpawner spawnerBlockState = (CreatureSpawner) spawnerBlock.getState();
            spawnerBlockState.setSpawnedType(entitySpawnerType);
            spawnerBlockState.update();
        }
    }
}

