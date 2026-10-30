package me.manossef.api.alexandria.config;

import org.bukkit.configuration.file.FileConfiguration;

import java.util.logging.Logger;

public interface ConfigurationRecord {
    void logOptions(Logger logger);

    void saveToFile(FileConfiguration file);
}