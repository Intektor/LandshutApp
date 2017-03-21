package de.intektor.landshut_app.client.gui.components;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import de.intektor.landshut_app.client.gui.GuiMultiComponent;
import de.intektor.landshut_app.client.render.RenderUtils;
import de.intektor.landshut_app.utils.i18n.I18n;

/**
 * @author Intektor
 */
public class GuiMenuSlider extends GuiMultiComponent {

    private int extending;
    private boolean extended;
    private int sourceX;

    private Color defaultColor = new Color(0.2f, 0.2f, 0.2f, 1);

    private GuiTextBasedButton buttonImageSelection;

    public GuiMenuSlider(int x, int y, int width, int height) {
        super(x, y, width, height);
        sourceX = x;
        setPosition(sourceX - width, y);
        buttonImageSelection = new GuiTextBasedButton(0, height - 250, width, 150, I18n.translate("gui.main_menu.button_image_selection.text"), false, defaultColor, defaultColor, Color.WHITE);
        registerGuiComponent(buttonImageSelection);
    }

    @Override
    protected void renderComponent(float drawX, float drawY, int mouseX, int mouseY, OrthographicCamera camera, ShapeRenderer sR, SpriteBatch sB, float partialTicks) {
        sR.begin(ShapeRenderer.ShapeType.Filled);
        sR.setColor(defaultColor);
        sR.rect(drawX, drawY, width, height);
        sR.end();
        sB.begin();
        RenderUtils.drawString("Landshut App", app.defaultFont54, drawX + 10, drawY + height - 32, sB, Color.WHITE);
        sB.end();
        super.renderComponent(drawX, drawY, mouseX, mouseY, camera, sR, sB, partialTicks);
    }

    @Override
    protected void updateComponent(int mouseX, int mouseY, float drawX, float drawY) {
        super.updateComponent(mouseX, mouseY, drawX, drawY);
        if (extending != 0) {
            int extendSpeed = 40;
            moveComponent(extending == 1 ? extendSpeed : -extendSpeed, y);
            if (this.x >= sourceX) {
                setPosition(0, y);
                extending = 0;
                extended = true;
            } else if (this.x <= sourceX - this.width) {
                setPosition(sourceX - this.width, y);
                extending = 0;
                extended = false;
            }
        }
    }

    @Override
    public void setShown(boolean shown) {
        super.setShown(shown);
    }

    public void setExtending(boolean extend) {
        extending = extend ? 1 : -1;
    }

    public boolean isExtended() {
        return extended;
    }

    public boolean isExtending() {
        return extending != 0;
    }
}
