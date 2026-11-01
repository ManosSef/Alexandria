package me.manossef.api.alexandria.config;

import me.manossef.api.alexandria.TypeChecks;
import me.manossef.api.alexandria.Util;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

import java.util.List;

/**
 * Contains methods for checking whether an option in a configuration file is present or has a value of the correct
 * type. Methods starting with <code>check</code> are intended to be called on startup, while methods starting with
 * <code>recheck</code> are intended to be called on reloads.
 */
public final class OptionValidations {
    private OptionValidations() {
    }

    /**
     * Checks if the option at the specified path in the provided plugin's <code>config.yml</code> file has been set to
     * a value, and disables the provided plugin otherwise.
     * @param path The path to check.
     * @param plugin The plugin whose configuration is to be checked.
     * @return <code>true</code> if the option has been set, and <code>false</code> otherwise.
     */
    public static boolean checkRequired(String path, Plugin plugin) {
        return checkRequired(path, plugin, plugin.getConfig());
    }

    /**
     * Checks if the option at the specified path in the provided configuration file has been set to a value, and
     * disables the provided plugin otherwise.
     * @param path The path to check.
     * @param plugin The plugin whose configuration is to be checked.
     * @param config The configuration file to check.
     * @return <code>true</code> if the option has been set, and <code>false</code> otherwise.
     */
    public static boolean checkRequired(String path, Plugin plugin, FileConfiguration config) {
        return checkRequired0(path, plugin, config, true);
    }

    /**
     * Checks if the option at the specified path in the provided plugin's <code>config.yml</code> file has been set to
     * a value, and logs a warning on behalf of the provided plugin otherwise.
     * @param path The path to check.
     * @param plugin The plugin whose configuration is to be checked.
     * @return <code>true</code> if the option has been set, and <code>false</code> otherwise.
     */
    public static boolean recheckRequired(String path, Plugin plugin) {
        return recheckRequired(path, plugin, plugin.getConfig());
    }

    /**
     * Checks if the option at the specified path in the provided configuration file has been set to a value, and logs a
     * warning on behalf of the provided plugin otherwise.
     * @param path The path to check.
     * @param plugin The plugin whose configuration is to be checked.
     * @param config The configuration file to check.
     * @return <code>true</code> if the option has been set, and <code>false</code> otherwise.
     */
    public static boolean recheckRequired(String path, Plugin plugin, FileConfiguration config) {
        return checkRequired0(path, plugin, config, false);
    }

    private static boolean checkRequired0(String path, Plugin plugin, FileConfiguration config, boolean disablePlugin) {
        Object value = config.get(path);
        if(value == null) {
            Util.logSevere("Found that \"" + path + "\" has not been set to a value.", plugin, disablePlugin);
            return false;
        }
        return true;
    }

    /**
     * Disables the provided plugin if the option at the specified path in its <code>config.yml</code> file has been set
     * to a value that cannot safely be converted to a <code>boolean</code>.
     * @param path The path to check.
     * @param plugin The plugin whose configuration is to be checked.
     * @return <code>false</code> if the option is not a valid boolean option, and <code>true</code> otherwise.
     */
    public static boolean checkBooleanOption(String path, Plugin plugin) {
        return checkBooleanOption(path, plugin, plugin.getConfig());
    }

    /**
     * Disables the provided plugin if the option at the specified path in the provided configuration file has been set
     * to a value that cannot safely be converted to a <code>boolean</code>.
     * @param path The path to check.
     * @param plugin The plugin whose configuration is to be checked.
     * @param config The configuration file to check.
     * @return <code>false</code> if the option is not a valid boolean option, and <code>true</code> otherwise.
     */
    public static boolean checkBooleanOption(String path, Plugin plugin, FileConfiguration config) {
        return checkBooleanOption0(path, plugin, config, true);
    }

    /**
     * Logs a warning on behalf of the provided plugin if the option at the specified path in its
     * <code>config.yml</code> file has been set to a value that cannot safely be converted to a <code>boolean</code>.
     * @param path The path to check.
     * @param plugin The plugin whose configuration is to be checked.
     * @return <code>false</code> if the option is not a valid boolean option, and <code>true</code> otherwise.
     */
    public static boolean recheckBooleanOption(String path, Plugin plugin) {
        return recheckBooleanOption(path, plugin, plugin.getConfig());
    }

    /**
     * Logs a warning on behalf of the provided plugin if the option at the specified path in the provided configuration
     * file has been set to a value that cannot safely be converted to a <code>boolean</code>.
     * @param path The path to check.
     * @param plugin The plugin whose configuration is to be checked.
     * @param config The configuration file to check.
     * @return <code>false</code> if the option is not a valid boolean option, and <code>true</code> otherwise.
     */
    public static boolean recheckBooleanOption(String path, Plugin plugin, FileConfiguration config) {
        return checkBooleanOption0(path, plugin, config, false);
    }

    private static boolean checkBooleanOption0(String path, Plugin plugin, FileConfiguration config, boolean disablePlugin) {
        Object value = config.get(path);
        if(value != null)
            if(!TypeChecks.isBoolean(value.toString())) {
                Util.logSevere("Found that \"" + path + "\" has been set to a value that is not true or false.", plugin, disablePlugin);
                return false;
            }
        return true;
    }

    /**
     * Returns <code>true</code>.
     * @param path The path to check.
     * @param plugin The plugin whose configuration is to be checked.
     * @return <code>true</code>.
     */
    public static boolean checkDebugOption(String path, Plugin plugin) {
        return checkDebugOption(path, plugin, plugin.getConfig());
    }

    /**
     * Returns <code>true</code>.
     * @param path The path to check.
     * @param plugin The plugin whose configuration is to be checked.
     * @param config The configuration file to check.
     * @return <code>true</code>.
     */
    public static boolean checkDebugOption(String path, Plugin plugin, FileConfiguration config) {
        return true;
    }

    /**
     * Returns <code>true</code>.
     * @param path The path to check.
     * @param plugin The plugin whose configuration is to be checked.
     * @return <code>true</code>.
     */
    public static boolean recheckDebugOption(String path, Plugin plugin) {
        return recheckDebugOption(path, plugin, plugin.getConfig());
    }

    /**
     * Returns <code>true</code>.
     * @param path The path to check.
     * @param plugin The plugin whose configuration is to be checked.
     * @param config The configuration file to check.
     * @return <code>true</code>.
     */
    public static boolean recheckDebugOption(String path, Plugin plugin, FileConfiguration config) {
        return true;
    }

    /**
     * Disables the provided plugin if the option at the specified path in its <code>config.yml</code> file has been set
     * to a value that cannot safely be converted to an <code>int</code>.
     * @param path The path to check.
     * @param plugin The plugin whose configuration is to be checked.
     * @return <code>false</code> if the option is not a valid integer option, and <code>true</code> otherwise.
     */
    public static boolean checkIntOption(String path, Plugin plugin) {
        return checkIntOption(path, plugin, plugin.getConfig());
    }

    /**
     * Disables the provided plugin if the option at the specified path in the provided configuration file has been set
     * to a value that cannot safely be converted to an <code>int</code>.
     * @param path The path to check.
     * @param plugin The plugin whose configuration is to be checked.
     * @param config The configuration file to check.
     * @return <code>false</code> if the option is not a valid integer option, and <code>true</code> otherwise.
     */
    public static boolean checkIntOption(String path, Plugin plugin, FileConfiguration config) {
        return checkIntOption(path, Integer.MIN_VALUE, plugin, config);
    }

    /**
     * Disables the provided plugin if the option at the specified path in its <code>config.yml</code> file has been set
     * to a value that cannot safely be converted to an <code>int</code> greater than or equal to the specified value.
     * @param path The path to check.
     * @param min The minimum value.
     * @param plugin The plugin whose configuration is to be checked.
     * @return <code>false</code> if the option is not a valid integer option or not in the specified range, and
     * <code>true</code> otherwise.
     */
    public static boolean checkIntOption(String path, int min, Plugin plugin) {
        return checkIntOption(path, min, plugin, plugin.getConfig());
    }

    /**
     * Disables the provided plugin if the option at the specified path in the provided configuration file has been set
     * to a value that cannot safely be converted to an <code>int</code> greater than or equal to the specified value.
     * @param path The path to check.
     * @param min The minimum value.
     * @param plugin The plugin whose configuration is to be checked.
     * @param config The configuration file to check.
     * @return <code>false</code> if the option is not a valid integer option or not in the specified range, and
     * <code>true</code> otherwise.
     */
    public static boolean checkIntOption(String path, int min, Plugin plugin, FileConfiguration config) {
        return checkIntOption(path, min, Integer.MAX_VALUE, plugin, config);
    }

    /**
     * Disables the provided plugin if the option at the specified path in its <code>config.yml</code> file has been set
     * to a value that cannot safely be converted to an <code>int</code> between the specified values (inclusive).
     * @param path The path to check.
     * @param min The minimum value.
     * @param max The maximum value.
     * @param plugin The plugin whose configuration is to be checked.
     * @return <code>false</code> if the option is not a valid integer option or not in the specified range, and
     * <code>true</code> otherwise.
     * @throws IllegalArgumentException If <code>min</code> is greater than <code>max</code>.
     */
    public static boolean checkIntOption(String path, int min, int max, Plugin plugin) {
        return checkIntOption(path, min, max, plugin, plugin.getConfig());
    }

