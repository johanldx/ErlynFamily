package fr.ldx.erlynfamily;

import fr.ldx.erlynfamily.commands.*;
import fr.ldx.erlynfamily.events.PlayerJoinQuit;
import fr.ldx.erlynfamily.events.PlayerMessage;
import fr.ldx.erlynfamily.events.PlayerDeath;
import fr.ldx.erlynfamily.events.PlayerRespawn;
import fr.ldx.erlynfamily.utils.ErlynConfiguration;
import fr.ldx.erlynfamily.utils.ErlynDatabase;
import fr.ldx.erlynfamily.utils.ErlynLocation;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {

    @Override
    public void onLoad() {
        super.onLoad();
        saveDefaultConfig();
        reloadConfig();
        this.loadMessage();
    }

    @Override
    public void onEnable() {
        super.onEnable();
        final ErlynConfiguration config = new ErlynConfiguration();
        final ErlynDatabase db = new ErlynDatabase();
        final ErlynLocation loc = new ErlynLocation(this, config, db);

        loc.initDelay();
        loc.initSpawn();

        getCommand("spawn").setExecutor(new CmdSpawn(config, db, loc));
        getCommand("setspawn").setExecutor(new CmdSetSpawn(config, loc));
        getCommand("annonce").setExecutor(new CmdAnnonce(config));
        getCommand("wiki").setExecutor(new CmdWiki(config));
        getCommand("msg").setExecutor(new CmdMsg(config));
        getCommand("kick").setExecutor(new CmdKick(config));
        getCommand("kiss").setExecutor(new CmdKiss(config));
        getCommand("stats").setExecutor(new CmdStats(config, db));

        getServer().getPluginManager().registerEvents(new PlayerJoinQuit(config, db), this);
        getServer().getPluginManager().registerEvents(new PlayerDeath(config, db), this);
        getServer().getPluginManager().registerEvents(new PlayerMessage(config), this);
        getServer().getPluginManager().registerEvents(new PlayerRespawn(config, db, loc), this);

        this.enableMessage();
    }

    @Override
    public void onDisable() {
        super.onDisable();
        this.disableMessage();
    }

    private void loadMessage() {
        System.out.println(" ");
        System.out.println("@@@@@@@@@  &@@@@@@@@#   (@@@.     @@@@    %@@@& &@@@@    /@@@,");
        System.out.println("@@@@.      &@@@***@@@@/ (@@@.      @@@@  &@@@,  &@@@@@*  /@@@,");
        System.out.println("@@@@@@@@%  &@@@   %@@@* (@@@.       %@@@@@@@    &@@@@@@@ /@@@,");
        System.out.println("@@@@%%%%/  &@@@@@@@@,   (@@@.        ,@@@@@     &@@@ .@@@@@@@,");
        System.out.println("@@@@.....  &@@@ ,@@@@   (@@@,         &@@@      &@@@   %@@@@@,");
        System.out.println("@@@@@@@@@  &@@@   @@@@. (@@@@@@@%     &@@@      &@@@     @@@@,");
        System.out.println(" ");
        System.out.println("Erlyn Family - Version BETA 1.1.0");
        System.out.println("Chargement en cours...");
        System.out.println(" ");
    }

    private void enableMessage() {
        System.out.println("Erlyn Family - Plugin chargé avec succès !");
    }

    private void disableMessage() {
        System.out.println("Erlyn Family - Plugin déchargé avec succès !");
    }
}