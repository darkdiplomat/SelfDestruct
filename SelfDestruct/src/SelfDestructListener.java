import java.util.Random;
import java.util.logging.Logger;


public class SelfDestructListener extends PluginListener{
	static Logger log = Logger.getLogger("Minecraft");
	Server server = etc.getServer();
	public boolean onCommand(Player player, String[] split){
		if (split[0].equals("/selfdestruct") && player.canUseCommand("/selfdestruct")){
			boolean inprogress = false;
			if (inprogress == false){
				inprogress = true;
				int i = -1;
				while (i < 2){
					i++;
					server.messageAll("§3" + player.getName() + " §cwill selfdestruct in §e" + (3-i) + " §cseconds");
					try{
						Thread.currentThread();
						Thread.sleep(1000);
					}catch(InterruptedException ie){
						log.info("[SelfDestruct] Something broke the timer...");
						server.messageAll("§c?");
						return true;
					}
				}
				Random explode = new Random();
				Integer boom = explode.nextInt(3);
				String booomed = boom.toString();
				float exploded = Float.parseFloat(booomed);
				player.getWorld().explode(player, player.getX(), player.getY(), player.getZ(), exploded);
				player.sendMessage(booomed);
				int rm = explode.nextInt(3);
				if (boom == 0){
					if (rm == 1){
						server.messageAll("§3" + player.getName() + " §6was a dud.");
						inprogress = false;
						return true;
					}else if (rm == 2){
						server.messageAll("§3" + player.getName() + " §6forgot to set the charge.");
						inprogress = false;
						return true;
					}else{
						server.messageAll("§3" + player.getName() + " §6deavtivated the charge just in time.");
						inprogress = false;
						return true;
					}
				}else if (boom == 1){
					server.messageAll("§3" + player.getName() + " §dwasn't fully charged.");
					inprogress = false;
					return true;
				}else{
					if (rm == 2){
						server.messageAll("§3" + player.getName() + " §4has selfdestructed!");
						inprogress = false;
						return true;
					}else if (rm == 1){
						server.messageAll("§3" + player.getName() + " §4has become one with the §aCreepers§4!");
						inprogress = false;
						return true;
					}else{
						server.messageAll("§3" + player.getName() + " §4has been blown sky high!");
						inprogress = false;
						return true;
					}
				}
			}else{
				return false;
			}
		}else if (player.isAdmin() && split[0].equals("/activate") && (split.length > 1)){
			Player detonated = etc.getServer().getPlayer(split[1]);
			if (detonated != null){
				int i = -1;
				server.messageAll("§3" + player.getName() + "§c activated §3" + detonated.getName());
				while (i < 2){
					i++;
					server.messageAll("§3" + detonated.getName() + " §cwill selfdestruct in §e" + (3-i) + " §cseconds");
					try{
						Thread.currentThread();
						Thread.sleep(1000);
					}catch(InterruptedException ie){
						log.info("[SelfDestruct] Something broke the timer...");
						server.messageAll("§c?");
						return true;
					}
				}
				Random explode = new Random();
				Integer boom = explode.nextInt(3);
				String booomed = boom.toString();
				float exploded = Float.parseFloat(booomed);
				detonated.getWorld().explode(detonated, player.getX(), player.getY(), player.getZ(), exploded);
				detonated.sendMessage(booomed);
				int rm = explode.nextInt(3);
				if (boom == 0){
					if (rm == 1){
						server.messageAll("§3" + detonated.getName() + " §6was a dud.");
						return true;
					}else if (rm == 2){
						server.messageAll("§3" + detonated.getName() + " §6forgot to set the charge.");
						return true;
					}else{
						server.messageAll("§3" + detonated.getName() + " §6deavtivated the charge just in time.");
						return true;
					}
				}else if (boom == 1){
					server.messageAll("§3" + detonated.getName() + " §dwasn't fully charged.");
					return true;
				}else{
					if (rm == 2){
						server.messageAll("§3" + detonated.getName() + " §4has selfdestructed!");
						return true;
					}else if (rm == 1){
						server.messageAll("§3" + detonated.getName() + " §4has become one with the §aCreepers§4!");
						return true;
					}else{
						server.messageAll("§3" + detonated.getName() + " §4has been blown sky high!");
						return true;
					}
				}
			}else{
				player.sendMessage("§3" + split[1] + "§c is not online...");
				return true;
			}
		}
		return false;
	}

}
