package com.signature;

import java.util.ArrayList;
import java.util.Collections;

public class SportsLeague<T extends Player> implements League {

    private final String name;
    private ArrayList<Team> leagueTable = new ArrayList<>();
    private Team[] sortedLst;


    public SportsLeague(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

//    @Override
    public boolean addTeam(Team team) {
        if (leagueTable.contains(team)) {
            System.out.println(team.getName() + " is already in the league.");
            return false;
        } else {
            leagueTable.add(team);
        }
        return false;
    }

    public void sortList() {
        sortedLst = leagueTable.toArray(new Team[0]);
        for (int i = 0; i < sortedLst.length-1; i++) {
            for (int j = i+1; j < leagueTable.size(); j++) {
                if (sortedLst[i].getPlayed() < sortedLst[j].getPlayed()) {
                    Team temp = sortedLst[i];
                    sortedLst[i] = sortedLst[j];
                    sortedLst[j] = temp;
                }
            }
//            System.out.println(sortedLst[i].getName() + " " + sortedLst[i].getPlayed());
        }
    }

    @Override
    public void getLeagueTable() {
        sortList();
        System.out.println("League Table :=>");
        for (int i = 0; i < leagueTable.size(); i++) {
            System.out.println((i+1) + ". " + sortedLst[i].getName());
        }
    }

    @Override
    public void getLast(int pos) {
        if (pos < 1) {
            System.out.println("Enter valid position!");
            return;
        }
        sortList();
        System.out.println("Last " + pos + " Teams in the league " + name + " :=>");
        for (int i = pos-1; i < leagueTable.size(); i++) {
            System.out.println((i+1) + ". " + sortedLst[i].getName());
        }
    }

    @Override
    public void getTop(int pos) {
        if (pos < 1) {
            System.out.println("Enter valid position!");
            return;
        }
        sortList();
        System.out.println("Top " + pos + " Teams in the league " + name + " :=>");
        for (int i = 0; i < pos; i++) {
            System.out.println((i+1) + ". " + sortedLst[i].getName());
        }
    }
}
