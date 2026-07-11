package me.loyalty.loyaltylogin.premium;

import java.util.UUID;

import me.loyalty.loyaltylogin.database.DatabaseManager;

public class PremiumManager {

    private final DatabaseManager databaseManager;


    public PremiumManager(DatabaseManager databaseManager) {

        this.databaseManager = databaseManager;

    }



    public boolean isPremium(UUID uuid) {

        return databaseManager.isPremium(
                uuid.toString()
        );

    }



    public void setPremium(UUID uuid) {

        databaseManager.setPremium(
                uuid.toString()
        );

    }

}
