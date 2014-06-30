/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Main;

import Duel.DuelHandler;
import static org.bukkit.Bukkit.getServer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author User
 */
public class Main extends JavaPlugin implements Listener{
    
    ClickHandler clickHandler;
    PrintFormatter printFormatter;
    DuelHandler duelHandler;

@Override
 public void onEnable(){
		               
PluginManager pm = getServer().getPluginManager();
pm.registerEvents(this, this);
                 /* TownCommands townCommands= new TownCommands(this);
                  pm.registerEvents(townCommands, this);
                  getCommand("twn").setExecutor(townCommands);
                  getCommand("towns").setExecutor(townCommands);*/ 


printFormatter = new PrintFormatter(this);
duelHandler = new DuelHandler(this);
pm.registerEvents(duelHandler, this);

clickHandler = new ClickHandler(this);
pm.registerEvents(clickHandler, this);
}
 
 

    @Override
public void onDisable(){
				 
}
  @Override
        public boolean onCommand(final CommandSender sender, Command command, final String speak, final String[] args) {
               
                if(speak.equalsIgnoreCase("duel") || (speak.equalsIgnoreCase("dl"))){
                
                        
                switch (args.length){
                
                case 0:
                    System.out.println("Test");
                    break;
                }
         }
                return true;
        }
    public PrintFormatter getPrintFormatter(){
        return printFormatter;
    }
    public DuelHandler getDuelHandler(){
        return duelHandler;
    }
    public ClickHandler getClickHandler(){
        return clickHandler;
    }
}