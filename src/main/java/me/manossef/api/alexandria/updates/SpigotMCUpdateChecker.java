package me.manossef.api.alexandria.updates;

import org.bukkit.plugin.Plugin;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Instances of this class can check resources on SpigotMC for updates.
 */
public class SpigotMCUpdateChecker extends SimpleUpdateChecker {
    /**
     * Constructs a new update checker for a resource.
     * @param plugin The resource in its current version.
     * @param resourceId The numeric ID of the resource on SpigotMC.
     * @param resourceWebName The URL name of the resource on SpigotMC.
     */
    public SpigotMCUpdateChecker(Plugin plugin, int resourceId, String resourceWebName) throws MalformedURLException {
        super(plugin, new URL("https://api.spigotmc.org/legacy/update.php?resource=" + resourceId),
            new URL("https://www.spigotmc.org/resources/" + resourceWebName + "." + resourceId + "/"));
    }
}