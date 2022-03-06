package fr.ldx.erlynfamily.utils;

import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ErlynConfiguration {

    private final File plugin_datafolder = new File("plugins/ErlynFamily/");
    private final Map<Player, Player> reply = new HashMap<>();

    private final File config = new File(plugin_datafolder, "config.yml");
    private final YamlConfiguration configuration = YamlConfiguration.loadConfiguration(config);

    public ConfigurationSection getConfigurationSection(String key) {
        return configuration.getConfigurationSection(key);
    }

    public List<String> getStringList(String key) {
        return configuration.getStringList(key);
    }

    public String getString(String key) {
        return Objects.requireNonNull(configuration.getString(key)).replace("&", "§");
    }


    public int getInt(String key) {
        return configuration.getInt(key);
    }

    public String getPlugin_name() {
        return "Erlyn Family";
    }

    public String getPlugin_permission() {
        return "erlynfamily.";
    }

    public File getPlugin_datafolder() {
        return plugin_datafolder;
    }

    public Map<Player, Player> getReply() {
        return reply;
    }

    @Deprecated
    public ItemStack getSkullPlayer(Player owner, int number, String name, List<String> lore) {
        ItemStack it = new ItemStack(Material.SKULL_ITEM, number, (byte) 3);
        SkullMeta meta = (SkullMeta) it.getItemMeta();
        meta.setOwningPlayer(owner);
        meta.setDisplayName(name);
        meta.setLore(lore);

        it.setItemMeta(meta);

        return it;
    }
}
