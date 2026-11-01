package me.manossef.api.alexandria;

import org.bukkit.Bukkit;
import org.bukkit.World;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

/**
 * Contains utility methods related to worlds.
 */
public final class Worlds {
    private static String mainWorldNameCache;

    private Worlds() {
    }

    /**
     * Unloads the provided world and deletes its folder.
     * @param world The world to delete.
     * @return <code>true</code> if the world was successfully deleted, and <code>false</code> otherwise.
     */
    public static boolean delete(World world) {
        File dir = world.getWorldFolder();
        Bukkit.unloadWorld(world, false);
        try {
            return dir.delete();
        } catch(SecurityException e) {
            return false;
        }
    }

    /**
     * Returns the server's main world. The main world is the Overworld of the world the server automatically creates
     * and loads on startup. In other words, it's the world named after the <code>level-name</code> server property.
     * @return The server's main world.
     */
    public static World getMainWorld() {
        Properties properties = new Properties();
        if(mainWorldNameCache == null) {
            try {
                FileInputStream file = new FileInputStream("server.properties");
                properties.load(file);
                mainWorldNameCache = properties.getProperty("level-name");
                file.close();
            } catch(IOException e) {
                return null;
            }
        }
        return Bukkit.getWorld(Objects.requireNonNullElse(mainWorldNameCache, "world"));
    }
}