package com.keisa1333.armorskull;

import de.tr7zw.changeme.nbtapi.NBTItem;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.inventory.ItemStack;

public class Util {
    public static class NotBoolException extends Exception {
        public NotBoolException(String message) {
            super(message);
        }
    }

    // プレイヤーがコマンドの引数を満たしているかをチェックするメソッド
    public static boolean checkArguments(CommandSender sender, String[] args, int requiredArgs) {
        if (args.length < requiredArgs) {
            //args.length >= requireArgs
            return false;
        }
        return true;
    }

    // プレイヤーがプレイヤーヘッドを持っているかをチェックするメソッド
    public static boolean checkPlayerHead(CommandSender sender, ItemStack item) {
        if (item == null || item.getType() != Material.PLAYER_HEAD) {
            sender.sendMessage("§cプレイヤーの頭を手に持ってください！");
            return false;
        }
        return true;
    }

    public static boolean checkItem(CommandSender sender, ItemStack item) {
        if (item == null) {
            sender.sendMessage("§cアイテムを手に持ってください！");
            return false;
        }
        return true;
    }

    // NBTItemからisSettingの値を取得するメソッド
    public static boolean getIsSetting(NBTItem nbti) {
        return nbti.getBoolean("armorskull.isSetting");
    }

    // isSettingの値によってメッセージを送信するメソッド
    public static boolean checkIsSetting(CommandSender sender, boolean isSetting, boolean requestSetting) {
        if (requestSetting && !isSetting) {
            sender.sendMessage("§c先に/as create <durability>で作成してください");
            return false;
        } else if (!requestSetting && isSetting) {
            sender.sendMessage("§c作成済みです。/as settingから編集してください。");
            return false;
        }
        return true;
    }
}
