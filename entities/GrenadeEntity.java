package entities;

import com.gmail.zyphicxs.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class GrenadeEntity{
    Item itemEntity;
    ItemStack item = new ItemStack(Material.FIREWORK_CHARGE, 1);
    int itemLoopID;

    public GrenadeEntity(Player player, int tier){
        itemEntity = player.getWorld().dropItem(player.getEyeLocation(), item);
        itemEntity.setPickupDelay(50000);
        itemEntity.setVelocity(player.getEyeLocation().getDirection());

        itemLoopID = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.instance, new Runnable() {
            public void run(){
                if(itemEntity.isOnGround()){
                    itemEntity.getWorld().playSound(itemEntity.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1, 0);
                    itemEntity.remove();
                    Bukkit.getScheduler().cancelTask(itemLoopID);
                }
            }
        }, 0, 2);
    }
}
