package net.onedsix.assets.groups;

import net.onedsix.assets.AttributedAsset;
import net.onedsix.registry.Identifier;
import net.onedsix.assets.Attributes;
import org.jetbrains.annotations.Nullable;

public abstract class Faction extends AttributedAsset {
    protected Faction(Identifier<? extends Faction> ident, Attributes attributes, @Nullable String name) {
        super(ident, attributes);
    }
}
