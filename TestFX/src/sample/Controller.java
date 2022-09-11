package sample;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

public class Controller {
    @FXML
    private TextField search;
    @FXML
    private ProgressBar progress;

    public void handleDownloadAction(ActionEvent actionEvent) {
        Task<Void> task = new DownloadTask(search.getText());
        progress.progressProperty().bind(task.progressProperty());

        Thread thread = new Thread(task);
        thread.setDaemon(true);
        thread.start();

    }

    public void reset(ActionEvent actionEvent) {
        search.clear();
    }

    public void play(ActionEvent actionEvent) {
        Media media = new Media(new File("/home/anonymous/Music/Samajh Kar Chand Jis Ko - Full Song _ Baazigar _ Shah Rukh Khan _ Kajol _ Superhit Bollywood Song.mp3").toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);

        mediaPlayer.setOnReady(() -> {
            System.out.println(media.getDuration());
            System.out.println(media.getDuration().toSeconds());
            System.out.println(media.getSource());
            System.out.println(media.getTracks());
            System.out.println(media.getMetadata());
            System.out.println(media.getMarkers());
        });
    }

    private static class DownloadTask extends Task<Void> {

        private final String url;

        public DownloadTask(String url) {
            this.url = url;
        }

        @Override
        protected Void call() throws Exception {
            String ext = url.substring(url.lastIndexOf("."));
            URLConnection connection = new URL(url).openConnection();
            long fileLength = connection.getContentLength();

            try (InputStream is = connection.getInputStream();
                 OutputStream os = Files.newOutputStream(Paths.get("download" + ext))) {

                Objects.requireNonNull(os, "out");
                long transferred = 0;
                byte[] buffer = new byte[8196];
                int read;
                while ((read = is.read(buffer, 0, 8196)) >= 0) {
                    os.write(buffer, 0, read);
                    transferred += read;
                    updateProgress(transferred, fileLength);
                }
            }
            return null;
        }

        @Override
        protected void succeeded() {
            super.succeeded();
            System.out.println("Download completed!");
        }

        @Override
        protected void failed() {
            super.failed();
            System.out.println("Download failed!");
        }
    }
}
