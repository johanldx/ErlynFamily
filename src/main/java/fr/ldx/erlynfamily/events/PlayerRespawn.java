package fr.ldx.erlynfamily.events;

import fr.ldx.erlynfamily.utils.ErlynConfiguration;
import fr.ldx.erlynfamily.utils.ErlynDatabase;
import fr.ldx.erlynfamily.utils.ErlynLocation;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

import java.io.File;

public class PlayerRespawn implements Listener {

    private final ErlynConfiguration config;
    private final ErlynDatabase db;
    private final ErlynLocation loc;

    public PlayerRespawn(ErlynConfiguration config, ErlynDatabase db, ErlynLocation loc) {
        this.config = config;
        this.db = db;
        this.loc = loc;
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent e) {
        e.getPlayer().setGameMode(GameMode.SURVIVAL);
        if (config.getString("location_respawn").equalsIgnoreCase("spawn")) {
            final File file = db.getTeleportationFile("spawn-" + e.getPlayer().getWorld().getName());
            if (file.exists()) {
                e.setRespawnLocation(loc.getSpawn(e.getPlayer()));
            }
        }
    }
}
