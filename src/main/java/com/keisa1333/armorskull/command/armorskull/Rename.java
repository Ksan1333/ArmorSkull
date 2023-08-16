package com.keisa1333.armorskull.command.armorskull;

import com.keisa1333.armorskull.Util;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class Rename implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String a, String[] args) {
        if (!Util.checkArguments(sender, args, 2)) {
            sender.sendMessage("§c引数が足りません！/as name <name>");
            return false;
        }

        String newName = String.join(" ", Arrays.copyOfRange(args, 1, args.length));
        Player player = (Player) sender;

        if (newName.length() > 64) {
            player.sendMessage("§cアイテム名は64文字以内にしてください。");
            return false;
        }

        if (newName.contains("&")) {
            newName = newName.replace("&", "§");
        }

        ItemStack item = player.getInventory().getItemInMainHand();
        ItemMeta itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(newName);
        item.setItemMeta(itemMeta);

        return true;
    }
}
