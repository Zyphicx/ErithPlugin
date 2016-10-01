package entities;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import utilities.Utils;

import java.io.File;
import java.util.HashMap;


public class EntityInfo{
    public static HashMap<String, EntityInfo> mobs;

    MobFile file;
    public String customName;
    public EntityType type;
    public int health;
    public double speed;
    public EnumEntityBehaviour behaviour;
    public int viewdistance;
    public ItemStack helmet;
    public ItemStack chest;
    public ItemStack leggings;
    public ItemStack boots;
    public ItemStack mainhand;
    public ItemStack offhand;
    public boolean invisible;
    public boolean baby;

    public EntityInfo(File file){
        this.file = new MobFile(YamlConfiguration.loadConfiguration(file));
        loadFileContent();
    }

    private void loadFileContent(){
        customName = file.getString("customName");
        type = EntityType.valueOf("CUSTOM_" + file.getString("type").toUpperCase());
        health = file.getInt("health");
        speed = file.getDouble("speed");
        behaviour = EnumEntityBehaviour.valueOf(file.getString("behaviour").toUpperCase());

        helmet = Utils.strToItem(file.getString("equipment.helmet"));
        chest = Utils.strToItem(file.getString("equipment.chest"));
        leggings = Utils.strToItem(file.getString("equipment.leggings"));
        boots = Utils.strToItem(file.getString("equipment.boots"));
        mainhand = Utils.strToItem(file.getString("equipment.mainhand"));
        offhand = Utils.strToItem(file.getString("equipment.offhand"));

        invisible = file.getBoolean("invisible");
        baby = file.getBoolean("baby");
    }

}
