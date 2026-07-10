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
            File file = new File(LoyaltyLogin.getInstance().getDataFolder(), "database.db");

            connection = DriverManager.getConnection("jdbc:sqlite:" + file);

            LoyaltyLogin.getInstance().getLogger().info("SQLite Connected!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
