package com.signature;

import java.util.ArrayList;

public class Team<T extends Player> implements Comparable<Team<T>>{
    private String name;
    private int played = 0;
    private int won = 0;
    private int lost = 0;
    private int tied = 0;

    private ArrayList<T> members = new ArrayList<>();

    public Team(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getPlayed() {
        return played;
    }

    public boolean addPlayer(T Player) {
        if (members.contains(Player)) {
            System.out.println(Player.getName() + " is already on this team");
            return false;
        } else {
            members.add(Player);
            System.out.println(Player.getName() + " picked for team " + this.name);
            return true;
        }
    }

    public int numPlayers() {
        return this.members.size();
    }

     public void matchResult(Team<T> opponent, int ourScore, int opponentScore) {
        if (ourScore > opponentScore) {
            this.won++;
        } else if (opponentScore > ourScore) {
            this.lost++;
        } else {
            this.tied++;
        }
        this.played++;
        if (opponent != null) {
            opponent.matchResult(null, opponentScore, ourScore);
        }
     }

     public int ranking() {
        return (this.won + this.tied);
     }

    @Override
    public int compareTo(Team<T> team) {
        if (this.ranking() > team.ranking()) {
            return 1;
        } else if (this.ranking() < team.ranking()) {
            return -1;
        } else {
            return 0;
        }
    }
}
