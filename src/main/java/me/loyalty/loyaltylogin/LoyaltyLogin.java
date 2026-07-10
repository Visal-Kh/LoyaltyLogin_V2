package me.loyalty.loyaltylogin;

import org.bukkit.plugin.java.JavaPlugin;

public class LoyaltyLogin extends JavaPlugin {

    private static LoyaltyLogin instance;
    private PlayerManager playerManager;

    @Override
    public void onEnable() {
        instance = this;

        saveDefaultConfig();

        playerManager = new PlayerManager();

        getLogger().info("LoyaltyLogin v2 Enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("LoyaltyLogin Disabled!");
    }

    public static LoyaltyLogin getInstance() {
        return instance;
    }

    public PlayerManager getPlayerManager() {
        return playerManager;
    }
}
