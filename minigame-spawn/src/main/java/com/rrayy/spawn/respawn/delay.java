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

public class Delay implements CommandExecutor, Listener {
    private JavaPlugin main;
    private long dt = spawn.delaytime;
    private long loopint = 0L;
    private Map<UUID, Long> deathtime = new HashMap<>();

    public Delay(JavaPlugin plugin) {
        main = plugin;
        for (World w : main.getServer().getWorlds()) w.setGameRule(GameRule.DO_IMMEDIATE_RESPAWN, true);
        main.getServer().getScheduler().scheduleSyncRepeatingTask(main, new Runnable() {
            @Override
            public void run() {
                for (Player player : main.getServer().getOnlinePlayers()) {
                    UUID playerId = player.getUniqueId();
                    if (deathtime.containsKey(playerId)) {
                        Location respawnLocation = new Location(player.getWorld(), 0, 100, 0);
                        if (loopint == deathtime.get(playerId) + dt) {
                            player.setGameMode(GameMode.SURVIVAL);
                            player.resetTitle();
                            Location bedLocation = player.getBedSpawnLocation();
                            if (bedLocation != null) respawnLocation = bedLocation;
                            player.teleport(respawnLocation);
                            deathtime.remove(playerId);
                        }
                    }
                }
                loopint++;
            }
        }, 0L, 20L);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) return false;
        try {
            dt = Long.parseLong(args[0]);
            return true;
        } catch (NumberFormatException ex) {
            main.getLogger().warning(ex.toString());
        }
        return false;
    }
//if (e.getEntity().getKiller() instanceof Player)
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e){
        Player pl = e.getEntity();
        pl.setGameMode(GameMode.SPECTATOR);
        pl.teleport(new Location(pl.getWorld(), 0, 100, 0));
        deathtime.put(pl.getUniqueId(), loopint);
        pl.sendTitle("죽었습니다!", dt + "초 후에 부활합니다", 0, 1000000000, 0);
    }
}
