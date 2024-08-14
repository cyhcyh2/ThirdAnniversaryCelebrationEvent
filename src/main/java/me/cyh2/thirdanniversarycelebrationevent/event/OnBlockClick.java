package me.cyh2.thirdanniversarycelebrationevent.event;

import me.cyh2.thirdanniversarycelebrationevent.Variable;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import java.time.Instant;
import java.util.Random;

import static me.cyh2.eternalstarlobbysys.hikaricpmysql.FinanceSystem.getStellarCoins;
import static me.cyh2.eternalstarlobbysys.hikaricpmysql.FinanceSystem.setStellarCoins;
import static me.cyh2.eternalstarlobbysys.hikaricpmysql.PlayerProfiles.*;
import static me.cyh2.solarmc.api.utils.MinecraftTextUtils.ReColor;
import static me.cyh2.thirdanniversarycelebrationevent.ThirdAnniversaryCelebrationEvent.NebulaBot;
import static me.cyh2.thirdanniversarycelebrationevent.mysql.Gift.getPlayerGift;
import static me.cyh2.thirdanniversarycelebrationevent.mysql.Gift.setPlayerGift;
import static me.cyh2.thirdanniversarycelebrationevent.utils.DateGet.isStart;


public class OnBlockClick extends Variable implements Listener {
    @EventHandler
    public void onPlayerRightClickBlock(PlayerInteractEvent event) {
        if (isStart()) {
            Block block = event.getClickedBlock();
            if (block != null) {
                if (GiftUUID.get(block.getLocation()) != null) {
                    GiftBlocks.forEach(block1 -> {
                        if (block.equals(block1)) {
                            event.setCancelled(true);
                            event.getPlayer().sendMessage(ReColor(NebulaBot + "&7是你按了这个神奇的礼包码awa"));
                            event.getPlayer().playSound(event.getPlayer(), Sound.BLOCK_CHEST_OPEN, 1, 1);
                            if (getPlayerGift(event.getPlayer(), GiftUUID.get(block.getLocation()))) {
                                event.getPlayer().sendMessage(ReColor(NebulaBot + "&7你已经打开过这个礼包了哦~"));
                            } else {
                                setPlayerGift(event.getPlayer(), GiftUUID.get(block.getLocation()), true);
                                event.getPlayer().sendMessage(ReColor(NebulaBot + "&7你打开了这个神奇的礼包，找到了一些东西："));
                                Random random = new Random(Instant.now().toEpochMilli());
                                for (int i = 0; i < random.nextInt(2, 5); i++) {
                                    int ran = random.nextInt(1, 3);
                                    if (ran == 1) {
                                        setPlayerExp(event.getPlayer(), getPlayerExp(event.getPlayer()) + 150);
                                        event.getPlayer().sendMessage(ReColor(NebulaBot + "&7永恒星经验值 * 150"));
                                    } else if (ran == 2) {
                                        setStellarCoins(event.getPlayer(), getStellarCoins(event.getPlayer()) + 200);
                                        event.getPlayer().sendMessage(ReColor(NebulaBot + "&7永恒星币 * 200"));
                                    }
                                }
                            }
                        }
                    });
                }
            }
        }
    }
}
