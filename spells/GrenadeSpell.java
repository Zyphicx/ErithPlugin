package spells;

import entities.GrenadeEntity;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class GrenadeSpell{
    static int manaCost[] = {35, 35, 35, 35, 35};

    public static void spellInit(Player player, final int tier){
        if((player.getLevel() - manaCost[tier - 1]) < 0){
            player.sendMessage(ChatColor.RED + "You need more energy to use this skill");
            return;
        }
        player.setLevel(player.getLevel() - manaCost[tier - 1]);

        spellAction(player, tier);
    }

    public static void spellAction(Player player, final int tier) {
        new GrenadeEntity(player, tier);

        spellEnd(player, tier);
    }

    public static void spellEnd(Player player, final int tier){
    }
}
