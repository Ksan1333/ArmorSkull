package com.keisa1333.armorskull.command.armorskull;

import de.tr7zw.changeme.nbtapi.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class Modify implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        ItemStack item = player.getInventory().getItemInMainHand();

        if (args[1].equalsIgnoreCase("clear")) {
            NBTItem nbti = new NBTItem(item);
            nbti.removeKey("AttributeModifiers");
            item = nbti.getItem();
            player.getPlayer().getInventory().setItemInMainHand(item);
            player.sendMessage("§7属性を削除しました！");
            return true;
        }

        String attributeName = args[1].toLowerCase();
        if (!attributeName.equals("damage") && !attributeName.equals("maxhealth") && !attributeName.equals("speed") && !attributeName.equals("knockback") && !attributeName.equals("armor") && !attributeName.equals("toughness") && !attributeName.equals("luck") && !attributeName.equals("attack_speed") && !attributeName.equals("attack") && !attributeName.equals("range")) {
            player.sendMessage("§c第２引数の値が違います。(damage, maxhealth, speed, knockback, armor, toughness, luck, attack_speed, attack, range)");
            return false;
        }

        double amount;
        try {
            amount = Double.parseDouble(args[2]);
        } catch (Exception e) {
            player.sendMessage("§c第３引数は数値で入力してください。");
            return false;
        }

        int scalar;
        try {
            scalar = Integer.parseInt(args[3]);
        } catch (Exception e) {
            return false;
        }
        if (scalar < 0 || scalar > 2) {
            player.sendMessage("§c第４引数の値が違います。(0(加算), 1(割合), 2(乗算))");
            return false;
        }

        String slot = args[4].toLowerCase();
        if (!slot.equals("mainhand") && !slot.equals("offhand") && !slot.equals("head") && !slot.equals("chest") && !slot.equals("legs") && !slot.equals("feet")) {
            player.sendMessage("§c第５引数の値が違います。(mainhand, offhand, head, chest, legs, feet)");
            return false;
        }

        item = createCustomItem(attributeName, amount, scalar, slot, item, sender);

        player.getPlayer().getInventory().setItemInMainHand(item);
        player.sendMessage("§7属性の設定ができました。");

        return true;
    }

    private ItemStack createCustomItem(String attributeName, double amount, int scalar, String slot, ItemStack item, CommandSender sender) {

        NBTItem nbti = new NBTItem(item);
        NBTCompoundList attribute = nbti.getCompoundList("AttributeModifiers");
        NBTListCompound mod1 = attribute.addCompound();
        Random random = new Random();

        mod1.setDouble("Amount", amount);
        mod1.setInteger("Operation", scalar);
        mod1.setInteger("UUIDLeast", random.nextInt(90000) + 100000);
        mod1.setInteger("UUIDMost", random.nextInt(90000) + 100000);
        mod1.setString("Slot", slot);

        if (attributeName.equalsIgnoreCase("maxhealth")) {
            mod1.setString("AttributeName", "generic.max_health");
            mod1.setString("Name", "generic.max_health" + random.nextInt(90000) + 100000);
        } else if (attributeName.equalsIgnoreCase("speed")) {
            mod1.setString("AttributeName", "generic.movement_speed");
            mod1.setString("Name", "generic.movement_speed" + random.nextInt(90000) + 100000);
        } else if (attributeName.equalsIgnoreCase("knockback")) {
            mod1.setString("AttributeName", "generic.knockback_resistance");
            mod1.setString("Name", "generic.knockback_resistance" + random.nextInt(90000) + 100000);
        } else if (attributeName.equalsIgnoreCase("armor")) {
            mod1.setString("AttributeName", "generic.armor");
            mod1.setString("Name", "generic.armor" + random.nextInt(90000) + 100000);
        } else if (attributeName.equalsIgnoreCase("toughness")) {
            mod1.setString("AttributeName", "generic.armor_toughness");
            mod1.setString("Name", "generic.armor_toughness" + random.nextInt(90000) + 100000);
        } else if (attributeName.equalsIgnoreCase("luck")) {
            mod1.setString("AttributeName", "generic.luck");
            mod1.setString("Name", "generic.luck" + random.nextInt(90000) + 100000);
        } else if (attributeName.equalsIgnoreCase("attack_speed")) {
            mod1.setString("AttributeName", "generic.attack_speed");
            mod1.setString("Name", "generic.attack_speed" + random.nextInt(90000) + 100000);
        } else if (attributeName.equalsIgnoreCase("attack")) {
            mod1.setString("AttributeName", "generic.attack_damage");
            mod1.setString("Name", "generic.attack_damage" + random.nextInt(90000) + 100000);
        } else if (attributeName.equalsIgnoreCase("range")) {
            mod1.setString("AttributeName", "generic.follow_range");
            mod1.setString("Name", "generic.follow_range" + random.nextInt(90000) + 100000);
        } else {
            sender.sendMessage("§c存在しないエラーが発生しました。");
            return item;
        }
        return nbti.getItem();
    }
}
