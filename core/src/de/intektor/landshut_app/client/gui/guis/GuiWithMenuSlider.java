package de.intektor.landshut_app.client.gui.guis;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import de.intektor.landshut_app.client.gui.Gui;
import de.intektor.landshut_app.client.gui.components.GuiButton;
import de.intektor.landshut_app.client.gui.components.GuiImageBasedButton;
import de.intektor.landshut_app.client.gui.components.GuiMenuSlider;

/**
 * @author Intektor
 */
public abstract class GuiWithMenuSlider extends Gui {

    protected GuiMenuSlider menuSlider;
    protected GuiImageBasedButton menuButton;

    private static final Texture menuButtonTexture = new Texture(Gdx.files.internal("assets/menu_button.png"));

    @Override
    public void enterGui() {
        super.enterGui();
        menuSlider = new GuiMenuSlider(0, 0, width / 5 * 3, height);
        menuButton = new GuiImageBasedButton(20, height - 90, 80, 60, menuButtonTexture);
        registerComponent(menuSlider);
        registerComponent(menuButton);
    }

    @Override
    protected void renderGui(int mouseX, int mouseY, OrthographicCamera camera, float partialTicks) {
        super.renderGui(mouseX, mouseY, camera, partialTicks);
    }

    @Override
    public void buttonCallback(GuiButton button) {
        super.buttonCallback(button);
        if (button == menuButton) {
            extendMenuSlider(!menuSlider.isExtended());
        }
    }

    @Override
    protected void pointerDown(int mouseX, int mouseY, int pointer, int button) {
        super.pointerDown(mouseX, mouseY, pointer, button);
    }

    @Override
    public void exitGui() {
        super.exitGui();
    }

    protected void extendMenuSlider(boolean extend) {
        menuSlider.setExtending(extend);
        menuButton.setShown(!extend);
        menuButton.setEnabled(!extend);
    }
}
