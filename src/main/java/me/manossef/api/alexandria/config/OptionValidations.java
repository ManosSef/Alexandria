package me.manossef.api.alexandria.config;

import me.manossef.api.alexandria.TypeChecks;
import me.manossef.api.alexandria.Util;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

import java.util.List;

public final class OptionValidations {

    private OptionValidations() {
    }

    public static boolean checkRequired(String path, Plugin plugin) {
        return checkRequired(path, plugin, plugin.getConfig());
    }

    public static boolean checkRequired(String path, Plugin plugin, FileConfiguration config) {
        return checkRequired0(path, plugin, config, true);
    }

    public static boolean recheckRequired(String path, Plugin plugin) {
        return recheckRequired(path, plugin, plugin.getConfig());
    }

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

    public static boolean checkBooleanOption(String path, Plugin plugin) {
        return checkBooleanOption(path, plugin, plugin.getConfig());
    }

    public static boolean checkBooleanOption(String path, Plugin plugin, FileConfiguration config) {
        return checkBooleanOption0(path, plugin, config, true);
    }

    public static boolean recheckBooleanOption(String path, Plugin plugin) {
        return recheckBooleanOption(path, plugin, plugin.getConfig());
    }

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

    public static boolean checkDebugOption(String path, Plugin plugin) {
        return checkDebugOption(path, plugin, plugin.getConfig());
    }

    public static boolean checkDebugOption(String path, Plugin plugin, FileConfiguration config) {
        return true;
    }

    public static boolean recheckDebugOption(String path, Plugin plugin) {
        return recheckDebugOption(path, plugin, plugin.getConfig());
    }

    public static boolean recheckDebugOption(String path, Plugin plugin, FileConfiguration config) {
        return true;
    }

    public static boolean checkIntOption(String path, Plugin plugin) {
        return checkIntOption(path, plugin, plugin.getConfig());
    }

    public static boolean checkIntOption(String path, Plugin plugin, FileConfiguration config) {
        return checkIntOption(path, Integer.MIN_VALUE, plugin, config);
    }

    public static boolean checkIntOption(String path, int min, Plugin plugin) {
        return checkIntOption(path, min, plugin, plugin.getConfig());
    }

    public static boolean checkIntOption(String path, int min, Plugin plugin, FileConfiguration config) {
        return checkIntOption(path, min, Integer.MAX_VALUE, plugin, config);
    }

    public static boolean checkIntOption(String path, int min, int max, Plugin plugin) {
        return checkIntOption(path, min, max, plugin, plugin.getConfig());
    }

    public static boolean checkIntOption(String path, int min, int max, Plugin plugin, FileConfiguration config) {
        return checkIntOption0(path, min, max, plugin, config, true);
    }

    public static boolean recheckIntOption(String path, Plugin plugin) {
        return recheckIntOption(path, plugin, plugin.getConfig());
    }

    public static boolean recheckIntOption(String path, Plugin plugin, FileConfiguration config) {
        return recheckIntOption(path, Integer.MIN_VALUE, plugin, config);
    }

    public static boolean recheckIntOption(String path, int min, Plugin plugin) {
        return recheckIntOption(path, min, plugin, plugin.getConfig());
    }

    public static boolean recheckIntOption(String path, int min, Plugin plugin, FileConfiguration config) {
        return recheckIntOption(path, min, Integer.MAX_VALUE, plugin, config);
    }

    public static boolean recheckIntOption(String path, int min, int max, Plugin plugin) {
        return recheckIntOption(path, min, max, plugin, plugin.getConfig());
    }

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

    public static boolean checkMinMaxIntOption(String path, Plugin plugin) {
        return checkMinMaxIntOption(path, plugin, plugin.getConfig());
    }

    public static boolean checkMinMaxIntOption(String path, Plugin plugin, FileConfiguration config) {
        return checkMinMaxIntOption(path, Integer.MIN_VALUE, plugin, config);
    }

    public static boolean checkMinMaxIntOption(String path, int min, Plugin plugin) {
        return checkMinMaxIntOption(path, min, plugin, plugin.getConfig());
    }

