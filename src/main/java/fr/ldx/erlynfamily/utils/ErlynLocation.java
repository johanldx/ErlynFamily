package fr.ldx.erlynfamily.utils;

import fr.ldx.erlynfamily.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class ErlynLocation {

    private final Main main;
    private final ErlynConfiguration config;
    private final ErlynDatabase db;

    public ErlynLocation(Main main, ErlynConfiguration config, ErlynDatabase db) {
        this.main = main;
        this.config = config;
        this.db = db;
    }

    private int delay_teleportation;
    private final Map<String, Location> spawn = new HashMap<>();
    private final List<String> worlds = new ArrayList<>();

    public void initDelay() {
        delay_teleportation = config.getInt("teleportation_delay");
    }

    public void initSpawn() {

        for (World world : Bukkit.getWorlds()) {
            worlds.add(world.getName());
        }

        System.out.println(worlds);

        for (String monde : worlds) {
            if (db.getTeleportationFile("spawn-" + monde).exists()) {
                final YamlConfiguration configuration = db.getTeleportation("spawn-" + monde);

                final World world = Bukkit.getWorld(monde);
                final double x = configuration.getDouble("x");
                final double y = configuration.getDouble("y");
                final double z = configuration.getDouble("z");
                final float yaw = (float) configuration.getDouble("yaw");
                final float pitch = (float) configuration.getDouble("pitch");

                spawn.put(monde, new Location(world, x, y, z, yaw, pitch));
                System.out.println(config.getPlugin_name() + " - Spawn du monde " + monde + " initialisé !");
            }
            else {
                System.out.println(config.getPlugin_name() + " - Spawn du monde " + monde + " inexistant !");
            }
        }
    }

    public boolean setSpawn(Player player) {
        final File file = db.getTeleportationFile("spawn-" + player.getWorld().getName());
        final YamlConfiguration configuration = db.getTeleportation("spawn-" + player.getWorld().getName());

        configuration.set("world", Objects.requireNonNull(player.getLocation().getWorld()).getName());
        configuration.set("x", player.getLocation().getX());
        configuration.set("y", player.getLocation().getY());
        configuration.set("z", player.getLocation().getZ());
        configuration.set("yaw", player.getLocation().getYaw());
        configuration.set("pitch", player.getLocation().getPitch());

        try {
            configuration.save(file);
            spawn.put(player.getWorld().getName(), player.getLocation());
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Location getSpawn(Player player) {
        return spawn.get(player.getWorld().getName());
    }

    public void teleport(Player player, Location location, int delay) {
        if (player.hasPermission(config.getPlugin_permission() + "bypass")) {
            player.teleport(location);
            player.sendMessage(config.getString("teleportation_global_message_done"));
        }
        else {
            final Location location_before = player.getLocation();
            final double tolerance = 0.5;
            Bukkit.getScheduler().scheduleSyncDelayedTask(main, () -> {
                if (location_before.getWorld() == player.getLocation().getWorld()) {
                    if (player.getLocation().getX() > location_before.getX() - tolerance & player.getLocation().getX() < location_before.getX() + tolerance) {
                        if (player.getLocation().getY() > location_before.getY() - tolerance & player.getLocation().getY() < location_before.getY() + tolerance) {
                            if (player.getLocation().getZ() > location_before.getZ() - tolerance & player.getLocation().getZ() < location_before.getZ() + tolerance) {
                                player.teleport(location);
                                player.sendMessage(config.getString("teleportation_global_message_done"));
                            }
                            else {
                                player.sendMessage(config.getString("teleportation_global_message_playermove"));
                            }
                        }
                        else {
                            player.sendMessage(config.getString("teleportation_global_message_playermove"));
                        }
                    }
                    else {
                        player.sendMessage(config.getString("teleportation_global_message_playermove"));
                    }
                }
                else {
                    player.sendMessage(config.getString("teleportation_global_message_playermove"));
                }
            }, 20L * delay);
        }
    }

    public int getDelay_teleportation() {
        return delay_teleportation;
    }
}
