package me.cyh2.solarmc.hikaricp.mysql.api;

import me.cyh2.solarmc.hikaricp.HikariCPStart;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQLRead {
    private final HikariCPStart hikariCPStart;
    public MySQLRead (HikariCPStart hikariCP) {
        this.hikariCPStart = hikariCP;
    }

    /**
     *
     * @param table : 查询的表名
     * @param line : 查询的行数
     * @param index : 索引
     * @param index_path : 索引的值
     * @return Integer : 返回查询的结果
     * @throws SQLException : 可能会抛出SQL异常
     */
    public int getInt (String table, String line, String index, String index_path) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try (Connection connection = this.hikariCPStart.getDataSource().getConnection()) {
            preparedStatement = connection.prepareStatement("SELECT " + line + " FROM " + table + " WHERE " + index + " = ?;");
            preparedStatement.setString(1, index_path);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(line);
            } else {
                return 0;
            }
        } finally {
            if (preparedStatement != null && resultSet != null) {
                resultSet.close();
                preparedStatement.close();
            }
        }
    }
    /**
     *
     * @param table : 查询的表名
     * @param line : 查询的行数
     * @param index : 索引
     * @param index_path : 索引的值
     * @return Long : 返回查询的结果
     * @throws SQLException : 可能会抛出SQL异常
     */
    public long getLong (String table, String line, String index, String index_path) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try (Connection connection = this.hikariCPStart.getDataSource().getConnection()) {
            preparedStatement = connection.prepareStatement("SELECT " + line + " FROM " + table + " WHERE " + index + " = ?;");
            preparedStatement.setString(1, index_path);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getLong(line);
            } else {
                return 0L;
            }
        } finally {
            if (preparedStatement != null && resultSet != null) {
                resultSet.close();
                preparedStatement.close();
            }
        }
    }
    /**
     *
     * @param table : 查询的表名
     * @param line : 查询的行数
     * @param index : 索引
     * @param index_path : 索引的值
     * @return Float : 返回查询的结果
     * @throws SQLException : 可能会抛出SQL异常
     */
    public float getFloat (String table, String line, String index, String index_path) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try (Connection connection = this.hikariCPStart.getDataSource().getConnection()) {
            preparedStatement = connection.prepareStatement("SELECT " + line + " FROM " + table + " WHERE " + index + " = ?;");
            preparedStatement.setString(1, index_path);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getFloat(line);
            } else {
                return 0.0F;
            }
        } finally {
            if (preparedStatement != null && resultSet != null) {
                resultSet.close();
                preparedStatement.close();
            }
        }
    }
    /**
     *
     * @param table : 查询的表名
     * @param line : 查询的行数
     * @param index : 索引
     * @param index_path : 索引的值
     * @return Boolean : 返回查询的结果
     * @throws SQLException : 可能会抛出SQL异常
     */
    public boolean getBoolean (String table, String line, String index, String index_path) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try (Connection connection = this.hikariCPStart.getDataSource().getConnection()) {
            preparedStatement = connection.prepareStatement("SELECT " + line + " FROM " + table + " WHERE " + index + " = ?;");
            preparedStatement.setString(1, index_path);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(line) == 1;
            } else {
                return false;
            }
        } finally {
            if (preparedStatement != null && resultSet != null) {
                resultSet.close();
                preparedStatement.close();
            }
        }
    }
    /**
     *
     * @param table : 查询的表名
     * @param line : 查询的行数
     * @param index : 索引
     * @param index_path : 索引的值
     * @return String : 返回查询的结果
     * @throws SQLException : 可能会抛出SQL异常
     */
    public String getString (String table, String line, String index, String index_path) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try (Connection connection = this.hikariCPStart.getDataSource().getConnection()) {
            preparedStatement = connection.prepareStatement("SELECT " + line + " FROM " + table + " WHERE " + index + " = ?;");
            preparedStatement.setString(1, index_path);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString(line);
            } else {
                return null;
            }
        } finally {
            if (preparedStatement != null && resultSet != null) {
                resultSet.close();
                preparedStatement.close();
            }
        }
    }
}
