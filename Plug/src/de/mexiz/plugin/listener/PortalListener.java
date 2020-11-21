package de.mexiz.plugin.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.PortalCreateEvent;

import de.mexiz.plugin.main.Main;


public class PortalListener implements Listener{
	
	public PortalListener(Main main) {this.main = main;}
	Main main;
	
//	@EventHandler
//	public void onPortalJoin(PlayerPortalEvent event) {
//		
//		Player player = event.getPlayer();
//		Bukkit.getServer().broadcastMessage("Listener Portal");
//        if (event.getCause() == PlayerTeleportEvent.TeleportCause.NETHER_PORTAL) {
//        	try {
//                Class.forName("org.bukkit.TravelAgent");
//              } catch (ClassNotFoundException ignore) {
//            	  Bukkit.getServer().broadcastMessage("TravelAgent not available for PlayerPortalEvent for " + event.getPlayer().getName() + ". There may be a portal created at spawn.");
//              } 
//        	Bukkit.getServer().broadcastMessage("Listener NETHER Portal");
//            Location location;
//            if (player.getWorld() == main.getworld()) {
//            	Bukkit.getServer().broadcastMessage("world2");
//                 location = new Location(main.getnether(), event.getFrom().getBlockX() / 8, event.getFrom().getBlockY(), event.getFrom().getBlockZ() / 8);
//            } else {
//            	Bukkit.getServer().broadcastMessage("main world");
//                location = new Location(main.getworld(), event.getFrom().getBlockX() * 8, event.getFrom().getBlockY(), event.getFrom().getBlockZ() * 8);
//            }
//            Bukkit.getServer().broadcastMessage("world2 event location");
//            event.setTo(location);
//        }
//		
//    }
	  @EventHandler
	  public void portalForm(PortalCreateEvent event) {
	    if (event.getWorld() == main.getworld2()) {
	      event.setCancelled(true);
	    } 
	  }
}
		

		
		
		
		
	
	


