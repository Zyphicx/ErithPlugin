package items;

import org.bukkit.potion.PotionEffectType;

public enum EnumAttackSpeed{
    EXTREMELY_SLOW("Extremely slow", 1500, PotionEffectType.SLOW_DIGGING, 8),
    VERY_SLOW("Very slow", 1200, PotionEffectType.SLOW_DIGGING, 6),
    SLOW("Slow", 900, PotionEffectType.SLOW_DIGGING, 4),
    AVERAGE("Average", 700, PotionEffectType.SLOW_DIGGING, 2),
    FAST("Fast", 500, null, 0),
    VERY_FAST("Very fast", 300, PotionEffectType.FAST_DIGGING, 1),
    EXTREMELY_FAST("Extremely fast", 150, PotionEffectType.FAST_DIGGING, 3);

    private String name;
    private int speed;
    private PotionEffectType swingPotionType;
    private int potionAmplifier;

    EnumAttackSpeed(String name, int speed, PotionEffectType swingPotionType, int potionAmplifier){
        this.name = name;
        this.speed = speed;
        this.swingPotionType = swingPotionType;
        this.potionAmplifier = potionAmplifier;
    }

    public String getName() {
        return name;
    }

    public int getSpeed(){
        return speed;
    }

    public PotionEffectType getSwingPotionType(){
        return swingPotionType;
    }

    public int getPotionAmplifier(){
        return potionAmplifier;
    }
}
