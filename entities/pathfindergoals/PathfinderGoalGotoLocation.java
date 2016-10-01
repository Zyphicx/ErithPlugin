package entities.pathfindergoals;

import net.minecraft.server.v1_10_R1.EntityCreature;
import net.minecraft.server.v1_10_R1.PathfinderGoal;
import org.bukkit.Bukkit;

public class PathfinderGoalGotoLocation extends PathfinderGoal{
    private final EntityCreature a;
    private final double targetX;
    private final double targetY;
    private final double targetZ;
    private final double speed;

    public PathfinderGoalGotoLocation(EntityCreature var1, double locX, double locY, double locZ, double speed) {
        this.a = var1;
        this.targetX = locX;
        this.targetY = locY;
        this.targetZ = locZ;
        this.speed = speed;
    }

    @Override
    public boolean a(){
        if(a.getGoalTarget() == null){
            return true;
        }
        return false;
    }

    @Override
    public boolean b() {
        return !this.a.getNavigation().n();
    }

    @Override
    public void c(){
        this.a.getNavigation().a(this.targetX, this.targetY, this.targetZ, this.speed);
    }
}
