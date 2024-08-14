package me.cyh2.thirdanniversarycelebrationevent.plays.giftseeker;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BlockDataMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.io.IOException;
import java.util.UUID;

import static me.cyh2.solarmc.api.utils.MinecraftTextUtils.ReColor;
import static me.cyh2.thirdanniversarycelebrationevent.ThirdAnniversaryCelebrationEvent.DataFolder;
import static me.cyh2.thirdanniversarycelebrationevent.Variable.*;

public class gift {
    @Deprecated
    public static void init () {
        ItemStack itemStack = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta skullMeta = (SkullMeta) itemStack.getItemMeta();
        skullMeta.setOwningPlayer(Bukkit.getOfflinePlayer("cyh2"));
        BlockDataMeta blockDataMeta = (BlockDataMeta) itemStack.getItemMeta();
        if (blockDataMeta != null) {
            BlockData blockData = blockDataMeta.getBlockData(Material.PLAYER_HEAD);
        }
    }
    public static void generate (Location location) {
        spawn(location);
        UUID uuid = UUID.randomUUID();
        GiftUUID.put(location, uuid.toString());
        giftLocation.set(uuid + ".Location", location);
        try {
            giftLocation.save(DataFolder + "/Location/gift/save.yml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void spawn (Location location) {
        ArmorStand armorStand = (ArmorStand) location.getWorld().spawnEntity(location.clone().subtract(-0.5, 1.5, -0.5), EntityType.ARMOR_STAND);
        armorStand.setCustomName(ReColor(giftConfig.getString("giftHologram")));
        armorStand.setCustomNameVisible(true);
        armorStand.setGravity(false);
        armorStand.setVisible(false);
        gifts.add(armorStand);
        GiftBlocks.add(location.getBlock());
    }
    public static void killGifts () {
        gifts.forEach(Entity::remove);
    }
}
