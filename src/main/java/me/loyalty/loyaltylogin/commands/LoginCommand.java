package me.loyalty.loyaltylogin.commands;

import me.loyalty.loyaltylogin.LoyaltyLogin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LoginCommand implements CommandExecutor {

    private final LoyaltyLogin plugin;


    public LoginCommand(LoyaltyLogin plugin) {
        this.plugin = plugin;
    }


    @Override
    public boolean onCommand(
            CommandSender sender,
            Command command,
            String label,
            String[] args
    ) {


        if (!(sender instanceof Player player)) {

            sender.sendMessage("Only players can use this command!");
            return true;

        }


        if (args.length != 1) {

            player.sendMessage("§cUsage: /login <password>");
            return true;

        }


        String uuid = player.getUniqueId().toString();
        String password = args[0];


        if (!plugin.getDatabaseManager()
                .isRegistered(uuid)) {

            player.sendMessage(
                    "§cYou don't have an account! Use /register <password>"
            );

            return true;
        }


        // Check password
        if (!plugin.getDatabaseManager()
                .checkPassword(uuid, password)) {

            player.sendMessage(
                    "§cWrong password!"
            );

            return true;
        }


        plugin.getLoginManager()
                .login(player.getUniqueId());


        player.sendMessage(
                "§aLogin successful!"
        );


        return true;
    }
}
