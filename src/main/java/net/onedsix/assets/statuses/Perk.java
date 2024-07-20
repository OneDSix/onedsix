package net.onedsix.assets.statuses;

import net.onedsix.assets.AttributedAsset;
import net.onedsix.assets.Attributes;
import net.onedsix.registry.Identifier;

public abstract class Perk extends AttributedAsset {
    protected Perk(Identifier<? extends Perk> ident, Attributes attributes) {
        super(ident, attributes);
    }
}
