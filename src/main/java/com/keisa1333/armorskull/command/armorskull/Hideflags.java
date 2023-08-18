package com.keisa1333.armorskull.command.armorskull;

import de.tr7zw.changeme.nbtapi.NBTItem;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Hideflags implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        ItemStack item = player.getInventory().getItemInMainHand();
        NBTItem nbti = new NBTItem(item);

        if (args[1].equalsIgnoreCase("clear")) {
            nbti.setInteger("HideFlags", 0);
            item = nbti.getItem();
            player.getPlayer().getInventory().setItemInMainHand(item);
            player.sendMessage("§7Hideflagsを削除しました！");
            return true;
        }

        int hidef;
        try {
            hidef = Integer.parseInt(args[1]);
            if (hidef < 0 && hidef > 255) throw new Exception();
        } catch (Exception e) {
            Audience audience = (Audience) sender;
            MiniMessage mm = MiniMessage.miniMessage();
            Component help = mm.deserialize("<red><hover:show_text:\"<blue>以下の値がそれぞれの項目と対応してます。\n1:エンチャ情報, 2:属性, 4:不可解\n8:破壊可能, 16: 設置可能, 32:その他, 64:染料, 128:装飾\n複数の項目を隠す場合は値を足してください。\n/as hideflags (clear|0|255|<number>)\">第２引数は整数で入力してください。(Hover me)");
            audience.sendMessage(help);
            return false;
        }

        nbti.setInteger("HideFlags", hidef);
        item = nbti.getItem();
        player.getPlayer().getInventory().setItemInMainHand(item);
        player.sendMessage("§7Hideflagsの設定ができました。");
        return true;
    }
}
