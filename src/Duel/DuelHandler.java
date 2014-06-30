package Duel;

import Main.Main;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.util.Vector;

public class DuelHandler implements Listener{
    
    DuelSettings settings;
    Main plugin;
    
    private List<Duel> duels = new ArrayList();
    
    public DuelHandler(Main main){
        settings = new DuelSettings();
        this.plugin = main;
    }
    
    
    public void createDuel(Player player1, Player player2){
        new Countdown(player1, player2, plugin).runTaskTimer(Bukkit.getPluginManager().getPlugin("Duel"),0L,20L);
    }
    public DuelSettings getSettings(){
        return settings;
    }
    public List<Duel> getDuels(){
        return duels;
    }
    public boolean IsInDuel(Player player){
        for(Duel duel: duels){
            if(duel.getParticipents()[0].equals(player.getName())
                    || duel.getParticipents()[1].equals(player.getName())
                    ){
                return true;
            }
        }
        return false;
    }
    public Duel getDuel(Player player){
        for(Duel duel: duels){
            if(duel.getParticipents()[0].equals(player.getName())
                    || duel.getParticipents()[1].equals(player.getName())
                    ){
                return duel;
            }
        }
        return null;
    }
    
    @EventHandler
      public void onPlayerDeath(PlayerDeathEvent event){
          if(event.getEntity() instanceof Player){
              Player deadPlayer = (Player)event.getEntity();
              ArrayList toRemove = new ArrayList();
              if(IsInDuel(deadPlayer)){
                  Duel duel = getDuel(deadPlayer);
                  toRemove.add(duel);
                  if(duel.getParticipents()[0].equals(deadPlayer.getName())){
                      plugin.getPrintFormatter().sendPlayerAccomplishmentMessage(
                              Bukkit.getPlayer(duel.getParticipents()[1]), "You won the duel!");
                      plugin.getPrintFormatter().sendPlayerError(
                              deadPlayer, "You lost the duel!");
                      event.setDeathMessage(
                              ChatColor.GOLD+"[Duel] " + ChatColor.YELLOW + duel.getParticipents()[1]
                                      + " defeated " + deadPlayer.getName() + " in a duel!");
                  }else{
                      plugin.getPrintFormatter().sendPlayerAccomplishmentMessage(
                              Bukkit.getPlayer(duel.getParticipents()[0]), "You won the duel!");
                      plugin.getPrintFormatter().sendPlayerError(
                              deadPlayer, "You lost the duel!");
                      event.setDeathMessage(
                              ChatColor.GOLD+"[Duel] " + ChatColor.YELLOW + duel.getParticipents()[0]
                                      + " defeated " + deadPlayer.getName() + " in a duel!");
                  }
              }
              for(Object object: toRemove){
                  duels.remove((Duel)object);
              }
          }
      }
      @EventHandler
      public void onPlayerMove(PlayerMoveEvent event){
          if(IsInDuel(event.getPlayer())){
              Duel playersDuel = getDuel(event.getPlayer());
              if(!playersDuel.containsLocation(event.getPlayer().getLocation())){
                  plugin.getPrintFormatter().sendPlayerError(event.getPlayer(), "You have reached the duel's boundries!");
                  launchBack(event.getPlayer(), 10, event.getPlayer().getLocation(), event);
                  event.setCancelled(true);
              }
          }
      }
      @EventHandler(priority = EventPriority.HIGHEST)
      public void onCommand(PlayerCommandPreprocessEvent event){
           if(IsInDuel(event.getPlayer())){
               event.setCancelled(true);
               plugin.getPrintFormatter().sendPlayerError(event.getPlayer(), "Commands are disabled during duels!");
           }
      }
      
      @EventHandler
      public void onPlayerQuit(PlayerQuitEvent event){
          Player bitchLogging = event.getPlayer();
          ArrayList toRemove = new ArrayList();
          if(IsInDuel(bitchLogging)){
              Duel duel = getDuel(event.getPlayer());
              toRemove.add(duel);
              if(duel.getParticipents()[0].equals(event.getPlayer().getName())){
                  plugin.getPrintFormatter().sendPlayerAccomplishmentMessage(
                          Bukkit.getPlayer(duel.getParticipents()[1]), event.getPlayer().getName() + " battle quit! You win!");
              }else{
                  plugin.getPrintFormatter().sendPlayerAccomplishmentMessage(
                          Bukkit.getPlayer(duel.getParticipents()[0]), event.getPlayer().getName() + " battle quit! You win!");
              }
          }
          for(Object object: toRemove){
              duels.remove((Duel)object);
          }
      }  
      public void launchBack(Player player, int speed, Location hitLocation, PlayerMoveEvent event){
          Location to = event.getTo();
        Location from = event.getFrom();
        
         player.teleport(from);//
      }
}