    public static boolean checkMinMaxIntOption(String path, int min, Plugin plugin, FileConfiguration config) {
        return checkMinMaxIntOption(path, min, Integer.MAX_VALUE, plugin, config);
    }

    public static boolean checkMinMaxIntOption(String path, int min, int max, Plugin plugin) {
        return checkMinMaxIntOption(path, min, max, plugin, plugin.getConfig());
    }

    public static boolean checkMinMaxIntOption(String path, int min, int max, Plugin plugin, FileConfiguration config) {
        return checkMinMaxIntOption0(path, min, max, plugin, config, true);
    }

    public static boolean recheckMinMaxIntOption(String path, Plugin plugin) {
        return recheckMinMaxIntOption(path, plugin, plugin.getConfig());
    }

    public static boolean recheckMinMaxIntOption(String path, Plugin plugin, FileConfiguration config) {
        return recheckMinMaxIntOption(path, Integer.MIN_VALUE, plugin, config);
    }

    public static boolean recheckMinMaxIntOption(String path, int min, Plugin plugin) {
        return recheckMinMaxIntOption(path, min, plugin, plugin.getConfig());
    }

    public static boolean recheckMinMaxIntOption(String path, int min, Plugin plugin, FileConfiguration config) {
        return recheckMinMaxIntOption(path, min, Integer.MAX_VALUE, plugin, config);
    }

    public static boolean recheckMinMaxIntOption(String path, int min, int max, Plugin plugin) {
        return recheckMinMaxIntOption(path, min, max, plugin, plugin.getConfig());
    }

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

    public static boolean checkDoubleOption(String path, Plugin plugin) {
        return checkDoubleOption(path, plugin, plugin.getConfig());
    }

    public static boolean checkDoubleOption(String path, Plugin plugin, FileConfiguration config) {
        return checkDoubleOption(path, Double.NEGATIVE_INFINITY, plugin, config);
    }

    public static boolean checkDoubleOption(String path, double min, Plugin plugin) {
        return checkDoubleOption(path, min, plugin, plugin.getConfig());
    }

    public static boolean checkDoubleOption(String path, double min, Plugin plugin, FileConfiguration config) {
        return checkDoubleOption(path, min, Double.POSITIVE_INFINITY, plugin, config);
    }

    public static boolean checkDoubleOption(String path, double min, double max, Plugin plugin) {
        return checkDoubleOption(path, min, max, plugin, plugin.getConfig());
    }

    public static boolean checkDoubleOption(String path, double min, double max, Plugin plugin, FileConfiguration config) {
        return checkDoubleOption0(path, min, max, plugin, config, true);
    }

    public static boolean recheckDoubleOption(String path, Plugin plugin) {
        return recheckDoubleOption(path, plugin, plugin.getConfig());
    }

    public static boolean recheckDoubleOption(String path, Plugin plugin, FileConfiguration config) {
        return recheckDoubleOption(path, Double.NEGATIVE_INFINITY, plugin, config);
    }

    public static boolean recheckDoubleOption(String path, double min, Plugin plugin) {
        return recheckDoubleOption(path, min, plugin, plugin.getConfig());
    }

    public static boolean recheckDoubleOption(String path, double min, Plugin plugin, FileConfiguration config) {
        return recheckDoubleOption(path, min, Double.POSITIVE_INFINITY, plugin, config);
    }

    public static boolean recheckDoubleOption(String path, double min, double max, Plugin plugin) {
        return recheckDoubleOption(path, min, max, plugin, plugin.getConfig());
    }

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

    public static boolean checkMinMaxDoubleOption(String path, Plugin plugin) {
        return checkMinMaxDoubleOption(path, plugin, plugin.getConfig());
    }

    public static boolean checkMinMaxDoubleOption(String path, Plugin plugin, FileConfiguration config) {
        return checkMinMaxDoubleOption(path, Double.NEGATIVE_INFINITY, plugin, config);
    }

    public static boolean checkMinMaxDoubleOption(String path, double min, Plugin plugin) {
        return checkMinMaxDoubleOption(path, min, plugin, plugin.getConfig());
    }

