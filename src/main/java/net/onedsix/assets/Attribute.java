package net.onedsix.assets;

import net.onedsix.registry.Identifier;

import java.util.LinkedList;

/**
 * The core data structure of 1D6, allowing for everything from a single {@link Float}<br>
 * to entire {@link com.google.gson.JsonObject} instances to be attached to any {@link AttributedAsset}.
 * */
public abstract class Attribute<T> extends Asset {
    public T value;
    /**Limits what {@link Asset} abstractions can use it. The attribute will remain on the asset for compatibility reasons, but it will not be run.<br>
     * If this is {@code null} or empty, it is treated as if it's applicable to all.<br>
     * Other mods may add onto it at any time.*/
    public LinkedList<Class<? extends Asset>> applicable;

	public Attribute(Identifier<? extends Asset> ident, LinkedList<Class<? extends Asset>> applicable, T value) {
		super(ident);
		this.applicable = applicable;
		this.value = value;
	}

    public boolean isApplicable(Class<? extends Asset> clazz) {
        return applicable == null || applicable.isEmpty() || applicable.contains(clazz);
    }

	public T getValue() {
		return value;
	}
}
