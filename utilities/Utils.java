package utilities;

import net.minecraft.server.v1_10_R1.EnumParticle;
import net.minecraft.server.v1_10_R1.PacketPlayOutWorldParticles;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_10_R1.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by Aztro on 25/09/2016.
 */
public class Utils {
    public static void playParticles(Player p, Location loc, EnumParticle particle, float offX, float offY, float offZ, int particleData, int count, int[] data){
        PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles(particle, false, (float)loc.getX(), (float)loc.getY(), (float)loc.getZ(), offX, offY, offZ, particleData, count, data);
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packet);
    }
    public static void playParticlesLocation(Location loc, int radius, EnumParticle particle, float offX, float offY, float offZ, int particleData, int count, int[] data){
        for(Player player : loc.getWorld().getPlayers()){
            if(player.getLocation().distanceSquared(loc) <= radius * radius){
                playParticles(player, loc, particle, offX, offY, offZ, particleData, count, data);
            }
        }
    }
    public static Object getPrivateField(String fieldName, Class clazz, Object object){
        Field field;
        Object o = null;

        try{
            field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
            o = field.get(object);
        }catch(NoSuchFieldException e){
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return o;
    }



    public static Entity getEntityInSight(Player player, int range){
        List<Entity> entities = player.getNearbyEntities(range, range, range);

        Iterator<Entity> iterator = entities.iterator();
        while(iterator.hasNext()){
            Entity entityNext = iterator.next();
            if(entityNext.equals(player) || !(entityNext instanceof LivingEntity) || entityNext == null){
                iterator.remove();
            }
        }
        List<Block> blocks = player.getLineOfSight((Set)null, range);
        for(Block block : blocks){
            if(block.getType() != Material.AIR){
                break;
            }

            Location middle = block.getLocation();

            for(Entity entity : entities){
                if(entity.getLocation().distance(middle) <= 2.5){
                    return entity;
                }
            }
        }
        return null;
    }

    public static boolean hasBlocksInWay(Entity entity, Vector vector){
        if(entity.getLocation().add(vector.getX() + 0.4, 0, 0).getBlock().getType().isSolid() || entity.getLocation().add(0, 0, vector.getZ() + 0.4).getBlock().getType().isSolid() ||
                entity.getLocation().add(vector.getX() - 0.4, 0, 0).getBlock().getType().isSolid() || entity.getLocation().add(0, 0, vector.getZ() - 0.4).getBlock().getType().isSolid()){
            return true;
        }
        return false;
    }

    //Item utilities


    private static HashSet<String> materialList = new HashSet<String>();

    static{
        for(Material material : Material.values()){
            materialList.add(material.toString());
        }
    }

    public static ItemStack strToItem(String item){
        if(materialList.contains(item)) {
            return new ItemStack(Material.valueOf(item));
        }
        return new ItemStack(Material.AIR);
    }

}
