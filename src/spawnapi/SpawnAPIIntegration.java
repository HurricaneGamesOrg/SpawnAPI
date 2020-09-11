package spawnapi;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.event.server.PluginEnableEvent;
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

		protected final Plugin plugin;

		protected Hook(Plugin plugin) {
			this.plugin = plugin;
		}

		protected void hook() {
			plugin.getServer().getPluginManager().registerEvents(this, plugin);
			hook0();
		}

		private void hook0() {
			try {
				SpawnAPI servicesSpawnAPI = plugin.getServer().getServicesManager().load(SpawnAPI.class);
				if (servicesSpawnAPI != null) {
					instance.spawnapi = servicesSpawnAPI;
				}
			} catch (Throwable e) {
				instance.spawnapi = MainWorldSpawnAPIImpl.getInstance();
			}
		}

		@EventHandler
		private void onPluginEnable(PluginEnableEvent event) {
			hook0();
		}

		@EventHandler
		private void onPluginDisable(PluginDisableEvent event) {
			hook0();
		}

	}

}
