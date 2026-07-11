package me.loyalty.loyaltylogin;

import me.loyalty.loyaltylogin.commands.LoginCommand;
import me.loyalty.loyaltylogin.commands.RegisterCommand;
import me.loyalty.loyaltylogin.database.DatabaseManager;
import me.loyalty.loyaltylogin.login.LoginManager;
import me.loyalty.loyaltylogin.listener.PlayerListener;
import me.loyalty.loyaltylogin.premium.PremiumManager;

import org.bukkit.plugin.java.JavaPlugin;

public class LoyaltyLogin extends JavaPlugin {


    private static LoyaltyLogin instance;


    private PlayerManager playerManager;

    private DatabaseManager databaseManager;

    private LoginManager loginManager;

    private PremiumManager premiumManager;



    @Override
    public void onEnable() {


        instance = this;


        saveDefaultConfig();



        // Player Manager
        playerManager = new PlayerManager();




        // Database
        databaseManager = new DatabaseManager();

        databaseManager.connect();

        databaseManager.createTables();




        // Login Manager
        loginManager = new LoginManager();




        // Premium Manager
        premiumManager = new PremiumManager(
                databaseManager
        );





        // Commands

        if (getCommand("register") != null) {

            getCommand("register")
                    .setExecutor(
                            new RegisterCommand(this)
                    );

        }



        if (getCommand("login") != null) {

            getCommand("login")
                    .setExecutor(
                            new LoginCommand(this)
                    );

        }





        // Listener

        getServer()
                .getPluginManager()
                .registerEvents(
                        new PlayerListener(this),
                        this
                );





        getLogger().info(
                "LoyaltyLogin v2 Enabled!"
        );

    }






    @Override
    public void onDisable() {


        if(databaseManager != null) {

            databaseManager.disconnect();

        }



        if(loginManager != null) {

            loginManager.clear();

        }



        getLogger().info(
                "LoyaltyLogin Disabled!"
        );

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







    public PremiumManager getPremiumManager() {

        return premiumManager;

    }

}
