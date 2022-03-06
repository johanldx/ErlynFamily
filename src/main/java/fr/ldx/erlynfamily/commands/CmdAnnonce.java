package fr.ldx.erlynfamily.commands;


import fr.ldx.erlynfamily.utils.ErlynConfiguration;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdAnnonce implements CommandExecutor {

    private final ErlynConfiguration config;

    public CmdAnnonce(ErlynConfiguration config) {
        this.config = config;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            final Player player = (Player) sender;
            if (cmd.getName().equalsIgnoreCase("annonce")) {
                if (args.length >= 1) {

                    StringBuilder message = new StringBuilder();
                    for (String part : args) {
                        message.append(part).append(" ");
                    }

                    player.sendMessage(" ");
                    Bukkit.broadcastMessage(config.getString("annonce_patern_message").replace("&", "§").replace("{MESSAGE}", message.toString()));
                    return true;
                }
                else {
                    player.sendMessage(config.getString("error_message_broadcast_length"));
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