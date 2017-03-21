package de.intektor.landshut_app.client;

/**
 * @author Intektor
 */
public class MousePos {

    public float x, y;

    public MousePos(float x, float y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return String.format("x: %s | y: %s", x, y);
    }
}
