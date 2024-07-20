package net.onedsix.event;

import lombok.extern.slf4j.Slf4j;
import net.onedsix.util.GameSettings;

import java.util.*;

@Slf4j
public class SettingsChangeEventManager  {
	private static final List<SettingsChangeListener> listeners = new ArrayList<>();

    public static class SettingsChangeEvent extends EventObject {
        public SettingsChangeEvent(Object source) {
            super(source);
        }
    }

    public interface SettingsChangeListener extends EventListener {
        void settingsChanged(SettingsChangeEvent event, GameSettings.SettingsJson sj);
    }

    public static void addSettingsChangeListener(SettingsChangeListener listener) {
        listeners.add(listener);
    }

    public static void sendSettingsChangedEvent(GameSettings.SettingsJson sj) {
        SettingsChangeEvent event = new SettingsChangeEvent(SettingsChangeEventManager.class);
        log.info("Settings Changed event tripped");
        for (SettingsChangeListener listener : listeners) {
            listener.settingsChanged(event, sj);
        }
    }
}
