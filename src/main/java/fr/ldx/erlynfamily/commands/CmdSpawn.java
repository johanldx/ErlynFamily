package fr.ldx.erlynfamily.commands;

import fr.ldx.erlynfamily.utils.ErlynConfiguration;
import fr.ldx.erlynfamily.utils.ErlynDatabase;
import fr.ldx.erlynfamily.utils.ErlynLocation;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.File;

public class CmdSpawn implements CommandExecutor {

    private final ErlynConfiguration config;
    private final ErlynDatabase db;
    private final ErlynLocation loc;

    public CmdSpawn(ErlynConfiguration config, ErlynDatabase db, ErlynLocation loc) {
        this.config = config;
        this.db = db;
        this.loc = loc;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            final Player player = (Player) sender;
            if (cmd.getName().equalsIgnoreCase("spawn")) {
                final File file = db.getTeleportationFile("spawn-" + player.getWorld().getName());

                if (file.exists()) {
                    player.sendMessage(config.getString("teleportation_spawn_message_teleport"));

                    if (player.hasPermission(config.getPlugin_permission() +"bypass")) {
                        loc.teleport(player, loc.getSpawn(player), 0);
                    }
                    else {
                        loc.teleport(player, loc.getSpawn(player), loc.getDelay_teleportation());
                    }
                    return true;
                }
                else {
                    player.sendMessage(config.getString("error_message_spawn_notset"));
                }
            }
        }
        else {
            sender.sendMessage(config.getString("error_message_sender"));
            return true;
        }
        return false;
    }
}
