
package Duel;

import Main.Main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

public class Duel{
    
    Player player1;
    Player player2;
    Location duelCenter;
    Main plugin;
    
    public Duel(Player player1, Player player2, Main plugin){
        this.player1 = player1;
        this.player2 = player2;
        duelCenter = player1.getLocation();
        this.plugin = plugin;
        plugin.getPrintFormatter().sendPlayerAccomplishmentMessage(player1, "You are now dueling " + player1.getName());
        plugin.getPrintFormatter().sendPlayerAccomplishmentMessage(player2, "You are now dueling " + player1.getName());
    }
    public String[] getParticipents(){
        String[] playersNames = {player1.getName(), player2.getName()};
        return playersNames;
    }
    public boolean containsLocation(Location location){
    	  int duelArenaSize = 10;
    	  double maxX = duelCenter.getX()+(duelArenaSize);
    	  double maxZ = duelCenter.getZ()+(duelArenaSize);
    	  double minX = duelCenter.getX()-(duelArenaSize);
    	  double minZ = duelCenter.getZ()-(duelArenaSize);
    	  
    	  if(location.getX() <= maxX && location.getX() >= minX){
    		  if(location.getZ() <= maxZ && location.getZ() >= minZ){
    			  return true;
    		  }
    	  }
    	    return false;
      }
         
}
