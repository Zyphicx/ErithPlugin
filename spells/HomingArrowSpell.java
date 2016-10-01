package spells;

import entities.HomingArrowEntity;
import org.bukkit.ChatColor;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;

public class HomingArrowSpell{
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
        switch(tier){
            case 1:
                new HomingArrowEntity(player, 20);
                break;
            case 2:
                new HomingArrowEntity(player, 30);
                break;
            case 3:
                new HomingArrowEntity(player, 50);
                break;
            case 4:
                new HomingArrowEntity(player, 50);
                break;
            case 5:
                new HomingArrowEntity(player, 60);
                break;
        }

        spellEnd(player, tier);
    }

    public static void spellEnd(Player player, final int tier){
    }
}
