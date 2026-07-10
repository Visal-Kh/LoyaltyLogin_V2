package me.loyalty.loyaltylogin;

import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class PlayerManager {

    private final Set<UUID> loggedInPlayers = new HashSet<>();

    public boolean isLoggedIn(Player player) {
        return loggedInPlayers.contains(player.getUniqueId());
    }

    public void login(Player player) {
        loggedInPlayers.add(player.getUniqueId());
    }

    public void logout(Player player) {
        loggedInPlayers.remove(player.getUniqueId());
    }
}
