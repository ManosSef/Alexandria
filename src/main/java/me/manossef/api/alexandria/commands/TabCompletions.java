package me.manossef.api.alexandria.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.*;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * Contains methods for creating tab completers for plugin commands. Arguments tab completed by any completers created
 * by these methods are removed when the argument typed in the chat in that position isn't a beginning substring to
 * those arguments.
 * <p>
 * If <code>null</code> is passed into any of these methods' parameters, a {@link NullPointerException} will be thrown.
 */
public final class TabCompletions {
    /**
     * A tab completer that always tab completes nothing.
     */
    public static final TabCompleter NONE = (s, c, l, a) -> Collections.emptyList();

    private TabCompletions() {
    }

    /**
     * Returns a tab completer that always tab completes nothing.
     * @return A tab completer that always tab completes nothing.
     */
    public static TabCompleter none() {
        return NONE;
    }

    /**
     * Returns a tab completer that always tab completes the specified strings.
     * @param literals The strings to tab complete.
     * @return A tab completer that always tab completes the specified strings.
     */
    public static TabCompleter literals(String... literals) {
        return everywhere(Arrays.stream(literals).collect(Collectors.toList()));
    }

    /**
     * Returns a tab completer that tab completes the specified strings for the first argument.
     * @param literals The strings to tab complete.
     * @return A tab completer that tab completes the specified strings for the first argument.
     */
    public static TabCompleter literalsAtFirst(String... literals) {
        return first(Arrays.stream(literals).collect(Collectors.toList()));
    }

    /**
     * Returns a tab completer that tab completes the specified strings for the argument at the specified index.
     * @param index The index of the argument to tab complete <code>literals</code> for.
     * @param literals The strings to tab complete.
     * @return A tab completer that tab completes the specified strings for the argument at the specified index.
     */
    public static TabCompleter literalsAtIndex(int index, String... literals) {
        return argumentAtIndex(index, Arrays.stream(literals).collect(Collectors.toList()));
    }

    /**
     * Returns a tab completer that always tab completes all online players' names.
     * @return A tab completer that always tab completes all online players' names.
     */
    public static TabCompleter players() {
        return everywhere(() -> Bukkit.getOnlinePlayers().stream().map(Player::getName).collect(Collectors.toList()));
    }

    /**
     * Returns a tab completer that tab completes all online players' names for the first argument.
     * @return A tab completer that tab completes all online players' names for the first argument.
     */
    public static TabCompleter playersAtFirst() {
        return first(() -> Bukkit.getOnlinePlayers().stream().map(Player::getName).collect(Collectors.toList()));
    }

    /**
     * Returns a tab completer that tab completes all online players' names for the argument at the specified index.
     * @param index The index of the argument to tab complete all players' names for.
     * @return A tab completer that tab completes all online players' names for the argument at the specified index.
     */
    public static TabCompleter playersAtIndex(int index) {
        return argumentAtIndex(index, () -> Bukkit.getOnlinePlayers().stream().map(Player::getName).collect(Collectors.toList()));
    }

    /**
     * Returns a tab completer that always tab completes the strings in the provided list.
     * @param arguments The list of strings to tab complete.
     * @return A tab completer that always tab completes the strings in the provided list.
     */
    public static TabCompleter everywhere(List<String> arguments) {
        Objects.requireNonNull(arguments, "Cannot create a tab completer because \"arguments\" is null");
        return (s, c, l, args) -> removeNotStartingWith(new ArrayList<>(arguments), args[args.length - 1]);
    }

    /**
     * Returns a tab completer that always tab completes the strings in the list returned by the provided supplier.
     * @param argumentSupplier The supplier for the list of strings to tab complete.
     * @return A tab completer that always tab completes the strings in the list returned by the provided supplier.
     */
    public static TabCompleter everywhere(Supplier<List<String>> argumentSupplier) {
        Objects.requireNonNull(argumentSupplier, "Cannot create a tab completer because \"argumentSupplier\" is null");
        return (s, c, l, args) -> removeNotStartingWith(argumentSupplier.get(), args[args.length - 1]);
    }

    /**
     * Returns a tab completer that tab completes the strings in the provided list for the first argument.
     * @param arguments The list of strings to tab complete.
     * @return A tab completer that tab completes the strings in the provided list for the first argument.
     */
    public static TabCompleter first(List<String> arguments) {
        Objects.requireNonNull(arguments, "Cannot create a tab completer because \"arguments\" is null");
        return argumentAtIndex(0, arguments);
    }

    /**
     * Returns a tab completer that tab completes the strings in the list returned by the provided supplier for the
     * first argument.
     * @param argumentSupplier The supplier for the list of strings to tab complete.
     * @return A tab completer that tab completes the strings in the list returned by the provided supplier for the
     * first argument.
     */
    public static TabCompleter first(Supplier<List<String>> argumentSupplier) {
        Objects.requireNonNull(argumentSupplier, "Cannot create a tab completer because \"argumentSupplier\" is null");
        return argumentAtIndex(0, argumentSupplier);
    }

