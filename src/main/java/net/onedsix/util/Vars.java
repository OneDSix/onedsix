package net.onedsix.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g3d.utils.*;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.*;
import com.esotericsoftware.kryonet.*;
import com.google.gson.Gson;
import com.moandjiezana.toml.Toml;
import lombok.extern.slf4j.Slf4j;
import net.onedsix.client.input.KeyCalls;
import net.onedsix.client.OnedsixClient;
import net.onedsix.assets.Asset;
import net.onedsix.registry.Identifier;
import net.onedsix.client.ClientPlayer;
import org.slf4j.LoggerFactory;

import javax.script.*;
import java.lang.reflect.Method;
import java.util.*;

import static net.onedsix.util.GameSettings.SettingsJson;

/**
 * Where a majority of the game is stored, alongside a short description of the usage of that variable/method.<br>
 * The idea for this setup was taken from Mindustry.
 * */
@Slf4j
public class Vars {
    /** Internal ID, used for tasks that require an {@link Identifier} and/or mod compatibility */
    public static final String ONEDSIX_ID = "onedsix";
    /** Current environment the game is running in. */
    public static EnvType envType;
    /** Preferred language of the user. */
    public static Locale locale = Locale.getDefault();
    /** Global Gson Instance to save on memory. */
    public static final Gson GSON = new Gson();
    /** Settings for the game. Is {@code null} until later in the startup process. */
    public static SettingsJson settings;
    /** 3D Camera. */
    public static PerspectiveCamera cam3D;
    /** 2D Camera, used in GUIs and very specific edge-cases. */
    public static OrthographicCamera camHUD;
    /** Camera Offset for all decals to look at */
    public static Vector3 camOffset;
    /**
     * Camera Controller.
     * @see Vars#cam3D
     * */
    public static CameraInputController camController;
    /** OpenGL Viewport. Used for rendering 3D objects */
    public static Viewport viewport3D;
    /** OpenGL Viewport. Used for rendering UIs and Sprites */
    public static Viewport viewportHUD;
    /**
     * Builds models
     * @see GeometryHandler GeometryHandler
     * */
    public static final ModelBuilder modelBuilder = new ModelBuilder();
    /** If the game is docked, or no longer visible, pause rendering to save on resources. */
    public static boolean shouldAttemptRendering = true;
    /** Debug mode toggle, shows an F3-like display on the screen.<br>Not development mode! */
    public static boolean debugMode = false;
    /** Variable used to store the size of the window, as well as the last time it was logged.<br>
     * 0 = width<br>
     * 1 = height<br>
     * 2 = miliseconds since last resized<br>
     * 3 = has the resize been logged yet<br>
     * @see OnedsixClient#resize(int, int)
     * */
    public static List<Integer> windowSize = new LinkedList<>();
    /**
     * If the game should be checking keystrokes.
     * @see KeyCalls
     * */
    public static KeyCalls keyCalls;
    /** The player instance, created at startup */
    public static ClientPlayer player;
    /** The global KyroNet instance.<br>Checking if it is {@link Client} or {@link Server} is necessary at all times. */
    public static EndPoint network;
    /**
     * The target server for both HTTP and TCP connections.
     * @see Vars#targetPort
     * */
    public static String targetHostname = "localhost";
    /** Target Port
     * @see Vars#targetHostname
     * */
    public static int targetPort = 36000;
    /** Milliseconds to try to reconnect to a connected server. */
    public static final int RECONNECT_DELAY_MS = 5000;
    /**
     * Hostname of the central server.<br> Used for server scanning, authentication, and a few other small services.
     * @see Vars#centralPort
     * */
    public static String centralHostname;
    /** Port of the central server.
     * @see Vars#centralHostname
     * */
    public static int centralPort = 80;
    /** Used for zip mods. */
    public static ScriptEngine scriptEngine;

    /** Init variables only meant for the OnedsixClient. */
    public static void clientInit() {
        envType = new EnvType(EnvType.ValidEnvironments.DESKTOP);
        cam3D = new PerspectiveCamera(90, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camHUD = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camController = new CameraInputController(cam3D);
        viewport3D = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), cam3D);
        viewportHUD = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camHUD);
        keyCalls = new KeyCalls();
        network = new Client();
        globalInit();
    }

    /** Init variables only meant for Servers. */
    public static void serverInit() {
        envType = new EnvType(EnvType.ValidEnvironments.SERVER);
        network = new Server();
        globalInit();
    }

    /** Init variables meant for both. */
    static void globalInit() {
        scriptEngine = new ScriptEngineManager().getEngineByName("nashorn");
        scriptEngine.put("console", LoggerFactory.getLogger(ScriptEngine.class));
        scriptEngine.put("Class", Class.class);
        scriptEngine.put("Method", Method.class);
        scriptEngine.put("Gson", Gson.class);
        scriptEngine.put("Toml", Toml.class);
        scriptEngine.put("Asset", Asset.class);
		/*
		scriptEngine.put("TypeDescription", TypeDescription.class);
		scriptEngine.put("DynamicType", DynamicType.class);
		scriptEngine.put("TypeResolution", TypeDescription.Generic.class);
		scriptEngine.put("ByteBuddy", ByteBuddy.class);
	 	*/
    }
}
