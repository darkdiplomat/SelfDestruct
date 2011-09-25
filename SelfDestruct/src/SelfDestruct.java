import java.util.logging.Logger;


public class SelfDestruct extends Plugin {
	SelfDestructListener listener = new SelfDestructListener();
	static Logger log = Logger.getLogger("Minecraft");
	String name = "SelfDestruct";
	String version = "1.2";
	String author = "Darkdiplomat";

	public void disable() {
		log.info(name + " version " + version + " disabled");

	}

	public void enable() {
		log.info(name + " version " + version + " by " + author + " enabled");
	}
	
	public void initialize(){
		etc.getLoader().addListener(PluginLoader.Hook.COMMAND, listener, this, PluginListener.Priority.MEDIUM);
		
	}

}
