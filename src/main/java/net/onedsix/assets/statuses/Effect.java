package net.onedsix.assets.statuses;

import net.onedsix.assets.AttributedAsset;
import net.onedsix.assets.Attributes;
import net.onedsix.registry.Identifier;

public abstract class Effect extends AttributedAsset {
    public int level;
    public boolean isPositive;

    public Effect(Identifier<? extends Effect> ident, Attributes attributes, boolean isPositive) {
        super(ident, attributes);
        this.isPositive = isPositive;
    }

}
