package net.onedsix.server;

public class ServerConfigBuilder {

	public ServerType serverType;

	/**  */
	public ServerConfigBuilder() {}

	public ServerConfigBuilder serverType(ServerType type) {
		this.serverType = type;
		return this;
	}

	public enum ServerType {
		/** Creates a dedicated server. */
		DEDICATED,
		/** Creates a internal server on the current JVM instance. Useful if RAM or CPU usage is an issue. */
		INTERNAL_SINGLE_CORE,
		/** Creates a internal server on a separate JVM instance. Used in most cases, the default. */
		INTERNAL_MULTI_CORE
	}
}
