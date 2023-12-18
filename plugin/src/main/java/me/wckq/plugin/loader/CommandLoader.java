package me.wckq.plugin.loader;

import cloud.commandframework.CommandTree;
import cloud.commandframework.annotations.AnnotationParser;
import cloud.commandframework.arguments.parser.ParserParameters;
import cloud.commandframework.arguments.parser.StandardParameters;
import cloud.commandframework.bukkit.BukkitCommandManager;
import cloud.commandframework.bukkit.CloudBukkitCapabilities;
import cloud.commandframework.execution.AsynchronousCommandExecutionCoordinator;
import cloud.commandframework.execution.CommandExecutionCoordinator;
import cloud.commandframework.meta.CommandMeta;
import cloud.commandframework.paper.PaperCommandManager;
import me.wckq.api.command.Command;
import me.wckq.api.loader.Loader;
import me.wckq.plugin.command.BlockShuffleCommand;
import org.bukkit.command.BlockCommandSender;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

import java.awt.print.Paper;
import java.util.function.Function;

public class CommandLoader implements Loader {

    private final Plugin plugin;

    public CommandLoader(final Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void load() {
        try {
            registerCommands(
                    new BlockShuffleCommand()
            );
        } catch (final Exception exception) {
            plugin.getLogger().info("Failed to register commands");
            plugin.getServer().getPluginManager().disablePlugin(plugin);
        }
    }

    private void registerCommands(final Command... commands) throws Exception {
        BukkitCommandManager<CommandSender> commandManager = null;

        final Function<CommandTree<CommandSender>, CommandExecutionCoordinator<CommandSender>> executionCoordinatorFunction =
                AsynchronousCommandExecutionCoordinator.<CommandSender>builder().build();

        final Function<CommandSender, CommandSender> mapper = Function.identity();

        commandManager = new PaperCommandManager<>(
                plugin,
                executionCoordinatorFunction,
                mapper,
                mapper
        );

        final Function<ParserParameters, CommandMeta> commandMetaFunction = parserParameters ->
                CommandMeta.simple()
                        .with(CommandMeta.DESCRIPTION, parserParameters.get(StandardParameters.DESCRIPTION, "No description"))
                        .build();

        final AnnotationParser<CommandSender> annotationParser = new AnnotationParser<>(
                commandManager,
                CommandSender.class,
                commandMetaFunction
        );

        for (final Command command : commands) {
            annotationParser.parse(command);
        }

        annotationParser.parseContainers();
    }
}
