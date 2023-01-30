package com.rrayy.spawn.playermanager;

import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;

public class playermanager {
    Player player;

    public playermanager(Player p){
        player = p;
    }

    public Player getplayer(){
        return player;
    }

    public Team addTeam(Team t){
        Team team = t;
        team.addEntry(player.getName());
        return team;
    }
}
