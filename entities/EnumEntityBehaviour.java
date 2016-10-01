package entities;


import org.bukkit.ChatColor;

public enum EnumEntityBehaviour {
    PASSIVE(ChatColor.GREEN),
    MELEE_NEUTRAL(ChatColor.YELLOW),
    NEUTRAL_BERSERK(ChatColor.GOLD),
    RANGE_NEUTRAL(ChatColor.YELLOW),
    MELEE_AGGRESSIVE(ChatColor.RED),
    RANGE_AGGRESSIVE(ChatColor.RED),
    GUARD(ChatColor.AQUA);

    ChatColor nameColour;

    EnumEntityBehaviour(ChatColor nameColour){
        this.nameColour = nameColour;
    }

    public ChatColor getNameColour(){
        return nameColour;
    }
}