package com.keisa1333.armorskull.command.armorskull;

import com.keisa1333.armorskull.ArmorSkull;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class DisplayLore implements CommandExecutor{
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
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
        }
        return true;
    }
}
