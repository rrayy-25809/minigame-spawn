package com.rrayy.spawn;

import org.bukkit.plugin.java.JavaPlugin;

import com.rrayy.spawn.respawn.delay;
import com.rrayy.spawn.respawn.teamspawn;
import com.rrayy.spawn.util.team;

public class spawn extends JavaPlugin{
    public delay d;
    public teamspawn t;
    private static JavaPlugin plugin = null;
    public boolean delaysp;
    public static Long delaytime;
    public boolean teamsp;

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
        delaysp = this.getConfig().getBoolean("delayed-respawn");
        delaytime = this.getConfig().getLong("respawn-delay");
        if (this.getConfig().getBoolean("basic-team")){
            team.red(plugin, null);
            team.blue(plugin, null);
            team.green(plugin, null);
            team.yellow(plugin, null);
        }
        if (delaysp) {
            d = new delay(this);
            getCommand("delay").setExecutor(d);
            getServer().getPluginManager().registerEvents(d, this);
        }
        teamsp = this.getConfig().getBoolean("team-respawn");
        if (teamsp) {
            t = new teamspawn(this);
            getCommand("teamspawn").setExecutor(t);
            getServer().getPluginManager().registerEvents(t, this);
        } 
    }

    @Override
    public void onDisable() {
        this.getConfig().set("delayed-respawn", delaysp);
        this.getConfig().set("respawn-delay", delaytime);
        this.getConfig().set("team-respawn", teamsp);
    }
}
