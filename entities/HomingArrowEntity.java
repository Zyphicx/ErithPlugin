package entities;

import com.gmail.zyphicxs.Main;
import net.minecraft.server.v1_10_R1.EnumParticle;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;
import utilities.Utils;

public class HomingArrowEntity{
    int arrowLoopID = 0;
    LivingEntity target;
    Arrow arrow;
    Location startingLocation;

    public HomingArrowEntity(final Player player, final int distance){
        arrow = player.getWorld().spawnArrow(player.getEyeLocation(), player.getEyeLocation().getDirection(), 3f, 0.1f);
        arrow.setShooter(player);
        startingLocation = arrow.getLocation();

        target = (LivingEntity)Utils.getEntityInSight(player, distance);
        Utils.playParticlesLocation(arrow.getLocation(), 64, EnumParticle.CRIT, 0, 0, 0, 0, 10, null);

        arrowLoopID = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.instance, new Runnable() {
            public void run() {
                if (arrow.isOnGround() || arrow.isDead() || arrow == null || startingLocation.distance(arrow.getLocation()) > distance) {
                    arrow.remove();
                    Bukkit.getScheduler().cancelTask(arrowLoopID);
                }

                if (target == null && arrow.getNearbyEntities(8, 8, 8) != null) {
                    for (Entity toTrack : arrow.getNearbyEntities(8, 8, 8)) {
                        if (!toTrack.equals(arrow.getShooter()) && toTrack instanceof LivingEntity) {
                            target = (LivingEntity)toTrack;
                            continue;
                        }
                    }
                }
                if (target != null) {
                    Vector arrowVector = arrow.getVelocity().add(target.getLocation().add(0, target.getEyeHeight(), 0).subtract(arrow.getLocation()).toVector()).normalize().multiply(1.35);
                    arrow.setVelocity(arrowVector);
                }
                Utils.playParticlesLocation(arrow.getLocation(), 64, EnumParticle.CRIT, 0, 0, 0, 0, 10, null);
            }
        }, 3, 2);
    }
}
