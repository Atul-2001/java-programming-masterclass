package sample.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataSource {

    public static final String DB_NAME = "music.db";
    public static final String CONNECTION_URL = "jdbc:sqlite:/home/anonymous/IdeaProjects/Music/" + DB_NAME;

    public static final String TABLE_ARTISTS = "artists";
    public static final String COL_ARTISTS_ID = "_id";
    public static final String COL_ARTISTS_NAME = "name";
    public static final int INDEX_ARTISTS_ID = 1;
    public static final int INDEX_ARTISTS_NAME = 2;

    public static final String TABLE_ALBUMS = "albums";
    public static final String COL_ALBUMS_ID = "_id";
    public static final String COL_ALBUMS_NAME = "name";
    public static final String COL_ALBUMS_ARTIST = "artist";
    public static final int INDEX_ALBUMS_ID = 1;
    public static final int INDEX_ALBUMS_NAME = 2;
    public static final int INDEX_ALBUMS_ARTIST = 3;

    public static final String TABLE_SONGS = "songs";
    public static final String COL_SONGS_TRACK = "track";
    public static final String COL_SONGS_TITLE = "title";
    public static final String COL_SONGS_ALBUM = "album";
    public static final int INDEX_SONGS_TRACK = 2;
    public static final int INDEX_SONGS_TITLE = 3;
    public static final int INDEX_SONGS_ALBUMS = 4;

    public static final String TABLE_ARTIST_SONG_VIEW = "artist_list";

    public static final int ORDER_BY_NONE = 1;
    public static final int ORDER_BY_ASC = 2;
    public static final int ORDER_BY_DESC = 3;

    public static final String CREATE_ARTIST_FOR_SONG_VIEW = "CREATE VIEW IF NOT EXISTS " +
            TABLE_ARTIST_SONG_VIEW + " AS SELECT " + TABLE_ARTISTS + "." + COL_ARTISTS_NAME + " AS " + COL_ALBUMS_ARTIST + ", " +
            TABLE_ALBUMS + "." + COL_ALBUMS_NAME + " AS " + COL_SONGS_ALBUM + ", " +
            TABLE_SONGS + "." + COL_SONGS_TRACK + ", " + TABLE_SONGS + "." + COL_SONGS_TITLE +
            " FROM " + TABLE_SONGS +
            " INNER JOIN " + TABLE_ALBUMS + " ON " + TABLE_SONGS + "." + COL_SONGS_ALBUM + " = " + TABLE_ALBUMS + "." + COL_ALBUMS_ID +
            " INNER JOIN " + TABLE_ARTISTS + " ON " + TABLE_ALBUMS + "." + COL_ALBUMS_ARTIST + " = " + TABLE_ARTISTS + "." + COL_ARTISTS_ID +
            " ORDER BY " + TABLE_ARTISTS + "." + COL_ARTISTS_NAME + ", " + TABLE_ALBUMS + "." + COL_ALBUMS_NAME + ", " + TABLE_SONGS + "." + COL_SONGS_TRACK +
            " COLLATE NOCASE ASC";

    public static final String QUERY_ALBUM_BY_ARTIST_ID = "SELECT * FROM " + TABLE_ALBUMS + " WHERE " + COL_ALBUMS_ARTIST + " = ? ORDER BY " + COL_ALBUMS_NAME + " COLLATE NOCASE";

    private Connection conn;
    private PreparedStatement pStmt;
    private PreparedStatement insertIntoArtist;
    private PreparedStatement insertIntoAlbum;
    private PreparedStatement insertIntoSong;
    private PreparedStatement queryArtist;
    private PreparedStatement queryAlbum;
    private PreparedStatement querySong;
    private PreparedStatement queryAlbumByArtistID;
    private PreparedStatement updateArtistName;

    private static DataSource instance = new DataSource();

    private DataSource() {}

    public static DataSource getInstance() {
        return instance;
    }

    public boolean open() {
        try {
            conn = DriverManager.getConnection(CONNECTION_URL);
            pStmt = conn.prepareStatement("SELECT " + COL_ALBUMS_ARTIST + ", " + COL_SONGS_ALBUM + ", " + COL_SONGS_TRACK + " FROM " + TABLE_ARTIST_SONG_VIEW + " WHERE " + COL_SONGS_TITLE + " = ?");
            insertIntoArtist = conn.prepareStatement("INSERT INTO " + TABLE_ARTISTS + " (" + COL_ARTISTS_NAME + ") VALUES (?)", Statement.RETURN_GENERATED_KEYS);
            insertIntoAlbum = conn.prepareStatement("INSERT INTO " + TABLE_ALBUMS + " (" + COL_ALBUMS_NAME + ", " + COL_ALBUMS_ARTIST + ") VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
            insertIntoSong = conn.prepareStatement("INSERT INTO " + TABLE_SONGS + " (" + COL_SONGS_TRACK + ", " + COL_SONGS_TITLE + ", " + COL_SONGS_ALBUM + ") VALUES (?, ?, ?)");
            queryArtist = conn.prepareStatement("SELECT " + COL_ARTISTS_ID + " FROM " + TABLE_ARTISTS + " WHERE " + COL_ARTISTS_NAME + " = ?");
            queryAlbum = conn.prepareStatement("SELECT " + COL_ALBUMS_ID + " FROM " + TABLE_ALBUMS + " WHERE " + COL_ALBUMS_NAME + " = ?");
            querySong = conn.prepareStatement("SELECT * FROM " + TABLE_SONGS + " WHERE " + COL_SONGS_ALBUM + " = ? AND " + COL_SONGS_TITLE + " = ?");
            queryAlbumByArtistID = conn.prepareStatement(QUERY_ALBUM_BY_ARTIST_ID);
            updateArtistName = conn.prepareStatement("UPDATE " + TABLE_ARTISTS + " SET " + COL_ARTISTS_NAME + " = ? WHERE " + COL_ARTISTS_ID + " = ?" );
            return true;
        } catch (SQLException e) {
            System.out.println("Failed to connect! " + e.getMessage());
            return false;
        }
    }

    public void close() {
        try {
            if (pStmt != null) {
                pStmt.close();
            }

            if (insertIntoArtist != null) {
                insertIntoArtist.close();
            }

            if (insertIntoAlbum != null) {
                insertIntoAlbum.close();
            }

            if (insertIntoSong != null) {
                insertIntoSong.close();
            }

            if (queryArtist != null) {
                queryArtist.close();
            }

            if (queryAlbum != null) {
                queryAlbum.close();
            }

            if (queryAlbumByArtistID != null) {
                queryAlbumByArtistID.close();
            }

            if (updateArtistName != null) {
                updateArtistName.close();
            }

            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Artist> queryArtists(int sortOrder) {
        StringBuilder query = new StringBuilder("SELECT * FROM ");
        query.append(TABLE_ARTISTS);
        if (sortOrder != ORDER_BY_NONE) {
            query.append(" ORDER BY ");
            query.append(COL_ARTISTS_NAME);
            query.append(" COLLATE NOCASE ");
            if (sortOrder == ORDER_BY_DESC) {
                query.append("DESC");
            } else {
                query.append("ASC");
            }
        }

        try (Statement statement = conn.createStatement();
             ResultSet results = statement.executeQuery(query.toString())) {

            List<Artist> artists = new ArrayList<>();
            while (results.next()) {
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    System.out.println("Interrupted : " + e.getMessage());
                }
                Artist artist = new Artist();
                artist.setId(results.getInt(INDEX_ARTISTS_ID));
                artist.setName(results.getString(INDEX_ARTISTS_NAME));
                artists.add(artist);
            }

            return artists;
        } catch (SQLException e) {
            System.out.println("Query Failed : " + e.getMessage());
            return null;
        }

    }

    public List<Album> queryAlbumsByArtists(int id) {
        try {
            queryAlbumByArtistID.setInt(1, id);
            ResultSet result = queryAlbumByArtistID.executeQuery();
            List<Album> albums = new ArrayList<>();
            while (result.next()) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    System.out.println("Interrupted : " + e.getMessage());
                }
                Album album = new Album();
                album.setId(result.getInt(1));
                album.setName(result.getString(2));
                album.setArtist(result.getInt(3));
                albums.add(album);
            }
            return albums;
        } catch (SQLException e) {
            System.out.println("Query failed : " + e.getMessage());
            return null;
        }
    }

    public List<String> queryAlbumsByArtists(String artist, int sortOrder) {
        StringBuilder query = new StringBuilder("SELECT " + TABLE_ALBUMS + "." + COL_ALBUMS_NAME + " FROM " + TABLE_ALBUMS);
        query.append(" INNER JOIN " + TABLE_ARTISTS + " ON " + TABLE_ALBUMS + "." + COL_ALBUMS_ARTIST + " = " + TABLE_ARTISTS + "." + COL_ARTISTS_ID);
        query.append(" WHERE " + TABLE_ARTISTS + "." + COL_ARTISTS_NAME + " = " + "\"").append(artist).append("\"");

        if (sortOrder != ORDER_BY_NONE) {
            query.append(" ORDER BY " + TABLE_ALBUMS + "." + COL_ALBUMS_NAME + " COLLATE NOCASE ");
            if (sortOrder == ORDER_BY_DESC) {
                query.append("DESC");
            } else {
                query.append("ASC");
            }
        }

        System.out.println(query.toString());

        try (Statement stmt = conn.createStatement();
             ResultSet results = stmt.executeQuery(query.toString())) {

            List<String> albums = new ArrayList<>();
            while (results.next()) {
                albums.add(results.getString(1));
            }

            return albums;
        } catch (SQLException e) {
            System.out.println("Query failed : " + e.getMessage());
            return null;
        }
    }

    public boolean createArtistsForSongView() {
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(CREATE_ARTIST_FOR_SONG_VIEW);
            return true;
        } catch (SQLException e) {
            System.out.println("Create view failed : " + e.getMessage());
            return false;
        }
    }

    private int insertArtist(String artist) throws SQLException {

        queryArtist.setString(1, artist);
        ResultSet result = queryArtist.executeQuery();
        if (result.next()) {
            return result.getInt(1);
        } else {
            insertIntoArtist.setString(1, artist);
            int affectedRows = insertIntoArtist.executeUpdate();
            if (affectedRows != 1) {
                throw new SQLException("Couldn't insert artist");
            } else {
                ResultSet generatedKeys = insertIntoArtist.getGeneratedKeys();
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Couldn't get _id for artist");
                }
            }
        }
    }

    private int insertAlbum(String album, int artistID) throws SQLException {

        queryAlbum.setString(1, album);
        ResultSet result = queryAlbum.executeQuery();
        if (result.next()) {
            return result.getInt(1);
        } else {
            insertIntoAlbum.setString(1, album);
            insertIntoAlbum.setInt(2, artistID);
            int affectedRows = insertIntoAlbum.executeUpdate();
            if (affectedRows != 1) {
                throw new SQLException("Couldn't insert album");
            } else {
                ResultSet generatedKeys = insertIntoAlbum.getGeneratedKeys();
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Couldn't get _id for album");
                }
            }
        }
    }

    public boolean insertSong(String artist, String album, int track, String title) {

        try {
            System.out.println("Locking database...");
            conn.setAutoCommit(false);

            int artistID = insertArtist(artist);
            int albumID = insertAlbum(album, artistID);

            querySong.setInt(1, albumID);
            querySong.setString(2, title);

            ResultSet result = querySong.executeQuery();
            if (result.next()) {
                System.out.println("Song '" + title + "' already exists.");
                return false;
            } else {
                insertIntoSong.setInt(1, track);
                insertIntoSong.setString(2, title);
                insertIntoSong.setInt(3, albumID);

                int affectedRows = insertIntoSong.executeUpdate();
                if (affectedRows == 1) {
                    conn.commit();
                    return true;
                } else {
                    throw new SQLException("The song insert failed!");
                }
            }

        } catch (SQLException e) {
            System.out.println("Insert song exception : " + e.getMessage());
            try {
                System.out.println("Performing rollback...");
                conn.rollback();
            } catch (SQLException e2) {
                System.out.println("This is really very bad. : " + e.getMessage());
            }
        } finally {
            try {
                System.out.println("Resetting default commit behaviour.");
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                System.out.println("Couldn't reset auto-commit. : " + e.getMessage());
            }
        }
        return false;
    }

    public boolean updateArtist(String name, int id) {
        try {
            updateArtistName.setString(1, name);
            updateArtistName.setInt(2, id);

            int affectedRows = updateArtistName.executeUpdate();
            return affectedRows == 1;
        } catch (SQLException e) {
            System.out.println("Query failed : " + e.getMessage());
            return false;
        }
    }
}
