package com.keisa1333.armorskull.event;

import com.keisa1333.armorskull.Util;
import com.keisa1333.armorskull.command.armorskull.Durability;
import de.tr7zw.changeme.nbtapi.NBTItem;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerExpChangeEvent;
import org.bukkit.event.player.PlayerItemMendEvent;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutionException;

public class Mend implements Listener {
    private boolean isMendEventExecuted = false;

    @EventHandler
    public void onExpChange(PlayerExpChangeEvent event) {
        if (!isMendEventExecuted) {
            Player player = event.getPlayer();
            String randomElement = this.checkMending(player);
            if (randomElement == null) return;

            if (randomElement.equalsIgnoreCase("HEAD")) {
                int repairAmount = event.getAmount() * 2;
                event.setAmount(0);

                ItemStack helmet = player.getInventory().getHelmet();
                int[] durability = Durability.getDurability(helmet);
                int toDurability = durability[1] + repairAmount;
                if (toDurability > durability[0]) {
                    toDurability = durability[0];
                }
                Durability.setDurability(durability[0], toDurability, helmet, player, "head");
            }
        }
    }

    @EventHandler
    public void onMend(PlayerItemMendEvent event) {
        isMendEventExecuted = true;
        Player player = event.getPlayer();
        String randomElement = this.checkMending(player);

        if (randomElement != null && randomElement.equalsIgnoreCase("HEAD")) {
            int repairAmount = event.getRepairAmount();
            event.setRepairAmount(0);

            ItemStack helmet = player.getInventory().getHelmet();
            int[] durability = Durability.getDurability(helmet);
            int toDurability = durability[1] + repairAmount;
            if (toDurability > durability[0]) {
                toDurability = durability[0];
            }
            Durability.setDurability(durability[0], toDurability, helmet, player, "head");
        }
        isMendEventExecuted = false;
    }

    public String checkMending(Player player) {
        ItemStack helmet = player.getInventory().getHelmet();
        NBTItem nbti = null;
        try {
            nbti = new NBTItem(helmet);
        } catch (Exception e) {
            return null;
        }

        if (Util.getIsSetting(nbti)) {

            Map<Enchantment, Integer> enchsHelmet = helmet.getEnchantments();
            if (enchsHelmet.containsKey(Enchantment.MENDING)) {
                List<String> mendingSlot = new ArrayList<>();
                mendingSlot.add("HEAD");

                ItemStack chestplate = player.getInventory().getChestplate();
                try {
                    Map<Enchantment, Integer> enchsChest = chestplate.getEnchantments();
                    if (enchsChest.containsKey(Enchantment.MENDING)) {
                        Material chestplateMaterial = chestplate.getType();
                        if (chestplate.getDurability() == chestplateMaterial.getMaxDurability()) ;
                        {
                            mendingSlot.add("CHEST");
                        }
                    }
                } catch (Exception e) {
                }

                ItemStack leggings = player.getInventory().getLeggings();
                try {
                    Map<Enchantment, Integer> enchsLegs = leggings.getEnchantments();
                    if (enchsLegs.containsKey(Enchantment.MENDING)) {
                        Material leggingsMaterial = leggings.getType();
                        if (leggings.getDurability() == leggingsMaterial.getMaxDurability()) ;
                        {
                            mendingSlot.add("LEGS");
                        }
                    }
                } catch (Exception e) {
                }

                ItemStack boots = player.getInventory().getBoots();
                try {
                    Map<Enchantment, Integer> enchsBoots = boots.getEnchantments();
                    if (enchsBoots.containsKey(Enchantment.MENDING)) {
                        Material bootsMaterial = boots.getType();
                        if (boots.getDurability() == bootsMaterial.getMaxDurability()) ;
                        {
                            mendingSlot.add("FEET");
                        }
                    }
                } catch (Exception e) {
                }

                ItemStack mainhand = player.getInventory().getItemInMainHand();
                try {
                    Map<Enchantment, Integer> enchsMain = mainhand.getEnchantments();
                    if (enchsMain.containsKey(Enchantment.MENDING)) {
                        Material mainhandMaterial = mainhand.getType();
                        if (mainhand.getDurability() == mainhandMaterial.getMaxDurability()) ;
                        {
                            mendingSlot.add("HAND");
                        }
                    }
                } catch (Exception e) {
                }

                ItemStack offhand = player.getInventory().getItemInOffHand();
                try {
                    Map<Enchantment, Integer> enchsOff = offhand.getEnchantments();
                    if (enchsOff.containsKey(Enchantment.MENDING)) {
                        Material offhandMaterial = offhand.getType();
                        if (offhand.getDurability() == offhandMaterial.getMaxDurability()) ;
                        {
                            mendingSlot.add("OFF_HAND");
                        }
                    }
                } catch (Exception e) {
                }


                Random random = new Random();
                String randomElement = mendingSlot.get(random.nextInt(mendingSlot.size()));
                return randomElement;
            }
        }
        return null;
    }
}
