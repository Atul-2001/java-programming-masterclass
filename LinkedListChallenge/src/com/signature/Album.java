package com.signature;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Album {

    private static Scanner input = new Scanner(System.in);
    private LinkedList<Song> album;
    private String name;

    private Album(String name) {
        this.name = name;
        album = new LinkedList<Song>();
    }

    @Contract("_ -> new")
    public static @NotNull Album createAlbum(String name) {
        return new Album(name);
    }

    public String getName() {
        return name;
    }

    public LinkedList<Song> getAlbum() {
        return album;
    }

    public boolean addSong() {
        System.out.print("Enter Title of Song : ");
        String title = input.nextLine();
        title = title.toLowerCase();

        System.out.print("Enter Duration of Song : ");
        String duration = input.nextLine();

        if (findSong(title) == null) {
            album.add(Song.addSong(title,duration));
            return true;
        } else {
            return false;
        }
    }

    public void showAllSongs() {
        System.out.println("\n====Album : " + this.name + "====");

        for (int i = 0; i < album.size(); i++) {
            Song song = album.get(i);
            System.out.println("\nTrack #" + (i+1));
            System.out.println("Title    : " + song.getTitle());
            System.out.println("Duration : " + song.getDuration());
        }
        System.out.println();
    }

    public boolean removeSong(@NotNull String title) {
        Song getSong = findSong(title.toLowerCase());
        if (getSong != null) {
            album.remove(getSong);
            return true;
        } else {
            return false;
        }
    }

    public boolean removeSong(int track) {
        if (track > 0) {
            album.remove(track-1);
            return true;
        } else {
            return false;
        }
    }

    private @Nullable Song findSong(String title) {
        for (int i = 0; i < album.size(); i++) {
            Song checkSong = album.get(i);
            if (checkSong.getTitle().equals(title)) {
                return checkSong;
            }
        }
        return null;
    }

    public boolean addToPlaylist(String title, Playlist playlist) {
        Song song = findSong(title);

        if (song != null) {
            playlist.addSong(song);
            return true;
        } else {
            return false;
        }
    }

    public boolean addToPlaylist(int track, Playlist playlist) {
        Song song;
        if (track > 0) {
            song = album.get(track-1);
        } else {
            return false;
        }

        if (song != null) {
            playlist.addSong(song);
            return true;
        } else {
            return false;
        }
    }
}
