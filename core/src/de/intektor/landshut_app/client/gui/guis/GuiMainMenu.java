package de.intektor.landshut_app.client.gui.guis;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import de.intektor.landshut_app.client.GuiUtils;
import de.intektor.landshut_app.client.render.RenderUtils;

/**
 * @author Intektor
 */
public class GuiMainMenu extends GuiWithMenuSlider {

    private static final Texture martinskirche = new Texture(Gdx.files.internal("assets/martinskirche_1.png"));

    private int pointerDownX, pointerDownY;
    private int pointerDownTicks = -1;

    @Override
    public void enterGui() {
        super.enterGui();
    }

    @Override
    protected void renderGui(int mouseX, int mouseY, OrthographicCamera camera, float partialTicks) {
        spriteBatch.begin();
        spriteBatch.draw(martinskirche, 0, 0, width, height);
        spriteBatch.end();
        if (pointerDownTicks != -1) {
            RenderUtils.enableBlending();
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.setColor(new Color(0.5f, 0.5f, 0.5f, 0.3f));
            shapeRenderer.circle(pointerDownX, pointerDownY, pointerDownTicks * 3);
            shapeRenderer.end();
            RenderUtils.disableBlending();
        }
        super.renderGui(mouseX, mouseY, camera, partialTicks);
    }

    @Override
    protected void updateGui(int mouseX, int mouseY) {
        super.updateGui(mouseX, mouseY);
        if (pointerDownTicks != -1) pointerDownTicks++;
    }

    @Override
    protected void pointerDown(int mouseX, int mouseY, int pointer, int button) {
        super.pointerDown(mouseX, mouseY, pointer, button);
        if (menuSlider.isExtended()) {
            if (GuiUtils.isPointInRegion(menuSlider.getX() + menuSlider.getWidth(), 0, width - menuSlider.getWidth(), height, mouseX, mouseY)) {
                extendMenuSlider(false);
            }
        } else if (!menuSlider.isExtending()) {
            pointerDownTicks = 0;
            pointerDownX = mouseX;
            pointerDownY = mouseY;
        }
    }

    @Override
    protected void pointerUp(int mouseX, int mouseY, int pointer, int button) {
        super.pointerUp(mouseX, mouseY, pointer, button);
        if (pointerDownTicks != -1) {
            pointerDownTicks = -1;
            extendMenuSlider(true);
        }
    }

    @Override
    public void exitGui() {
        super.exitGui();
    }
}
