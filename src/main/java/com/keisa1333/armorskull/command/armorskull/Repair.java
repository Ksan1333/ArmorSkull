package com.keisa1333.armorskull.command.armorskull;

import com.keisa1333.armorskull.Util;
import de.tr7zw.changeme.nbtapi.NBTItem;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Repair implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String a, String[] args) {
        Player player = (Player) sender;
        ItemStack item = player.getInventory().getItemInMainHand();

        if (!Util.checkPlayerHead(sender, item)) return false;

        NBTItem nbti = new NBTItem(item);
        boolean isSetting = Util.getIsSetting(nbti);

        if (!Util.checkIsSetting(sender, isSetting, true)) return false;

        try {
            int[] durability = Durability.getDurability(item);
            int toDurability = 0;

            //指定なしMAX、指定ありプラマイ許可
            if (args[1].length() == 0 || args[1].equalsIgnoreCase("all")) {
                toDurability = durability[0];
            } else if (args[1].equalsIgnoreCase("0")) {
                toDurability = durability[1];
            } else {
                int arg = Integer.parseInt(args[1]);
                if (arg > 0) {
                    toDurability = durability[1] + arg;
                    if (toDurability > durability[0]) {
                        toDurability = durability[0];
                    }
                } else if (arg < 0) {
                    toDurability = durability[1] + arg;
                    if (toDurability <= 0) {
                        toDurability = 0;
                    }
                } else {
                    sender.sendMessage("§c有効な値を入力してください(整数、all)");
                }
            }

            Durability.setDurability(durability[0], toDurability, item, player, "default");

            sender.sendMessage("§7耐久値を回復しました。(" + toDurability + ")");
            return true;
        } catch(Exception e) {
            return false;
        }
    }
}
