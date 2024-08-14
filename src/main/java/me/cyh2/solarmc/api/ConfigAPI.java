package me.cyh2.solarmc.api;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ConfigAPI {
    public static YamlConfiguration LOAD (String FileName, File DataFolder) {
        return YamlConfiguration.loadConfiguration(new File(DataFolder + "/" + FileName));
    }
    @Deprecated
    public static YamlConfiguration WRITE (YamlConfiguration yamlConfiguration) throws IOException {
        yamlConfiguration.save("");
        return null;
    }
}
