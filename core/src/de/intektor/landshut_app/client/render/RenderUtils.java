package de.intektor.landshut_app.client.render;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
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

    public static void enableBlending() {
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
    }

    public static void disableBlending() {
        Gdx.gl.glDisable(GL20.GL_BLEND);
    }
}
