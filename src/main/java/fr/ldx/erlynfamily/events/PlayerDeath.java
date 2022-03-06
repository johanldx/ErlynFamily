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
import org.bukkit.event.entity.PlayerDeathEvent;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class PlayerDeath implements Listener {

    private final ErlynConfiguration config;
    private final ErlynDatabase db;

    public PlayerDeath(ErlynConfiguration config, ErlynDatabase db) {
        this.config = config;
        this.db = db;
    }

    @EventHandler
    @Deprecated
    public void DeathEvent(PlayerDeathEvent e) throws IOException {
        final Player player = e.getEntity().getPlayer();

        assert player != null;

        final List<String> death_messages = config.getStringList("death_messages");

        for (Player p : Bukkit.getOnlinePlayers()) {
            p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(death_messages.get(new Random().nextInt(death_messages.size() - 1)).replace("{PLAYER}", player.getName()).replace("&", "§")));
        }

        final File file_player = db.getPlayerFile(player.getUniqueId().toString(), "death-" + player.getUniqueId());
        final YamlConfiguration configuration_player = db.getPlayer(player.getUniqueId().toString(), "death-" + player.getUniqueId());

        final int death = configuration_player.getInt("death");
        configuration_player.set("death", death + 1);
        configuration_player.save(file_player);

        if (e.getEntity().getKiller() != null) {
            final Player killer = e.getEntity().getKiller();

            if (new Random().nextInt(99) < config.getInt("probability_drop_death"))
                e.getDrops().add(config.getSkullPlayer(player, 1, player.getName(), Collections.singletonList("Tué par " + killer.getName())));

            final File file_killer = db.getPlayerFile(killer.getUniqueId().toString(), "kill-" + player.getUniqueId());
            final YamlConfiguration configuration_killer = db.getPlayer(killer.getUniqueId().toString(), "kill-" + player.getUniqueId());

            final int kill1 = configuration_killer.getInt("kill");
            configuration_killer.set("kill", kill1 + 1);
            configuration_killer.save(file_killer);

            for (Player p : Bukkit.getOnlinePlayers()) {
                p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(config.getString("kill_message").replace("&", "§").replace("{PLAYER}", killer.getName()).replace("{KILLED}", player.getName()).replace("{WEAPON}", e.getEntity().getKiller().getInventory().getItemInHand().getType().name())));
            }
        }
    }
}
