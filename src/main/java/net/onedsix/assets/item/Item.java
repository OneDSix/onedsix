package net.onedsix.assets.item;

import com.badlogic.gdx.graphics.g3d.Model;
import net.onedsix.assets.AttributedAsset;
import net.onedsix.assets.attributes.LevelAttribute;
import net.onedsix.registry.Identifier;
import net.onedsix.assets.Attributes;
import net.onedsix.client.ClientPlayer;

public abstract class Item extends AttributedAsset {
	static {
		defaultAttributes.add(
			new LevelAttribute(0)
		);
	}
    public String[] flavorText;
    public Model model;
    public int level;
    public Recipe recipe;
    public long roughCost;

    public Item(Identifier<? extends Item> ident, Attributes attributes, Recipe recipe, long roughCost) {
        super(ident, attributes);
        this.recipe = recipe;
        this.roughCost = roughCost;
    }

    public Item(Identifier<? extends Item> ident, Attributes attributes, String[] flavorText, Model model, int level, Recipe recipe, long roughCost) {
        super(ident, attributes);
        this.flavorText = flavorText;
        this.model = model;
        this.level = level;
        this.recipe = recipe;
        this.roughCost = roughCost;
    }

    public long roughCost() {
        long amount = 0;

        amount += recipe.levelNeeded * 0.12;
        amount += recipe.perksNeeded.size() * 0.16;
        amount += recipe.factionsNeeded.size() * 0.18;
        for (Item i : recipe.itemsNeeded.keySet()) {
            amount += i.roughCost * 1.05;
        }

        return amount;
    }
    /** Called whenever the item is on the ground in a cell and the player interacts with it. */
    public abstract void onInteract(ClientPlayer player);
    /** Called whenever the item is on the ground in a cell and the player interacts with it during battle. */
    public abstract void onInteractBattle(ClientPlayer player);
    /** Called whenever the item is used when inside the player's inventory. */
    public abstract void onUse(ClientPlayer player);
    /** Called whenever the item is used when inside the player's inventory during battle. */
    public abstract void onUseBattle(ClientPlayer player);
}
