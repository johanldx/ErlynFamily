package fr.ldx.erlynfamily.commands;

import fr.ldx.erlynfamily.utils.ErlynConfiguration;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdKick implements CommandExecutor {

    private final ErlynConfiguration config;

    public CmdKick(ErlynConfiguration config) {
        this.config = config;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            final Player player = (Player) sender;
            if (cmd.getName().equalsIgnoreCase("kick")) {
                if (args.length >= 2) {
                    if (Bukkit.getOnlinePlayers().contains(Bukkit.getPlayer(args[0]))) {
                        final Player player_kicked = Bukkit.getPlayer(args[0]);

                        StringBuilder message = new StringBuilder();
                        for (String part : args) {
                            message.append(part).append(" ");
                        }

                        player_kicked.kickPlayer(message.substring(player_kicked.getName().length()));

                        for (Player p : Bukkit.getOnlinePlayers()) {
                            p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("&c" + player_kicked.getName() + " s'est fait éjecter du serveur par " + player.getName() + "!"));
                        }
                    }
                    else {
                        player.sendMessage(config.getString("error_message_playervalid"));
                    }

                }
                else {
                    player.sendMessage(config.getString("error_message_broadcast_length"));
                }
                return true;
            }
        }
        else {
            sender.sendMessage(config.getString("error_message_sender"));
            return true;
        }
        return false;
    }
}