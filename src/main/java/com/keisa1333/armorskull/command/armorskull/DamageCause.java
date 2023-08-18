package com.keisa1333.armorskull.command.armorskull;


import com.keisa1333.armorskull.ArmorSkull;
import com.keisa1333.armorskull.Util;
import de.tr7zw.changeme.nbtapi.NBTCompoundList;
import de.tr7zw.changeme.nbtapi.NBTItem;
import de.tr7zw.changeme.nbtapi.NBTListCompound;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class DamageCause implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String a, String[] args) {
        Player player = (Player) sender;
        ItemStack item = player.getInventory().getItemInMainHand();
        NBTItem nbti = new NBTItem(item);

        if (args[1].equalsIgnoreCase("clear")) {
            nbti.setString("armorskull.damageCause", "");
            ItemStack head = nbti.getItem();
            player.getPlayer().getInventory().setItemInMainHand(head);
            sender.sendMessage("§7DamageCauseを削除しました。");
            return true;

        } else if (args[1].equalsIgnoreCase("default")) {
            nbti.setString("armorskull.damageCause", "default");
            ItemStack head = nbti.getItem();
            player.getPlayer().getInventory().setItemInMainHand(head);
            sender.sendMessage("§7DamageCauseをデフォルトに戻しました。");
            return true;
        }

        if (!args[1].equalsIgnoreCase("add")) {
            sender.sendMessage("§c引数が違います！/as damage (clear|default|add <damagecause>)");
            return false;
        }

        List<String> damageList = new ArrayList<>(List.of("BLOCK_EXPLOSION", "CONTACT", "CRAMMING", "CUSTOM", "DRAGON_BREATH", "DROWING", "DRYOUT","ENTITY_ATTACK", "ENTITY_EXPLOSION", "ENTITY_SWEEP_ATTACK", "FALL", "FALLING_BLOCK", "FIRE", "FIRE_TICK", "FLY_INTO_WALL", "FREEZE", "HOT_FLOOR", "KILL", "LAVA", "LIGHTNING", "MAGIC", "MELTING", "POISON", "PROJECTILE", "SONIC_BOOM", "STARVATION", "SUFFOCATION", "SUICIDE", "THORNS", "VOID", "WITHER", "WORLD_BORDER"));

        if (damageList.contains(args[2])) {
            Audience audience = (Audience) sender;
            MiniMessage mm = MiniMessage.miniMessage();
            Component help = mm.deserialize("<red><click:open_url:https://hub.spigotmc.org/javadocs/spigot/org/bukkit/event/entity/EntityDamageEvent.DamageCause.html>設定項目が違います！クリックして設定項目を確認してください。");
            audience.sendMessage(help);
            return false;
        }

        String damage = nbti.getString("armorskull.damageCause");

        if (damage.contains("default")) {
            nbti.setString("armorskull.damageCause", null);
            damage = args[2];
        } else {
            StringBuilder sb = new StringBuilder(damage);
            sb.append(",").append(args[2]);
            damage = sb.toString();
        }

        nbti.setString("damageCause", damage);
        ItemStack head = nbti.getItem();
        player.getPlayer().getInventory().setItemInMainHand(head);

        sender.sendMessage("§7DamageCauseを" + damage + "に設定しました。");

        return true;
    }
}
