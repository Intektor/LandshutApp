package de.intektor.landshut_app.client.gui.guis;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import de.intektor.landshut_app.client.gui.Gui;
import de.intektor.landshut_app.client.render.RenderUtils;

/**
 * @author Intektor
 */
public class GuiStartScreen extends Gui {

    private int ticksInGui;

    @Override
    public void enterGui() {
        super.enterGui();
    }

    @Override
    protected void updateGui(int mouseX, int mouseY) {
        super.updateGui(mouseX, mouseY);
        if (ticksInGui == 10) {
            app.enterGui(new GuiMainMenu());
        }
        ticksInGui++;
    }

    @Override
    protected void renderGui(int mouseX, int mouseY, OrthographicCamera camera, float partialTicks) {
        super.renderGui(mouseX, mouseY, camera, partialTicks);
        spriteBatch.begin();
        RenderUtils.drawString("Start Screen", app.defaultFont32, width / 2, height / 2, spriteBatch, Color.WHITE, true);
        spriteBatch.end();
    }

    @Override
    public void exitGui() {
        super.exitGui();
    }
}