    /**
     * Disables the provided plugin if the option at the specified path in the provided configuration file has been set
     * to a value that cannot safely be converted to an <code>int</code> between the specified values (inclusive).
     * @param path The path to check.
     * @param min The minimum value.
     * @param max The maximum value.
     * @param plugin The plugin whose configuration is to be checked.
     * @param config The configuration file to check.
     * @return <code>false</code> if the option is not a valid integer option or not in the specified range, and
     * <code>true</code> otherwise.
     * @throws IllegalArgumentException If <code>min</code> is greater than <code>max</code>.
     */
    public static boolean checkIntOption(String path, int min, int max, Plugin plugin, FileConfiguration config) {
        return checkIntOption0(path, min, max, plugin, config, true);
    }

    /**
     * Logs a warning on behalf of the provided plugin if the option at the specified path in its
     * <code>config.yml</code> file has been set to a value that cannot safely be converted to an <code>int</code>.
     * @param path The path to check.
     * @param plugin The plugin whose configuration is to be checked.
     * @return <code>false</code> if the option is not a valid integer option, and <code>true</code> otherwise.
     */
    public static boolean recheckIntOption(String path, Plugin plugin) {
        return recheckIntOption(path, plugin, plugin.getConfig());
    }

    /**
     * Logs a warning on behalf of the provided plugin if the option at the specified path in the provided configuration
     * file has been set to a value that cannot safely be converted to an <code>int</code>.
     * @param path The path to check.
     * @param plugin The plugin whose configuration is to be checked.
     * @param config The configuration file to check.
     * @return <code>false</code> if the option is not a valid integer option, and <code>true</code> otherwise.
     */
    public static boolean recheckIntOption(String path, Plugin plugin, FileConfiguration config) {
        return recheckIntOption(path, Integer.MIN_VALUE, plugin, config);
    }

    /**
     * Logs a warning on behalf of the provided plugin if the option at the specified path in its
     * <code>config.yml</code> file has been set to a value that cannot safely be converted to an <code>int</code>
     * greater than or equal to the specified value.
     * @param path The path to check.
     * @param min The minimum value.
     * @param plugin The plugin whose configuration is to be checked.
     * @return <code>false</code> if the option is not a valid integer option or not in the specified range, and
     * <code>true</code> otherwise.
     */
    public static boolean recheckIntOption(String path, int min, Plugin plugin) {
        return recheckIntOption(path, min, plugin, plugin.getConfig());
    }

    /**
     * Logs a warning on behalf of the provided plugin if the option at the specified path in the provided configuration
     * file has been set to a value that cannot safely be converted to an <code>int</code> greater than or equal to the
     * specified value.
     * @param path The path to check.
     * @param min The minimum value.
     * @param plugin The plugin whose configuration is to be checked.
     * @param config The configuration file to check.
     * @return <code>false</code> if the option is not a valid integer option or not in the specified range, and
     * <code>true</code> otherwise.
     */
    public static boolean recheckIntOption(String path, int min, Plugin plugin, FileConfiguration config) {
        return recheckIntOption(path, min, Integer.MAX_VALUE, plugin, config);
    }

    /**
     * Logs a warning on behalf of the provided plugin if the option at the specified path in its
     * <code>config.yml</code> file has been set to a value that cannot safely be converted to an <code>int</code>
     * between the specified values (inclusive).
     * @param path The path to check.
     * @param min The minimum value.
     * @param max The maximum value.
     * @param plugin The plugin whose configuration is to be checked.
     * @return <code>false</code> if the option is not a valid integer option or not in the specified range, and
     * <code>true</code> otherwise.
     * @throws IllegalArgumentException If <code>min</code> is greater than <code>max</code>.
     */
    public static boolean recheckIntOption(String path, int min, int max, Plugin plugin) {
        return recheckIntOption(path, min, max, plugin, plugin.getConfig());
    }

    /**
     * Logs a warning on behalf of the provided plugin if the option at the specified path in the provided configuration
     * file has been set to a value that cannot safely be converted to an <code>int</code> between the specified values
     * (inclusive).
     * @param path The path to check.
     * @param min The minimum value.
     * @param max The maximum value.
     * @param plugin The plugin whose configuration is to be checked.
     * @param config The configuration file to check.
     * @return <code>false</code> if the option is not a valid integer option or not in the specified range, and
     * <code>true</code> otherwise.
     * @throws IllegalArgumentException If <code>min</code> is greater than <code>max</code>.
     */
    public static boolean recheckIntOption(String path, int min, int max, Plugin plugin, FileConfiguration config) {
        return checkIntOption0(path, min, max, plugin, config, false);
    }

    private static boolean checkIntOption0(String path, int min, int max, Plugin plugin, FileConfiguration config, boolean disablePlugin) {
        if(min > max) throw new IllegalArgumentException("The minimum value cannot be greater than the maximum value");
        Object value = config.get(path);
        if(value != null) {
            if(!TypeChecks.isInt(value.toString())) {
                Util.logSevere("Found that \"" + path + "\" has been set to a value that is not an integer.", plugin, disablePlugin);
                return false;
            }
            int intValue = config.getInt(path);
            if(intValue < min) {
                Util.logSevere("Found that \"" + path + "\" has been set to a value that is less than " + min + ".", plugin, disablePlugin);
                return false;
            }
            if(intValue > max) {
                Util.logSevere("Found that \"" + path + "\" has been set to a value that is greater than " + max + ".", plugin, disablePlugin);
                return false;
            }
        }
        return true;
    }

    /**
     * Disables the provided plugin if the options at the specified path in its <code>config.yml</code> file with
     * <code>.min</code> and <code>.max</code> appended to it have been set to values that cannot safely be converted
     * to <code>int</code>s, or if one of them is set while the other isn't, or if the <code>.min</code> option is set
     * to a value greater than the <code>.max</code> option.
     * @param path The path to check.
     * @param plugin The plugin whose configuration is to be checked.
     * @return <code>false</code> if the option is not a valid min-max integer option, and <code>true</code> otherwise.
     */
    public static boolean checkMinMaxIntOption(String path, Plugin plugin) {
        return checkMinMaxIntOption(path, plugin, plugin.getConfig());
    }

    /**
     * Disables the provided plugin if the options at the specified path in the provided configuration file with
     * <code>.min</code> and <code>.max</code> appended to it have been set to values that cannot safely be converted to
     * <code>int</code>s, or if one of them is set while the other isn't, or if the <code>.min</code> option is set to
     * a value greater than the <code>.max</code> option.
     * @param path The path to check.
     * @param plugin The plugin whose configuration is to be checked.
     * @param config The configuration file to check.
     * @return <code>false</code> if the option is not a valid min-max integer option, and <code>true</code> otherwise.
     */
    public static boolean checkMinMaxIntOption(String path, Plugin plugin, FileConfiguration config) {
        return checkMinMaxIntOption(path, Integer.MIN_VALUE, plugin, config);
    }

    /**
     * Disables the provided plugin if the options at the specified path in its <code>config.yml</code> file with
     * <code>.min</code> and <code>.max</code> appended to it have been set to values that cannot safely be converted to
     * <code>int</code>s, or if one of them is set while the other isn't, or if the <code>.min</code> option is set to
     * a value greater than the <code>.max</code> option, or if either of them has been set to a value less than the
     * specified value.
     * @param path The path to check.
     * @param min The minimum value.
     * @param plugin The plugin whose configuration is to be checked.
     * @return <code>false</code> if the option is not a valid min-max integer option or not in the specified range, and
     * <code>true</code> otherwise.
     */
    public static boolean checkMinMaxIntOption(String path, int min, Plugin plugin) {
        return checkMinMaxIntOption(path, min, plugin, plugin.getConfig());
    }

    /**
     * Disables the provided plugin if the options at the specified path in the provided configuration file with
     * <code>.min</code> and <code>.max</code> appended to it have been set to values that cannot safely be converted to
     * <code>int</code>s, or if one of them is set while the other isn't, or if the <code>.min</code> option is set to
     * a value greater than the <code>.max</code> option, or if either of them has been set to a value less than the
     * specified value.
     * @param path The path to check.
     * @param min The minimum value.
     * @param plugin The plugin whose configuration is to be checked.
     * @param config The configuration file to check.
     * @return <code>false</code> if the option is not a valid min-max integer option or not in the specified range, and
     * <code>true</code> otherwise.
     */
    public static boolean checkMinMaxIntOption(String path, int min, Plugin plugin, FileConfiguration config) {
        return checkMinMaxIntOption(path, min, Integer.MAX_VALUE, plugin, config);
    }

    /**
     * Disables the provided plugin if the options at the specified path in its <code>config.yml</code> file with
     * <code>.min</code> and <code>.max</code> appended to it have been set to values that cannot safely be converted to
     * <code>int</code>s, or if one of them is set while the other isn't, or if the <code>.min</code> option is set to
     * a value greater than the <code>.max</code> option, or if either of them has been set to a value outside the
     * specified values.
     * @param path The path to check.
     * @param min The minimum value.
     * @param max The maximum value.
     * @param plugin The plugin whose configuration is to be checked.
     * @return <code>false</code> if the option is not a valid min-max integer option or not in the specified range, and
     * <code>true</code> otherwise.
     */
    public static boolean checkMinMaxIntOption(String path, int min, int max, Plugin plugin) {
        return checkMinMaxIntOption(path, min, max, plugin, plugin.getConfig());
    }

    /**
     * Disables the provided plugin if the options at the specified path in the provided configuration file with
     * <code>.min</code> and <code>.max</code> appended to it have been set to values that cannot safely be converted
     * to <code>int</code>s, or if one of them is set while the other isn't, or if the <code>.min</code> option is set
     * to a value greater than the <code>.max</code> option, or if either of them has been set to a value outside the
     * specified values.
     * @param path The path to check.
     * @param min The minimum value.
     * @param max The maximum value.
     * @param plugin The plugin whose configuration is to be checked.
     * @param config The configuration file to check.
     * @return <code>false</code> if the option is not a valid min-max integer option or not in the specified range, and
     * <code>true</code> otherwise.
     */
    public static boolean checkMinMaxIntOption(String path, int min, int max, Plugin plugin, FileConfiguration config) {
        return checkMinMaxIntOption0(path, min, max, plugin, config, true);
    }

