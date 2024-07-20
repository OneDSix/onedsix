package net.onedsix.client.graphics.environments;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.*;
import com.badlogic.gdx.graphics.g3d.attributes.BlendingAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.DepthTestAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import lombok.extern.slf4j.Slf4j;
import net.onedsix.util.GeometryHandler;

import java.util.ArrayList;
import java.util.List;

import static net.onedsix.util.Vars.*;

@Slf4j
public class MainMenuEnvironment implements Screen {
	public Environment environment = new Environment();
	/** Handles everything to do with the Version Geometry, Players, Some GUI elements, etc. */
	public ModelBatch modelBatch = new ModelBatch();
	/** A list of all the loaded models. Will get quite large! */
	public List<ModelInstance> modelInstances = new ArrayList<>();
	public MainMenuEnvironment() {
		log.info("Creating Main Menu Environment");
		this.environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));
		this.environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f));

		Texture t;

		t = new Texture(Gdx.files.internal("assets/grass.jpg"), Pixmap.Format.RGB565, true);
		t.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
		t.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

		long miscAttr = VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal | VertexAttributes.Usage.TextureCoordinates;
		Attribute[] matAttr = {
			TextureAttribute.createDiffuse(t),
			new BlendingAttribute(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA),
			new DepthTestAttribute(false)
		};

		Model model = modelBuilder.createBox(50, 1, 50,
			new Material(matAttr),
			miscAttr);
		modelInstances.add(GeometryHandler.transformShorthand(0,-10,0, new ModelInstance(model)));

		cam3D.position.set(10,10,0);
		cam3D.lookAt(0,0,0);
		cam3D.near = 1;
		cam3D.far = 100;
		cam3D.update(true);
	}

	@Override
	public void show() {}

	@Override
	public void render(float delta) {
		this.modelBatch.begin(cam3D);
		for (ModelInstance m : modelInstances) {
			modelBatch.render(m, this.environment);
		}
		this.modelBatch.end();
	}

	@Override
	public void resize(int width, int height) {
		viewport3D.update(width, height);
	}

	@Override
	public void pause() {}

	@Override
	public void resume() {}

	@Override
	public void hide() {}

	@Override
	public void dispose() {
		//modelBatch.dispose();
		//modelInstances.forEach((modelInstance) -> {
		//	modelInstance.model.dispose();
		//});
	}
}
