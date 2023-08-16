package com.keisa1333.armorskull.event;

import com.keisa1333.armorskull.Util;
import com.keisa1333.armorskull.command.armorskull.Durability;
import de.tr7zw.changeme.nbtapi.NBTItem;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Map;
import java.util.Random;

public class Damage implements Listener {

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();

            ItemStack helmet = player.getInventory().getHelmet();

            if (helmet != null && (helmet.getType() == Material.PLAYER_HEAD || helmet.getType() == Material.PLAYER_WALL_HEAD)) {
                NBTItem nbti = new NBTItem(helmet);
                if (Util.getIsSetting(nbti)) {
                    int nowD;
                    int[] durability = Durability.getDurability(helmet);
                    Map<Enchantment, Integer> enchantments = helmet.getEnchantments();

                    EntityDamageEvent.DamageCause cause = event.getCause();

                    // Check for specific causes of damage
                    if (
                        // Mob or player's direct attack
                            cause == EntityDamageEvent.DamageCause.ENTITY_ATTACK ||
                                    cause == EntityDamageEvent.DamageCause.ENTITY_SWEEP_ATTACK ||

                                    // Projectile hits (Arrow, Shulker Bullet, Trident, Egg, Snowball, Fireball)
                                    cause == EntityDamageEvent.DamageCause.PROJECTILE ||

                                    // Lightning strike
                                    cause == EntityDamageEvent.DamageCause.LIGHTNING ||

                                    // Fire, Lava, Magma Block, Cactus, or Sweeetberry Bush damage
                                    cause == EntityDamageEvent.DamageCause.FIRE || cause == EntityDamageEvent.DamageCause.LAVA || cause == EntityDamageEvent.DamageCause.HOT_FLOOR
                    ) {
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

                        //耐久値が0以下になる時
                        if (nowD <= 0) {
                            if (nbti.getBoolean("isVanish")) {
                                Durability.setDurability(durability[0], 0, helmet, player, "head");
                                int emptySlot = player.getInventory().firstEmpty();

                                // インベントリに空きスロットがある場合、頭装備を空きスロットに入れる
                                if (emptySlot != -1) {
                                    player.getInventory().setItem(emptySlot, helmet);
                                    player.getInventory().setHelmet(null);
                                    //空きスロットがない場合、ドロップする
                                } else {
                                    player.getInventory().setHelmet(null);
                                    player.getWorld().dropItem(player.getLocation(), helmet);
                                }
                            } else {
                                player.getInventory().setHelmet(null);
                            }

                            //耐久値が0より大きい時
                        } else if (nowD > 0) {
                            Durability.setDurability(durability[0], nowD, helmet, player, "head");
                        }
                    }
                }
            }
        }
    }
}