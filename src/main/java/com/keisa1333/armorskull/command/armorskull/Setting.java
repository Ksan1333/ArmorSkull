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

public class Setting implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String a, String[] args) {
        Player player = (Player) sender;
        ItemStack item = player.getInventory().getItemInMainHand();
        NBTItem nbti = new NBTItem(item);

        if (args.length == 1 || args[1].equalsIgnoreCase("1")) {
            settingList1(item, sender);
            return true;
        }

        if (args[1].equalsIgnoreCase("2")) {
            settingList2(item, sender);
            return true;
        }

        List<String> validArgs = Arrays.asList("isanvil", "isvanish", "isenchant", "ismend");
        if (!validArgs.contains(args[1].toLowerCase())) {
            return false;
        }


        boolean bool = nbti.getBoolean("armorskull." + args[1]);
        if (bool) {
            bool = false;
        } else {
            bool = true;
        }
        nbti.setBoolean("armorskull." + args[1], bool);
        ItemStack head = nbti.getItem();
        player.getPlayer().getInventory().setItemInMainHand(head);

//        item = player.getInventory().getItemInMainHand();
//       settingList1(item, sender);
        sender.sendMessage("§7" + args[1] + "を" + bool + "に設定しました。");

        return true;

    }

    public void settingList1(ItemStack item, CommandSender sender) {
        NBTItem nbti = new NBTItem(item);

        Audience player = (Audience)sender;
        MiniMessage mm = MiniMessage.miniMessage();

        boolean isAnvil = nbti.getBoolean("armorskull.isAnvil");
        boolean isVanish = nbti.getBoolean("armorskull.isVanish");
        boolean isEnchant = nbti.getBoolean("armorskull.isEnchant");
        boolean isMend = nbti.getBoolean("armorskull.isMend");

        Component content1 = mm.deserialize("<gray><click:run_command:/armorskull setting isAnvil>金床修理可: " + isAnvil);

        Component content2 = mm.deserialize("<gray><click:run_command:/armorskull setting isMend>修繕可: " + isMend);

        Component content3 = mm.deserialize("<gray><click:run_command:/armorskull setting isEnchant>エンチャント可: " + isEnchant);

        Component content4 = mm.deserialize("<gray><click:run_command:/armorskull setting isVanish>耐久消費後消滅: " + isVanish);

        int[] durability = Durability.getDurability(item);
        Component content5 = mm.deserialize("<gray><click:suggest_command:/armorskull durability >耐久値: " + durability[1] + " / " + durability[0]);

        Component content6 = mm.deserialize("<yellow><click:run_command:/armorskull setting 2>[NEXT]");

        sender.sendMessage("§eArmorSkull Settings 1/2");
        player.sendMessage(content1);
        player.sendMessage(content2);
        player.sendMessage(content3);
        player.sendMessage(content4);
        player.sendMessage(content5);
        player.sendMessage(content6);
    }

    public void settingList2(ItemStack item, CommandSender sender) {
        NBTItem nbti = new NBTItem(item);

        Audience player = (Audience)sender;
        MiniMessage mm = MiniMessage.miniMessage();

        Component content1 = mm.deserialize("<gray><click:suggest_command:/armorskull rename >名前変更");

        Component content2 = mm.deserialize("<gray><click:suggest_command:/armorskull enchant >エンチャント");

        Component content3 = mm.deserialize("<gray><click:suggest_command:/armorskull modify >属性追加");

        ArmorSkull plugin = ArmorSkull.getInstance();
        String displayLore = plugin.getDisplayLore();
        Component content4 = mm.deserialize("<gray><click:suggest_command:/armorskull displaylore >耐久値表示形式: " + displayLore);

        Component content5;
        String anvilItem = nbti.getString("anvilItem");
        if (anvilItem.equalsIgnoreCase("")) {
            content5 = mm.deserialize("<gray><click:suggest_command:/armorskull setting anvilItem >金床修復アイテムID: 未設定");
        } else {
            content5 = mm.deserialize("<gray><click:suggest_command:/armorskull setting anvilItem >金床修復アイテムID: " + anvilItem);
        }

        Component content6 = mm.deserialize("<yellow><click:run_command:/armorskull setting>[PREVIOUS]");

        sender.sendMessage("§eArmorSkull Settings 2/2");
        player.sendMessage(content1);
        player.sendMessage(content2);
        player.sendMessage(content3);
        player.sendMessage(content4);
        player.sendMessage(content5);
        player.sendMessage(content6);
    }
}