    /**
     * Returns a tab completer that tab completes the strings in the provided list for the argument at the specified
     * index.
     * @param index The index of the argument to tab complete strings for.
     * @param arguments The list of strings to tab complete.
     * @return A tab completer that tab completes the strings in the provided list for the argument at the specified
     * index.
     */
    public static TabCompleter argumentAtIndex(int index, List<String> arguments) {
        Objects.requireNonNull(arguments, "Cannot create a tab completer because \"arguments\" is null");
        return (s, c, l, args) -> {
            if(args.length == index + 1)
                return removeNotStartingWith(new ArrayList<>(arguments), args[index]);
            return Collections.emptyList();
        };
    }

    /**
     * Returns a tab completer that tab completes the strings in the list returned by the provided supplier for the
     * argument at the specified index.
     * @param index The index of the argument to tab complete strings for.
     * @param argumentSupplier The supplier for the list of strings to tab complete.
     * @return A tab completer that tab completes the strings in the list returned by the provided supplier for the
     * argument at the specified index.
     */
    public static TabCompleter argumentAtIndex(int index, Supplier<List<String>> argumentSupplier) {
        Objects.requireNonNull(argumentSupplier, "Cannot create a tab completer because \"argumentSupplier\" is null");
        return (s, c, l, args) -> {
            if(args.length == index + 1)
                return removeNotStartingWith(argumentSupplier.get(), args[index]);
            return Collections.emptyList();
        };
    }

    /**
     * Returns a tab completer that, for the index of the argument being tab completed, applies the tab completer at
     * that index of the given array, only passing the argument being tab completed into the tab completers as the
     * first argument.
     * @param tabCompleters The tab completers to apply.
     * @return A tab completer that, for the index of the argument being tab completed, applies the tab completer at
     * that index of the given array.
     */
    public static TabCompleter arguments(TabCompleter... tabCompleters) {
        for(TabCompleter tabCompleter : tabCompleters)
            Objects.requireNonNull(tabCompleter, "Cannot create a tab completer because an object in \"tabCompleters\" is null");
        return (s, c, l, args) -> {
            if(args.length <= tabCompleters.length)
                return tabCompleters[args.length - 1].onTabComplete(s, c, l, new String[] {args[args.length - 1]});
            return Collections.emptyList();
        };
    }

    /**
     * Returns a tab completer that, for the index of the argument being tab completed, applies the tab completer at
     * that index of the given array, always passing all arguments into the tab completers.
     * @param tabCompleters The tab completers to apply.
     * @return A tab completer that, for the index of the argument being tab completed, applies the tab completer at
     * that index of the given array.
     */
    public static TabCompleter completers(TabCompleter... tabCompleters) {
        for(TabCompleter tabCompleter : tabCompleters)
            Objects.requireNonNull(tabCompleter, "Cannot create a tab completer because an object in \"tabCompleters\" is null");
        return (s, c, l, args) -> {
            if(args.length <= tabCompleters.length)
                return tabCompleters[args.length - 1].onTabComplete(s, c, l, args);
            return Collections.emptyList();
        };
    }

    /**
     * Returns a tab completer that always applies the provided tab completer so long as the provided condition is met,
     * and tab completes nothing otherwise.
     * @param tabCompleter The tab completer to apply.
     * @param condition The predicate to test on the typed arguments.
     * @return A tab completer that always applies the provided tab completer so long as the provided condition is met,
     * and tab completes nothing otherwise.
     */
    public static TabCompleter conditional(TabCompleter tabCompleter, Predicate<String[]> condition) {
        Objects.requireNonNull(tabCompleter, "Cannot create a tab completer because \"tabCompleter\" is null");
        Objects.requireNonNull(condition, "Cannot create a tab completer because \"condition\" is null");
        return conditionalOrElse(tabCompleter, condition, NONE);
    }

    /**
     * Returns a tab completer that always applies the provided tab completer so long as the provided condition is met,
     * and applies the provided fallback tab completer otherwise.
     * @param tabCompleter The tab completer to apply.
     * @param condition The predicate to test on the typed arguments.
     * @param fallback The tab completer to fall back on when the condition is not met.
     * @return A tab completer that always applies the provided tab completer so long as the provided condition is met,
     * and applies the provided fallback tab completer otherwise.
     */
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

    /**
     * Removes all strings that don't start with the specified string from the provided list, then returns that list.
     * @param arguments The list to remove strings from.
     * @param typedArgument The string that strings need to start with to remain in the list.
     * @return <code>arguments</code> after removing any strings that don't start with <code>typedArgument</code>.
     */
    public static List<String> removeNotStartingWith(List<String> arguments, String typedArgument) {
        Objects.requireNonNull(arguments, "Cannot remove arguments because \"arguments\" is null");
        Objects.requireNonNull(typedArgument, "Cannot remove arguments because \"typedArgument\" is null");
        return filter(arguments, argument -> argument.toLowerCase().startsWith(typedArgument.toLowerCase()));
    }

    /**
     * Removes all strings that don't meet the provided condition from the provided list, then returns that list.
     * @param arguments The list to remove strings from.
     * @param predicate The predicate to test on the strings in the list.
     * @return <code>arguments</code> after removing any strings that don't meet <code>predicate</code>.
     */
    public static List<String> filter(List<String> arguments, Predicate<String> predicate) {
        Objects.requireNonNull(arguments, "Cannot filter arguments because \"arguments\" is null");
        Objects.requireNonNull(predicate, "Cannot filter arguments because \"predicate\" is null");
        arguments.removeIf(predicate.negate());
        return arguments;
    }
}