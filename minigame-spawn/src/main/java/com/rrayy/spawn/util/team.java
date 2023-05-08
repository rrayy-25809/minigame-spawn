package com.rrayy.spawn.util;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class team {
    private static Scoreboard sb = null;

    public static Team getteam(JavaPlugin spawn, Player player){
        if (spawn == null) return null;
        sb = spawn.getServer().getScoreboardManager().getMainScoreboard();
        for (Team team : sb.getTeams()) {
            if (team.hasEntry(player.getDisplayName())) return team;
        }
        return null;
    }

    public static Team red(JavaPlugin spawn, Player player){
        if (spawn == null) return null;
        sb = spawn.getServer().getScoreboardManager().getMainScoreboard();
        Team t = sb.registerNewTeam("red");
        t.setColor(ChatColor.RED);
        if (!(player == null)) jointeam(player, t);
        return t;
    }

    public static Team blue(JavaPlugin spawn, Player player){
        if (spawn == null) return null;
        sb = spawn.getServer().getScoreboardManager().getMainScoreboard();
        Team t = sb.registerNewTeam("blue");
        t.setColor(ChatColor.BLUE);
        if (!(player == null)) jointeam(player, t);
        return t;
    }

    public static Team yellow(JavaPlugin spawn, Player player){
        if (spawn == null) return null;
        sb = spawn.getServer().getScoreboardManager().getMainScoreboard();
        Team t = sb.registerNewTeam("yellow");
        t.setColor(ChatColor.YELLOW);
        if (!(player == null)) jointeam(player, t);
        return t;
    }

    public static Team green(JavaPlugin spawn, Player player){
        if (spawn == null) return null;
        sb = spawn.getServer().getScoreboardManager().getMainScoreboard();
        Team t = sb.registerNewTeam("green");
        t.setColor(ChatColor.GREEN);
        if (!(player == null)) jointeam(player, t);
        return t;
    }

    public static Team purple(JavaPlugin spawn, Player player){
        if (spawn == null) return null;
        sb = spawn.getServer().getScoreboardManager().getMainScoreboard();
        Team t = sb.registerNewTeam("purple");
        t.setColor(ChatColor.DARK_PURPLE);
        if (!(player == null)) jointeam(player, t);
        return t;
    }
    
    public static Team aqua(JavaPlugin spawn, Player player){
        if (spawn == null) return null;
        sb = spawn.getServer().getScoreboardManager().getMainScoreboard();
        Team t = sb.registerNewTeam("aqua");
        t.setColor(ChatColor.AQUA);
        if (!(player == null)) jointeam(player, t);
        return t;
    }
    
    public static Team pink(JavaPlugin spawn, Player player){
        if (spawn == null) return null;
        sb = spawn.getServer().getScoreboardManager().getMainScoreboard();
        Team t = sb.registerNewTeam("pink");
        t.setColor(ChatColor.LIGHT_PURPLE);
        if (!(player == null)) jointeam(player, t);
        return t;
    }
    
    public static void jointeam(Player player, Team team){
        if (player == null) return;
        if (team == null) return;
        team.addEntry(player.getName());
    }
}
