package items;

import org.bukkit.ChatColor;

public enum EnumItemQuality {
    COMMON("Common", ChatColor.YELLOW, 10000),
    MAGICAL("Magical", ChatColor.AQUA, 2000),
    EPIC("Epic", ChatColor.GREEN, 1000),
    MYTHICAL("Mythical", ChatColor.DARK_PURPLE, 200),
    ANCIENT("Ancient", ChatColor.GOLD, 1);

    String name;
    ChatColor colour;
    int dropChance;

    EnumItemQuality(String name, ChatColor colour, int dropChance){
        this.name = name;
        this.colour = colour;
        this.dropChance = dropChance;
    }

    public String getName(){
        return name;
    }

    public ChatColor getColour(){
        return colour;
    }

    public int getDropChance(){
        return dropChance;
    }
}
