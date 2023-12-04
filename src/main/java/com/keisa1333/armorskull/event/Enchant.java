package com.keisa1333.armorskull.event;

import com.keisa1333.armorskull.Util;
import de.tr7zw.changeme.nbtapi.NBTItem;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Map;
import java.util.Objects;

public class Enchant implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        if (!event.getAction().toString().contains("RIGHT_CLICK")) return;
        if (event.getClickedBlock() == null || !event.getClickedBlock().getType().toString().equals("ENCHANTING_TABLE"))
            return;

        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();

        NBTItem nbti;
        try {
            nbti = new NBTItem(item);
        } catch (Exception e) {
            return;
        }

        boolean isSetting = Util.getIsSetting(nbti);

        if (!isSetting) return;
        if (item == null || item.getType() != Material.PLAYER_HEAD) return;

        PlayerInventory inventory = event.getPlayer().getInventory();
        int expBottleCount = countExpBottles(inventory);

        if (expBottleCount < 3) return;

        Map<Enchantment, Integer> itemEnchantments = item.getEnchantments();

        if (itemEnchantments.isEmpty()) return;

        ItemMeta itemMeta = item.getItemMeta();
        Enchantment[] enchantments = Enchantment.values();
        int numberOfEnchants = (int) (Math.random() * enchantments.length) + 1;

        for (int i = 0; i < numberOfEnchants; i++) {
            Enchantment enchantment = enchantments[(int) (Math.random() * enchantments.length)];
            int enchantLevel = (int) (Math.random() * enchantment.getMaxLevel()) + 1;

            Objects.requireNonNull(itemMeta).addEnchant(enchantment, enchantLevel, true);
        }

        item.setItemMeta(itemMeta);

    }

    private int countExpBottles(PlayerInventory inventory) {
        int count = 0;
        for (ItemStack item : inventory.getContents()) {
            if (isExpBottle(item)) {
                count += item.getAmount();
            }
        }
        return count;
    }

    private boolean isExpBottle(ItemStack item) {
        return item != null && item.getType() == Material.EXPERIENCE_BOTTLE;
    }
}
