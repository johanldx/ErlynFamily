package fr.ldx.erlynfamily.commands;

import fr.ldx.erlynfamily.utils.ErlynConfiguration;
import fr.ldx.erlynfamily.utils.ErlynDatabase;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class CmdStats implements CommandExecutor {

    private final ErlynConfiguration config;
    private final ErlynDatabase db;

    public CmdStats(ErlynConfiguration config, ErlynDatabase db) {
        this.config = config;
        this.db = db;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            final Player player = (Player) sender;
            if (cmd.getName().equalsIgnoreCase("stats")) {
                if (args.length > 0) {
                    if (Bukkit.getOnlinePlayers().contains(Bukkit.getPlayer(args[0]))) {
                        final Player requested = Bukkit.getPlayer(args[0]);

                        final YamlConfiguration configuration_player_death = db.getPlayer(requested.getUniqueId().toString(), "death-" + requested.getUniqueId());
                        final int death = configuration_player_death.getInt("death");

                        final YamlConfiguration configuration_player_kill = db.getPlayer(requested.getUniqueId().toString(), "kill-" + requested.getUniqueId());
                        final int kill = configuration_player_kill.getInt("kill");

                        final String time_played = PlaceholderAPI.setPlaceholders(requested, "%statistic_time_played:hours%");
                        final String mobs_kills = PlaceholderAPI.setPlaceholders(requested, "%statistic_mob_kills%");

                        player.sendMessage("§7----- §6" + requested.getName() + " §7-----");
                        player.sendMessage("§7Nombre de morts : §e" + death);
                        player.sendMessage("§7Nombre mobs tués : §e" + mobs_kills);
                        player.sendMessage("§7Nombre joueurs tués : §e" + kill);
                        player.sendMessage("§7Temps de jeu : §e" + time_played + " heures");
                        player.sendMessage("§7-------------------");
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