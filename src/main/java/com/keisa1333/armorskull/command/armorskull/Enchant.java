package com.keisa1333.armorskull.command.armorskull;

import com.keisa1333.armorskull.Util;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Enchant implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String a, String[] args) {
        Player player = (Player) sender;
        ItemStack item = player.getInventory().getItemInMainHand();

        if (args[1].equalsIgnoreCase("clear")) {
            item.getEnchantments().forEach((enchantment, level) -> {
                item.removeEnchantment(enchantment);
            });

            player.getInventory().setItemInMainHand(item);
            player.sendMessage("§7エンチャントを削除しました！");
            return true;
        }

        String enchantmentName = args[1].toLowerCase();

        try {
            Enchantment enchantment = Enchantment.getByKey(NamespacedKey.minecraft(enchantmentName));
            if (enchantment == null) {
                player.sendMessage("§c無効なエンチャント名です。");
                return true;
            }

            int enchantLevel = 1;
            if (args.length == 3) {
                enchantLevel = Integer.parseInt(args[2]);
            }

            ItemMeta itemMeta = item.getItemMeta();

            itemMeta.addEnchant(enchantment, enchantLevel, true); // エンチャントを追加（レベル1）
            item.setItemMeta(itemMeta);
            player.sendMessage("§7 " + enchantmentName + " エンチャントをレベル " + enchantLevel + " で付与しました！");

        } catch (IllegalArgumentException e) {
            player.sendMessage("§c指定されたエンチャント名は存在しません！");
        } catch (Exception e) {
            player.sendMessage("§c不明なエラーが発生しました。");
            return false;
        }
        return true;
    }
}
