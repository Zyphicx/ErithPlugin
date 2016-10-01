package entities;

import net.minecraft.server.v1_10_R1.EntityZombie;
import net.minecraft.server.v1_10_R1.GenericAttributes;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_10_R1.CraftWorld;
import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class CustomEntityZombie extends EntityZombie{
    EntityInfo info;

    public CustomEntityZombie(org.bukkit.World world, Location loc, EntityInfo info) {
        super(((CraftWorld)world).getHandle());
        this.setPosition(loc.getX(), loc.getY(), loc.getZ());
        this.info = info;
        setEntityInfo();
    }

    private void setEntityInfo(){
        this.setCustomName(info.behaviour.getNameColour() + info.customName);
        this.setHealth(info.health);
        this.getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).setValue(info.speed);
        Behaviour.applyBehaviour(this, info.behaviour);

        ((LivingEntity)this.getBukkitEntity()).getEquipment().setHelmet(info.helmet);
        ((LivingEntity)this.getBukkitEntity()).getEquipment().setChestplate(info.chest);
        ((LivingEntity)this.getBukkitEntity()).getEquipment().setLeggings(info.leggings);
        ((LivingEntity)this.getBukkitEntity()).getEquipment().setBoots(info.boots);
        ((LivingEntity)this.getBukkitEntity()).getEquipment().setItemInMainHand(info.mainhand);
        ((LivingEntity)this.getBukkitEntity()).getEquipment().setItemInOffHand(info.offhand);

        if(info.invisible){((LivingEntity)this.getBukkitEntity()).addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 500000000, 1, true, false));}
        this.setBaby(info.baby);
    }

}
