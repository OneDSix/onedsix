package net.onedsix.assets;

import net.onedsix.registry.Identifier;
import org.jetbrains.annotations.NotNull;

/**
 * An extension of {@link Asset} made for of most of 1D6's assets. Any breaking changes to this or {@link Asset} will be noted at the top of the changelog.<br>
 * Only adds 1 thing; the ability to hold and modify {@link Attributes}.
 * */
public abstract class AttributedAsset extends Asset {

	/**
	 * The default attributes of this asset.<br>
	 * Add to this when creating a new asset.
	 * */
	public static Attributes defaultAttributes = new Attributes();
	/**
	 * This instance's {@link Attributes} instance.
	 * */
	@NotNull public final Attributes attributes;

	protected AttributedAsset(Identifier<? extends Asset> ident, @NotNull Attributes attributes) {
		super(ident);
		this.attributes = attributes;
	}

	protected AttributedAsset(Identifier<? extends Asset> ident) {
		super(ident);
		this.attributes = defaultAttributes;
	}
}
