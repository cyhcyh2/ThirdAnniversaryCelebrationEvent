package me.cyh2.solarmc.hikaricp.mysql.api;

import me.cyh2.solarmc.hikaricp.HikariCPStart;

import javax.annotation.Nullable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MySQLWrite {
    private final HikariCPStart hikariCPStart;
    public MySQLWrite (HikariCPStart start) {
        this.hikariCPStart = start;
    }
    /**
     * @param data: 要写入的数据
     * @param table: 要写入的数据表
     * @param path1: 写入的位置的查询路径，如：PlayerUUID
     * @param path2: 写入的位置，如：EternalCoins
     * @param path3: path1的查询用数据，如：player.getUniqueId().toString();
     */
    @SafeVarargs
    @Deprecated
    public final void set(Object data, String table, String path1, String path2, String path3, Map<String, Object>... writePaths) throws SQLException {
        PreparedStatement preparedStatement;
        Connection connection = hikariCPStart.getDataSource().getConnection();
        preparedStatement = connection.prepareStatement("SELECT * FROM " + table + " WHERE " + path1 + " = ?");
        preparedStatement.setString(1, path3);
        ResultSet result = preparedStatement.executeQuery();
        if (result.next()) {
            preparedStatement = connection.prepareStatement("UPDATE " + table + " SET " + path2 + " = ? WHERE " + path1 + " = ?");
            preparedStatement.setObject(1, data);
            preparedStatement.setString(2, path3);
            preparedStatement.executeUpdate();
        } else {
            if (writePaths != null) {
                StringBuilder stringBuilder = new StringBuilder();
                StringBuilder stringBuilder1 = new StringBuilder();
                List<String> stringList = new ArrayList<>();
                List<Map<String, Object>> paths = new ArrayList<>();
                for (Map<String, Object> writePath : writePaths) {
                    for (String str : writePath.keySet()) {
                        stringBuilder.append(str).append(", ");
                        stringBuilder1.append("?, ");
                        stringList.add(str);
                        paths.add(writePath);
                    }
                }
                preparedStatement = connection.prepareStatement("INSERT INTO " + table + " (" + path1 + ", " + path2 + ", " + stringBuilder + ") VALUES (" + stringBuilder1 + ")");
                preparedStatement.setString(1, path3);
                preparedStatement.setObject(2, data);
                for (int i = 0; i < stringList.size(); i++) {
                    preparedStatement.setObject(i, paths.get(i).get(stringList.get(i)));
                };
            } else {
                preparedStatement = connection.prepareStatement("INSERT INTO " + table + " (" + path1 + ", " + path2 + ") VALUES (?, ?)");
                preparedStatement.setString(1, path3);
                preparedStatement.setObject(2, data);
            }
            preparedStatement.executeUpdate();
        }
        result.close();
        preparedStatement.close();
        connection.close();
    }
    public void set(PreparedStatement check, PreparedStatement update, PreparedStatement insert) throws SQLException {
        ResultSet resultSet = check.executeQuery();
        if (resultSet.next()) {
            update.executeUpdate();
        } else {
            insert.executeUpdate();
        }
        resultSet.close();
        check.getConnection().close();
        check.close();
        update.close();
        insert.close();
    }
}
