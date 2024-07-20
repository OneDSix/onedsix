package net.onedsix.assets.attributes;

import net.onedsix.assets.Attribute;
import net.onedsix.registry.Identifier;
import net.onedsix.util.Vars;

import java.util.LinkedList;

public class ArmorAttribute extends Attribute<Long> {
	public ArmorAttribute(long armor) {
		super(
			new Identifier<>(ArmorAttribute.class, Vars.ONEDSIX_ID),
			new LinkedList<>(),
			armor
		);
	}
}
