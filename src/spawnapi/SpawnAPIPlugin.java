package spawnapi;

import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;

public class SpawnAPIPlugin extends JavaPlugin {

	@Override
	public void onEnable() {
		getServer().getServicesManager().register(SpawnAPI.class, MainWorldSpawnAPIImpl.getInstance(), this, ServicePriority.Lowest);
		new SpawnAPIIntegration.Hook(this){}.hook();
	}

}
