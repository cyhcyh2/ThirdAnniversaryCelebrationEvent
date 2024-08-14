package me.cyh2.thirdanniversarycelebrationevent.event;

import me.cyh2.thirdanniversarycelebrationevent.Variable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerLoadEvent;

import static me.cyh2.thirdanniversarycelebrationevent.plays.giftseeker.ServerGift.spawnGifts;

public class OnServerLoaded extends Variable implements Listener {
    @EventHandler
    public void onGiftGen (ServerLoadEvent event) {
        spawnGifts();
    }
}
