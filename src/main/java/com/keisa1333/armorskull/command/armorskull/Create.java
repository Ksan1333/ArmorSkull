package com.keisa1333.armorskull.command.armorskull;

import com.keisa1333.armorskull.Util;
import de.tr7zw.changeme.nbtapi.NBTItem;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Create implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String a, String[] args) {
        if (!Util.checkArguments(sender, args, 2)) {
            sender.sendMessage("§c引数が足りません！/as create <durability>");
            return false;
        }

        Player player = (Player) sender;
        ItemStack item = player.getInventory().getItemInMainHand();

        if (!Util.checkPlayerHead(sender, item)) { return false; }

        NBTItem nbti = new NBTItem(item);
        boolean isSetting = Util.getIsSetting(nbti);

        if (!Util.checkIsSetting(sender, isSetting, false)) { return false; }

        try {
            nbti.setBoolean("armorskull.isAnvil", false);
            nbti.setBoolean("armorskull.isVanish", true);
            nbti.setBoolean("armorskull.isEnchant", true);
            nbti.setBoolean("armorskull.isMend", true);
            nbti.setBoolean("armorskull.isSetting", true);
            nbti.setString("armorskull.isAnvilItem", "");
            ItemStack head = nbti.getItem();
            player.getPlayer().getInventory().setItemInMainHand(head);

            int durability = Integer.parseInt(args[1]);
            Durability.setDurability(durability, durability, head, player, "default");

            sender.sendMessage("§7作成完了！次に/as settingを実行してください。");

            return true;

        } catch(Exception e) {
            return false;
        }
    }
}