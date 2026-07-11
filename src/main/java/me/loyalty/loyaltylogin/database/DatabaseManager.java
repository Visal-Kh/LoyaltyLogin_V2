package me.loyalty.loyaltylogin.database;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import me.loyalty.loyaltylogin.LoyaltyLogin;

public class DatabaseManager {

    private Connection connection;


    public void connect() {

        try {

            File file = new File(
                    LoyaltyLogin.getInstance().getDataFolder(),
                    "database.db"
            );


            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }


            connection = DriverManager.getConnection(
                    "jdbc:sqlite:" + file
            );


            LoyaltyLogin.getInstance()
                    .getLogger()
                    .info("SQLite Connected!");


        } catch (SQLException e) {

            e.printStackTrace();

        }
    }



    public Connection getConnection() {

        return connection;

    }



    public void createTables() {

        try {

            connection.createStatement()
                    .executeUpdate(

                    "CREATE TABLE IF NOT EXISTS players (" +

                    "uuid TEXT PRIMARY KEY," +

                    "name TEXT NOT NULL," +

                    "password TEXT NOT NULL," +

                    "premium INTEGER DEFAULT 0" +

                    ");"

            );


        } catch (SQLException e) {

            e.printStackTrace();

        }
    }



    public boolean isRegistered(String uuid) {


        try {


            var ps = connection.prepareStatement(

                    "SELECT uuid FROM players WHERE uuid=?"

            );


            ps.setString(1, uuid);


            var rs = ps.executeQuery();


            return rs.next();



        } catch (SQLException e) {


            e.printStackTrace();


        }


        return false;

    }





    public void registerPlayer(
            String uuid,
            String name,
            String password
    ) {


        try {


            var ps = connection.prepareStatement(

                    "INSERT INTO players(uuid,name,password,premium) VALUES(?,?,?,?)"

            );


            ps.setString(1, uuid);

            ps.setString(2, name);

            ps.setString(3, password);

            ps.setInt(4, 0);



            ps.executeUpdate();



        } catch (SQLException e) {


            e.printStackTrace();


        }

    }






    public boolean checkPassword(
            String uuid,
            String password
    ) {


        try {


            var ps = connection.prepareStatement(

                    "SELECT password FROM players WHERE uuid=?"

            );


            ps.setString(1, uuid);



            var rs = ps.executeQuery();



            if (rs.next()) {


                String savedPassword =
                        rs.getString("password");



                return savedPassword.equals(password);


            }



        } catch (SQLException e) {


            e.printStackTrace();


        }



        return false;

    }






    public void disconnect() {


        try {


            if (connection != null &&
                    !connection.isClosed()) {


                connection.close();


                LoyaltyLogin.getInstance()
                        .getLogger()
                        .info("SQLite Disconnected!");

            }



        } catch (SQLException e) {


            e.printStackTrace();


        }

    }

}
