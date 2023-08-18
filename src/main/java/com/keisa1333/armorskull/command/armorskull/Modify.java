package com.keisa1333.armorskull.command.armorskull;

import com.google.common.collect.Multimap;
import com.keisa1333.armorskull.Util;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Map;
import java.util.UUID;

public class Modify implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        ItemStack item = player.getInventory().getItemInMainHand();
        ItemMeta itemMeta = item.getItemMeta();

        if (args[1].equalsIgnoreCase("clear")) {
            itemMeta.removeAttributeModifier(EquipmentSlot.HAND);
            item.setItemMeta(itemMeta);
            player.sendMessage("§7属性を削除しました！");
            return true;
        }

        int scalar;
        try {
            scalar = Integer.parseInt(args[3]);
        } catch (Exception e) {
            return false;
        }
        if (scalar < 0 || scalar > 2) {
            player.sendMessage("§c第４引数の値が違います。(0, 1, 2)");
            return false;
        }

        String attributeName = args[1].toLowerCase();
        if (!attributeName.equals("damage") && !attributeName.equals("maxhealth") && !attributeName.equals("speed") && !attributeName.equals("knockback") && !attributeName.equals("armor") && !attributeName.equals("toughness")) {
            player.sendMessage("§c第２引数の値が違います。(damage, maxhealth, speed, knockback, armor, toughness)");
            return false;
        }

        double amount;
        try {
            amount = Double.parseDouble(args[2]);
        } catch (Exception e) {
            player.sendMessage("§c第３引数は数値で入力してください。");
            return false;
        }
        item = createCustomItem(attributeName, amount, item, sender);

        player.getPlayer().getInventory().setItemInMainHand(item);
        player.sendMessage("§7属性の設定ができました。");

        return true;
    }

    private ItemStack createCustomItem(String attributeName, double amount, ItemStack item, CommandSender sender) {
        ItemMeta itemMeta = item.getItemMeta();

        AttributeModifier attributeModifier = new AttributeModifier(UUID.randomUUID(), attributeName, amount, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HEAD);

        if (attributeName.equalsIgnoreCase("maxhealth")) {
            itemMeta.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, attributeModifier);
        } else if (attributeName.equalsIgnoreCase("speed")) {
            itemMeta.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED, attributeModifier);
        } else if (attributeName.equalsIgnoreCase("knockback")) {
            itemMeta.addAttributeModifier(Attribute.GENERIC_KNOCKBACK_RESISTANCE, attributeModifier);
        } else if (attributeName.equalsIgnoreCase("armor")) {
            itemMeta.addAttributeModifier(Attribute.GENERIC_ARMOR, attributeModifier);
        } else if (attributeName.equalsIgnoreCase("toughness")) {
            itemMeta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, attributeModifier);
        } else {
            sender.sendMessage("§c不明なエラーが発生しました。");
            return item;
        }

        item.setItemMeta(itemMeta);

        return item;
    }
}
