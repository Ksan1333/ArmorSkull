package com.keisa1333.armorskull;

import com.keisa1333.armorskull.command.ArmorSkullCommand;
import com.keisa1333.armorskull.command.ReloadCommand;
import com.keisa1333.armorskull.event.Damage;
import com.keisa1333.armorskull.event.Mend;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public final class ArmorSkull extends JavaPlugin {

    // グローバル変数
    private String displayLore;
    private static ArmorSkull instance;

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("Hello, World");

        loadConfig();
        instance = this;

//        Bukkit.getServer().getPluginManager().registerEvents(new Anvil(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new Damage(), this);
//        Bukkit.getServer().getPluginManager().registerEvents(new Enchant(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new Mend(), this);

        getCommand("armorskull").setExecutor(new ArmorSkullCommand());
        getCommand("armorskullreload").setExecutor(new ReloadCommand(this));
    }

    @Override
    public void onDisable() {
    }

    public void reloadConfig() {
        loadConfig();
    }

    private void loadConfig () {
        // デフォルトのコンフィグファイルを生成
        saveDefaultConfig();

        // コンフィグファイルを取得
        File configFile = new File(getDataFolder(), "config.yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(configFile);

        displayLore = config.getString("displayLore", "Default");

        //displayLore = "Bar";

        // ファイルの保存
        try {
            config.save(configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArmorSkull getInstance() {
        return instance;
    }

    public String getDisplayLore() {
        return displayLore;
    }

    public void setDisplayLore(String displayLore) {
        this.displayLore = displayLore;

        // コンフィグファイルを更新
        File configFile = new File(getDataFolder(), "config.yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(configFile);
        config.set("displayLore", displayLore);

        // ファイルの保存
        try {
            config.save(configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
