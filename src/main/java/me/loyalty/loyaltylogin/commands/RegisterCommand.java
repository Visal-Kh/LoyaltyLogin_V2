package me.loyalty.loyaltylogin.commands;

import me.loyalty.loyaltylogin.LoyaltyLogin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RegisterCommand implements CommandExecutor {


    private final LoyaltyLogin plugin;


    public RegisterCommand(LoyaltyLogin plugin) {
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

            player.sendMessage("§cUsage: /register <password>");
            return true;

        }


        String uuid = player.getUniqueId().toString();
        String name = player.getName();
        String password = args[0];


        if(plugin.getDatabaseManager()
                .isRegistered(uuid)) {

            player.sendMessage(
                    "§cYou are already registered!"
            );

            return true;
        }


        plugin.getDatabaseManager()
                .registerPlayer(
                        uuid,
                        name,
                        password
                );


        player.sendMessage(
                "§aRegistration successful!"
        );


        return true;
    }
}
