package fr.ldx.erlynfamily.commands;

import fr.ldx.erlynfamily.utils.ErlynConfiguration;
import fr.ldx.erlynfamily.utils.ErlynLocation;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdSetSpawn implements CommandExecutor {

    private final ErlynConfiguration config;
    private final ErlynLocation loc;

    public CmdSetSpawn(ErlynConfiguration config, ErlynLocation loc) {
        this.config = config;
        this.loc = loc;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            final Player player = (Player) sender;
            if (cmd.getName().equalsIgnoreCase("setspawn")) {
                if (loc.setSpawn(player)) {
                    player.sendMessage(config.getString("teleportation_spawn_message_set"));
                    return true;
                }
                else {
                    player.sendMessage(config.getString("error_message_unexpected"));
                    return false;
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
