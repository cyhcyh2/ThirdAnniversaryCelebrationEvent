package me.cyh2.solarmc.api.utils;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class MinecraftTextUtils {
    public static String ReColor (@Nullable String toColor) {
        if (toColor != null) return toColor.replace("&", "§").replace("§§", "&");
        else return "NULL";
    }
    public static List<String> ReColorList (List<String> toColorList) {
        List<String> reColor = new ArrayList<>();
        for (String str : toColorList) {
            reColor.add(ReColor(str));
        }
        return reColor;
    }
    public static List<Character> to_String(String s) {
        List<Character> charList = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            charList.add(s.charAt(i));
        }
        return charList;
    }
    public static void sendDynamicTitle(Player player, String title, String subtitle, int fadeIn, int stay, int fadeOut, Plugin plugin) {
        final String[] str1 = {""};
        List<Character> charList1 = to_String(ReColor(title));
        final String[] str2 = {""};
        List<Character> charList2 = to_String(ReColor(subtitle));
        BukkitRunnable task = new BukkitRunnable() {
            int currentIndex = 0;
            int currentIndex2 = 0;

            @Override
            public void run() {
                if (currentIndex < charList1.size() || currentIndex2 < charList2.size()) {
                    if (currentIndex < charList1.size()) {
                        str1[0] += charList1.get(currentIndex);
                        if (charList1.size() >= (currentIndex + 2)) {
                            if (charList1.get((currentIndex + 1)).toString().equals(ReColor("&"))) {
                                str1[0] += charList1.get((currentIndex + 1));
                                str1[0] += charList1.get((currentIndex + 2));
                            }
                        }
                        currentIndex++;
                    } else {
                        str2[0] += charList2.get(currentIndex2);
                        if (charList2.size() >= (currentIndex2 + 2)) {
                            if (charList2.get((currentIndex2 + 1)).toString().equals(ReColor("&"))) {
                                str2[0] += charList2.get((currentIndex2 + 1));
                                str2[0] += charList2.get((currentIndex2 + 2));
                            }
                        }
                        currentIndex2 ++;
                    }
                    player.sendTitle(str1[0], str2[0], fadeIn, stay, fadeOut);
                } else {
                    //player.sendTitlePart(TitlePart.TITLE, Component.text(str1[0]));
                    //player.sendTitlePart(TitlePart.SUBTITLE, Component.text(str2[0]));
                    player.sendTitle(str1[0], str2[0], 0, 60, 30);
                    this.cancel();
                }
            }
        };
        task.runTaskTimer(plugin, 0L, (stay + fadeIn + fadeOut) - 1);
    }
    public static String kickTextBuilder (FileConfiguration config, String Prompt, String Reason) {
        String kickFormat = config.getString("KickMessageFormat");
        if (kickFormat != null) {
            return ReColor(kickFormat.replace("%prompt%", Prompt).replace("%reason%", Reason));
        } else {
            return ReColor(Prompt + "\n" + Reason);
        }
    }
}
