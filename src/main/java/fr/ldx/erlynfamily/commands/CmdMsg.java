package fr.ldx.erlynfamily.commands;

import fr.ldx.erlynfamily.utils.ErlynConfiguration;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CmdMsg implements CommandExecutor {

    private final ErlynConfiguration config;

    public CmdMsg(ErlynConfiguration config) {
        this.config = config;
    }

    @Override
    @Deprecated
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            final Player player = (Player) sender;
            if (cmd.getName().equalsIgnoreCase("msg")) {
                if (args.length > 1) {
                    if (Bukkit.getOnlinePlayers().contains(Bukkit.getPlayer(args[0]))) {
                        final Player receiver = Bukkit.getPlayer(args[0]);

                        StringBuilder message = new StringBuilder();
                        for (String part : args) {
                            message.append(part).append(" ");
                        }

                        assert receiver != null;
                        final String receiver_name;
                        if (receiver == player) {
                            receiver_name = "Moi";
                        }
                        else {
                            receiver_name = receiver.getName();
                        }

                        player.sendMessage(config.getString("msg_patern_message")
                                .replace("&", "§")
                                .replace("{SENDER}", "Moi")
                                .replace("{RECEIVER}", receiver_name)
                                .replace("{MESSAGE}", message.substring(args[0].length() + 1)));

                        TextComponent msg = new TextComponent(config.getString("msg_patern_message")
                                .replace("&", "§")
                                .replace("{SENDER}", player.getName())
                                .replace("{RECEIVER}", "Moi")
                                .replace("{MESSAGE}", message.substring(args[0].length() + 1)));
                        msg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§cRépondre").create()));
                        msg.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/msg " + player.getName() + " "));
                        receiver.spigot().sendMessage(msg);

                        config.getReply().put(receiver, player);
                    }
                    else {
                        player.sendMessage("error_message_playervalid");
                    }
                }
                else {
                    player.sendMessage(config.getString("msg_message_cmd"));
                }
                return true;
            }
            else if (cmd.getName().equalsIgnoreCase("r") || cmd.getName().equalsIgnoreCase("reply")) {
                if (config.getReply().containsKey(player.getPlayer())) {
                    if (args.length > 0) {
                        if (Bukkit.getOnlinePlayers().contains(config.getReply().get(player))) {
                            final Player receiver = config.getReply().get(player);

                            StringBuilder message = new StringBuilder();
                            for (String part : args) {
                                message.append(part).append(" ");
                            }

                            assert receiver != null;
                            final String receiver_name;
                            if (receiver == player) {
                                receiver_name = "Moi";
                            }
                            else {
                                receiver_name = receiver.getName();
                            }

                            player.sendMessage(config.getString("msg_patern_message")
                                    .replace("&", "§")
                                    .replace("{SENDER}", "Moi")
                                    .replace("{RECEIVER}", receiver_name)
                                    .replace("{MESSAGE}", message));

                            TextComponent msg = new TextComponent(config.getString("msg_patern_message")
                                    .replace("&", "§")
                                    .replace("{SENDER}", player.getName())
                                    .replace("{RECEIVER}", "Moi")
                                    .replace("{MESSAGE}", message));
                            msg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§cRépondre").create()));
                            msg.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/msg " + player.getName() + " "));
                            receiver.spigot().sendMessage(msg);
                        }
                        else {
                            player.sendMessage("error_message_playervalid");
                        }
                    }
                    else {
                        player.sendMessage(config.getString("reply_message_cmd"));
                    }
                }
                else {
                    player.sendMessage(config.getString("error_message_reply_notfound"));
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
