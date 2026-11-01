package me.manossef.api.alexandria.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.TabCompleter;
import org.bukkit.plugin.Plugin;

import java.util.function.Consumer;

/**
 * Houses the implementation of a basic command that reloads a plugin's configuration files.
 */
public final class ReloadConfigCommand {
    private ReloadConfigCommand() {
    }

    /**
     * Returns a command executor for a basic command that reloads the provided plugin's <code>config.yml</code> file.
     * The command is named after the plugin and takes <code>reload</code> as its first argument, which is required
     * for the configuration file to be reloaded.
     * @param plugin The plugin to create the command executor for.
     * @return A command executor for a basic configuration reloading command.
     */
    public static CommandExecutor executor(Plugin plugin) {
        return executor(plugin, Plugin::reloadConfig);
    }

    /**
     * Returns a command executor for a basic command that runs the provided function on the provided plugin. The
     * intention is that the function should reload some configuration file used by the plugin. The command is named
     * after the plugin and takes <code>reload</code> as its first argument, which is required for the function to be
     * run.
     * @param plugin The plugin to create the command executor for.
     * @param reloadFunction The function the command runs on the plugin.
     * @return A command executor for a basic configuration reloading command.
     */
    public static CommandExecutor executor(Plugin plugin, Consumer<Plugin> reloadFunction) {
        return (sender, cmd, label, args) -> {
            if(args.length == 0) {
                sender.sendMessage(ChatColor.RED + "You need to specify an argument for this command.");
                return true;
            }
            if(args[0].equalsIgnoreCase("reload")) {
                reloadFunction.accept(plugin);
                sender.sendMessage(ChatColor.GREEN + "Reloaded the configuration settings for \"" + plugin.getName() + "\".");
                return true;
            }
            sender.sendMessage(ChatColor.RED + "Invalid argument: \"" + args[0] + "\". Expected \"reload\".");
            return true;
        };
    }

    /**
     * Returns a tab completer that tab completes <code>reload</code> for the first argument. This tab completer is
     * meant to be used for commands with executors created using {@link #executor(Plugin)} or
     * {@link #executor(Plugin, Consumer)}.
     * @return A tab completer that tab completes <code>reload</code> for the first argument.
     */
    public static TabCompleter tabCompleter() {
        return TabCompletions.literalsAtFirst("reload");
    }

    /**
     * Sets the provided plugin command's executor to one created using {@link #executor(Plugin)}, and its tab
     * completer to the one returned by {@link #tabCompleter()}.
     * @param command The command to set up.
     */
    public static void setupCommand(PluginCommand command) {
        command.setExecutor(executor(command.getPlugin()));
        command.setTabCompleter(tabCompleter());
    }

    /**
     * Sets the provided plugin command's executor to one created using {@link #executor(Plugin, Consumer)}, and its tab
     * completer to the one returned by {@link #tabCompleter()}.
     * @param command The command to set up.
     * @param reloadFunction The function the command runs on the plugin.
     */
    public static void setupCommand(PluginCommand command, Consumer<Plugin> reloadFunction) {
        command.setExecutor(executor(command.getPlugin(), reloadFunction));
        command.setTabCompleter(tabCompleter());
    }
}