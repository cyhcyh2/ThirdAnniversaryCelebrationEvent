package me.cyh2.thirdanniversarycelebrationevent;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.ArmorStand;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static me.cyh2.solarmc.api.ConfigAPI.LOAD;
import static me.cyh2.thirdanniversarycelebrationevent.ThirdAnniversaryCelebrationEvent.DataFolder;
import static me.cyh2.thirdanniversarycelebrationevent.plays.giftseeker.gift.killGifts;

public class Variable {
    public static YamlConfiguration giftLocation;
    public static YamlConfiguration giftConfig;
    public static List<ArmorStand> gifts;
    public static List<Block> GiftBlocks;
    public static Map<Location, String> GiftUUID;
    public static void init () {
        giftLocation = LOAD("Location/gift/save.yml", DataFolder);
        giftConfig = LOAD("config/gift/config.yml", DataFolder);
        gifts = new ArrayList<>();
        GiftBlocks = new ArrayList<>();
        GiftUUID = new HashMap<>();
    }
    public static void unload () {
        killGifts();
        gifts.clear();
    }
}
