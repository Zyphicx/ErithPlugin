package items;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import utilities.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class Weapon extends ItemStack{

    private String itemName;
    private Material material;
    private EnumItemQuality quality;
    private Pair<Integer, Integer> damage;
    private int level;
    private List<String> lore = new ArrayList<String>();
    private EnumAttackSpeed attackSpeed;
    private LinkedHashMap<EnumItemAttribute, Integer> attributes = new LinkedHashMap();

    public Weapon(String itemName, Material material, EnumItemQuality quality, Pair<Integer, Integer> damage, int level, EnumAttackSpeed attackSpeed, LinkedHashMap<EnumItemAttribute, Integer> attributes){
        this.itemName = itemName;
        this.material = material;
        this.quality = quality;
        this.level = level;
        this.damage = damage;
        this.attackSpeed = attackSpeed;
        this.attributes = attributes;

        this.setType(material);
        this.setAmount(1);
        ItemMeta meta = this.getItemMeta();
        meta.setDisplayName(quality.colour + itemName);
        lore.add(ChatColor.GRAY + "Quality: " + quality.colour + quality.getName());
        lore.add(ChatColor.GRAY + "Level: " + ChatColor.WHITE + level);
        lore.add(ChatColor.GRAY + "Damage: " + ChatColor.GOLD + damage.getKey() + "-" + damage.getValue());
        lore.add(ChatColor.GRAY + "Attack speed: " + ChatColor.WHITE + attackSpeed.getName());
        lore.add("");

        lore.add(ChatColor.GREEN + "" + ChatColor.BOLD + "Positives");
        for(EnumItemAttribute key : attributes.keySet()) {
            if(attributes.get(key) > 0)
                lore.add(ChatColor.GREEN + "- " + ChatColor.GRAY + attributes.get(key) + key.getSuffix() + " " + key.getAttributeName());
        }

        lore.add("");

        lore.add(ChatColor.RED + "" + ChatColor.BOLD + "Negatives");
        for(EnumItemAttribute key : attributes.keySet()) {
            if(attributes.get(key) < 0)
                lore.add(ChatColor.RED + "- " + ChatColor.GRAY + attributes.get(key) + key.getSuffix() + " " + key.getAttributeName());
        }
        meta.setLore(lore);
        meta.spigot().setUnbreakable(true);
        meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        this.setItemMeta(meta);
    }
}
