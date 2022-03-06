package fr.ldx.erlynfamily.events;

import fr.ldx.erlynfamily.utils.ErlynConfiguration;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

public class PlayerMessage implements Listener {

    private final ErlynConfiguration config;

    public PlayerMessage(ErlynConfiguration config) {
        this.config = config;
    }

    @EventHandler
    @Deprecated
    public void onChat(PlayerChatEvent e) {
        final Player player = e.getPlayer();
        String message;

        if (player.hasPermission(config.getPlugin_permission() + "chatcolor")) {
            message = e.getMessage().replace("&", "§");
        }
        else {
            message = e.getMessage();
        }
        e.setCancelled(true);
        Bukkit.broadcastMessage(config.getString("chat_patern_message")
                .replace("{PLAYER}", player.getName())
                .replace("{MESSAGE}", message));
    }
}
