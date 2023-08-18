package com.keisa1333.armorskull.command;

import com.keisa1333.armorskull.Util;
import com.keisa1333.armorskull.command.armorskull.*;
import de.tr7zw.changeme.nbtapi.NBTItem;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ArmorSkullCommand implements CommandExecutor, TabCompleter {


    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String a, String[] args) {
        if (!cmd.getName().equalsIgnoreCase("armorskull")) {
            return null;
        }

        if (!(sender instanceof Player)) {
            sender.sendMessage("§cこのコマンドはプレイヤーのみが使用できます。");
            return null;
        }

        if (args.length == 1) {
            if (args[0].length() == 0) {
                return Arrays.asList("anvilitem", "create", "displaylore", "durability", "enchant", "help", "modify", "remove", "rename", "repair", "info", "setting");
            } else {
                if ("create".startsWith(args[0])) {
                    return Collections.singletonList("create");
                } else if ("durability".startsWith(args[0])) {
                    return Collections.singletonList("durability");
                } else if ("enchant".startsWith(args[0])) {
                    return Collections.singletonList("enchant");
                } else if ("help".startsWith(args[0])) {
                    return Collections.singletonList("help");
                } else if ("modify".startsWith(args[0])) {
                    return Collections.singletonList("modify");
                } else if ("remove".startsWith(args[0])) {
                    return Collections.singletonList("remove");
                } else if ("rename".startsWith(args[0])) {
                    return Collections.singletonList("rename");
                } else if ("repair".startsWith(args[0])) {
                    return Collections.singletonList("repair");
                } else if ("info".startsWith(args[0])) {
                    return Collections.singletonList("info");
                } else if ("setting".startsWith(args[0])) {
                    return Collections.singletonList("setting");
                } else if ("anvilitem".startsWith(args[0])) {
                    return Collections.singletonList("anvilitem");
                } else if ("displaylore".startsWith(args[0])) {
                    return Collections.singletonList("displaylore");
                }
            }
        }

        if (args.length >= 2) {
            if (args[0].equalsIgnoreCase("setting")) {
                if (args[1].length() == 0) {
                    return Arrays.asList("isAnvil", "isVanish", "isEnchant", "isMend");
                } else {
                    if ("isAnvil".startsWith(args[1])) {
                        return Collections.singletonList("isAnvil");
                    } else if ("isVanish".startsWith(args[1])) {
                        return Collections.singletonList("isVanish");
                    } else if ("isEnchant".startsWith(args[1])) {
                        return Collections.singletonList("isEnchant");
                    } else if ("isMend".startsWith(args[1])) {
                        return Collections.singletonList("isMend");
                    }
                }

            } else if (args[0].equalsIgnoreCase("durability") || args[0].equalsIgnoreCase("create")) {
                if (args[1].length() == 0) {
                    return Arrays.asList("58", "78", "166", "364", "408");
                } else {
                    if ("58".startsWith(args[1])) {
                        return Collections.singletonList("58");
                    } else if ("78".startsWith(args[1])) {
                        return Collections.singletonList("78");
                    } else if ("166".startsWith(args[1])) {
                        return Collections.singletonList("166");
                    } else if ("364".startsWith(args[1])) {
                        return Collections.singletonList("364");
                    } else if ("408".startsWith(args[1])) {
                        return Collections.singletonList("408");
                    }
                }
            } else if (args[0].equalsIgnoreCase("repair")) {
                if (args[1].length() == 0) {
                    return Arrays.asList("all", "10", "-10");
                } else {
                    if ("all".startsWith(args[1])) {
                        return Collections.singletonList("all");
                    } else if ("10".startsWith(args[1])) {
                        return Collections.singletonList("10");
                    } else if ("-10".startsWith(args[1])) {
                        return Collections.singletonList("-10");
                    }
                }
            } else if (args[0].equalsIgnoreCase("enchant")) {
                if (args.length == 3) {
                    return Arrays.asList("1", "2", "3", "4", "5");
                } else if (args.length == 2) {
                    if (args[1].length() == 0) {
                        return Arrays.asList("aqua_affinity", "blast_protection", "binding_curse", "vanishing_curse", "fire_protection", "mending", "projectile_protection", "protection", "respiration", "thorns", "unbreaking");
                    } else {
                        if ("aqua_affinity".startsWith(args[1])) {
                            return Collections.singletonList("aqua_affinity");
                        } else if ("blast_protection".startsWith(args[1])) {
                            return Collections.singletonList("blast_protection");
                        } else if ("binding_curse".startsWith(args[1])) {
                            return Collections.singletonList("binding_curse");
                        } else if ("vanishing_curse".startsWith(args[1])) {
                            return Collections.singletonList("vanishing_curse");
                        } else if ("fire_protection".startsWith(args[1])) {
                            return Collections.singletonList("fire_protection");
                        } else if ("mending".startsWith(args[1])) {
                            return Collections.singletonList("mending");
                        } else if ("projectile_protection".startsWith(args[1])) {
                            return Collections.singletonList("projectile_protection");
                        } else if ("protection".startsWith(args[1])) {
                            return Collections.singletonList("protection");
                        } else if ("respiration".startsWith(args[1])) {
                            return Collections.singletonList("respiration");
                        } else if ("thorns".startsWith(args[1])) {
                            return Collections.singletonList("thorns");
                        } else if ("unbreaking".startsWith(args[1])) {
                            return Collections.singletonList("unbreaking");
                        }
                    }
                }
            } else if (args[0].equalsIgnoreCase("modify")) {
                if (args.length == 4) {
                    return Arrays.asList("0", "1", "2");
                } else if (args.length == 3) {
                    if (args[2].length() == 0) {
                        return Collections.singletonList("<number>");
                    }
                } else if (args.length == 2) {
                    if (args[1].length() == 0) {
                        return Arrays.asList("maxhealth", "speed", "knockback", "armor", "toughness");
                    } else {
                        if ("maxhealth".startsWith(args[1])) {
                            return Collections.singletonList("maxhealth");
                        } else if ("speed".startsWith(args[1])) {
                            return Collections.singletonList("speed");
                        } else if ("knockback".startsWith(args[1])) {
                            return Collections.singletonList("knockback");
                        } else if ("armor".startsWith(args[1])) {
                            return Collections.singletonList("armor");
                        } else if ("toughness".startsWith(args[1])) {
                            return Collections.singletonList("toughness");
                        }
                    }
                }
            } else if (args[0].equalsIgnoreCase("displaylore")) {
                if (args.length == 2) {
                    if (args[1].length() == 0) {
                        return Arrays.asList("default", "number", "japanese", "bar");
                    } else {
                        if ("default".startsWith(args[1])) {
                            return Collections.singletonList("default");
                        } else if ("number".startsWith(args[1])) {
                            return Collections.singletonList("number");
                        } else if ("japanese".startsWith(args[1])) {
                            return Collections.singletonList("japanese");
                        } else if ("bar".startsWith(args[1])) {
                            return Collections.singletonList("bar");
                        }
                    }
                }
            }
        }
        return null;
    }

    private List<String> filterTabOptions(String currentArg, String... options) {
        List<String> suggestions = new ArrayList<>();
        for (String option : options) {
            if (option.startsWith(currentArg)) {
                suggestions.add(option);
            }
        }
        return suggestions;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String a, String[] args) {
        Player player = (Player) sender;

        if (args[0].equalsIgnoreCase("damage") || args[0].equalsIgnoreCase("durability") || args[0].equalsIgnoreCase("info") || args[0].equalsIgnoreCase("remove") || args[0].equalsIgnoreCase("repair") || args[0].equalsIgnoreCase("setting")) {
            ItemStack item = player.getInventory().getItemInMainHand();
            NBTItem nbti;
            try {
                nbti = new NBTItem(item);
            } catch (Exception e) {
                player.sendMessage("§cプレイヤーヘッドを手に持ってください！");
                return false;
            }
            boolean isSetting = Util.getIsSetting(nbti);

            if (!Util.checkPlayerHead(sender, item)) return false;
            if (!Util.checkIsSetting(sender, isSetting, true)) return false;
        }

        if (args[0].equalsIgnoreCase("enchant") || args[0].equalsIgnoreCase("modify") || args[0].equalsIgnoreCase("rename") || args[0].equalsIgnoreCase("hideflags")) {
            ItemStack item = player.getInventory().getItemInMainHand();
            NBTItem nbti;
            try {
                nbti = new NBTItem(item);
            } catch (Exception e) {
                player.sendMessage("§cアイテムを手に持ってください！");
                return false;
            }
        }

        if (args[0].equalsIgnoreCase("create")) {
            ItemStack item = player.getInventory().getItemInMainHand();
            NBTItem nbti;
            try {
                nbti = new NBTItem(item);
            } catch (Exception e) {
                player.sendMessage("§cプレイヤーヘッドを手に持ってください！");
                return false;
            }
            boolean isSetting = Util.getIsSetting(nbti);

            if (!Util.checkPlayerHead(sender, item)) return false;
            if (!Util.checkIsSetting(sender, isSetting, false)) return false;
        }

        if (args.length > 0) {
            String commandName = args[0].toLowerCase();
            switch (commandName) {
                case "anvilitem":
                    AnvilItem anvilItem = new AnvilItem();
                    anvilItem.onCommand(sender, cmd, a, args);
                    return true;

                case "create":
                    if (!Util.checkArguments(sender, args, 2)) {
                        sender.sendMessage("§c引数が足りません！/as create <durability>");
                        return false;
                    }
                    Create create = new Create();
                    create.onCommand(sender, cmd, a, args);
                    return true;

                case "damage":
                    if (args.length == 1 || (!args[1].equalsIgnoreCase("clear") && !args[1].equalsIgnoreCase("default") && !Util.checkArguments(sender, args, 3))) {
                        sender.sendMessage("§c引数が足りません！/as damage (clear|default|add <damagecause>)");
                        return false;
                    }
                    DamageCause damage = new DamageCause();
                    damage.onCommand(sender, cmd, a, args);
                    return true;

                case "displaylore":
                    if (!Util.checkArguments(sender, args, 2)) {
                        sender.sendMessage("§c引数が足りません！/as displaylore (japanese|default|number|bar)");
                        return false;
                    }
                    DisplayLore displayLore = new DisplayLore();
                    displayLore.onCommand(sender, cmd, a, args);
                    return true;

                case "durability":
                    if (!Util.checkArguments(sender, args, 2)) {
                        sender.sendMessage("§c引数が足りません！/as durability <durability>");
                        return false;
                    }
                    Durability durability = new Durability();
                    durability.onCommand(sender, cmd, a, args);
                    return true;

                case "enchant":
                    if (args.length == 1 || (!args[1].equalsIgnoreCase("clear") && !Util.checkArguments(sender, args, 3))) {
                        sender.sendMessage("§c引数が足りません！/as enchant (clear|<enchant type> [<level>])");
                        return false;
                    }
                    Enchant enchant = new Enchant();
                    enchant.onCommand(sender, cmd, a, args);
                    return true;

                case "help":
                    Help help = new Help();
                    help.onCommand(sender, cmd, a, args);
                    return true;

                case "hideflags":
                    if (args.length == 1 || (!args[1].equalsIgnoreCase("clear") && !Util.checkArguments(sender, args, 2))) {
                        sender.sendMessage("§c引数が足りません！/as hideflags (clear|0|255|<number>)");
                        return false;
                    }
                    Hideflags hidef = new Hideflags();
                    hidef.onCommand(sender, cmd, a, args);
                    return true;

                case "info":
                    Info info = new Info();
                    info.onCommand(sender, cmd, a, args);
                    return true;

                case "modify":
                    if (args.length == 1 || (!args[1].equalsIgnoreCase("clear") && !Util.checkArguments(sender, args,5))) {
                        //<0,1,2> ADD_SCALAR（スカラー値を加算）、MULTIPLY_SCALAR_1（スカラー値を乗算）、MULTIPLY_SCALAR_2（スカラー値を2倍乗算）
                        player.sendMessage("§c引数が足りません！/as modify (clear|<attribute> <number> <0,1,2> <slot>)");
                        return false;
                    }
                    Modify modify = new Modify();
                    modify.onCommand(sender, cmd, a, args);
                    return true;

                case "remove":
                    Remove remove = new Remove();
                    remove.onCommand(sender, cmd, a, args);
                    return true;

                case "rename":
                    if (args.length == 1 || (!args[1].equalsIgnoreCase("clear") && !Util.checkArguments(sender, args, 2))) {
                        sender.sendMessage("§c引数が足りません！/as rename (clear|<name>)");
                        return false;
                    }
                    Rename rename = new Rename();
                    rename.onCommand(sender, cmd, a, args);
                    return true;

                case "repair":
                    if (!Util.checkArguments(sender, args, 2)) {
                        sender.sendMessage("§c引数が足りません！/as repair (all|0|<number>)");
                        return false;
                    }
                    Repair repair = new Repair();
                    repair.onCommand(sender, cmd, a, args);
                    return true;

                case "setting":
                    Setting setting = new Setting();
                    setting.onCommand(sender, cmd, a, args);
                    return true;

                default:
                    sender.sendMessage("§cそのようなサブコマンドは登録されていません。/as helpを用いて確認してください。");
                    break;
            }
        }
        return false;
    }
}
