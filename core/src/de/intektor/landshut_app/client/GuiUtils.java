package de.intektor.landshut_app.client;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import de.intektor.landshut_app.LandshutAppClient;

import javax.vecmath.Point2f;

/**
 * @author Intektor
 */
public class GuiUtils {

    private static LandshutAppClient app = LandshutAppClient.getAppInstance();

    public static boolean isPointInRegion(float x, float y, float width, float height, float pX, float pY) {
        return pX >= x && pX <= x + width && pY >= y && pY <= y + height;
    }

    public static int unscaleScreenCoordX(float x) {
        double scale = app.getPreferredScreenWidth() / (double) Gdx.graphics.getWidth();
        return (int) (x / scale);
    }

    public static int unscaleScreenCoordY(float y) {
        double scale = app.getPreferredScreenHeight() / (double) Gdx.graphics.getHeight();
        return (int) (y / scale);
    }

    public static MousePos unprojectMousePosition(OrthographicCamera camera, int mouseX, int mouseY) {
        Vector3 unproject = camera.unproject(new Vector3(mouseX, mouseY, 0));
        return new MousePos(unproject.x, unproject.y);
    }

    public static MousePos unprojectMousePosition(OrthographicCamera camera) {
        return unprojectMousePosition(camera, Gdx.input.getX(), Gdx.input.getY());
    }

    public static Point2f projectWorldPosition(OrthographicCamera camera, float posX, float posY) {
        Vector3 project = camera.project(new Vector3(posX, posY, 0));
        float scaleX = (float) (app.getPreferredScreenWidth() / (double) Gdx.graphics.getWidth());
        float scaleY = (float) (app.getPreferredScreenHeight() / (double) Gdx.graphics.getHeight());
        return new Point2f(project.x * scaleX, project.y * scaleY);
    }

    public static int scaleMouseX() {
        return scaleMouseX(Gdx.input.getX());
    }

    public static int scaleMouseY() {
        return scaleMouseY(Gdx.input.getY());
    }

    public static int scaleMouseX(int mouseX) {
        double scale = app.getPreferredScreenWidth() / (double) Gdx.graphics.getWidth();
        return (int) (mouseX * scale);
    }

    public static int scaleMouseY(int mouseY) {
        double scale = app.getPreferredScreenHeight() / (double) Gdx.graphics.getHeight();
        return app.getPreferredScreenHeight() - (int) ((mouseY * scale));
    }
}
