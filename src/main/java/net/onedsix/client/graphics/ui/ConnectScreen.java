package net.onedsix.client.graphics.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.esotericsoftware.kryonet.Client;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextButton;
import com.kotcrab.vis.ui.widget.VisTextField;
import lombok.extern.slf4j.Slf4j;
import net.onedsix.client.OnedsixClient;
import net.onedsix.util.Vars;

@Slf4j
public class ConnectScreen implements Screen {
	public final Stage stage = new Stage(new ScreenViewport());
	public final VisTable root = new VisTable();
    public final VisLabel addressLabel = new VisLabel("Address");
    public final VisTextField addressField = new VisTextField("localhost");
    public final VisLabel portLabel = new VisLabel("Port");
    public final VisTextField portField = new VisTextField("36000");
    public final VisLabel usernameLabel = new VisLabel("Username");
    public final VisTextField usernameField = new VisTextField("Username");
    public final VisTextButton connectButton = new VisTextButton("Connect");
    public final VisTextButton backButton = new VisTextButton("Back");
    public final VisLabel errorLabel = new VisLabel(null);

    public ConnectScreen() {
        stage.setViewport(Vars.viewportHUD);
        root.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        connectButton.addListener(new ClickListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                try {
                    Vars.network.start();
                    ((Client) Vars.network).connect(15000, addressField.getText(), Integer.parseInt(portField.getText()),  Integer.parseInt(portField.getText()));
                }
                catch (Exception e) {
                    errorLabel.setText(e.getMessage());
                    log.error(e.getMessage(), e);

                    super.touchUp(event, x, y, pointer, button);
                    super.setVisualPressed(false);
                    return; // Exit this method due to the failed connection
                }

                // If you get to here, the connection was successful
                super.touchUp(event, x, y, pointer, button);
                super.setVisualPressed(false);
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
        root.add(addressLabel).width(250).padBottom(20);
        root.add(addressField).width(250).padBottom(20).row();
        root.add(portLabel).width(250).padBottom(20);
        root.add(portField).width(250).padBottom(20).row();
        root.add(usernameLabel).width(250).padBottom(20);
        root.add(usernameField).width(250).padBottom(20).row();
        root.add(connectButton).width(250).padBottom(20);
        root.add(backButton).width(250).padBottom(20).row();
        root.add(errorLabel).colspan(2);
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
