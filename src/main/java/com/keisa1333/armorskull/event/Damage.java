package com.keisa1333.armorskull.event;

import com.keisa1333.armorskull.Util;
import com.keisa1333.armorskull.command.armorskull.Durability;
import de.tr7zw.changeme.nbtapi.NBTItem;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Map;
import java.util.Random;

public class Damage implements Listener {

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if (!(event.getEntity() instanceof Player)) return;

        Player player = (Player) event.getEntity();
        ItemStack helmet = player.getInventory().getHelmet();

        if (helmet == null || (helmet.getType() != Material.PLAYER_HEAD && helmet.getType() != Material.PLAYER_WALL_HEAD)) return;

        NBTItem nbti = new NBTItem(helmet);

        if (!Util.getIsSetting(nbti)) return;

        int nowD;
        int[] durability = Durability.getDurability(helmet);
        Map<Enchantment, Integer> enchantments = helmet.getEnchantments();

        EntityDamageEvent.DamageCause cause = event.getCause();
//        player.sendMessage(cause.toString());

        // Check for specific causes of damage
        String damageCause = nbti.getString("armorskull.damageCause");
        if (damageCause.contains("default")) {
            if (
                    cause != EntityDamageEvent.DamageCause.ENTITY_ATTACK &&
                            cause != EntityDamageEvent.DamageCause.ENTITY_SWEEP_ATTACK &&
                            cause != EntityDamageEvent.DamageCause.PROJECTILE &&
                            cause != EntityDamageEvent.DamageCause.LIGHTNING &&
                            cause != EntityDamageEvent.DamageCause.FIRE &&
                            cause != EntityDamageEvent.DamageCause.LAVA &&
                            cause != EntityDamageEvent.DamageCause.HOT_FLOOR &&
                            cause != EntityDamageEvent.DamageCause.BLOCK_EXPLOSION &&
                            cause != EntityDamageEvent.DamageCause.CONTACT &&
                            cause != EntityDamageEvent.DamageCause.ENTITY_EXPLOSION &&
                            cause != EntityDamageEvent.DamageCause.SONIC_BOOM &&
                            cause != EntityDamageEvent.DamageCause.THORNS
            ) return;

        } else {
            String[] causes = damageCause.split(",");
            for (int i = 0; i < causes.length; i++) {
                if (!cause.toString().equals(causes[i])) return;
            }
        }

        //耐久値が0なのに着用していた場合
        if (durability[1] <= 0) {
            event.setCancelled(true);
            double damage = event.getDamage();
            player.damage(damage);
        }

        //耐久力エンチャがついてた時の処理
        if (enchantments.containsKey(Enchantment.DURABILITY)) {
            int level = enchantments.get(Enchantment.DURABILITY);
            double chance = 60 + 40.0 / (level + 1);
            Random random = new Random();
            int randomNumber = random.nextInt(100);

            if (randomNumber <= chance) {
                nowD = durability[1] - 1;
            } else {
                nowD = durability[1];
            }

        //耐久力エンチャがないときの処理
        } else {
            nowD = durability[1] - 1;
        }

        //棘の鎧エンチャがついてた時の処理
        if (enchantments.containsKey(Enchantment.THORNS)) {
            nowD = durability[1] - 1;
        }

        //耐久値が0以下になる時
        if (nowD <= 0) {
            if (!nbti.getBoolean("armorskull.isVanish")) {
                player.sendMessage("vanish!");
                Durability.setDurability(durability[0], 0, helmet, player, "head");
                int emptySlot = player.getInventory().firstEmpty();

                // インベントリに空きスロットがある場合、頭装備を空きスロットに入れる
                if (emptySlot != -1) {
                    player.getInventory().setItem(emptySlot, helmet);
                    player.getInventory().setHelmet(null);
                    player.sendMessage("§cArmorSkullの耐久値がないためインベントリに移しました");
                    player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_TRADE, 1.0f, 1.0f);

                //空きスロットがない場合、ドロップする
                } else {
                    player.getInventory().setHelmet(null);
                    player.getWorld().dropItem(player.getLocation(), helmet);
                    player.sendMessage("§cArmorSkullの耐久値がないためドロップしました");
                    player.playSound(player.getLocation(), Sound.ENTITY_VILLAGER_TRADE, 1.0f, 1.0f);
                }

            //isVanishが有効の場合、消滅
            } else {
                player.getInventory().setHelmet(null);
                player.playSound(player.getLocation(), Sound.ITEM_ARMOR_EQUIP_GENERIC, 1.0f, 1.0f);
                player.sendMessage("§cArmorSkullが壊れました");
            }

        //耐久値が0より大きい時
        } else if (nowD > 0) {
            Durability.setDurability(durability[0], nowD, helmet, player, "head");
        }
    }

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        if (!(event.getEntity() instanceof Player) && !(event.getDamager() instanceof Player)) return;

        Player attacker = (Player) event.getDamager();
        ItemStack helmet = attacker.getInventory().getHelmet();

        if (helmet == null || (helmet.getType() != Material.PLAYER_HEAD && helmet.getType() != Material.PLAYER_WALL_HEAD)) return;

        NBTItem nbti = new NBTItem(helmet);

        if (!Util.getIsSetting(nbti)) return;

        int nowD;
        int[] durability = Durability.getDurability(helmet);

        // ダメージを与える側のプレイヤーが棘の鎧を装備しているかチェック
        if (attacker.getInventory().getArmorContents()[2] != null) {
            if (attacker.getInventory().getArmorContents()[2].containsEnchantment(org.bukkit.enchantments.Enchantment.THORNS)) {
                nowD = durability[1] - 2;
                Durability.setDurability(durability[0], nowD, helmet, attacker, "head");
            }
        }
    }
}
