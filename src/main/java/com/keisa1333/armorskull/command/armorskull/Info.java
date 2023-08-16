package com.keisa1333.armorskull.command.armorskull;

import com.keisa1333.armorskull.Util;
import de.tr7zw.changeme.nbtapi.NBTItem;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Info implements CommandExecutor {
//持っているアイテムのisや耐久値などを見れるようにする

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String a, String[] args) {
        Player player = (Player) sender;
        ItemStack item = player.getInventory().getItemInMainHand();

        if (!Util.checkPlayerHead(sender, item)) { return false; }

        NBTItem nbti = new NBTItem(item);
        boolean isSetting = Util.getIsSetting(nbti);

        if (!Util.checkIsSetting(sender, isSetting, true)) { return false; }

        boolean isAnvil = nbti.getBoolean("armorskull.isAnvil");
        boolean isVanish = nbti.getBoolean("armorskull.isVanish");
        boolean isEnchant = nbti.getBoolean("armorskull.isEnchant");
        boolean isMend = nbti.getBoolean("armorskull.isMend");
        String anvilItem = nbti.getString("armorskull.anvilItem");
        int[] durability = Durability.getDurability(item);

        sender.sendMessage("§eArmorskull Info");
        sender.sendMessage("§7金床修復: " + isAnvil);
        sender.sendMessage("§7修繕修復: " + isMend);
        sender.sendMessage("§7エンチャント: " + isEnchant);
        sender.sendMessage("§7耐久消費後消滅: " + isVanish);
        if (anvilItem.equalsIgnoreCase("")) {
            sender.sendMessage("§7金床修復アイテムID: 未設定");
        } else {
            sender.sendMessage("§7金床修復アイテムID: " + anvilItem);
        }
        sender.sendMessage("§7耐久値: " + durability[1] + " / " + durability[0]);

        return true;
    }
}