    public static boolean checkMinMaxDoubleOption(String path, double min, Plugin plugin, FileConfiguration config) {
        return checkMinMaxDoubleOption(path, min, Double.POSITIVE_INFINITY, plugin, config);
    }

    public static boolean checkMinMaxDoubleOption(String path, double min, double max, Plugin plugin) {
        return checkMinMaxDoubleOption(path, min, max, plugin, plugin.getConfig());
    }

    public static boolean checkMinMaxDoubleOption(String path, double min, double max, Plugin plugin, FileConfiguration config) {
        return checkMinMaxDoubleOption0(path, min, max, plugin, config, true);
    }

    public static boolean recheckMinMaxDoubleOption(String path, Plugin plugin) {
        return recheckMinMaxDoubleOption(path, plugin, plugin.getConfig());
    }

    public static boolean recheckMinMaxDoubleOption(String path, Plugin plugin, FileConfiguration config) {
        return recheckMinMaxDoubleOption(path, Double.NEGATIVE_INFINITY, plugin, config);
    }

    public static boolean recheckMinMaxDoubleOption(String path, double min, Plugin plugin) {
        return recheckMinMaxDoubleOption(path, min, plugin, plugin.getConfig());
    }

    public static boolean recheckMinMaxDoubleOption(String path, double min, Plugin plugin, FileConfiguration config) {
        return recheckMinMaxDoubleOption(path, min, Double.POSITIVE_INFINITY, plugin, config);
    }

    public static boolean recheckMinMaxDoubleOption(String path, double min, double max, Plugin plugin) {
        return recheckMinMaxDoubleOption(path, min, max, plugin, plugin.getConfig());
    }

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

    public static boolean checkStringOption(String path, Plugin plugin) {
        return checkStringOption(path, plugin, plugin.getConfig());
    }

    public static boolean checkStringOption(String path, Plugin plugin, FileConfiguration config) {
        return checkStringOption0(path, plugin, config, true);
    }

    public static boolean recheckStringOption(String path, Plugin plugin) {
        return recheckStringOption(path, plugin, plugin.getConfig());
    }

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

    public static <T extends Enum<T>> boolean checkEnumOption(String path, Class<T> clazz, Plugin plugin) {
        return checkEnumOption(path, clazz, plugin, plugin.getConfig());
    }

    public static <T extends Enum<T>> boolean checkEnumOption(String path, Class<T> clazz, Plugin plugin, FileConfiguration config) {
        return checkEnumOption0(path, clazz, plugin, config, true);
    }

    public static <T extends Enum<T>> boolean recheckEnumOption(String path, Class<T> clazz, Plugin plugin) {
        return recheckEnumOption(path, clazz, plugin, plugin.getConfig());
    }

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

    public static boolean checkListOption(String path, Plugin plugin) {
        return checkListOption(path, plugin, plugin.getConfig());
    }

    public static boolean checkListOption(String path, Plugin plugin, FileConfiguration config) {
        return checkListOption0(path, plugin, config, true);
    }

    public static boolean recheckListOption(String path, Plugin plugin) {
        return recheckListOption(path, plugin, plugin.getConfig());
    }

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

    public static boolean checkBooleanList(String path, Plugin plugin) {
        return checkBooleanList(path, plugin, plugin.getConfig());
    }

    public static boolean checkBooleanList(String path, Plugin plugin, FileConfiguration config) {
        return checkBooleanList0(path, plugin, config, true);
    }

    public static boolean recheckBooleanList(String path, Plugin plugin) {
        return recheckBooleanList(path, plugin, plugin.getConfig());
    }

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

    public static boolean checkIntList(String path, Plugin plugin) {
        return checkIntList(path, plugin, plugin.getConfig());
    }

    public static boolean checkIntList(String path, Plugin plugin, FileConfiguration config) {
        return checkIntList(path, Integer.MIN_VALUE, plugin, config);
    }

    public static boolean checkIntList(String path, int min, Plugin plugin) {
        return checkIntList(path, min, plugin, plugin.getConfig());
    }

    public static boolean checkIntList(String path, int min, Plugin plugin, FileConfiguration config) {
        return checkIntList(path, min, Integer.MAX_VALUE, plugin, config);
    }

