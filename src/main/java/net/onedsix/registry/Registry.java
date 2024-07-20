package net.onedsix.registry;

import java.util.LinkedList;

/**
 * A global registry of all assets, used for lookups and keeping track of all the loaded assets.
 * */
public final class Registry {

    private static final LinkedList<Identifier<?>> ASSET_REGISTRY = new LinkedList<>();
    public static void registerAsset(Identifier<?> ident) {
		ASSET_REGISTRY.add(ident);
	}
    public static LinkedList<Identifier<?>> getAssetRegistry() {
		return ASSET_REGISTRY;
	}
}
