package com.gmail.zyphicxs;

import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.ProjectileHitEvent;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class Events implements Listener{

    @EventHandler
    public void onProjectileHit(ProjectileHitEvent event){
        Entity projectile = event.getEntity();

        if(projectile instanceof Arrow){
            if((((Arrow) projectile).getShooter() instanceof Player)){
                if(projectile.getCustomName() != null){
                    if(projectile.getCustomName().equals("grapplingHook")) {
                        Player player = (Player) ((Arrow) projectile).getShooter();
                        player.setVelocity(projectile.getLocation().subtract(player.getLocation()).toVector().divide(new Vector(6, 6, 6)));
                        projectile.remove();
                    }
                }
            }
            projectile.remove();
        }
    }
}
