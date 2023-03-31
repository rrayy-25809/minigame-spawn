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
    private Map<Team, Location> teamspwanloc = new HashMap<Team, Location>();// 각 팀의 스폰 위치를 저장하는 Map
    private Location spawLocation;// 새로운 스폰 위치를 저장할 변수

    // 생성자
    public teamspawn(JavaPlugin spawn){
        main = spawn;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (main == null) return false;
        Player p = (Player) sender;// 명령어를 실행한 플레이어
        if (team.getteam(main, p) == null) return false;
        Team t = team.getteam(main, p);
        // 명령어로 입력한 좌표값을 이용하여 새로운 스폰 위치 생성
        Location l;
        try{
            l = new Location(p.getWorld(), Long.parseLong(args[0]), Long.parseLong(args[1]), Long.parseLong(args[2]));
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
            l = p.getLocation();
        }
        // 해당 팀의 스폰 위치를 갱신
        teamspwanloc.put(t, l);
        p.sendMessage("팀의 스폰 위치가 " + l.toString() + "로 설정 되었습니다");
        return false;
    }
    
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e){
        // 죽은 플레이어가 속한 팀
        if (e.getEntity() == null) return;
        Player pl = e.getEntity();
        if (team.getteam(main, pl) == null) return;
        Team t = team.getteam(main, pl);
        // 해당 팀의 스폰 위치가 설정되어 있으면 스폰 위치를 해당 위치로 설정
        if (t.getName() != null && teamspwanloc.containsKey(t)) spawLocation = teamspwanloc.get(t);
        else spawLocation = pl.getBedSpawnLocation();
        // 플레이어의 스폰 위치를 변경
        pl.setBedSpawnLocation(spawLocation, true);
    }
}