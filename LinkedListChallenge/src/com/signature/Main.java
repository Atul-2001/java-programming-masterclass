package com.signature;

import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Scanner;

public class Main {

    private static Scanner input = new Scanner(System.in);
    private static ArrayList<Album> musicPlayer = new ArrayList<>();
    private static ArrayList<Playlist> musicPlaylist = new ArrayList<>();
    private static Main main = new Main();
    private static boolean playing = false;
    private static boolean goingForward;
    private static ListIterator<Song> i;

    public static void main(String[] args) {
        System.out.println("*-*-*Music Player*-*-*\n");

        musicPlayer.add(Album.createAlbum("Default"));
        boolean exit = false;
        printMenu(main);

        while (!exit) {
            System.out.print("\nEnter your option (press 6 for options) : ");
            int action = input.nextInt();
            input.nextLine();

            switch (action) {
                case 0:
                    exit = true;
                    break;

                case 1:
                    addSong();
                    break;

                case 2:
                    createAlbum();
                    break;

                case 3:
                    openAlbum();
                    break;

                case 4:
                    createPlaylist();
                    break;

                case 5:
                    openPlaylist();
                    break;

                case 6:
                    printMenu(main);
                    break;

                default:
                    System.out.println("Wrong option!");
                    break;
            }
        }
    }

    private static void printMenu(Object object) {
        if (object.equals(musicPlayer)) {
            System.out.println("0. Exit Album");
            System.out.println("1. Play");
            System.out.println("2. Replay");
            System.out.println("3. Play Previous");
            System.out.println("4. Play Next");
            System.out.println("5. Add To Playlist");
            System.out.println("6. Remove");
            System.out.println("7. Print Album Menu");
        } else if (object.equals(musicPlaylist)) {
            System.out.println("0. Exit Playlist");
            System.out.println("1. Play");
            System.out.println("2. Replay");
            System.out.println("3. Play Previous");
            System.out.println("4. Play Next");
            System.out.println("5. Remove From Playlist");
            System.out.println("6. Print Playlist Menu");
        } else {
            System.out.println("0. Exit player");
            System.out.println("1. Add Song");
            System.out.println("2. Create Album");
            System.out.println("3. Open Album");
            System.out.println("4. Create Playlist");
            System.out.println("5. Open Playlist");
            System.out.println("6. Print Menu");
        }
    }

    private static void addSong() {
        if (musicPlayer.size() == 1) {
            if (musicPlayer.get(0).addSong()) {
                System.out.println("Album : Default > Song added successfully..");
            } else {
                System.out.println("Album : Default > Song already exist!");
            }
        } else {
            System.out.println("\nChoose Album : ");
            for (int i = 0; i < musicPlayer.size(); i++) {
                Album album = musicPlayer.get(i);
                System.out.println((i+1) + ". " + album.getName());
            }
            System.out.print(">> ");
            int ch = input.nextInt();
            input.nextLine();

            if (musicPlayer.get(ch-1).addSong()) {
                System.out.println("Album : " + musicPlayer.get(ch-1).getName() + " > Song added successfully..");
            } else {
                System.out.println("Album : " + musicPlayer.get(ch-1).getName() + " > Song already exist!");
            }
        }
    }

    private static void createAlbum() {
        System.out.print("Enter name of New Album : ");
        String name = input.nextLine();

        if (!findAlbum(name)){
            musicPlayer.add(Album.createAlbum(name));
            System.out.println("Music player :> Album created successfully..");
        } else {
            System.out.println("Music player :> Album " + name + " already exist! Failed to create Album.");
        }
    }

    public static void openAlbum() {
        if (musicPlayer.size() == 1) {
            musicPlayer.get(0).showAllSongs();
            menu(musicPlayer, 0);
        } else {
            System.out.println("\nChoose Album : ");
            for (int i = 0; i < musicPlayer.size(); i++) {
                Album album = musicPlayer.get(i);
                System.out.println((i+1) + ". " + album.getName());
            }
            System.out.print(">> ");
            int ch = input.nextInt();
            if (ch > 0 && ch <= musicPlayer.size()) {
                musicPlayer.get(ch-1).showAllSongs();
                menu(musicPlayer, ch-1);
            } else {
                System.out.println("Wrong choice of Album! Choose from available Album.");
            }
        }
    }

