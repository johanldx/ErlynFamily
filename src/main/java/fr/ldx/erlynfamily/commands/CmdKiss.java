package fr.ldx.erlynfamily.commands;

import fr.ldx.erlynfamily.utils.ErlynConfiguration;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdKiss implements CommandExecutor {

    private final ErlynConfiguration config;

    public CmdKiss(ErlynConfiguration config) {
        this.config = config;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            final Player player = (Player) sender;
            if (cmd.getName().equalsIgnoreCase("kiss")) {
                if (args.length > 0) {
                    if (Bukkit.getOnlinePlayers().contains(Bukkit.getPlayer(args[0]))) {
                        final Player receiver = Bukkit.getPlayer(args[0]);
                        if (receiver != player) {
                            double player_health = player.getHealth();
                            double receiver_health = receiver.getHealth();

                            if (player_health - (20 - receiver_health) > 0) {
                                player.setHealth(20 - (20 - receiver_health));
                                receiver.setHealth(20);
                            }
                            else {
                                player.setHealth(1);
                                receiver.setHealth(player_health - 1);
                            }

                            receiver.spawnParticle(Particle.HEART, receiver.getEyeLocation(), 2);

                            for (Player p : Bukkit.getOnlinePlayers()) {
                                p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(config.getString("kiss_message")
                                        .replace("{SENDER}", player.getName())
                                        .replace("{RECEIVER}", receiver .getName())));
                            }
                        }
                        else {
                            player.sendMessage(config.getString("error_message_playerisyou"));
                        }
                    }
                    else {
                        player.sendMessage(config.getString("error_message_playervalid"));
                    }
                }
                else {
                    player.sendMessage(config.getString("error_message_playervalid"));
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