    /**
     * Logs a warning on behalf of the provided plugin if the options at the specified path in its
     * <code>config.yml</code> file with <code>.min</code> and <code>.max</code> appended to it have been set to values
     * that cannot safely be converted to <code>int</code>s, or if one of them is set while the other isn't, or if the
     * <code>.min</code> option is set to a value greater than the <code>.max</code> option.
     * @param path The path to check.
     * @param plugin The plugin whose configuration is to be checked.
     * @return <code>false</code> if the option is not a valid min-max integer option, and <code>true</code> otherwise.
     */
    public static boolean recheckMinMaxIntOption(String path, Plugin plugin) {
        return recheckMinMaxIntOption(path, plugin, plugin.getConfig());
    }

    /**
     * Logs a warning on behalf of the provided plugin if the options at the specified path in the provided
     * configuration file with <code>.min</code> and <code>.max</code> appended to it have been set to values that
     * cannot safely be converted to <code>int</code>s, or if one of them is set while the other isn't, or if the
     * <code>.min</code> option is set to a value greater than the <code>.max</code> option.
     * @param path The path to check.
     * @param plugin The plugin whose configuration is to be checked.
     * @param config The configuration file to check.
     * @return <code>false</code> if the option is not a valid min-max integer option, and <code>true</code> otherwise.
     */
    public static boolean recheckMinMaxIntOption(String path, Plugin plugin, FileConfiguration config) {
        return recheckMinMaxIntOption(path, Integer.MIN_VALUE, plugin, config);
    }

    /**
     * Logs a warning on behalf of the provided plugin if the options at the specified path in its
     * <code>config.yml</code> file with <code>.min</code> and <code>.max</code> appended to it have been set to values
     * that cannot safely be converted to <code>int</code>s, or if one of them is set while the other isn't, or if the
     * <code>.min</code> option is set to a value greater than the <code>.max</code> option, or if either of them has
     * been set to a value less than the specified value.
     * @param path The path to check.
     * @param min The minimum value.
     * @param plugin The plugin whose configuration is to be checked.
     * @return <code>false</code> if the option is not a valid min-max integer option or not in the specified range, and
     * <code>true</code> otherwise.
     */
    public static boolean recheckMinMaxIntOption(String path, int min, Plugin plugin) {
        return recheckMinMaxIntOption(path, min, plugin, plugin.getConfig());
    }

    /**
     * Logs a warning on behalf of the provided plugin if the options at the specified path in the provided
     * configuration file with <code>.min</code> and <code>.max</code> appended to it have been set to values that
     * cannot safely be converted to <code>int</code>s, or if one of them is set while the other isn't, or if the
     * <code>.min</code> option is set to a value greater than the <code>.max</code> option, or if either of them has
     * been set to a value less than the specified value.
     * @param path The path to check.
     * @param min The minimum value.
     * @param plugin The plugin whose configuration is to be checked.
     * @param config The configuration file to check.
     * @return <code>false</code> if the option is not a valid min-max integer option or not in the specified range, and
     * <code>true</code> otherwise.
     */
    public static boolean recheckMinMaxIntOption(String path, int min, Plugin plugin, FileConfiguration config) {
        return recheckMinMaxIntOption(path, min, Integer.MAX_VALUE, plugin, config);
    }

    /**
     * Logs a warning on behalf of the provided plugin if the options at the specified path in its
     * <code>config.yml</code> file with <code>.min</code> and <code>.max</code> appended to it have been set to values
     * that cannot safely be converted to <code>int</code>s, or if one of them is set while the other isn't, or if the
     * <code>.min</code> option is set to a value greater than the <code>.max</code> option, or if either of them has
     * been set to a value outside the specified values.
     * @param path The path to check.
     * @param min The minimum value.
     * @param max The maximum value.
     * @param plugin The plugin whose configuration is to be checked.
     * @return <code>false</code> if the option is not a valid min-max integer option or not in the specified range, and
     * <code>true</code> otherwise.
     */
    public static boolean recheckMinMaxIntOption(String path, int min, int max, Plugin plugin) {
        return recheckMinMaxIntOption(path, min, max, plugin, plugin.getConfig());
    }

    /**
     * Logs a warning on behalf of the provided plugin if the options at the specified path in the provided
     * configuration file with <code>.min</code> and <code>.max</code> appended to it have been set to values that
     * cannot safely be converted to <code>int</code>s, or if one of them is set while the other isn't, or if the
     * <code>.min</code> option is set to a value greater than the <code>.max</code> option, or if either of them has
     * been set to a value outside the specified values.
     * @param path The path to check.
     * @param min The minimum value.
     * @param max The maximum value.
     * @param plugin The plugin whose configuration is to be checked.
     * @param config The configuration file to check.
     * @return <code>false</code> if the option is not a valid min-max integer option or not in the specified range, and
     * <code>true</code> otherwise.
     */
    public static boolean recheckMinMaxIntOption(String path, int min, int max, Plugin plugin, FileConfiguration config) {
        return checkMinMaxIntOption0(path, min, max, plugin, config, false);
    }

    private static boolean checkMinMaxIntOption0(String path, int min, int max, Plugin plugin, FileConfiguration config, boolean disablePlugin) {
        if(!checkIntOption0(path + ".min", min, max, plugin, config, disablePlugin)) return false;
        if(!checkIntOption0(path + ".max", min, max, plugin, config, disablePlugin)) return false;
        Object minValue = config.get(path + ".min");
        Object maxValue = config.get(path + ".max");
        if(minValue == null && maxValue == null) return true;
        if(minValue == null) {
            Util.logSevere("Found that \"" + path + "\" has a maximum value but not a minimum value.", plugin, disablePlugin);
            return false;
        }
        if(maxValue == null) {
            Util.logSevere("Found that \"" + path + "\" has a minimum value but not a maximum value.", plugin, disablePlugin);
            return false;
        }
        if(config.getInt(path + ".min") > config.getInt(path + ".max")) {
            Util.logSevere("Found that the minimum value of \"" + path + "\" is greater than its maximum value.", plugin, disablePlugin);
            return false;
        }
        return true;
    }

    /**
     * Disables the provided plugin if the option at the specified path in its <code>config.yml</code> file has been set
     * to a value that cannot safely be converted to a <code>double</code>.
     * @param path The path to check.
     * @param plugin The plugin whose configuration is to be checked.
     * @return <code>false</code> if the option is not a valid double option, and <code>true</code> otherwise.
     */
    public static boolean checkDoubleOption(String path, Plugin plugin) {
        return checkDoubleOption(path, plugin, plugin.getConfig());
    }

    /**
     * Disables the provided plugin if the option at the specified path in the provided configuration file has been set
     * to a value that cannot safely be converted to a <code>double</code>.
     * @param path The path to check.
     * @param plugin The plugin whose configuration is to be checked.
     * @param config The configuration file to check.
     * @return <code>false</code> if the option is not a valid double option, and <code>true</code> otherwise.
     */
    public static boolean checkDoubleOption(String path, Plugin plugin, FileConfiguration config) {
        return checkDoubleOption(path, Double.NEGATIVE_INFINITY, plugin, config);
    }

    /**
     * Disables the provided plugin if the option at the specified path in its <code>config.yml</code> file has been set
     * to a value that cannot safely be converted to a <code>double</code> greater than or equal to the specified value.
     * @param path The path to check.
     * @param min The minimum value.
     * @param plugin The plugin whose configuration is to be checked.
     * @return <code>false</code> if the option is not a valid double option or not in the specified range, and
     * <code>true</code> otherwise.
     */
    public static boolean checkDoubleOption(String path, double min, Plugin plugin) {
        return checkDoubleOption(path, min, plugin, plugin.getConfig());
    }

    /**
     * Disables the provided plugin if the option at the specified path in the provided configuration file has been set
     * to a value that cannot safely be converted to a <code>double</code> greater than or equal to the specified value.
     * @param path The path to check.
     * @param min The minimum value.
     * @param plugin The plugin whose configuration is to be checked.
     * @param config The configuration file to check.
     * @return <code>false</code> if the option is not a valid double option or not in the specified range, and
     * <code>true</code> otherwise.
     */
    public static boolean checkDoubleOption(String path, double min, Plugin plugin, FileConfiguration config) {
        return checkDoubleOption(path, min, Double.POSITIVE_INFINITY, plugin, config);
    }

    /**
     * Disables the provided plugin if the option at the specified path in its <code>config.yml</code> file has been set
     * to a value that cannot safely be converted to a <code>double</code> between the specified values (inclusive).
     * @param path The path to check.
     * @param min The minimum value.
     * @param max The maximum value.
     * @param plugin The plugin whose configuration is to be checked.
     * @return <code>false</code> if the option is not a valid double option or not in the specified range, and
     * <code>true</code> otherwise.
     * @throws IllegalArgumentException If <code>min</code> is greater than <code>max</code>.
     */
    public static boolean checkDoubleOption(String path, double min, double max, Plugin plugin) {
        return checkDoubleOption(path, min, max, plugin, plugin.getConfig());
    }

