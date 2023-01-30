package com.rrayy.spawn.respawn;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import com.rrayy.spawn.spawn;

public class delay implements CommandExecutor, Listener {
    private JavaPlugin main = new spawn();
    private int dt = spawn.delaytime;
    public int loopint = 0;

    public delay() {
        main.getServer().getScheduler().scheduleSyncRepeatingTask(main, new Runnable() {
            public void run() {
                Math.floorMod(loopint, dt);
                loopint++;
            }
        }, 0L, 20L);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        try {
            dt =Integer.parseInt(args[0]);
            return true;
        } catch (NumberFormatException ex) {
            main.getLogger().warning(ex.toString());
        }
        return false;
    }
}
