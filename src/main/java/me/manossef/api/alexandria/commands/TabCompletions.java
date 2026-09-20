package me.manossef.api.alexandria.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.*;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public final class TabCompletions {

    public static final TabCompleter NONE = (s, c, l, a) -> Collections.emptyList();

    private TabCompletions() {
    }

    public static TabCompleter none() {

        return NONE;

    }

    public static TabCompleter literals(String... literals) {

        return everywhere(Arrays.stream(literals).collect(Collectors.toList()));

    }

    public static TabCompleter literalsAtFirst(String... literals) {

        return first(Arrays.stream(literals).collect(Collectors.toList()));

    }

    public static TabCompleter literalsAtIndex(int index, String... literals) {

        return argumentAtIndex(index, Arrays.stream(literals).collect(Collectors.toList()));

    }

    public static TabCompleter players() {

        return everywhere(() -> Bukkit.getOnlinePlayers().stream().map(Player::getName).collect(Collectors.toList()));

    }

    public static TabCompleter playersAtFirst() {

        return first(() -> Bukkit.getOnlinePlayers().stream().map(Player::getName).collect(Collectors.toList()));

    }

    public static TabCompleter playersAtIndex(int index) {

        return argumentAtIndex(index, () -> Bukkit.getOnlinePlayers().stream().map(Player::getName).collect(Collectors.toList()));

    }

    public static TabCompleter everywhere(List<String> arguments) {

        Objects.requireNonNull(arguments, "Cannot create a tab completer because \"arguments\" is null");
        return (s, c, l, args) -> removeNotStartingWith(new ArrayList<>(arguments), args[args.length - 1]);

    }

    public static TabCompleter everywhere(Supplier<List<String>> argumentSupplier) {

        Objects.requireNonNull(argumentSupplier, "Cannot create a tab completer because \"argumentSupplier\" is null");
        return (s, c, l, args) -> removeNotStartingWith(argumentSupplier.get(), args[args.length - 1]);

    }

    public static TabCompleter first(List<String> arguments) {

        Objects.requireNonNull(arguments, "Cannot create a tab completer because \"arguments\" is null");
        return argumentAtIndex(0, arguments);

    }

    public static TabCompleter first(Supplier<List<String>> argumentSupplier) {

        Objects.requireNonNull(argumentSupplier, "Cannot create a tab completer because \"argumentSupplier\" is null");
        return argumentAtIndex(0, argumentSupplier);

    }

    public static TabCompleter argumentAtIndex(int index, List<String> arguments) {

        Objects.requireNonNull(arguments, "Cannot create a tab completer because \"arguments\" is null");
        return (s, c, l, args) -> {

            if(args.length == index + 1)
                return removeNotStartingWith(new ArrayList<>(arguments), args[index]);
            return Collections.emptyList();

        };

    }

    public static TabCompleter argumentAtIndex(int index, Supplier<List<String>> argumentSupplier) {

        Objects.requireNonNull(argumentSupplier, "Cannot create a tab completer because \"argumentSupplier\" is null");
        return (s, c, l, args) -> {

            if(args.length == index + 1)
                return removeNotStartingWith(argumentSupplier.get(), args[index]);
            return Collections.emptyList();

        };

    }

    public static TabCompleter arguments(TabCompleter... tabCompleters) {

        for(TabCompleter tabCompleter : tabCompleters)
            Objects.requireNonNull(tabCompleter, "Cannot create a tab completer because an object in \"tabCompleters\" is null");
        return (s, c, l, args) -> {

            if(args.length <= tabCompleters.length)
                return tabCompleters[args.length - 1].onTabComplete(s, c, l, new String[] {args[args.length - 1]});
            return Collections.emptyList();

        };

    }

    public static TabCompleter completers(TabCompleter... tabCompleters) {

        for(TabCompleter tabCompleter : tabCompleters)
            Objects.requireNonNull(tabCompleter, "Cannot create a tab completer because an object in \"tabCompleters\" is null");
        return (s, c, l, args) -> {

            if(args.length <= tabCompleters.length)
                return tabCompleters[args.length - 1].onTabComplete(s, c, l, args);
            return Collections.emptyList();

        };

    }

    public static TabCompleter conditional(TabCompleter tabCompleter, Predicate<String[]> condition) {

        Objects.requireNonNull(tabCompleter, "Cannot create a tab completer because \"tabCompleter\" is null");
        Objects.requireNonNull(condition, "Cannot create a tab completer because \"condition\" is null");
        return conditionalOrElse(tabCompleter, condition, NONE);

    }

    public static TabCompleter conditionalOrElse(TabCompleter tabCompleter, Predicate<String[]> condition, TabCompleter fallback) {

        Objects.requireNonNull(tabCompleter, "Cannot create a tab completer because \"tabCompleter\" is null");
        Objects.requireNonNull(condition, "Cannot create a tab completer because \"condition\" is null");
        Objects.requireNonNull(fallback, "Cannot create a tab completer because \"fallback\" is null");
        return (s, c, l, args) -> {

            if(condition.test(args))
                return tabCompleter.onTabComplete(s, c, l, args);
            return fallback.onTabComplete(s, c, l, args);

        };

    }

    public static List<String> removeNotStartingWith(List<String> arguments, String typedArgument) {

        Objects.requireNonNull(arguments, "Cannot remove arguments because \"arguments\" is null");
        Objects.requireNonNull(typedArgument, "Cannot remove arguments because \"typedArgument\" is null");
        return filter(arguments, argument -> argument.toLowerCase().startsWith(typedArgument.toLowerCase()));

    }

    public static List<String> filter(List<String> arguments, Predicate<String> predicate) {

        Objects.requireNonNull(arguments, "Cannot filter arguments because \"arguments\" is null");
        Objects.requireNonNull(predicate, "Cannot filter arguments because \"predicate\" is null");
        arguments.removeIf(predicate.negate());
        return arguments;

    }

}
