package com.signature;

import com.signature.model.Artist;
import com.signature.model.DataSource;
import com.signature.model.SongArtist;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        DataSource source = new DataSource();
        if (source.open()) {
            System.out.println("Connection established successfully!");
        } else {
            System.out.println("Connection Failed!");
        }

        List<Artist> artists = source.queryArtists(0);
        if (artists == null) {
            System.out.println("No Artist found!");
        } else {
            for (Artist artist : artists) {
                System.out.printf("%03d", artist.getId());
                System.out.println(" " + artist.getName());
            }
        }

        List<String> albums = source.queryAlbumsByArtists("Iron Maiden", DataSource.ORDER_BY_ASC);
        if (albums != null) {
            for (String album : albums) {
                System.out.println(album);
            }
        } else {
            System.out.println("No album from Iron Maiden");
        }

        List<SongArtist> songArtists = source.queryArtistForSong("Go Your Own Way", DataSource.ORDER_BY_ASC);
        if (songArtists != null) {
            for (SongArtist songArtist : songArtists) {
                System.out.println(songArtist.getArtist() + " | "
                                   + songArtist.getAlbum() + " | "
                                   + songArtist.getTrack() + "  | "
                                   + songArtist.getSong());
            }
        } else {
            System.out.println("No song found!");
        }

        System.out.println(source.createArtistsForSongView());

//        Scanner scanner = new Scanner(System.in);
//        System.out.print("Enter song : ");
//        String title = scanner.nextLine();
////        input : Go Your Own Way" or 1=1 "
        songArtists = source.viewArtistForSongH("Go Your Own Way");
        if (songArtists.isEmpty()) {
            System.out.println("Can't find any artist.");
        } else {
            for (SongArtist songArtist : songArtists) {
                System.out.println("From View --- " + "Artist = " + songArtist.getArtist() +
                        " Album = " + songArtist.getAlbum() + " Track = " + songArtist.getTrack());
            }
        }

        System.out.println("PS ---------------------------------");
        songArtists = source.viewArtistForSong("Go Your Own Way");
        if (songArtists.isEmpty()) {
            System.out.println("Can't find any artist.");
        } else {
            for (SongArtist songArtist : songArtists) {
                System.out.println("From View --- " + "Artist = " + songArtist.getArtist() +
                        " Album = " + songArtist.getAlbum() + " Track = " + songArtist.getTrack());
            }
        }

        source.insertSong("Grateful Dead", "In The Dark", 1, "Touch of gray");
        source.insertSong("Bob Dylan", "Bob Dylan's Greatest Hits", 5, "Like A Rolling Stone");

        source.close();
    }
}
