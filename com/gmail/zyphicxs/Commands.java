package com.gmail.zyphicxs;

import entities.CustomEntityZombie;
import entities.EntityInfo;
import entities.EntityType;
import entities.NPC;
import items.EnumAttackSpeed;
import items.EnumItemAttribute;
import items.EnumItemQuality;
import items.Weapon;
import javafx.scene.paint.Material;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_10_R1.CraftWorld;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.CreatureSpawnEvent;
import spells.*;
import utilities.Pair;

import java.util.HashMap;
import java.util.LinkedHashMap;

public class Commands implements CommandExecutor{
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        if(!(sender instanceof Player))
            return false;

        final Player player = (Player)sender;

        if(cmd.getName().equalsIgnoreCase("grapple")) {
            RopedArrowSpell.spellInit(player, 1);
        }

        if(cmd.getName().equalsIgnoreCase("homingarrow") && args.length == 1){
            HomingArrowSpell.spellInit(player, Integer.parseInt(args[0]));
        }

        if(cmd.getName().equalsIgnoreCase("grenade") && args.length == 1){
            GrenadeSpell.spellInit(player, Integer.parseInt(args[0]));
        }

        if(cmd.getName().equalsIgnoreCase("cogdrive") && args.length == 1){
            CogDriveSpell.spellInit(player, Integer.parseInt(args[0]));
        }

        if(cmd.getName().equalsIgnoreCase("turret") && args.length == 1){
            TurretSpell.spellInit(player, Integer.parseInt(args[0]));
        }

        if(cmd.getName().equalsIgnoreCase("doFriend") && args.length == 1){
            Bukkit.getPlayer(args[0]).performCommand("friend " + args[0]);
        }

        if(cmd.getName().equalsIgnoreCase("friend") && args.length == 1){
            final NPC entityPlayer = new NPC(player, Bukkit.getOfflinePlayer(args[0]).getUniqueId(), args[0]);
            ((CraftWorld)player.getWorld()).addEntity(entityPlayer, CreatureSpawnEvent.SpawnReason.CUSTOM);
            Location loc = player.getLocation();
            entityPlayer.setPosition(loc.getX(), loc.getY(), loc.getZ());

            Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.instance, new Runnable() {
                public void run() {
                    //entityPlayer.setPositionRotation(player.getLocation().getX(), player.getLocation().getY(), -558 + (-557 - player.getLocation().getZ()), 360 - player.getLocation().getYaw(), player.getLocation().getPitch());
                    entityPlayer.setLocation(player.getLocation().getX(), player.getLocation().getY(), -558 + (-557 - player.getLocation().getZ()), player.getLocation().getYaw() + 180, player.getLocation().getPitch());
                    entityPlayer.setSneaking(player.isSneaking() ? true : false);
                }

            }, 1, 1);
        }

        if(cmd.getName().equalsIgnoreCase("spawnZombie") && args.length == 1){
            EntityType.spawnEntity(new CustomEntityZombie(player.getWorld(), player.getLocation(), EntityInfo.mobs.get(args[0])), player.getLocation());
        }

        if(cmd.getName().equalsIgnoreCase("giveItem")){
            LinkedHashMap<EnumItemAttribute, Integer> attributes = new LinkedHashMap<EnumItemAttribute, Integer>();
            attributes.put(EnumItemAttribute.BLOOD_LUST, 20);
            attributes.put(EnumItemAttribute.WALK_SPEED, 58);
            attributes.put(EnumItemAttribute.SWAG_FACTOR, -9000);

            player.getInventory().addItem(new Weapon("Coolio", org.bukkit.Material.DIAMOND_HOE, EnumItemQuality.ANCIENT, new Pair<Integer, Integer>(420, 1337), 91, EnumAttackSpeed.EXTREMELY_FAST, attributes));
        }

        return true;
    }
}
