package me.cyh2.thirdanniversarycelebrationevent;

import me.cyh2.solarmc.api.ColourLogger;
import me.cyh2.solarmc.hikaricp.HikariCPStart;
import me.cyh2.solarmc.hikaricp.config.HikariCPConfig;
import me.cyh2.solarmc.hikaricp.mysql.api.MySQLRead;
import me.cyh2.solarmc.hikaricp.mysql.api.MySQLWrite;
import me.cyh2.thirdanniversarycelebrationevent.commands.spawnGift;
import me.cyh2.thirdanniversarycelebrationevent.event.OnBlockClick;
import me.cyh2.thirdanniversarycelebrationevent.event.OnEntityClick;
import me.cyh2.thirdanniversarycelebrationevent.event.OnPlayerJoin;
import me.cyh2.thirdanniversarycelebrationevent.event.OnServerLoaded;
import me.cyh2.thirdanniversarycelebrationevent.mysql.TableRegister;
import me.cyh2.thirdanniversarycelebrationevent.runnable.StartRunnable;
import org.bukkit.Server;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.sql.SQLException;
import java.time.Instant;
import java.util.logging.Logger;

import static me.cyh2.solarmc.api.utils.MinecraftTextUtils.ReColor;
import static me.cyh2.thirdanniversarycelebrationevent.utils.DateGet.getTimeStamp;

public final class ThirdAnniversaryCelebrationEvent extends JavaPlugin {
    public static Server server;
    public static Plugin plugin;
    public static Logger logger;
    public static PluginManager pluginManager;
    public static HikariCPStart MySQLStart;
    public static MySQLWrite mysqlWriter;
    public static MySQLRead mysqlReader;
    public static ColourLogger clogger;
    public static String PluginName;
    public static String NebulaBot;
    public static File DataFolder;
    public static long StartDate;
    public static long EndDate;
    @Override
    public void onEnable() {
        long start = Instant.now().toEpochMilli();
        PluginName = ReColor("&b&l「 EternalStar-&r三周年庆系统 &b&l」");
        NebulaBot = ReColor("&bNebulaBot » ");
        clogger = new ColourLogger(PluginName);
        MySQLStart = new HikariCPStart(new HikariCPConfig("jdbc:mysql://localhost:3306/EternalStarPlayerData", "root", "131136"));
        MySQLStart.start();
        mysqlWriter = new MySQLWrite(MySQLStart);
        mysqlReader = new MySQLRead(MySQLStart);
        server = getServer();
        plugin = getPlugin(getClass());
        logger = getLogger();
        pluginManager = server.getPluginManager();
        DataFolder = getDataFolder();
        StartDate = getTimeStamp("2024-08-11") + 50400000;
        EndDate = getTimeStamp("2024-09-01") + 50400000;
        pluginManager.registerEvents(new OnPlayerJoin(), plugin);
        pluginManager.registerEvents(new OnServerLoaded(), plugin);
        pluginManager.registerEvents(new OnEntityClick(), plugin);
        pluginManager.registerEvents(new OnBlockClick(), plugin);
        saveResource("config/gift/config.yml", false);
        saveResource("Location/gift/save.yml", false);
        getCommand("spawn-gift").setExecutor(new spawnGift());
        StartRunnable.start();
        Variable.init();
        try {
            TableRegister.regTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        logger.info("主要部分加载完成！");
        clogger.info("&b&lEternal&a&lStar&b三周年庆祝活动插件启动成功了哦~");
        clogger.warn("本插件仅授权并提供给Minecraft服务器 &b&lEternal&a&lStar &r及其运维及管理团队 &e&lNebula 使用！");
        clogger.info("本次启动耗时：&b" + ( Instant.now().toEpochMilli() - start ) + "ms&r");
    }

    @Override
    public void onDisable() {
        long stop = Instant.now().toEpochMilli();
        MySQLStart.stop();
        Variable.unload();
        logger.info("主要部分卸载完成！");
        clogger.info("&b&lEternal&a&lStar&b三周年庆祝活动插件卸载成功了哦~");
        clogger.info("本次关闭耗时：&b" + ( Instant.now().toEpochMilli() - stop ) + "ms&r");
    }
}
