package net.onedsix.desktop;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import lombok.extern.slf4j.Slf4j;
import net.onedsix.client.OnedsixClient;

@Slf4j
public class DesktopEntrypoint {
	public static void main(String[] args) {
		log.info("Starting Lwjgl3...");
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();

		//config.setWindowIcon("./icon.png");
		config.setWindowedMode(1920, 1440);
		config.setMaximized(true);
		config.useVsync(true);
		config.setForegroundFPS(60);

		new Lwjgl3Application(new OnedsixClient(), config);
		log.info("Stopped Lwjgl3!");
	}
}
