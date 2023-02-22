package com.rrayy.spawn;

import org.bukkit.plugin.java.JavaPlugin;

import com.rrayy.spawn.respawn.delay;
import com.rrayy.spawn.respawn.teamspawn;

public class spawn extends JavaPlugin{
    public delay d;
    public teamspawn t;
    private static JavaPlugin plugin = null;
    public boolean delay;
    public static Long delaytime;
    public boolean team;

    // For other classes in our library
    public static JavaPlugin getPlugin() {
        return plugin;
    }

    // This method must not be used any where in the library!
    public static void setPlugin(final JavaPlugin plugin) {
        spawn.plugin = plugin;
    }

    @Override
    public void onEnable() {
        if (!(spawn.plugin == null)) getLogger().info("spawn is running with "+spawn.plugin.getName());
        this.saveDefaultConfig();
        delay = this.getConfig().getBoolean("delayed-respawn");
        delaytime = this.getConfig().getLong("respawn-delay");
        if (delay) {
            d = new delay(this);
            getCommand("delay").setExecutor(d);
            getServer().getPluginManager().registerEvents(d, this);
        }
        team = this.getConfig().getBoolean("team-respawn");
        if (team) {
            t = new teamspawn(this);
            getCommand("teamspawn").setExecutor(t);
            getServer().getPluginManager().registerEvents(t, this);
        } 
    }

    @Override
    public void onDisable() {
        this.getConfig().set("delayed-respawn", delay);
        this.getConfig().set("respawn-delay", delaytime);
        this.getConfig().set("team-respawn", team);
    }
}
