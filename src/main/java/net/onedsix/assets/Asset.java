package net.onedsix.assets;

import net.onedsix.registry.Identifier;

/**
 * The basis of everything in 1D6, the {@link Asset}.
 * This is built to be extended off of, and any breaking changes will be noted at the top of the changelog.
 * Use {@link AttributedAsset} for most cases.<br>
 * It is perfectly fine to set anything in the constructors of this object to {@code null}, as there is no code that does anything to them inside this class.
 * In fact, this class does nothing on it's own. However, most inheritors tend to force a not-null value.
 * */
public abstract class Asset {

    public final Identifier<? extends Asset> ident;

    protected Asset(Identifier<? extends Asset> ident) {
        this.ident = ident;
    }
}
