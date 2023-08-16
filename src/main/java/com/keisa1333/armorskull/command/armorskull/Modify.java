package com.keisa1333.armorskull.command.armorskull;

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

import java.util.UUID;

public class Modify implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        if (!Util.checkArguments(sender, args,4)) {
            //<0,1,2> ADD_SCALAR（スカラー値を加算）、MULTIPLY_SCALAR_1（スカラー値を乗算）、MULTIPLY_SCALAR_2（スカラー値を2倍乗算）
            player.sendMessage("§c使用法: /as modify <attribute> <number> <0,1,2>");
            return false;
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
        ItemStack item = player.getInventory().getItemInMainHand();
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
