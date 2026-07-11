package me.loyalty.loyaltylogin.login;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class LoginManager {

    private final Set<UUID> loggedInPlayers = new HashSet<>();


    public void login(UUID uuid) {
        loggedInPlayers.add(uuid);
    }


    public void logout(UUID uuid) {
        loggedInPlayers.remove(uuid);
    }


    public boolean isLoggedIn(UUID uuid) {
        return loggedInPlayers.contains(uuid);
    }


    public void clear() {
        loggedInPlayers.clear();
    }
}
