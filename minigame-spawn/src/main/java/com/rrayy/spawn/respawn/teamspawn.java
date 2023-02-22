package com.rrayy.spawn.respawn;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Team;

import com.rrayy.spawn.util.team;

public class teamspawn implements Listener, CommandExecutor {
    private static JavaPlugin main = null;
    private Map<Team, Location> teamspwanloc = new HashMap<Team, Location>();
    private Location spawLocation;

    public teamspawn(JavaPlugin spawn){
        main = spawn;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (main == null) return false;
        Player p = (Player) sender;
        if (team.getteam(main, p) == null) return false;
        Team t = team.getteam(main, p);
        Location l;
        try{
            l = new Location(p.getWorld(), Integer.parseInt(args[0]), Integer.parseInt(args[1]), Integer.parseInt(args[2]));
        } catch (NumberFormatException ex) {
            l = p.getLocation();
        }
        teamspwanloc.put(t, l);
        p.sendMessage("팀의 스폰 위치가"+l.toString()+"로 설정 되었습니다");
        return false;
    }
    
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e){
        //if (e.getEntity().getKiller() instanceof Player)
        Player pl = e.getEntity();
        if (team.getteam(main, pl) == null) return;
        Team t = team.getteam(main, pl);
        if (t.getName() != null && teamspwanloc.containsKey(t)) {
            spawLocation = teamspwanloc.get(t);
        } else spawLocation = pl.getBedSpawnLocation();
        pl.setBedSpawnLocation(spawLocation);
    }


}
