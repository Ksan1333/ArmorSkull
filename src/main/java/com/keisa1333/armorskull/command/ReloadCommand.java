package com.keisa1333.armorskull.command;

import com.keisa1333.armorskull.ArmorSkull;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ReloadCommand implements CommandExecutor {

    private final ArmorSkull plugin;

    public ReloadCommand(ArmorSkull plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
//        if (sender instanceof Player && !sender.hasPermission("plugin.reload")){
//            sender.sendMessage("You do not have permission to use this command!");
//            return false;
//        }

        plugin.reloadConfig();
        sender.sendMessage("§7リロードしました！");
        return true;
    }
}