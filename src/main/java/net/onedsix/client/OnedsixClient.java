package net.onedsix.client;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.kotcrab.vis.ui.VisUI;
import lombok.extern.slf4j.Slf4j;
import net.onedsix.client.graphics.environments.MainMenuEnvironment;
import net.onedsix.client.graphics.ui.MainMenuScreen;
import net.onedsix.util.EnvType;
import net.onedsix.util.FileHandler;
import net.onedsix.util.Vars;

import static net.onedsix.event.SettingsChangeEventManager.*;
import static net.onedsix.util.GameSettings.*;

@Slf4j
public class OnedsixClient implements ApplicationListener, SettingsChangeListener {
	/**
	 * The background that's show upon launch. Easily editable via mixins if you choose to change this.
	 * */
	public static Class<?> LAUNCH_ENV = MainMenuEnvironment.class;
	/**
	 * The UI that's show upon launch. Easily editable via mixins if you choose to change this.
	 * */
	public static Class<?> LAUNCH_UI = MainMenuScreen.class;
	protected Screen userInterface;
	protected Screen environment;

    @Override public void create() {

        // Load UI backend now because Vars needs it
        VisUI.load(VisUI.SkinScale.X1);

        // Load Client Variables
        Vars.clientInit();

        // Settings
        addSettingsChangeListener(OnedsixClient.this);
        readSettings();

        // Splash Text
        Gdx.graphics.setTitle(Title.title);
        if (Vars.envType.getEnv() == EnvType.ValidEnvironments.DESKTOP) {
            Gdx.graphics.setTitle(Title.randomSplash());
        }

        // Create directories
        FileHandler.createDirectory("./temp/");
        FileHandler.createDirectory("./screenshots/");

        // Create and Set Screens
		try {
			this.setEnvironment((Screen) LAUNCH_ENV.getDeclaredConstructor().newInstance());
			this.setUI((Screen) LAUNCH_UI.getDeclaredConstructor().newInstance());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

    @Override public void render() {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
		if (this.environment != null) this.environment.render(Gdx.graphics.getDeltaTime());
		if (this.userInterface != null) userInterface.render(Gdx.graphics.getDeltaTime());
    }

    @Override public void dispose() {
		if (environment != null) environment.hide();
		if (userInterface != null) userInterface.hide();
        log.info("Stopped");
    }

    @Override public void resize(int width, int height) {
		if (environment != null) environment.resize(width, height);
		if (userInterface != null) userInterface.resize(width, height);
    }

    @Override public void pause() {
		if (environment != null) environment.pause();
		if (userInterface != null) userInterface.pause();
        Vars.shouldAttemptRendering = false;
        log.info("Paused");
    }

    @Override public void resume() {
		if (environment != null) environment.resume();
		if (userInterface != null) userInterface.resume();
        Vars.shouldAttemptRendering = true;
        log.info("Unpaused");
    }

    /** Event listener for when settings are changed. */
    @Override public void settingsChanged(SettingsChangeEvent event, SettingsJson sj) {
        log.info("Refreshing settings...");
        Gdx.graphics.setForegroundFPS(Vars.settings.targetFps);
        Gdx.graphics.setVSync(Vars.settings.useVsync);
    }

    public static OnedsixClient getClient() {
        return (OnedsixClient) Gdx.app.getApplicationListener();
    }

	/** Sets the current scene. {@link Screen#hide()} is called on any old scene, and {@link Screen#show()} is called on the new
	 * scene, if any.
	 * @param environment may be {@code null} */
	public void setEnvironment(Screen environment) {
		if (this.environment != null) this.environment.hide();
		this.environment = environment;
		if (this.environment != null) {
			this.environment.show();
			this.environment.resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		}
	}

	/** @return the currently active Environment {@link Screen}. */
	public Screen getEnvironment() {
		return environment;
	}

	/** Sets the current UI. {@link Screen#hide()} is called on any old UI, and {@link Screen#show()} is called on the new
	 * UI, if any.
	 * @param ui may be {@code null} */
	public void setUI(Screen ui) {
		if (this.userInterface != null) this.userInterface.hide();
		this.userInterface = ui;
		if (this.userInterface != null) {
			this.userInterface.show();
			this.userInterface.resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		}
	}

	/** @return the currently active UI {@link Screen}. */
	public Screen getUserInterface() {
		return userInterface;
	}
}
