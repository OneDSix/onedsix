package net.onedsix.assets.item.equipables;

import net.onedsix.assets.Attributes;
import net.onedsix.assets.item.Item;
import net.onedsix.assets.item.Recipe;
import net.onedsix.registry.Identifier;

public abstract class WeaponItem extends Item {
	public WeaponItem(Identifier<? extends Item> ident, Attributes attributes, Recipe recipe, long roughCost) {
		super(ident, attributes, recipe, roughCost);
	}
}