    /**
     * Disables the provided plugin if the option at the specified path in the provided configuration file has been set
     * to a value that cannot safely be converted to a <code>double</code> between the specified values (inclusive).
     * @param path The path to check.
     * @param min The minimum value.
     * @param max The maximum value.
     * @param plugin The plugin whose configuration is to be checked.
     * @param config The configuration file to check.
     * @return <code>false</code> if the option is not a valid double option or not in the specified range, and
     * <code>true</code> otherwise.
     * @throws IllegalArgumentException If <code>min</code> is greater than <code>max</code>.
     */
    public static boolean checkDoubleOption(String path, double min, double max, Plugin plugin, FileConfiguration config) {
        return checkDoubleOption0(path, min, max, plugin, config, true);
    }

    /**
     * Logs a warning on behalf of the provided plugin if the option at the specified path in its
     * <code>config.yml</code> file has been set to a value that cannot safely be converted to a <code>double</code>.
     * @param path The path to check.
     * @param plugin The plugin whose configuration is to be checked.
     * @return <code>false</code> if the option is not a valid double option, and <code>true</code> otherwise.
     */
    public static boolean recheckDoubleOption(String path, Plugin plugin) {
        return recheckDoubleOption(path, plugin, plugin.getConfig());
    }

    /**
     * Logs a warning on behalf of the provided plugin if the option at the specified path in the provided configuration
     * file has been set to a value that cannot safely be converted to a <code>double</code>.
     * @param path The path to check.
     * @param plugin The plugin whose configuration is to be checked.
     * @param config The configuration file to check.
     * @return <code>false</code> if the option is not a valid double option, and <code>true</code> otherwise.
     */
    public static boolean recheckDoubleOption(String path, Plugin plugin, FileConfiguration config) {
        return recheckDoubleOption(path, Double.NEGATIVE_INFINITY, plugin, config);
    }

    /**
     * Logs a warning on behalf of the provided plugin if the option at the specified path in its
     * <code>config.yml</code> file has been set to a value that cannot safely be converted to a <code>double</code>
     * greater than or equal to the specified value.
     * @param path The path to check.
     * @param min The minimum value.
     * @param plugin The plugin whose configuration is to be checked.
     * @return <code>false</code> if the option is not a valid double option or not in the specified range, and
     * <code>true</code> otherwise.
     */
    public static boolean recheckDoubleOption(String path, double min, Plugin plugin) {
        return recheckDoubleOption(path, min, plugin, plugin.getConfig());
    }

    /**
     * Logs a warning on behalf of the provided plugin if the option at the specified path in the provided configuration
     * file has been set to a value that cannot safely be converted to a <code>double</code> greater than or equal to
     * the specified value.
     * @param path The path to check.
     * @param min The minimum value.
     * @param plugin The plugin whose configuration is to be checked.
     * @param config The configuration file to check.
     * @return <code>false</code> if the option is not a valid double option or not in the specified range, and
     * <code>true</code> otherwise.
     */
    public static boolean recheckDoubleOption(String path, double min, Plugin plugin, FileConfiguration config) {
        return recheckDoubleOption(path, min, Double.POSITIVE_INFINITY, plugin, config);
    }

    /**
     * Logs a warning on behalf of the provided plugin if the option at the specified path in its
     * <code>config.yml</code> file has been set to a value that cannot safely be converted to a <code>double</code>
     * between the specified values (inclusive).
     * @param path The path to check.
     * @param min The minimum value.
     * @param max The maximum value.
     * @param plugin The plugin whose configuration is to be checked.
     * @return <code>false</code> if the option is not a valid double option or not in the specified range, and
     * <code>true</code> otherwise.
     * @throws IllegalArgumentException If <code>min</code> is greater than <code>max</code>.
     */
    public static boolean recheckDoubleOption(String path, double min, double max, Plugin plugin) {
        return recheckDoubleOption(path, min, max, plugin, plugin.getConfig());
    }

    /**
     * Logs a warning on behalf of the provided plugin if the option at the specified path in the provided configuration
     * file has been set to a value that cannot safely be converted to a <code>double</code> between the specified
     * values (inclusive).
     * @param path The path to check.
     * @param min The minimum value.
     * @param max The maximum value.
     * @param plugin The plugin whose configuration is to be checked.
     * @param config The configuration file to check.
     * @return <code>false</code> if the option is not a valid double option or not in the specified range, and
     * <code>true</code> otherwise.
     * @throws IllegalArgumentException If <code>min</code> is greater than <code>max</code>.
     */
    public static boolean recheckDoubleOption(String path, double min, double max, Plugin plugin, FileConfiguration config) {
        return checkDoubleOption0(path, min, max, plugin, config, false);
    }

    private static boolean checkDoubleOption0(String path, double min, double max, Plugin plugin, FileConfiguration config, boolean disablePlugin) {
        if(min > max) throw new IllegalArgumentException("The minimum value cannot be greater than the maximum value");
        Object value = config.get(path);
        if(value != null) {
            if(!TypeChecks.isDouble(value.toString())) {
                Util.logSevere("Found that \"" + path + "\" has been set to a value that is not a number.", plugin, disablePlugin);
                return false;
            }
            double doubleValue = config.getDouble(path);
            if(doubleValue < min) {
                Util.logSevere("Found that \"" + path + "\" has been set to a value that is less than " + min + ".", plugin, disablePlugin);
                return false;
            }
            if(doubleValue > max) {
                Util.logSevere("Found that \"" + path + "\" has been set to a value that is greater than " + max + ".", plugin, disablePlugin);
                return false;
            }
        }
        return true;
    }

    /**
     * Disables the provided plugin if the options at the specified path in its <code>config.yml</code> file with
     * <code>.min</code> and <code>.max</code> appended to it have been set to values that cannot safely be converted
     * to <code>double</code>s, or if one of them is set while the other isn't, or if the <code>.min</code> option is
     * set to a value greater than the <code>.max</code> option.
     * @param path The path to check.
     * @param plugin The plugin whose configuration is to be checked.
     * @return <code>false</code> if the option is not a valid min-max double option, and <code>true</code> otherwise.
     */
    public static boolean checkMinMaxDoubleOption(String path, Plugin plugin) {
        return checkMinMaxDoubleOption(path, plugin, plugin.getConfig());
    }

    /**
     * Disables the provided plugin if the options at the specified path in the provided configuration file with
     * <code>.min</code> and <code>.max</code> appended to it have been set to values that cannot safely be converted to
     * <code>double</code>s, or if one of them is set while the other isn't, or if the <code>.min</code> option is set
     * to a value greater than the <code>.max</code> option.
     * @param path The path to check.
     * @param plugin The plugin whose configuration is to be checked.
     * @param config The configuration file to check.
     * @return <code>false</code> if the option is not a valid min-max double option, and <code>true</code> otherwise.
     */
    public static boolean checkMinMaxDoubleOption(String path, Plugin plugin, FileConfiguration config) {
        return checkMinMaxDoubleOption(path, Double.NEGATIVE_INFINITY, plugin, config);
    }

    /**
     * Disables the provided plugin if the options at the specified path in its <code>config.yml</code> file with
     * <code>.min</code> and <code>.max</code> appended to it have been set to values that cannot safely be converted to
     * <code>double</code>s, or if one of them is set while the other isn't, or if the <code>.min</code> option is set
     * to a value greater than the <code>.max</code> option, or if either of them has been set to a value less than the
     * specified value.
     * @param path The path to check.
     * @param min The minimum value.
     * @param plugin The plugin whose configuration is to be checked.
     * @return <code>false</code> if the option is not a valid min-max double option or not in the specified range, and
     * <code>true</code> otherwise.
     */
    public static boolean checkMinMaxDoubleOption(String path, double min, Plugin plugin) {
        return checkMinMaxDoubleOption(path, min, plugin, plugin.getConfig());
    }

    /**
     * Disables the provided plugin if the options at the specified path in the provided configuration file with
     * <code>.min</code> and <code>.max</code> appended to it have been set to values that cannot safely be converted to
     * <code>double</code>s, or if one of them is set while the other isn't, or if the <code>.min</code> option is set
     * to a value greater than the <code>.max</code> option, or if either of them has been set to a value less than the
     * specified value.
     * @param path The path to check.
     * @param min The minimum value.
     * @param plugin The plugin whose configuration is to be checked.
     * @param config The configuration file to check.
     * @return <code>false</code> if the option is not a valid min-max double option or not in the specified range, and
     * <code>true</code> otherwise.
     */
    public static boolean checkMinMaxDoubleOption(String path, double min, Plugin plugin, FileConfiguration config) {
        return checkMinMaxDoubleOption(path, min, Double.POSITIVE_INFINITY, plugin, config);
    }

    /**
     * Disables the provided plugin if the options at the specified path in its <code>config.yml</code> file with
     * <code>.min</code> and <code>.max</code> appended to it have been set to values that cannot safely be converted to
     * <code>double</code>s, or if one of them is set while the other isn't, or if the <code>.min</code> option is set
     * to a value greater than the <code>.max</code> option, or if either of them has been set to a value outside the
     * specified values.
     * @param path The path to check.
     * @param min The minimum value.
     * @param max The maximum value.
     * @param plugin The plugin whose configuration is to be checked.
     * @return <code>false</code> if the option is not a valid min-max double option or not in the specified range, and
     * <code>true</code> otherwise.
     */
    public static boolean checkMinMaxDoubleOption(String path, double min, double max, Plugin plugin) {
        return checkMinMaxDoubleOption(path, min, max, plugin, plugin.getConfig());
    }

