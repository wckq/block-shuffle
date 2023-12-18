package me.wckq.plugin.command;

import cloud.commandframework.annotations.CommandMethod;
import me.wckq.api.command.Command;
import org.bukkit.entity.Player;

public class BlockShuffleCommand implements Command {

    @CommandMethod("blockshuffle")
    public void blockShuffle(final Player player) {
        player.sendMessage(":V");
    }
}
