package me.wckq.plugin;

import me.wckq.plugin.loader.BlockShuffleLoader;
import org.bukkit.plugin.java.JavaPlugin;

public class BlockShufflePlugin extends JavaPlugin {

    private final BlockShuffleLoader loader = new BlockShuffleLoader(this);

    @Override
    public void onEnable() {
        loader.load();
    }

    @Override
    public void onDisable() {

    }
}
