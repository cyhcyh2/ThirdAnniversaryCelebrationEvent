package me.cyh2.thirdanniversarycelebrationevent.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static me.cyh2.thirdanniversarycelebrationevent.ThirdAnniversaryCelebrationEvent.MySQLStart;

public class TableRegister {
    public static void regTable () throws SQLException {
        Connection connection;
        try {
            connection = MySQLStart.getDataSource().getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        PreparedStatement statement;
        String createGiftTable = "CREATE TABLE IF NOT EXISTS Gift (" +
                "PlayerName VARCHAR(255) NOT NULL UNIQUE," +
                "PlayerUUID CHAR(36) NOT NULL UNIQUE" +
                ")";
        statement = connection.prepareStatement(createGiftTable);
        statement.executeUpdate();
    }
}
