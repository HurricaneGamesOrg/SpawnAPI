package spawnapi;

import java.util.function.Consumer;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public interface SpawnAPI {

	/**
	 * Get spawn location
	 * @return spawn location
	 */
	public Location getLocation();

	/**
	 * Set spawn location
	 * @param location spawn location
	 */
	public void setLocation(Location location);

	/**
	 * Teleport player to spawn location<br>
	 * This teleport may not happen right now, may require user input, etc, etc<br>
	 * Implementation should return teleport attempt result value, and if it was delayed, call the result consumer after teleport finish
	 * If the teleport didn't start at all, the result boolean won't be called at all
	 * @param player player to teleport
	 * @param result teleport success
	 * @return {@link TeleportAttemptResult} teleport attempt result
	 */
	public TeleportAttemptResult teleport(Player player, Consumer<Boolean> result);

	public enum TeleportAttemptResult {
		SUCCESS, DELAYED, FAIL
	}

	/**
	 * Forcefully teleport player to spawn location<br>
	 * Implementation should ignore any built-in delays, permission checks, or any user settings<br>
	 * @param player player to teleport
	 * @return boolean teleport success
	 */
	public boolean forceTeleport(Player player);

}
