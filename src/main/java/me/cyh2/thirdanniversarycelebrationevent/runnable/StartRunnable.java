package me.cyh2.thirdanniversarycelebrationevent.runnable;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

import static me.cyh2.solarmc.api.utils.MinecraftTextUtils.ReColor;
import static me.cyh2.thirdanniversarycelebrationevent.ThirdAnniversaryCelebrationEvent.plugin;
import static me.cyh2.thirdanniversarycelebrationevent.utils.DateGet.isStart;

public class StartRunnable {
    public static void start () {
        new BukkitRunnable() {
            @Override
            public void run() {
                if (isStart()) {
                    Bukkit.getOnlinePlayers().forEach( p -> {
                        p.sendMessage(ReColor(""));
                        p.sendMessage(ReColor(""));
                        p.sendMessage(ReColor(""));
                        p.sendMessage(ReColor(""));
                        p.sendMessage(ReColor(""));
                        p.sendMessage(ReColor(""));
                        p.sendMessage(ReColor(""));
                        p.sendMessage(ReColor(""));
                        p.sendMessage(ReColor(""));
                        p.sendMessage(ReColor(""));
                        p.sendMessage(ReColor("&7====================================="));
                        p.sendMessage(ReColor(""));
                        p.sendMessage(ReColor("&b&l全服公告"));
                        p.sendMessage(ReColor(""));
                        p.sendMessage(ReColor("&b&lEternal&a&lStar服务器三周年周年庆典正式开始了！"));
                        p.sendMessage(ReColor(""));
                        p.sendMessage(ReColor("&7====================================="));
                    });
                    this.cancel();
                }
            }
        }.runTaskTimer(plugin, 0L, 20L);
    }
}
