package net.onedsix.client.graphics.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.*;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.kotcrab.vis.ui.widget.*;
import net.onedsix.util.Vars;
import net.onedsix.client.OnedsixClient;

public class SinglePlayerScreen implements Screen {
	public final Stage stage = new Stage(new ScreenViewport());
	public final VisTable root = new VisTable();
    public final VisTable lanTable = new VisTable();
    public final VisRadioButton lanButton = new VisRadioButton("Enable LAN");
    public final VisLabel portLabel = new VisLabel("Port");
    public final VisTextField portField = new VisTextField("36000");
    public final VisLabel passLabel = new VisLabel("Password");
    public final VisTextField passField = new VisTextField("OnlyYouShouldKnowThis");
    public final VisTextButton backButton = new VisTextButton("Back");
    public final VisLabel errorLabel = new VisLabel(null);

    public SinglePlayerScreen() {
        stage.setViewport(Vars.viewportHUD);
        root.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        lanButton.addListener(new ChangeListener() {
            @Override
            public void changed (ChangeEvent event, Actor actor) {
                portField.setDisabled(!portField.isDisabled());
                passField.setDisabled(!passField.isDisabled());
            }
        });

        backButton.addListener(new ClickListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				OnedsixClient.getClient().getUserInterface().dispose();
                OnedsixClient.getClient().setUI(new MainMenuScreen());
            }
        });

        stage.addActor(root);
        root.clear();
        root.padLeft(Vars.viewportHUD.getWorldWidth()*0.1f).left();

        root.add(lanButton).row();
        root.add(lanTable).row();

        lanTable.add(portLabel).width(250).padBottom(20);
        lanTable.add(portField).width(250).padBottom(20).row();
        portField.setDisabled(true);
        lanTable.add(passLabel).width(250).padBottom(20);
        lanTable.add(passField).width(250).padBottom(20).row();
        passField.setDisabled(true);

        root.add(backButton).width(250).padBottom(20).row();
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
