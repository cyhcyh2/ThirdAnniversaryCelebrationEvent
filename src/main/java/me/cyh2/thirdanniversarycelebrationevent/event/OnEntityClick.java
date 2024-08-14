package me.cyh2.thirdanniversarycelebrationevent.event;

import org.bukkit.Sound;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;

import static me.cyh2.solarmc.api.utils.MinecraftTextUtils.ReColor;
import static me.cyh2.thirdanniversarycelebrationevent.ThirdAnniversaryCelebrationEvent.NebulaBot;
import static me.cyh2.thirdanniversarycelebrationevent.Variable.gifts;


public class OnEntityClick implements Listener {
    @EventHandler
    public void OnMysteryBoxClick (PlayerInteractAtEntityEvent event) {
        Player player = event.getPlayer();
        Entity entity = event.getRightClicked();
        if (entity instanceof ArmorStand) {
            ArmorStand armor = (ArmorStand) entity;
            gifts.forEach(armorStand -> {
                if (armorStand.getCustomName() != null) {
                    if (armorStand.getCustomName().equals(armor.getCustomName())) {
                        event.setCancelled(true);
                        player.sendMessage(ReColor(NebulaBot + "&7是你按了这个神奇的礼包码awa"));
                        player.playSound(player, Sound.BLOCK_CHEST_OPEN, 1, 1);
                    }
                }
            });
        }
    }
}
