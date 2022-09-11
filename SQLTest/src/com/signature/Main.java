package com.signature;

import java.sql.*;

public class Main {

    public static void main(String[] args) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:sqlite:/home/anonymous/IdeaProjects/SQLTest/Contact.db");
            Statement stmt = conn.createStatement();
            stmt.execute("CREATE TABLE IF NOT EXISTS contact (Name TEXT, Email TEXT, Phone INTEGER)");
//            stmt.execute("INSERT INTO contact values ('Atul', 'atul@email.com', 8869975446)");
//            stmt.execute("INSERT INTO contact values ('Papa', 'papa@email.com', 8009626948)");
//            stmt.execute("INSERT INTO contact values ('Rishu', 'rishu@email.com', 7054935280)");
//            stmt.execute("INSERT INTO contact values ('Home', 'home@email.com', 9935577383)");

//            stmt.execute("UPDATE contact SET Email='atul@gmail.com' WHERE Name='Atul'");
//            stmt.execute("DELETE FROM contact WHERE Name='Home'");

            stmt.execute("SELECT * FROM contact");
            ResultSet result = stmt.getResultSet();

            while (result.next()) {
                System.out.println(result.getString(1) + " " + result.getString(2) + " " + result.getString(3));
            }
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
