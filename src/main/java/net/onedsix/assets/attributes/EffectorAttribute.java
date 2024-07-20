package net.onedsix.assets.attributes;

import net.onedsix.assets.Attribute;
import net.onedsix.registry.Identifier;
import net.onedsix.util.Vars;
import net.onedsix.assets.statuses.Effect;

import java.util.LinkedList;

public class EffectorAttribute extends Attribute<Effect[]> {
	public EffectorAttribute(Effect[] effects) {
		super(
			new Identifier<>(EffectorAttribute.class, Vars.ONEDSIX_ID),
			new LinkedList<>(),
			effects
		);
	}
}
