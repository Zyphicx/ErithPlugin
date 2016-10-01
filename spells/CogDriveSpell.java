package spells;

import com.gmail.zyphicxs.Main;
import net.minecraft.server.v1_10_R1.EnumParticle;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import utilities.Utils;

public class CogDriveSpell{
    static final int manaCost[] = {35, 35, 35, 35, 35};
    static final int driveTimeMilliseconds[] = {1500, 2000, 3250, 4500, /*7500*/20000};
    static final int particleDataDirt[] = {3};
    static final int particleDataStone[] = {1};

    public static void spellInit(final Player player, final int tier){
        if((player.getLevel() - manaCost[tier - 1]) < 0){
            player.sendMessage(ChatColor.RED + "You need more energy to use this skill");
            return;
        }
        player.setLevel(player.getLevel() - manaCost[tier - 1]);

        spellAction(player, tier);
    }

    public static void spellAction(final Player player, final int tier) {
        final long runTimeStart = System.currentTimeMillis();
        final Vector initialDirection = new Vector(Math.cos(Math.toRadians(player.getLocation().getYaw() + 90)), -1, Math.sin(Math.toRadians(player.getLocation().getYaw() + 90))).normalize();

        new BukkitRunnable(){
            public void run(){
                if((runTimeStart + driveTimeMilliseconds[tier - 1]) < System.currentTimeMillis()){
                    this.cancel();
                }

                switch(tier){
                    case 1:
                        player.setVelocity(initialDirection);
                        if(Utils.hasBlocksInWay(player, player.getVelocity()))
                            player.setVelocity(player.getVelocity().setY(0.3));
                        break;
                    case 2:
                        player.setVelocity(initialDirection);
                        if(Utils.hasBlocksInWay(player, player.getVelocity()))
                            player.setVelocity(player.getVelocity().setY(0.3));
                    break;
                    case 3:
                        player.setVelocity(new Vector(Math.cos(Math.toRadians(player.getLocation().getYaw() + 90)), -1, Math.sin(Math.toRadians(player.getLocation().getYaw() + 90))).normalize());
                        if(Utils.hasBlocksInWay(player, player.getVelocity()))
                            player.setVelocity(player.getVelocity().setY(0.3));
                        break;
                    case 4:
                        player.setVelocity(new Vector(Math.cos(Math.toRadians(player.getLocation().getYaw() + 90)), -1, Math.sin(Math.toRadians(player.getLocation().getYaw() + 90))).normalize());
                        if(Utils.hasBlocksInWay(player, player.getVelocity()))
                            player.setVelocity(player.getVelocity().setY(0.3));
                        break;
                    case 5:
                        player.setVelocity(new Vector(Math.cos(Math.toRadians(player.getLocation().getYaw() + 90)), -1, Math.sin(Math.toRadians(player.getLocation().getYaw() + 90))).normalize());
                        if(Utils.hasBlocksInWay(player, player.getVelocity()))
                            player.setVelocity(player.getVelocity().setY(0.3));
                        break;
                }

                Utils.playParticlesLocation(player.getLocation(), 64, EnumParticle.BLOCK_CRACK, 0, 0.1f, 0, 0, 75, particleDataDirt);
                Utils.playParticlesLocation(player.getLocation(), 64, EnumParticle.BLOCK_CRACK, 0, 0.1f, 0, 0, 25, particleDataStone);
            }
        }.runTaskTimer(Main.instance, 0, 1);

        spellEnd(player, tier);
    }

    public static void spellEnd(Player player, final int tier){
    }
}
