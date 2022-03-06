package fr.ldx.erlynfamily.utils;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class ErlynDatabase {

    private YamlConfiguration getDatabase(String path, String file) {
        return YamlConfiguration.loadConfiguration(new File("erlyn/data/" + path + "/" + file));
    }

    private String getDirectory(String path) {
        return "erlyn/data/" + path + "/";
    }

    public File getPlayerFolder(String uuid) {
        return new File(getDirectory("players") + uuid + "/");
    }

    public File getPlayerFile(String uuid, String file) {
        return new File(getDirectory("players") + uuid + "/" + file + ".yml");
    }

    public File getTeleportationFile(String path) {
        return new File(getDirectory("teleportations") + path + ".yml");
    }

    public File getTeleportationFolder() {
        return new File("erlyn/data/teleportations/");
    }

    public YamlConfiguration getPlayer(String uuid, String file) {
        return getDatabase("players", uuid + "/" + file + ".yml");
    }

    public YamlConfiguration getTeleportation(String location) {
        if (location.contains(".yml")) {
            return getDatabase("teleportations", location);
        }
        else {
            return getDatabase("teleportations", location + ".yml");
        }
    }
}

