package net.onedsix.assets;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.function.Consumer;

/**
 * Stores many {@link Attribute}
 * */
@SuppressWarnings("rawtypes")
public class Attributes {

    /*
    @Getter @Setter private String name;
    @Getter @Setter private int karma;
    @Getter @Setter private int cash;
    @Getter @Setter private HashMap<Faction, Integer> factionStanding;
    @Getter @Setter private boolean factionLocked;
    @Getter @Setter private int staminaCurrent;
    @Getter @Setter private int staminaMaximum;
    @Getter @Setter private double temperature;
    @Getter @Setter private float sicknessChance;
    @Getter @Setter private int absorbedRadiation;
    @Getter @Setter private int outputRadiation;
    */
	/**
	 * A {@link LinkedList} of {@link Attribute}s held and modified by this class.
	 * */
    public final LinkedList<Attribute> attributes;

    /**
     * Creates a {@link Attributes} instance with the specified {@link Attribute} list.
     * */
    public Attributes(LinkedList<Attribute> attributes) {
        this.attributes = attributes;
    }

    /**
     * Creates a {@link Attributes} instance with the specified {@link Attribute} list.
     * */
    public Attributes(Attribute[] attr) {
        this.attributes = new LinkedList<>(Arrays.asList(attr));
    }

    /**
     * Creates a {@link Attributes} instance with an empty {@link Attribute} list. Used mostly for debugging.
     * */
    public Attributes() {
        this.attributes = new LinkedList<>();
    }

    /** Loops over all the attributes on this instance */
    public void forEach(Consumer<Attribute> processor) {
        for (Attribute item : this.attributes) {
            processor.accept(item);
        }
    }

    /**
     * Takes in a list of {@link Attributes} and merges all the values into this {@link Attributes} instance.<br>
	 * This does not collapse the attributes, only adds all the values into current instance with all of the combined attributes.
	 * There may be many copies of a single attribute in the instance that returned.
     * */
    public void merge(Attributes... attrs) {
        for (Attributes attributes : attrs) {
			for (Attribute attribute : attributes.attributes) {
				this.add(attribute);
			}
        }
    }

	/**
	 * Adds the specified attributes to the current instance.
	 * */
	public void add(Attribute... attrs) {
		this.attributes.addAll(Arrays.asList(attrs));
	}
}
