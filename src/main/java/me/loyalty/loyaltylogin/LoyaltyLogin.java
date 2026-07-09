package me.loyalty.loyaltylogin;

import org.bukkit.plugin.java.JavaPlugin;

public class LoyaltyLogin extends JavaPlugin {

    private static LoyaltyLogin instance;

    @Override
    public void onEnable() {
        instance = this;

        getLogger().info("=================================");
        getLogger().info(" LoyaltyLogin v2.0 Enabled!");
        getLogger().info(" Author: LoyaltyMC");
        getLogger().info("=================================");
    }

    @Override
    public void onDisable() {
        getLogger().info("LoyaltyLogin Disabled!");
    }

    public static LoyaltyLogin getInstance() {
        return instance;
    }
}
