package com.rrayy.spawn.util;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class team {
    private static JavaPlugin main = null;
    private static Scoreboard sb = main.getServer().getScoreboardManager().getMainScoreboard();

    public static Team getteam(JavaPlugin spawn ,Player player){
        main = spawn;
        if (spawn == null) return null;
        for (Team team : sb.getTeams()) {
            if (team.hasEntry(player.getDisplayName())) return team;
        }
        return null;
    }

    public static void addteam(JavaPlugin spawn ,Player player){
        main = spawn;
        if (spawn == null) return;
    }
}
