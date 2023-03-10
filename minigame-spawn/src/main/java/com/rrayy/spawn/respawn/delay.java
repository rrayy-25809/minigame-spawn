package com.rrayy.spawn.respawn;

import org.bukkit.GameMode;
import org.bukkit.GameRule;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.java.JavaPlugin;

import com.rrayy.spawn.spawn;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class delay implements CommandExecutor, Listener {
    private JavaPlugin main;
    private Long dt = spawn.delaytime;
    private Long loopint = (long) 0;
    private Map<UUID, Long> deathtime = new HashMap<UUID, Long>();

    public delay(JavaPlugin plugin) {
        main = plugin;
        for (World w : main.getServer().getWorlds()) w.setGameRule(GameRule.DO_IMMEDIATE_RESPAWN, true);
        main.getServer().getScheduler().scheduleSyncRepeatingTask(main, new Runnable() {
            @Override
            public void run() {
                for (Player p1 : main.getServer().getOnlinePlayers()) {
                    if (deathtime.containsKey(p1.getUniqueId())){
                        for(World w : main.getServer().getWorlds()) p1.teleport(new Location(w, 0, 100, 0));
                        if (loopint == deathtime.get(p1.getUniqueId()) + dt) {
                            p1.setGameMode(GameMode.SURVIVAL);
                            p1.resetTitle();
                            p1.teleport(p1.getBedLocation());
                            deathtime.remove(p1.getUniqueId());
                        }
                    }
                }
                loopint++;
            }
        }, 0L, 20L);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (main == null) return false;
        try {
            dt = (long) Integer.parseInt(args[0]);
            return true;
        } catch (NumberFormatException ex) {
            main.getLogger().warning(ex.toString());
        }
        return false;
    }

    @EventHandler//if (e.getEntity().getKiller() instanceof Player)
    public void onPlayerDeath(PlayerDeathEvent e){
        Player pl = e.getEntity();
        pl.setGameMode(GameMode.SPECTATOR);
        deathtime.put(pl.getUniqueId(), loopint);
        pl.sendTitle("???????????????!", dt + "??? ?????? ???????????????", 0, 1000000000, 0);
    }
}
