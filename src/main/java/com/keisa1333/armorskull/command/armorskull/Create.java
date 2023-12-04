package com.keisa1333.armorskull.command.armorskull;

import de.tr7zw.changeme.nbtapi.NBTItem;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Create implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String a, String[] args) {
        Player player = (Player) sender;
        ItemStack item = player.getInventory().getItemInMainHand();
        NBTItem nbti = new NBTItem(item);

        try {
            nbti.setBoolean("armorskull.isAnvil", false);
            nbti.setBoolean("armorskull.isVanish", true);
            nbti.setBoolean("armorskull.isEnchant", true);
            nbti.setBoolean("armorskull.isMend", true);
            nbti.setBoolean("armorskull.isSetting", true);
            nbti.setString("armorskull.isAnvilItem", "");
            nbti.setString("armorskull.damageCause", "default");
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
