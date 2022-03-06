package fr.ldx.erlynfamily.commands;

import fr.ldx.erlynfamily.utils.ErlynConfiguration;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class CmdWiki implements CommandExecutor {

    private final ErlynConfiguration config;

    public CmdWiki(ErlynConfiguration config) {
        this.config = config;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            final Player player = (Player) sender;
            if (cmd.getName().equalsIgnoreCase("wiki")) {

                final List<String> wikis_links = config.getStringList("wikis_links");
                int wiki_index = 0;

                player.sendMessage(" ");
                player.sendMessage("§3Les wikis des mods du serveur :");

                for (String wikis_mods : config.getStringList("wikis_mods")) {
                    TextComponent message = new TextComponent("§7" + wikis_mods.replace("&", "§"));
                    message.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§3" + wikis_mods.replace("&", "§")).create()));
                    message.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, wikis_links.get(wiki_index).replace("&", "§")));
                    wiki_index += 1;
                    player.spigot().sendMessage(message);
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