package me.cyh2.thirdanniversarycelebrationevent.event;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.time.Instant;

import static me.cyh2.solarmc.api.utils.MinecraftTextUtils.ReColor;
import static me.cyh2.thirdanniversarycelebrationevent.ThirdAnniversaryCelebrationEvent.*;
import static me.cyh2.thirdanniversarycelebrationevent.utils.DateGet.getDay;

public class OnPlayerJoin implements Listener {
    @EventHandler
    public void JoinMessage (PlayerJoinEvent event) {
        Player p = event.getPlayer();
        if ((StartDate - Instant.now().toEpochMilli()) <= 604800000 && (StartDate - Instant.now().toEpochMilli()) >= 0) {
            p.sendMessage(ReColor(NebulaBot + "&7距离&b&lEternal&a&lStar三周年只剩下 " + getDay(StartDate - Instant.now().toEpochMilli()) + " 天了哦~"));
        } else if ((StartDate - Instant.now().toEpochMilli()) <= 0 && (EndDate - Instant.now().toEpochMilli()) >= 0) {
            p.sendMessage(ReColor(NebulaBot + "&7欢迎来到&b&lEternal&a&lStar三周年庆典哦~"));
            Sound.BLOCK_WOOD_HIT
        }
    }
}
