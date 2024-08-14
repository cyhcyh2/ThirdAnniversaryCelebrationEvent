package me.cyh2.thirdanniversarycelebrationevent.mysql;

import org.bukkit.OfflinePlayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static me.cyh2.thirdanniversarycelebrationevent.ThirdAnniversaryCelebrationEvent.*;

public class Gift {
    public static void setPlayerGift(OfflinePlayer player, String key, boolean data) {
        key = key.replace("-", "_");
        try (Connection connection = MySQLStart.getDataSource().getConnection()) {
            PreparedStatement exits = connection.prepareStatement("SELECT EXISTS (" +
                    "SELECT 1 " +
                    "FROM INFORMATION_SCHEMA.COLUMNS " +
                    "WHERE TABLE_SCHEMA = 'EternalStarPlayerData' " +
                    "AND TABLE_NAME = 'Gift' " +
                    "AND COLUMN_NAME = '" + key + "')");
            ResultSet resultSet = exits.executeQuery();
            if (resultSet.next()) {
                if (resultSet.getInt(1) == 0) {
                    exits = connection.prepareStatement("ALTER TABLE Gift ADD COLUMN " + key + " INT DEFAULT 0;");
                    exits.executeUpdate();
                }
            }
            PreparedStatement check = connection.prepareStatement("SELECT * FROM Gift WHERE PlayerUUID = ?");
            PreparedStatement update = connection.prepareStatement("UPDATE Gift SET " + key + " = ? WHERE PlayerUUID = ?");
            PreparedStatement insert = connection.prepareStatement("INSERT INTO Gift (PlayerUUID, PlayerName, " + key + ") VALUES (?, ?, ?)");
            check.setString(1, player.getUniqueId().toString());
            if (data) update.setInt(1, 1);
            else update.setInt(1, 0);
            update.setString(2, player.getUniqueId().toString());
            insert.setString(1, player.getUniqueId().toString());
            insert.setString(2, player.getName());
            if (data) insert.setInt(3, 1);
            else insert.setInt(3, 0);
            mysqlWriter.set(check, update, insert);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean getPlayerGift(OfflinePlayer player, String key) {
        key = key.replace("-", "_");
        try (Connection connection = MySQLStart.getDataSource().getConnection()) {
            PreparedStatement exits = connection.prepareStatement("SELECT EXISTS (" +
                    "SELECT 1 " +
                    "FROM INFORMATION_SCHEMA.COLUMNS " +
                    "WHERE TABLE_SCHEMA = 'EternalStarPlayerData' " +
                    "AND TABLE_NAME = 'Gift' " +
                    "AND COLUMN_NAME = '" + key + "')");
            ResultSet resultSet = exits.executeQuery();
            if (resultSet.next()) {
                if (resultSet.getInt(1) == 0) {
                    exits = connection.prepareStatement("ALTER TABLE Gift ADD COLUMN " + key + " INT DEFAULT 0;");
                    exits.executeUpdate();
                }
            }
            return mysqlReader.getBoolean("Gift", key, "PlayerUUID", player.getUniqueId().toString());
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
