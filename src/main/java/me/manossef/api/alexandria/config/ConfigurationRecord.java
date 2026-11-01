package me.manossef.api.alexandria.config;

import org.bukkit.configuration.file.FileConfiguration;

import java.util.logging.Logger;

/**
 * An interface intended to be implemented by records holding the values of a plugin's configuration options.
 */
public interface ConfigurationRecord {
    /**
     * Logs the values of all options handled by this record to the provided logger. This method is primarily meant to
     * be called when the plugin starts up, to inform the server owner of the values of a plugin's options.
     * @param logger The logger to log the options to.
     */
    void logOptions(Logger logger);

    /**
     * Saves the values of all options handled by this record to the provided file.
     * @param file The file to save the options to.
     */
    void saveToFile(FileConfiguration file);
}