    /**
     * Disables the provided plugin if the options at the specified path in the provided configuration file with
     * <code>.min</code> and <code>.max</code> appended to it have been set to values that cannot safely be converted
     * to <code>double</code>s, or if one of them is set while the other isn't, or if the <code>.min</code> option is
     * set to a value greater than the <code>.max</code> option, or if either of them has been set to a value outside
     * the specified values.
     * @param path The path to check.
     * @param min The minimum value.
     * @param max The maximum value.
     * @param plugin The plugin whose configuration is to be checked.
     * @param config The configuration file to check.
     * @return <code>false</code> if the option is not a valid min-max double option or not in the specified range, and
     * <code>true</code> otherwise.
     */
    public static boolean checkMinMaxDoubleOption(String path, double min, double max, Plugin plugin, FileConfiguration config) {
        return checkMinMaxDoubleOption0(path, min, max, plugin, config, true);
    }

    /**
     * Logs a warning on behalf of the provided plugin if the options at the specified path in its
     * <code>config.yml</code> file with <code>.min</code> and <code>.max</code> appended to it have been set to values
     * that cannot safely be converted to <code>double</code>s, or if one of them is set while the other isn't, or if
     * the <code>.min</code> option is set to a value greater than the <code>.max</code> option.
     * @param path The path to check.
     * @param plugin The plugin whose configuration is to be checked.
     * @return <code>false</code> if the option is not a valid min-max double option, and <code>true</code> otherwise.
     */
    public static boolean recheckMinMaxDoubleOption(String path, Plugin plugin) {
        return recheckMinMaxDoubleOption(path, plugin, plugin.getConfig());
    }

    /**
     * Logs a warning on behalf of the provided plugin if the options at the specified path in the provided
     * configuration file with <code>.min</code> and <code>.max</code> appended to it have been set to values that
     * cannot safely be converted to <code>double</code>s, or if one of them is set while the other isn't, or if the
     * <code>.min</code> option is set to a value greater than the <code>.max</code> option.
     * @param path The path to check.
     * @param plugin The plugin whose configuration is to be checked.
     * @param config The configuration file to check.
     * @return <code>false</code> if the option is not a valid min-max double option, and <code>true</code> otherwise.
     */
    public static boolean recheckMinMaxDoubleOption(String path, Plugin plugin, FileConfiguration config) {
        return recheckMinMaxDoubleOption(path, Double.NEGATIVE_INFINITY, plugin, config);
    }

    /**
     * Logs a warning on behalf of the provided plugin if the options at the specified path in its
     * <code>config.yml</code> file with <code>.min</code> and <code>.max</code> appended to it have been set to values
     * that cannot safely be converted to <code>double</code>s, or if one of them is set while the other isn't, or if
     * the <code>.min</code> option is set to a value greater than the <code>.max</code> option, or if either of them
     * has been set to a value less than the specified value.
     * @param path The path to check.
     * @param min The minimum value.
     * @param plugin The plugin whose configuration is to be checked.
     * @return <code>false</code> if the option is not a valid min-max double option or not in the specified range, and
     * <code>true</code> otherwise.
     */
    public static boolean recheckMinMaxDoubleOption(String path, double min, Plugin plugin) {
        return recheckMinMaxDoubleOption(path, min, plugin, plugin.getConfig());
    }

    /**
     * Logs a warning on behalf of the provided plugin if the options at the specified path in the provided
     * configuration file with <code>.min</code> and <code>.max</code> appended to it have been set to values that
     * cannot safely be converted to <code>double</code>s, or if one of them is set while the other isn't, or if the
     * <code>.min</code> option is set to a value greater than the <code>.max</code> option, or if either of them has
     * been set to a value less than the specified value.
     * @param path The path to check.
     * @param min The minimum value.
     * @param plugin The plugin whose configuration is to be checked.
     * @param config The configuration file to check.
     * @return <code>false</code> if the option is not a valid min-max double option or not in the specified range, and
     * <code>true</code> otherwise.
     */
    public static boolean recheckMinMaxDoubleOption(String path, double min, Plugin plugin, FileConfiguration config) {
        return recheckMinMaxDoubleOption(path, min, Double.POSITIVE_INFINITY, plugin, config);
    }

    /**
     * Logs a warning on behalf of the provided plugin if the options at the specified path in its
     * <code>config.yml</code> file with <code>.min</code> and <code>.max</code> appended to it have been set to values
     * that cannot safely be converted to <code>double</code>s, or if one of them is set while the other isn't, or if
     * the <code>.min</code> option is set to a value greater than the <code>.max</code> option, or if either of them
     * has been set to a value outside the specified values.
     * @param path The path to check.
     * @param min The minimum value.
     * @param max The maximum value.
     * @param plugin The plugin whose configuration is to be checked.
     * @return <code>false</code> if the option is not a valid min-max double option or not in the specified range, and
     * <code>true</code> otherwise.
     */
    public static boolean recheckMinMaxDoubleOption(String path, double min, double max, Plugin plugin) {
        return recheckMinMaxDoubleOption(path, min, max, plugin, plugin.getConfig());
    }

    /**
     * Logs a warning on behalf of the provided plugin if the options at the specified path in the provided
     * configuration file with <code>.min</code> and <code>.max</code> appended to it have been set to values that
     * cannot safely be converted to <code>double</code>s, or if one of them is set while the other isn't, or if the
     * <code>.min</code> option is set to a value greater than the <code>.max</code> option, or if either of them has
     * been set to a value outside the specified values.
     * @param path The path to check.
     * @param min The minimum value.
     * @param max The maximum value.
     * @param plugin The plugin whose configuration is to be checked.
     * @param config The configuration file to check.
     * @return <code>false</code> if the option is not a valid min-max double option or not in the specified range, and
     * <code>true</code> otherwise.
     */
    public static boolean recheckMinMaxDoubleOption(String path, double min, double max, Plugin plugin, FileConfiguration config) {
        return checkMinMaxDoubleOption0(path, min, max, plugin, config, false);
    }

    private static boolean checkMinMaxDoubleOption0(String path, double min, double max, Plugin plugin, FileConfiguration config, boolean disablePlugin) {
        if(!checkDoubleOption0(path + ".min", min, max, plugin, config, disablePlugin)) return false;
        if(!checkDoubleOption0(path + ".max", min, max, plugin, config, disablePlugin)) return false;
        Object minValue = config.get(path + ".min");
        Object maxValue = config.get(path + ".max");
        if(minValue == null && maxValue == null) return true;
        if(minValue == null) {
            Util.logSevere("Found that \"" + path + "\" has a maximum value but not a minimum value.", plugin, disablePlugin);
            return false;
        }
        if(maxValue == null) {
            Util.logSevere("Found that \"" + path + "\" has a minimum value but not a maximum value.", plugin, disablePlugin);
            return false;
        }
        if(config.getDouble(path + ".min") > config.getDouble(path + ".max")) {
            Util.logSevere("Found that the minimum value of \"" + path + "\" is greater than its maximum value.", plugin, disablePlugin);
            return false;
        }
        return true;
    }

    /**
     * Disables the provided plugin if the option at the specified path in its <code>config.yml</code> file has been set
     * to a value that cannot safely be converted to a {@link String}.
     * @param path The path to check.
     * @param plugin The plugin whose configuration is to be checked.
     * @return <code>false</code> if the option is not a valid string option, and <code>true</code> otherwise.
     */
    public static boolean checkStringOption(String path, Plugin plugin) {
        return checkStringOption(path, plugin, plugin.getConfig());
    }

    /**
     * Disables the provided plugin if the option at the specified path in the provided configuration file has been set
     * to a value that cannot safely be converted to a {@link String}.
     * @param path The path to check.
     * @param plugin The plugin whose configuration is to be checked.
     * @param config The configuration file to check.
     * @return <code>false</code> if the option is not a valid string option, and <code>true</code> otherwise.
     */
    public static boolean checkStringOption(String path, Plugin plugin, FileConfiguration config) {
        return checkStringOption0(path, plugin, config, true);
    }

    /**
     * Logs a warning on behalf of the provided plugin if the option at the specified path in its
     * <code>config.yml</code> file has been set to a value that cannot safely be converted to a {@link String}.
     * @param path The path to check.
     * @param plugin The plugin whose configuration is to be checked.
     * @return <code>false</code> if the option is not a valid string option, and <code>true</code> otherwise.
     */
    public static boolean recheckStringOption(String path, Plugin plugin) {
        return recheckStringOption(path, plugin, plugin.getConfig());
    }

    /**
     * Logs a warning on behalf of the provided plugin if the option at the specified path in the provided configuration
     * file has been set to a value that cannot safely be converted to a {@link String}.
     * @param path The path to check.
     * @param plugin The plugin whose configuration is to be checked.
     * @param config The configuration file to check.
     * @return <code>false</code> if the option is not a valid string option, and <code>true</code> otherwise.
     */
    public static boolean recheckStringOption(String path, Plugin plugin, FileConfiguration config) {
        return checkStringOption0(path, plugin, config, false);
    }

    private static boolean checkStringOption0(String path, Plugin plugin, FileConfiguration config, boolean disablePlugin) {
        Object value = config.get(path);
        if(value != null)
            if(!(value instanceof String)) {
                Util.logSevere("Found that \"" + path + "\" has been set to a value that is not a string.", plugin, disablePlugin);
                return false;
            }
        return true;
    }

    /**
     * Disables the provided plugin if the option at the specified path in its <code>config.yml</code> file has been set
     * to a value that cannot safely be converted to an instance of the specified enum.
     * @param path The path to check.
     * @param clazz The enum class to check against.
     * @param plugin The plugin whose configuration is to be checked.
     * @return <code>false</code> if the option is not a valid enum option, and <code>true</code> otherwise.
     * @param <T> The enum type.
     */
    public static <T extends Enum<T>> boolean checkEnumOption(String path, Class<T> clazz, Plugin plugin) {
        return checkEnumOption(path, clazz, plugin, plugin.getConfig());
    }

