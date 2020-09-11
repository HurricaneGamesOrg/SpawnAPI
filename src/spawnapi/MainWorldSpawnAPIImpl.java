package spawnapi;

import java.util.function.Consumer;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class MainWorldSpawnAPIImpl implements SpawnAPI {

	private static final MainWorldSpawnAPIImpl instance = new MainWorldSpawnAPIImpl();

	public static final MainWorldSpawnAPIImpl getInstance() {
		return instance;
	}

	@Override
	public Location getLocation() {
		return Bukkit.getWorlds().get(0).getSpawnLocation();
	}

	@Override
	public void setLocation(Location location) {
		Bukkit.getWorlds().get(0).setSpawnLocation(location);
	}

	@Override
	public TeleportAttemptResult teleport(Player player, Consumer<Boolean> result) {
		return forceTeleport(player) ? TeleportAttemptResult.SUCCESS : TeleportAttemptResult.FAIL;
	}

	@Override
	public boolean forceTeleport(Player player) {
		return player.teleport(getLocation());
	}

}
