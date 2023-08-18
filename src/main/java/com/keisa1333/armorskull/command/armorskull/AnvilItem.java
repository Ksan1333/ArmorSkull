package com.keisa1333.armorskull.command.armorskull;

import com.keisa1333.armorskull.ArmorSkull;
import com.keisa1333.armorskull.Util;
import de.tr7zw.changeme.nbtapi.NBTFile;
import de.tr7zw.changeme.nbtapi.NBTItem;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;

public class AnvilItem implements CommandExecutor {
    //なにこれは
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player) sender;
        ItemStack item = player.getInventory().getItemInMainHand();
        NBTItem nbti = new NBTItem(item);

        NBTFile file = null;
        try {
            file = new NBTFile(new File("plugins/ArmorSkull/", "test.nbt"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

//        file.setItemStack("item");
        file.addCompound("testcomp");
        file.setLong("time", System.currentTimeMillis());
        file.setString("test", "test");
        try {
            file.save();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return false;
    }
}