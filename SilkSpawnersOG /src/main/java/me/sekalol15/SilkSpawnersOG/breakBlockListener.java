package me.sekalol15.SilkSpawnersOG;


import org.bukkit.ChatColor;
import org.bukkit.Material;

import org.bukkit.block.CreatureSpawner;
import org.bukkit.enchantments.Enchantment;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;


import java.util.ArrayList;

import static org.bukkit.Material.*;

public class breakBlockListener implements Listener {
    @EventHandler
    public void BlockBreakEvent(BlockBreakEvent event) {
        if (event.getBlock().getType() == SPAWNER) {

            int random = (int)(Math.random() * 2);

            random += 1;


            if(event.getPlayer().getInventory().getItemInMainHand().containsEnchantment(Enchantment.SILK_TOUCH) && random == 1){

                //find spawner type
                CreatureSpawner getter = (CreatureSpawner) event.getBlock().getState();

                //get spawner meta and set name
                ItemStack mobSpawner = new ItemStack(Material.SPAWNER);
                BlockStateMeta meta = (BlockStateMeta) mobSpawner.getItemMeta();
                CreatureSpawner getter2 = (CreatureSpawner) meta.getBlockState();

                getter2.setSpawnedType(getter.getSpawnedType());

                meta.setBlockState(getter2);

                mobSpawner.setItemMeta(meta);

                ArrayList<String> lore = new ArrayList();

                lore.add(String.valueOf(getter2.getSpawnedType()));
                meta.setLore(lore);
                mobSpawner.setItemMeta(meta);


                meta.setDisplayName(ChatColor.GOLD + String.valueOf(getter2.getSpawnedType()) + ChatColor.LIGHT_PURPLE + " Spawner");
                mobSpawner.setItemMeta(meta);
                event.getPlayer().sendMessage(ChatColor.GRAY + "Spawner gotten");

                event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), mobSpawner);


        }}


           

        }

    }

