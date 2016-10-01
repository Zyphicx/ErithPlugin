package entities;

import com.gmail.zyphicxs.Main;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.EulerAngle;

public class TurretEntity{
    static final int range[] = {7, 8, 10, 10, 10};
    long timeLastShot = 0;

    ArmorStand armourStand;
    static final ItemStack head = new ItemStack(Material.DISPENSER);

    public TurretEntity(final Player player, final int tier) {
        Location loc = player.getLocation();

        loc.setPitch(0);
        loc.setYaw(0);
        loc.setX((loc.getX() - loc.getX() % 1) + ((loc.getX() >= 0) ? 0.5 : -0.5));
        loc.setZ((loc.getZ() - loc.getZ() % 1) + ((loc.getZ() >= 0) ? 0.5 : -0.5));

        armourStand = player.getWorld().spawn(loc, ArmorStand.class);
        armourStand.setHelmet(head);
        armourStand.setInvulnerable(true);
        armourStand.setVisible(false);
        armourStand.setCustomName(ChatColor.RED + player.getDisplayName() + "'s turret");
        armourStand.setCustomNameVisible(true);

        new BukkitRunnable(){
            public void run () {
                if(armourStand.isDead() || player.isDead()){
                    armourStand.remove();
                    this.cancel();
                }

                Location armourStandLoc = armourStand.getLocation();

                for(Entity entity : armourStand.getNearbyEntities(10, 10, 10)){
                    if(armourStand.getLocation().distance(entity.getLocation()) <= range[tier - 1] && entity instanceof LivingEntity && !entity.equals(player)){
                        Location entityLoc = entity.getLocation();

                        armourStand.setHeadPose(new EulerAngle(Math.asin((entityLoc.getY() - armourStandLoc.getY()) / armourStandLoc.distance(entityLoc)) * -1,
                                (entityLoc.getX() - armourStandLoc.getX() >= 0 ? (2 * Math.PI) - Math.acos((entityLoc.getZ() - armourStandLoc.getZ()) / armourStandLoc.distance(entityLoc)) :
                                        Math.acos((entityLoc.getZ() - armourStandLoc.getZ()) / armourStandLoc.distance(entityLoc))), 0));

                        player.sendMessage("Angle: " + (entityLoc.getX() - armourStandLoc.getX() >= 0 ? (2 * Math.PI) - Math.acos((entityLoc.getZ() - armourStandLoc.getZ()) / armourStandLoc.distance(entityLoc)) :
                                Math.acos((entityLoc.getZ() - armourStandLoc.getZ()) / armourStandLoc.distance(entityLoc))));

                        switch(tier) {
                            case 1:
                                if (timeLastShot + 2000 <= System.currentTimeMillis()) {
                                    Arrow turretArrow = player.getWorld().spawnArrow(armourStand.getEyeLocation(), entityLoc.subtract(armourStand.getEyeLocation()).toVector(), 4f, 0f);
                                    turretArrow.setShooter(armourStand);
                                    turretArrow.setCustomName("turretArrow");
                                    timeLastShot = System.currentTimeMillis();
                                }
                                break;
                            case 2:
                                if (timeLastShot + 1000 <= System.currentTimeMillis()) {
                                    Arrow turretArrow = player.getWorld().spawnArrow(armourStand.getEyeLocation(), entityLoc.subtract(armourStand.getEyeLocation()).toVector(), 4f, 0f);
                                    turretArrow.setShooter(armourStand);
                                    turretArrow.setCustomName("turretArrow");
                                    timeLastShot = System.currentTimeMillis();
                                }
                                break;
                            case 3:
                                if (timeLastShot + 1000 <= System.currentTimeMillis()) {
                                    Arrow turretArrow = player.getWorld().spawnArrow(armourStand.getEyeLocation(), entityLoc.subtract(armourStand.getEyeLocation()).toVector(), 4f, 0f);
                                    turretArrow.setShooter(armourStand);
                                    turretArrow.setCustomName("turretArrow");
                                    timeLastShot = System.currentTimeMillis();
                                }
                                break;
                            case 4:
                                if (timeLastShot + 1000 <= System.currentTimeMillis()) {
                                    Arrow turretArrow = player.getWorld().spawnArrow(armourStand.getEyeLocation(), entityLoc.subtract(armourStand.getEyeLocation()).toVector(), 4f, 0f);
                                    turretArrow.setShooter(armourStand);
                                    turretArrow.setCustomName("turretArrow");
                                    timeLastShot = System.currentTimeMillis();
                                }
                                break;
                            case 5:
                                if (timeLastShot + 1000 <= System.currentTimeMillis()) {
                                    Arrow turretArrow = player.getWorld().spawnArrow(armourStand.getEyeLocation(), entityLoc.subtract(armourStand.getEyeLocation()).toVector(), 4f, 0f);
                                    turretArrow.setShooter(armourStand);
                                    turretArrow.setCustomName("turretArrow");
                                    timeLastShot = System.currentTimeMillis();
                                }
                                break;
                        }

                        break;
                    }
                }

            }
        }.runTaskTimer(Main.instance, 0, 1);
    }
}
