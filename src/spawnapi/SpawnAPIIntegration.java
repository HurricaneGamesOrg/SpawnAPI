package spawnapi;

import java.util.logging.Level;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServiceRegisterEvent;
import org.bukkit.event.server.ServiceUnregisterEvent;
import org.bukkit.plugin.Plugin;

public class SpawnAPIIntegration {

	private static final SpawnAPIIntegration instance = new SpawnAPIIntegration();

	protected static SpawnAPIIntegration getInstance() {
		return instance;
	}

	public static SpawnAPI get() {
		return instance.getAPI();
	}

	private SpawnAPIIntegration() {
	}

	private SpawnAPI spawnapi = MainWorldSpawnAPIImpl.getInstance();

	public SpawnAPI getAPI() {
		return spawnapi;
	}

	protected static class Hook implements Listener {

		protected static void hook(Plugin plugin) {
			Hook hookinst = new Hook(plugin);
			plugin.getServer().getPluginManager().registerEvents(hookinst, plugin);
			hookinst.hook0();
		}

		protected final Plugin plugin;

		protected Hook(Plugin plugin) {
			this.plugin = plugin;
		}

		private void hook0() {
			try {
				SpawnAPI servicesSpawnAPI = plugin.getServer().getServicesManager().load(SpawnAPI.class);
				if (servicesSpawnAPI != instance.spawnapi) {
					instance.spawnapi = servicesSpawnAPI;
					plugin.getLogger().log(Level.INFO, "SpawnAPI implementation set to " + servicesSpawnAPI.getClass().getName());
				}
			} catch (Throwable e) {
				instance.spawnapi = MainWorldSpawnAPIImpl.getInstance();
				plugin.getLogger().log(Level.WARNING, "Error hooking SpawnAPI implementation, using main world spawnapi implementation", e);
			}
		}

		@EventHandler
		private void onServiceRegister(ServiceRegisterEvent event) {
			hook0();
		}

		@EventHandler
		private void onServiceUnregister(ServiceUnregisterEvent event) {
			hook0();
		}

	}

}
