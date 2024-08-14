package me.cyh2.solarmc.hikaricp;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import me.cyh2.solarmc.hikaricp.config.HikariCPConfig;

public class HikariCPStart {
    public HikariDataSource dataSource;
    public HikariCPConfig Config;
    public HikariCPStart(HikariCPConfig config) {
        this.Config = config;
    }
    public void start () {
        HikariConfig config = new HikariConfig();

        // 设置数据库连接参数
        config.setJdbcUrl(Config.Url);
        config.setUsername(Config.UserName);
        config.setPassword(Config.PassWord);

        // 设置连接池参数（可选）
        config.setMaximumPoolSize(10);
        config.setMinimumIdle(5);
        config.setConnectionTimeout(30000);
        this.dataSource = new HikariDataSource(config);
    }
    public void stop () {
        if (!this.dataSource.isClosed()) this.dataSource.close();
    }
    public HikariDataSource getDataSource () {
        return this.dataSource;
    }
}
