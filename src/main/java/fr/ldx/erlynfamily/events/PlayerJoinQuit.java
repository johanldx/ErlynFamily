package fr.ldx.erlynfamily.events;

import fr.ldx.erlynfamily.utils.ErlynConfiguration;
import fr.ldx.erlynfamily.utils.ErlynDatabase;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class PlayerJoinQuit implements Listener {

    private final ErlynConfiguration config;
    private final ErlynDatabase db;

    public PlayerJoinQuit(ErlynConfiguration config, ErlynDatabase db) {
        this.config = config;
        this.db = db;
    }

    @EventHandler
    @Deprecated
    public void JoinEvent(PlayerJoinEvent e) throws IOException {
        final Player player = e.getPlayer();
        e.setJoinMessage(null);
        for (Player p : Bukkit.getOnlinePlayers()) {
            p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(config.getString("join_message").replace("{PLAYER}", player.getName())));
        }

        if (!(db.getPlayerFolder(player.getUniqueId().toString()).exists())) {

            for (String message : config.getStringList("welcome")) {
                player.sendMessage(message.replace("&", "§"));
            }

            final File file = db.getPlayerFile(player.getUniqueId().toString(), "name-" + player.getUniqueId());
            final YamlConfiguration configuration = db.getPlayer(player.getUniqueId().toString(), "name");

            configuration.set("name", player.getName());
            configuration.save(file);

            final File file2 = db.getPlayerFile(player.getUniqueId().toString(), "death-" + player.getUniqueId());
            final YamlConfiguration configuration2 = db.getPlayer(player.getUniqueId().toString(), "death");

            configuration2.set("death", 0);
            configuration2.save(file2);

            final File file3 = db.getPlayerFile(player.getUniqueId().toString(), "kill-" + player.getUniqueId());
            final YamlConfiguration configuration3 = db.getPlayer(player.getUniqueId().toString(), "kill");

            configuration3.set("kill", 0);
            configuration3.save(file3);
        }
    }

    @EventHandler
    @Deprecated
    public void onQuit(PlayerQuitEvent e) {
        e.setQuitMessage(null);
        for (Player p : Bukkit.getOnlinePlayers()) {
            p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(config.getString("quit_message").replace("{PLAYER}", e.getPlayer().getName())));
        }
    }
}
