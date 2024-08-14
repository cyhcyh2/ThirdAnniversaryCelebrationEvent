package me.cyh2.thirdanniversarycelebrationevent.commands;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.RayTraceResult;

import static me.cyh2.solarmc.api.utils.MinecraftTextUtils.ReColor;
import static me.cyh2.thirdanniversarycelebrationevent.ThirdAnniversaryCelebrationEvent.PluginName;
import static me.cyh2.thirdanniversarycelebrationevent.plays.giftseeker.gift.generate;

public class spawnGift implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            Player p = (Player) commandSender;
            if (p.hasPermission("TACE.gift")) {
                p.sendMessage(ReColor(PluginName + "&7正在寻找合适的位置来生成礼包哦~"));
                RayTraceResult rayTraceResult = p.rayTraceBlocks(10);
                if (rayTraceResult != null && rayTraceResult.getHitBlock() != null) {
                    Block block = rayTraceResult.getHitBlock();
                    if (block.getType() != Material.AIR) {
                        generate(block.getLocation());
                        p.sendMessage(ReColor(PluginName + "&7礼包已成功生成在 " + block.getLocation()));
                    } else {
                        p.sendMessage(ReColor(PluginName + "&7无法在此位置生成礼包，请尝试其他位置哦~"));
                    }
                } else {
                    p.sendMessage(ReColor(PluginName + "&7无法找到合适的位置来生成礼包哦~"));
                }
            }
            return true;
        }
        return false;
    }
}
