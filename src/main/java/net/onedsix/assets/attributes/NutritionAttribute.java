package net.onedsix.assets.attributes;

import net.onedsix.assets.Attribute;
import net.onedsix.registry.Identifier;
import net.onedsix.util.Vars;
import net.onedsix.assets.item.consumeables.Nutrition;

import java.util.LinkedList;

public class NutritionAttribute<T extends Nutrition.AbstractNutrition> extends Attribute<T> {
	public NutritionAttribute(T nut) {
		super(
			new Identifier<>(NutritionAttribute.class, Vars.ONEDSIX_ID),
			new LinkedList<>(),
			nut
		);
	}
}
