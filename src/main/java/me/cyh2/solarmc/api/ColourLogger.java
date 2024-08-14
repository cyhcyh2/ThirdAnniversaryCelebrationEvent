package me.cyh2.solarmc.api;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;

import static me.cyh2.solarmc.api.utils.MinecraftTextUtils.ReColor;

public class ColourLogger {
    public ConsoleCommandSender consoleCommandSender = Bukkit.getConsoleSender();
    public String PluginName;
    public ColourLogger(String pluginName) {
        this.PluginName = pluginName;
    }
    public void info (String msg) {
        consoleCommandSender.sendMessage(PluginName + ReColor(" &b[ -信息- ]&r " + msg));
    }
    public void warn (String msg) {
        consoleCommandSender.sendMessage(PluginName + ReColor(" &e[ ·警告· ]&r " + msg));
    }
    public void error (String msg) {
        consoleCommandSender.sendMessage(PluginName + ReColor(" &c[ ！错误！ ]&r " + msg));
    }
}
