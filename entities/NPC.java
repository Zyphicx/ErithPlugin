package entities;

import com.mojang.authlib.GameProfile;
import net.minecraft.server.v1_10_R1.EntityPlayer;
import net.minecraft.server.v1_10_R1.EnumProtocolDirection;
import net.minecraft.server.v1_10_R1.PlayerConnection;
import net.minecraft.server.v1_10_R1.PlayerInteractManager;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_10_R1.CraftServer;
import org.bukkit.craftbukkit.v1_10_R1.CraftWorld;
import org.bukkit.entity.Player;
import utilities.DummyNetworkManager;

import java.util.UUID;


public class NPC extends EntityPlayer {
    public NPC(Player player, UUID uuid, String name){
        super(((CraftServer)Bukkit.getServer()).getServer(), ((CraftWorld)player.getWorld()).getHandle(), new GameProfile(uuid, name), /*new PlayerInteractManager(((CraftWorld) player.getWorld()).getHandle())*/
                new PlayerInteractManager(((CraftWorld)player.getWorld()).getHandle()));
        playerConnection = new PlayerConnection(((CraftServer)Bukkit.getServer()).getServer(), new DummyNetworkManager(EnumProtocolDirection.CLIENTBOUND), this);
    }
}