package net.onedsix.assets.item.consumeables;

import net.onedsix.assets.Attributes;
import net.onedsix.assets.attributes.NutritionAttribute;
import net.onedsix.assets.item.Item;
import net.onedsix.assets.item.Recipe;
import net.onedsix.assets.statuses.Effect;
import net.onedsix.registry.Identifier;

public abstract class ConsumableItem extends Item {
	//
	public Nutrition.AbstractNutrition nutrition;
	public Effect[] effect;

	public ConsumableItem(Identifier<? extends Item> ident, Attributes attributes, Recipe recipe, long roughCost, Nutrition.AbstractNutrition nutrition) {
		super(ident, attributes, recipe, roughCost);
		this.attributes.attributes.add(new NutritionAttribute<>(nutrition));
	}
}
