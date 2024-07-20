package net.onedsix.server;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ServerEntrypoint {
	public static void main(String[] args) {
		log.info("Starting server...");
		new DedicatedServer();
		log.info("Stopped server!");
	}
}
