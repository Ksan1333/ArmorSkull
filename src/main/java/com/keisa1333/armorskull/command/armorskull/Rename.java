package com.keisa1333.armorskull.command.armorskull;

import com.keisa1333.armorskull.Util;
import net.md_5.bungee.api.ChatColor;
import org.apache.logging.log4j.util.StringBuilders;
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
            sender.sendMessage("§c引数が足りません！/as name <name>/clear [<RGB Color>]");
            return false;
        }

        Player player = (Player) sender;
        ItemStack item = player.getInventory().getItemInMainHand();
        ItemMeta itemMeta = item.getItemMeta();

        if (args[1].equalsIgnoreCase("clear")) {
            itemMeta.setDisplayName(null);
            return true;
        }

        String newName = String.join(" ", Arrays.copyOfRange(args, 1, args.length));

//        if (newName.length() > 64) {
//            player.sendMessage("§cアイテム名は64文字以内にしてください。");
//            return false;
//        }

        if (newName.contains("&")) {
            newName = newName.replace("&", "§");
        }

        StringBuilder sb;

        if (newName.contains("#")) {
            sb = new StringBuilder();
            String[] names = newName.split("#");
            for (int i = 0; i < names.length; i++) {
//                player.sendMessage(names[i]);
                if (names[i].matches("^[0-9A-Fa-f]{6}.*$")) {
                    String hexColor = names[i]; // 6文字の16進数カラーコード

                    String redHex = hexColor.substring(0, 2);     // 最初の2文字 (FF)
                    player.sendMessage(redHex);
                    String greenHex = hexColor.substring(2, 4);   // 次の2文字 (AA)
                    player.sendMessage(greenHex);
                    String blueHex = hexColor.substring(4, 6);    // 最後の2文字 (BB)
                    player.sendMessage(blueHex);

                    int red = Integer.parseInt(redHex, 16);       // 16進数文字列を10進数数値に変換
                    int green = Integer.parseInt(greenHex, 16);
                    int blue = Integer.parseInt(blueHex, 16);

                    // RGBカラーコードを作成
                    ChatColor color = ChatColor.of(new java.awt.Color(red, green, blue));   // 次の2文字 (AA)
                    String text = hexColor.substring(6);
//                    player.sendMessage(text);
                    sb.append(color).append(text);
                } else {
                    if (i == 0) {
                        sb.append(names[i]);
                    } else {
                        sb.append("#").append(names[i]);
                    }
                }
            }
            newName = sb.toString();
        }


        itemMeta.setDisplayName(newName);
        item.setItemMeta(itemMeta);
        return true;
    }
}
