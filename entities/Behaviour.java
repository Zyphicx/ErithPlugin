package entities;

import com.google.common.collect.Sets;
import entities.pathfindergoals.PathfinderGoalGotoLocation;
import net.minecraft.server.v1_10_R1.*;

import java.lang.reflect.Field;

public class Behaviour{
    public static void applyBehaviour(EntityCreature entity, EnumEntityBehaviour behaviour){
        if(behaviour.equals(EnumEntityBehaviour.PASSIVE)){
            clearPathfinders(entity);
            applyPassive(entity);
        }
        else if(behaviour.equals(EnumEntityBehaviour.MELEE_NEUTRAL)){
            clearPathfinders(entity);
            applyMeleeNeutral(entity);
        }
        else if(behaviour.equals(EnumEntityBehaviour.NEUTRAL_BERSERK)){
            clearPathfinders(entity);
            applyMeleeNeutral(entity);
        }
        else if(behaviour.equals(EnumEntityBehaviour.MELEE_AGGRESSIVE)){
            clearPathfinders(entity);
            applyMeleeAggressive(entity);
        }
        else if(behaviour.equals(EnumEntityBehaviour.GUARD)){
            clearPathfinders(entity);
            applyGuard(entity);
        }
    }

    public static void clearPathfinders(EntityCreature entity){
        try {
        Field bField = PathfinderGoalSelector.class.getDeclaredField("b");
        bField.setAccessible(true);
        Field cField = PathfinderGoalSelector.class.getDeclaredField("c");
        cField.setAccessible(true);
        bField.set(entity.goalSelector, Sets.newLinkedHashSet());
        bField.set(entity.targetSelector, Sets.newLinkedHashSet());
        cField.set(entity.goalSelector, Sets.newLinkedHashSet());
        cField.set(entity.targetSelector, Sets.newLinkedHashSet());
    } catch (Exception exc) {
        exc.printStackTrace();
    }
    }

    public static void applyPassive(EntityCreature entity){
        entity.goalSelector.a(0, new PathfinderGoalFloat(entity));
        entity.goalSelector.a(1, new PathfinderGoalPanic(entity, 1.5));
        entity.goalSelector.a(2, new PathfinderGoalRandomLookaround(entity));
        entity.goalSelector.a(3, new PathfinderGoalLookAtPlayer(entity, EntityHuman.class, 6.0F));
        entity.goalSelector.a(4, new PathfinderGoalRandomStroll(entity, 0.5));
    }

    public static void applyMeleeNeutral(EntityCreature entity){
        entity.goalSelector.a(0, new PathfinderGoalFloat(entity));
        entity.goalSelector.a(1, new PathfinderGoalMeleeAttack(entity, 1.0D, true));
        entity.goalSelector.a(2, new PathfinderGoalLookAtPlayer(entity, EntityHuman.class, 6.0F));
        entity.goalSelector.a(3, new PathfinderGoalRandomStroll(entity, 0.5));
        entity.goalSelector.a(4, new PathfinderGoalRandomLookaround(entity));
        entity.targetSelector.a(1, new PathfinderGoalHurtByTarget(entity, true));
    }

    public static void applyNeutralBerserk(EntityCreature entity){
        entity.goalSelector.a(0, new PathfinderGoalFloat(entity));
        entity.goalSelector.a(1, new PathfinderGoalMeleeAttack(entity, 2.0D, true));
        entity.goalSelector.a(2, new PathfinderGoalLookAtPlayer(entity, EntityHuman.class, 6.0F));
        entity.goalSelector.a(3, new PathfinderGoalRandomStroll(entity, 0.5));
        entity.goalSelector.a(4, new PathfinderGoalRandomLookaround(entity));
        entity.targetSelector.a(1, new PathfinderGoalHurtByTarget(entity, true));
    }

    public static void applyMeleeAggressive(EntityCreature entity){
        entity.goalSelector.a(0, new PathfinderGoalFloat(entity));
        entity.goalSelector.a(1, new PathfinderGoalMeleeAttack(entity, 1.0D, true));
        entity.goalSelector.a(2, new PathfinderGoalLookAtPlayer(entity, EntityHuman.class, 6.0F));
        entity.goalSelector.a(3, new PathfinderGoalRandomStroll(entity, 0.5));
        entity.goalSelector.a(4, new PathfinderGoalRandomLookaround(entity));
        entity.targetSelector.a(1, new PathfinderGoalHurtByTarget(entity, true));
        entity.targetSelector.a(2, new PathfinderGoalNearestAttackableTarget(entity, EntityHuman.class, true));
    }

    public static void applyGuard(EntityCreature entity){
        entity.goalSelector.a(0, new PathfinderGoalFloat(entity));
        entity.goalSelector.a(1, new PathfinderGoalMeleeAttack(entity, 1.0D, true));
        entity.goalSelector.a(2, new PathfinderGoalGotoLocation(entity, entity.locX, entity.locY, entity.locZ, 1));
        entity.goalSelector.a(3, new PathfinderGoalRandomLookaround(entity));
        entity.goalSelector.a(4, new PathfinderGoalLookAtPlayer(entity, EntityHuman.class, 6.0F));
        entity.targetSelector.a(1, new PathfinderGoalNearestAttackableTarget(entity, EntityHuman.class, true));
    }
}
