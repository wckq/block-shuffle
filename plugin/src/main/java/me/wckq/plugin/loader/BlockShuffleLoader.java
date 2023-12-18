package me.wckq.plugin.loader;

import me.wckq.api.loader.Loader;
import org.bukkit.plugin.Plugin;

public class BlockShuffleLoader implements Loader {

    private final Plugin plugin;

    public BlockShuffleLoader(final Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public void load() {
        new CommandLoader(plugin).load();
    }
}
