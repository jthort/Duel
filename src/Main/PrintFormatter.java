/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Main;

import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class PrintFormatter {
    
   private final ConsoleCommandSender sender;
    private final String pluginTag = "[Duel] ";
    
    public PrintFormatter(Main duel){
        sender = duel.getServer().getConsoleSender();
    }
    
    public void sendConsoleNotification(String notification){
       sender.sendMessage(ChatColor.YELLOW + pluginTag + notification);
    }
    public void sendConsoleError(String error){
        sender.sendMessage(ChatColor.RED + pluginTag + error);
    }
    public void sendConsoleMessage(String message){
        sender.sendMessage(pluginTag + message);
    }
    public void sendPlayerNotification(Player player, String message){
        player.sendMessage(ChatColor.YELLOW + message);
    }
    public void sendPlayerError(Player player, String message){
        player.sendMessage(ChatColor.RED + message);
    }
    public void sendPlayerMessage(Player player, String message){
        player.sendMessage(ChatColor.WHITE + message);
    }
    public void sendPlayerAccomplishmentMessage(Player player, String message){
        player.sendMessage(ChatColor.GREEN + message);
    }
}
