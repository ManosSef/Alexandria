package me.manossef.api.alexandria;

import org.bukkit.plugin.Plugin;

import java.util.logging.Level;

/**
 * Contains various utility methods.
 */
public final class Util {
    private Util() {
    }

    /**
     * Logs a {@link Level#SEVERE} message on behalf of the provided plugin, optionally disabling it as well.
     * @param message The message to log.
     * @param plugin The plugin to log the message on behalf of.
     * @param disablePlugin Whether the plugin should be disabled. The string
     *                      <code>" This plugin is being disabled."</code> will be appended to the message if this is
     *                      set to <code>true</code>.
     */
    public static void logSevere(String message, Plugin plugin, boolean disablePlugin) {
        plugin.getLogger().log(Level.SEVERE, message + (disablePlugin ? " This plugin is being disabled." : ""));
        if(disablePlugin) plugin.getServer().getPluginManager().disablePlugin(plugin);
    }

    /**
     * Disabled the provided plugin after logging the specified message with the {@link Level#SEVERE} level. Unlike
     * {@link #logSevere(String, Plugin, boolean)}, this method does not append anything to the message.
     * @param message The message to log.
     * @param plugin The plugin to disable.
     */
    public static void disablePluginWithMessage(String message, Plugin plugin) {
        logSevere(message, plugin, false);
        plugin.getServer().getPluginManager().disablePlugin(plugin);
    }
}