package sample;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableView;
import javafx.scene.effect.Effect;
import sample.model.Album;
import sample.model.Artist;
import sample.model.DataSource;

public class Controller {

    @FXML private TableView artistTable;
    @FXML private ProgressBar progressBar;

    public void initialize() {
//        showArtists(null);
    }

    public void showAlbumsByArtist(ActionEvent actionEvent) {
        final Artist artist = (Artist) artistTable.getSelectionModel().getSelectedItem();
        if (artist == null) {
            System.out.println("No Artist is selected.");
        } else {
            Task<ObservableList<Album>> showAlbumsTask = new Task<ObservableList<Album>>() {
                @Override
                protected ObservableList<Album> call() throws Exception {
                    return FXCollections.observableArrayList(DataSource.getInstance().queryAlbumsByArtists(artist.getId()));
                }
            };

            artistTable.itemsProperty().bind(showAlbumsTask.valueProperty());
            progressBar.progressProperty().bind(showAlbumsTask.progressProperty());
            progressBar.setVisible(true);

            showAlbumsTask.setOnSucceeded(event -> progressBar.setVisible(false));
            showAlbumsTask.setOnFailed(event -> progressBar.setVisible(false));

            new Thread(showAlbumsTask).start();
        }
    }

    public void showArtists(ActionEvent actionEvent) {
        Task<ObservableList<Artist>> showArtistsTask = new GetAllArtists();
        artistTable.itemsProperty().bind(showArtistsTask.valueProperty());
        progressBar.progressProperty().bind(showArtistsTask.progressProperty());
        progressBar.setVisible(true);

        showArtistsTask.setOnSucceeded(event -> progressBar.setVisible(false));
        showArtistsTask.setOnFailed(event -> progressBar.setVisible(false));
//        Platform.runLater(() -> showArtistsTask.run());
        new Thread(showArtistsTask).start();
    }

    public void updateArtists(ActionEvent actionEvent) {
        final Artist artist = (Artist) artistTable.getItems().get(2);
        Task<Boolean> updateArtistTask = new Task<Boolean>() {
            @Override
            protected Boolean call() throws Exception {
                return DataSource.getInstance().updateArtist("AC/DC", artist.getId());
            }
        };

        updateArtistTask.setOnSucceeded(event -> {
            if (updateArtistTask.valueProperty().get()) {
                artist.setName("AC/DC");
                artistTable.refresh();
            }
        });

        new Thread(updateArtistTask).start();
    }
}

class GetAllArtists extends Task<ObservableList<Artist>> {
    @Override
    protected ObservableList<Artist> call() {
        return FXCollections.observableArrayList(DataSource.getInstance().queryArtists(DataSource.ORDER_BY_ASC));
    }
}
