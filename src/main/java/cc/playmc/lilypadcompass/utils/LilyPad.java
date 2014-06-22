package cc.playmc.lilypadcompass.utils;

import lilypad.client.connect.api.Connect;
import lilypad.client.connect.api.request.RequestException;
import lilypad.client.connect.api.request.impl.RedirectRequest;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class LilyPad {

	private Connect connect;

	public void registerConnect() {
		connect = ((Connect) Bukkit.getServicesManager()
				.getRegistration(Connect.class).getProvider());
	}

	public Connect getConnect() {
		return connect;
	}

	public void redirect(String server, Player p) {
		try {
			connect.request(new RedirectRequest(server, p.getName()));
		} catch (RequestException e) {
			// IGNORE
		}
	}
}