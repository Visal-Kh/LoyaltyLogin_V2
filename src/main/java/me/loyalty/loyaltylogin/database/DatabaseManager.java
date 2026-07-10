public me.loyalty.loyaltylogin.database;

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

public void createTables() {
    try {
        connection.createStatement().executeUpdate(
                "CREATE TABLE IF NOT EXISTS players (" +
                "uuid TEXT PRIMARY KEY," +
                "name TEXT NOT NULL," +
                "password TEXT," +
                "premium INTEGER DEFAULT 0" +
                ");"
        );
    } catch (Exception e) {
        e.printStackTrace();
    }
}

public boolean isRegistered(String uuid) {
    try {
        var statement = connection.prepareStatement(
                "SELECT uuid FROM players WHERE uuid = ?"
        );
        statement.setString(1, uuid);

        var result = statement.executeQuery();
        return result.next();
    } catch (Exception e) {
        e.printStackTrace();
    }

    return false;
}
}
