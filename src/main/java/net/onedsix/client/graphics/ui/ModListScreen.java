package net.onedsix.client.graphics.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.kotcrab.vis.ui.widget.*;
import lombok.extern.slf4j.Slf4j;
import net.onedsix.util.Vars;
import net.onedsix.client.OnedsixClient;

@Slf4j
public class ModListScreen implements Screen {
	public final Stage stage = new Stage(new ScreenViewport());
	public final VisTable root = new VisTable();
	public final VisScrollPane scroll = new VisScrollPane(null);
    private final VisTable leftTable = new VisTable();
    private final VisTextButton backButton = new VisTextButton("Back");
    private final VisTable modList = new VisTable();
    private final VisTable modListSubRoot = new VisTable();
    private final VisTable modInfo = new VisTable();
    private final VisLabel modLabel = new VisLabel("Installed");

    public ModListScreen() {
        stage.setViewport(Vars.viewportHUD);
        root.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        stage.addActor(root);
        root.clear();
        root.setDebug(true, true);
        root.setSize(Vars.viewportHUD.getWorldWidth()/3, Vars.viewportHUD.getWorldHeight()/2);

        root.add(leftTable).size(root.getWidth()*.25f, root.getHeight());
        root.add(modInfo).size(root.getWidth()*.75f, root.getHeight()).row();

        modLabel.setFontScale(1f);
        leftTable.add(modLabel).row();
        leftTable.add(backButton).padBottom(20).row();
        backButton.addListener(new ClickListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				OnedsixClient.getClient().getUserInterface().dispose();
                OnedsixClient.getClient().setUI(new MainMenuScreen());
            }
        });
        leftTable.add(scroll).row();

        scroll.setActor(modList);

        VisTable modTable = new VisTable();
        modList.add(modTable);

        // TODO: make this loop over all the mods and grab the needed data
        modTable.add(new VisImage(new Texture(Gdx.files.internal("assets/badlogic.jpg"))))
                .size((float) (scroll.getWidth()*.33), (float) (scroll.getWidth()*.33));
        modTable.add(modListSubRoot).row();
        modTable.addListener(new ClickListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                log.info("clicked");
                super.touchUp(event, x, y, pointer, button);
            }
        });

        modListSubRoot.add("name").height(10).row();
        modListSubRoot.add("mod version").height(10).row();
        modListSubRoot.add("first credit").height(10).row();

        modInfo.add("name").height(10).row();
        modInfo.add("mod version").height(10).row();
        modInfo.add("first credit").height(10).row();
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
