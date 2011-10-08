import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Logger;
import java.util.ArrayList;


public class SelfDestructListener extends PluginListener{
	static Logger log = Logger.getLogger("Minecraft");
	Server server = etc.getServer();
	Timer countdown;
	ArrayList<Player> blowingup = new ArrayList<Player>();
	ArrayList<String> powermessage;
	
	
	public boolean onCommand(Player player, String[] split){
		int delay = 3;
		if (split[0].equals("/selfdestruct") && player.canUseCommand("/selfdestruct")){
			if (!blowingup.contains(player) ){
				countdown = new Timer();
				blowingup.add(player);
				if (split.length > 1){
					try{
						delay = Integer.parseInt(split[1]);
					}catch (NumberFormatException nfe){
						player.sendMessage("§e"+split[1]+"§c is not a number!");
						player.sendMessage("§cDelay auto-set to §e3 seconds");
						delay = 3;
					}
				}
				server.messageAll("§3" + player.getName() + " §cwill selfdestruct in §e" + delay + " §cseconds");
				countdown.schedule(new TheFinalCountDown(player, delay, delay), 1000);
				return true;
			}else{
				player.sendMessage("§cYou are already activated!");
				return true;
			}
		}else if (split[0].equals("/activate") && player.canUseCommand("/activate")){
			if (split.length > 1){
				Player activated = etc.getServer().getPlayer(split[1]);
				if (activated != null){
					if (!activated.canUseCommand("/noactivate")){
						if (!blowingup.contains(activated)){
							countdown = new Timer();
							blowingup.add(activated);
							if (split.length > 2){
								try{
									delay = Integer.parseInt(split[2]);
								}catch (NumberFormatException nfe){
									player.sendMessage("§e"+split[2]+"§c is not a number!");
									player.sendMessage("§cDealy auto-set to §e3 seconds");
									delay = 3;
								}
							}
							server.messageAll("§3"+activated.getName()+" §cwill selfdestruct in §e"+delay+" §cseconds");
							countdown.schedule(new TheFinalCountDown(activated, delay, delay), 1000);
							return true;
						}else{
							player.sendMessage("§3" + split[1] + "§c is already activated!");
							return true;
						}
					}else{
						player.sendMessage("§3"+split[1]+"§c cannot be activated by others!");
					}
				}else{
					player.sendMessage("§3" + split[1] + "§c is not online...");
					return true;
				}
			}else{
				player.sendMessage("§cUsage is: /activate <Player> [delay]");
				return true;
			}
		}
		return false;
	}
	public void Detonate(Player player){
		blowingup.remove(player);
		if (player.isConnected()){
			Random explode = new Random();
			Integer power = explode.nextInt(4);
			Mob creeper = new Mob("Pig");
			player.getWorld().explode(creeper, player.getX(), player.getY(), player.getZ(), (float)power);
			server.messageAll(DetMessage(power, player));
		}else{
			server.messageAll("§3"+player.getName()+"§b disconnected before they exploded!");
		}
	}
	public String DetMessage(int power, Player player){
		powermessage = new ArrayList<String>();
		Random random = new Random();
		String Detmessage = "";
		String Dir = "plugins/config/SelfDestruct/";
		String FileLoc = "";
		if (power == 0){
			FileLoc = "Dud.txt";
		}else if (power == 1){
			FileLoc = "LowPower.txt";
		}else if (power == 2){
			FileLoc = "MediumPower.txt";
		}else if (power >= 3){
			FileLoc = "HighPower.txt";
		}
		try{
			BufferedReader in = new BufferedReader(new FileReader(Dir + FileLoc));
		    String str;
		    while ((str = in.readLine()) != null) {
		    	powermessage.add(str);
		    }
		    in.close();
		}catch (IOException ioe){
			log.info(ioe.toString());
		}
		int message = random.nextInt(powermessage.size() -1);
		String predet = powermessage.get(message);
		Detmessage = predet.replace("@", "§").replace("%player%", player.getName());
		
		return Detmessage;
	}
	public class TheFinalCountDown extends TimerTask{
		Player player;
		int remaining, delay;
		
		public TheFinalCountDown(Player activated, int remaining, int delay){
			this.player = activated;
			this.remaining = remaining;
			this.delay = delay;
		}
		public void run() {
			remaining--;
			if (remaining > 0){
				if (player.isConnected()){
					server.messageAll("§3"+player.getName()+" §cwill selfdestruct in §e"+remaining+" §cseconds");
					countdown.schedule(new TheFinalCountDown(player, remaining, delay), 1000);
				}else{
					server.messageAll("§3"+player.getName()+"§b disconnected before they exploded!");
					blowingup.remove(player);
				}
			}else{
				Detonate(player);
			}
		}
	}
}
