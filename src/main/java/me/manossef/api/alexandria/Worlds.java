package me.manossef.api.alexandria;

import org.bukkit.Bukkit;
import org.bukkit.World;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

public final class Worlds {

    private static String mainWorldNameCache;

    private Worlds() {
    }

    public static boolean delete(World w) {

        File dir = w.getWorldFolder();
        Bukkit.unloadWorld(w, false);
        try {

            return dir.delete();

        } catch(SecurityException e) {

            return false;

        }

    }

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
