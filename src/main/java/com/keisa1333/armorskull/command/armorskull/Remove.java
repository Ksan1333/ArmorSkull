package com.keisa1333.armorskull.command.armorskull;

import com.keisa1333.armorskull.Util;
import de.tr7zw.changeme.nbtapi.NBTItem;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Remove implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String a, String[] args) {
        Player player = (Player) sender;
        ItemStack item = player.getInventory().getItemInMainHand();

        if (!Util.checkPlayerHead(sender, item)) { return false; }

        NBTItem nbti = new NBTItem(item);
        boolean isSetting = Util.getIsSetting(nbti);

        if (!Util.checkIsSetting(sender, isSetting, true)) { return false; }

        nbti.removeKey("armorskull.isAnvil");
        nbti.removeKey("armorskull.isVanish");
        nbti.removeKey("armorskull.isEnchant");
        nbti.removeKey("armorskull.isMend");
        nbti.removeKey("armorskull.isSetting");
        ItemStack head = nbti.getItem();
        player.getPlayer().getInventory().setItemInMainHand(head);

        Durability.removeDurability(head, player);

        sender.sendMessage("§7ArmorSkullの設定を削除しました。");
        return true;
    }
}
