package de.intektor.landshut_app;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import de.intektor.landshut_app.client.gui.Gui;
import de.intektor.landshut_app.client.render.RenderUtils;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class LandshutAppClient extends ApplicationAdapter {

    private static LandshutAppClient appInstance;

    private Queue<Runnable> scheduledTasks = new LinkedBlockingQueue<Runnable>();

    private long lastTickTime;

    private OrthographicCamera camera;
    private Viewport viewport;

    public BitmapFont defaultFont12;

    private final int preferredScreenWidth = 1920;
    private final int preferredScreenHeight = 1080;

    private ShapeRenderer defaultShapeRenderer;
    private SpriteBatch defaultSpriteBatch;

    private Gui currentGui;

    private float partialTicks;

    @Override
    public void create() {
        appInstance = this;

        camera = new OrthographicCamera(preferredScreenWidth, preferredScreenHeight);
        viewport = new FillViewport(preferredScreenWidth, preferredScreenHeight, camera);
        viewport.apply(false);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);

        FreeTypeFontGenerator gen = new FreeTypeFontGenerator(Gdx.files.internal("arial.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter param = new FreeTypeFontGenerator.FreeTypeFontParameter();
        param.size = 12;
        defaultFont12 = gen.generateFont(param);
        gen.dispose();

        defaultShapeRenderer = new ShapeRenderer();
        defaultShapeRenderer.setAutoShapeType(true);
        defaultShapeRenderer.setProjectionMatrix(camera.combined);
        defaultSpriteBatch = new SpriteBatch();
        defaultSpriteBatch.setProjectionMatrix(camera.combined);
        defaultSpriteBatch.setBlendFunction(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
    }

    @Override
    public void render() {
        camera.update();
        if (System.currentTimeMillis() - lastTickTime >= 15.625D) {
            lastTickTime = System.currentTimeMillis();
            updateGame();
        }
        renderGame();
    }

    /**
     * The updateWorld method of the game: Called 64 times per second
     */
    private void updateGame() {
        Runnable r;
        while ((r = scheduledTasks.poll()) != null) {
            r.run();
        }
        if (currentGui != null) currentGui.update(Gdx.input.getX(), Gdx.input.getY());
    }

    /**
     * The render method of the game: Called as often as could
     */
    private void renderGame() {
        //Clear the last frame
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        defaultShapeRenderer.setProjectionMatrix(camera.combined);
        defaultSpriteBatch.setProjectionMatrix(camera.combined);
        partialTicks = (System.currentTimeMillis() - lastTickTime) / (15.625f);
        SpriteBatch spriteBatch = new SpriteBatch();
        spriteBatch.begin();
        RenderUtils.drawString(Gdx.graphics.getFramesPerSecond() + "", defaultFont12, 10, getPreferredScreenHeight() / 2, spriteBatch, Color.WHITE);
        spriteBatch.end();
        spriteBatch.dispose();
        if (currentGui != null) currentGui.render(Gdx.input.getX(), Gdx.input.getY(), camera, partialTicks);
    }

    public void showGui(Gui gui) {
        if (currentGui != null) currentGui.exitGui();
        currentGui = gui;
        currentGui.enterGui();
    }

    @Override
    public void dispose() {

    }

    public static LandshutAppClient getAppInstance() {
        return appInstance;
    }

    public int getPreferredScreenHeight() {
        return preferredScreenHeight;
    }

    public int getPreferredScreenWidth() {
        return preferredScreenWidth;
    }

    public ShapeRenderer getDefaultShapeRenderer() {
        return defaultShapeRenderer;
    }

    public SpriteBatch getDefaultSpriteBatch() {
        return defaultSpriteBatch;
    }

    public OrthographicCamera getDefaultCamera() {
        return camera;
    }
}
