package com.keisa1333.armorskull.command.armorskull;


import com.keisa1333.armorskull.ArmorSkull;
import com.keisa1333.armorskull.Util;
import de.tr7zw.changeme.nbtapi.NBTItem;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.List;

public class DamageCause implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String a, String[] args) {
        Player player = (Player) sender;
        ItemStack item = player.getInventory().getItemInMainHand();
        NBTItem nbti = new NBTItem(item);

        if (args[1].equalsIgnoreCase("clear")) {
            String str = "";
            nbti.setByteArray("damagecause", str.getBytes());
            ItemStack head = nbti.getItem();
            player.getPlayer().getInventory().setItemInMainHand(head);
            sender.sendMessage("§7DamageCauseを削除しました。");

        } else if (args[1].equalsIgnoreCase("default")) {
            String str = "default";
            nbti.setByteArray("damagecause", str.getBytes());
            ItemStack head = nbti.getItem();
            player.getPlayer().getInventory().setItemInMainHand(head);
            sender.sendMessage("§7DamageCauseをデフォルトに戻しました。");
        }

        String str = args[2];
        nbti.setByteArray("damagecause", str.getBytes());
        ItemStack head = nbti.getItem();
        player.getPlayer().getInventory().setItemInMainHand(head);

        sender.sendMessage("§7DamageCauseに" + str + "を追加しました。");

        return true;
    }
}
