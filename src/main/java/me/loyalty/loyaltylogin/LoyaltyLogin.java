package me.loyalty.loyaltylogin;

import me.loyalty.loyaltylogin.database.DatabaseManager;
import me.loyalty.loyaltylogin.login.LoginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class LoyaltyLogin extends JavaPlugin {

    private static LoyaltyLogin instance;

    private PlayerManager playerManager;
    private DatabaseManager databaseManager;
    private LoginManager loginManager;


    @Override
    public void onEnable() {

        instance = this;

        saveDefaultConfig();


        playerManager = new PlayerManager();

        databaseManager = new DatabaseManager();
        databaseManager.connect();
        databaseManager.createTables();


        loginManager = new LoginManager();


        getLogger().info("LoyaltyLogin v2 Enabled!");
    }


    @Override
    public void onDisable() {

        if(databaseManager != null) {
            databaseManager.disconnect();
        }

        if(loginManager != null) {
            loginManager.clear();
        }


        getLogger().info("LoyaltyLogin Disabled!");
    }


    public static LoyaltyLogin getInstance() {
        return instance;
    }


    public PlayerManager getPlayerManager() {
        return playerManager;
    }


    public DatabaseManager getDatabaseManager() {
        return databaseManager;
    }


    public LoginManager getLoginManager() {
        return loginManager;
    }
}
