package com.signature;

public class Song {

    private String Title;
    private String Duration;

    private Song(String title, String duration) {
        Title = title;
        Duration = duration;
    }

    public String getTitle() {
        return Title;
    }

    public String getDuration() {
        return Duration;
    }

    public static Song addSong(String title, String duration) {
        return new Song(title, duration);
    }
}
