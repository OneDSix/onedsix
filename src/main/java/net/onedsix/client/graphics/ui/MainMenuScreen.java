package net.onedsix.client.graphics.ui;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextButton;
import lombok.extern.slf4j.Slf4j;
import net.onedsix.util.Vars;
import net.onedsix.client.OnedsixClient;

@Slf4j
public class MainMenuScreen implements Screen {
	public final Stage stage = new Stage(new ScreenViewport());
	public final VisTable root = new VisTable();
    public final VisTextButton singlePlayerButton = new VisTextButton("Singleplayer");
    public final VisTextButton multiPlayerButton = new VisTextButton("Multiplayer");
    public final VisTextButton settingsButton = new VisTextButton("Settings");
    public final VisTextButton modsButton = new VisTextButton("Mods");
    public final VisTextButton exitButton = new VisTextButton("Exit");

    public MainMenuScreen() {
        stage.setViewport(Vars.viewportHUD);
        root.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        singlePlayerButton.addListener(new ClickListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				OnedsixClient.getClient().getUserInterface().dispose();
                OnedsixClient.getClient().setUI(new SinglePlayerScreen());
            }
        });

        multiPlayerButton.addListener(new ClickListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				OnedsixClient.getClient().getUserInterface().dispose();
                OnedsixClient.getClient().setUI(new ConnectScreen());
            }
        });

        settingsButton.addListener(new ClickListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				OnedsixClient.getClient().getUserInterface().dispose();
                OnedsixClient.getClient().setUI(new SettingsScreen());
            }
        });

        modsButton.addListener(new ClickListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				OnedsixClient.getClient().getUserInterface().dispose();
                OnedsixClient.getClient().setUI(new ModListScreen());
            }
        });

        exitButton.addListener(new ClickListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				OnedsixClient.getClient().getUserInterface().dispose();
                Gdx.app.exit();
            }
        });

        stage.addActor(root);
        root.clear();
        root.padLeft(Vars.viewportHUD.getWorldWidth()*0.1f).left();
        root.add(singlePlayerButton).width(250).padBottom(20).row();
        root.add(multiPlayerButton).width(250).padBottom(20).row();
        root.add(settingsButton).width(250).padBottom(20).row();
        root.add(modsButton).width(250).padBottom(20).row();
        root.add(exitButton).width(250).padBottom(20).row();
    }

	@Override
	public void show() {
		Gdx.input.setInputProcessor(stage);
	}

	@Override
	public void render(float delta) {
		this.stage.act(delta);
		this.stage.draw();
	}

	@Override public void resize(int width, int height) {
		Vars.viewportHUD.update(width, height, true);
	}

	@Override public void pause() {}

	@Override public void resume() {}

	@Override public void hide() {}

	@Override public void dispose() {
		this.stage.dispose();
	}
}
