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
    private Map<UUID, Long> deathtime = new HashMap<>();

    public delay(JavaPlugin plugin) {
        main = plugin;
        for (World w : main.getServer().getWorlds()) w.setGameRule(GameRule.DO_IMMEDIATE_RESPAWN, true);
        main.getServer().getScheduler().scheduleSyncRepeatingTask(main, new Runnable() {
            @Override
            public void run() {
                for (Player p1 : main.getServer().getOnlinePlayers()) {
                    if (deathtime.containsKey(p1.getUniqueId())) {
                        for (World w : main.getServer().getWorlds()) p1.teleport(new Location(w, 0, 100, 0));
                        if (loopint.equals(deathtime.get(p1.getUniqueId()) + dt)) { // 값의 비교에는 equals 메소드를 사용
                            p1.setGameMode(GameMode.SURVIVAL);
                            p1.resetTitle();
                            if (p1.getBedSpawnLocation() != null) p1.teleport(p1.getBedSpawnLocation());// 플레이어의 침대 위치가 있는 경우에만 이동하도록 변경
                            else p1.teleport(p1.getWorld().getSpawnLocation()); // 침대 위치가 없는 경우 월드의 스폰 지점으로 이동하도록 변경
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
        if (main == null || args.length == 0) return false;
        try {
            dt = Long.parseLong(args[0]); // Integer.parseInt 대신 Long.parseLong을 사용하여 오버플로우 방지
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
        pl.sendTitle("죽었습니다!", dt + "초 후에 부활합니다", 0, 1000000000, 0);
    }
}
