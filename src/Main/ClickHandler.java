/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class ClickHandler implements Listener{
    
    Main plugin;
    
    public ClickHandler(Main mainClass){
        this.plugin = mainClass;
    }
    private List<String> recentlyRequestedDuels = new ArrayList();
    
     
     @EventHandler
      public void onPlayerInteract(PlayerInteractEntityEvent event){
          if(event.getRightClicked() instanceof Player){
              Player playerClicked = (Player) event.getRightClicked();
	      Player playerClicking = event.getPlayer();
              if(plugin.getDuelHandler().IsInDuel(playerClicked)){
                  plugin.getPrintFormatter().sendPlayerError(playerClicking, playerClicked.getName() + " is already in a duel!");
              }else if(plugin.getDuelHandler().IsInDuel(playerClicking)){
                  plugin.getPrintFormatter().sendPlayerError(playerClicking, "You are already in a duel, get fighting!");
              }else if(playerAlreadyRequested(playerClicking)){
                  plugin.getPrintFormatter().sendPlayerError(
                          playerClicking, "You already sent out a request, please wait...");
              }else if(playerHasPendingRequest(playerClicked)){
                  plugin.getPrintFormatter().sendPlayerNotification(
                          playerClicking, playerClicked.getName() + " already has a pending request, please wait...");
              }else if(playerHasPendingRequest(playerClicking)){
                  if(playerAlreadyRequested(playerClicked)){
                      if(isMatched(playerClicked, playerClicking)){
                          
                          plugin.getDuelHandler().createDuel(playerClicked, playerClicking);
                          recentlyRequestedDuels.remove(playerClicked.getName() + " " + playerClicking.getName());
                          
                      }else{
                          performInitialExchange(playerClicked, playerClicking);
                      }
                  }else{
                      performInitialExchange(playerClicked, playerClicking);
                  }
              }else{
                   performInitialExchange(playerClicked, playerClicking);
              }
          }   
      }
      //if the player already requested someone, to prevent spam
      public boolean playerAlreadyRequested(Player player){
          for(String pairedPlayers : recentlyRequestedDuels){
              String[] playerNames = pairedPlayers.split(" ");
              try{
                  Player playerClicking = Bukkit.getPlayer(playerNames[1]);
                  
                  if(player.getName().equals(playerClicking.getName())){
                      return true;
                  }
                  
              }catch(Exception ex){
                  //Do nothing incase the error was thrown due to a player loggin off after a request.
              }
          }
          return false;
      }
      //if the player already has a request for a duel
      public boolean playerHasPendingRequest(Player player){
          for(String pairedPlayers : recentlyRequestedDuels){
              String[] playerNames = pairedPlayers.split(" ");
              try{
                  Player playerClicked = Bukkit.getPlayer(playerNames[0]);
                  
                  if(player.getName().equals(playerClicked.getName())){
                      return true;
                  }
                  
              }catch(Exception ex){
                  //Do nothing incase the error was thrown due to a player loggin off after a request.
              }
          }
          return false;
      }
      private boolean isMatched(Player clicked, Player clicker){
          return true;
      }
      private void performInitialExchange(Player playerClicked, Player playerClicking){
          plugin.getPrintFormatter().sendPlayerAccomplishmentMessage(
                      playerClicking, "You requested a duel with " + playerClicked.getName());
              plugin.getPrintFormatter().sendPlayerNotification(
                      playerClicked, playerClicking.getName() + " is requesting a duel with you, right click "
                              + playerClicking.getName() + " to accept this request!");
              recentlyRequestedDuels.add(playerClicked.getName() + " " + playerClicking.getName());
      }
}
