import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class SelfDestruct extends Plugin {
	SelfDestructListener listener = new SelfDestructListener();
	static Logger log = Logger.getLogger("Minecraft");
	String name = "SelfDestruct";
	String version = "1.3";
	String author = "Darkdiplomat";

	public void disable() {
		log.info(name + " version " + version + " disabled");

	}

	public void enable() {
		createMessageFiles();
		log.info(name + " version " + version + " by " + author + " enabled");
	}
	
	public void initialize(){
		etc.getLoader().addListener(PluginLoader.Hook.COMMAND, listener, this, PluginListener.Priority.MEDIUM);
		
	}
	
	public void createMessageFiles(){
		File Dir = new File("plugins/config/SelfDestruct");
		//create SelfDestruct directory if it does not exits
		if(!Dir.exists()){
			Dir.mkdir();
		}
		//create SelfDestruct Message Files if they don't exist
		File DudFile = new File(Dir + File.separator + "Dud.txt");
		File LowPowerFile = new File(Dir + File.separator + "LowPower.txt");
		File MediumPowerFile = new File(Dir + File.separator + "MediumPower.txt");
		File HighPowerFile = new File(Dir + File.separator + "HighPower.txt");
		//DudFile does not exist, create it
		if(!DudFile.exists()){
			try{
				DudFile.createNewFile();
				BufferedWriter writer = new BufferedWriter(new FileWriter(DudFile));
				writer.write("@3%player% @6was a dud!"); writer.newLine();
				writer.write("@3%player% @6forgot to set the charge!"); writer.newLine();
				writer.write("@3%player% @6deactivated the charge just in time!"); writer.newLine();
				writer.write("@3%player% @6didn't know how to wire themself!"); writer.newLine();
				writer.close();
			} catch (IOException e) {
				SelfDestruct.log.log(Level.SEVERE, "[SelfDestruct] Failed to create \"Dud.txt\"");
			}
		}
		//LowPowerFile does not exist, create it
		if(!LowPowerFile.exists()){
			try{
				LowPowerFile.createNewFile();
				BufferedWriter writer = new BufferedWriter(new FileWriter(LowPowerFile));
				writer.write("@3%player% @dwasn't fully charged."); writer.newLine();
				writer.write("@3%player% @6forgot to connect all the charges!"); writer.newLine();
				writer.write("@3%player% @6had a lot of fake @cTNT@6 in the mix!"); writer.newLine();
				writer.write("@3%player% @6thought @8GunPowder@6 would be enough!"); writer.newLine();
				writer.write("@3%player% @6forgot to add @eSand@6 to the mix!"); writer.newLine();
				
				writer.write(""); writer.newLine();
				writer.close();
			} catch (IOException e) {
				SelfDestruct.log.log(Level.SEVERE, "[SelfDestruct] Failed to create \"LowPower.txt\"");
			}
		}
		//MediumFile does not exist, create it
		if(!MediumPowerFile.exists()){
			try{
				MediumPowerFile.createNewFile();
				BufferedWriter writer = new BufferedWriter(new FileWriter(MediumPowerFile));
				writer.write("@3%player% @6has selfdestructed!"); writer.newLine();
				writer.write("@3%player% @6thought @cTNT@6 was safe!"); writer.newLine();
				writer.write("@3%player% @6tried being a @7Ghast Fireball@6!"); writer.newLine();
				writer.write("@3%player% @6dropped a stack of @cTNT@6!"); writer.newLine();
				writer.write("@3%player% @6mixed @eSand@6 with their @8GunPowder@6!"); writer.newLine();
				writer.close();
			} catch (IOException e) {
				SelfDestruct.log.log(Level.SEVERE, "[SelfDestruct] Failed to create \"MediumPower.txt\"");
			}
		}
		//HighFile does not exist, create it
		if(!HighPowerFile.exists()){
			try{
				HighPowerFile.createNewFile();
				BufferedWriter writer = new BufferedWriter(new FileWriter(HighPowerFile));
				writer.write("@3%player% @4has become one with the @aCreepers@4!"); writer.newLine();
				writer.write("@3%player% @4tried taking everything out!"); writer.newLine();
				writer.write("@3%player% @4used a little too much!"); writer.newLine();
				writer.write("@3%player% @4has been blown sky high!"); writer.newLine();
				writer.close();
			} catch (IOException e) {
				SelfDestruct.log.log(Level.SEVERE, "[SelfDestruct] Failed to create \"HighPower.txt\"");
			}
		}
	}
}