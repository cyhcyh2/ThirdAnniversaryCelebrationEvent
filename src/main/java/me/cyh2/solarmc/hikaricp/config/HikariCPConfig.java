package me.cyh2.solarmc.hikaricp.config;

public class HikariCPConfig {
    public String Url;
    public String UserName;
    public String PassWord;
    public HikariCPConfig(String Uri, String userName, String passWord) {
        this.Url = Uri;
        this.UserName = userName;
        this.PassWord = passWord;
    }
    public String getUrl () {
        return this.Url;
    }
    public String getUserName () {
        return this.UserName;
    }
    public String getPassWord () {
        return this.PassWord;
    }
    public void setUrl (String Uri) {
        this.Url = Uri;
    }
    public void setUserName (String userName) {
        this.UserName = userName;
    }
    public void setPassWord (String passWord) {
        this.PassWord = passWord;
    }
}
