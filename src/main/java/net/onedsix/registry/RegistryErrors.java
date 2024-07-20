package net.onedsix.registry;

public class RegistryErrors {
	/**
	 * Thrown if an invalid registry entry is grabbed or modified.<br>
	 * Most common causes are an empty/null {@link Registry} entry at game load, or a failed/incorrect override/mixin at boot.<br>
	 * Not meant to be used outside the game's internals or heavy modifications, but still a public and documented API in case its needed.
	 * */
	public static class BrokenRegistryError extends Error {
		public BrokenRegistryError(String msg) {super(msg);}
		public BrokenRegistryError(String msg, Throwable e) {super(msg, e);}
		public BrokenRegistryError(Throwable e) {super(e);}
	}
}
