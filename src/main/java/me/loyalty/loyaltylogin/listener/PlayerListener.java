package me.loyalty.loyaltylogin.listener;

import me.loyalty.loyaltylogin.LoyaltyLogin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.entity.Player;

public class PlayerListener implements Listener {

    private final LoyaltyLogin plugin;


    public PlayerListener(LoyaltyLogin plugin) {
        this.plugin = plugin;
    }



    @EventHandler
    public void onJoin(PlayerJoinEvent event) {

        Player player = event.getPlayer();

        String uuid = player.getUniqueId().toString();


        if (!plugin.getDatabaseManager()
                .isRegistered(uuid)) {


            player.sendMessage(
                    "§eWelcome! Please register using:"
            );

            player.sendMessage(
                    "§a/register <password>"
            );


        } else {


            player.sendMessage(
                    "§ePlease login using:"
            );

            player.sendMessage(
                    "§a/login <password>"
            );

        }


    }





    @EventHandler
    public void onQuit(PlayerQuitEvent event) {

        plugin.getLoginManager()
                .logout(event.getPlayer()
                .getUniqueId());

    }







    @EventHandler
    public void onMove(PlayerMoveEvent event) {

        Player player = event.getPlayer();


        if (!plugin.getLoginManager()
                .isLoggedIn(player.getUniqueId())) {


            if (event.getFrom()
                    .distance(event.getTo()) > 0) {


                event.setTo(event.getFrom());

            }

        }

    }







    @EventHandler
    public void onBreak(BlockBreakEvent event) {

        if (!plugin.getLoginManager()
                .isLoggedIn(event.getPlayer()
                .getUniqueId())) {


            event.setCancelled(true);


            event.getPlayer()
                    .sendMessage(
                    "§cPlease login first!"
                    );

        }

    }







    @EventHandler
    public void onPlace(BlockPlaceEvent event) {

        if (!plugin.getLoginManager()
                .isLoggedIn(event.getPlayer()
                .getUniqueId())) {


            event.setCancelled(true);

        }

    }







    @EventHandler
    public void onInteract(PlayerInteractEvent event) {

        if (!plugin.getLoginManager()
                .isLoggedIn(event.getPlayer()
                .getUniqueId())) {


            event.setCancelled(true);

        }

    }

}