    private static void createPlaylist() {
        System.out.print("Enter name of New Playlist : ");
        String name = input.nextLine();

        if (!findPlaylist(name)){
            musicPlaylist.add(Playlist.createPlaylist(name));
            System.out.println("Music player :> Playlist created successfully..");
        } else {
            System.out.println("Music player :> Playlist " + name + " already exist! Failed to create Playlist.");
        }
    }

    private static void openPlaylist() {
        if (musicPlaylist.size() == 0){
            System.out.println("No playlist available !");
        } else if (musicPlaylist.size() == 1) {
            musicPlaylist.get(0).showAllSongs();
            menu(musicPlaylist, 0);
        } else {
            System.out.println("\nChoose Playlist : ");
            for (int i = 0; i < musicPlaylist.size(); i++) {
                Playlist playlist = musicPlaylist.get(i);
                System.out.println((i+1) + ". " + playlist.getName());
            }
            System.out.print(">> ");
            int ch = input.nextInt();
            if (ch > 0 && ch <= musicPlaylist.size()) {
                musicPlaylist.get(ch-1).showAllSongs();
                menu(musicPlaylist, ch-1);
            } else {
                System.out.println("Wrong choice of Playlist! Choose from available Playlist.");
            }
        }
    }

    private static boolean findAlbum(String name) {
        for (int i = 0; i < musicPlayer.size(); i++) {
            Album checkAlbum = musicPlayer.get(i);
            if (checkAlbum.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    private static boolean findPlaylist(String name) {
        for (int i = 0; i < musicPlaylist.size(); i++) {
            Playlist checkPlaylist = musicPlaylist.get(i);
            if (checkPlaylist.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    private static void menu(Object object, int index) {
        boolean exit = false;
        printMenu(object);

        while (!exit) {
            System.out.print("\n Choose option :> ");
            int op = input.nextInt();
            input.nextLine();

            switch (op) {
                case 0:
                    exit = true;
                    break;

                case 1:
                    play(object, index);
                    break;

                case 2:
                    replay(object);
                    break;

                case 3:
                    playPrevious(object);
                    break;

                case 4:
                    playNext(object);
                    break;

                case 5:
                    if (object.equals(musicPlayer)) {
                        addToPlaylist(index);
                    } else if (object.equals(musicPlaylist)) {
                        removeFromPlaylist(index);
                    }
                    break;

                case 6:
                    if (object.equals(musicPlayer)) {
                        remove(index);
                    } else if (object.equals(musicPlaylist)) {
                        printMenu(object);
                    }
                    break;

                case 7:
                    if (object.equals(musicPlayer)) {
                        printMenu(object);
                    } else if (object.equals(musicPlaylist)) {
                        System.out.println("Wrong Choice !");
                    }
                    break;

                default:
                    System.out.println("Wrong Choice !");
                    break;
            }
        }
    }

    public static void play(Object object, int index) {
        String title = null, duration = null;

        if (playing) {
            System.out.println("Song paused...");
            playing = false;
            return;
        }

        if (object.equals(musicPlayer)) {
            i = musicPlayer.get(index).getAlbum().listIterator();
            if (i.hasNext()) {
                Song song = i.next();
                title = song.getTitle();
                duration = song.getDuration();
            } else {
                System.out.println("Reached the end of the list");
                return;
            }

        } else if (object.equals(musicPlaylist)) {
            i = musicPlaylist.get(index).getPlaylist().listIterator();
            if (i.hasNext()) {
                Song song = i.next();
                title = song.getTitle();
                duration = song.getDuration();
            } else {
                System.out.println("Reached the end of the list");
                return;
            }
        }
        System.out.println("Playing Song : " + title + " for Duration : " + duration);
        playing = true;
        goingForward = true;
    }

    public static void replay(Object object) {
        String title = null, duration = null;

        if (playing) {
            System.out.println("Song paused...");

            if (object.equals(musicPlayer)) {
                Song song = i.previous();
                title = song.getTitle();
                duration = song.getDuration();;
                i.next();
            } else if (object.equals(musicPlaylist)) {
                Song song = i.previous();
                title = song.getTitle();
                duration = song.getDuration();
                i.next();
            }
            System.out.println("Replaying Song : " + title + " for Duration : " + duration);
            playing = true;
        } else {
            System.out.println("Song is not playing. So cannot replay!");
        }
    }

    public static void playPrevious(Object object) {
        String title = null, duration = null;

        if (playing) {
            if(goingForward) {
                if(i.hasPrevious()) {
                    i.previous();
                }
                goingForward = false;
            }

            if (object.equals(musicPlayer)) {
                if (i.hasPrevious()) {
                    Song song = i.previous();
                    title = song.getTitle();
                    duration = song.getDuration();
                } else {
                    System.out.println("Reached the end of the list");
                    return;
                }

            } else if (object.equals(musicPlaylist)) { ;
                if (i.hasPrevious()) {
                    Song song = i.previous();
                    title = song.getTitle();
                    duration = song.getDuration();
                } else {
                    System.out.println("Reached the start of the list");
                    return;
                }
            }
            System.out.println("Playing Song : " + title + " for Duration : " + duration);
            playing = true;
        } else {
            System.out.println("Firstly play the Song.");
        }
    }

    public static void playNext(Object object) {
        String title = null, duration = null;

        if (playing) {
            if(!goingForward) {
                if(i.hasNext()) {
                    i.next();
                }
                goingForward = true;
            }

            if (object.equals(musicPlayer)) {
                if (i.hasNext()) {
                    Song song = i.next();
                    title = song.getTitle();
                    duration = song.getDuration();
                } else {
                    System.out.println("Reached the end of the list");
                    return;
                }

            } else if (object.equals(musicPlaylist)) { ;
                if (i.hasNext()) {
                    Song song = i.next();
                    title = song.getTitle();
                    duration = song.getDuration();
                } else {
                    System.out.println("Reached the end of the list");
                    return;
                }
            }
            System.out.println("Playing Song : " + title + " for Duration : " + duration);
            playing = true;
        } else {
            System.out.println("Firstly play the Song.");
        }
    }

    public static void addToPlaylist(int index) {
        if (musicPlaylist.size() == 0) {
            System.out.println("No playlist available!");
            return;
        }

        Album album = musicPlayer.get(index);
        System.out.print("Enter the Track# : ");
        int track = input.nextInt();
        input.nextLine();

        if (!(track > 0 && track <= album.getAlbum().size())) {
            System.out.println("Wrong choice of track! Choose from available track.");
            return;
        }

        System.out.println("\nChoose Playlist : ");
        for (int i = 0; i < musicPlaylist.size(); i++) {
            Playlist playlist = musicPlaylist.get(i);
            System.out.println((i+1) + ". " + playlist.getName());
        }
        System.out.print(">> ");
        int ch = input.nextInt();
        Playlist playlist = null;
        if (ch > 0 && ch <= musicPlaylist.size()) {
            playlist = musicPlaylist.get(ch-1);
        } else {
            System.out.println("Wrong Playlist ! Please choose correct playlist.");
            return;
        }

        if (album.addToPlaylist(track, playlist)) {
            System.out.println("Song Added to Playlist");
        } else {
            System.out.println("Failed to add to playlist !");
        }
    }

    public static void remove(int index) {
        Album album = musicPlayer.get(index);

        System.out.print("Enter the Track# : ");
        int track = input.nextInt();
        input.nextLine();

        if (!(track > 0 && track <= album.getAlbum().size())) {
            System.out.println("Wrong choice of track! Choose from available track.");
        } else {
            String song = album.getAlbum().get(track-1).getTitle();
            album.getAlbum().remove(track-1);

            for (int i = 0; i < musicPlaylist.size(); i++) {
                Playlist playlist = musicPlaylist.get(i);

                for (int j = 0; j < playlist.getPlaylist().size(); j++) {
                    if (playlist.getPlaylist().get(i).getTitle().equals(song)) {
                        playlist.getPlaylist().remove(j);
                        break;
                    }
                }
            }

            System.out.println("Song removed successfully !");
        }
    }

    public static void removeFromPlaylist(int index) {
        Playlist playlist = musicPlaylist.get(index);

        System.out.print("Enter the Track# : ");
        int track = input.nextInt();
        input.nextLine();

        if (!(track > 0 && track <= playlist.getPlaylist().size())) {
            System.out.println("Wrong choice of track! Choose from available track.");
        } else {
            playlist.getPlaylist().remove(track-1);
            System.out.println("Removed from playlist !");
        }
    }
}