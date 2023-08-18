package com.keisa1333.armorskull.command.armorskull;


import com.keisa1333.armorskull.ArmorSkull;
import com.keisa1333.armorskull.Util;
import de.tr7zw.changeme.nbtapi.NBTItem;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Durability implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String a, String[] args) {
        Player player = (Player) sender;
        ItemStack item = player.getInventory().getItemInMainHand();

        try {
            int durability = Integer.parseInt(args[1]);
            setDurability(durability, durability, item, player, "default");
            sender.sendMessage("§7耐久値を" + durability + "に設定しました。");
            return true;
        } catch(Exception e) {
            return false;
        }
    }

    public static void setDurability(int maxDurability, int nowDurability, ItemStack item, Player player, String mode) {
        ItemMeta itemMeta = item.getItemMeta();

        List<String> lore = itemMeta.getLore();
        if (lore == null) {
            lore = new ArrayList<>();
        }

        ArmorSkull armorSkull = ArmorSkull.getInstance();
        String displayLore = armorSkull.getDisplayLore();

        String regexDefault = "Durability: \\d+ / \\d+";
        String regexNumber = "\\d+ / \\d+";
        String regexJapanese = "耐久値 : \\d+／\\d+";
        Pattern patternDefault = Pattern.compile(regexDefault);
        Pattern patternNumber = Pattern.compile(regexNumber);
        Pattern patternJapanese = Pattern.compile(regexJapanese);

        for (int i = 0; i < lore.size(); i++) {
            String line = lore.get(i);
            Matcher matcherDefault = patternDefault.matcher(line);
            Matcher matcherNumber = patternNumber.matcher(line);
            Matcher matcherJapanese = patternJapanese.matcher(line);

            if (matcherDefault.find() || matcherNumber.find() || matcherJapanese.find() || line.contains("■")) {
                lore.remove(i);
            }
        }

        String text = "";

        if (displayLore.equalsIgnoreCase("default")) {
            text = "§fDurability: " + nowDurability + " / " + maxDurability;
        } else if (displayLore.equalsIgnoreCase("number")) {
            text = "§f" + nowDurability + " / " + maxDurability;
        }else if (displayLore.equalsIgnoreCase("japanese")) {
            text = "§f耐久値 : " + nowDurability + "／" + maxDurability;
        } else if (displayLore.equalsIgnoreCase("bar")) {
            double numberDivide = (double) nowDurability / maxDurability * 10;
            int numberFloor = (int) Math.floor(numberDivide);

            if (numberFloor == 0) {
                text = "§7■■■■■■■■■■";
            } else {
                StringBuilder sb = new StringBuilder("§b");
                for (int i = 1; i <= numberFloor; i++) {
                    sb.append("■");
                }

                sb.append("§7");
                for (int i = 1; i <= 10 - numberFloor; i++) {
                    sb.append("■");
                }

                sb.append(" §7(" + nowDurability + ")");
                text = sb.toString();
            }
        }
        text = String.join("\n", text);
        lore.add(text);
        itemMeta.setLore(lore);
        item.setItemMeta(itemMeta);

        NBTItem nbti = new NBTItem(item);
        nbti.setInteger("armorskull.maxDurability", maxDurability);
        nbti.setInteger("armorskull.nowDurability", nowDurability);
        ItemStack head = nbti.getItem();
        if (mode.equalsIgnoreCase("default")) {
            player.getPlayer().getInventory().setItemInMainHand(head);
        } else if (mode.equalsIgnoreCase("head")) {
            player.getPlayer().getInventory().setHelmet(head);
        }
    }

    public static void removeDurability(ItemStack item, Player player) {
        ItemMeta itemMeta = item.getItemMeta();

        List<String> lore = itemMeta.getLore();
        if (lore == null) {
            lore = new ArrayList<>();
        }

        String regexDefault = "Durability: \\d+ / \\d+";
        String regexNumber = "\\d+ / \\d+";
        String regexJapanese = "耐久値 : \\d+／\\d+";
        Pattern patternDefault = Pattern.compile(regexDefault);
        Pattern patternNumber = Pattern.compile(regexNumber);
        Pattern patternJapanese = Pattern.compile(regexJapanese);

        for (int i = 0; i < lore.size(); i++) {
            String line = lore.get(i);
            Matcher matcherDefault = patternDefault.matcher(line);
            Matcher matcherNumber = patternNumber.matcher(line);
            Matcher matcherJapanese = patternJapanese.matcher(line);

            if (matcherDefault.find() || matcherNumber.find() || matcherJapanese.find() || line.contains("■")) {
                lore.remove(i);
            }
        }

        itemMeta.setLore(lore);
        item.setItemMeta(itemMeta);

        NBTItem nbti = new NBTItem(item);
        nbti.removeKey("armorskull.maxDurability");
        nbti.removeKey("armorskull.nowDurability");
        ItemStack head = nbti.getItem();
        player.getPlayer().getInventory().setItemInMainHand(head);
    }

    public static int[] getDurability(ItemStack item) {

        NBTItem nbti = new NBTItem(item);
        int maxD = nbti.getInteger("armorskull.maxDurability");
        int nowD = nbti.getInteger("armorskull.nowDurability");
        return new int[]{maxD, nowD};
    }
}
