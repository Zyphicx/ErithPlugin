package entities;

import net.minecraft.server.v1_10_R1.Entity;

import java.util.Map;

import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_10_R1.CraftWorld;
import utilities.Utils;

public enum EntityType {
    CUSTOM_ZOMBIE("Zombie", 54, CustomEntityZombie.class);

    EntityType(String name, int ID, Class<? extends Entity> customClass){
        addToMaps(name, ID, customClass);
    }

    public static void spawnEntity(Entity entity, Location loc){
        entity.setPosition(loc.getX(), loc.getY(), loc.getZ());
        ((CraftWorld)loc.getWorld()).getHandle().addEntity(entity);
    }

    private static void addToMaps(String name, int ID, Class<? extends Entity> customClass){
        ((Map)Utils.getPrivateField("c", net.minecraft.server.v1_10_R1.EntityTypes.class, null)).put(name, customClass);
        ((Map)Utils.getPrivateField("d", net.minecraft.server.v1_10_R1.EntityTypes.class, null)).put(customClass, name);
        //((Map)getPrivateField("e", net.minecraft.server.v1_7_R4.EntityType.class, null)).put(Integer.valueOf(id), clazz);
        ((Map)Utils.getPrivateField("f", net.minecraft.server.v1_10_R1.EntityTypes.class, null)).put(customClass, Integer.valueOf(ID));
        //((Map)getPrivateField("g", net.minecraft.server.v1_7_R4.EntityType.class, null)).put(name, Integer.valueOf(id));
    }
}
