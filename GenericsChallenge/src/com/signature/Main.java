package com.signature;

public class Main {

    public static void main(String[] args) {
        Team<Football> super11 = new Team<>("super11");
        super11.addPlayer(new Football("Messi"));
        super11.addPlayer(new Football("Ronaldo"));

        Team<Football> adelaideCrows = new Team<>("Adelaide Crows");
        adelaideCrows.addPlayer(new Football("Joe"));

        Team<Football> melbourne = new Team<>("Melbourne");
        Football banks = new Football("Gordon");
        melbourne.addPlayer(banks);

        Team<Basketball> baseballTeam = new Team<>("Chicago Cubs");
        baseballTeam.addPlayer(new Basketball("Pat"));
        
        System.out.println(adelaideCrows.numPlayers());

        Team<Football> hawthorn= new Team<>("Hawthorn");
        Team<Football> fremantle= new Team<>("Fremantle");

        hawthorn.matchResult(fremantle, 1, 0);
        hawthorn.matchResult(adelaideCrows, 3, 8);

        adelaideCrows.matchResult(fremantle, 2, 1);
//        adelaideCrows.matchResult(baseballTeam, 1, 1);

        System.out.println("Rankings");
        System.out.println(adelaideCrows.getName() + ": " + adelaideCrows.ranking());
        System.out.println(melbourne.getName() + ": " + melbourne.ranking());
        System.out.println(fremantle.getName() + ": " + fremantle.ranking());
        System.out.println(hawthorn.getName() + ": " + hawthorn.ranking());

        System.out.println(adelaideCrows.compareTo(melbourne));
        System.out.println(adelaideCrows.compareTo(hawthorn));
        System.out.println(hawthorn.compareTo(adelaideCrows));
        System.out.println(melbourne.compareTo(fremantle));

        SportsLeague<Football> FPL = new SportsLeague<Football>("FPL");
        FPL.addTeam(super11);
        FPL.addTeam(adelaideCrows);
        FPL.addTeam(melbourne);
        FPL.addTeam(hawthorn);
        FPL.addTeam(fremantle);

        FPL.getLeagueTable();
        FPL.getTop(3);
        FPL.getLast(3);
//        FPL.sortList();
    }
}
