package net.onedsix.client;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

public class Title {

    public static final String title = "1D6";
    public static LinkedList<String> splashes = new LinkedList<>();

    static {
        Collections.addAll(
            splashes,
			// References to how the name of the game was originally supposed to be.
            "Community-Developed Data-Derived Dynamically Distributed Distractor",
            "Data-Derived Distributed Adventure Quests",
            "Dynamic Data-Derived Distributed Distractor",
            "Open-Source Distributed Questing Software",
            "Dumb Game Be Distributed, Open-Source, and Dynamic",
			"Whats this again?",

			// programming
			"Your Lifetime = <'static>",
			"pcm_psvmsa.java",
			"pcm_pmsa.kt",
			"Then the Mixin nation attacked!",
			"ModdingWrapperConstructorFactoryFactory.java",
			"return 0;",
			"53 classes and counting!", // Update this on each release!

			// Movies
			"Hans Grubber has fallen!",
			"REDRUM",
			"tldr; dog was gift from wife, dog assassinated, guy goes on rampage, gets kicked out of the assassin nation, 200 million in the box office.",
			"Fun fact, there is no limit to how long the splash texts can go on for... not gonna bother finding the bee movie script you get a pass.",
			".     .     .",
			"The one above is in reference to The Quiet Place.",

			// umm..?
            "These splashes went from \"1d6 = this\" to \"uwu mfer :3\"",
            "meow :3",
			"Loading, please wait, system processing. Please follow the instructions on the pin-pad."
        );
    }

    public static String randomSplash() {
		return title + ": " + splashes.get(new Random().nextInt(0, splashes.size() - 1));
    }
}
