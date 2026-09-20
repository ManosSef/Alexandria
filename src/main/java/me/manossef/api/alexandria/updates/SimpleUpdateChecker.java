package me.manossef.api.alexandria.updates;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;
import java.util.logging.Level;

public class SimpleUpdateChecker implements UpdateChecker {

    private final Plugin plugin;
    private final URL updateCheckUrl;
    private final URL downloadUrl;

    /**
     * Constructs a new update checker for a resource.
     *
     * @param plugin         The resource in its current version.
     * @param updateCheckUrl The URL to visit to check for updates.
     * @param downloadUrl    The URL to point server owners to so they can download a newer version of the resource.
     */
    public SimpleUpdateChecker(Plugin plugin, URL updateCheckUrl, URL downloadUrl) {

        this.plugin = plugin;
        this.updateCheckUrl = updateCheckUrl;
        this.downloadUrl = downloadUrl;

    }

    /**
     * Checks the resource for updates.
     */
    public void checkForUpdates() {

        String currentVersion = plugin.getDescription().getVersion();
        if(!UpdateChecker.isStable(currentVersion)) {

            plugin.getLogger().log(Level.WARNING,
                "This server is running a development version of " + plugin.getName() + ", " + currentVersion + ".");
            plugin.getLogger().log(Level.WARNING, "Issues may arise; you have been warned.");
            return;

        }
        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {

            try {

                InputStream stream = updateCheckUrl.openStream();
                Scanner scan = new Scanner(stream);
                if(scan.hasNext()) {

                    String newVersion = scan.next();
                    scan.close();
                    int comparison = UpdateChecker.compareVersions(currentVersion, newVersion);
                    if(comparison < 0) {

                        plugin.getLogger().log(Level.WARNING, "This server is running an older version of "
                            + plugin.getName() + ", " + currentVersion + ".");
                        plugin.getLogger().log(Level.WARNING, "There is a new version available: " + newVersion + ".");
                        plugin.getLogger().log(Level.WARNING,
                            "You can download it here: " + downloadUrl);

                    } else if(comparison > 0) {

                        plugin.getLogger().log(Level.INFO, "This server is running a soon to be released version of "
                            + plugin.getName() + ", " + currentVersion + ".");
                        plugin.getLogger().log(Level.INFO, "Last version I knew of was " + newVersion + ".");
                        plugin.getLogger().log(Level.INFO,
                            "Thank you developer, for enlightening me on your short term plans.");

                    }

                }

            } catch(IOException e) {

                plugin.getLogger().log(Level.WARNING, "Failed to check for updates for " + plugin.getName() + "!");

            }

        });

    }

    /**
     * Returns the resource's name.
     *
     * @return The resource's name.
     */
    public String getPluginName() {

        return this.plugin.getName();

    }

}
