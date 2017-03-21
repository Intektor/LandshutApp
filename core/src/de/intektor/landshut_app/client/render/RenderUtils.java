package de.intektor.landshut_app.client.render;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * @author Intektor
 */
public class RenderUtils {

    public static void drawString(String text, BitmapFont font, float x, float y, SpriteBatch spriteBatch, Color color) {
        drawString(text, font, x, y, spriteBatch, color, false);
    }

    public static void drawString(String text, BitmapFont font, float x, float y, SpriteBatch spriteBatch, Color color, boolean centered) {
        drawString(text, font, x, y, spriteBatch, color, centered, centered);
    }

    public static void drawString(String text, BitmapFont font, float x, float y, SpriteBatch spriteBatch, Color color, boolean centerX, boolean centerY) {
        font.setColor(color);
        float rx = centerX ? x - FontUtils.getStringWidth(text, font) / 2 : x;
        float ry = centerY ? y + FontUtils.getStringHeight(text, font) / 2 : y;
        font.draw(spriteBatch, text, rx, ry);
    }
}