    public static boolean checkIntList(String path, int min, int max, Plugin plugin) {
        return checkIntList(path, min, max, plugin, plugin.getConfig());
    }

    public static boolean checkIntList(String path, int min, int max, Plugin plugin, FileConfiguration config) {
        return checkIntList0(path, min, max, plugin, config, true);
    }

    public static boolean recheckIntList(String path, Plugin plugin) {
        return recheckIntList(path, plugin, plugin.getConfig());
    }

    public static boolean recheckIntList(String path, Plugin plugin, FileConfiguration config) {
        return recheckIntList(path, Integer.MIN_VALUE, plugin, config);
    }

    public static boolean recheckIntList(String path, int min, Plugin plugin) {
        return recheckIntList(path, min, plugin, plugin.getConfig());
    }

    public static boolean recheckIntList(String path, int min, Plugin plugin, FileConfiguration config) {
        return recheckIntList(path, min, Integer.MAX_VALUE, plugin, config);
    }

    public static boolean recheckIntList(String path, int min, int max, Plugin plugin) {
        return recheckIntList(path, min, max, plugin, plugin.getConfig());
    }

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

    public static boolean checkDoubleList(String path, Plugin plugin) {
        return checkDoubleList(path, plugin, plugin.getConfig());
    }

    public static boolean checkDoubleList(String path, Plugin plugin, FileConfiguration config) {
        return checkDoubleList(path, Double.NEGATIVE_INFINITY, plugin, config);
    }

    public static boolean checkDoubleList(String path, double min, Plugin plugin) {
        return checkDoubleList(path, min, plugin, plugin.getConfig());
    }

    public static boolean checkDoubleList(String path, double min, Plugin plugin, FileConfiguration config) {
        return checkDoubleList(path, min, Double.POSITIVE_INFINITY, plugin, config);
    }

    public static boolean checkDoubleList(String path, double min, double max, Plugin plugin) {
        return checkDoubleList(path, min, max, plugin, plugin.getConfig());
    }

    public static boolean checkDoubleList(String path, double min, double max, Plugin plugin, FileConfiguration config) {
        return checkDoubleList0(path, min, max, plugin, config, true);
    }

    public static boolean recheckDoubleList(String path, Plugin plugin) {
        return recheckDoubleList(path, plugin, plugin.getConfig());
    }

    public static boolean recheckDoubleList(String path, Plugin plugin, FileConfiguration config) {
        return recheckDoubleList(path, Double.NEGATIVE_INFINITY, plugin, config);
    }

    public static boolean recheckDoubleList(String path, double min, Plugin plugin) {
        return recheckDoubleList(path, min, plugin, plugin.getConfig());
    }

    public static boolean recheckDoubleList(String path, double min, Plugin plugin, FileConfiguration config) {
        return recheckDoubleList(path, min, Double.POSITIVE_INFINITY, plugin, config);
    }

    public static boolean recheckDoubleList(String path, double min, double max, Plugin plugin) {
        return recheckDoubleList(path, min, max, plugin, plugin.getConfig());
    }

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

    public static boolean checkStringList(String path, Plugin plugin) {
        return checkStringList(path, plugin, plugin.getConfig());
    }

    public static boolean checkStringList(String path, Plugin plugin, FileConfiguration config) {
        return checkStringList0(path, plugin, config, true);
    }

    public static boolean recheckStringList(String path, Plugin plugin) {
        return recheckStringList(path, plugin, plugin.getConfig());
    }

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

    public static <T extends Enum<T>> boolean checkEnumList(String path, Class<T> clazz, Plugin plugin) {
        return checkEnumList(path, clazz, plugin, plugin.getConfig());
    }

    public static <T extends Enum<T>> boolean checkEnumList(String path, Class<T> clazz, Plugin plugin, FileConfiguration config) {
        return checkEnumList0(path, clazz, plugin, config, true);
    }

    public static <T extends Enum<T>> boolean recheckEnumList(String path, Class<T> clazz, Plugin plugin) {
        return recheckEnumList(path, clazz, plugin, plugin.getConfig());
    }

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