    /**
     * Disables the provided plugin if the option at the specified path in the provided configuration file has been set
     * to a value that cannot safely be converted to an instance of the specified enum.
     * @param path The path to check.
     * @param clazz The enum class to check against.
     * @param plugin The plugin whose configuration is to be checked.
     * @param config The configuration file to check.
     * @return <code>false</code> if the option is not a valid enum option, and <code>true</code> otherwise.
     * @param <T> The enum type.
     */
    public static <T extends Enum<T>> boolean checkEnumOption(String path, Class<T> clazz, Plugin plugin, FileConfiguration config) {
        return checkEnumOption0(path, clazz, plugin, config, true);
    }

    /**
     * Logs a warning on behalf of the provided plugin if the option at the specified path in its
     * <code>config.yml</code> file has been set to a value that cannot safely be converted to an instance of the
     * specified enum.
     * @param path The path to check.
     * @param clazz The enum class to check against.
     * @param plugin The plugin whose configuration is to be checked.
     * @return <code>false</code> if the option is not a valid enum option, and <code>true</code> otherwise.
     * @param <T> The enum type.
     */
    public static <T extends Enum<T>> boolean recheckEnumOption(String path, Class<T> clazz, Plugin plugin) {
        return recheckEnumOption(path, clazz, plugin, plugin.getConfig());
    }

    /**
     * Logs a warning on behalf of the provided plugin if the option at the specified path in the provided configuration
     * file has been set to a value that cannot safely be converted to an instance of the specified enum.
     * @param path The path to check.
     * @param clazz The enum class to check against.
     * @param plugin The plugin whose configuration is to be checked.
     * @param config The configuration file to check.
     * @return <code>false</code> if the option is not a valid enum option, and <code>true</code> otherwise.
     * @param <T> The enum type.
     */
    public static <T extends Enum<T>> boolean recheckEnumOption(String path, Class<T> clazz, Plugin plugin, FileConfiguration config) {
        return checkEnumOption0(path, clazz, plugin, config, false);
    }

    private static <T extends Enum<T>> boolean checkEnumOption0(String path, Class<T> clazz, Plugin plugin, FileConfiguration config, boolean disablePlugin) {
        Object value = config.get(path);
        if(value != null)
            if(!TypeChecks.isEnum(value.toString(), clazz)) {
                Util.logSevere("Found that \"" + path + "\" has been set to a value that is not an instance of " + clazz.getName() + ".", plugin, disablePlugin);
                return false;
            }
        return true;
    }

    /**
     * Disables the provided plugin if the option at the specified path in its <code>config.yml</code> file has been set
     * to a value that is not a list of values.
     * @param path The path to check.
     * @param plugin The plugin whose configuration is to be checked.
     * @return <code>false</code> if the option is not a valid list option, and <code>true</code> otherwise.
     */
    public static boolean checkListOption(String path, Plugin plugin) {
        return checkListOption(path, plugin, plugin.getConfig());
    }

    /**
     * Disables the provided plugin if the option at the specified path in the provided configuration file has been set
     * to a value that is not a list of values.
     * @param path The path to check.
     * @param plugin The plugin whose configuration is to be checked.
     * @param config The configuration file to check.
     * @return <code>false</code> if the option is not a valid list option, and <code>true</code> otherwise.
     */
    public static boolean checkListOption(String path, Plugin plugin, FileConfiguration config) {
        return checkListOption0(path, plugin, config, true);
    }

    /**
     * Logs a warning on behalf of the provided plugin if the option at the specified path in its
     * <code>config.yml</code> file has been set to a value that is not a list of values.
     * @param path The path to check.
     * @param plugin The plugin whose configuration is to be checked.
     * @return <code>false</code> if the option is not a valid list option, and <code>true</code> otherwise.
     */
    public static boolean recheckListOption(String path, Plugin plugin) {
        return recheckListOption(path, plugin, plugin.getConfig());
    }

    /**
     * Logs a warning on behalf of the provided plugin if the option at the specified path in the provided configuration
     * file has been set to a value that is not a list of values.
     * @param path The path to check.
     * @param plugin The plugin whose configuration is to be checked.
     * @param config The configuration file to check.
     * @return <code>false</code> if the option is not a valid list option, and <code>true</code> otherwise.
     */
    public static boolean recheckListOption(String path, Plugin plugin, FileConfiguration config) {
        return checkListOption0(path, plugin, config, false);
    }

    private static boolean checkListOption0(String path, Plugin plugin, FileConfiguration config, boolean disablePlugin) {
        Object value = config.get(path);
        if(value != null)
            if(!(value instanceof List<?>)) {
                Util.logSevere("Found that \"" + path + "\" has been set to a value that is not a list.", plugin, disablePlugin);
                return false;
            }
        return true;
    }

    /**
     * Disables the provided plugin if the option at the specified path in its <code>config.yml</code> file has been set
     * to a value that is not a list of <code>boolean</code>s.
     * @param path The path to check.
     * @param plugin The plugin whose configuration is to be checked.
     * @return <code>false</code> if the option is not a valid boolean list, and <code>true</code> otherwise.
     */
    public static boolean checkBooleanList(String path, Plugin plugin) {
        return checkBooleanList(path, plugin, plugin.getConfig());
    }

    /**
     * Disables the provided plugin if the option at the specified path in the provided configuration file has been set
     * to a value that is not a list of <code>boolean</code>s.
     * @param path The path to check.
     * @param plugin The plugin whose configuration is to be checked.
     * @param config The configuration file to check.
     * @return <code>false</code> if the option is not a valid boolean list, and <code>true</code> otherwise.
     */
    public static boolean checkBooleanList(String path, Plugin plugin, FileConfiguration config) {
        return checkBooleanList0(path, plugin, config, true);
    }

    /**
     * Logs a warning on behalf of the provided plugin if the option at the specified path in its
     * <code>config.yml</code> file has been set to a value that is not a list of <code>boolean</code>s.
     * @param path The path to check.
     * @param plugin The plugin whose configuration is to be checked.
     * @return <code>false</code> if the option is not a valid boolean list, and <code>true</code> otherwise.
     */
    public static boolean recheckBooleanList(String path, Plugin plugin) {
        return recheckBooleanList(path, plugin, plugin.getConfig());
    }

    /**
     * Logs a warning on behalf of the provided plugin if the option at the specified path in the provided configuration
     * file has been set to a value that is not a list of <code>boolean</code>s.
     * @param path The path to check.
     * @param plugin The plugin whose configuration is to be checked.
     * @param config The configuration file to check.
     * @return <code>false</code> if the option is not a valid boolean list, and <code>true</code> otherwise.
     */
    public static boolean recheckBooleanList(String path, Plugin plugin, FileConfiguration config) {
        return checkBooleanList0(path, plugin, config, false);
    }

    private static boolean checkBooleanList0(String path, Plugin plugin, FileConfiguration config, boolean disablePlugin) {
        if(!checkListOption0(path, plugin, config, disablePlugin)) return false;
        List<?> list = config.getList(path);
        if(list != null)
            for(Object value : list) {
                if(!TypeChecks.isBoolean(value.toString())) {
                    Util.logSevere("Found that \"" + path + "\" contains a value that is not true or false.", plugin, disablePlugin);
                    return false;
                }
            }
        return true;
    }

    /**
     * Disables the provided plugin if the option at the specified path in its <code>config.yml</code> file has been set
     * to a value that is not a list of <code>int</code>s.
     * @param path The path to check.
     * @param plugin The plugin whose configuration is to be checked.
     * @return <code>false</code> if the option is not a valid integer list, and <code>true</code> otherwise.
     */
    public static boolean checkIntList(String path, Plugin plugin) {
        return checkIntList(path, plugin, plugin.getConfig());
    }

    /**
     * Disables the provided plugin if the option at the specified path in the provided configuration file has been set
     * to a value that is not a list of <code>int</code>s.
     * @param path The path to check.
     * @param plugin The plugin whose configuration is to be checked.
     * @param config The configuration file to check.
     * @return <code>false</code> if the option is not a valid integer list, and <code>true</code> otherwise.
     */
    public static boolean checkIntList(String path, Plugin plugin, FileConfiguration config) {
        return checkIntList(path, Integer.MIN_VALUE, plugin, config);
    }

    /**
     * Disables the provided plugin if the option at the specified path in its <code>config.yml</code> file has been set
     * to a value that is not a list of <code>int</code>s greater than or equal to the specified value.
     * @param path The path to check.
     * @param min The minimum value.
     * @param plugin The plugin whose configuration is to be checked.
     * @return <code>false</code> if the option is not a valid list of integers in the specified range, and
     * <code>true</code> otherwise.
     */
    public static boolean checkIntList(String path, int min, Plugin plugin) {
        return checkIntList(path, min, plugin, plugin.getConfig());
    }

    /**
     * Disables the provided plugin if the option at the specified path in the provided configuration file has been set
     * to a value that is not a list of <code>int</code>s greater than or equal to the specified value.
     * @param path The path to check.
     * @param min The minimum value.
     * @param plugin The plugin whose configuration is to be checked.
     * @param config The configuration file to check.
     * @return <code>false</code> if the option is not a valid list of integers in the specified range, and
     * <code>true</code> otherwise.
     */
    public static boolean checkIntList(String path, int min, Plugin plugin, FileConfiguration config) {
        return checkIntList(path, min, Integer.MAX_VALUE, plugin, config);
    }

