package com.keisa1333.armorskull.command.armorskull;

import com.keisa1333.armorskull.ArmorSkull;
import com.keisa1333.armorskull.Util;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class AnvilItem implements CommandExecutor {
    //なにこれは
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("manageskull")) {
            if (!Util.checkArguments(sender, args, 2)) {
                sender.sendMessage("§e/manageskull (/ms)");
                sender.sendMessage("§7 ..displayLore <loreType>: 耐久値表記のパターンを変更できます");
                sender.sendMessage("§7(※一度ダメージを受ける、repairするなど耐久値変更の処理が行われる必要あり)");
                sender.sendMessage("§7  (default, number, japanese, bar)");
                sender.sendMessage("§7 .. anvilItem: 金床修復に使うアイテムを設定できます。");
                return false;
            }

            Player player = (Player) sender;

            if (args[0].equalsIgnoreCase("displayLore")) {
                String displayLore = args[1];

                if (displayLore.equalsIgnoreCase("Default") || displayLore.equalsIgnoreCase("Number") || displayLore.equalsIgnoreCase("Japanese") || displayLore.equalsIgnoreCase("Bar")) {
                    // ArmorSkullクラスのインスタンスを取得し、displayLoreを更新する
                    ArmorSkull plugin = ArmorSkull.getInstance();
                    plugin.setDisplayLore(displayLore);

                    sender.sendMessage("§7displayLoreを更新しました。");

                    return true;
                } else {
                    sender.sendMessage("§cそのような設定項目はありません。(Default, Number, Japanese, Bar)");
                    return false;
                }
            } else if (args[0].equalsIgnoreCase("anvilItem")) {
                ItemStack item = player.getItemInHand(); // 手持ちのアイテムを取得
                if (item != null && item.getType() != Material.AIR) {

                } else {
                    player.sendMessage("§c手持ちのアイテムがありません。");
                }
                return true;
            } else {
                sender.sendMessage("そのような設定項目はありません。(displayLore, anvilItem)");
            }
        }
        return false;
    }
}