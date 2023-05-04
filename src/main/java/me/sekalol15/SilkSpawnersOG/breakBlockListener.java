package me.sekalol15.SilkSpawnersOG;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockStateMeta;
import java.util.Arrays;
import static org.bukkit.Material.*;

public class breakBlockListener implements Listener {
    @EventHandler
    public void BlockBreakEvent(BlockBreakEvent event) {
        //check for spawner
        if (event.getBlock().getType() == SPAWNER) {

            int random = (int)(Math.random() * 2);

            random += 1;

            //check for silk touch and random chance.
            if(event.getPlayer().getInventory().getItemInMainHand().containsEnchantment(Enchantment.SILK_TOUCH) && random == 1){

                //find spawner type
                CreatureSpawner getter = (CreatureSpawner) event.getBlock().getState();

                //get spawner meta and set lore to use as mob identifier
                ItemStack mobSpawner = new ItemStack(Material.SPAWNER);
                BlockStateMeta meta = (BlockStateMeta) mobSpawner.getItemMeta();
                CreatureSpawner getter2 = (CreatureSpawner) meta.getBlockState();

                getter2.setSpawnedType(getter.getSpawnedType());

                meta.setBlockState(getter2);

                mobSpawner.setItemMeta(meta);

                String lore = String.valueOf(getter2.getSpawnedType());
                //ArrayList<String> lore = new ArrayList();

                TextComponent loreTranslate = Component.text(lore);

                mobSpawner.lore(Arrays.asList(loreTranslate));


                event.getPlayer().sendMessage(ChatColor.GRAY + "Spawner gotten");



                event.getBlock().getWorld().dropItemNaturally(event.getBlock().getLocation(), mobSpawner);


        }}


           

        }

    }

