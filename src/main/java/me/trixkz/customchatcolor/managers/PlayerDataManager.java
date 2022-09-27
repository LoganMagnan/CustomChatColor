package me.trixkz.customchatcolor.managers;

import lombok.Getter;
import me.trixkz.customchatcolor.CustomChatColor;
import me.trixkz.customchatcolor.playerdata.PlayerData;
import java.util.*;

public class PlayerDataManager {

	private CustomChatColor main = CustomChatColor.getInstance();

	@Getter private final Map<UUID, PlayerData> players = new HashMap<>();

	public PlayerData getOrCreate(UUID uniqueId) {
		return this.players.computeIfAbsent(uniqueId, PlayerData::new);
	}

	public PlayerData getPlayerData(UUID uniqueId) {
		return this.players.getOrDefault(uniqueId, new PlayerData(uniqueId));
	}

	public Collection<PlayerData> getAllPlayers() {
		return this.players.values();
	}

	public void deletePlayer(UUID uniqueId) {
		this.getPlayers().remove(uniqueId);
	}
}
