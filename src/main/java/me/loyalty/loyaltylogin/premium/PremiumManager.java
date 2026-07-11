package me.loyalty.loyaltylogin.premium;

import me.loyalty.loyaltylogin.database.DatabaseManager;

import java.util.UUID;

public class PremiumManager {

    private final DatabaseManager database;


    public PremiumManager(DatabaseManager database) {
        this.database = database;
    }


    public boolean isPremium(UUID uuid) {

        return database.isPremium(
                uuid.toString()
        );

    }


    public void setPremium(UUID uuid) {

        database.setPremium(
                uuid.toString()
        );

    }

}
