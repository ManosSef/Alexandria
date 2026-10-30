package me.manossef.api.alexandria.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.TabCompleter;
import org.bukkit.plugin.Plugin;

import java.util.function.Consumer;

public final class ReloadConfigCommand {
    private ReloadConfigCommand() {
    }

    public static CommandExecutor executor(Plugin plugin) {
        return executor(plugin, Plugin::reloadConfig);
    }

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

    public static TabCompleter tabCompleter() {
        return TabCompletions.literalsAtFirst("reload");
    }

    public static void setupCommand(PluginCommand command) {
        command.setExecutor(executor(command.getPlugin()));
        command.setTabCompleter(tabCompleter());
    }

    public static void setupCommand(PluginCommand command, Consumer<Plugin> reloadFunction) {
        command.setExecutor(executor(command.getPlugin(), reloadFunction));
        command.setTabCompleter(tabCompleter());
    }
}