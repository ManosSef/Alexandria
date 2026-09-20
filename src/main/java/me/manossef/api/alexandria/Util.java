package me.manossef.api.alexandria;

import org.bukkit.plugin.Plugin;

import java.util.logging.Level;

public final class Util {

    private Util() {
    }

    public static void logSevere(String message, Plugin plugin, boolean disablePlugin) {

        plugin.getLogger().log(Level.SEVERE, message + (disablePlugin ? " This plugin is being disabled." : ""));
        if(disablePlugin) plugin.getServer().getPluginManager().disablePlugin(plugin);

    }

    public static void disablePluginWithMessage(String message, Plugin plugin) {

        logSevere(message, plugin, false);
        plugin.getServer().getPluginManager().disablePlugin(plugin);

    }

}