    /**
     * Disables the provided plugin if the option at the specified path in its <code>config.yml</code> file has been set
     * to a value that is not a list of <code>int</code>s between the specified values (inclusive).
     * @param path The path to check.
     * @param min The minimum value.
     * @param max The maximum value.
     * @param plugin The plugin whose configuration is to be checked.
     * @return <code>false</code> if the option is not a valid list of integers in the specified range, and
     * <code>true</code> otherwise.
     */
    public static boolean checkIntList(String path, int min, int max, Plugin plugin) {
        return checkIntList(path, min, max, plugin, plugin.getConfig());
    }

    /**
     * Disables the provided plugin if the option at the specified path in the provided configuration file has been set
     * to a value that is not a list of <code>int</code>s between the specified values (inclusive).
     * @param path The path to check.
     * @param min The minimum value.
     * @param max The maximum value.
     * @param plugin The plugin whose configuration is to be checked.
     * @param config The configuration file to check.
     * @return <code>false</code> if the option is not a valid list of integers in the specified range, and
     * <code>true</code> otherwise.
     */
    public static boolean checkIntList(String path, int min, int max, Plugin plugin, FileConfiguration config) {
        return checkIntList0(path, min, max, plugin, config, true);
    }

    /**
     * Logs a warning on behalf of the provided plugin if the option at the specified path in its
     * <code>config.yml</code> file has been set to a value that is not a list of <code>int</code>s.
     * @param path The path to check.
     * @param plugin The plugin whose configuration is to be checked.
     * @return <code>false</code> if the option is not a valid integer list, and <code>true</code> otherwise.
     */
    public static boolean recheckIntList(String path, Plugin plugin) {
        return recheckIntList(path, plugin, plugin.getConfig());
    }

    /**
     * Logs a warning on behalf of the provided plugin if the option at the specified path in the provided configuration
     * file has been set to a value that is not a list of <code>int</code>s.
     * @param path The path to check.
     * @param plugin The plugin whose configuration is to be checked.
     * @param config The configuration file to check.
     * @return <code>false</code> if the option is not a valid integer list, and <code>true</code> otherwise.
     */
    public static boolean recheckIntList(String path, Plugin plugin, FileConfiguration config) {
        return recheckIntList(path, Integer.MIN_VALUE, plugin, config);
    }

    /**
     * Logs a warning on behalf of the provided plugin if the option at the specified path in its
     * <code>config.yml</code> file has been set to a value that is not a list of <code>int</code>s greater than or
     * equal to the specified value.
     * @param path The path to check.
     * @param min The minimum value.
     * @param plugin The plugin whose configuration is to be checked.
     * @return <code>false</code> if the option is not a valid list of integers in the specified range, and
     * <code>true</code> otherwise.
     */
    public static boolean recheckIntList(String path, int min, Plugin plugin) {
        return recheckIntList(path, min, plugin, plugin.getConfig());
    }

    /**
     * Logs a warning on behalf of the provided plugin if the option at the specified path in the provided configuration
     * file has been set to a value that is not a list of <code>int</code>s greater than or equal to the specified
     * value.
     * @param path The path to check.
     * @param min The minimum value.
     * @param plugin The plugin whose configuration is to be checked.
     * @param config The configuration file to check.
     * @return <code>false</code> if the option is not a valid list of integers in the specified range, and
     * <code>true</code> otherwise.
     */
    public static boolean recheckIntList(String path, int min, Plugin plugin, FileConfiguration config) {
        return recheckIntList(path, min, Integer.MAX_VALUE, plugin, config);
    }

    /**
     * Logs a warning on behalf of the provided plugin if the option at the specified path in its
     * <code>config.yml</code> file has been set to a value that is not a list of <code>int</code>s between the
     * specified values (inclusive).
     * @param path The path to check.
     * @param min The minimum value.
     * @param max The maximum value.
     * @param plugin The plugin whose configuration is to be checked.
     * @return <code>false</code> if the option is not a valid list of integers in the specified range, and
     * <code>true</code> otherwise.
     */
    public static boolean recheckIntList(String path, int min, int max, Plugin plugin) {
        return recheckIntList(path, min, max, plugin, plugin.getConfig());
    }

    /**
     * Logs a warning on behalf of the provided plugin if the option at the specified path in the provided configuration
     * file has been set to a value that is not a list of <code>int</code>s between the specified values (inclusive).
     * @param path The path to check.
     * @param min The minimum value.
     * @param max The maximum value.
     * @param plugin The plugin whose configuration is to be checked.
     * @param config The configuration file to check.
     * @return <code>false</code> if the option is not a valid list of integers in the specified range, and
     * <code>true</code> otherwise.
     */
    public static boolean recheckIntList(String path, int min, int max, Plugin plugin, FileConfiguration config) {
        return checkIntList0(path, min, max, plugin, config, false);
    }

    private static boolean checkIntList0(String path, int min, int max, Plugin plugin, FileConfiguration config, boolean disablePlugin) {
        if(min > max) throw new IllegalArgumentException("The minimum value cannot be greater than the maximum value");
        if(!checkListOption0(path, plugin, config, disablePlugin)) return false;
        List<?> list = config.getList(path);
        if(list != null)
            for(Object value : list) {
                if(!TypeChecks.isInt(value.toString())) {
                    Util.logSevere("Found that \"" + path + "\" contains a value that is not an integer.", plugin, disablePlugin);
                    return false;
                }
                int intValue = Integer.parseInt(value.toString());
                if(intValue < min) {
                    Util.logSevere("Found that \"" + path + "\" contains a value that is less than " + min + ".", plugin, disablePlugin);
                    return false;
                }
                if(intValue > max) {
                    Util.logSevere("Found that \"" + path + "\" contains a value that is greater than " + max + ".", plugin, disablePlugin);
                    return false;
                }
            }
        return true;
    }

    /**
     * Disables the provided plugin if the option at the specified path in its <code>config.yml</code> file has been set
     * to a value that is not a list of <code>double</code>s.
     * @param path The path to check.
     * @param plugin The plugin whose configuration is to be checked.
     * @return <code>false</code> if the option is not a valid double list, and <code>true</code> otherwise.
     */
    public static boolean checkDoubleList(String path, Plugin plugin) {
        return checkDoubleList(path, plugin, plugin.getConfig());
    }

    /**
     * Disables the provided plugin if the option at the specified path in the provided configuration file has been set
     * to a value that is not a list of <code>double</code>s.
     * @param path The path to check.
     * @param plugin The plugin whose configuration is to be checked.
     * @param config The configuration file to check.
     * @return <code>false</code> if the option is not a valid double list, and <code>true</code> otherwise.
     */
    public static boolean checkDoubleList(String path, Plugin plugin, FileConfiguration config) {
        return checkDoubleList(path, Double.NEGATIVE_INFINITY, plugin, config);
    }

    /**
     * Disables the provided plugin if the option at the specified path in its <code>config.yml</code> file has been set
     * to a value that is not a list of <code>double</code>s greater than or equal to the specified value.
     * @param path The path to check.
     * @param min The minimum value.
     * @param plugin The plugin whose configuration is to be checked.
     * @return <code>false</code> if the option is not a valid list of doubles in the specified range, and
     * <code>true</code> otherwise.
     */
    public static boolean checkDoubleList(String path, double min, Plugin plugin) {
        return checkDoubleList(path, min, plugin, plugin.getConfig());
    }

    /**
     * Disables the provided plugin if the option at the specified path in the provided configuration file has been set
     * to a value that is not a list of <code>double</code>s greater than or equal to the specified value.
     * @param path The path to check.
     * @param min The minimum value.
     * @param plugin The plugin whose configuration is to be checked.
     * @param config The configuration file to check.
     * @return <code>false</code> if the option is not a valid list of doubles in the specified range, and
     * <code>true</code> otherwise.
     */
    public static boolean checkDoubleList(String path, double min, Plugin plugin, FileConfiguration config) {
        return checkDoubleList(path, min, Double.POSITIVE_INFINITY, plugin, config);
    }

    /**
     * Disables the provided plugin if the option at the specified path in its <code>config.yml</code> file has been set
     * to a value that is not a list of <code>double</code>s between the specified values (inclusive).
     * @param path The path to check.
     * @param min The minimum value.
     * @param max The maximum value.
     * @param plugin The plugin whose configuration is to be checked.
     * @return <code>false</code> if the option is not a valid list of doubles in the specified range, and
     * <code>true</code> otherwise.
     */
    public static boolean checkDoubleList(String path, double min, double max, Plugin plugin) {
        return checkDoubleList(path, min, max, plugin, plugin.getConfig());
    }

    /**
     * Disables the provided plugin if the option at the specified path in the provided configuration file has been set
     * to a value that is not a list of <code>double</code>s between the specified values (inclusive).
     * @param path The path to check.
     * @param min The minimum value.
     * @param max The maximum value.
     * @param plugin The plugin whose configuration is to be checked.
     * @param config The configuration file to check.
     * @return <code>false</code> if the option is not a valid list of doubles in the specified range, and
     * <code>true</code> otherwise.
     */
    public static boolean checkDoubleList(String path, double min, double max, Plugin plugin, FileConfiguration config) {
        return checkDoubleList0(path, min, max, plugin, config, true);
    }

    /**
     * Logs a warning on behalf of the provided plugin if the option at the specified path in its
     * <code>config.yml</code> file has been set to a value that is not a list of <code>double</code>s.
     * @param path The path to check.
     * @param plugin The plugin whose configuration is to be checked.
     * @return <code>false</code> if the option is not a valid double list, and <code>true</code> otherwise.
     */
    public static boolean recheckDoubleList(String path, Plugin plugin) {
        return recheckDoubleList(path, plugin, plugin.getConfig());
    }

