package com.keisa1333.armorskull.command.armorskull;

import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Help implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String a, String[] args) {
        if (args.length == 1 || args[1].equalsIgnoreCase("1")) {
            helpList1(sender);
            return true;
        }

        if (args[1].equalsIgnoreCase("2")) {
            helpList2(sender);
            return true;
        }
        return true;
    }


    public void helpList1(CommandSender sender) {
        Audience player = (Audience) sender;
        MiniMessage mm = MiniMessage.miniMessage();

        Component top = mm.deserialize("<yellow>ArmorSkull Help 1/2<gray><hover:show_text:\"<blue>このように説明が表示されます。\">(Hover Text)");

        Component content1 = mm.deserialize("<gray><hover:show_text:\"<blue>手に持ってる頭に金床で修復可能なアイテムを追加します。\nidを入力した後、修復アイテムを持って再度実行することで登録されます。\n/as settingから金床修復可に設定するのを忘れないように！\"><click:suggest_command:/armorskull anvilItem >/as anvilItem <id>");

        Component content2 = mm.deserialize("<gray><hover:show_text:\"<blue>手に持ってる頭をArmor仕様にします。\n<durability>は設定する耐久値です。\nその後、/as settingから詳細な設定ができます。\"><click:suggest_command:/armorskull create >/as create <durability>");

        Component content3 = mm.deserialize("<gray><hover:show_text:\"<blue>防具耐久値が減るダメージソースを編集できます。\nデフォルトでは通常の防具と同じ設定です。\nダメージソースは複数追加できます。\n設定項目は、クリックで出てくるリンクを参照してください。\"><click:open_url:https://hub.spigotmc.org/javadocs/spigot/org/bukkit/event/entity/EntityDamageEvent.DamageCause.html>/as damage (clear|default|add <damege cause>)");

        Component content4 = mm.deserialize("<gray><hover:show_text:\"<blue>耐久値の表記を変更することができます。\n(default, number, japanese, bar)\"><click:suggest_command:/armorskull displayLore >/as displayLore <type>");

        Component content5 = mm.deserialize("<gray><hover:show_text:\"<blue>手に持ってる頭の最大耐久値を設定できます。\n<durability>は設定する耐久値です。\n耐久値説明文を上書きした際は、repairコマンドを使用するなどすれば再表示されます。\"><click:suggest_command:/armorskull durability >/as durability <durability>");

        Component content6 = mm.deserialize("<gray><hover:show_text:\"<blue>手に持ってる頭にエンチャを付与します。\n<type>はエンチャ名、[<level>]はレベル。\n[<level>]を入力しなかった場合、レベル1で設定されます。\"><click:suggest_command:/armorskull enchant >/as enchant (clear|<type> [<level>])");

        Component content7 = mm.deserialize("<gray><hover:show_text:\"<blue>armorskullコマンドのヘルプを参照できます。\"><click:suggest_command:/armorskull help>/as help");

        Component next = mm.deserialize("<yellow><click:run_command:/armorskull help 2>[NEXT]");

        player.sendMessage(top);
        player.sendMessage(content1);
        player.sendMessage(content2);
        player.sendMessage(content3);
        player.sendMessage(content4);
        player.sendMessage(content5);
        player.sendMessage(content6);
        player.sendMessage(content7);
        player.sendMessage(next);
    }

    public void helpList2(CommandSender sender) {
        Audience player = (Audience) sender;
        MiniMessage mm = MiniMessage.miniMessage();

        Component top = mm.deserialize("<yellow>ArmorSkull Help 2/2<gray><hover:show_text:\"<blue>このように説明が表示されます。\">(Hover Text)");

        Component content1 = mm.deserialize("<gray><hover:show_text:\"<blue>手に持ってる頭の情報を確認できます。\"><click:suggest_command:/armorskull info>/as info");

        Component content2 = mm.deserialize("<gray><hover:show_text:\"<blue>手に持ってる頭に属性を追加できます。\n<attribute> (maxhealth, speed, knockback, armor, toughness)\n<amount>属性の値\n<scalar> (0 (加算),1 (乗算),2 (2倍乗算))\"><click:suggest_command:/armorskull modify >/as modify (clear|<attribute> <amount> <scalar>)");

        Component content3 = mm.deserialize("<gray><hover:show_text:\"<blue>手に持ってる頭のArmor仕様を削除します。\"><click:suggest_command:/armorskull remove>/as remove");

        Component content4 = mm.deserialize("<gray><hover:show_text:\"<blue>手に持ってる頭の名前を変更できます。\n<name>は設定する名前です。\nカラーコードの接頭辞は&、#です。\n&の場合は1~0、a~f、nmkなどがあります。\n#の場合、六桁のrgbカラーコードをお使いください。\"><click:suggest_command:/as rename >/armorskull rename (clear|<name>)");

        Component content5 = mm.deserialize("<gray><hover:show_text:\"<blue>手に持ってる頭の耐久値を増減できます。\n<number>は設定する増減値です。\n正の値も負の値も対応しています。\"><click:suggest_command:/as repair >/armorskull repair <number>");

        Component content6 = mm.deserialize("<gray><hover:show_text:\"<blue>手に持ってる頭を詳細に設定できます。\n設定方法はチャットクリックです。\"><click:suggest_command:/armorskull setting>/as setting");

        Component content7 = mm.deserialize("<gray><hover:show_text:\"<blue>yamlで変更した情報を読み込むためにリロードできます。\"><click:suggest_command:/armorskullreload>/armorskullreload");

        Component previous = mm.deserialize("<yellow><click:run_command:/armorskull help>[PREVIOUS]");

        player.sendMessage(top);
        player.sendMessage(content1);
        player.sendMessage(content2);
        player.sendMessage(content3);
        player.sendMessage(content4);
        player.sendMessage(content5);
        player.sendMessage(content6);
        player.sendMessage(content7);
        player.sendMessage(previous);
    }
}

