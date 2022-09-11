package com.signature;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedList;

public class Playlist {

    private String name;
    private LinkedList<Song> playlist;

    private Playlist(String name) {
        this.name = name;
        playlist = new LinkedList<Song>();
    }

    public String getName() {
        return name;
    }

    public LinkedList<Song> getPlaylist() {
        return playlist;
    }

    public static Playlist createPlaylist(String name) {
        return new Playlist(name);
    }

    public boolean addSong(Song song) {
        if (!findSong(song)) {
            playlist.add(song);
            return true;
        } else {
            return false;
        }
    }

    public boolean removeSong(@NotNull String title) {
        Song getSong = findSong(title.toLowerCase());
        if (getSong != null) {
            playlist.remove(getSong);
            return true;
        } else {
            return false;
        }
    }

    public boolean removeSong(int track) {
        if (track > 0) {
            playlist.remove(track-1);
            return true;
        } else {
            return false;
        }
    }

    public void showAllSongs() {
        System.out.println("\n====Playlist : " + this.name + "====");

        for (int i = 0; i < playlist.size(); i++) {
            Song song = playlist.get(i);
            System.out.println("\nTrack #" + (i+1));
            System.out.println("Title    : " + song.getTitle());
            System.out.println("Duration : " + song.getDuration());
        }
        System.out.println();
    }

    private boolean findSong(Song song) {
        if (playlist.contains(song)) {
            return true;
        } else {
            return false;
        }
    }

    private @Nullable Song findSong(String title) {
        for (int i = 0; i < playlist.size(); i++) {
            Song checkSong = playlist.get(i);
            if (checkSong.getTitle().equals(title)) {
                return checkSong;
            }
        }
        return null;
    }
}
