/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Duel;

import Main.Main;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

/**
 *
 * @author User
 */
public class Countdown extends BukkitRunnable{
    
    int counter = 0;
    Main plugin;
    Player player1;
    Player player2;
    
    public Countdown(Player player1, Player player2, Main main){
        this.plugin = main;
        this.player1 = player1;
        this.player2 = player2;
    }

    @Override
    public void run() {
        if(counter == 10){
            plugin.getPrintFormatter().sendPlayerNotification(player1, "Fight!");
            plugin.getPrintFormatter().sendPlayerNotification(player2, "Fight!");
            plugin.getDuelHandler().getDuels().add(new Duel(player1, player2, plugin));
            this.cancel();
        }else{
        plugin.getPrintFormatter().sendPlayerNotification(player1, counter + "");
        plugin.getPrintFormatter().sendPlayerNotification(player2, counter + "");
        counter++;
        }
    }
    
}