    /**
     * Logs a warning on behalf of the provided plugin if the option at the specified path in the provided configuration
     * file has been set to a value that is not a list of <code>double</code>s.
     * @param path The path to check.
     * @param plugin The plugin whose configuration is to be checked.
     * @param config The configuration file to check.
     * @return <code>false</code> if the option is not a valid double list, and <code>true</code> otherwise.
     */
    public static boolean recheckDoubleList(String path, Plugin plugin, FileConfiguration config) {
        return recheckDoubleList(path, Double.NEGATIVE_INFINITY, plugin, config);
    }

    /**
     * Logs a warning on behalf of the provided plugin if the option at the specified path in its
     * <code>config.yml</code> file has been set to a value that is not a list of <code>double</code>s greater than or
     * equal to the specified value.
     * @param path The path to check.
     * @param min The minimum value.
     * @param plugin The plugin whose configuration is to be checked.
     * @return <code>false</code> if the option is not a valid list of doubles in the specified range, and
     * <code>true</code> otherwise.
     */
    public static boolean recheckDoubleList(String path, double min, Plugin plugin) {
        return recheckDoubleList(path, min, plugin, plugin.getConfig());
    }

    /**
     * Logs a warning on behalf of the provided plugin if the option at the specified path in the provided configuration
     * file has been set to a value that is not a list of <code>double</code>s greater than or equal to the specified
     * value.
     * @param path The path to check.
     * @param min The minimum value.
     * @param plugin The plugin whose configuration is to be checked.
     * @param config The configuration file to check.
     * @return <code>false</code> if the option is not a valid list of doubles in the specified range, and
     * <code>true</code> otherwise.
     */
    public static boolean recheckDoubleList(String path, double min, Plugin plugin, FileConfiguration config) {
        return recheckDoubleList(path, min, Double.POSITIVE_INFINITY, plugin, config);
    }

    /**
     * Logs a warning on behalf of the provided plugin if the option at the specified path in its
     * <code>config.yml</code> file has been set to a value that is not a list of <code>double</code>s between the
     * specified values (inclusive).
     * @param path The path to check.
     * @param min The minimum value.
     * @param max The maximum value.
     * @param plugin The plugin whose configuration is to be checked.
     * @return <code>false</code> if the option is not a valid list of doubles in the specified range, and
     * <code>true</code> otherwise.
     */
    public static boolean recheckDoubleList(String path, double min, double max, Plugin plugin) {
        return recheckDoubleList(path, min, max, plugin, plugin.getConfig());
    }

    /**
     * Logs a warning on behalf of the provided plugin if the option at the specified path in the provided configuration
     * file has been set to a value that is not a list of <code>double</code>s between the specified values (inclusive).
     * @param path The path to check.
     * @param min The minimum value.
     * @param max The maximum value.
     * @param plugin The plugin whose configuration is to be checked.
     * @param config The configuration file to check.
     * @return <code>false</code> if the option is not a valid list of doubles in the specified range, and
     * <code>true</code> otherwise.
     */
    public static boolean recheckDoubleList(String path, double min, double max, Plugin plugin, FileConfiguration config) {
        return checkDoubleList0(path, min, max, plugin, config, false);
    }

    private static boolean checkDoubleList0(String path, double min, double max, Plugin plugin, FileConfiguration config, boolean disablePlugin) {
        if(min > max) throw new IllegalArgumentException("The minimum value cannot be greater than the maximum value");
        if(!checkListOption0(path, plugin, config, disablePlugin)) return false;
        List<?> list = config.getList(path);
        if(list != null)
            for(Object value : list) {
                if(!TypeChecks.isDouble(value.toString())) {
                    Util.logSevere("Found that \"" + path + "\" contains a value that is not a number.", plugin, disablePlugin);
                    return false;
                }
                double doubleValue = Double.parseDouble(value.toString());
                if(doubleValue < min) {
                    Util.logSevere("Found that \"" + path + "\" contains a value that is less than " + min + ".", plugin, disablePlugin);
                    return false;
                }
                if(doubleValue > max) {
                    Util.logSevere("Found that \"" + path + "\" contains a value that is greater than " + max + ".", plugin, disablePlugin);
                    return false;
                }
            }
        return true;
    }

    /**
     * Disables the provided plugin if the option at the specified path in its <code>config.yml</code> file has been set
     * to a value that is not a list of {@link String}s.
     * @param path The path to check.
     * @param plugin The plugin whose configuration is to be checked.
     * @return <code>false</code> if the option is not a valid string list, and <code>true</code> otherwise.
     */
    public static boolean checkStringList(String path, Plugin plugin) {
        return checkStringList(path, plugin, plugin.getConfig());
    }

    /**
     * Disables the provided plugin if the option at the specified path in the provided configuration file has been set
     * to a value that is not a list of {@link String}s.
     * @param path The path to check.
     * @param plugin The plugin whose configuration is to be checked.
     * @param config The configuration file to check.
     * @return <code>false</code> if the option is not a valid string list, and <code>true</code> otherwise.
     */
    public static boolean checkStringList(String path, Plugin plugin, FileConfiguration config) {
        return checkStringList0(path, plugin, config, true);
    }

    /**
     * Logs a warning on behalf of the provided plugin if the option at the specified path in its
     * <code>config.yml</code> file has been set to a value that is not a list of {@link String}s.
     * @param path The path to check.
     * @param plugin The plugin whose configuration is to be checked.
     * @return <code>false</code> if the option is not a valid string list, and <code>true</code> otherwise.
     */
    public static boolean recheckStringList(String path, Plugin plugin) {
        return recheckStringList(path, plugin, plugin.getConfig());
    }

    /**
     * Logs a warning on behalf of the provided plugin if the option at the specified path in the provided configuration
     * file has been set to a value that is not a list of {@link String}s.
     * @param path The path to check.
     * @param plugin The plugin whose configuration is to be checked.
     * @param config The configuration file to check.
     * @return <code>false</code> if the option is not a valid string list, and <code>true</code> otherwise.
     */
    public static boolean recheckStringList(String path, Plugin plugin, FileConfiguration config) {
        return checkStringList0(path, plugin, config, false);
    }

    private static boolean checkStringList0(String path, Plugin plugin, FileConfiguration config, boolean disablePlugin) {
        if(!checkListOption0(path, plugin, config, disablePlugin)) return false;
        List<?> list = config.getList(path);
        if(list != null)
            for(Object value : list) {
                if(!(value instanceof String)) {
                    Util.logSevere("Found that \"" + path + "\" contains a value that is not a string.", plugin, disablePlugin);
                    return false;
                }
            }
        return true;
    }

    /**
     * Disables the provided plugin if the option at the specified path in its <code>config.yml</code> file has been set
     * to a value that is not a list of instances of the specified enum.
     * @param path The path to check.
     * @param clazz The enum class to check against.
     * @param plugin The plugin whose configuration is to be checked.
     * @return <code>false</code> if the option is not a valid enum list, and <code>true</code> otherwise.
     * @param <T> The enum type.
     */
    public static <T extends Enum<T>> boolean checkEnumList(String path, Class<T> clazz, Plugin plugin) {
        return checkEnumList(path, clazz, plugin, plugin.getConfig());
    }

    /**
     * Disables the provided plugin if the option at the specified path in the provided configuration file has been set
     * to a value that is not a list of instances of the specified enum.
     * @param path The path to check.
     * @param clazz The enum class to check against.
     * @param plugin The plugin whose configuration is to be checked.
     * @param config The configuration file to check.
     * @return <code>false</code> if the option is not a valid enum list, and <code>true</code> otherwise.
     * @param <T> The enum type.
     */
    public static <T extends Enum<T>> boolean checkEnumList(String path, Class<T> clazz, Plugin plugin, FileConfiguration config) {
        return checkEnumList0(path, clazz, plugin, config, true);
    }

    /**
     * Logs a warning on behalf of the provided plugin if the option at the specified path in its
     * <code>config.yml</code> file has been set to a value that is not a list of instances of the specified enum.
     * @param path The path to check.
     * @param clazz The enum class to check against.
     * @param plugin The plugin whose configuration is to be checked.
     * @return <code>false</code> if the option is not a valid enum list, and <code>true</code> otherwise.
     * @param <T> The enum type.
     */
    public static <T extends Enum<T>> boolean recheckEnumList(String path, Class<T> clazz, Plugin plugin) {
        return recheckEnumList(path, clazz, plugin, plugin.getConfig());
    }

    /**
     * Logs a warning on behalf of the provided plugin if the option at the specified path in the provided configuration
     * file has been set to a value that is not a list of instances of the specified enum.
     * @param path The path to check.
     * @param clazz The enum class to check against.
     * @param plugin The plugin whose configuration is to be checked.
     * @param config The configuration file to check.
     * @return <code>false</code> if the option is not a valid enum list, and <code>true</code> otherwise.
     * @param <T> The enum type.
     */
    public static <T extends Enum<T>> boolean recheckEnumList(String path, Class<T> clazz, Plugin plugin, FileConfiguration config) {
        return checkEnumList0(path, clazz, plugin, config, false);
    }

    private static <T extends Enum<T>> boolean checkEnumList0(String path, Class<T> clazz, Plugin plugin, FileConfiguration config, boolean disablePlugin) {
        if(!checkListOption0(path, plugin, config, disablePlugin)) return false;
        List<?> list = config.getList(path);
        if(list != null)
            for(Object value : list) {
                if(!TypeChecks.isEnum(value.toString(), clazz)) {
                    Util.logSevere("Found that \"" + path + "\" has been set to a value that is not an instance of " + clazz.getName() + ".", plugin, disablePlugin);
                    return false;
                }
            }
        return true;
    }
}